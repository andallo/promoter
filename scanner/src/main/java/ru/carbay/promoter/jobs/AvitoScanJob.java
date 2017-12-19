package ru.carbay.promoter.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import ru.carbay.promoter.drivers.UnstoppableDriver;
import ru.carbay.promoter.utils.AvitoSiteScanBuilder;

public class AvitoScanJob implements Job{

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        tryCatchAvitoScan("Москва", "Lada");
        tryCatchAvitoScan("Москва", "Audi");
        tryCatchAvitoScan("Москва", "BMW");
        tryCatchAvitoScan("Москва", "Cadillac");
        tryCatchAvitoScan("Москва", "Chevrolet");
        tryCatchAvitoScan("Москва", "Citroen");
        tryCatchAvitoScan("Москва", "Fiat");
        tryCatchAvitoScan("Москва", "Ford");
        tryCatchAvitoScan("Москва", "Hyundai");
        tryCatchAvitoScan("Москва", "Infiniti");
        tryCatchAvitoScan("Москва", "Jaguar");
        tryCatchAvitoScan("Москва", "Jeep");
        tryCatchAvitoScan("Москва", "KIA");
        tryCatchAvitoScan("Москва", "Land Rover");
        tryCatchAvitoScan("Москва", "Lifan");
        tryCatchAvitoScan("Москва", "MINI");
        tryCatchAvitoScan("Москва", "Mazda");
        tryCatchAvitoScan("Москва", "Mercedes-Benz");
        tryCatchAvitoScan("Москва", "Mitsubishi");
        tryCatchAvitoScan("Москва", "Nissan");
        tryCatchAvitoScan("Москва", "Peugeot");
        tryCatchAvitoScan("Москва", "Porsche");
        tryCatchAvitoScan("Москва", "Ravon");
        tryCatchAvitoScan("Москва", "Renault");
        tryCatchAvitoScan("Москва", "Skoda");
        tryCatchAvitoScan("Москва", "Subaru");
        tryCatchAvitoScan("Москва", "Suzuki");
        tryCatchAvitoScan("Москва", "Toyota");
        tryCatchAvitoScan("Москва", "Volkswagen");
        tryCatchAvitoScan("Москва", "Volvo");
        tryCatchAvitoScan("Москва", "УАЗ");
    }

    public void tryCatchAvitoScan(String city, String brand) {
        try {
            UnstoppableDriver.getInstance().scan(AvitoSiteScanBuilder.build(city, brand));
        } catch (Throwable e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
