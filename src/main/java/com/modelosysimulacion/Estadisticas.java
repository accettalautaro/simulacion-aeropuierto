package com.modelosysimulacion;

import java.util.ArrayList;
import java.util.List;

public class Estadisticas  {
    private final List<Double> esperas;
    private final List<Double> tiempoEnSistema;
    private List<Double> ocioTotal;
    private List<Double> inicioOcio;
    private double maxOcio;
    private double minOcio;
    private int aeronaveArribada ;
    private int aeronaveAterrizada ;
    private int maxCola;
    private int minCola;
    
    public Estadisticas(int cantServers) {
        this.esperas=new ArrayList<>();
        this.tiempoEnSistema=new ArrayList<>();
        this.ocioTotal = new ArrayList<>();
        this.inicioOcio = new ArrayList<>();
        for (int i = 0; i < cantServers; i++) {
            this.ocioTotal.add(0.0);
            this.inicioOcio.add(0.0);
        }
        this.maxOcio=0;
        this.minOcio= Double.MAX_VALUE;
        this.aeronaveArribada = 0;
        this.aeronaveAterrizada = 0;
        this.maxCola = 0;
        this.minCola = Integer.MAX_VALUE;
    }

    
    public void setInicioOcio(Double tiempo,int ServerID) {
        this.inicioOcio.set(ServerID, tiempo);
    }


    public void registrarArribo() {
        aeronaveArribada += 1;
        
    }

    
    public void registrarSalida() {
        aeronaveAterrizada += 1;
    }

    
    public void imprimirRepeorte(double tiempoTotalSimulacion,ArrayList<Server> servers) {
        
        //Espera
        double maxEspera = esperas.stream().mapToDouble(v->v).max().orElse(0);
        double minEspera = esperas.stream().filter(v -> v > 0.0).mapToDouble(v->v).min().orElse(0);
        double mediaEspera = esperas.stream().mapToDouble(v->v).average().orElse(0);
        // ---------------------------------
        //Tiempo en Sistema
        double maxEnSistema= tiempoEnSistema.stream().mapToDouble(v->v).max().orElse(0);
        double minEnSistema =tiempoEnSistema.stream().filter(v -> v > 0.0).mapToDouble(v->v).min().orElse(0);
        double mediaEnSistema= tiempoEnSistema.stream().mapToDouble(v->v).average().orElse(0);
        //Ocio
        for(int i=0;i<ocioTotal.size();i++){
        System.out.println("Server "+(i+1));
        System.out.printf("Desgaste: %.2f\n",servers.get(i).getDesgaste());
        System.out.printf("Ociosidad (min) -> Total: %.2f (%.2f%%)\n", this.ocioTotal.get(i), (this.ocioTotal.get(i)/tiempoTotalSimulacion) * 100 );
        }
        System.out.printf("Ocio\nMax: %.2f \n",this.maxOcio);
        System.out.printf("Min: %.2f\n",this.minOcio);
        System.out.printf("Tiempos en Sistema (min) -> Media: %.2f | Max: %.2f | Min: %.2f\n", mediaEnSistema, maxEnSistema, minEnSistema);
        System.out.printf("Tiempos de Espera (min) -> Media: %.2f | Max: %.2f | Min: %.2f\n", mediaEspera, maxEspera,minEspera);
        System.out.println("Arribos de Aeronaves : "+ this.aeronaveArribada);
        System.out.println("Aterrizajes de Aeronaves : "+ this.aeronaveAterrizada);
        System.out.println("Tamaño Maximo de Cola : " + this.maxCola);
        System.out.println("Tamaño Minimo de Cola : "+ this.minCola);

    }
    
    
    public void registrarTiempoEspera(double tiempo) {
        esperas.add(tiempo);
    }
    
    
    public void registrarTiempoEnSistema(double tiempo) {
        tiempoEnSistema.add(tiempo);
        
    }


    public void registrarTiempoOcio(int serverID, double tiempoFin) {
        double delta = tiempoFin-inicioOcio.get(serverID);
        if(delta>0){
            ocioTotal.set(serverID, ocioTotal.get(serverID)+delta);
            if(delta>this.maxOcio){
                this.maxOcio=delta;
            }
            if(delta<this.minOcio){
                this.minOcio=delta;
            }
        }
        
    }


    public void registrarTamañoCola(int tamaño) {
        if(tamaño> maxCola){
            maxCola=tamaño;
        }
        if(tamaño != 0){
            if(tamaño<minCola){
                minCola= tamaño;
            }
        }
    }
    public void verificarOcio(List<Server> servers, double duracionSimulacion){
        for(int i = 0; i< servers.size();i++){
            if(!servers.get(i).estaOcupada()){
                registrarTiempoOcio(i,duracionSimulacion);
            }
        }    
    }


    
    
    
}
