/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.SQLException;

/**
 *
 * @author Joel
 */
public class ConexionBD {
    public static Connection conectar() {
        try {
            String url = "jdbc:mysql://localhost:3306/adivina_quien";
            String user = "root";
            String password = "";
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Insertar un jugador
    public static boolean insertarJugador(Jugador j) {
    String sql = "INSERT INTO jugadores " +
            "(nombre_jugador, profile, victorias, derrotas, edad, personaje_adivinado, tiempo_juego, intentos, fecha_partida, jugador_vs, ranking) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        // Campos obligatorios
        stmt.setString(1, j.getNickname());
        stmt.setString(2, j.getProfileIcon());
        stmt.setInt(3, j.getVictorias()); // 0 por defecto si no se asigna
        stmt.setInt(4, j.getDerrotas());  // 0 por defecto si no se asigna
        stmt.setInt(5, j.getEdad());

        // Campos opcionales: revisa nulos antes de asignar
        if (j.getPersonajeAdivinado() != null)
            stmt.setString(6, j.getPersonajeAdivinado());
        else
            stmt.setNull(6, java.sql.Types.VARCHAR);

        if (j.getTiempo() != null)
            stmt.setTime(7, j.getTiempo());
        else
            stmt.setNull(7, java.sql.Types.TIME);

        stmt.setInt(8, j.getIntentos()); // 0 si no se asigna

        if (j.getFechaPartida() != null)
            stmt.setDate(9, new java.sql.Date(j.getFechaPartida().getTime()));
        else
            stmt.setNull(9, java.sql.Types.DATE);

        if (j.getJugadorVS() != null)
            stmt.setString(10, j.getJugadorVS());
        else
            stmt.setNull(10, java.sql.Types.VARCHAR);

        stmt.setInt(11, j.getRanking()); // 0 si no se asigna

        return stmt.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


    // Obtener todos los jugadores
    public static List<Jugador> obtenerJugadores() {
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT * FROM jugadores";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearJugador(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Buscar jugador por ID
    public static Jugador buscarPorId(int id) {
        String sql = "SELECT * FROM jugadores WHERE id = ?";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearJugador(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public static Jugador buscarPorNombre(String nickname) {
        String sql = "SELECT * FROM jugadores WHERE nombre_jugador = ?";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nickname);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearJugador(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Actualizar jugador
    public static boolean actualizarJugador(Jugador j, int id) {
        String sql = "UPDATE jugadores SET nombre_jugador = ?, profile = ?, victorias = ?, derrotas = ?, edad = ?, personaje_adivinado = ?, tiempo_juego = ?, intentos = ?, fecha_partida = ?, jugador_vs = ?, ranking = ? WHERE id = ?";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, j.getNickname());
            stmt.setString(2, j.getProfileIcon());
            stmt.setInt(3, j.getVictorias());
            stmt.setInt(4, j.getDerrotas());
            stmt.setInt(5, j.getEdad());
            stmt.setString(6, j.getPersonajeAdivinado());
            stmt.setTime(7, j.getTiempo());
            stmt.setInt(8, j.getIntentos());
            stmt.setDate(9, new java.sql.Date(j.getFechaPartida().getTime()));
            stmt.setString(10, j.getJugadorVS());
            stmt.setInt(11, j.getRanking());
            stmt.setInt(12, id);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar jugador por ID
    public static boolean eliminarJugador(int id) {
        String sql = "DELETE FROM jugadores WHERE id = ?";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método auxiliar para mapear un ResultSet a un objeto Jugador
    private static Jugador mapearJugador(ResultSet rs) throws SQLException {
        Jugador j = new Jugador();
        j.setId(rs.getInt("id"));
        j.setNickname(rs.getString("nombre_jugador"));
        j.setProfileIcon(rs.getString("profile"));
        j.setVictorias(rs.getInt("victorias"));
        j.setDerrotas(rs.getInt("derrotas"));
        j.setEdad(rs.getInt("edad"));
        j.setPersonajeAdivinado(rs.getString("personaje_adivinado"));
        j.setTiempo(rs.getTime("tiempo_juego"));
        j.setIntentos(rs.getInt("intentos"));
        j.setFechaPartida(rs.getDate("fecha_partida"));
        j.setJugadorVS(rs.getString("jugador_vs"));
        j.setRanking(rs.getInt("ranking"));
        return j;
    }
}



