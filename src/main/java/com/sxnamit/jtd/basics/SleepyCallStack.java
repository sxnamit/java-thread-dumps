package com.sxnamit.jtd.basics;

// Program creates two threads, both are put to sleep.
// When the threads go to TIMED_WAITING (sleeping) state,
// capture the thread dump. Both call stacks will be there
// in the thread dump.
public class SleepyCallStack extends Thread {

    @Override
    public void run() {
        third();
    }

    public static void main(String[] args) {
        first();
    }

    public static void first() {
        second();
    }

    public static void second() {
        Thread t = new SleepyCallStack();
        t.setName("Garfield");
        t.setDaemon(true);
        t.start();
        System.out.println("Id: " + Thread.currentThread().getId()
                + ", Name: " + Thread.currentThread().getName()
                + " going to sleep...");

        try {
            Thread.sleep(600000); // 10min
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void third() {
        System.out.println("Id: " + Thread.currentThread().getId()
                + ", Name: " + Thread.currentThread().getName()
                + " going to sleep...");
        try {
            Thread.sleep(600000); // 10min
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
