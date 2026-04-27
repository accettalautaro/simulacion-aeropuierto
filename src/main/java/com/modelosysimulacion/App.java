package com.modelosysimulacion;


import com.modelosysimulacion.generadores.GenerarArriboT1;
import com.modelosysimulacion.generadores.GenerarSalidaT2;
import com.modelosysimulacion.generadores.GenerarTArribo;
import com.modelosysimulacion.generadores.GenerarTSalida;
import com.modelosysimulacion.random.Randomizer;
import com.modelosysimulacion.random.Randomizer1;

public class App 
{
    public static void main( String[] args )
    {
        Randomizer randomizer= new Randomizer1();
        
        Simulacion simulador= new Simulacion.Builder()
            .tiempoSimulacion(40320.0)
            .servers(1)
            .tiempoArribos(new GenerarArriboT1(randomizer))
            .tiempoSalidas(new GenerarSalidaT2(randomizer))
            .build();
        simulador.ejecutar();
        simulador.reporte();
    }
}

