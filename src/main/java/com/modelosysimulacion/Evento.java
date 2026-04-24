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
    public abstract void procesar (Server estado,Fel fel,Estadisticas estadisticas,GenerarTiempo genArribo, GenerarTiempo genSalida);
    public abstract void procesar1(Server estado, Fel fel, Estadisticas estadisticas, Exponencial exponencial , Uniforme uniforme, Randomizer random);

}
