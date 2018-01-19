package ru.carbay.promoter.model.ds;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(value = "promoter.proxies", noClassnameStored = true)
public class Proxy {

    @Id
    private ObjectId objectId;

    private String ipAdress;
    private String port;
    private String country;
    private String provider;
    private List<String> errorDates = new ArrayList<>();
    private Date lastUsage;

    public String getId() {
        return objectId == null ? null : objectId.toString();
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

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

    public List<String> getErrorDates() {
        return errorDates;
    }

    public void setErrorDates(List<String> errorDates) {
        this.errorDates = errorDates;
    }
}
