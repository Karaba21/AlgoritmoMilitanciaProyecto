package org.example;

/**
 * Representa un camino entre dos ciudades, con un tiempo de viaje y un clima asociado.
 */
public class Camino implements ICamino {

    private Ciudad ciudadOrigen;

    private Ciudad ciudadDestino;

    private int tiempo;

    private String clima; //soleado, lluvioso o niebla

    /**
     * Crea una nueva instancia de Camino.
     *
     * @param ciudadOrigen la ciudad de origen
     * @param ciudadDestino la ciudad de destino
     * @param tiempo el tiempo de viaje en condiciones climáticas normales
     * @param clima el clima del camino (soleado, lluvioso, niebla)
     */
    public Camino(Ciudad ciudadOrigen, Ciudad ciudadDestino, int tiempo, String clima) {
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.tiempo = tiempo;
        this.clima = clima;
    }


    public Ciudad getCiudadOrigen() {
        return ciudadOrigen;
    }

    @Override
    public Ciudad getCiudadDestino() {
        return ciudadDestino;
    }

    @Override
    public int getTiempo() {
        return tiempo;
    }

    @Override
    public String getClima() {
        return clima;
    }

    @Override
    public int tiempoConClima(){
        switch (clima){
            case "soleado":
                return tiempo;
            case "lluvioso":
                return tiempo*2;
            case "niebla":
                return Integer.MAX_VALUE; //imposibilita el transito
            default:
                throw new IllegalArgumentException("El clima es desconocido");
        }
    }

    @Override
    public double costoPonderado(){
        double tiempoConClima = tiempoConClima();
        double porcentajeApoyo = ciudadDestino.getPorcentajeApoyo() / 100;
        return tiempoConClima / (1 + porcentajeApoyo);
    }

    @Override
    public String toString() {
        return ciudadDestino.getNombre() + " (Tiempo con clima: " + tiempoConClima() + ", Costo ponderado: " + costoPonderado() + ")";
    }
}
