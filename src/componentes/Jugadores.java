/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package componentes;

import Estilos.RoundedPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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

/**
 *
 * @author Joel
 */
public class Jugadores extends javax.swing.JPanel {
     public Jugadores() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder());

        JLabel titulo = new JLabel("Base de datos de jugadores", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(new Color(100, 0, 200));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);

        List<Jugador> jugadores = ConexionBD.obtenerJugadores();
        System.out.println("Jugadores cargados: " + jugadores.size());

        int contador = 1;
        for (Jugador j : jugadores) {
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(crearPanelJugador(j, contador));
            panel.add(Box.createRigidArea(new Dimension(0, 20)));
            contador++;
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBackground(new Color(151, 231, 255));
        scrollPane.getViewport().setBackground(new Color(151, 231, 255));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBorder(null);

        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel crearPanelJugador(Jugador jugador, int numero) {
        Color[] colores = {
            new Color(151, 231, 255),
            new Color(200, 255, 200),
            new Color(255, 235, 180)
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

        JPanel usuario = new JPanel();
        usuario.setBackground(colorFondo);
        usuario.setLayout(new BoxLayout(usuario, BoxLayout.Y_AXIS));
        usuario.add(crearLabelEstilizado("Datos del usuario:", fuente, colorTexto));
        usuario.add(crearLabelEstilizado("Id: " + jugador.getId(), fuente, colorTexto));
        usuario.add(crearLabelEstilizado("Edad: " + jugador.getEdad(), fuente, colorTexto));
        usuario.add(crearLabelEstilizado("Victorias: " + jugador.getVictorias(), fuente, colorTexto));
        usuario.add(crearLabelEstilizado("Derrotas: " + jugador.getDerrotas(), fuente, colorTexto));
        datosPanel.add(usuario);

        JPanel partida = new JPanel();
        partida.setBackground(colorFondo);
        partida.setLayout(new BoxLayout(partida, BoxLayout.Y_AXIS));
        partida.add(crearLabelEstilizado("Datos de la partida:", fuente, colorTexto));
        partida.add(crearLabelEstilizado("Personaje adivinado: " + jugador.getPersonajeAdivinado(), fuente, colorTexto));
        partida.add(crearLabelEstilizado("Tiempo de partida: " + jugador.getTiempo(), fuente, colorTexto));
        partida.add(crearLabelEstilizado("Intentos: " + jugador.getIntentos(), fuente, colorTexto));
        partida.add(crearLabelEstilizado("Fecha: " + jugador.getFechaPartida(), fuente, colorTexto));
        datosPanel.add(partida);

        jugadorPanel.add(datosPanel, BorderLayout.CENTER);

        JPanel rankingPanel = new JPanel();
        rankingPanel.setBackground(colorFondo);
        JLabel ranking = new JLabel("RANKING: " + jugador.getRanking());
        ranking.setFont(new Font("Arial", Font.BOLD, 18));
        ranking.setForeground(Color.BLACK);
        rankingPanel.add(ranking);
        jugadorPanel.add(rankingPanel, BorderLayout.SOUTH);

        return jugadorPanel;
    }

    private Component crearLabelEstilizado(String texto, Font fuente, Color color) {
        JPanel contenedor = new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.setOpaque(false);

        JLabel label = new JLabel(texto);
        label.setFont(fuente);
        label.setForeground(color);
        contenedor.add(label);

        contenedor.add(Box.createVerticalStrut(13));
        return contenedor;
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
