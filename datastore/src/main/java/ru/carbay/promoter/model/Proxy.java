package ru.carbay.promoter.model;

import org.mongodb.morphia.annotations.Entity;

import java.util.Date;

@Entity(value = "promoter.proxies", noClassnameStored = true)
public class Proxy {
    private String ipAdress;
    private String port;
    private String country;
    private Date lastUsage;


    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public String getPort() {
        return port;
    }

    public String getCountry() {
        return country;
    }

    public Date getLastUsage() {
        return lastUsage;
    }

    public void setLastUsage(Date lastUsage) {
        this.lastUsage = lastUsage;
    }
}
