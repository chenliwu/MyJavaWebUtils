package com.charlie.ssm.demo.service.impl;

import com.charlie.ssm.demo.service.IATask;
import com.charlie.ssm.demo.service.IScheduledService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class ATask implements IATask{

    @Scheduled(cron="0/10 * *  * * ? ")   //每10秒执行一次
    @Override
    public void aTask() {
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date())+"*********A任务每10秒执行一次进入测试");
    }
}
