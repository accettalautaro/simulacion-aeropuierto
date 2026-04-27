package com.modelosysimulacion.distribuciones;

import java.util.ArrayList;

import com.modelosysimulacion.random.Randomizer;

public class Empirica implements Distribucion {
    private ArrayList<Double> probAcumulada ;
    private ArrayList<Double> tiempos ;

    public Empirica(ArrayList<Double> probAcumulada, ArrayList<Double> tiempos) {
        this.probAcumulada = probAcumulada;
        this.tiempos = tiempos;
    }

    @Override
    public double getValue(Randomizer randomizer) {
        double random = randomizer.nextRandom();
        int i=0;
        while(random> probAcumulada.get(i))
        {
            i++;
        }
        return tiempos.get(i);
    }

    
}
