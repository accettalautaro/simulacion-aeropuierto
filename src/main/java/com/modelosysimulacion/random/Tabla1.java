package com.modelosysimulacion.random;

import java.util.ArrayList;
import java.util.Arrays;

public class Tabla1 implements Randomizer{
    private ArrayList<Double> tiempos;

    
    public Tabla1() {
        this.tiempos= new ArrayList<>(Arrays.asList(0.01,0.5,0.9));
        
    }


    @Override
    public double nextRandom() {
        double r= tiempos.getFirst();
        tiempos.removeFirst();
        return r;
    }

    
}
