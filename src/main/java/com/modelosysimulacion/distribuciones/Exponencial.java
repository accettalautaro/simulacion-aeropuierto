package com.modelosysimulacion.distribuciones;

import com.modelosysimulacion.random.Randomizer;

public class Exponencial implements Distribucion  {
    private int media;
    
    public Exponencial(int media) {
        this.media = media;
    }
    
    @Override
    public double getValue(Randomizer randomizer) {
        
        return ((-60/media)*Math.log(1-randomizer.nextRandom()));
    }    

}
