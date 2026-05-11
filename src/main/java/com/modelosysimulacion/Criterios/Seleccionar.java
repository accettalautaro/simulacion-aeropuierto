package com.modelosysimulacion.Criterios;

import java.util.ArrayList;

import com.modelosysimulacion.Server;

public class Seleccionar implements Criterio {  
    @Override
    public int seleccionar(ArrayList<Server> servidores) {
        for (int i = 0; i < servidores.size(); i++) {
            if (!servidores.get(i).estaOcupada()) {
                return i; // Retorna la primera pista libre que encuentre
            }
        }
        int serverElegido = 0;
        int tamMinCola = servidores.get(0).getTamañoCola(); //tomo el primero
        for (int i = 1; i < servidores.size(); i++) {
            if (servidores.get(i).getTamañoCola() < tamMinCola) {
                serverElegido = i;
                tamMinCola = servidores.get(i).getTamañoCola();
            }
        }
        return serverElegido;
    }
    
}
