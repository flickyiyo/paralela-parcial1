package com.company.barbero2;

import javax.swing.*;
import java.util.ArrayList;

public class BarberiaManager extends Thread {
    private Silla[] sillas;
    private ArrayList<Cliente> clientes;
    private Barbero barbero;
    private Silla sillaBarbero;

    public BarberiaManager(Silla[] sillas, Silla sillaBarbero, JLabel labelBarbero) {
        this.sillas = sillas;
        this.sillaBarbero = sillaBarbero;
        this.clientes = new ArrayList<>(5);
        crearBarbero(labelBarbero);
    }

    public void crearBarbero(JLabel label) {
        Barbero barbero = new Barbero(label, this);
        barbero.setSilla(sillaBarbero);
        this.barbero = barbero;
        barbero.start();
    }

    public void agregarCliente(Cliente cliente) throws InterruptedException {
        if (hayEspacio()) {
            configurarCliente(cliente);
        }
    }

    private void configurarCliente(Cliente cliente) throws InterruptedException {
        clientes.add(cliente);
        cliente.setSilla(buscarSillaDisponible());
        cliente.getSilla().usar();
        cliente.getSilla().getLabel().setText("Usada...");
        cliente.start();
    }

    public void solicitarServicio(Cliente cliente) throws InterruptedException {
            despertarBarberoSiDuerme(cliente);
            cliente.pedirServicio(sillaBarbero);
            // barbero.atenderCliente();
    }

    private void despertarBarberoSiDuerme(Cliente cliente) {
        synchronized (barbero) {
            if (barbero.getStatus() == StatusBarbero.DORMIDO)
                barbero.despertar(cliente);
            else
                barbero.setCliente(cliente);
            barbero.notify();
        }

    }

    public Silla buscarSillaDisponible() {
        for (Silla silla: sillas) {
            if (!silla.isOcupada()) {
                return silla;
            }
        }
        return null;
    }

    public boolean hayEspacio() {
        for (Silla silla: sillas) {
            if (!silla.isOcupada()) {
                return true;
            }
        }
        return false;
    }

    public boolean hayClientes() {
        for (Silla silla: sillas) {
            if (silla.isOcupada()) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Silla[] getSillas() {
        return sillas;
    }

    public void setSillas(Silla[] sillas) {
        this.sillas = sillas;
    }

    public void setSillaBarbero(Silla sillaBarbero) {
        this.sillaBarbero = sillaBarbero;
    }

    public Silla getSillaBarbero() {
        return sillaBarbero;
    }
}
