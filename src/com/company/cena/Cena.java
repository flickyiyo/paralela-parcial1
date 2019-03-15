package com.company.cena;

public class Cena {
    Pensador[] pensadores;
    Tenedor[] tenedores;

    public Cena(Pensador[] pensadores, Tenedor[] tenedores) {
        this.pensadores = pensadores;
        this.tenedores = tenedores;
    }

    public void iniciarPensadores() {
        for (Pensador pensador: pensadores) {
            pensador.start();
        }
    }

    public Pensador[] getPensadores() {
        return pensadores;
    }

    public Tenedor[] getTenedores() {
        return tenedores;
    }

    public void setPensadores(Pensador[] pensadores) {
        this.pensadores = pensadores;
    }

    public void setTenedores(Tenedor[] tenedores) {
        this.tenedores = tenedores;
    }
}
