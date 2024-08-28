package com.sxnamit.jtd.basics;

// Program creates two threads, both fail with an exception.
// Both stacktraces (or call stacks) are individually printed on the console.
// Demonstrates the idea of one call stack per thread or
// one thread per call stack.
public class CallStack extends Thread {

    @Override
    public void run() {
        third();
    }

    public static void main(String[] args) {
        first();
    }

    public static void first() {
        System.out.println(Thread.currentThread().getName() + " : first");
        second();
    }

    public static void second() {
        System.out.println(Thread.currentThread().getName() + " : second");
        Thread t = new CallStack();
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

        throw new RuntimeException("I messed up once!");
    }

    public static void third() {
        System.out.println(Thread.currentThread().getName() + " : third");

        throw new RuntimeException("I messed up twice!");
    }

}
