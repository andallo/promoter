package ru.carbay.promoter.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.carbay.promoter.model.Proxy;
import ru.carbay.promoter.utils.Pair;

public class UnstoppableDriver extends AbstractDriver {

    private WebDriver webDriver;

    private static UnstoppableDriver instance;

    public static synchronized UnstoppableDriver getInstance() {
        if (instance != null ) {
            return instance;
        } else {
            instance = new UnstoppableDriver();
            return instance;
        }
    }

    private UnstoppableDriver() {
        webDriver = new FirefoxDriver();
    }

    @Override
    public WebDriver getDriver(boolean force) {
        return webDriver;
    }

    @Override
    public Pair<Proxy, WebDriver> getProxyAndDriver(boolean force) throws Exception {
        return null;
    }
}
