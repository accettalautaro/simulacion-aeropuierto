package com.modelosysimulacion;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


import com.modelosysimulacion.random.Randomizer;
import com.modelosysimulacion.distribuciones.Exponencial;
import com.modelosysimulacion.random.Tabla1;
public class ExponencialTest {
    @Test
    public void exponencialTest(){
        Randomizer random = new Tabla1();
        Exponencial exponencial = new Exponencial(15) ;
        double delta = 0.000000001;
        

        assertAll("test tiempo Arribos", 
            () -> assertEquals(0.04020134341, exponencial.getValue(random), delta),
            () -> assertEquals(2.772588722, exponencial.getValue(random), delta),
            () -> assertEquals(9.210340372, exponencial.getValue(random), delta)
        );
    }
}
