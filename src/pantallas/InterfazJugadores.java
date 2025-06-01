/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

/**
 *
 * @author Joel
 */

import Estilos.RoundedBorder;
import Estilos.RoundedPanel;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import pantallas.ConexionBD;

public class InterfazJugadores extends JFrame {
    public InterfazJugadores() {
        setTitle("Base de datos de jugadores");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Base de datos de jugadores", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(new Color(100, 0, 200));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);

        List<Jugador> jugadores = ConexionBD.obtenerJugadores();
        System.out.println("Jugadores cargados: " + jugadores.size());

        int contador = 1;
        for (Jugador j : jugadores) {
            System.out.println("Cargando jugador: " + j.getNickname());
            panel.add(Box.createRigidArea(new Dimension(0, 10))); // 20 píxeles de espacio vertica
            panel.add(crearPanelJugador(j, contador));
            panel.add(Box.createRigidArea(new Dimension(0, 20))); // 20 píxeles de espacio vertical
            contador++;
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBackground(new Color(151, 231, 255));
        scrollPane.getViewport().setBackground(new Color(151, 231, 255)); // <-- muy importante
        add(scrollPane);
    }

  private JPanel crearPanelJugador(Jugador jugador, int numero) {
    // Definir colores diferentes para cada jugador
    Color[] colores = {
        new Color(151, 231, 255),  // Azul
        new Color(200, 255, 200),  // Verde claro
        new Color(255, 235, 180)   // Amarillo claro
    };
    Color colorFondo = colores[(numero - 1) % colores.length];

    RoundedPanel jugadorPanel = new RoundedPanel(30);
    jugadorPanel.setPreferredSize(new Dimension(900, 270));
    jugadorPanel.setMaximumSize(new Dimension(925, 270));
    jugadorPanel.setMinimumSize(new Dimension(900, 270));
    jugadorPanel.setBackground(colorFondo);
    jugadorPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    jugadorPanel.setLayout(new BorderLayout());

    JPanel datosPanel = new JPanel(new GridLayout(1, 3));
    datosPanel.setBackground(colorFondo);
    datosPanel.setOpaque(true);

    JLabel icono = new JLabel();
    icono.setIcon(new ImageIcon(getClass().getResource(jugador.getProfileIcon())));
    icono.setFont(new Font("Century Gothic", Font.BOLD, 20));
    icono.setText("Nickname: " + jugador.getNickname());
    icono.setHorizontalTextPosition(SwingConstants.CENTER);
    icono.setVerticalTextPosition(SwingConstants.BOTTOM);
    icono.setForeground(Color.DARK_GRAY);
    datosPanel.add(icono);

    Font fuente = new Font("Century Gothic", Font.BOLD, 16);
    Color colorTexto = new Color(40, 40, 40);

    String encabezadoUsuario = "Datos del usuario: ";
    JPanel usuario = new JPanel();
    usuario.setBackground(colorFondo);
    usuario.setOpaque(true);
    usuario.setLayout(new BoxLayout(usuario, BoxLayout.Y_AXIS));
    usuario.add(crearLabelEstilizado(encabezadoUsuario, fuente, colorTexto));
    usuario.add(crearLabelEstilizado("Id: " + jugador.getId(), fuente, colorTexto));
    usuario.add(crearLabelEstilizado("Edad: " + jugador.getEdad(), fuente, colorTexto));
    usuario.add(crearLabelEstilizado("Victorias: " + jugador.getVictorias(), fuente, colorTexto));
    usuario.add(crearLabelEstilizado("Derrotas: " + jugador.getDerrotas(), fuente, colorTexto));
    datosPanel.add(usuario);

    String encabezadoPartida = "Datos del usuario: ";
    JPanel partida = new JPanel();
    partida.setBackground(colorFondo);
    partida.setOpaque(true);
    partida.setLayout(new BoxLayout(partida, BoxLayout.Y_AXIS));
    partida.add(crearLabelEstilizado(encabezadoPartida, fuente, colorTexto));
    partida.add(crearLabelEstilizado("Personaje adivinado: " + jugador.getPersonajeAdivinado(), fuente, colorTexto));
    partida.add(crearLabelEstilizado("Tiempo de partida: " + jugador.getTiempo(), fuente, colorTexto));
    partida.add(crearLabelEstilizado("Intentos: " + jugador.getIntentos(), fuente, colorTexto));
    partida.add(crearLabelEstilizado("Fecha: " + jugador.getFechaPartida(), fuente, colorTexto));
    datosPanel.add(partida);

    jugadorPanel.add(datosPanel, BorderLayout.CENTER);

    JPanel rankingPanel = new JPanel();
    rankingPanel.setBackground(colorFondo);
    rankingPanel.setOpaque(true);
    JLabel ranking = new JLabel("RANKING: " + jugador.getRanking());
    ranking.setForeground(Color.BLACK);
    ranking.setFont(new Font("Arial", Font.BOLD, 18));
    rankingPanel.add(ranking);
    jugadorPanel.add(rankingPanel, BorderLayout.SOUTH);

    return jugadorPanel;
}
 private Component crearLabelEstilizado(String texto, Font fuente, Color color) {
    JPanel contenedor = new JPanel();
    contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
    contenedor.setOpaque(false); // sin fondo

    JLabel label = new JLabel(texto);
    label.setFont(fuente);
    label.setForeground(color);
    contenedor.add(label);

    contenedor.add(Box.createVerticalStrut(13)); // espacio de 5px abajo

    return contenedor;
}
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InterfazJugadores().setVisible(true);
        });
    }
}
