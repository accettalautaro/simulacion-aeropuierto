package com.modelosysimulacion;

import java.util.ArrayList;
import java.util.List;

public class Estadisticas implements ColeccionarEstadisticas {
    private final List<Double> esperas = new ArrayList<>();
    private final List<Double> ocios= new ArrayList<>();
    private final List<Double> tiempoEnSistema= new ArrayList<>();
    private double ocioTotal =0.0;
    private int aeronaveArribada = 0;
    private int aeronaveAterrizada = 0;
    private int maxCola=0;
    private int minCola= Integer.MAX_VALUE;
    @Override
    public void registrarArribo() {
        aeronaveArribada += 1;
        
    }

    @Override
    public void registrarSalida() {
        aeronaveAterrizada += 1;
    }

    @Override
    public void imprimirRepeorte(double tiempoTotalSimulacion) {
        //Espera
        double maxEspera = esperas.stream().mapToDouble(v->v).max().orElse(0);
        double minEspera = esperas.stream().filter(v -> v > 0.0).mapToDouble(v->v).min().orElse(0);
        double mediaEspera = esperas.stream().mapToDouble(v->v).average().orElse(0);
        //Tiempo en Sistema
        double maxEnSistema= tiempoEnSistema.stream().mapToDouble(v->v).max().orElse(0);
        double minEnSistema =tiempoEnSistema.stream().filter(v -> v > 0.0).mapToDouble(v->v).min().orElse(0);
        double mediaEnSistema= tiempoEnSistema.stream().mapToDouble(v->v).average().orElse(0);
        //Ocio
        double maxOcio = ocios.stream().mapToDouble(v->v).max().orElse(0);
        double minOcio = ocios.stream().filter(v -> v > 0.0).mapToDouble(v->v).min().orElse(0);
        double porcentajeOcio = (ocioTotal/tiempoTotalSimulacion) * 100;
    
        System.out.printf("Tiempos en Sistema (min) -> Media: %.2f | Max: %.2f | Min: %.2f\n", mediaEnSistema, maxEnSistema, minEnSistema);
        System.out.printf("Tiempos de Espera (min) -> Media: %.2f | Max: %.2f | Min: %.2f\n", mediaEspera, maxEspera, minEspera);
        System.out.printf("Ociosidad (min) -> Total: %.2f (%.2f%%) | Max: %.2f | Min: %.2f\n", ocioTotal, porcentajeOcio, maxOcio, minOcio);
        System.out.println("Arribos de Aeronaves : "+ aeronaveArribada);
        System.out.println("Aterrizajes de Aeronaves : "+ aeronaveAterrizada);
        System.out.println("Tamaño Maximo de Cola : " + maxCola);
        System.out.println("Tamaño Minimo de Cola : "+minCola);

    }
    
    @Override
    public void registrarTiempoEspera(double tiempo) {
        esperas.add(tiempo);
        
    }
    
    @Override
    public void registrarTiempoEnSistema(double tiempo) {
        tiempoEnSistema.add(tiempo);
        
    }

    @Override
    public void registrarTiempoOcio(double tiempoInicio, double tiempoFin) {
        double delta = tiempoFin-tiempoInicio;
        if(delta>0){
            ocios.add(delta);
            ocioTotal += delta;
        }
        
    }

    @Override
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
    
    
}
