package com.hcb.thread.c3_1_1.reentrantlook;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ChengBing Han
 * @date 22:30  2018/6/23
 * @description
 */
public class TimeLock implements Runnable{

    static ReentrantLock reentrantLock = new ReentrantLock();
    public void run() {

        try {
            if(reentrantLock.tryLock(3, TimeUnit.SECONDS)){
                System.out.println(Thread.currentThread().getName() + " run");
                Thread.sleep(6000);

            }else {
                System.out.println(Thread.currentThread().getName() + "getLock failed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //注意finally 这里释放锁的方式
            if(reentrantLock.isHeldByCurrentThread()){
                reentrantLock.unlock();
            }
        }


    }

    public static void main(String[] args) throws InterruptedException {
        final TimeLock r1 = new TimeLock();

        final Thread thread1 = new Thread(r1);
        thread1.setName("t1");
        thread1.start();
        Thread.sleep(100);

        final TimeLock r2 = new TimeLock();
        final Thread thread2 = new Thread(r2);
        thread2.setName("t2");
        thread2.start();

    }
}
