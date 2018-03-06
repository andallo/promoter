package ru.carbay.promoter.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.carbay.promoter.drivers.additional.BestProxyCycle;
import ru.carbay.promoter.model.Proxy;
import ru.carbay.promoter.utils.Pair;

public class EveryBrandProxyDriver extends AbstractDriver {

    private static WebDriver webDriver;
    private static Proxy proxy;

    private static EveryBrandProxyDriver instance;

    public static synchronized EveryBrandProxyDriver getInstance() {
        if (instance != null ) {
            return instance;
        } else {
            instance = new EveryBrandProxyDriver();
            return instance;
        }
    }

    private EveryBrandProxyDriver() {}

    @Override
    public Pair<Proxy, WebDriver> getProxyAndDriver(boolean changeProxy, boolean isBrandChanged) throws Exception {
        if (webDriver != null && proxy != null && !changeProxy && !isBrandChanged) {
            return new Pair<>(proxy, webDriver);
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

        return new Pair<>(proxy, webDriver);
    }
}
