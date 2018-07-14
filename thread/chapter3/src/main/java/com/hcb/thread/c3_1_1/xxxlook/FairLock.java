package com.hcb.thread.c3_1_1.xxxlook;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ChengBing Han
 * @date 22:35  2018/6/27
 * @description
 */
public class FairLock {

    //构造函数为true,表示公平
    static ReentrantLock reentrantLock = new ReentrantLock(true);

    public static class ThreadFair implements Runnable {
        public void run() {
            while (true) {
                try {
                    reentrantLock.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "  run  ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (reentrantLock.isHeldByCurrentThread()) {
                        reentrantLock.unlock();
                    }
                }
            }

        }
    }

    public static void main(String[] args) {

            final ThreadFair threadFair = new ThreadFair();
            final Thread fairThread1 = new Thread(threadFair, "fairThread1");
            final ThreadFair threadFair2 = new ThreadFair();
            final Thread fairThread2 = new Thread(threadFair2, "fairThread2");

            fairThread1.start();
            fairThread2.start();


    }

}
