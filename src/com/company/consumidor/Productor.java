package com.company.consumidor;

import javax.swing.*;
import java.util.Random;

public class Productor extends Thread {
    private Contenedor contenedor;
    private JLabel label;
    @Override
    public void run() {
        escribirProduciendo();
        for (int i = 0; i < 10; i++){
            contenedor.put(i);
            escribirProduciendo();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Productor put:" + i);
            try {
                escribirDurmiendo();
                Random rnd = new Random();
                sleep(rnd.nextInt((9000 - 1000) + 1) + 1000);
            } catch (Exception err) {}
        }
    }

    public void escribirProduciendo() {
        label.setText("Productor: Produciendo!!");
    }

    public void escribirDurmiendo() {
        label.setText("Productor: Durmiendo...");
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public Contenedor getContenedor() {
        return contenedor;
    }

    public void setContenedor(Contenedor contenedor) {
        this.contenedor = contenedor;
    }
}
