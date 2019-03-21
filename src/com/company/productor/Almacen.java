package com.company.productor;

public class Almacen {
    boolean enUso;
    int[] elementos;

    public Almacen() {
        elementos = new int[5];
    }



    public void initElementos() {
        for (int elemento: elementos) {
            elemento = -1;
        }
    }

    public boolean hayEspacio() {
        for (int elemento: elementos) {
            if (elemento == -1) {
                return true;
            }
        }
        return false;
    }

    public int[] getElementos() {
        return elementos;
    }

    public void setElementos(int[] elementos) {
        this.elementos = elementos;
    }

    public boolean isEnUso() {
        return enUso;
    }

    public void setEnUso(boolean enUso) {
        this.enUso = enUso;
    }
}
