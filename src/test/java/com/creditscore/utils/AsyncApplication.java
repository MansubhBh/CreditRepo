package com.creditscore.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by boys on 12/2/19.
 */
public class AsyncApplication {

    public static void main(String[] args) {

        //getting the numbers of core of the cpu
        int coreCount = Runtime.getRuntime().availableProcessors();
        //creating a fixed pool
        ExecutorService service = Executors.newFixedThreadPool(coreCount);
        //submit the task for execution
        for (int i = 0; i < 100; i++) {
//            Thread thread1 = new Thread(new Task());
//            thread1.start();
            service.execute(new Task());
        }
        System.out.println("Thread name " + Thread.currentThread().getName());
    }
    //thread pool uses a blocking queue
    /*
    ideal pool size`
     having too many threads is not a good idea as our cpu may be (1/2/4/8 )cores

     */

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("thread Name:  " + Thread.currentThread().getName());
        }
    }
}
