package org.example;

import java.util.List;

public interface IGrafo {

    /**
     * Agrega una nueva ciudad al grafo. Si la ciudad ya existe, no se realiza ninguna acción.
     *
     * @param ciudad La ciudad a agregar al grafo.
     * @throws NullPointerException Si la ciudad es {@code null}.
     */
    void agregarCiudad(Ciudad ciudad);

    /**
     * Busca una ciudad en el grafo por su nombre.
     *
     * @param nombre El nombre de la ciudad que se desea buscar.
     * @return La ciudad correspondiente al nombre, o {@code null} si no existe una ciudad con ese nombre.
     */
    Ciudad buscarCiudad(String nombre);

    /**
     * Agrega un nuevo camino entre dos ciudades en el grafo. Si las ciudades no existen en el grafo, se lanzará una excepción.
     *
     * @param origen La ciudad de origen del camino.
     * @param destino La ciudad de destino del camino.
     * @param tiempo El tiempo necesario para recorrer el camino.
     * @param clima El clima que afecta el tiempo de viaje.
     * @throws IllegalArgumentException Si alguna de las ciudades no existe en el grafo.
     * @throws NullPointerException Si {@code origen}, {@code destino} o {@code clima} son {@code null}.
     */
    void agregarCamino(Ciudad origen, Ciudad destino, int tiempo, String clima);

    /**
     * Determina las ciudades más favorables para hacer campaña política desde una ciudad dada,
     * considerando el costo de los caminos y las condiciones climáticas.
     *
     * @param ciudadActual La ciudad desde la cual se desea determinar las ciudades más favorables.
     * @return Una lista de ciudades ordenadas por el costo ponderado de los caminos, excluyendo los caminos imposibles.
     * @throws IllegalArgumentException Si {@code ciudadActual} es {@code null} o no existe en el grafo.
     */
    List<Ciudad> ciudadesMasFavorables(Ciudad ciudadActual);

    /**
     * Obtiene la lista de caminos salientes desde una ciudad dada.
     *
     * @param ciudad La ciudad desde la cual obtener los caminos.
     * @return Una lista de caminos desde la ciudad dada. Devuelve una lista vacía si la ciudad no tiene caminos salientes.
     */
    List<Camino> getCaminosDesde(Ciudad ciudad);

}
