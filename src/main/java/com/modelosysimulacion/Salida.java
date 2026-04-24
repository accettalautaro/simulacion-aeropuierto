package com.modelosysimulacion;

public class Salida extends Evento{
    private Avion avion;
    public Salida(double timepoEjecucion,Avion avion) {
        super(timepoEjecucion,0);
        this.avion=avion;
    }

    @Override
    public void procesar(Server server, Fel fel, Estadisticas estadisticas, GenerarTiempo genArribo,GenerarTiempo genSalida) {
        
        estadisticas.registrarSalida();//actualizo cant aviones aterrizados
        //registro timepo en sistema
        double tiempoEnSistema= this.getTiempo() - avion.getTiempoArribo(); 
        estadisticas.registrarTiempoEnSistema(tiempoEnSistema);

        if(!server.hayCola()){
            //si no hay cola, libero la pista
            server.liberarPista(this.tiempoEjecucion);
        }
        else{
            //si hay cola, proceso el proximo avion
            Avion proximAvion = server.quitarDCola();
            estadisticas.registrarTamañoCola(server.getTamañoCola()); // registro el tamaño de la cola
            //registro el tiempo de espera
            double tiempoEspera = this.tiempoEjecucion-proximAvion.getTiempoArribo();
            estadisticas.registrarTiempoEspera(tiempoEspera);
            //programo la proxima salida
            double tiempoSalida= this.tiempoEjecucion+ genSalida.generarTiempo();
            fel.programarEvento(new Salida(tiempoSalida,proximAvion));
        }
    }

    @Override
    public void procesar1(Server server, Fel fel, Estadisticas estadisticas, Exponencial exponencial,Uniforme uniforme, Randomizer random) {
        
            estadisticas.registrarSalida();//actualizo cant aviones aterrizados
        //registro timepo en sistema
        double tiempoEnSistema= this.getTiempo() - avion.getTiempoArribo(); 
        estadisticas.registrarTiempoEnSistema(tiempoEnSistema);

        if(!server.hayCola()){
            //si no hay cola, libero la pista
            server.liberarPista(this.tiempoEjecucion);
        }
        else{
            //si hay cola, proceso el proximo avion
            Avion proximAvion = server.quitarDCola();
            estadisticas.registrarTamañoCola(server.getTamañoCola()); // registro el tamaño de la cola
            //registro el tiempo de espera
            double tiempoEspera = this.tiempoEjecucion-proximAvion.getTiempoArribo();
            estadisticas.registrarTiempoEspera(tiempoEspera);
            //actualizo desgaste de pista
            server.actualizarDesgaste();
            //programo la proxima salida
            double tiempoSalida= this.tiempoEjecucion+ uniforme.getValue(random);
            fel.programarEvento(new Salida(tiempoSalida,proximAvion));
        }
        
    }
    

}
