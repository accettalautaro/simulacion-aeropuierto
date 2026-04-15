package com.modelosysimulacion;

import java.util.LinkedList;
import java.util.Queue;

public class EstadoPista {
    private boolean ocupada = false;
    private final Queue<Avion> colaEspera = new LinkedList<>();
    private double inicioUltimoOcio=0.0;
    private double desgaste= 3000;
    public boolean estaOcupada(){return ocupada;}
    public void ocupar(){this.ocupada=true;}
    public void liberarPista(double reloj){
        this.ocupada=false;
        this.inicioUltimoOcio=reloj;
    }
    public void añadirACola(Avion avion){colaEspera.add(avion); }
    public Avion quitarDCola(){return colaEspera.poll();}
    public boolean hayCola(){return !colaEspera.isEmpty();}
    public double getInicioUltimoOcio(){return inicioUltimoOcio;}
    public int getTamañoCola(){
        return colaEspera.size();
    }
    public void actualizarDesgaste(){
        Distribucion normal = new Normal(35, 5, 1);
        Randomizer  random = new Randomizer1(null);
        this.desgaste= this.desgaste - normal.getValue(random);
    }
}
