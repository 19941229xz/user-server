package com.example.userserver.common.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configuration
//@EnableScheduling
@Slf4j
public class TestTask {


//    @Scheduled(cron = "0/5 * * * * ?")
    @Scheduled(fixedRate = 5000)
    public void Task1(){
        log.info("我是task1");
    }





}
