package ru.carbay.promoter.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.carbay.promoter.ds.ProxyDS;
import ru.carbay.promoter.ds.SiteScanDS;
import ru.carbay.promoter.model.Offer;
import ru.carbay.promoter.model.Proxy;
import ru.carbay.promoter.model.SiteScan;
import ru.carbay.promoter.scanners.AutoruScanner;
import ru.carbay.promoter.services.pager_duty.PagerDutyAlerts;
import ru.carbay.promoter.services.proxy.ProxyList;

import java.util.*;

@Deprecated
public class BestProxyDriver {

    public static void scan(SiteScan siteScan) throws Exception {
        WebDriver webDriver = getDriver(null);

        try {
            siteScan.setStarted(new Date());
            List<Offer> offers = new ArrayList<>();
            for (String url : siteScan.getPageUrls()) {
                boolean pageScanCompleted = false;
                boolean scanCompleted = false;
                while (!pageScanCompleted) {
                    List<Offer> pageOffers;
                    if (siteScan.getSite().equalsIgnoreCase("auto.ru")) {
                        pageOffers = AutoruScanner.scan(url, siteScan.getBrand(), webDriver).getOffers();
                    } else if (siteScan.getSite().equalsIgnoreCase("avito.ru")) {
                        throw new Exception("Unknown site: " + siteScan.getSite());
                    } else if (siteScan.getSite().equalsIgnoreCase("drom.ru")) {
                        throw new Exception("Unknown site: " + siteScan.getSite());
                    } else {
                        throw new Exception("Unknown site: " + siteScan.getSite());
                    }

                    if (pageOffers.size() < 1) {
                        PagerDutyAlerts.alert("Stop scanning", "0 offers on the page: " + url);
                        System.out.println("--> 0 offers on the page " + url);
                        System.out.println("--> 'rescan' to scan again");
                        System.out.println("--> 'proxy' to change proxy and scan again");
                        System.out.println("--> 'stop' to stop this scan");
                        Scanner sc = new Scanner(System.in);
                        boolean ok = false;
                        while (!ok) {
                            String typed = sc.next();
                            if (typed.equals("rescan")) {
                                ok = true;
                            } else if (typed.equals("proxy")) {
                                webDriver = getDriver(webDriver);
                                ok = true;
                            } else if (typed.equals("stop")) {
                                pageScanCompleted = true;
                                scanCompleted = true;
                                ok = true;
                            }
                        }
                    } else {
                        if (siteScan.getSite().equalsIgnoreCase("auto.ru") && pageOffers.size() < 37) {
                            scanCompleted = true;
                        }
                        offers.addAll(pageOffers);
                        pageScanCompleted = true;
                    }
                }

                if (scanCompleted) break;
            }

            siteScan.setOffers(offers);
            siteScan.setCompleted(new Date());
            siteScan.setStatus("completed");
            SiteScanDS.save(siteScan);
        } catch (Throwable t) {
            System.out.println(t.getMessage());
            t.printStackTrace();
        } finally {
            if (webDriver != null) {
                webDriver.close();
            }
        }
    }

    private static WebDriver getDriver(WebDriver lastProxyDriver) throws Exception {
        List<Proxy> freshProxies = ProxyList.usProxyOrg();
        List<Proxy> usedProxies = ProxyDS.getAllProxies();
        Map<String, Proxy> usedProxyMap = new HashMap<>();
        usedProxies.forEach((proxy -> {
            usedProxyMap.put(proxy.getIpAdress() + ":" + proxy.getPort(), proxy);
        }));

        Proxy bestProxy = null;
        for (Proxy freshProxy : freshProxies) {
            Proxy usedProxy = usedProxyMap.get(freshProxy.getIpAdress() + ":" + freshProxy.getPort());
            if (usedProxy == null) {
                bestProxy = freshProxy;
                break;
            } else if (bestProxy == null) {
                bestProxy = usedProxy;
            } else if (bestProxy.getLastUsage().after(usedProxy.getLastUsage())) {
                bestProxy = usedProxy;
            }
        }
        bestProxy.setLastUsage(new Date());
        ProxyDS.save(bestProxy);

        String PROXY = bestProxy.getIpAdress();
        int PORT = Integer.valueOf(bestProxy.getPort());
        com.google.gson.JsonObject json = new com.google.gson.JsonObject();
        json.addProperty("proxyType", "MANUAL");
        json.addProperty("httpProxy", PROXY);
        json.addProperty("httpProxyPort", PORT);
        json.addProperty("sslProxy", PROXY);
        json.addProperty("sslProxyPort", PORT);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("proxy", json);
        cap.setAcceptInsecureCerts(true);

        if (lastProxyDriver != null) {
            lastProxyDriver.close();
        }
        WebDriver webDriver = new FirefoxDriver(cap);
        return webDriver;
    }
}
