package com.modelosysimulacion;

import java.util.Random;

public class GenerarSalidaT2 implements GeneradorTiempos {
    private final Random random = new Random();
    
    @Override
    public double generarTiempo() {
        double r = random.nextDouble();
        if (r < 0.38) return 8.0;
        if (r < 0.7) return 10.0; 
        if (r < 0.8) return 13.0; 
        return 15.0;
        
    }


}
