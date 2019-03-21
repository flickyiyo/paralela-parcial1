package com.company.escritores;

import javax.swing.*;
import java.util.Random;

public class Lector implements Runnable{
    private Vuelo vuelo;
    private JLabel label;
    private Random rnd;

    public Lector(Vuelo vuelo, JLabel label) {
        this.vuelo = vuelo;
        this.label = label;
        rnd = new Random();
    }

    @Override
    public void run() {
        while (true) {
            try {
                label.setText("leyendo");
                Thread.sleep(generateRandomNumber());
                leerVuelo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void leerVuelo() throws InterruptedException {
        vuelo.leer(this);
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    private int generateRandomNumber() {
        return rnd.nextInt((5000 - 1000) + 1) + 1000;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
}
