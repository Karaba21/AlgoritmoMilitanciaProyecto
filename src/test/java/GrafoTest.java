import org.example.Ciudad;
import org.example.Grafo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class GrafoTest {

    private Grafo grafo;

    @BeforeEach
    public void setUp() {
        grafo = new Grafo();

        //Creo las ciudades
        Ciudad ciudad1 = new Ciudad("Ciudad1", 10);
        Ciudad ciudad2 = new Ciudad("Ciudad2", 15);
        Ciudad ciudad3 = new Ciudad("Ciudad3", 20);
        Ciudad ciudad4 = new Ciudad("Ciudad4", 5);

        //Agrego ciudades al grafo
        grafo.agregarCiudad(ciudad1);
        grafo.agregarCiudad(ciudad2);
        grafo.agregarCiudad(ciudad3);
        grafo.agregarCiudad(ciudad4);

        //Creo y agrego los caminos
        grafo.agregarCamino(ciudad1, ciudad2, 30, "soleado");
        grafo.agregarCamino(ciudad1, ciudad3, 45, "lluvioso");
        grafo.agregarCamino(ciudad1, ciudad4, 20, "soleado");
        grafo.agregarCamino(ciudad2, ciudad3, 60, "niebla");
        grafo.agregarCamino(ciudad2, ciudad4, 50, "lluvioso");
    }

    @Test
    public void testCiudadesMasFavorables() {
        Ciudad ciudad1 = grafo.buscarCiudad("Ciudad1");
        List<Ciudad> ciudadesFavorables = grafo.ciudadesMasFavorables(ciudad1);

        // La ciudad más favorable debería ser Ciudad4 (menor costoPonderado)
        assertEquals("Ciudad4", ciudadesFavorables.get(0).getNombre());
        // La segunda ciudad más favorable debería ser Ciudad2
        assertEquals("Ciudad2", ciudadesFavorables.get(1).getNombre());
        // La tercera ciudad más favorable debería ser Ciudad3 (después de ajustar el tiempo por clima lluvioso)
        assertEquals("Ciudad3", ciudadesFavorables.get(2).getNombre());
    }

    @Test
    public void testCiudadesMasFavorablesOpcionesSimilares() {
        Ciudad ciudad1 = grafo.buscarCiudad("Ciudad1");
        Ciudad ciudad5 = new Ciudad("Ciudad5", 25);
        Ciudad ciudad6 = new Ciudad("Ciudad6", 25);
        Ciudad ciudad7 = new Ciudad("Ciudad7", 25);

        grafo.agregarCiudad(ciudad5);
        grafo.agregarCiudad(ciudad6);
        grafo.agregarCiudad(ciudad7);

        grafo.agregarCamino(ciudad1, ciudad5, 15, "soleado"); // Costo: 15 / (1 + 0.25) = 12
        grafo.agregarCamino(ciudad1, ciudad6, 16, "soleado"); // Costo: 16 / (1 + 0.25) = 12.8
        grafo.agregarCamino(ciudad1, ciudad7, 17, "soleado"); // Costo: 17 / (1 + 0.25) = 13.6

        List<Ciudad> ciudadesFavorables = grafo.ciudadesMasFavorables(ciudad1);

        // La ciudad más favorable debería ser Ciudad5
        assertEquals("Ciudad5", ciudadesFavorables.get(0).getNombre());
        // La segunda ciudad más favorable debería ser Ciudad6
        assertEquals("Ciudad6", ciudadesFavorables.get(1).getNombre());
        // La tercera ciudad más favorable debería ser Ciudad7
        assertEquals("Ciudad7", ciudadesFavorables.get(2).getNombre());
    }

    @Test
    public void testCiudadesMasFavorablesCasoBorde() {
        Ciudad ciudad1 = grafo.buscarCiudad("Ciudad1");
        Ciudad ciudad8 = new Ciudad("Ciudad8", 50);

        grafo.agregarCiudad(ciudad8);

        // Camino imposible debido a la niebla
        grafo.agregarCamino(ciudad1, ciudad8, 10, "niebla");

        // Camino posible pero con apoyo alto y clima lluvioso
        grafo.agregarCamino(ciudad1, grafo.buscarCiudad("Ciudad3"), 20, "lluvioso"); // Costo: 40 / (1 + 0.3) = 33.33

        List<Ciudad> ciudadesFavorables = grafo.ciudadesMasFavorables(ciudad1);

        // La ciudad más favorable debería ser Ciudad4
        assertEquals("Ciudad4", ciudadesFavorables.get(0).getNombre());
        // La segunda ciudad más favorable debería ser Ciudad2
        assertEquals("Ciudad2", ciudadesFavorables.get(1).getNombre());
        // La tercera ciudad más favorable debería ser Ciudad3
        assertEquals("Ciudad3", ciudadesFavorables.get(2).getNombre());
        // Ciudad8 no debería estar en la lista porque el camino es imposible
        assertFalse(ciudadesFavorables.contains(ciudad8));
    }

    @Test
    public void testCiudadesMasFavorablesCiudadNoValida() {
        // Ciudad no válida que no está en el grafo
        Ciudad ciudadNoValida = new Ciudad("CiudadNoValida", 0);

        // Verifica que se lanza una IllegalArgumentException al pasar una ciudad no válida
        try {
            grafo.ciudadesMasFavorables(ciudadNoValida);
            fail("Se esperaba una IllegalArgumentException al pasar una ciudad no válida.");
        } catch (IllegalArgumentException e) {
            // Verificar que el mensaje de la excepción es el esperado
            assertEquals("La ciudad no es válida o no existe en el grafo.", e.getMessage());
        }
    }

}