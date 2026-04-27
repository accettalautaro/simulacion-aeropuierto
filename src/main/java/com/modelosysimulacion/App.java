package com.modelosysimulacion;

import java.util.ArrayList;
import java.util.Arrays;

import com.modelosysimulacion.eventos.Arribo;
import com.modelosysimulacion.eventos.Evento;
import com.modelosysimulacion.generadores.GenerarArriboT1;
import com.modelosysimulacion.generadores.GenerarSalidaT2;
import com.modelosysimulacion.generadores.GenerarTArribo;
import com.modelosysimulacion.generadores.GenerarTSalida;
import com.modelosysimulacion.generadores.GenerarTiempo;
import com.modelosysimulacion.random.Randomizer;
import com.modelosysimulacion.random.Randomizer1;

public class App 
{
    public static void main( String[] args )
    {
        Fel fel = new Fel();
        Estadisticas estadisticas= new Estadisticas();
        Randomizer randomizer= new Randomizer1();
        GenerarTiempo genArribo= new GenerarTArribo(randomizer);
        GenerarTiempo genSalida = new GenerarTSalida(randomizer);
        double duracionSimulacion=40320.0;//4 semanas 
        ArrayList<Server> servers= new ArrayList<>(Arrays.asList(new Server(),new Server(),new Server(),new Server(),new Server()));
        //inicio de simulacion
        Avion primerAvion = new Avion(1, 0);
        fel.programarEvento(new Arribo(0, primerAvion));
        double clock = fel.verProximo().getTiempo();
        //bucle de simulacion
        while (!fel.estaVacia()&& clock  <= duracionSimulacion) {
            Evento eventoActual = fel.extraerProximo();
            clock = eventoActual.getTiempo();
            eventoActual.procesar(servers, fel, estadisticas, genArribo, genSalida);
            

            
        }
        
        /*if(!server.estaOcupada()){
            estadisticas.registrarTiempoOcio(server.getInicioUltimoOcio(), duracionSimulacion);

        }*/
        for(int i = 0; i< servers.size();i++){
            if(!servers.get(i).estaOcupada()){
                estadisticas.registrarTiempoOcio(i,duracionSimulacion);
            }
        }    
        estadisticas.imprimirRepeorte(duracionSimulacion);
        for(int i=0; i<servers.size();i++){  
            System.out.println("Server "+(i+1)+" desgaste: "+servers.get(i).getDesgaste());
        
        }
    }
}

