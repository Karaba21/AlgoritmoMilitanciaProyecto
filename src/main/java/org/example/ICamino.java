package org.example;

public interface ICamino {

    /**
     * Obtiene la ciudad de origen del camino.
     *
     * @return la ciudad de origen
     */
    Ciudad getCiudadOrigen();

    /**
     * Obtiene la ciudad de destino del camino.
     *
     * @return la ciudad de destino
     */
    Ciudad getCiudadDestino();

    /**
     * Obtiene el tiempo de viaje en condiciones climáticas normales.
     *
     * @return el tiempo de viaje
     */
    int getTiempo();

    /**
     * Obtiene el clima del camino.
     *
     * @return el clima del camino
     */
    String getClima();

    /**
     * Calcula el tiempo de viaje ajustado según el clima.
     *
     * @return el tiempo de viaje ajustado
     */
    int tiempoConClima();

    /**
     * Calcula el costo ponderado del camino, considerando el tiempo ajustado por el clima y el porcentaje de apoyo en la ciudad de destino.
     *
     * @return el costo ponderado del camino
     */
    double costoPonderado();

}
