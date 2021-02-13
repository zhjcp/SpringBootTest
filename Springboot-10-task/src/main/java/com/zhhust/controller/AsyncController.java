package com.zhhust.controller;

import com.zhhust.service.AsyncService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class AsyncController {
    @Resource
    AsyncService asyncService;

    @GetMapping("/doAsyncService")
    @ResponseBody
    public String doAsyncService() throws InterruptedException {
        asyncService.doAsyncTask();
        return "doAsyncService OK!";
    }
}
