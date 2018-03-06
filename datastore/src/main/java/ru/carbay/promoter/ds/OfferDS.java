package ru.carbay.promoter.ds;

import ru.carbay.promoter.model.Offer;

import java.util.ArrayList;
import java.util.List;

public class OfferDS {

    public static void save(Offer offer) {
        MongoDS.getInstance().ds().save(offer);
    }

    public static List<Offer> getOffers(String brand, String model, String site) {
        List<Offer> offers = MongoDS.getInstance().ds().createQuery(Offer.class)
                .field("brand").equal(brand)
                .field("model").equal(model)
                .field("scanFrom").equal(site).asList();

        return offers == null ? new ArrayList<>() : offers;
    }

}
