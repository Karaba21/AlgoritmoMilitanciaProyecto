package org.example;
import java.util.ArrayList;
import java.util.*;

/**
 * La clase {@code Grafo} representa un grafo dirigido donde los vértices son instancias de {@code Ciudad}
 * y las aristas son instancias de {@code Camino}. Este grafo permite agregar ciudades y caminos,
 * buscar ciudades por nombre, y determinar las ciudades más favorables para hacer campaña política
 * basándose en el costo de los caminos y las condiciones climáticas.
 */
public class Grafo implements IGrafo {
    // Mapa de adyacencias que asocia cada ciudad con una lista de caminos.
    private Map<Ciudad , List<Camino>> adyacencias;

    // Mapa que asocia el nombre de una ciudad con su instancia correspondiente.
    private Map<String, Ciudad> mapaCiudades;

    /**
     * Crea una nueva instancia de {@code Grafo} inicializando los mapas de adyacencias y ciudades.
     */
    public Grafo() {
        adyacencias = new HashMap<>();
        mapaCiudades = new HashMap<>();
    }

    @Override
    public void agregarCiudad (Ciudad ciudad) {
        adyacencias.putIfAbsent(ciudad, new ArrayList<>());
        mapaCiudades.put(ciudad.getNombre(), ciudad);
    }

    @Override
    public void agregarCamino (Ciudad origen, Ciudad destino, int tiempo, String clima) {
        Camino camino = new Camino(origen, destino, tiempo, clima);
        adyacencias.get(origen).add(camino);
    }

    @Override
    public List<Camino> getCaminosDesde(Ciudad ciudad) {
        return adyacencias.getOrDefault(ciudad, Collections.emptyList());
    }

    @Override
    public List<Ciudad> ciudadesMasFavorables(Ciudad ciudadActual) {
        if (ciudadActual == null || !adyacencias.containsKey(ciudadActual)) {
            throw new IllegalArgumentException("La ciudad no es válida o no existe en el grafo.");
        }

        List<Camino> caminos = getCaminosDesde(ciudadActual);
        caminos.sort(Comparator.comparingDouble(Camino::costoPonderado));
        List<Ciudad> ciudadesFavorables = new ArrayList<>();
        for (Camino camino : caminos) {
            if (camino.tiempoConClima() == Integer.MAX_VALUE) continue; // Omitir caminos imposibles (niebla)
            ciudadesFavorables.add(camino.getCiudadDestino());
        }
        return ciudadesFavorables;
    }

    @Override
    public Ciudad buscarCiudad(String nombre) {
        return mapaCiudades.get(nombre);
    }

}
