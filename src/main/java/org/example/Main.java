package org.example;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        //CARGAR GRAFO MEDIANTE ARCHIVOS TXT

        // Leer y agregar caminos desde el archivo de caminos
        String[] vertices = ManejadorArchivosGenerico.leerArchivo("src/main/java/org/example/ciudades.txt", false);
        for (String linea : vertices) {
            String[] datos = linea.split(",");
            String nombreCiudad = datos[0];
            double porcentajeApoyo = Double.parseDouble(datos[1]);
            Ciudad ciudad = new Ciudad(nombreCiudad, porcentajeApoyo);
            grafo.agregarCiudad(ciudad);
        }

        // Leer y agregar caminos desde el archivo de caminos
        String[] caminos = ManejadorArchivosGenerico.leerArchivo("src/main/java/org/example/caminos.txt", false);
        for (String linea : caminos) {
            String[] datos = linea.split(",");
            String nombreOrigen = datos[0];
            String nombreDestino = datos[1];
            int tiempo = Integer.parseInt(datos[2]);
            String clima = datos[3];

            Ciudad ciudadOrigen = grafo.buscarCiudad(nombreOrigen);
            Ciudad ciudadDestino = grafo.buscarCiudad(nombreDestino);

            if (ciudadOrigen != null && ciudadDestino != null) {
                grafo.agregarCamino(ciudadOrigen, ciudadDestino, tiempo, clima);
            }
        }

        // Ejemplo de uso del método ciudadesMasFavorables
        Ciudad ciudadInicial = grafo.buscarCiudad("Pocitos");

        if (ciudadInicial != null) {
            List<Ciudad> ciudadesFavorables2 = grafo.ciudadesMasFavorables(ciudadInicial);

            System.out.println("Ciudades más favorables para ir a militar desde " + ciudadInicial.getNombre() + ":");
            if (ciudadesFavorables2.isEmpty()) {
                System.out.println("No hay ciudades favorables para ir a militar desde esta ciudad.");
            } else {
                for (int i = 0; i < ciudadesFavorables2.size(); i++) {
                    Ciudad ciudad = ciudadesFavorables2.get(i);
                    if (i == 0) {
                        System.out.println("1. La ciudad más favorable es " + ciudad.getNombre() + " con un porcentaje de apoyo de " + ciudad.getPorcentajeApoyo() + "%.");
                    } else {
                        System.out.println((i + 1) + ". Otra ciudad favorable es " + ciudad.getNombre() + " con un porcentaje de apoyo de " + ciudad.getPorcentajeApoyo() + "%.");
                    }
                }
            }
        } else {
            System.out.println("La ciudad inicial no existe en el grafo.");
        }


        System.out.println("");
        System.out.println("");
        ArbolDeDecision arbol = new ArbolDeDecision("");
        ArbolDeDecision raiz = arbol.armarArbolDeDecision();
        arbol.imprimirArbol(raiz, "");
    }
}
