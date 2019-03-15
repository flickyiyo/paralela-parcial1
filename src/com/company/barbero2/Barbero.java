package com.company.barbero2;

import javax.swing.*;

public class Barbero extends Persona {
    private JLabel label;
    private boolean durmiendo;
    private BarberiaManager clienteManager;
    private StatusBarbero status;
    private Cliente cliente;

    public Barbero(JLabel label, BarberiaManager clienteManager) {
        this.label = label;
        this.clienteManager = clienteManager;
        this.status = StatusBarbero.DORMIDO;
    }

    public synchronized void despertar(Cliente cliente) {
        this.status = StatusBarbero.DESPIERTO;
        this.cliente = cliente;
        this.silla.dejarDeUsar();
        this.silla.getLabel().setText("Silla libre");
        label.setText("Barbero despierto!");
        notifyAll();
    }

    @Override
    public void run() {
        while (true) {
            if (this.status == StatusBarbero.DESPIERTO && cliente != null) { atenderCliente(); continue; }
            if (!this.clienteManager.hayClientes()) { dormir(); }
        }
    }

    public synchronized void dormir() {
        this.status = StatusBarbero.DORMIDO;
        try {
            this.silla.usar();
            this.silla.getLabel().setText("Usada por barbero");
            label.setText("Barbero durmiendo..");
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void atenderCliente() {
        System.out.println("Atendiendo cliente " + cliente.getTiempoDeCorte());
        try {
            System.out.println("Atendiendo cliente " + cliente.getTiempoDeCorte());
            label.setText("Atendiendo cliente " + cliente.getTiempoDeCorte());
            Thread.sleep(cliente.getTiempoDeCorte());
            label.setText("Despidiendo cliente");
        } catch (InterruptedException e) { e.printStackTrace(); }
        cliente.getSilla().dejarDeUsar();
        cliente.getSilla().getLabel().setText("Silla libre");
        clienteManager.getClientes().remove(cliente);
        cliente.interrupt();
        cliente = null;
        notifyAll();
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void setStatus(StatusBarbero status) {
        this.status = status;
    }

    public StatusBarbero getStatus() {
        return status;
    }

    public BarberiaManager getClienteManager() {
        return clienteManager;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
