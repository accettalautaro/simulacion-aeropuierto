package com.modelosysimulacion;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.modelosysimulacion.distribuciones.Distribucion;
import com.modelosysimulacion.distribuciones.Empirica;
import com.modelosysimulacion.random.Randomizer;
import com.modelosysimulacion.random.Tabla1;
import com.modelosysimulacion.random.Tabla2;

public class EmpiricaTest {
    @Test
    public void generarTiempoArribo() {
        ArrayList<Double> acumulada = new ArrayList<>(Arrays.asList(0.35,0.8,1.0));
        ArrayList<Double> tiempos= new ArrayList<>(Arrays.asList(10.0,15.0,17.0));
        Distribucion empirica;
        Randomizer randomizer= new Tabla1();
        empirica = new Empirica(acumulada, tiempos);
        assertAll("test tiempo Arribos", 
            () -> assertEquals(10.0,empirica.getValue(randomizer)),
            () -> assertEquals(15.0,empirica.getValue(randomizer)),
            () -> assertEquals(17.0,empirica.getValue(randomizer))
        );
    }

    @Test
    void generarTiempoSalida() {
        ArrayList<Double> acumulada = new ArrayList<>(Arrays.asList(0.38,0.7,0.8,1.0));
        ArrayList<Double> tiempos= new ArrayList<>(Arrays.asList(8.0,10.0,13.0,15.0));
        Distribucion empirica;
        Randomizer randomizer= new Tabla2();
        empirica = new Empirica(acumulada, tiempos);
        assertAll("test tiempo Salidas", 
            () -> assertEquals(8.0,empirica.getValue(randomizer)),
            () -> assertEquals(10.0,empirica.getValue(randomizer)),
            () -> assertEquals(13.0,empirica.getValue(randomizer)),
            () -> assertEquals(15.0,empirica.getValue(randomizer))
        );
        
    }   
    

}
