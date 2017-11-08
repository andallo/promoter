package ru.carbay.promoter.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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
}
