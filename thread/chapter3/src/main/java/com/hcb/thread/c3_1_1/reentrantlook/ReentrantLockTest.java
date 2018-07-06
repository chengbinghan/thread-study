package com.hcb.thread.c3_1_1.reentrantlook;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ChengBing Han
 * @date 21:50  2018/6/23
 * @description
 */
public class ReentrantLockTest {
    static ReentrantLock reentrantLock = new ReentrantLock();
    static final Object obj = new Object();
    public static void main(String[] args) throws InterruptedException {

        final Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println("T1 lock1");
                reentrantLock.lock();
                System.out.println("T1 lock2");
                reentrantLock.lock();
                System.out.println("T1 unlock1");

                reentrantLock.unlock();
                System.out.println("T1 unlock2");
                reentrantLock.unlock();
            }
        });









        final Thread t2 = new Thread(new Runnable() {
            public void run() {
                synchronized (obj){
                    System.out.println("t2 lock1");
                    synchronized (obj){
                        System.out.println("t2 lock2 ");

                    }
                }
                System.out.println("t2 end");
            }

        });

        System.out.println("lock============");
        t1.start();
        Thread.sleep(1000);
        System.out.println("syschronized==================");
        t2.start();


    }


}
