package ru.carbay.promoter.ds;

import ru.carbay.promoter.model.SiteScan;

/**
 * Created by andrey on 11.09.17.
 */
public class SiteScanDS {

    public static void save(SiteScan siteScan) {
        MongoDS.getInstance().ds().save(siteScan);
    }
}
