package com.zhhust.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    @Scheduled(cron = "30 16 17 * * *")
    public void doScheduledTask(){
        System.out.println("定时任务执行了");
    }
}
