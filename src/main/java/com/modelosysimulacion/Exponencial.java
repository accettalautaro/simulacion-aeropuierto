package com.modelosysimulacion;

public class Exponencial implements Distribucion  {
    private int media;
    
    public Exponencial(int media) {
        this.media = media;
    }

    @Override
    public double getValue(Randomizer randomizer) {
        
        return ((-1/media)*Math.log(1-randomizer.nextRandom()));
    }
    
    
    

}
