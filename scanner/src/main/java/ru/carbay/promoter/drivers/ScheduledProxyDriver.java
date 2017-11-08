package ru.carbay.promoter.drivers;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.carbay.promoter.ds.ProxyDS;
import ru.carbay.promoter.model.Proxy;
import ru.carbay.promoter.services.us_proxy.ProxyList;
import ru.carbay.promoter.utils.TimeUtils;

import java.util.*;

public class ScheduledProxyDriver extends AbstractDriver {

    private static DateTime launchProxyTime;
    private static WebDriver webDriver;

    private static ScheduledProxyDriver instance;

    public static synchronized ScheduledProxyDriver getInstance() {
        if (instance != null ) {
            return instance;
        } else {
            instance = new ScheduledProxyDriver();
            return instance;
        }
    }

    private ScheduledProxyDriver() {}

    public WebDriver getDriver(boolean force) throws Exception {
        if (webDriver != null && !force) {
            DateTime now = new DateTime(DateTimeZone.forTimeZone(TimeUtils.moscowTimeZone()));
            DateTime time_to_change_1 = now.minusDays(1).withTime(22, 59, 0, 0);
            DateTime time_to_change_2 = now.withTime(13, 59, 0, 0);
            DateTime time_to_change_3 = now.withTime(22, 59, 0, 0);

            if ((now.isBefore(time_to_change_2) && launchProxyTime.isAfter(time_to_change_1)) ||
                    now.isAfter(time_to_change_2) && now.isBefore(time_to_change_3) && launchProxyTime.isAfter(time_to_change_2) ||
                    now.isAfter(time_to_change_3) && launchProxyTime.isAfter(time_to_change_3)) {
                return webDriver;
            }
        }

        List<Proxy> freshProxies = ProxyList.proxySellerCom();
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


        if (webDriver != null) {
            webDriver.close();
        }
        webDriver = new FirefoxDriver(cap);
        launchProxyTime = new DateTime();

        return webDriver;
    }
}
