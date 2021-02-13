package com.zhhust.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
    @Async  //告诉spring这是一个异步任务
    public void doAsyncTask() throws InterruptedException {
        //通过线程休眠模仿一个耗时的任务
        System.out.println("大量数据正在处理，请耐心等待");
        Thread.sleep(3000);
        System.out.println("数据处理已完成");
    }

}
