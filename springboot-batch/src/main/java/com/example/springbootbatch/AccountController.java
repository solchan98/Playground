package com.example.springbootbatch;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class AccountController {


    private final JobLauncher jobLauncher;
    private final Job job1;
    private final Job job2;
    private final LocalDateTime now;

    public AccountController(JobLauncher jobLauncher, @Qualifier("test1") Job job1, @Qualifier("test2") Job job2, LocalDateTime now) {
        this.jobLauncher = jobLauncher;
        this.job1 = job1;
        this.job2 = job2;
        this.now = now;
    }

    @GetMapping("/batch1")
    public void batch1() throws Exception {
        jobLauncher.run(job1, new JobParametersBuilder().addString("date", now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분"))).toJobParameters());
    }

    @GetMapping("/batch2")
    public void batch2() throws Exception {
        jobLauncher.run(job2, new JobParametersBuilder().addString("date", now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분"))).toJobParameters());
    }


}
