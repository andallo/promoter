package ru.carbay.promoter.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import ru.carbay.promoter.model.Offer;

import java.util.Date;
import java.util.List;

/**
 * Created by andrey on 10.09.17.
 */

@Entity(value = "promoter.site_scan", noClassnameStored = true)
public class SiteScan {

    @Id
    private ObjectId objectId;

    private Date started;
    private Date completed;
    private String site;
    private String city;
    private String brand;
    private String model;
    private List<String> pageUrls;
    private Integer offers;
    private String status;

    public String getId() {
        return objectId == null ? null : objectId.toString();
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

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

    public String getCity() {
        return city;
    }

    public String getBrand() {
        return brand;
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

    public Integer getOffers() {
        return offers;
    }

    public void setOffers(Integer offers) {
        this.offers = offers;
    }
}
