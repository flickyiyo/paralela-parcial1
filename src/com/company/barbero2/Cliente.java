package com.company.barbero2;

import java.util.Random;

public class Cliente extends Persona {
    private Random rnd;
    private int tiempoDeCorte;
    BarberiaManager barberiaManager;

    public Cliente(BarberiaManager b) {
        this.rnd = new Random();
        generarTiempoDeCorte();
        this.barberiaManager = b;
    }

    public void pedirServicio(Silla silla) throws InterruptedException {
        silla.usar();
        this.silla.dejarDeUsar();
        this.silla.getLabel().setText("Silla libre");
        this.silla = silla;
        this.silla.getLabel().setText("Siendo usada por cliente");
    }

    @Override
    public void run() {
        try {
            barberiaManager.solicitarServicio(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {}
    }

    public int generarNumeroEnRango() {
        return this.rnd.nextInt((tiempoMaximo - tiempoMinimo) + 1) + tiempoMinimo;
    }

    private void generarTiempoDeCorte() {
        this.tiempoDeCorte = generarNumeroEnRango();
    }

    public Random getRnd() {
        return rnd;
    }

    public void setRnd(Random rnd) {
        this.rnd = rnd;
    }

    public int getTiempoDeCorte() {
        return tiempoDeCorte;
    }
}
