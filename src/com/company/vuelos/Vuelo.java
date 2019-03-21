package com.company.vuelos;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Vuelo {
    private boolean siendoLeido;
    private boolean siendoEscrito;
    private long time;
    private String nombre;
    private ArrayList<Lector> lectores;
    private JLabel label;

    public static int generateRandomNumberBetween(int max, int min) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public Vuelo(String nombre, JLabel label) {
        siendoEscrito = false;
        siendoLeido = false;
        time = System.currentTimeMillis();
        lectores = new ArrayList<>();
        this.label = label;
        this.nombre = nombre;
    }

    public void leerVuelo(Lector lector) throws InterruptedException {
        if (siendoEscrito == false) {
            System.out.println(lectores.size());
            agregarLector(lector);
            Thread.sleep(generateRandomNumberBetween(5000, 2000));
            removerLector(lector);
        }
    }

    private synchronized void esperarHastaTerminarDeEscribir() throws InterruptedException {
        while (isSiendoEscrito()) wait();
    }

    private synchronized void removerLector(Lector lector) {
        this.lectores.remove(lector);
        notifyAll();
    }

    public void agregarLector(Lector lector) {
        if (!lectores.contains(lector)) {
            lectores.add(lector);
        }
    }

    public synchronized void escribirVuelo(long time) throws InterruptedException {
        this.siendoEscrito = true;
        esperarHastaLiberarLectores();
        modificarVuelo(time);
        notify();
    }

    private void modificarVuelo(long time) {
        if (siendoEscrito) {
            this.time = time;
            this.siendoEscrito = false;
        }
    }

    public synchronized void esperarHastaLiberarLectores() throws InterruptedException {
        while (tieneLectores())
            wait();
    }

    private synchronized void modificarVuelo() {
        this.time = System.currentTimeMillis();
    }

    public boolean tieneLectores() {
        return !this.lectores.isEmpty();
    }

    public boolean isSiendoEscrito() {
        return siendoEscrito;
    }

    public boolean isSiendoLeido() {
        return siendoLeido;
    }

    public void setSiendoEscrito(boolean siendoEscrito) {
        this.siendoEscrito = siendoEscrito;
    }

    public void setSiendoLeido(boolean siendoLeido) {
        this.siendoLeido = siendoLeido;
    }

    public JLabel getLabel() {
        return label;
    }

    public ArrayList<Lector> getLectores() {
        return lectores;
    }

    public String getNombre() {
        return nombre;
    }

    public long getTime() {
        return time;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void setLectores(ArrayList<Lector> lectores) {
        this.lectores = lectores;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
