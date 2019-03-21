package com.company.vuelos;

import javax.swing.*;
import java.util.ArrayList;

public class AerelineaFrame extends JFrame {
    public static void main(String args[]) {
        new AerelineaFrame();
    }

    ArrayList<JLabel> labelsVuelos;
    ArrayList<JLabel> labelsLectores;
    ArrayList<JLabel> labelsEscritores;

    public AerelineaFrame() {
        this.setBounds(100, 100, 700, 500);
        this.setLayout(null);

        labelsEscritores = new ArrayList<>(5);
        labelsVuelos = new ArrayList<>(5);
        labelsLectores = new ArrayList<>(5);

        Escritor[] escritors = new Escritor[5];
        Lector[] lectors = new Lector[5];
        ArrayList<Vuelo> vuelos = new ArrayList<>(5);

        for (int i = 0; i < 5; i++) {
            JLabel lVuelo = new JLabel("Iniciando");
            JLabel lEscritor = new JLabel("Iniciando");
            JLabel lLector = new JLabel("Iniciando");
            lLector.setBounds(10, 25 * (i + 1), 150, 25);
            lVuelo.setBounds(160, 25 * (i + 1), 150, 25);
            lEscritor.setBounds(320, 25 * (i + 1), 200, 25);
            labelsLectores.add(lLector);
            labelsVuelos.add(lVuelo);
            labelsEscritores.add(lEscritor);
            add(lLector);
            add(lEscritor);
            add(lVuelo);
        }

        for (int i = 0; i < 5; i++) {
            Escritor e = new Escritor(null, vuelos, labelsEscritores.get(i));
            Lector l = new Lector(vuelos, labelsLectores.get(i));
            Vuelo v = new Vuelo("Vuelo " + i, labelsVuelos.get(i));
            escritors[i] = e;
            lectors[i] = l;
            vuelos.add(v);
        }

        for (int i = 0; i < 5; i++) {
            escritors[i].start();
            lectors[i].start();
        }

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
