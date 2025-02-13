package org.example;

/**
 * Representa una ciudad con un nombre y un porcentaje de apoyo.
 */
public class Ciudad implements ICiudad {

    private String nombre;

    private double porcentajeApoyo;

    /**
     * Crea una nueva instancia de Ciudad.
     *
     * @param nombre el nombre de la ciudad
     * @param porcentajeApoyo el porcentaje de apoyo en la ciudad
     */
    public Ciudad(String nombre, double porcentajeApoyo) {
        this.nombre = nombre;
        this.porcentajeApoyo = porcentajeApoyo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPorcentajeApoyo() {
        return porcentajeApoyo;
    }

    /**
     * Devuelve una representación en cadena de la ciudad.
     *
     * @return una cadena que representa la ciudad y su porcentaje de apoyo
     */
    @Override
    public String toString() {
        return nombre + " (" + porcentajeApoyo + "%)";
    }
}
