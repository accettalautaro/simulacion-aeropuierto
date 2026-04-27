package com.modelosysimulacion.generadores;

import com.modelosysimulacion.distribuciones.Distribucion;
import com.modelosysimulacion.distribuciones.Uniforme;
import com.modelosysimulacion.random.Randomizer;

public class GenerarTSalida implements GenerarTiempo{
    private Randomizer random;

    public GenerarTSalida(Randomizer random) {
        this.random = random;
    }

    @Override
    public double generarTiempo(double clock) {
        Distribucion uniforme= new Uniforme(10,25 );

        return uniforme.getValue(this.random);
    }

    
    
}
