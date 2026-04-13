package com.modelosysimulacion;

public class Salida extends Evento{
    private Avion avion;
    public Salida(double timepoEjecucion,Avion avion) {
        super(timepoEjecucion,0);
        this.avion=avion;
    }

    @Override
    public void procesar(EstadoPista estado, FEL fel, ColeccionarEstadisticas estadisticas, GeneradorTiempos genArribo,GeneradorTiempos genSalida) {
        
        estadisticas.registrarSalida();//actualizo cant aviones aterrizados
        //registro timepo en sistema
        double tiempoEnSistema= this.getTiempo() - avion.getTiempoArribo(); 
        estadisticas.registrarTiempoEnSistema(tiempoEnSistema);

        if(!estado.hayCola()){
            //si no hay cola, libero la pista
            estado.liberarPista(this.tiempoEjecucion);
        }
        else{
            //si hay cola, proceso el proximo avion
            Avion proximAvion = estado.quitarDCola();
            estadisticas.registrarTamañoCola(estado.getTamañoCola()); // registro el tamaño de la cola
            //registro el tiempo de espera
            double tiempoEspera = this.tiempoEjecucion-proximAvion.getTiempoArribo();
            estadisticas.registrarTiempoEspera(tiempoEspera);
            //programo la proxima salida
            double tiempoSalida= this.tiempoEjecucion+ genSalida.generarTiempo();
            fel.programarEvento(new Salida(tiempoSalida,proximAvion));
        }
    }
    

}
