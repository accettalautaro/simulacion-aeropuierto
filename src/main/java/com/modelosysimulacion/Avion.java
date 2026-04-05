package com.modelosysimulacion;

public class Avion {
    private final int id;
    private final double tiempoArribo;

    public Avion(int id, double tiempoArribo){
        this.id=id;
        this.tiempoArribo=tiempoArribo;
    }
    public int getId(){return id;}
    public double getTiempoArribo(){return tiempoArribo;}
}
