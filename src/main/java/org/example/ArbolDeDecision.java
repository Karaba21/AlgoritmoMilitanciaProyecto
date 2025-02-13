package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase representa un árbol de decisión utilizado para determinar la mejor ciudad a visitar para una campaña política,
 * basándose en el porcentaje de apoyo, el costo del viaje y el clima.
 *
 * El árbol de decisión tiene una estructura jerárquica donde cada nodo representa una decisión o criterio, y cada hijo
 * representa una opción o resultado de esa decisión.
 */
public class ArbolDeDecision {

    private String decision;

    private List<ArbolDeDecision> hijos;

    /**
     * Constructor para crear un nuevo nodo en el árbol de decisión.
     *
     * @param decision La decisión o criterio representado por este nodo.
     */
    public ArbolDeDecision(String decision) {
        this.decision = decision;
        this.hijos = new ArrayList<>();
    }

    /**
     * Agrega un hijo al nodo actual en el árbol de decisión.
     *
     * @param hijo El nodo hijo que se agregará al árbol.
     */
    public void agregarHijo(ArbolDeDecision hijo) {
        this.hijos.add(hijo);
    }

    /**
     * Obtiene la lista de nodos hijos de este nodo en el árbol de decisión.
     *
     * @return La lista de nodos hijos.
     */
    public List<ArbolDeDecision> getHijos() {
        return hijos;
    }

    /**
     * Obtiene la decisión o criterio representado por este nodo.
     *
     * @return La decisión o criterio del nodo.
     */
    public String getDecision() {
        return decision;
    }

    /**
     * Crea y configura el árbol de decisión con las categorías y subcategorías necesarias para evaluar las ciudades.
     *
     * @return El nodo raíz del árbol de decisión.
     */
    public static ArbolDeDecision armarArbolDeDecision() {
        ArbolDeDecision raiz = new ArbolDeDecision("Arbol de decisión:");

        // Categorías basadas en el porcentaje de apoyo
        ArbolDeDecision rango0_20 = new ArbolDeDecision("0-20%");
        ArbolDeDecision rango21_50 = new ArbolDeDecision("21-50%");
        ArbolDeDecision rango51_100 = new ArbolDeDecision("51-100%");

        raiz.agregarHijo(rango0_20);
        raiz.agregarHijo(rango21_50);
        raiz.agregarHijo(rango51_100);

        // Categorías basadas en el costo del viaje
        ArbolDeDecision costo0_30_1 = new ArbolDeDecision("0-30");
        ArbolDeDecision costo31_60_1 = new ArbolDeDecision("31-60");
        ArbolDeDecision costo61_100_1 = new ArbolDeDecision("61-100");

        ArbolDeDecision costo0_30_2 = new ArbolDeDecision("0-30");
        ArbolDeDecision costo31_60_2 = new ArbolDeDecision("31-60");
        ArbolDeDecision costo61_100_2 = new ArbolDeDecision("61-100");

        ArbolDeDecision costo0_30_3 = new ArbolDeDecision("0-30");
        ArbolDeDecision costo31_60_3 = new ArbolDeDecision("31-60");
        ArbolDeDecision costo61_100_3 = new ArbolDeDecision("61-100");

        rango0_20.agregarHijo(costo0_30_1);
        rango0_20.agregarHijo(costo31_60_1);
        rango0_20.agregarHijo(costo61_100_1);

        rango21_50.agregarHijo(costo0_30_2);
        rango21_50.agregarHijo(costo31_60_2);
        rango21_50.agregarHijo(costo61_100_2);

        rango51_100.agregarHijo(costo0_30_3);
        rango51_100.agregarHijo(costo31_60_3);
        rango51_100.agregarHijo(costo61_100_3);

        // Categorías basadas en el clima
        ArbolDeDecision soleado = new ArbolDeDecision("Soleado");
        ArbolDeDecision lluvioso = new ArbolDeDecision("Lluvioso");
        ArbolDeDecision niebla = new ArbolDeDecision("Niebla");

        costo0_30_1.agregarHijo(soleado);
        costo0_30_1.agregarHijo(lluvioso);
        costo0_30_1.agregarHijo(niebla);

        costo31_60_1.agregarHijo(soleado);
        costo31_60_1.agregarHijo(lluvioso);
        costo31_60_1.agregarHijo(niebla);

        costo61_100_1.agregarHijo(soleado);
        costo61_100_1.agregarHijo(lluvioso);
        costo61_100_1.agregarHijo(niebla);

        costo0_30_2.agregarHijo(soleado);
        costo0_30_2.agregarHijo(lluvioso);
        costo0_30_2.agregarHijo(niebla);

        costo31_60_2.agregarHijo(soleado);
        costo31_60_2.agregarHijo(lluvioso);
        costo31_60_2.agregarHijo(niebla);

        costo61_100_2.agregarHijo(soleado);
        costo61_100_2.agregarHijo(lluvioso);
        costo61_100_2.agregarHijo(niebla);

        costo0_30_3.agregarHijo(soleado);
        costo0_30_3.agregarHijo(lluvioso);
        costo0_30_3.agregarHijo(niebla);

        costo31_60_3.agregarHijo(soleado);
        costo31_60_3.agregarHijo(lluvioso);
        costo31_60_3.agregarHijo(niebla);

        costo61_100_3.agregarHijo(soleado);
        costo61_100_3.agregarHijo(lluvioso);
        costo61_100_3.agregarHijo(niebla);

        return raiz;
    }

    /**
     * Imprime el árbol de decisión en la consola, mostrando las decisiones y opciones de forma jerárquica.
     *
     * @param nodo   El nodo actual del árbol de decisión que se imprimirá.
     * @param prefix El prefijo que se utiliza para mostrar la jerarquía del árbol.
     */
    public static void imprimirArbol(ArbolDeDecision nodo, String prefix) {
        System.out.println(prefix + nodo.getDecision());
        for (ArbolDeDecision hijo : nodo.getHijos()) {
            imprimirArbol(hijo, prefix + "  ");
        }
    }

}
