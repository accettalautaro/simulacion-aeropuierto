package com.modelosysimulacion;

public class Uniforme implements Distribucion {
    private int a;
    private int b;
    
    public Uniforme(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double getValue(Randomizer randomizer) {
        
        return (a+(b-a)*randomizer.nextRandom());
    }

}
