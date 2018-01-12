package ru.carbay.promoter.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import ru.carbay.promoter.drivers.ScheduledProxyDriver;
import ru.carbay.promoter.drivers.UnstoppableDriver;
import ru.carbay.promoter.utils.AutoruSiteScanBuilder;

public class AutoruScanJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        tryCatchAutoruScan("Москва", "Lada", "Kalina");
        tryCatchAutoruScan("Москва", "Lada", "Vesta");
        tryCatchAutoruScan("Москва", "Lada", "Granta");
        tryCatchAutoruScan("Москва", "Lada", "Largus");
        tryCatchAutoruScan("Москва", "Lada", "2121");
        tryCatchAutoruScan("Москва", "Lada", "XRAY");
        //tryCatchAutoruScan("Москва", "Audi", "A3");
        //tryCatchAutoruScan("Москва", "Bentley", "Bentayga");
        //tryCatchAutoruScan("Москва", "BMW", "1er");
        tryCatchAutoruScan("Москва", "Cadillac", "Escalade");
        tryCatchAutoruScan("Москва", "Cadillac", "XT5");
        tryCatchAutoruScan("Москва", "Chevrolet", "Niva");
        tryCatchAutoruScan("Москва", "Citroen", "C4");
        tryCatchAutoruScan("Москва", "Ford", "Focus");
        tryCatchAutoruScan("Москва", "Ford", "Explorer");
        tryCatchAutoruScan("Москва", "Honda", "CR-V");
        tryCatchAutoruScan("Москва", "Hyundai", "Solaris");
        tryCatchAutoruScan("Москва", "Hyundai", "Creta");
        //tryCatchAutoruScan("Москва", "Infiniti");
        //tryCatchAutoruScan("Москва", "Jaguar");
        //tryCatchAutoruScan("Москва", "Jeep");
        tryCatchAutoruScan("Москва", "KIA", "Rio");
        tryCatchAutoruScan("Москва", "KIA", "Sportage");
        //tryCatchAutoruScan("Москва", "Land Rover");
        //tryCatchAutoruScan("Москва", "Lifan");
        //tryCatchAutoruScan("Москва", "MINI");
        tryCatchAutoruScan("Москва", "Mazda", "CX5");
        tryCatchAutoruScan("Москва", "Mazda", "6");
        //tryCatchAutoruScan("Москва", "Mercedes-Benz");
        tryCatchAutoruScan("Москва", "Mitsubishi", "Outlander");
        tryCatchAutoruScan("Москва", "Mitsubishi", "ASX");
        tryCatchAutoruScan("Москва", "Nissan", "X-Trail");
        tryCatchAutoruScan("Москва", "Nissan", "Qashqai");
        tryCatchAutoruScan("Москва", "Peugeot", "408");
        tryCatchAutoruScan("Москва", "Peugeot", "3008");
        //tryCatchAutoruScan("Москва", "Porsche");
        //tryCatchAutoruScan("Москва", "Ravon");
        tryCatchAutoruScan("Москва", "Renault", "Duster");
        tryCatchAutoruScan("Москва", "Renault", "Logan");
        tryCatchAutoruScan("Москва", "Renault", "Sandero");
        tryCatchAutoruScan("Москва", "Renault", "Kaptur");
        tryCatchAutoruScan("Москва", "Skoda", "Rapid");
        tryCatchAutoruScan("Москва", "Skoda", "Octavia");
        //tryCatchAutoruScan("Москва", "Subaru");
        //tryCatchAutoruScan("Москва", "Suzuki");
        tryCatchAutoruScan("Москва", "Toyota", "RAV4");
        tryCatchAutoruScan("Москва", "Toyota", "Camry");
        tryCatchAutoruScan("Москва", "Volkswagen", "Polo");
        tryCatchAutoruScan("Москва", "Volkswagen", "Tiguan");
        tryCatchAutoruScan("Москва", "Volvo", "XC90");
        //tryCatchAutoruScan("Москва", "УАЗ");
        tryCatchAutoruScan("Москва", "Datsun", "on-DO");
    }

    public void tryCatchAutoruScan(String city, String brand, String model) {
        try {
            ScheduledProxyDriver.getInstance().scan(AutoruSiteScanBuilder.build(city, brand, model));
        } catch (Throwable e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
