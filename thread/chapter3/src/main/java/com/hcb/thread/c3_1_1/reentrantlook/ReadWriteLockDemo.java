package com.hcb.thread.c3_1_1.reentrantlook;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author ChengBing Han
 * @date 14:44  2018/7/7
 * @description
 */
public class ReadWriteLockDemo {
    public static ReentrantLock lock = new ReentrantLock();
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    public static Lock readLock = reentrantReadWriteLock.readLock();
    public static Lock writeLock = reentrantReadWriteLock.writeLock();
    public static int value;

    private static int index = 0;

    public static Object handleRead(Lock lock) {

        lock.lock();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println("read value is " + value);
        return value;
    }

    public static void handleWrite(Lock lock, int newValue) {
        lock.lock();

        try {
            Thread.sleep(1000);
            value = newValue;
            System.out.println("write value is " + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {


        final long startTime = System.currentTimeMillis();


        final Runnable readRunnable = new Runnable() {
            public void run() {
                handleRead(readLock);
            }
        };


        final Runnable writeRunnable = new Runnable() {
            public void run() {

                handleWrite(writeLock, ++index);
            }
        };

        for (int i = 0; i < 10; i++) {
            final Thread thread = new Thread(readRunnable);
            thread.start();
        }

        for (int i = 0; i < 10; i++) {
            final Thread thread = new Thread(writeRunnable);
            thread.start();

        }

        //为什么要在while中输出一些东西呢
        //如果还是一个空的While循环，while 会被优化在-Server模式下
        while (value != 10){
            System.out.print("");
        }

        final long end = System.currentTimeMillis();
        System.out.println("app use :" + (end - startTime)/1000);

    }
}
