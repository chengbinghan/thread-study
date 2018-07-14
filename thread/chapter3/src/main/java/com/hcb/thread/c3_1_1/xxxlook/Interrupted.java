package com.hcb.thread.c3_1_1.xxxlook;


import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ChengBing Han
 * @date 22:12  2018/6/23
 * @description
 */
public class Interrupted implements Runnable {
    private  Integer state = 0;

    public Interrupted() {
    }

    public Interrupted(Integer state) {
        this.state = state;
    }
   static ReentrantLock reentrantLock1 = new ReentrantLock();
   static ReentrantLock reentrantLock2 = new ReentrantLock();

    public void run() {

        try {
            if(state == 1) {
                reentrantLock1.lockInterruptibly();
                System.out.println("state1===lock1");
                Thread.sleep(1000);
                reentrantLock2.lockInterruptibly();
                System.out.println("state1===lock2");

            }else if(state == 2){
                reentrantLock2.lockInterruptibly();
                System.out.println("state2===lock2");
                Thread.sleep(1000);

                reentrantLock1.lockInterruptibly();
                System.out.println("state2===lock1");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(reentrantLock1.isHeldByCurrentThread()){
                reentrantLock1.unlock();
            }
            if(reentrantLock2.isHeldByCurrentThread()){
                reentrantLock2.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Interrupted r1 = new Interrupted(1);
        final Interrupted r2 = new Interrupted(2);
        final Thread t1 = new Thread(r1);
        final Thread t2 = new Thread(r2);

        t1.start();
        Thread.sleep(100);
        t2.start();
        Thread.sleep(5000);

        t2.interrupt();
    }
}
