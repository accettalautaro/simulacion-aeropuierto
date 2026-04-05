package com.modelosysimulacion;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ColaPrioridadFEL implements FEL {
    private final PriorityQueue<Evento> cola = new PriorityQueue<>(Comparator.comparingDouble(Evento::getTiempo).thenComparingInt(Evento::getOrder));

    @Override
    public boolean estaVacia() {
        return cola.isEmpty();
    }

    @Override
    public Evento extraerProximo() {
        return cola.poll();
    }

    @Override
    public void programarEvento(Evento e) {
        cola.add(e);
    }

    @Override
    public Evento verProximo() {
        return cola.peek();
    }
    
}
