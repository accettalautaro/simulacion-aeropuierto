package com.modelosysimulacion.eventos;

import java.util.ArrayList;

import com.modelosysimulacion.Estadisticas;
import com.modelosysimulacion.Fel;
import com.modelosysimulacion.Server;
import com.modelosysimulacion.generadores.GenerarTiempo;

public abstract class Evento {
    protected final double tiempoEjecucion;
    private int order;
    public Evento(double timepoEjecucion,int order){
        this.tiempoEjecucion = timepoEjecucion;
        this.order=order;
    }
    public int getOrder(){return order;}
    public double getTiempo(){return tiempoEjecucion;}
    public abstract void procesar (ArrayList<Server> servers,Fel fel,Estadisticas estadisticas,GenerarTiempo genArribo, GenerarTiempo genSalida);

}
