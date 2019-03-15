package com.company.barbero2;

import javax.swing.*;

public class Silla {
    boolean ocupada;
    JLabel label;

    public boolean isOcupada() {
        return ocupada;
    }

    public synchronized void usar() throws InterruptedException {
        while (ocupada) {
            wait();
        }
        ocupada = true;
        notifyAll();
    }

    public synchronized void dejarDeUsar() {
        if (!ocupada) {
            return;
        }
        ocupada = false;
        notifyAll();
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
}
