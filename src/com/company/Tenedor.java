package com.company;

import javax.swing.*;

public class Tenedor {
    public boolean isEnUso = false;
    public Main frame;
    public int numeroTenedor;
    private Filosofo f;

    public synchronized void setF(Filosofo f) {
        this.f = f;
    }

    public Tenedor(Main frame, int numeroTenedor) {
        this.frame = frame;
        this.numeroTenedor = numeroTenedor;
    }

    public synchronized void usar(Filosofo f) {
        this.isEnUso = true;
        System.out.println("fork " + this.numeroTenedor + " ocupado por " + f.getName());
        frame.labelsTenedores.get(this.numeroTenedor).setText("Fork " + this.numeroTenedor + " usado por " + f.getName());
        /*f.label.setText(f.getName() + " comiendo");
        try {
            Thread.sleep(f.getEatTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dejarDeUsar(f);
        this.isEnUso = false;*/
    }

    public synchronized void dejarDeUsar(Filosofo f) {
        frame.labelsTenedores.get(this.numeroTenedor).setText("Fork " + this.numeroTenedor + " libre");
        System.out.println("fork " + this.numeroTenedor + " libre por " + f.getName());
        this.isEnUso = false;
    }
}
