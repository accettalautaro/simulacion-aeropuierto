package com.modelosysimulacion;

import java.util.Random;

public class Randomizer1 implements Randomizer {
    private final Random random;
    
    public Randomizer1(Random random) {
        this.random = new Random();
    }

    @Override
    public double nextRandom() {
        
        return random.nextDouble();
    }

}
