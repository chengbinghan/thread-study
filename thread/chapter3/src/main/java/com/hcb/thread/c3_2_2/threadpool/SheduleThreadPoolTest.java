package com.hcb.thread.c3_2_2.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author ChengBing Han
 * @date 12:24  2018/7/14
 * @description
 */
public class SheduleThreadPoolTest {

    public static class MyTask implements Runnable{

        public void run() {
            System.out.println("出现异常：");
            System.out.println(1/0);
            System.out.println("Thread is run which  id is : " + Thread.currentThread().getId());
        }
    }

    public static void main(String[] args) {
        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.scheduleAtFixedRate(new MyTask(),3,1,TimeUnit.SECONDS);
    }
}
