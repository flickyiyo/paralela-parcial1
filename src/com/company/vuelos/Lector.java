package com.company.vuelos;

import javax.swing.*;
import java.util.ArrayList;

public class Lector extends Thread {
    private Vuelo vuelo;
    private JLabel label;
    private ArrayList<Vuelo> vuelos;

    public Lector(ArrayList<Vuelo> vuelos, JLabel label) {
        this.vuelos = vuelos;
        this.label = label;
    }

    @Override
    public void run() {
        while (true) {
            try {
                leerVuelos();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void leerVuelos() throws InterruptedException {
        for (Vuelo v: vuelos) {
            sleep(Vuelo.generateRandomNumberBetween(5000, 1000));
            leerVuelo(v);
        }
    }

    private void leerVuelo(Vuelo v) throws InterruptedException {
        setTextLeyendoVuelo(v);
        v.leerVuelo(this);
        setTextEsperarLeerVuelo();
    }

    public void setTextPorLeerVuelo(Vuelo vuelo) {
        label.setText("Por leer vuelo " + vuelo.getNombre());
    }

    public void setTextLeyendoVuelo(Vuelo vuelo) {
        label.setText("Leyendo vuelo " + vuelo.getNombre());
    }

    public void setTextEsperarLeerVuelo() {
        label.setText("Esperando...");
    }

    public ArrayList<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(ArrayList<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    public Vuelo getVuelo() {
        return vuelo;
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
