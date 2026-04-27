package com.modelosysimulacion.Criterios;

import java.util.ArrayList;

import com.modelosysimulacion.Server;

public class Seleccionar implements Criterio {
    private int tamMinCola;
    private int server;
    public Seleccionar() {
        this.server=0;
        this.tamMinCola = Integer.MAX_VALUE;
    }

    @Override
    public int seleccionar(ArrayList<Server> servidores) {
        int i=0;
        while (i<servidores.size()&& servidores.get(i).estaOcupada()==true) {
            if(servidores.get(i).getTamañoCola()<this.tamMinCola){
                this.server=i;
                this.tamMinCola=servidores.get(i).getTamañoCola();
            }
            i++;
        }
        if(i==servidores.size()){
            return this.server;
        }
        return i;
    }
    
}
