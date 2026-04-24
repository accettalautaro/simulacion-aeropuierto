package com.modelosysimulacion;

public class Normal implements Distribucion{

    private double media;
    private double desviacion;
    
    public Normal( double media, double desviacion) {
        this.media = media;
        this.desviacion = desviacion;
    }

    @Override
    public double getValue(Randomizer randomizer) {
        double sumatoria=0;
        double z;
        for(int i=0;i<35;i++){
            sumatoria=sumatoria+randomizer.nextRandom();
        }
        z=(sumatoria-this.media)/this.desviacion;


        return (z*this.desviacion+this.media);
    }

}
