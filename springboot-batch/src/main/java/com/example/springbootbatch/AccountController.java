package com.example.springbootbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

@RestController
public class AccountController {


    private final JobLauncher jobLauncher;
    private final Job job1;
    private final Job job2;

    public AccountController(JobLauncher jobLauncher, @Qualifier("test1") Job job1, @Qualifier("test2") Job job2) {
        this.jobLauncher = jobLauncher;
        this.job1 = job1;
        this.job2 = job2;
    }

    @GetMapping("/batch1")
    public void batch1() throws Exception {
        Date date = new Date();
        jobLauncher.run(job1, new JobParametersBuilder().addString("date", String.valueOf(date)).toJobParameters());
    }

    @GetMapping("/batch2")
    public void batch2() throws Exception {
        Date date = new Date();
        jobLauncher.run(job2, new JobParametersBuilder().addString("date", String.valueOf(date)).toJobParameters());
    }


}
