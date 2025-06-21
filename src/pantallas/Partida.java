/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

/**
 *
 * @author Joel
 */

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;


public class Partida implements Serializable {
    private static final long serialVersionUID = 1L; // Opcional, pero recomendado
    private int id;
    private String jugador1;
    private String jugador2;
    private String ganador;
    private String personajeGanador;
    private Date fecha;
    private Time duracion;

    // Constructor vacío
    public Partida() {}

    // Constructor completo
    public Partida(int id, String jugador1, String jugador2, String ganador, String personajeGanador, Date fecha, Time duracion) {
        this.id = id;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.ganador = ganador;
        this.personajeGanador = personajeGanador;
        this.fecha = fecha;
        this.duracion = duracion;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJugador1() {
        return jugador1;
    }

    public void setJugador1(String jugador1) {
        this.jugador1 = jugador1;
    }

    public String getJugador2() {
        return jugador2;
    }

    public void setJugador2(String jugador2) {
        this.jugador2 = jugador2;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    public String getPersonajeGanador() {
        return personajeGanador;
    }

    public void setPersonajeGanador(String personajeGanador) {
        this.personajeGanador = personajeGanador;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getDuracion() {
        return duracion;
    }

    public void setDuracion(Time duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Partida{" +
                "id=" + id +
                ", jugador1='" + jugador1 + '\'' +
                ", jugador2='" + jugador2 + '\'' +
                ", ganador='" + ganador + '\'' +
                ", personajeGanador='" + personajeGanador + '\'' +
                ", fecha=" + fecha +
                ", duracion=" + duracion +
                '}';
    }
}
