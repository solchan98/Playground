package com.example.springbootbatch.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class Scheduler {

    private final JobLauncher jobLauncher;
    private final Job job1;
    private final Job job2;

    public Scheduler(JobLauncher jobLauncher, @Qualifier("test1") Job job1, @Qualifier("test2") Job job2) {
        this.jobLauncher = jobLauncher;
        this.job1 = job1;
        this.job2 = job2;
    }

    @Scheduled(cron = "0/2 * * * * *")
    public void loggingAccount() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Date date = new Date();
        jobLauncher.run(job1, new JobParametersBuilder().addString("date", String.valueOf(date)).toJobParameters());
    }
}
