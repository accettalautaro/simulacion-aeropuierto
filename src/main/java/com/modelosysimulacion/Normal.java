package com.modelosysimulacion;

public class Normal implements Distribucion{
    private int n;
    private double media;
    private double desviacion;
    
    public Normal(int n, double media, double desviacion) {
        this.n = n;
        this.media = media;
        this.desviacion = desviacion;
    }

    @Override
    public double getValue(Randomizer randomizer) {
        double sumatoria=0;
        double z;
        for(int i=0;i<n;i++){
            sumatoria=sumatoria+randomizer.nextRandom();
        }
        z=(sumatoria-this.media)/this.desviacion;


        return (z*this.desviacion+this.media);
    }

}
