/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import java.io.Serializable; // ← IMPORTANTE
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Joel y lore
 */

public class PersonajeDisney implements Serializable {
    private static final long serialVersionUID = 1L; // ← Recomendado para clases serializables
    
    private String nombre;
    private String rutaImagen;

    public PersonajeDisney(String nombre, String rutaImagen) {
        this.nombre = nombre;
        this.rutaImagen = rutaImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    @Override
    public String toString() {
        return nombre;
    }

    // --- Aquí va la inicialización ---
    public static final PersonajeDisney[] LISTA = {
        new PersonajeDisney("Aladdin", "/Imagenes/Personajes/aladdin.png"),
        new PersonajeDisney("Alicia", "/Imagenes/Personajes/alicia.png"),
        new PersonajeDisney("Ana", "/Imagenes/Personajes/ana.png"),
        new PersonajeDisney("Ariel", "/Imagenes/Personajes/ariel.png"),
        new PersonajeDisney("Aurora", "/Imagenes/Personajes/aurora.png"),
        new PersonajeDisney("Bella", "/Imagenes/Personajes/bella.png"),
        new PersonajeDisney("Blanca Nieves", "/Imagenes/Personajes/blanca_nieves.png"),
        new PersonajeDisney("Bob", "/Imagenes/Personajes/bob.png"),
        new PersonajeDisney("Carl", "/Imagenes/Personajes/carl.png"),
        new PersonajeDisney("Cenicienta", "/Imagenes/Personajes/cenicienta.png"),
        new PersonajeDisney("Cruella", "/Imagenes/Personajes/cruella.png"),
        new PersonajeDisney("Dash", "/Imagenes/Personajes/dash.png"),
        new PersonajeDisney("Elsa", "/Imagenes/Personajes/elsa.png"),
        new PersonajeDisney("Flynn", "/Imagenes/Personajes/flynn.png"),
        new PersonajeDisney("Helen", "/Imagenes/Personajes/helen.png"),
        new PersonajeDisney("Hércules", "/Imagenes/Personajes/hercules.png"),
        new PersonajeDisney("Jack Jack", "/Imagenes/Personajes/jack_jack.png"),
        new PersonajeDisney("Jasmine", "/Imagenes/Personajes/jasmine.png"),
        new PersonajeDisney("Lilo", "/Imagenes/Personajes/lilo.png"),
        new PersonajeDisney("Linguini", "/Imagenes/Personajes/linguini.png"),
        new PersonajeDisney("Maléfica", "/Imagenes/Personajes/malefica.png"),
        new PersonajeDisney("Merida", "/Imagenes/Personajes/merida.png"),
        new PersonajeDisney("Moana", "/Imagenes/Personajes/moana.png"),
        new PersonajeDisney("Mulán", "/Imagenes/Personajes/mulan.png"),
        new PersonajeDisney("Peter Pan", "/Imagenes/Personajes/peter_pan.png"),
        new PersonajeDisney("Pocahontas", "/Imagenes/Personajes/pocahontas.png"),
        new PersonajeDisney("Quasimodo", "/Imagenes/Personajes/quasimodo.png"),
        new PersonajeDisney("Ralph", "/Imagenes/Personajes/ralph.png"),
        new PersonajeDisney("Rapunzel", "/Imagenes/Personajes/rapunzel.png"),
        new PersonajeDisney("Russell", "/Imagenes/Personajes/russell.png"),
        new PersonajeDisney("Tzaran", "/Imagenes/Personajes/tarzan.png"),
        new PersonajeDisney("Tiana", "/Imagenes/Personajes/tiana.png"),
        new PersonajeDisney("Vanellope", "/Imagenes/Personajes/vanellope.png"),
        new PersonajeDisney("Violeta", "/Imagenes/Personajes/violeta.png"),

    };

    // --- Si quieres HashMap directamente en la clase ---
    public static final java.util.Map<String, PersonajeDisney> MAPA;
    static {
        MAPA = new java.util.HashMap<>();
        for (PersonajeDisney p : LISTA) {
            MAPA.put(p.getNombre(), p);
        }
    }
    
    public static PersonajeDisney[] elegirPersonajesAleatorios(int cantidad) {
        List<PersonajeDisney> lista = new ArrayList<>(Arrays.asList(LISTA));
        Collections.shuffle(lista);
        return lista.subList(0, cantidad).toArray(new PersonajeDisney[0]);
    }
}
