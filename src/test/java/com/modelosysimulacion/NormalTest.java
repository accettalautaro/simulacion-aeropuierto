package com.modelosysimulacion;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.modelosysimulacion.distribuciones.Normal;
import com.modelosysimulacion.random.Randomizer;
import com.modelosysimulacion.random.Randomizer1;

public class NormalTest {
    @Test
    public void normalTest(){
        double u=5;
        double d=1;
        Normal normal = new Normal(u, d);
        int n=10000;
        double contador1d=0;
        double contador2d=0;
        double contador3d=0;
        double delta=0.1;
        Randomizer random= new Randomizer1();
        double r;
        for(int i=0; i<n;i++){
            r=normal.getValue(random);
            if(u-d<=r && r<=u+d){
                contador1d= contador1d +1;
            }
            if(u-2*d<=r && r<= u+2*d){
                contador2d= contador2d +1;
            }
            if(u-3*d<=r && r<= u+3*d){
                contador3d= contador3d +1;
            }
        }
        System.out.println("contador 1d:"+contador1d);
        System.out.println("contador 2d:"+contador2d);
        System.out.println("contador 3d:"+contador3d);

        
        double por1d =(contador1d/n)*100;
        double por2d =(contador2d/n)*100;
        double por3d =(contador3d/n)*100;
        System.out.println("porcentaje 1d"+por1d);
        System.out.println("porcentaje 2d"+por2d);
        System.out.println("porcentaje 3d"+por3d);
        
        assertAll("test tiempo Arribos", 
            () -> assertEquals(68.0,por1d , delta),
            () -> assertEquals(95.5,por2d , delta),
            () -> assertEquals(99.7,por3d, delta)
        );
    }
}
