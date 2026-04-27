package com.modelosysimulacion.generadores;

import com.modelosysimulacion.distribuciones.Distribucion;
import com.modelosysimulacion.distribuciones.Exponencial;
import com.modelosysimulacion.random.Randomizer;

public class GenerarTArribo implements GenerarTiempo {
    private Randomizer random;

    public GenerarTArribo(Randomizer random) {
        this.random = random;
    }

    @Override
    public double generarTiempo(double clock) {
        double hora = (clock %1440) /60;
        Distribucion exponencial;
        if(hora>=9 && hora <= 13 || hora >= 20 && hora<= 23){
            exponencial= new Exponencial(9);
        }
        else{
            exponencial= new Exponencial(15);
        }
        return exponencial.getValue(this.random);
    }
    

}
