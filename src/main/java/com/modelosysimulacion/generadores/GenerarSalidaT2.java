package com.modelosysimulacion.generadores;

import java.util.ArrayList;
import java.util.Arrays;

import com.modelosysimulacion.distribuciones.Distribucion;
import com.modelosysimulacion.distribuciones.Empirica;
import com.modelosysimulacion.random.Randomizer;


public class GenerarSalidaT2 implements GenerarTiempo {
    private Randomizer random;
    private ArrayList<Double> acumulada;
    private ArrayList<Double> tiempos;
    
    
    public GenerarSalidaT2(Randomizer random) {
        this.random= random;
        this.acumulada= new ArrayList<>(Arrays.asList(0.38,0.7,0.8,1.0));
        this.tiempos= new ArrayList<>(Arrays.asList(8.0,10.0,13.0,15.0));
    }


    @Override
    public double generarTiempo(double clock) {
        Distribucion empirica= new Empirica(this.acumulada, this.tiempos);
        return empirica.getValue(this.random);
    }


}
