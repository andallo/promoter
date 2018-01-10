package ru.carbay.promoter.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import ru.carbay.promoter.drivers.UnstoppableDriver;
import ru.carbay.promoter.utils.AvitoSiteScanBuilder;

public class AvitoScanJob implements Job{

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        tryCatchAvitoScan("Москва", "Lada", "Vesta");
        tryCatchAvitoScan("Москва", "Lada", "Granta");
        tryCatchAvitoScan("Москва", "Lada", "Largus");
        tryCatchAvitoScan("Москва", "Lada", "4x4 niva");
        tryCatchAvitoScan("Москва", "Lada", "XRAY");
        tryCatchAvitoScan("Москва", "Lada", "Kalina");
        //tryCatchAvitoScan("Москва", "Audi", "A3");
        //tryCatchAvitoScan("Москва", "BMW", "1er");
        tryCatchAvitoScan("Москва", "Cadillac", "Escalade");
        tryCatchAvitoScan("Москва", "Cadillac", "XT5");
        tryCatchAvitoScan("Москва", "Chevrolet", "Niva");
        tryCatchAvitoScan("Москва", "Citroen", "C4");
        tryCatchAvitoScan("Москва", "Ford", "Focus");
        tryCatchAvitoScan("Москва", "Ford", "Explorer");
        tryCatchAvitoScan("Москва", "Hyundai", "Solaris");
        tryCatchAvitoScan("Москва", "Hyundai", "Creta");
        //tryCatchAvitoScan("Москва", "Infiniti");
        //tryCatchAvitoScan("Москва", "Jaguar");
        //tryCatchAvitoScan("Москва", "Jeep");
        tryCatchAvitoScan("Москва", "KIA", "Rio");
        tryCatchAvitoScan("Москва", "KIA", "Sportage");
        //tryCatchAvitoScan("Москва", "Land Rover");
        //tryCatchAvitoScan("Москва", "Lifan");
        //tryCatchAvitoScan("Москва", "MINI");
        tryCatchAvitoScan("Москва", "Mazda", "CX5");
        tryCatchAvitoScan("Москва", "Mazda", "6");
        //tryCatchAvitoScan("Москва", "Mercedes-Benz");
        tryCatchAvitoScan("Москва", "Mitsubishi", "Outlander");
        tryCatchAvitoScan("Москва", "Mitsubishi", "ASX");
        tryCatchAvitoScan("Москва", "Nissan", "X-Trail");
        tryCatchAvitoScan("Москва", "Nissan", "Qashqai");
        tryCatchAvitoScan("Москва", "Peugeot", "408");
        tryCatchAvitoScan("Москва", "Peugeot", "3008");
        //tryCatchAvitoScan("Москва", "Porsche");
        //tryCatchAvitoScan("Москва", "Ravon");
        tryCatchAvitoScan("Москва", "Renault", "Duster");
        tryCatchAvitoScan("Москва", "Renault", "Logan");
        tryCatchAvitoScan("Москва", "Renault", "Sandero");
        tryCatchAvitoScan("Москва", "Renault", "Kaptur");
        tryCatchAvitoScan("Москва", "Skoda", "Rapid");
        tryCatchAvitoScan("Москва", "Skoda", "Octavia");
        //tryCatchAvitoScan("Москва", "Subaru");
        //tryCatchAvitoScan("Москва", "Suzuki");
        tryCatchAvitoScan("Москва", "Toyota", "RAV4");
        tryCatchAvitoScan("Москва", "Toyota", "Camry");
        tryCatchAvitoScan("Москва", "Volkswagen", "Polo");
        tryCatchAvitoScan("Москва", "Volkswagen", "Tiguan");
        tryCatchAvitoScan("Москва", "Volvo", "XC90");
        //tryCatchAvitoScan("Москва", "УАЗ");
        tryCatchAvitoScan("Москва", "Datsun", "on-DO");
    }

    public void tryCatchAvitoScan(String city, String brand, String model) {
        try {
            UnstoppableDriver.getInstance().scan(AvitoSiteScanBuilder.build(city, brand, model));
        } catch (Throwable e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
