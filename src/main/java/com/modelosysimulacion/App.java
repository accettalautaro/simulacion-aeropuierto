package com.modelosysimulacion;

import java.util.Random;

public class App 
{
    public static void main( String[] args )
    {
        Fel fel = new Fel();
        Server server = new Server();
        Estadisticas estadisticas= new Estadisticas();
        GenerarTiempo genArribo= new GenerarArriboT1();
        GenerarTiempo genSalida = new GenerarSalidaT2();
        Uniforme uniforme= new Uniforme(10,25);
        Exponencial exponencial= new Exponencial(15);
        double duracionSimulacion=40320.0;//4 semanas 
        Random random= new Random();
        Randomizer randomizer= new Randomizer1(random);
        //inicio de simulacion
        Avion primerAvion = new Avion(1, 0);
        fel.programarEvento(new Arribo(0, primerAvion));
        double clock = fel.verProximo().getTiempo();
        //bucle de simulacion
        while (!fel.estaVacia()&& clock  <= duracionSimulacion) {
            Evento eventoActual = fel.extraerProximo();
            clock = eventoActual.getTiempo();
            //eventoActual.procesar(server, fel, estadisticas, genArribo, genSalida);
            eventoActual.procesar1(server, fel, estadisticas,exponencial, uniforme,randomizer);

            
        }
        
        if(!server.estaOcupada()){
            estadisticas.registrarTiempoOcio(server.getInicioUltimoOcio(), duracionSimulacion);

        }
        estadisticas.imprimirRepeorte(duracionSimulacion);
        System.out.println("Desgaste: "+ server.getDesgaste());
    }
}
