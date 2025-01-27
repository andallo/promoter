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
import java.util.Date;
import java.util.List;

public class AutoruScanner {

    public static ScanResult scan(int currentPage, String url, String brand, String model, WebDriver webDriver) {
        List<Offer> offers = new ArrayList<>();
        boolean lastPage = false;

        try {
            webDriver.get(url);

            List<WebElement> offerElements = webDriver.findElements(By.className("listing-item"));
            int i = 1;
            for (WebElement offerElement : offerElements) {
                try {
                    Offer offer = new Offer();
                    String dealerName = offerElement.findElement(By.className("listing-item__dealer-name")).getText();
                    String offerUrl = offerElement.findElement(By.className("listing-item__link")).getAttribute("href");
                    String offerModel = offerElement.findElement(By.className("listing-item__link")).getText();
                    String offerYear = offerElement.findElement(By.className("listing-item__year")).getText();
                    String offerPrice = offerElement.findElement(By.className("listing-item__price")).getText();
                    String description = offerElement.findElement(By.className("listing-item__description")).getText();
                    offerModel = offerModel.replace(brand, "").trim();
                    offerPrice = offerPrice.replaceAll("[^0-9]","");
                    String[] descriptionParams = description.split(",");
                    String offerModification = descriptionParams[0];
                    String offerDrive = descriptionParams[1];
                    String offerBody = descriptionParams[2];
                    String offerColor = descriptionParams[3];

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
                    offer.setUrl(offerUrl);
                    offer.setScanFrom("auto.ru");
                    offer.setScanned(new Date());
                    offer.setViews(0);
                    offer.setPosition(i);
                    offer.setBrand(brand);
                    offer.setModel(model);
                    offer.setGeneration(offerModel);
                    offer.setYear(Integer.valueOf(offerYear));
                    offer.setPrice(Integer.valueOf(offerPrice));
                    offer.setModification(offerModification.trim());
                    offer.setDrive(offerDrive.trim());
                    offer.setBody(offerBody.trim());
                    offer.setColor(offerColor.trim());
                    offer.setPremium(promo_top);
                    offer.setTopJump(promo_jump);

                    offers.add(offer);
                    ++i;
                } catch (Throwable t) {
                    System.out.println(t.getMessage());
                    t.printStackTrace();
                }
            }

            try {
                webDriver.findElement(By.className("pager__wrap"));
            } catch (Throwable t) {
                lastPage = true;
            }

            if (!lastPage) {
                List<WebElement> pageButtons = webDriver.findElement(By.className("pager__wrap")).findElement(By.className("radio-group")).findElements(By.tagName("label"));
                WebElement lastPageButton = pageButtons.get(pageButtons.size() - 1).findElement(By.tagName("button")).findElement(By.tagName("span"));
                //webDriver.findElement(By.xpath("/div[contains(@class,'pager__wrap')]/span[contains(@class,'radio-group')]/label[last()]/button/span"));
                if (lastPageButton != null
                        && lastPageButton.getText() != null
                        && lastPageButton.getText().equals(Integer.valueOf(currentPage).toString())) {
                    lastPage = true;
                }
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
        SiteScan siteScan = AutoruSiteScanBuilder.build("Москва", "Audi", "A3");

        scan(1, siteScan.getPageUrls().get(0), siteScan.getBrand(), siteScan.getModel(), webDriver);
    }
}
