package ru.carbay.promoter;

import ru.carbay.promoter.ds.MongoDS;
import ru.carbay.promoter.jobs.AutoruScanJob;
import ru.carbay.promoter.jobs.AvitoScanJob;
import ru.carbay.promoter.jobs.CoreScheduler;

import java.util.Properties;

/**
 * Created by andrey on 06.09.17.
 */
public class Main {
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

            if (args.length > 1 && args[1] != null) {
                if (args[1].equalsIgnoreCase("autoru")) {
                    CoreScheduler.getInstance().start(true, false);
                } else if (args[1].equalsIgnoreCase("avito")) {
                    CoreScheduler.getInstance().start(false, true);
                } else if (args[1].equalsIgnoreCase("test-autoru")) {
                    AutoruScanJob autoruScanJob = new AutoruScanJob();
                    autoruScanJob.execute(null);
                } else if (args[1].equalsIgnoreCase("test-avito")) {
                    AvitoScanJob avitoScanJob = new AvitoScanJob();
                    avitoScanJob.execute(null);
                } else {
                    throw new Exception("Second argument is wrong ('avito', 'autoru', 'test-avito', 'test-autoru')");
                }
            } else {
                throw new Exception("Second argument not found ('avito', 'autoru', 'test-avito', 'test-autoru')");
            }

            System.out.println("--> Promoter scan started.");
        } catch (Throwable t) {
            System.out.println(t.getMessage());
            t.printStackTrace();
        }
    }
}
