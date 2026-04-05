package com.modelosysimulacion;

import java.util.Random;

public class GenerarArriboT1 implements GeneradorTiempos {
    private final Random random= new Random();
    @Override
        public double generarTiempo(){
            double r = random.nextDouble();
            if (r < 0.35) return 10.0;
            if (r < 0.80) return 15.0; 
            return 17.0;
            
        }

}
