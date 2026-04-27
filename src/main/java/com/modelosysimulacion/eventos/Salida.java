package com.modelosysimulacion.eventos;

import java.util.ArrayList;

import com.modelosysimulacion.Avion;
import com.modelosysimulacion.Estadisticas;
import com.modelosysimulacion.Fel;
import com.modelosysimulacion.Server;
import com.modelosysimulacion.generadores.GenerarTiempo;

public class Salida extends Evento{
    private Avion avion;
    private int serverID;
    public Salida(double timepoEjecucion,Avion avion,int serverID) {
        super(timepoEjecucion,0);
        this.avion=avion;
        this.serverID = serverID;
    }

    @Override
    public void procesar(ArrayList<Server> servers, Fel fel, Estadisticas estadisticas, GenerarTiempo genArribo,GenerarTiempo genSalida) {
        
        estadisticas.registrarSalida();//actualizo cant aviones aterrizados
        //registro timepo en sistema
        double tiempoEnSistema= this.getTiempo() - avion.getTiempoArribo(); 
        estadisticas.registrarTiempoEnSistema(tiempoEnSistema);

        if(!servers.get(this.serverID).hayCola()){
            //si no hay cola, libero la pista
            servers.get(this.serverID).liberarPista();
            estadisticas.setInicioOcio(this.tiempoEjecucion, serverID);
        }
        else{
            //si hay cola, proceso el proximo avion
            Avion proximAvion = servers.get(this.serverID).quitarDCola();
            estadisticas.registrarTamañoCola(servers.get(this.serverID).getTamañoCola()); // registro el tamaño de la cola
            //registro el tiempo de espera
            double tiempoEspera = this.tiempoEjecucion-proximAvion.getTiempoArribo();
            estadisticas.registrarTiempoEspera(tiempoEspera);
            //programo la proxima salida
            double tiempoSalida= this.tiempoEjecucion+ genSalida.generarTiempo(this.tiempoEjecucion);
            fel.programarEvento(new Salida(tiempoSalida,proximAvion,this.serverID));
            
        }
        servers.get(this.serverID).actualizarDesgaste();
    }

}
