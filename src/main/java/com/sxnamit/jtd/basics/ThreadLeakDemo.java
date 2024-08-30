package com.sxnamit.jtd.basics;

/*
 * Capture thread dumps periodically, say every 1 minute.
 * Compare the thread dumps to see if a particular calls stack
 * a.k.a. stacktrace shows up consistently and is growing in number.
 * If that's the case, a thread leak is there in the respective code.
 * 
 * If run indefinitely, this code will cause java.lang.OutOfMemoryError
 */
public class ThreadLeakDemo {

    public static void main(String[] args) {
        // Start a thread that creates new threads indefinitely
        while (true) {
            new Thread(new LeakyTask()).start();

            // Adding a slight delay to slow down the thread creation
            try {
                Thread.sleep(1000l);
            }
            catch (InterruptedException ignored) {
            }
        }
    }

    static class LeakyTask implements Runnable {
        @Override
        public void run() {
            // Simulate some work with a thread that never ends
            while (true) {
                try {
                    // Keep the thread alive indefinitely
                    Thread.sleep(1000);
                }
                catch (InterruptedException ignored) {
                }
            }
        }
    }
}
