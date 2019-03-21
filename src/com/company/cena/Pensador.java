package com.company.cena;

import javax.swing.*;
import java.util.Random;

public class Pensador extends Thread {
    Tenedor tendorIzq;
    Tenedor tenedorDer;
    String nombre;
    Random rnd;
    JLabel label;
    int maxTime = 8000;
    int minTime = 1100;

    public Pensador(String nombre, Tenedor tendorIzq, Tenedor tendorDer, JLabel l) {
        this.nombre = nombre;
        this.tendorIzq = tendorIzq;
        this.tenedorDer = tendorDer;
        this.label = l;
        rnd = new Random();
    }

    public synchronized void comer() {
        try {
            label.setText(nombre + " Pidiendo tenedores");
            pedirTenedores();
            label.setText(nombre + " Comiendo!");
            sleep(generarNumeroRandom());
            soltarTenedores();
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void soltarTenedores() throws InterruptedException {
        tendorIzq.soltar();
        tenedorDer.soltar();
    }

    public void pedirTenedores() throws InterruptedException {
        while (tenedorDer.getPensador() != this || tendorIzq.getPensador() != this) {
            if (tendorIzq.getPensador()!=this) pedirTenedor(tendorIzq);
            if (tenedorDer.getPensador()!=this) pedirTenedor(tenedorDer);
        }
    }

    public void pedirTenedor(Tenedor t) throws InterruptedException {
        t.usar(this);
        t.getLabel().setText("En uso por " + nombre);
    }

    @Override
    public void run() {
        while (true) {
            pensar();
            comer();
        }
    }

    public void pensar() {
        try {
            label.setText(nombre + " Pensando");
            sleep(generarNumeroRandom());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int generarNumeroRandom() {
        return rnd.nextInt((maxTime - minTime) + 1) + minTime;
    }

    public Tenedor getTendorIzq() {
        return tendorIzq;
    }

    public Tenedor getTenedorDer() {
        return tenedorDer;
    }

    public void setTendorIzq(Tenedor tendorIzq) {
        this.tendorIzq = tendorIzq;
    }

    public void setTenedorDer(Tenedor tenedorDer) {
        this.tenedorDer = tenedorDer;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
}
