package com.company.escritores;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

public class Vuelo {
    private String nombre;
    private Date fecha;
    private JLabel label;
    private boolean leido;
    private boolean escribiendo;
    private ArrayList<Lector> lectores;

    public Vuelo(String nombre, Date fecha, JLabel label) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.label = label;
        lectores = new ArrayList<>();
    }

    public void leer(Lector lector) throws InterruptedException {
        if (this.isEscribiendo()) handleEscribiendo(lector);
        else if (!verificarLector(lector))
            agregarLector(lector);
        lector.getLabel().setText("Leyendo " + nombre);
        Thread.sleep(3000);
        lector.getLabel().setText("Lector");
        quitarLector(lector);
    }

    public synchronized void handleEscribiendo(Lector lector) throws InterruptedException {
        while (isEscribiendo()) {
            wait();
        }
        notifyAll();
    }

    public synchronized void actualizarVuelo(Date fecha) throws InterruptedException {
        if (isEscribiendo()) return;
        this.setEscribiendo(true);
        while (!this.lectores.isEmpty())
            wait();
        this.fecha = fecha;
        this.setEscribiendo(false);
        notifyAll();
    }

    public boolean verificarLector(Lector lector) {
        return encontrarLector(lector) >= 0;
    }

    public int encontrarLector(Lector lector) {
        for (int i = 0; i < this.lectores.size(); i++)
            if (this.lectores.get(i) == lector)
                return i;
        return -1;
    }

    public void agregarLector(Lector lector) {
        this.lectores.add(lector);
    }

    public synchronized void quitarLector(Lector lector) {
        this.lectores.remove(lector);
        notifyAll();
    }

    public Date getFecha() {
        return fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public JLabel getLabel() {
        return label;
    }

    public boolean isEscribiendo() {
        return escribiendo;
    }

    public boolean isLeido() {
        return leido;
    }

    public synchronized void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public synchronized void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void setEscribiendo(boolean escribiendo) {
        this.escribiendo = escribiendo;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    public ArrayList<Lector> getLectores() {
        return lectores;
    }

    public void setLectores(ArrayList<Lector> lectores) {
        this.lectores = lectores;
    }
}
