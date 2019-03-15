package com.company.consumidor;

import javax.swing.*;
import java.util.ArrayList;

public class Contenedor {
    private int dato;
    private ArrayList<Integer> datos;
    private boolean hayDato = false;
    private JLabel label;

    public Contenedor() {
        datos = new ArrayList<>(5);
    }

    public synchronized int get() {
        while (hayDato == false) {
            try {
                System.out.println("Consumidor esperando porque no hay dato aqui!!");
                wait();
            } catch (Exception err) {}

            System.out.println("Fuera y despues del wait");
        }
        System.out.println("Vaciando");
        System.out.println("Consumidor obteniendo valor!!");
        hayDato = false;
        escribirContenedorVacio();
        notifyAll();
        return dato;
    }

    public synchronized void put(int valor) {
        while (hayDato) {
            try {
                wait();
            } catch (Exception err) {}
        }
        System.out.println("Escribiendo valor");
        dato = valor;
        hayDato = true;
        writeInLabelActualValues();
        notifyAll();
    }

    public void writeInLabelActualValues(){
        label.setText("En el contenedor esta el valor de: " + dato);
    }

    public void escribirContenedorVacio() {
        label.setText("El contenedor esta vacio");
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

}
