package ru.carbay.promoter.scanners;

import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.carbay.promoter.model.Offer;
import ru.carbay.promoter.model.ScanResult;

import java.text.SimpleDateFormat;
import java.util.*;

public class AvitoScanner {

    private static final Map<String, String> monthes = new HashMap<>();

    static {
        monthes.put("января", "01");
        monthes.put("февраля", "02");
        monthes.put("марта", "03");
        monthes.put("апреля", "04");
        monthes.put("мая", "05");
        monthes.put("июня", "06");
        monthes.put("июля", "07");
        monthes.put("августа", "08");
        monthes.put("сентября", "09");
        monthes.put("октября", "10");
        monthes.put("ноября", "11");
        monthes.put("декабря", "12");
    }

    public static ScanResult scan(String url, String brand, WebDriver webDriver) {
        List<Offer> offers = new ArrayList<>();
        boolean lastPage = true;

        try {
            webDriver.get(url);

            List<WebElement> offerElements = webDriver.findElements(By.className("item"));
            for (WebElement offerElement : offerElements) {
                try {
                    Offer offer = new Offer();
                    String dealerName;
                    WebElement dataElement = offerElement.findElement(By.className("data"));
                    try {
                        WebElement dealerNameElement = dataElement.findElement(By.xpath("a"));
                        dealerName = dealerNameElement.getText();
                    } catch (NoSuchElementException e) {
                        dealerName = "";
                    }

                    String offerUrl = offerElement.findElement(By.className("item-description-title-link")).getAttribute("href");
                    String offerModelYear = offerElement.findElement(By.className("item-description-title-link")).getText();
                    String offerPrice = offerElement.findElement(By.className("about")).getText();
                    String offerDate = offerElement.findElement(By.className("date")).getText();
                    String[] modelYear = offerModelYear.split(",");
                    String offerModel = modelYear[0].trim();
                    String offerYear = modelYear[1].trim();
                    offerModel = offerModel.replace(avitoBrand(brand), "").trim();
                    offerPrice = offerPrice.substring(0, offerPrice.indexOf("руб.")).replaceAll("[^0-9]","");
                    Date offerDate_asDate = parseDateString(offerDate);

                    boolean promo_top = false;
                    List<WebElement> promo_topElements = offerElement.findElements(By.className("monetization-icon_vas-premium"));
                    if (promo_topElements != null && promo_topElements.size() > 0) {
                        promo_top = true;
                    }
                    boolean promo_jump = false;
                    List<WebElement> promo_jumpElements = offerElement.findElements(By.className("monetization-icon_vas-pushup"));
                    if (promo_jumpElements != null && promo_jumpElements.size() > 0) {
                        promo_jump = true;
                    }
                    boolean promo_vip = false;
                    List<WebElement> promo_vipElements = offerElement.findElements(By.className("monetization-icon_vas-vip"));
                    if (promo_vipElements != null && promo_vipElements.size() > 0) {
                        promo_vip = true;
                    }

                    offer.setDealerName(dealerName);
                    offer.setId(offerUrl);
                    offer.setModel(offerModel);
                    offer.setYear(Integer.valueOf(offerYear));
                    offer.setPrice(Integer.valueOf(offerPrice));
                    offer.setPremium(promo_top);
                    offer.setTopJump(promo_jump);
                    offer.setVip(promo_vip);
                    offer.setCreated(offerDate_asDate);

                    offers.add(offer);
                } catch (Throwable t) {
                    System.out.println(t.getMessage());
                    t.printStackTrace();
                }
            }

            List<WebElement> webElements = webDriver.findElements(By.xpath("//a[@class='pagination-page' and text()='Последняя']"));
            if (webElements != null && webElements.size() > 0) {
                lastPage = false;
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
            t.printStackTrace();
        }

        return new ScanResult(offers, lastPage);
    }

    private static Date parseDateString(String avitoDate) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        avitoDate = avitoDate.trim();
        if (avitoDate.contains("Сегодня")) {
            Date now = new Date();
            String dateString = simpleDateFormat.format(now).substring(0, 11) + avitoDate.substring(8);
            return simpleDateFormat.parse(dateString);
        } else if (avitoDate.contains("Вчера")) {
            Date yesterday = new Date(new Date().getTime() - 1000 * 60 * 60 * 24);
            String dateString = simpleDateFormat.format(yesterday).substring(0, 11) + avitoDate.substring(6);
            return simpleDateFormat.parse(dateString);
        } else {
            String[] dateParts = avitoDate.split(" ");
            String year2digits = String.valueOf(LocalDate.now().getYear()).substring(2);
            String dateString = dateParts[0] + "." + monthes.get(dateParts[1]) + "." + year2digits + " " + dateParts[2];
            return simpleDateFormat.parse(dateString);
        }
    }

    private static String avitoBrand(String brand) {
        if (brand.equalsIgnoreCase("lada")) {
            return "LADA";
        }

        return brand;
    }
}