package com.company.cena;

import javax.swing.*;

public class Tenedor {
    boolean enUso;
    JLabel label;
    Pensador pensador;

    public Tenedor(JLabel l) {
        label = l;
        enUso = false;
        label.setText("Libre");
    }

    public synchronized void usar(Pensador p) throws InterruptedException {
        while (isEnUso()) {
            wait();
        }
        enUso = true;
        pensador = p;
        label.setText("En uso");
        notifyAll();
    }

    public synchronized void soltar() throws InterruptedException {
        while (!isEnUso()) {
            wait();
        }
        enUso = false;
        pensador = null;
        label.setText("Libre");
        notifyAll();
    }

    public boolean isEnUso() {
        return enUso;
    }

    public void setEnUso(boolean enUso) {
        this.enUso = enUso;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public Pensador getPensador() {
        return pensador;
    }

    public void setPensador(Pensador pensador) {
        this.pensador = pensador;
    }
}
