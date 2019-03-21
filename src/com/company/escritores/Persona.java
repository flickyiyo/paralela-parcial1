package com.company.escritores;

import java.util.Random;

public class Persona {
    protected Random rnd;
    protected int max = 4000, min = 1000;

    protected int generateRandomNumber() {
        return rnd.nextInt((max - min) + 1) + min;
    }
}
