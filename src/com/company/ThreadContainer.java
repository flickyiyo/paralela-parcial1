package com.company;

public class ThreadContainer implements Runnable {
    Tenedor t1, t2;
    public long sleepTime;
    public long eatTime;
    public ThreadContainer(Tenedor t1, Tenedor t2) {
        this.t1 = t1;
        this.t2  = t2;
    }

    @Override
    public void run() {

    }



    public synchronized void eat() {
        while (true) {
            try {
                Thread.sleep(1000);
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
