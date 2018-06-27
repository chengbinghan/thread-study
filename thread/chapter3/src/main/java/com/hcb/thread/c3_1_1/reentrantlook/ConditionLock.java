package com.hcb.thread.c3_1_1.reentrantlook;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ChengBing Han
 * @date 22:53  2018/6/27
 * @description
 */
public class ConditionLock {
    static ReentrantLock reentrantLock = new ReentrantLock();
    static Condition condition = reentrantLock.newCondition();


    static class ConditionLockThread implements Runnable {

        public void run() {

            reentrantLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " wait...");
                //方法是await不是object的wait
                condition.await();
                System.out.println(Thread.currentThread().getName() + " end wait...");


            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                if(reentrantLock.isHeldByCurrentThread()){
                    reentrantLock.unlock();
                }

            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ConditionLockThread conditionLockThread1 = new ConditionLockThread();
        final Thread thread1 = new Thread(conditionLockThread1, "ConditionLockThread1");

        final ConditionLockThread conditionLockThread2 = new ConditionLockThread();
        final Thread thread2 = new Thread(conditionLockThread2, "ConditionLockThread2");

        thread1.start();

        thread2.start();
        Thread.sleep(1000);
        //必须在 lock.lock/unlock 中间使用
        reentrantLock.lock();
        condition.signalAll();
        reentrantLock.unlock();

    }
}
