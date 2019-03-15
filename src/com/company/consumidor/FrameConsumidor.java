package com.company.consumidor;

import javax.swing.*;

public class FrameConsumidor extends JFrame implements Runnable {
    public static void main(String args[]) {
        FrameConsumidor frame = new FrameConsumidor();
    }

    Contenedor contenedor;
    Productor productor;
    Consumidor consumidor;

    JLabel contenedorLabel;
    JLabel productorLabel;
    JLabel consumidorLabel;

    public FrameConsumidor() {
        this.setBounds(300, 300, 500, 500);
        consumidor = new Consumidor();
        productor= new Productor();
        contenedor = new Contenedor();

        consumidorLabel = new JLabel();
        productorLabel = new JLabel();
        contenedorLabel = new JLabel();

        productorLabel.setBounds(25, 25, 250, 25);
        contenedorLabel.setBounds(25, 60, 250, 25);
        consumidorLabel.setBounds(25, 90, 250, 25);
        contenedor.setLabel(contenedorLabel);
        productor.setLabel(productorLabel);
        consumidor.setLabel(consumidorLabel);

        consumidor.setContenedor(contenedor);
        productor.setContenedor(contenedor);

        productor.start();
        consumidor.start();

        this.add(consumidorLabel);
        this.add(contenedorLabel);
        this.add(productorLabel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        new Thread(this).start();
    }


    @Override
    public void run() {
        while (true) {}
    }
}
