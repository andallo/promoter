package ru.carbay.promoter.model;

import java.util.List;

public class ScanResult {
    private boolean lastPage;
    private List<Offer> offers;

    public ScanResult(List<Offer> offers, boolean lastPage) {
        this.offers = offers;
        this.lastPage = lastPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Offer> getOffers() {
        return offers;
    }
}
