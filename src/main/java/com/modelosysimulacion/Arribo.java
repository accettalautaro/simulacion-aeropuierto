package com.modelosysimulacion;

public class Arribo extends Evento{
    private final Avion avion;

    public Arribo(double timepoEjecucion, Avion avion) {
        super(timepoEjecucion,1);
        this.avion = avion;
    }

    @Override
    public void procesar(Server server, Fel fel, Estadisticas estadisticas, GenerarTiempo genArribo, GenerarTiempo genSalida){
        
        //actualizo cant aviones  arribados
        estadisticas.registrarArribo();
        if(!server.estaOcupada()){
            // para calcular los intervalos de hora pico mod 1440
            // server desocupado
            server.ocupar();
            estadisticas.registrarTiempoEspera(0.0); // no hay tiempo de espera
            estadisticas.registrarTiempoOcio(server.getInicioUltimoOcio(), this.tiempoEjecucion); //registro el tiempo de ocio
            //programo la salida del avion
            double salida = this.tiempoEjecucion + genSalida.generarTiempo();
            fel.programarEvento(new Salida(salida, avion));
        }
        else{
            //server ocupado
            server.añadirACola(avion); // añado el avion a la cola de espera
            estadisticas.registrarTamañoCola(server.getTamañoCola()); //registro el tamaño de la cola 
        }
        //programo el proximo arribo
        double tiempoProximoArribo= this.tiempoEjecucion + genArribo.generarTiempo();
        fel.programarEvento(new Arribo(tiempoProximoArribo, new Avion(avion.getId()+1, tiempoProximoArribo)));
        

}

    @Override
    public void procesar1(Server server, Fel fel, Estadisticas estadisticas,Exponencial exponencial,Uniforme uniforme,Randomizer random ) {
        //actualizo cant aviones  arribados
        estadisticas.registrarArribo();
        if(!server.estaOcupada()){
            // para calcular los intervalos de hora pico mod 1440
            // server desocupado
            server.ocupar();
            estadisticas.registrarTiempoEspera(0.0); // no hay tiempo de espera
            estadisticas.registrarTiempoOcio(server.getInicioUltimoOcio(), this.tiempoEjecucion); //registro el tiempo de ocio
            //programo la salida del avion
            double salida = this.tiempoEjecucion + uniforme.getValue(random);
            fel.programarEvento(new Salida(salida, avion));
        }
        else{
            //server ocupado
            server.añadirACola(avion); // añado el avion a la cola de espera
            estadisticas.registrarTamañoCola(server.getTamañoCola()); //registro el tamaño de la cola 
        }
        //programo el proximo arribo
        if( (this.tiempoEjecucion%1440)/60>= 9 && (this.tiempoEjecucion%1440)/60<= 13){
            exponencial.setMedia(9);
        }
        else{
            exponencial.setMedia(15);
        }
        double tiempoProximoArribo= this.tiempoEjecucion + exponencial.getValue(random);
        fel.programarEvento(new Arribo(tiempoProximoArribo, new Avion(avion.getId()+1, tiempoProximoArribo)));
        
    }

}
