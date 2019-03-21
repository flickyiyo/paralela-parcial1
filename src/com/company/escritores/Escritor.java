package com.company.escritores;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

public class Escritor implements Runnable {
    private ArrayList<Vuelo> vuelos;
    private JLabel label;
    private boolean isEscribiendo = false;

    public Escritor(ArrayList<Vuelo> vuelos, JLabel label) {
        this.vuelos = vuelos;
        this.label = label;
    }

    @Override
    public void run() {
        while (true) {
            try {
                label.setText("Verificando...");
                verificarVuelos();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    public void verificarVuelos() throws InterruptedException {
        for (Vuelo vuelo: vuelos) {
            synchronized (vuelo) {
                actualizarVuelo(vuelo);
            }
        }
        isEscribiendo = false;
    }

    public void actualizarVuelo(Vuelo vuelo) throws InterruptedException {
        if (deberiaActualizarVuelo(vuelo) && !isEscribiendo) {
            isEscribiendo = true;
            label.setText("Reescribiendo " + vuelo.getNombre());
            vuelo.actualizarVuelo(new Date());
            label.setText("Verificando...");
        }
    }

    public synchronized boolean deberiaActualizarVuelo(Vuelo vuelo) {
        return vuelo.getFecha().getTime() + 5000 < System.currentTimeMillis();
    }

    public JLabel getLabel() {
        return label;
    }

    public ArrayList<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void setVuelos(ArrayList<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    public boolean isEscribiendo() {
        return isEscribiendo;
    }

    public void setEscribiendo(boolean escribiendo) {
        isEscribiendo = escribiendo;
    }
}
