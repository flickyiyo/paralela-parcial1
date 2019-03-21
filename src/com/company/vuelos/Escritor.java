package com.company.vuelos;

import javax.swing.*;
import java.util.ArrayList;

public class Escritor extends Thread {
    private Vuelo vuelo;
    private ArrayList<Vuelo> vuelos;
    private JLabel label;

    public Escritor(Vuelo vuelo, ArrayList<Vuelo> vuelos, JLabel label) {
        this.vuelo = vuelo;
        this.vuelos = vuelos;
        this.label = label;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("asdas");
                chequearVuelos();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
    
    public void chequearVuelos() throws InterruptedException {
        for (Vuelo v: vuelos) {
            sleep(Vuelo.generateRandomNumberBetween(5000, 1000));
            this.vuelo = v;
            escribirVuelo();
        }
    }

    public void escribirVuelo() throws InterruptedException {
        synchronized (vuelo) {
            if (vuelo.isSiendoEscrito() == false) {
                modificarVuelo(System.currentTimeMillis());
                vuelo.notifyAll();
            }
        }
    }

    public void modificarVuelo(long time) throws InterruptedException {
            setTextEscribiendoVuelo(vuelo);
            vuelo.escribirVuelo(time);
            sleep(1000);
            setTextEsperando();
    }

    private void setTextEscribiendoVuelo(Vuelo v) {
        label.setText("Escribiendo vuelo " + v.getNombre());
    }

    private void setTextEsperando() {
        label.setText("Esperando...");
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }
}
