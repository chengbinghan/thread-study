package com.hcb.thread.c2_2_2.stop;

/**
 * @author ChengBing Han
 * @date 14:03  2018/6/5
 * @description
 */
public class ThreadStop {

    static class User {
        String name;
        String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    static User user = new User();

    static class WriteThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                final long v = System.currentTimeMillis();
                user.setId(v + "");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                user.setName(v + "");
                Thread.yield();
            }
        }
    }

    static class ReadThread implements Runnable {


        @Override
        public void run() {
            while (true) {
                final String name = user.getName();
                final String id = user.getId();
                if (name != null && !name.equals(id)) {

                    System.out.println("name:id===>" + name + ":" + id);
                    break;
                }

                Thread.yield();
            }


        }
    }

    public static void main(String[] args) {

        final Thread readThread = new Thread(new ReadThread());
        readThread.start();

        while (true) {
            final Thread writeThread = new Thread(new  WriteThread());
            writeThread.start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            writeThread.stop();


        }

    }


}
