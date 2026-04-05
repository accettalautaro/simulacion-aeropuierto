package com.modelosysimulacion;

public class Salida extends Evento{
    private Avion avion;
    public Salida(double timepoEjecucion,Avion avion) {
        super(timepoEjecucion,0);
        this.avion=avion;
    }

    @Override
    public void procesar(EstadoPista estado, FEL fel, ColeccionarEstadisticas estadisticas, GeneradorTiempos genArribo,GeneradorTiempos genSalida) {
        estadisticas.registrarSalida();
        double tiempoEnSistema= this.getTiempo() - avion.getTiempoArribo();
        estadisticas.registrarTiempoEnSistema(tiempoEnSistema);
        if(!estado.hayCola()){
            estado.liberarPista(this.tiempoEjecucion);
        }
        else{
            Avion proximAvion = estado.quitarDCola();
            estadisticas.registrarTamañoCola(estado.getTamañoCola());
            double tiempoEspera = this.tiempoEjecucion-proximAvion.getTiempoArribo();
            estadisticas.registrarTiempoEspera(tiempoEspera);
            
            double tiempoSalida= this.tiempoEjecucion+ genSalida.generarTiempo();
            fel.programarEvento(new Salida(tiempoSalida,proximAvion));
        }
    }
    

}
