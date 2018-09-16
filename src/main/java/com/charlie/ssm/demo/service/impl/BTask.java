package com.charlie.ssm.demo.service.impl;

import com.charlie.ssm.demo.service.IBTask;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class BTask implements IBTask {

    @Scheduled(cron="0/5 * *  * * ? ")   //每5秒执行一次
    @Override
    public void bTask() {
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date())+"*********B任务每5秒执行一次进入测试");
    }
}
