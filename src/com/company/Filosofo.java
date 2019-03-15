package com.company;

import javax.swing.*;
import java.util.Random;

public class Filosofo implements Runnable {
    private long eatTime;
    private long thinkTime;
    private Main frame;
    private Tenedor  tIzq, tDer;
    private String name;
    public JLabel label;
    public int nFilosofo;
    public Filosofo(
            long eatTime,
            long thinkTime,
            Tenedor tIzq,
            Tenedor tDer,
            Main frame,
            String name,
            int nFilosofo,
            JLabel label
    ) {
        this.tDer = tDer;
        this.tIzq = tIzq;
        this.frame = frame;
        this.eatTime = eatTime;
        this.thinkTime = thinkTime;
        this.name = name;
        this.nFilosofo = nFilosofo;
        this.label = label;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTenedores(Tenedor tIzq, Tenedor tDer) {
        this.tIzq = tIzq;
        this.tDer = tDer;
    }

    @Override
    public void run() {
        while(true) {
            thin();
            eat();
        }
    }

    public synchronized void thin() {
        try {
            label.setText(getName() + " pensando " + this.getThinkTime());
            Thread.sleep(this.thinkTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public long getThinkTime() {
        return thinkTime;
    }

    public long getEatTime() {
        return eatTime;
    }

    public void changeLabels(boolean flag) {
        if (flag) {

        } else {

        }
    }

    public synchronized void eat() {

        synchronized (tIzq) {

            synchronized (tDer){
                System.out.println(getName() + " quiere comer");
                tIzq.usar(this);
                tDer.usar(this);
                label.setText(getName() + " comiendo " + this.getEatTime());
                try {
                    Thread.sleep(eatTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tIzq.dejarDeUsar(this);
                tDer.dejarDeUsar(this);
                tIzq.isEnUso = false;
                tDer.isEnUso = false;
                Random rnd = new Random();
                this.eatTime = rnd.nextInt((9000 - 1000) + 1) + 1000;
                this.thinkTime = rnd.nextInt((9000 - 1000) + 1) + 1000;
                notifyAll();
            }
        }

    }

}
