package com.example.springbootbatch.batch;
import com.example.springbootbatch.Account;
import com.example.springbootbatch.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.*;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

    private final AccountRepository accountRepository;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public LocalDateTime localDateTime() {
        return LocalDateTime.now();
    }

    @Bean(name = "test1")
    public Job myJob() {
        return jobBuilderFactory.get("myjob")
                .start(mystep())
                .incrementer(new RunIdIncrementer())
                .build();

    }

    @Bean
    public Step mystep() {
        return stepBuilderFactory.get("mystep")
                .<Account, Account> chunk(5)
                .reader(myReader())
                .processor(myProcessor())
                .writer(myWriter())
                .build();
    }

    @Bean(name = "test2")
    public Job myJob2() {
        return jobBuilderFactory.get("myjob2")
                .start(mystep2())
                .incrementer(new RunIdIncrementer())
                .build();

    }

    @Bean
    public Step mystep2() {
        return stepBuilderFactory.get("mystep2")
                .<Account, Account> chunk(3)
                .reader(myReader())
                .processor(myProcessor())
                .writer(myWriter())
                .build();
    }

    @Bean
    @StepScope
    public ListItemReader<Account> myReader() {
        List<Account> accounts = accountRepository.findAll();
        return new ListItemReader<>(accounts);
    }
    /* 처리 과정 */
    public ItemProcessor<Account, Account> myProcessor() {
        ItemProcessor<Account, Account> itemProcessor = new ItemProcessor<>() {
            @Override
            public Account process(Account account) throws Exception {
                System.out.println("이름: " + account.getName() + " 나이: " + account.getAge());
                return null;
            }
        };
        return itemProcessor;
    }
    /* Chunk 한 블럭 실행 후 동작 */
    public ItemWriter<Account> myWriter() {
        ItemWriter<Account> itemWriter = new ItemWriter<>() {
            @Override
            public void write(List<? extends Account> items) throws Exception {
                System.out.println("ok");
            }
        };
        return itemWriter;
    }

}
