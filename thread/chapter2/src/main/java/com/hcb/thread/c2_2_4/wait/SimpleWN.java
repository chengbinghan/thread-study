package com.hcb.thread.c2_2_4.wait;

/**
 * @author ChengBing Han
 * @date 9:05  2018/6/22
 * @description
 */
public class SimpleWN {

    final static Object obj = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(obj) {
                    System.out.println("t1.start,and t1 will wait");
                    try {
                        // 调用wait 方法的这个对象必须被synchronizeed
                        //wait 会释放锁
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t1 was notify");


                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(obj){
                    System.out.println("t2 start and will notify t1");
                    // 调用notify 方法的这个对象必须被synchronizeed
                    //如果 有n个线程此时都是由于调用了obj.wait方法，那么会唤醒任意一个
                    obj.notify();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("t2 end run");
                }
            }
        });
        t1.start();
        t2.start();
    }
}
