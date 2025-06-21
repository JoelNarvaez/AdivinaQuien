/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas;

import canciones.ReproductorMusica;
import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;
import java.io.File;

public class JFrameCreditos extends javax.swing.JFrame {
    private JFramePresentacion presentacion;
    ReproductorMusica reproductor = ReproductorMusica.getInstancia();

    public void setPresentacion(JFramePresentacion presentacion) {
        this.presentacion = presentacion;
    }
    
    public JFrameCreditos() {
        initComponentsCustom();
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    //@SuppressWarnings("unchecked")
    private void initComponentsCustom(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                
        configurarTeclaEnterParaPausarReanudarMusica();
        
        JPanel jPanelPrincipal = new JPanel(new BorderLayout());
        jPanelPrincipal.setBackground(new java.awt.Color(231,231,255));
        
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setOpaque(false);
        panelSuperior.add(new JLabel(new ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/EsqSupIzq.png"))), BorderLayout.WEST);
        panelSuperior.add(new JLabel(new ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/creditosb.png"))), BorderLayout.CENTER);
        panelSuperior.add(new JLabel(new ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/EsqSupDer.png"))), BorderLayout.EAST);

        JLayeredPane panelCentral = new JLayeredPane();
        panelCentral.setPreferredSize(new Dimension(1000, 500));
        panelCentral.setOpaque(false);
        
        agregarFotoConInfo(panelCentral, "/Imagenes/disenoPantallas/taniagm.png", "Tania Paola García Meza<br>Diseño de interfaz", 300, 50);
        agregarFotoConInfo(panelCentral, "/Imagenes/disenoPantallas/joelnm.png", "Joel Narváez Martínez<br>Base de  datos", 850, 50);
        agregarFotoConInfo(panelCentral, "/Imagenes/disenoPantallas/lorenzopl.png", "Lorenzo Antonio Pacheco Leyva<br>Configuración de Sockets", 1400, 50);
        agregarFotoConInfo(panelCentral, "/Imagenes/disenoPantallas/anard.png", "Ana Lorena Rosales Delgado<br>Diseño de interfaz", 500, 400);
        agregarFotoConInfo(panelCentral, "/Imagenes/disenoPantallas/marielvm.png", "Fatima Mariel Villalpando Mota<br>Diseño de interfaz", 1200, 400);
        
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setOpaque(false);
        
        JButton jButtonCerrar = new JButton(new ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/volver.png")));
        jButtonCerrar.setContentAreaFilled(false);
        jButtonCerrar.setBorderPainted(false);
        jButtonCerrar.addActionListener(evt -> {
            presentacion.setVisible(true);
            this.setVisible(false);
        });
        
        JButton jButtonMusic = new JButton(new ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/musicicon.png")));
        jButtonMusic.setContentAreaFilled(false);
        jButtonMusic.setBorderPainted(false);
        jButtonMusic.addActionListener(evt -> reproductor.alternarMusica());
        
        panelInferior.add(jButtonCerrar, BorderLayout.WEST);
        panelInferior.add(jButtonMusic, BorderLayout.EAST);
        panelInferior.add(new JLabel(new ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/datos.png"))), BorderLayout.CENTER);
        
        
        jPanelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        jPanelPrincipal.add(panelCentral, BorderLayout.CENTER);
        jPanelPrincipal.add(panelInferior, BorderLayout.SOUTH);
        
        getContentPane().add(jPanelPrincipal);
        pack();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1391, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1021, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void agregarFotoConInfo(JLayeredPane panel, String rutaImagen, String textoHTML, int x, int y){
        JLabel imagenLabel = new JLabel(new ImageIcon(getClass().getResource(rutaImagen)));
        imagenLabel.setBounds(x, y, 250, 270);
        imagenLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel infoPanel = new JPanel(null);
        infoPanel.setBounds(x, y, 200, 80);
        infoPanel.setBackground(new Color(255, 255, 255, 230));
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        infoPanel.setVisible(false);

        JLabel texto = new JLabel("<html><center>" + textoHTML + "</center></html>", SwingConstants.CENTER);
        texto.setBounds(10, 10, 180, 30);
        infoPanel.add(texto);

        JButton cerrarBtn = new JButton("X");
        cerrarBtn.setMargin(new Insets(0, 0, 0, 0));
        cerrarBtn.setBounds(170, 0, 30, 30);
        cerrarBtn.setFocusable(false);
        cerrarBtn.setFont(new Font("Arial", Font.BOLD, 12));
        cerrarBtn.setBackground(Color.LIGHT_GRAY);
        cerrarBtn.addActionListener(e -> infoPanel.setVisible(false));
        infoPanel.add(cerrarBtn);

        imagenLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                infoPanel.setVisible(true);
            }
        });

        panel.add(imagenLabel, Integer.valueOf(0));
        panel.add(infoPanel, Integer.valueOf(1));
    }
    
    private void configurarTeclaEnterParaPausarReanudarMusica() {
        JRootPane rootPane = this.getRootPane();

        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("ENTER"), "toggleMusica");

        rootPane.getActionMap().put("toggleMusica", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                reproductor.pausarOReanudar(); // método que debes tener en ReproductorMusica
            }
        });
    }


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new JFrameCreditos().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField jPasswordField1;
    // End of variables declaration//GEN-END:variables
}
