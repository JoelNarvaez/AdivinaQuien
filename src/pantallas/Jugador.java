/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import java.util.Date;
import java.sql.Time;


/**
 *
 * @author Joel
 */
public class Jugador {
    //Datos del jugador
    private int id;
    private String nickname;
    private String profileIcon;
    private int edad;
    private int victorias;
    private int derrotas;
    //Datos de la ultima partida jugada
    private String personajeAdivinado;
    private Date FechaPartida;
    private int intentos;
    private Time tiempo;
    private String jugadorVS;
    private int ranking;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfileIcon() {
        return profileIcon;
    }

    public void setProfileIcon(String profileIcon) {
        this.profileIcon = profileIcon;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public String getPersonajeAdivinado() {
        return personajeAdivinado;
    }

    public void setPersonajeAdivinado(String personajeAdivinado) {
        this.personajeAdivinado = personajeAdivinado;
    }

    public Date getFechaPartida() {
        return FechaPartida;
    }

    public void setFechaPartida(Date FechaPartida) {
        this.FechaPartida = FechaPartida;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public Time getTiempo() {
        return tiempo;
    }

    public void setTiempo(Time tiempo) {
        this.tiempo = tiempo;
    }

    public String getJugadorVS() {
        return jugadorVS;
    }

    public void setJugadorVS(String jugadorVS) {
        this.jugadorVS = jugadorVS;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

   
    
    
    
    
    
    
}
