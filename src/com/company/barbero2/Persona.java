package com.company.barbero2;

public class Persona extends Thread {
    protected Silla silla;
    protected final int tiempoMaximo = 5000;
    protected final int tiempoMinimo = 500;
    public void pedirSillaBarbero(Silla sillaBarbero) throws InterruptedException {
        sillaBarbero.usar();
        this.silla = sillaBarbero;
    }

    public void salirDeBarberia() {
        this.silla.dejarDeUsar();
        this.silla = null;
    }

    public static boolean isNotPersonaNula(Persona persona) {
        return persona != null;
    }

    public Silla getSilla() {
        return silla;
    }

    public void setSilla(Silla silla) {
        this.silla = silla;
    }
}
