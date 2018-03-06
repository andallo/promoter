package ru.carbay.promoter.drivers;

import org.openqa.selenium.WebDriver;
import ru.carbay.promoter.ds.OfferDS;
import ru.carbay.promoter.ds.ProxyDS;
import ru.carbay.promoter.ds.SiteScanDS;
import ru.carbay.promoter.model.*;
import ru.carbay.promoter.model.Proxy;
import ru.carbay.promoter.model.SiteScan;
import ru.carbay.promoter.scanners.AutoruScanner;
import ru.carbay.promoter.scanners.AvitoScanner;
import ru.carbay.promoter.services.pager_duty.PagerDutyAlerts;
import ru.carbay.promoter.utils.*;

import java.util.*;

public abstract class AbstractDriver {

    private static final int MAX_OFFERS_TO_SCAN = 1000;
    private static final int MAX_PAGES_TO_SCAN = 25;

    private static String previousBrand = "";

    public void scan(SiteScan siteScan) throws Exception {
        try {
            siteScan.setStarted(new Date());
            List<Offer> offers = new ArrayList<>();
            int currentPage = 0;
            boolean scanCompleted = false;
            while (!scanCompleted && offers.size() < MAX_OFFERS_TO_SCAN && currentPage < MAX_PAGES_TO_SCAN) {
                ++currentPage;
                String url = siteScan.getPageUrls().get(0);
                if (currentPage > 1) {
                    if (siteScan.getSite().equalsIgnoreCase("auto.ru")) {
                        url = AutoruSiteScanBuilder.pageUrl_byPage(url, currentPage);
                    } else if (siteScan.getSite().equalsIgnoreCase("avito.ru")) {
                        url = AvitoSiteScanBuilder.pageUrl_byPage(url, currentPage);
                    } else if (siteScan.getSite().equalsIgnoreCase("drom.ru")) {
                        throw new Exception("Unknown site: " + siteScan.getSite());
                    } else {
                        throw new Exception("Unknown site: " + siteScan.getSite());
                    }
                    siteScan.getPageUrls().add(url);
                }

                boolean pageScanCompleted = false;
                while (!pageScanCompleted) {
                    ScanResult scanResult;

                    boolean isBrandChanged = !previousBrand.equals(siteScan.getBrand());
                    previousBrand = siteScan.getBrand();

                    Pair<Proxy, WebDriver> pair = getProxyAndDriver(false, isBrandChanged);
                    Proxy proxy = pair.getFirst();
                    WebDriver webDriver = pair.getSecond();

                    Date startScanDate = new Date();
                    if (siteScan.getSite().equalsIgnoreCase("auto.ru")) {
                        scanResult = AutoruScanner.scan(currentPage, url, siteScan.getBrand(), siteScan.getModel(), webDriver);
                    } else if (siteScan.getSite().equalsIgnoreCase("avito.ru")) {
                        scanResult = AvitoScanner.scan(url, siteScan.getBrand(), siteScan.getModel(), webDriver);
                    } else if (siteScan.getSite().equalsIgnoreCase("drom.ru")) {
                        throw new Exception("Unknown site: " + siteScan.getSite());
                    } else {
                        throw new Exception("Unknown site: " + siteScan.getSite());
                    }

                    Date finishScanDate = new Date();
                    if (scanResult.getOffers().size() < 1) {
                        if (proxy != null) {
                            proxy.getErrorDates().add(TimeUtils.nowMoscowTimeAsString());
                            ProxyDS.save(proxy);
                        }
                        PagerDutyAlerts.alert((proxy == null ? "" : proxy.getIpAdress() + " ") + "stop scanning", "0 offers on the page: " + url);
                        System.out.println("--> 0 offers on the page " + url);
                        System.out.println("--> 'rescan' to scan again");
                        System.out.println("--> 'proxy' to change proxy and scan again");
                        System.out.println("--> 'stop' to stop this scan");
                        Scanner sc = new Scanner(System.in);
                        while (true) {
                            String typed = sc.next();
                            if (typed.equals("rescan")) {
                                break;
                            } else if (typed.equals("proxy")) {
                                getProxyAndDriver(true, false);
                                break;
                            } else if (typed.equals("stop")) {
                                pageScanCompleted = true;
                                scanCompleted = true;
                                break;
                            }
                        }
                    } else {
                        if (scanResult.isLastPage()) {
                            scanCompleted = true;
                        }
                        offers.addAll(scanResult.getOffers());
                        pageScanCompleted = true;
                    }
                }
            }

            siteScan.setOffers(offers.size());
            siteScan.setCompleted(new Date());
            siteScan.setStatus("completed");
            SiteScanDS.save(siteScan);

            offers.forEach(offer -> {
                OfferDS.save(offer);
            });
        } catch (Throwable t) {
            System.out.println(t.getMessage());
            t.printStackTrace();
        }
    }

    public abstract Pair<Proxy, WebDriver> getProxyAndDriver(boolean changeProxy, boolean isBrandChanged) throws Exception;
}