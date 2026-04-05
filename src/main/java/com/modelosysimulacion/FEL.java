package com.modelosysimulacion;

public interface FEL {
    void programarEvento(Evento e);
    Evento extraerProximo();
    boolean estaVacia();
    Evento verProximo();
}
