package ru.carbay.promoter.jobs;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import ru.carbay.promoter.utils.TimeUtils;

import java.util.HashSet;
import java.util.Set;

import static org.quartz.CronScheduleBuilder.dailyAtHourAndMinute;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;


public class CoreScheduler {

    private static CoreScheduler instance = new CoreScheduler();
    private static final String group = "core-scheduler";
    private Scheduler scheduler;

    public static CoreScheduler getInstance() {
        return instance;
    }

    public void start(boolean autoru, boolean avito) {
        try {
            scheduler = new StdSchedulerFactory().getScheduler();

            if (autoru) {
                scheduleAutoruScanJob();
            }

            if (avito) {
                scheduleAvitoScanJob();
            }
            scheduler.start();
        } catch (SchedulerException e) {
            throw new RuntimeException("Couldn't start job.", e);
        }
    }

    public void stop() throws SchedulerException {
        if (scheduler != null) {
            scheduler.shutdown();
        }
    }

    private void scheduleAvitoScanJob() throws SchedulerException {
        JobDetail job = newJob(AvitoScanJob.class)
                .withIdentity("AvitoScanJob", group)
                .build();

        Set<Trigger> triggers = new HashSet<>();
        triggers.add(newTrigger()
                .withIdentity("AvitoScanJobTrigger8", group)
                .withSchedule(dailyAtHourAndMinute(8,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("AvitoScanJobTrigger9", group)
                .withSchedule(dailyAtHourAndMinute(9,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("AvitoScanJobTrigger10", group)
                .withSchedule(dailyAtHourAndMinute(10,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("AvitoScanJobTrigger11", group)
                .withSchedule(dailyAtHourAndMinute(11,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("AvitoScanJobTrigger12", group)
                .withSchedule(dailyAtHourAndMinute(12,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("AvitoScanJobTrigger13", group)
                .withSchedule(dailyAtHourAndMinute(13,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("AvitoScanJobTrigger14", group)
                .withSchedule(dailyAtHourAndMinute(14,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("AvitoScanJobTrigger15", group)
                .withSchedule(dailyAtHourAndMinute(15,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("AvitoScanJobTrigger16", group)
                .withSchedule(dailyAtHourAndMinute(16,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("AvitoScanJobTrigger17", group)
                .withSchedule(dailyAtHourAndMinute(17,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("AvitoScanJobTrigger18", group)
                .withSchedule(dailyAtHourAndMinute(18,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("AvitoScanJobTrigger19", group)
                .withSchedule(dailyAtHourAndMinute(19,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("AvitoScanJobTrigger20", group)
                .withSchedule(dailyAtHourAndMinute(20,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("AvitoScanJobTrigger21", group)
                .withSchedule(dailyAtHourAndMinute(21,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("AvitoScanJobTrigger22", group)
                .withSchedule(dailyAtHourAndMinute(22,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());

        scheduler.scheduleJob(job, triggers, false);
    }

    private void scheduleAutoruScanJob() throws SchedulerException {
        JobDetail job = newJob(AutoruScanJob.class)
                .withIdentity("AutoruScanJob", group)
                .build();

        Set<Trigger> triggers = new HashSet<>();
        triggers.add(newTrigger()
                .withIdentity("ScanJobTrigger8", group)
                .withSchedule(dailyAtHourAndMinute(8,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("ScanJobTrigger9", group)
                .withSchedule(dailyAtHourAndMinute(9,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("ScanJobTrigger10", group)
                .withSchedule(dailyAtHourAndMinute(10,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("ScanJobTrigger11", group)
                .withSchedule(dailyAtHourAndMinute(11,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("ScanJobTrigger12", group)
                .withSchedule(dailyAtHourAndMinute(12,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("ScanJobTrigger13", group)
                .withSchedule(dailyAtHourAndMinute(13,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("ScanJobTrigger14", group)
                .withSchedule(dailyAtHourAndMinute(14,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("ScanJobTrigger15", group)
                .withSchedule(dailyAtHourAndMinute(15,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("ScanJobTrigger16", group)
                .withSchedule(dailyAtHourAndMinute(16,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("ScanJobTrigger17", group)
                .withSchedule(dailyAtHourAndMinute(17,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("ScanJobTrigger18", group)
                .withSchedule(dailyAtHourAndMinute(18,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("ScanJobTrigger19", group)
                .withSchedule(dailyAtHourAndMinute(19,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("ScanJobTrigger20", group)
                .withSchedule(dailyAtHourAndMinute(20,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("ScanJobTrigger21", group)
                .withSchedule(dailyAtHourAndMinute(21,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());
        triggers.add(newTrigger()
                .withIdentity("ScanJobTrigger22", group)
                .withSchedule(dailyAtHourAndMinute(22,00).inTimeZone(TimeUtils.moscowTimeZone()))
                .build());

        scheduler.scheduleJob(job, triggers, false);
    }

}
