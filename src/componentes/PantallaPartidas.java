/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package componentes;

/**
 *
 * @author Joel
 */


import Estilos.RoundedPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import pantallas.ConexionBD;
import pantallas.Jugador;
import pantallas.Partida;
import net.miginfocom.swing.MigLayout;

public class PantallaPartidas extends javax.swing.JPanel {
    public PantallaPartidas() {
    setLayout(new BorderLayout());
    setBackground(Color.WHITE);

    // Título superior del panel (NO se mueve con el scroll)
    JLabel titulo = new JLabel("Base de datos de partidas", SwingConstants.CENTER);
    titulo.setFont(new Font("Century Gothic", Font.BOLD, 20));
    titulo.setForeground(new Color(100, 0, 200));
    titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // margen arriba/abajo
    add(titulo, BorderLayout.NORTH); // título como encabezado del panel

    // Contenedor del contenido scrollable
    JPanel panelContenido = new JPanel();
    panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
    panelContenido.setBackground(Color.WHITE);

    List<Partida> partidas = ConexionBD.obtenerTodasLasPartidas();

    for (Partida partida : partidas) {
        panelContenido.add(crearPanelPartida(partida));
        panelContenido.add(Box.createVerticalStrut(20));
    }

    JScrollPane scrollPane = new JScrollPane(panelContenido);
    scrollPane.getViewport().setBackground(new Color(85, 38, 131));
    scrollPane.setBorder(null);
    scrollPane.getViewport().setBorder(null);

    add(scrollPane, BorderLayout.CENTER); // scroll ocupa el centro
}
   

