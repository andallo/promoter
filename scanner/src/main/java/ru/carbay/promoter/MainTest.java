package ru.carbay.promoter;


import ru.carbay.promoter.ds.MongoDS;
import ru.carbay.promoter.jobs.AutoruScanJob;

import java.util.Properties;

public class MainTest {
    public static void main(String[] args) throws Exception {
        try {
            if (args == null || args.length == 0 || args[0] == null || args[0].isEmpty()) {
                throw new RuntimeException("No arguments... Firefox driver path must be a java argument.");
            }

            String firefoxDriverPath = args[0];
            System.setProperty("webdriver.gecko.driver", firefoxDriverPath);

            Properties mongoProperties = new Properties();
            mongoProperties.load(Main.class.getResourceAsStream("/mongo.properties"));
            MongoDS.getInstance().start(mongoProperties);

            System.out.println("--> Promoter scan started.");


            AutoruScanJob autoruScanJob = new AutoruScanJob();
            autoruScanJob.execute(null);
            //SiteScan siteScan = AutoruSiteScanBuilder.build("Москва", "Audi");
            //ScheduledProxyDriver.scan(siteScan);

        } catch (Throwable t) {
            System.out.println(t.getMessage());
            t.printStackTrace();
        }
    }
}
