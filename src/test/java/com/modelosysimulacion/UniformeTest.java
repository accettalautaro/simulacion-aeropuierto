package com.modelosysimulacion;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.modelosysimulacion.distribuciones.Uniforme;
import com.modelosysimulacion.random.Randomizer;
import com.modelosysimulacion.random.Tabla1;

public class UniformeTest {
    @Test
    public void uniformeTest(){
        Randomizer random = new Tabla1();
        Uniforme uniforme= new Uniforme(10, 25);
        double delta = 0.000000001;
        

        assertAll("test tiempo Arribos", 
            () -> assertEquals(10.15, uniforme.getValue(random), delta),
            () -> assertEquals(17.5, uniforme.getValue(random), delta),
            () -> assertEquals(23.5, uniforme.getValue(random), delta)
        );

    }
}
