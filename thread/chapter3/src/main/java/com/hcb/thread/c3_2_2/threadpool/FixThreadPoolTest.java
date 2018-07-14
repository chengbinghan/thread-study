package com.hcb.thread.c3_2_2.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ChengBing Han
 * @date 12:19  2018/7/14
 * @description
 */
public class FixThreadPoolTest {

    static boolean flag = true;

    public static class MyTask implements Runnable{
        public void run() {
            if(flag){
                flag=false;
                System.out.println("出现异常");
                System.out.println(1/0);
                System.out.println("异常结束");
            }

            final long id = Thread.currentThread().getId();
            System.out.println("当前线程的id是： "  + id);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        final ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            newFixedThreadPool.submit(new MyTask());
        }
        newFixedThreadPool.shutdown();
    }
}
