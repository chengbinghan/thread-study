package com.hcb.thread.c2_2_5.volatile_;



/**
 * @author ChengBing Han
 * @date 9:39  2018/6/22
 * @description
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;


    private static class ReaderThread extends Thread {
        public void run() {

            /*
           while (!ready){
                System.out.println(new Date());
            }*/
            //这个和上述的优化不同，这个在-server模式下会优化
            while (!ready) ;
            System.out.println(number);
        }

    }

    public static void main(String[] args) throws InterruptedException {

        //永远不会终止程序
        new ReaderThread().start();
        Thread.sleep(1000);
        number = 42;
        ready = true;
        Thread.sleep(2000);
    }
}