  private JPanel crearPanelPartida(Partida p) {
    RoundedPanel panel = new RoundedPanel(30);
    panel.setBackground(new Color(85, 38, 131));
    panel.setLayout(new BorderLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    panel.setPreferredSize(new Dimension(1000, 300));
    panel.setMaximumSize(new Dimension(1100, 300));

    // === Panel jugadores (izquierda) con título ===
    JPanel jugadoresPanel = new JPanel();
    jugadoresPanel.setLayout(new BoxLayout(jugadoresPanel, BoxLayout.X_AXIS));
    jugadoresPanel.setBackground(new Color(85, 38, 131));
    jugadoresPanel.setPreferredSize(new Dimension(500, 240));

    // Jugador 1
    JPanel panelJugador1 = new JPanel(new BorderLayout());
    panelJugador1.setBackground(new Color(85, 38, 131));
    panelJugador1.setPreferredSize(new Dimension(220, 240));
    JLabel jugador1 = crearLabelJugador(ConexionBD.obtenerProfilePorNombre(p.getJugador1()), "Nickname: " + p.getJugador1());
    panelJugador1.add(jugador1, BorderLayout.CENTER);

    // "vs"
    JPanel panelVs = new JPanel(new BorderLayout());
    panelVs.setBackground(new Color(85, 38, 131));
    panelVs.setPreferredSize(new Dimension(60, 240));
    JLabel vs = new JLabel("vs", SwingConstants.CENTER);
    vs.setForeground(Color.WHITE);
    vs.setFont(new Font("Arial", Font.BOLD, 18));
    panelVs.add(vs, BorderLayout.CENTER);

    // Jugador 2
    JPanel panelJugador2 = new JPanel(new BorderLayout());
    panelJugador2.setBackground(new Color(85, 38, 131));
    panelJugador2.setPreferredSize(new Dimension(220, 240));
    JLabel jugador2 = crearLabelJugador(ConexionBD.obtenerProfilePorNombre(p.getJugador2()), "Nickname: " + p.getJugador2());
    panelJugador2.add(jugador2, BorderLayout.CENTER);

    // Agregar a jugadoresPanel
    jugadoresPanel.add(panelJugador1);
    jugadoresPanel.add(panelVs);
    jugadoresPanel.add(panelJugador2);

    // === Contenedor vertical con título "Jugadores:" ===
    JPanel contenedorJugadores = new JPanel();
    contenedorJugadores.setLayout(new BoxLayout(contenedorJugadores, BoxLayout.Y_AXIS));
    contenedorJugadores.setBackground(new Color(85, 38, 131)); 
    JLabel tituloJugadores = new JLabel("Jugadores:");
    tituloJugadores.setForeground(new Color(0, 255, 255));
    tituloJugadores.setFont(new Font("Arial", Font.BOLD, 18));
    tituloJugadores.setAlignmentX(Component.LEFT_ALIGNMENT);

    // Ensamblar el contenedor
    contenedorJugadores.add(tituloJugadores);
    contenedorJugadores.add(Box.createVerticalStrut(5)); // espacio
    contenedorJugadores.add(jugadoresPanel);

    // === Panel datos partida (centro) ===
    JPanel datosPanel = new JPanel();
    datosPanel.setLayout(new BoxLayout(datosPanel, BoxLayout.Y_AXIS));
    datosPanel.setBackground(new Color(85, 38, 131));
    JLabel tituloDatos = crearTitulo("Datos de partida:");
    JLabel ganador = crearDato("Ganador: " + p.getGanador());
    JLabel duracion = crearDato("Duración: " + p.getDuracion().toString());
    JLabel fecha = crearDato("Fecha: " + p.getFecha().toString());
    datosPanel.add(tituloDatos);
    datosPanel.add(Box.createVerticalStrut(20)); // espacio entre título y ganador
    datosPanel.add(ganador);
    datosPanel.add(Box.createVerticalStrut(15)); // espacio entre título y ganador
    datosPanel.add(duracion);
    datosPanel.add(Box.createVerticalStrut(15)); // espacio entre título y ganador
    datosPanel.add(fecha);

    // === Panel personaje (derecha) ===
    JPanel personajePanel = new JPanel();
    personajePanel.setBackground(new Color(85, 38, 131));
    personajePanel.setLayout(new BoxLayout(personajePanel, BoxLayout.Y_AXIS));
    JLabel tituloPersonaje = crearTitulo("Personaje con el que ganó:");
    JLabel imagenPersonaje = crearLabelPersonaje(p.getPersonajeGanador());
    imagenPersonaje.setAlignmentX(Component.CENTER_ALIGNMENT);
    personajePanel.add(tituloPersonaje);
    personajePanel.add(Box.createVerticalStrut(15)); // espacio entre título y ganador
    personajePanel.add(imagenPersonaje);

    // Agregar a panel principal
    panel.add(contenedorJugadores, BorderLayout.WEST);
    panel.add(datosPanel, BorderLayout.CENTER);
    panel.add(personajePanel, BorderLayout.EAST);

    return panel;
}


    private JLabel crearLabelJugador(String rutaImagen, String texto) {
        JLabel label = new JLabel();
        java.net.URL url = getClass().getResource(rutaImagen);

        if (url != null) {
            ImageIcon iconoOriginal = new ImageIcon(url);
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(imagenEscalada));
        } else {
            System.out.println("Imagen no encontrada: " + rutaImagen);
            label.setText("Imagen no disponible");
        }

        label.setText(texto);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Century Gothic", Font.BOLD, 18));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private JLabel crearLabelPersonaje(String rutaImagen) {
        JLabel label = new JLabel();
        java.net.URL url = getClass().getResource(rutaImagen);

        if (url != null) {
            ImageIcon iconoOriginal = new ImageIcon(url);
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(imagenEscalada));
        } else {
            System.out.println("Imagen de personaje no encontrada: " + rutaImagen);
            label.setText("Imagen no disponible");
        }

        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Century Gothic", Font.BOLD, 25));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private JLabel crearTitulo(String texto) {
        JLabel label = new JLabel(texto);
        label.setForeground(new Color(0, 255, 255));
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JLabel crearDato(String texto) {
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Century Gothic", Font.BOLD, 18));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
