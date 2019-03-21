package com.company.escritores;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

public class EscritorLectorFrame extends JFrame {
    public static void main(String []args) {
        new EscritorLectorFrame();
    }

    JLabel[] labelsVuelos;
    JLabel[] labelsLectores;
    JLabel[] labelsEscritores;

    public EscritorLectorFrame() {
        this.setBounds(100, 100, 500, 500);
        this.setLayout(null);

        labelsVuelos = new JLabel[5];
        labelsEscritores = new JLabel[5];
        labelsLectores = new JLabel[5];
        ArrayList<Escritor> escritores = new ArrayList<>(5);
        ArrayList<Lector> lectores = new ArrayList<>(5);
        ArrayList<Vuelo> vuelos = new ArrayList<>(5);

        for (int i = 0; i < 5; i++) {
            inicializarLabelLector(i);
            inicializarLabelEscritor(i);
            inicializarLabelVuelo(i);
            vuelos.add(new Vuelo("Vuelo " + i+1, new Date(), labelsVuelos[i]));
            escritores.add(new Escritor(vuelos, labelsEscritores[i]));
            lectores.add(new Lector(vuelos.get(i), labelsLectores[i]));
        }

        for (int i = 0; i < 5; i++) {
            new Thread(escritores.get(i)).start();
            new Thread(lectores.get(i)).start();
        }

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void inicializarLabelLector(int index) {
        JLabel l = new JLabel();
        l.setBounds(20, 25*(index + 1), 150, 25);
        add(l);
        labelsLectores[index] = l;
    }

    public void inicializarLabelEscritor(int index) {
        JLabel l = new JLabel();
        l.setBounds(150, 25*(index + 1), 150, 25);
        add(l);
        labelsEscritores[index] = l;
    }

    public void inicializarLabelVuelo(int index) {
        JLabel l = new JLabel();
        l.setBounds(300, 25*(index + 1), 150, 25);
        add(l);
        labelsVuelos[index] = l;
    }

}
