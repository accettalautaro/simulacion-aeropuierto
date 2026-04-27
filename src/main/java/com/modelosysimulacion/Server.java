package com.modelosysimulacion;

import java.util.LinkedList;
import java.util.Queue;
import com.modelosysimulacion.distribuciones.Distribucion;
import com.modelosysimulacion.distribuciones.Normal;
import com.modelosysimulacion.random.Randomizer;
import com.modelosysimulacion.random.Randomizer1;

public class Server {
    private boolean ocupada;
    private final Queue<Avion> colaEspera = new LinkedList<>();
    private double desgaste;
    
    public Server() {
        this.ocupada = false;
        this.desgaste = 3000;
    }
    
    public boolean estaOcupada(){return ocupada;}
    public void ocupar(){this.ocupada=true;}
    public void liberarPista(){
        this.ocupada=false;
        
    }
    public void añadirACola(Avion avion){colaEspera.add(avion); }
    public Avion quitarDCola(){return colaEspera.poll();}
    public boolean hayCola(){return !colaEspera.isEmpty();}
    
    public int getTamañoCola(){
        return colaEspera.size();
    }

    public void actualizarDesgaste(){
        Distribucion normal = new Normal( 5, 1);
        Randomizer  random = new Randomizer1();
        this.desgaste= this.desgaste - normal.getValue(random);
    }
    public double getDesgaste() {
        return desgaste;
    }

}
