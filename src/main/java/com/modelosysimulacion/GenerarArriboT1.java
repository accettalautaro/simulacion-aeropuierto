package com.modelosysimulacion;

import java.util.Random;

public class GenerarArriboT1 implements GenerarTiempo {
    private final Random random;
    
    public GenerarArriboT1() {
        this.random = new Random();;
    }

    @Override
        public double generarTiempo(){
            double r = random.nextDouble();
            if (r < 0.35) return 10.0;
            if (r < 0.80) return 15.0; 
            return 17.0;
            
        }

}
