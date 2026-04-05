package com.modelosysimulacion;
public interface ColeccionarEstadisticas {
    void registrarTiempoEspera(double tiempo);
    void registrarArribo();
    void registrarSalida();
    void registrarTiempoOcio(double tiempoInicio, double tiempoFin);
    void imprimirRepeorte(double tiempoTotalSimulacion );
    void registrarTamañoCola(int tamaño);
    void registrarTiempoEnSistema(double tiempo);
}
