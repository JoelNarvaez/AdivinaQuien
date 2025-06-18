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
import Estilos.Button;
import componentes.Jugadores;
import componentes.PantallaDuracion;
import componentes.PantallaNombres;
import componentes.PantallaPartidas;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import pantallas.ConexionBD;

public class BaseDeDatos extends JFrame {
     private CardLayout cardLayout;
    private JPanel contenedorPantallas;

    public BaseDeDatos() {
        setTitle("Sistema de Base de Datos");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);

        cardLayout = new CardLayout();
        contenedorPantallas = new JPanel(cardLayout);

        // Pantallas
        contenedorPantallas.add(new Jugadores(), "jugadores");
        contenedorPantallas.add(new PantallaPartidas(), "todasPartidas");
        contenedorPantallas.add(new PantallaDuracion(), "porDuracion");
        contenedorPantallas.add(new PantallaNombres(), "porNombre");
        
        
        setLayout(new BorderLayout());
        add(crearMenu(), BorderLayout.NORTH);
        add(contenedorPantallas, BorderLayout.CENTER);
    }

    private JPanel crearMenu() {
        JPanel menu = new JPanel();
        menu.setLayout(new FlowLayout(FlowLayout.LEFT));
        menu.setBackground(new Color(255, 255, 255));

       Font botonFont = new Font("Century Gothic", Font.BOLD, 18);

        // Botón Jugadores
        Button btnJugadores = new Button();
        btnJugadores.setText("  Jugadores  ");
        btnJugadores.setFont(botonFont);
        btnJugadores.setBackground(new Color(204, 102, 255)); // #CC66FF
        btnJugadores.setForeground(Color.WHITE);

        // Botón Todas las partidas
        Button btnTodas = new Button();
        btnTodas.setText("  Todas las partidas  ");
        btnTodas.setFont(botonFont);
        btnTodas.setBackground(new Color(102, 204, 255)); // #66CCFF
        btnTodas.setForeground(Color.WHITE);

        // Botón por duración
        Button btnDuracion = new Button();
        btnDuracion.setText("  Partidas por duración  ");
        btnDuracion.setFont(botonFont);
        btnDuracion.setBackground(new Color(255, 179, 71)); // #FFB347
        btnDuracion.setForeground(Color.WHITE);

        // Botón por jugador
        Button btnPorJugador = new Button();
        btnPorJugador.setText(" Partidas por jugador  ");
        btnPorJugador.setFont(botonFont);
        btnPorJugador.setBackground(new Color(102, 255, 153)); // #66FF99
        btnPorJugador.setForeground(Color.WHITE);
        
        //Boton para regresar
        Button btnRegresar = new Button();
        btnRegresar.setText("  Regresar  ");
        btnRegresar.setFont(botonFont);
        btnRegresar.setBackground(new Color(102, 255, 153)); // #66FF99
        btnRegresar.setForeground(Color.WHITE);
        
        btnJugadores.addActionListener(e -> cardLayout.show(contenedorPantallas, "jugadores"));
        btnTodas.addActionListener(e -> cardLayout.show(contenedorPantallas, "todasPartidas"));
        btnDuracion.addActionListener(e -> cardLayout.show(contenedorPantallas, "porDuracion"));
        btnPorJugador.addActionListener(e -> cardLayout.show(contenedorPantallas, "porNombre"));
        
        btnRegresar.addActionListener(e -> {
            new JFramePresentacion().setVisible(true);
            dispose();  // << Cierra esta ventana JFrame directamente
        });

        menu.add(btnJugadores);
        menu.add(btnTodas);
        menu.add(btnDuracion);
        menu.add(btnPorJugador);
        menu.add(btnRegresar);

        return menu;
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BaseDeDatos().setVisible(true);
        });
    }
}
