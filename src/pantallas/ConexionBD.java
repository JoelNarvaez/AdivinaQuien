
package pantallas;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Joel
 */
public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/adivina_quien";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Imagen de perfil del jugador
    public static String obtenerProfilePorNombre(String nickname) {
    String sql = "SELECT profile FROM jugadores WHERE nombre_jugador = ?";

    try (Connection conn = conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, nickname);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getString("profile");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null; // Retorna null si no se encontró el jugador o si hubo error
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
        stmt.setInt(5, j.getEdad()); // 👈 NUEVA LÍNEA para la edad
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


    
    public static List<String> obtenerPreguntasActivas() {
        List<String> preguntas = new ArrayList<>();
        String sql = "SELECT pregunta FROM preguntas_adivinaquien WHERE activa=1 ORDER BY pregunta";
        try (Connection con = conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next())
                preguntas.add(rs.getString("pregunta"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preguntas;
    }
    
    // Metodo para insertar datos de una partida
     public static boolean insertarPartida(Partida partida) {
        String sql = "INSERT INTO partidas (jugador1, jugador2, ganador, personaje_ganador, fecha, duracion) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, partida.getJugador1());
            ps.setString(2, partida.getJugador2());
            ps.setString(3, partida.getGanador());
            ps.setString(4, partida.getPersonajeGanador());
            ps.setDate(5, partida.getFecha());
            ps.setTime(6, partida.getDuracion());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //Metdodo para ordenar las partidas por duracion
   public static List<Partida> obtenerPartidasOrdenadasPorDuracion() {
        List<Partida> lista = new ArrayList<>();
        String sql = "SELECT * FROM partidas ORDER BY duracion ASC";

        try (Connection conn = conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Partida partida = new Partida();
                partida.setId(rs.getInt("id"));
                partida.setJugador1(rs.getString("jugador1"));
                partida.setJugador2(rs.getString("jugador2"));
                partida.setGanador(rs.getString("ganador"));
                partida.setPersonajeGanador(rs.getString("personaje_ganador"));
                partida.setFecha(rs.getDate("fecha"));
                partida.setDuracion(rs.getTime("duracion"));
                lista.add(partida);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    
    //metdodo para que me muestre todas las partidas donde aparece el nombre de un jugador
    public static List<Partida> obtenerPartidasPorJugador(String nombreJugador) {
        List<Partida> lista = new ArrayList<>();
        String sql = "SELECT * FROM partidas WHERE jugador1 = ? OR jugador2 = ?";

        try (Connection conn = conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombreJugador);
            ps.setString(2, nombreJugador);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Partida partida = new Partida();
                partida.setId(rs.getInt("id"));
                partida.setJugador1(rs.getString("jugador1"));
                partida.setJugador2(rs.getString("jugador2"));
                partida.setGanador(rs.getString("ganador"));
                partida.setPersonajeGanador(rs.getString("personaje_ganador"));
                partida.setFecha(rs.getDate("fecha"));
                partida.setDuracion(rs.getTime("duracion"));
                lista.add(partida);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    //Metodo para ver todas las partidas
    public static List<Partida> obtenerTodasLasPartidas() {
        List<Partida> lista = new ArrayList<>();
        String sql = "SELECT * FROM partidas";

        try (Connection conn = conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Partida partida = new Partida();
                partida.setId(rs.getInt("id"));
                partida.setJugador1(rs.getString("jugador1"));
                partida.setJugador2(rs.getString("jugador2"));
                partida.setGanador(rs.getString("ganador"));
                partida.setPersonajeGanador(rs.getString("personaje_ganador"));
                partida.setFecha(rs.getDate("fecha"));
                partida.setDuracion(rs.getTime("duracion"));
                lista.add(partida);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
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



