package com.chenlw.java.web.utils.service.impl;

import com.chenlw.java.web.utils.service.IScheduledService;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class ScheduledServiceImpl implements IScheduledService {

    /**
     * 测试定时任务
     */
    //@Scheduled(cron="0/10 * *  * * ? ")   //每10秒执行一次
    @Override
    public void testScheduled() {
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date())+"*********A任务每10秒执行一次进入测试");
    }
}
