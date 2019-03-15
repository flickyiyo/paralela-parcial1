package com.company.consumidor;

import javax.swing.*;

public class Consumidor extends Thread {
    private Contenedor contenedor;
    private JLabel label;
    public Contenedor getContenedor() {
        return contenedor;
    }

    public void setContenedor(Contenedor contenedor) {
        this.contenedor = contenedor;
    }

    public void escribirConsumiendo(int value) {
        label.setText("Consumidor: Consumiendo!!! " + value);
    }

    public void escribirEsperando() {
        label.setText("Consumidor: Esperando...");
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    @Override
    public void run() {
        System.out.println("COnsumiendo");
        int value = 0;
        for (int i = 0; i < 10; i++) {
            value = contenedor.get();
            System.out.println("Consumidor. get: " + value);
            escribirConsumiendo(value);

            escribirEsperando();
        }
    }
}
