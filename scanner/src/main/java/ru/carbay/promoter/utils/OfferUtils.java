package ru.carbay.promoter.utils;

import ru.carbay.promoter.model.Offer;

@Deprecated
public class OfferUtils {

    public static boolean paramsEquals(Offer offer1, Offer offer2) {
        if (! fieldsEquals(offer1.getYear(), offer2.getYear())) {
            return false;
        }

        if (! fieldsEquals(offer1.getPrice(), offer2.getPrice())) {
            return false;
        }

        if (! fieldsEquals(offer1.getGeneration(), offer2.getGeneration())) {
            return false;
        }

        if (! fieldsEquals(offer1.getModification(), offer2.getModification())) {
            return false;
        }

        if (! fieldsEquals(offer1.getDrive(), offer2.getDrive())) {
            return false;
        }

        if (! fieldsEquals(offer1.getBody(), offer2.getBody())) {
            return false;
        }

        if (! fieldsEquals(offer1.getColor(), offer2.getColor())) {
            return false;
        }

        return true;
    }

    public static boolean positioningEquals(Offer offer1, Offer offer2) {
        if (! fieldsEquals(offer1.getViews(), offer2.getViews())) {
            return false;
        }

        if (! fieldsEquals(offer1.getPosition(), offer2.getPosition())) {
            return false;
        }

        if (! fieldsEquals(offer1.getPremium(), offer2.getPremium())) {
            return false;
        }

        if (! fieldsEquals(offer1.getTopJump(), offer2.getTopJump())) {
            return false;
        }

        if (! fieldsEquals(offer1.getVip(), offer2.getVip())) {
            return false;
        }

        return true;
    }

    public static void copyFields(Offer from, Offer to) {
        to.setBrand(from.getBrand());
        to.setModel(from.getModel());
        to.setYear(from.getYear());
        to.setPrice(from.getPrice());
        to.setModification(from.getModification());
        to.setDrive(from.getDrive());
        to.setBody(from.getBody());
        to.setColor(from.getColor());
        to.setViews(from.getViews());
        to.setPosition(from.getPosition());
        to.setPremium(from.getPremium());
        to.setTopJump(from.getTopJump());
        to.setVip(from.getVip());
    }

    private static boolean fieldsEquals(Integer field1, Integer field2) {
        if (field1 == null && field2 == null) return true;
        if (field1 == null && field2 != null) return false;
        return field1.equals(field2);
    }

    private static boolean fieldsEquals(String field1, String field2) {
        if (field1 == null && field2 == null) return true;
        if (field1 == null && field2 != null) return false;
        return field1.equals(field2);
    }

    private static boolean fieldsEquals(Boolean field1, Boolean field2) {
        if (field1 == null && field2 == null) return true;
        if (field1 == null && field2 != null) return false;
        return field1.equals(field2);
    }
}
