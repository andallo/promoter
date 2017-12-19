package ru.carbay.promoter.model;

import org.mongodb.morphia.annotations.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(value = "promoter.proxies", noClassnameStored = true)
public class Proxy {
    private String ipAdress;
    private String port;
    private String country;
    private String provider;
    private List<ProxyHistory> proxyHistory = new ArrayList<>();
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

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public List<ProxyHistory> getProxyHistory() {
        return proxyHistory;
    }

    public void setProxyHistory(List<ProxyHistory> proxyHistory) {
        this.proxyHistory = proxyHistory;
    }
}
