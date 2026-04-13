package com.modelosysimulacion;

public class Arribo extends Evento{
    private final Avion avion;

    public Arribo(double timepoEjecucion, Avion avion) {
        super(timepoEjecucion,1);
        this.avion = avion;
    }

    @Override
    public void procesar(EstadoPista estado, FEL fel, ColeccionarEstadisticas estadisticas, GeneradorTiempos genArribo, GeneradorTiempos genSalida) {
        //actualizo cant aviones  arribados
        estadisticas.registrarArribo();
        if(!estado.estaOcupada()){
            // server desocupado
            estado.ocupar();
            estadisticas.registrarTiempoEspera(0.0); // no hay tiempo de espera
            estadisticas.registrarTiempoOcio(estado.getInicioUltimoOcio(), this.tiempoEjecucion); //registro el tiempo de ocio
            //programo la salida del avion
            double salida = this.tiempoEjecucion + genSalida.generarTiempo();
            fel.programarEvento(new Salida(salida, avion));
        }
        else{
            //server ocupado
            estado.añadirACola(avion); // añado el avion a la cola de espera
            estadisticas.registrarTamañoCola(estado.getTamañoCola()); //registro el tamaño de la cola 
        }
        //programo el proximo arribo
        double tiempoProximoArribo= this.tiempoEjecucion + genArribo.generarTiempo();
        fel.programarEvento(new Arribo(tiempoProximoArribo, new Avion(avion.getId()+1, tiempoProximoArribo)));
        
        

}
}
