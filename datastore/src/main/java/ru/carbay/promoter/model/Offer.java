package ru.carbay.promoter.model;

import java.util.Date;
import java.util.List;

public class Offer {
    private String id;
    private String url;
    private Integer views;
    private String model;
    private Integer year;
    private Integer price;
    private String dealerName;
    private List<String> badges;
    private Boolean isPremium;
    private Boolean isTopJump;
    private Boolean isVip;
    private Date created;

    public void setId(String id) {
        this.id = id;
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

    public String getId() {
        return id;
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

    public List<String> getBadges() {
        return badges;
    }

    public void setBadges(List<String> badges) {
        this.badges = badges;
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
}
