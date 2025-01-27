package ru.carbay.promoter.drivers;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.carbay.promoter.drivers.additional.BestProxyCycle;
import ru.carbay.promoter.model.Proxy;
import ru.carbay.promoter.utils.Pair;
import ru.carbay.promoter.utils.TimeUtils;

public class ScheduledProxyDriver extends AbstractDriver {

    private static DateTime launchProxyTime;
    private static WebDriver webDriver;
    private static Proxy proxy;

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

    @Override
    public Pair<Proxy, WebDriver> getProxyAndDriver(boolean changeProxy, boolean isBrandChanged) throws Exception {
        if (webDriver != null && proxy != null && !changeProxy) {
            DateTime now = new DateTime(DateTimeZone.forTimeZone(TimeUtils.moscowTimeZone()));
            DateTime time_to_change_1 = now.minusDays(1).withTime(20, 59, 0, 0);
            DateTime time_to_change_2 = now.withTime(13, 59, 0, 0);
            DateTime time_to_change_3 = now.withTime(20, 59, 0, 0);

            if ((now.isBefore(time_to_change_2) && launchProxyTime.isAfter(time_to_change_1)) ||
                    now.isAfter(time_to_change_2) && now.isBefore(time_to_change_3) && launchProxyTime.isAfter(time_to_change_2) ||
                    now.isAfter(time_to_change_3) && launchProxyTime.isAfter(time_to_change_3)) {
                return new Pair<>(proxy, webDriver);
            }
        }

        Proxy bestProxy = BestProxyCycle.getBestProxy_proxySellerCom();

        com.google.gson.JsonObject json = new com.google.gson.JsonObject();
        json.addProperty("proxyType", "MANUAL");
        json.addProperty("httpProxy", bestProxy.getIpAdress() + ":" + bestProxy.getPort());
        json.addProperty("sslProxy", bestProxy.getIpAdress() + ":" + bestProxy.getPort());
        //json.addProperty("httpProxyPort", Integer.valueOf(bestProxy.getPort()));
        //json.addProperty("sslProxyPort", Integer.valueOf(bestProxy.getPort()));
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("proxy", json);
        cap.setAcceptInsecureCerts(true);


        if (webDriver != null) {
            webDriver.close();
        }
        webDriver = new FirefoxDriver(cap);
        proxy = bestProxy;
        launchProxyTime = new DateTime();

        return new Pair<>(proxy, webDriver);
    }
}
