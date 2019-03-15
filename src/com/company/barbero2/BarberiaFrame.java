package com.company.barbero2;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class BarberiaFrame extends JFrame implements Runnable {
    public static void main(String[] args){
        new BarberiaFrame();
    }

    JLabel[] labelsSillas;
    JLabel labelBarbero;
    JLabel labelSillaBarbero;
    JButton boton;

    public BarberiaFrame() {
        this.setBounds(100, 100, 500, 500);
        this.setLayout(null);
        labelsSillas = new JLabel[5];
        Silla[] sillas = new  Silla[5];

        for (int i = 0; i < 5; i++) {
            labelsSillas[i] = new JLabel();
            labelsSillas[i].setText("Silla libre");
            labelsSillas[i].setBounds(20, 27 * (i + 1), 200, 25);
            sillas[i] = new Silla();
            sillas[i].setLabel(labelsSillas[i]);
            add(labelsSillas[i]);
        }

        Silla sillabarbero = new Silla();
        labelSillaBarbero = new JLabel();
        labelSillaBarbero.setBounds(220, 50, 300, 25);
        labelSillaBarbero.setText("Silla barbero libre");
        sillabarbero.setLabel(labelSillaBarbero);
        add(labelSillaBarbero);

        labelBarbero = new JLabel();
        labelBarbero.setBounds(220, 80, 300, 26);
        labelBarbero.setText("Barbero");
        add(labelBarbero);

        boton = new JButton();
        boton.setBounds(220, 120, 200, 50);
        boton.setText("Agregar Cliente");
        add(boton);

        BarberiaManager barberia = new BarberiaManager(sillas, sillabarbero, labelBarbero);

        boton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    barberia.agregarCliente(new Cliente(barberia));
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {}
    }
}
