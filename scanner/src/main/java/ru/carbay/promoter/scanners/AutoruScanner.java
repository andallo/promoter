package ru.carbay.promoter.scanners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.carbay.promoter.model.Offer;
import ru.carbay.promoter.model.ScanResult;
import ru.carbay.promoter.model.SiteScan;
import ru.carbay.promoter.utils.AutoruSiteScanBuilder;

import java.util.ArrayList;
import java.util.List;

public class AutoruScanner {

    public static ScanResult scan(String url, String brand, WebDriver webDriver) {
        List<Offer> offers = new ArrayList<>();
        boolean lastPage = false;

        try {
            webDriver.get(url);

            List<WebElement> offerElements = webDriver.findElements(By.className("listing-item"));
            for (WebElement offerElement : offerElements) {
                try {
                    Offer offer = new Offer();
                    String dealerName = offerElement.findElement(By.className("listing-item__dealer-name")).getText();
                    String offerUrl = offerElement.findElement(By.className("listing-item__link")).getAttribute("href");
                    String offerModel = offerElement.findElement(By.className("listing-item__link")).getText();
                    String offerYear = offerElement.findElement(By.className("listing-item__year")).getText();
                    String offerPrice = offerElement.findElement(By.className("listing-item__price")).getText();
                    offerModel = offerModel.replace(brand, "").trim();
                    offerPrice = offerPrice.replaceAll("[^0-9]","");

                    List<String> badges = new ArrayList<>();
                    List<WebElement> badgeElements = offerElement.findElements(By.className("listing-item__badge"));
                    if (!badgeElements.equals(null) && badgeElements.size() != 0) {
                        badgeElements.forEach((element) -> {
                            badges.add(element.getText());
                        });
                    }

                    boolean promo_top = false;
                    List<WebElement> promo_topElements = offerElement.findElements(By.className("icon_type_top"));
                    if (promo_topElements != null && promo_topElements.size() > 0) {
                        promo_top = true;
                    }
                    boolean promo_jump = false;
                    List<WebElement> promo_jumpElements = offerElement.findElements(By.className("icon_type_top-search"));
                    if (promo_jumpElements != null && promo_jumpElements.size() > 0) {
                        promo_jump = true;
                    }

                    offer.setDealerName(dealerName);
                    offer.setId(offerUrl);
                    offer.setModel(offerModel);
                    offer.setYear(Integer.valueOf(offerYear));
                    offer.setPrice(Integer.valueOf(offerPrice));
                    offer.setPremium(promo_top);
                    offer.setTopJump(promo_jump);
                    offer.setBadges(badges);

                    offers.add(offer);
                } catch (Throwable t) {
                    System.out.println(t.getMessage());
                    t.printStackTrace();
                }
            }

            WebElement nextButton = webDriver.findElement(By.className("pager__next"));
            if (nextButton != null && nextButton.getAttribute("disabled") != null &&
                    nextButton.getAttribute("disabled").equalsIgnoreCase("disabled")) {
                lastPage = true;
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
            t.printStackTrace();
        }

        return new ScanResult(offers, lastPage);
    }

    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "/Projects/promoter/firefox_driver/mac/geckodriver");
        WebDriver webDriver = new FirefoxDriver();
        SiteScan siteScan = AutoruSiteScanBuilder.build("Москва", "Audi");

        scan(siteScan.getPageUrls().get(0), siteScan.getBrand(), webDriver);
    }
}
