package ru.carbay.promoter.model;

import org.mongodb.morphia.annotations.Entity;

import java.util.Date;
import java.util.List;

/**
 * Created by andrey on 10.09.17.
 */

@Entity(value = "promoter.site_scan", noClassnameStored = true)
public class SiteScan {
    private Date started;
    private Date completed;
    private String site;
    private String city;
    private String brand;
    private String model;
    private List<String> pageUrls;
    private List<Offer> offers;
    private String status;

    public void setSite(String site) {
        this.site = site;
    }

    public void setPageUrls(List<String> pageUrls) {
        this.pageUrls = pageUrls;
    }

    public String getSite() {
        return site;
    }

    public List<String> getPageUrls() {
        return pageUrls;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public String getCity() {
        return city;
    }

    public String getBrand() {
        return brand;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCompleted() {
        return completed;
    }

    public void setCompleted(Date completed) {
        this.completed = completed;
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
