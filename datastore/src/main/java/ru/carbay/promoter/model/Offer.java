package ru.carbay.promoter.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Date;
import java.util.List;

@Entity(value = "promoter.offers", noClassnameStored = true)
public class Offer {

    @Id
    private ObjectId objectId;

    private String siteId;
    private String url;
    private String scanFrom;
    private Date scanned;
    private String brand;
    private String model;
    private String generation;
    private Integer year;
    private Integer price;
    private String dealerName;
    private String modification;
    private String drive;
    private String body;
    private String color;
    private Integer views;
    private Integer position;
    private Boolean isPremium;
    private Boolean isTopJump;
    private Boolean isVip;
    private Date created;

    public String getId() {
        return objectId == null ? null : objectId.toString();
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public void setPremium(Boolean premium) {
        isPremium = premium;
    }

    public String getModel() {
        return model;
    }

    public String getDealerName() {
        return dealerName;
    }

    public Boolean getPremium() {
        return isPremium;
    }

    public Boolean getTopJump() {
        return isTopJump;
    }

    public void setTopJump(Boolean topJump) {
        isTopJump = topJump;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getUrl() {
        return url;
    }

    public Integer getViews() {
        return views;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Boolean getVip() {
        return isVip;
    }

    public void setVip(Boolean vip) {
        isVip = vip;
    }

    public String getBrand() {
        return brand;
    }

    public String getModification() {
        return modification;
    }

    public String getDrive() {
        return drive;
    }

    public String getColor() {
        return color;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModification(String modification) {
        this.modification = modification;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getScanFrom() {
        return scanFrom;
    }

    public void setScanFrom(String scanFrom) {
        this.scanFrom = scanFrom;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public void setScanned(Date scanned) {
        this.scanned = scanned;
    }

    public Date getScanned() {
        return scanned;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
}
