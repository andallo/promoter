package ru.carbay.promoter.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import ru.carbay.promoter.drivers.BestProxyDriver;
import ru.carbay.promoter.drivers.ScheduledProxyDriver;
import ru.carbay.promoter.drivers.UnstoppableDriver;
import ru.carbay.promoter.utils.AutoruSiteScanBuilder;
import ru.carbay.promoter.utils.AvitoSiteScanBuilder;

public class AutoruScanJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        tryCatchAutoruScan("Москва", "Lada");
        tryCatchAutoruScan("Москва", "Audi");
        tryCatchAutoruScan("Москва", "BMW");
        tryCatchAutoruScan("Москва", "Cadillac");
        tryCatchAutoruScan("Москва", "Chevrolet");
        tryCatchAutoruScan("Москва", "Citroen");
        tryCatchAutoruScan("Москва", "Fiat");
        tryCatchAutoruScan("Москва", "Ford");
        tryCatchAutoruScan("Москва", "Honda");
        tryCatchAutoruScan("Москва", "Hyundai");
        tryCatchAutoruScan("Москва", "Infiniti");
        tryCatchAutoruScan("Москва", "Jaguar");
        tryCatchAutoruScan("Москва", "Jeep");
        tryCatchAutoruScan("Москва", "KIA");
        tryCatchAutoruScan("Москва", "Land Rover");
        tryCatchAutoruScan("Москва", "Lifan");
        tryCatchAutoruScan("Москва", "MINI");
        tryCatchAutoruScan("Москва", "Mazda");
        tryCatchAutoruScan("Москва", "Mercedes-Benz");
        tryCatchAutoruScan("Москва", "Mitsubishi");
        tryCatchAutoruScan("Москва", "Nissan");
        tryCatchAutoruScan("Москва", "Peugeot");
        tryCatchAutoruScan("Москва", "Porsche");
        tryCatchAutoruScan("Москва", "Ravon");
        tryCatchAutoruScan("Москва", "Renault");
        tryCatchAutoruScan("Москва", "Skoda");
        tryCatchAutoruScan("Москва", "Subaru");
        tryCatchAutoruScan("Москва", "Suzuki");
        tryCatchAutoruScan("Москва", "Toyota");
        tryCatchAutoruScan("Москва", "Volkswagen");
        tryCatchAutoruScan("Москва", "Volvo");
        tryCatchAutoruScan("Москва", "УАЗ");
    }

    public void tryCatchAutoruScan(String city, String brand) {
        try {
            ScheduledProxyDriver.getInstance().scan(AutoruSiteScanBuilder.build(city, brand));
        } catch (Throwable e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
