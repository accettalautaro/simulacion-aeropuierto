package com.modelosysimulacion;

import java.util.ArrayList;


import com.modelosysimulacion.eventos.Arribo;
import com.modelosysimulacion.eventos.Evento;
import com.modelosysimulacion.generadores.GenerarArriboT1;
import com.modelosysimulacion.generadores.GenerarSalidaT2;
import com.modelosysimulacion.generadores.GenerarTiempo;
import com.modelosysimulacion.random.Randomizer1;

public class Simulacion {
    private Fel fel;
    private Estadisticas estadisticas;
    private GenerarTiempo genArribo;
    private GenerarTiempo genSalida;
    private final double duracionSimulacion;
    private ArrayList<Server> servers;
    
    private Simulacion(Builder builder) {
        this.fel= builder.fel;
        this.estadisticas= new Estadisticas(builder.cantServer);
        this.genArribo= builder.genArribo;
        this.genSalida= builder.genSalida;
        this.duracionSimulacion= builder.tiempoSimulacion;
        this.servers= builder.servers;
    }
    public void ejecutar(){
        //inicio de simulacion
        Avion primerAvion = new Avion(1, 0);
        fel.programarEvento(new Arribo(0, primerAvion));
        double clock = fel.verProximo().getTiempo();
        //bucle de simulacion
        while (!fel.estaVacia()&& clock  <= this.duracionSimulacion) {
            Evento eventoActual = fel.extraerProximo();
            clock = eventoActual.getTiempo();
            eventoActual.procesar(this.servers, this.fel, this.estadisticas, this.genArribo, this.genSalida);
            
        }
        estadisticas.verificarOcio(this.servers, this.duracionSimulacion);
    }
    
    public void reporte(){
        estadisticas.imprimirRepeorte(this.duracionSimulacion, this.servers);
    }

    


public static class Builder {
    private Fel fel= new Fel();
    private ArrayList<Server> servers = new ArrayList<>();
    private GenerarTiempo genArribo;
    private GenerarTiempo genSalida;
    private Integer cantServer;
    private Double tiempoSimulacion;

    public Builder tiempoSimulacion(double tiempoSimulacion){
        this.tiempoSimulacion= tiempoSimulacion;
        return this;
    }
    public Builder servers(int cantServers){
        this.cantServer=cantServers;
        this.servers.clear();
        for (int i = 0; i < cantServers; i++) {
                this.servers.add(new Server());
            }
        return this;
    }
    public Builder tiempoArribos(GenerarTiempo genArribo){
        this.genArribo=genArribo;
        return this;
    }
    public Builder tiempoSalidas(GenerarTiempo genSalida){
        this.genSalida=genSalida;
        return this;
    }
    public Simulacion build(){
        if( tiempoSimulacion == null || tiempoSimulacion<0){
            tiempoSimulacion=40320.0;
        }
        if( cantServer==null|| cantServer<1){
            cantServer=1;
            this.servers.add(new Server());
        }
        if(genArribo==null){
            genArribo= new GenerarArriboT1(new Randomizer1());
        }
        if(genSalida==null){
            genSalida=new GenerarSalidaT2(new Randomizer1());
        }
        return new Simulacion(this);
    }
    
}
}