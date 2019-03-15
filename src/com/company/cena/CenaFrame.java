package com.company.cena;

import javax.swing.*;
import java.util.ArrayList;

public class CenaFrame extends JFrame implements Runnable {

    public static void main(String args[]) {
        new CenaFrame();
    }

    JLabel[] labelsTenedores;
    JLabel[] labelsFilosofos;

    public CenaFrame() {
        this.setBounds(100, 100, 500, 500);
        this.setLayout(null);

        labelsFilosofos = new JLabel[5];
        labelsTenedores = new JLabel[5];
        Pensador[] pensadors = new Pensador[5];
        Tenedor[] tenedors = new Tenedor[5];
        String [] nombres = {"Heracito", "Aristoteles", "Platon", "Socrates", "Anaxagoras"};
        for (int i = 0; i < 5; i++) {
            JLabel lbTenedor= new JLabel();
            lbTenedor.setBounds(20, (27 * i) + 10, 200, 25);
            labelsTenedores[i] = lbTenedor;
            add(lbTenedor);
            JLabel lbPensador = new JLabel();
            lbPensador.setBounds(220, (27 * i) + 10, 200, 25);
            labelsFilosofos[i] = lbPensador;
            add(lbPensador);
        }

        for (int i = 0; i < 5; i++) {
            tenedors[i] = new Tenedor(labelsTenedores[i]);
        }

        for (int i = 0; i < 5; i++) {
            pensadors[i] = new Pensador(nombres[i], tenedors[i], tenedors[nombres[i].equals("Anaxagoras") ? 0 : i + 1], labelsFilosofos[i]);
        }

        Cena cena = new Cena(pensadors, tenedors);
        cena.iniciarPensadores();

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void run() {

    }

}
