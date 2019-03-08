package com.company;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main extends JFrame implements Runnable {
    ArrayList<Tenedor> tenedores;
    public static void main(String[] args) {
        Main main = new Main();
        Thread t = new Thread(main);
        t.start();
    }
    public ArrayList<JLabel> labelsTenedores;
    ArrayList<JLabel> labelsFilosofos;
    ArrayList<Filosofo> filosofos;
    String []names = {"Aristoteles", "Platon", "Arquimides", "Un Wey", "Otro wey"};
    long eatTimes[] = {1000, 2500, 1234, 7000, 4000};
    long thinkTimes[] = {3000, 1400, 2345, 2000, 5000};

    public Main() {
        this.setBounds(0, 0, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.tenedores = new ArrayList<>(5);
        this.labelsTenedores = new ArrayList<>(5);
        this.labelsFilosofos = new ArrayList<>(5);
        for (int i = 0; i < 5; i ++) {
            System.out.println(names[i]);
            this.labelsFilosofos.add(new JLabel(names[i] + " Pensando"));
            this.labelsTenedores.add(new JLabel("Tenedor " + i + "Libre"));
            this.labelsTenedores.get(i).setBounds(0, 25*(i), 200, 100);
            this.labelsFilosofos.get(i).setBounds(260, 25*(i), 200, 100);
            this.tenedores.add(new Tenedor(this, i));

        }

        Thread f1 = new Thread(new Filosofo(7000, 1000, tenedores.get(4), tenedores.get(0), this, "Aristoteles", 0, labelsFilosofos.get(0)));
        Thread f2 = new Thread(new Filosofo(6000, 1400, tenedores.get(0), tenedores.get(1), this, "platon", 1, labelsFilosofos.get(1)));
        Thread f3 = new Thread(new Filosofo(3000, 1000, tenedores.get(1), tenedores.get(2), this, "Arquimides", 2, labelsFilosofos.get(2)));
        Thread f4 = new Thread(new Filosofo(8000, 2000, tenedores.get(2), tenedores.get(3), this, "Un wey", 3, labelsFilosofos.get(3)));
        Thread f5 = new Thread(new Filosofo(1200, 5000, tenedores.get(3), tenedores.get(4), this, "Otro wey", 4, labelsFilosofos.get(4)));
        f1.start();
        f2.start();
        f3.start();
        f4.start();
        f5.start();
        for (int i = 0 ; i < 5 ; i++) {
            this.add(labelsTenedores.get(i));
            this.add(labelsFilosofos.get(i));
        }
    }

    @Override
    public void run() {
        while(true) {}
    }
}
