package com.modelosysimulacion;

public class Arribo extends Evento{
    private final Avion avion;

    public Arribo(double timepoEjecucion, Avion avion) {
        super(timepoEjecucion,1);
        this.avion = avion;
    }

    @Override
    public void procesar(EstadoPista estado, FEL fel, ColeccionarEstadisticas estadisticas, GeneradorTiempos genArribo, GeneradorTiempos genSalida) {
        estadisticas.registrarArribo();
        if(!estado.estaOcupada()){
            estado.ocupar();
            estadisticas.registrarTiempoEspera(0.0);
            estadisticas.registrarTiempoOcio(estado.getInicioUltimoOcio(), this.tiempoEjecucion);
            double salida = this.tiempoEjecucion + genSalida.generarTiempo();
            fel.programarEvento(new Salida(salida, avion));
        }
        else{
            estado.añadirACola(avion);
            estadisticas.registrarTamañoCola(estado.getTamañoCola());
        }
        double tiempoProximoArribo= this.tiempoEjecucion + genArribo.generarTiempo();
        fel.programarEvento(new Arribo(tiempoProximoArribo, new Avion(avion.getId()+1, tiempoProximoArribo)));
        
        

}
}
