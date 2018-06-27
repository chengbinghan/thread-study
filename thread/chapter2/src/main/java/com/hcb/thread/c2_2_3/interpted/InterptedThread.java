package com.hcb.thread.c2_2_3.interpted;

/**
 * @author ChengBing Han
 * @date 17:19  2018/6/5
 * @description
 */
public class InterptedThread {


    static class MyThread implements Runnable {


        @Override
        public void run() {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {

                    System.out.println("Thread interpted");
                    break;
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("Thread interrupted................");
                    Thread.currentThread().interrupt();//import , must to set interrupt
                }
                Thread.yield();


            }


        }
    }


    public static void main(String[] args) throws InterruptedException {

        final Thread thread = new Thread(new MyThread());
        thread.start();

        Thread.sleep(1000);
        thread.interrupt();
    }

}
