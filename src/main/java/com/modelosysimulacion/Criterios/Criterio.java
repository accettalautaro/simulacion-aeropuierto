package com.modelosysimulacion.Criterios;

import java.util.ArrayList;

import com.modelosysimulacion.Server;

public interface Criterio {
    public int seleccionar(ArrayList<Server> servidores);
}
