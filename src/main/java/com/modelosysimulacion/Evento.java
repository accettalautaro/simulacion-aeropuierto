package com.modelosysimulacion;

abstract class Evento {
    protected final double tiempoEjecucion;
    private int order;
    public Evento(double timepoEjecucion,int order){
        this.tiempoEjecucion = timepoEjecucion;
        this.order=order;
    }
    public int getOrder(){return order;}
    public double getTiempo(){return tiempoEjecucion;}
    public abstract void procesar (EstadoPista estado,FEL fel,ColeccionarEstadisticas estadisticas,GeneradorTiempos genArribo, GeneradorTiempos genSalida);

}
