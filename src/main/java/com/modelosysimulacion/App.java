package com.modelosysimulacion;

public class App 
{
    public static void main( String[] args )
    {
        FEL fel = new ColaPrioridadFEL();
        EstadoPista estado = new EstadoPista();
        ColeccionarEstadisticas estadisticas= new Estadisticas();
        GeneradorTiempos genArribo= new GenerarArriboT1();
        GeneradorTiempos genSalida = new GenerarSalidaT2();

        double duracionSimulacion=40320.0;//4 semanas 
        
        //inicio de simulacion
        Avion primerAvion = new Avion(1, 0);
        fel.programarEvento(new Arribo(0, primerAvion));
        
        //bucle de simulacion
        while (!fel.estaVacia()&& fel.verProximo().getTiempo() <= duracionSimulacion) {
            Evento eventoActual = fel.extraerProximo();
            
            eventoActual.procesar(estado, fel, estadisticas, genArribo, genSalida);

            
        }
        if(!estado.estaOcupada()){
            estadisticas.registrarTiempoOcio(estado.getInicioUltimoOcio(), duracionSimulacion);

        }
        estadisticas.imprimirRepeorte(duracionSimulacion);
    }
}
