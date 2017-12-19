package ru.carbay.promoter.model;

import java.util.Date;

public class ProxyHistory {

    private Date startScan;
    private Integer scanTimeSeconds;
    private boolean successful;

    public ProxyHistory(Date startDate, Date finishDate, boolean successful) {
        this.startScan = startDate;
        this.scanTimeSeconds = (int) ((finishDate.getTime() - startDate.getTime()) / 1000l);
        this.successful = successful;
    }


    public void setStartScan(Date startScan) {
        this.startScan = startScan;
    }

    public void setScanTimeSeconds(Integer scanTimeSeconds) {
        this.scanTimeSeconds = scanTimeSeconds;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public Date getStartScan() {
        return startScan;
    }

    public Integer getScanTimeSeconds() {
        return scanTimeSeconds;
    }

    public boolean isSuccessful() {
        return successful;
    }
}