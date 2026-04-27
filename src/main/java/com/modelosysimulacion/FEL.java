package com.modelosysimulacion;

import java.util.Comparator;
import java.util.PriorityQueue;

import com.modelosysimulacion.eventos.Evento;

public class Fel {
    private final PriorityQueue<Evento> cola;

    public Fel() {
        this.cola= new PriorityQueue<>(Comparator.comparingDouble(Evento::getTiempo).thenComparingInt(Evento::getOrder));
    }


    public boolean estaVacia() {
        return cola.isEmpty();
    }

    
    public Evento extraerProximo() {
        return cola.poll();
    }

    
    public void programarEvento(Evento e) {
        cola.add(e);
    }

    
    public Evento verProximo() {
        return cola.peek();
    }
}
