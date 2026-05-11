package com.modelosysimulacion.eventos;

import java.util.ArrayList;

import com.modelosysimulacion.Avion;
import com.modelosysimulacion.Estadisticas;
import com.modelosysimulacion.Fel;
import com.modelosysimulacion.Server;
import com.modelosysimulacion.Criterios.Criterio;
import com.modelosysimulacion.Criterios.Seleccionar;
import com.modelosysimulacion.generadores.GenerarTiempo;
public class Arribo extends Evento{
    private final Avion avion;

    public Arribo(double timepoEjecucion, Avion avion) {
        super(timepoEjecucion,1);
        this.avion = avion;
    }

    @Override
    public void procesar(ArrayList<Server> servers, Fel fel, Estadisticas estadisticas, GenerarTiempo genArribo, GenerarTiempo genSalida){
        int servidor;
        //actualizo cant aviones  arribados
        estadisticas.registrarArribo();
        Criterio seleccionar = new Seleccionar();
        servidor=seleccionar.seleccionar(servers);
        if(!servers.get(servidor).estaOcupada()){
            // server desocupado

            servers.get(servidor).ocupar();
            //estadisticas.registrarTiempoEspera(0.0); // no hay tiempo de espera
            estadisticas.registrarTiempoOcio(servidor, this.tiempoEjecucion); //registro el tiempo de ocio
            //programo la salida del avion
            double salida = this.tiempoEjecucion + genSalida.generarTiempo(this.tiempoEjecucion);
            fel.programarEvento(new Salida(salida, avion,servidor));
        }
        else{
            //server ocupado
            servers.get(servidor).añadirACola(avion); // añado el avion a la cola de espera
            estadisticas.registrarTamañoCola(servers.get(servidor).getTamañoCola()); //registro el tamaño de la cola 
        }
        //programo el proximo arribo
        double tiempoProximoArribo= this.tiempoEjecucion + genArribo.generarTiempo(this.tiempoEjecucion);
        fel.programarEvento(new Arribo(tiempoProximoArribo, new Avion(avion.getId()+1, tiempoProximoArribo)));
        

}

    

}
