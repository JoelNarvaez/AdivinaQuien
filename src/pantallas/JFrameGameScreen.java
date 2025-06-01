/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas;

import javax.swing.*;
import java.awt.*;


public class JFrameGameScreen extends javax.swing.JFrame {
    
    private JButton[] personajeButtons = new JButton[24];

    public JFrameGameScreen() {
        setTitle("Adivina Quién");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla completa

        // Colores 
        Color fondoPrincipal = new Color(245, 245, 255);
        Color fondoSecundario = new Color(200, 220, 255);
        Color colorBoton = new Color(180, 200, 255);
        
        // Layout principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(fondoPrincipal);

        // Panel superior
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(fondoPrincipal);

        JPanel leftTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftTopPanel.setBackground(fondoPrincipal);
        JButton salirBtn = new JButton("Salir");
        leftTopPanel.add(salirBtn);
        
        JPanel centerTopPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        centerTopPanel.setBackground(fondoPrincipal);
        centerTopPanel.setPreferredSize(new Dimension(650, 40));

        JComboBox<String> personajeCombo = new JComboBox<>(new String[]{"Elsa", "Aladdin", "Valiente", "Elastigirl"});
        JButton adivinarBtn = new JButton("Adivinar");
        centerTopPanel.add(personajeCombo);
        centerTopPanel.add(adivinarBtn);

        topPanel.add(leftTopPanel, BorderLayout.WEST);
        topPanel.add(centerTopPanel, BorderLayout.CENTER);

        
        // Panel personajes
        JPanel personajesPanel = new JPanel(new GridLayout(4, 6, 10, 10));
        personajesPanel.setBackground(fondoSecundario);
        for (int i = 0; i < 24; i++) {
            personajeButtons[i] = new JButton("?");
            personajeButtons[i].setPreferredSize(new Dimension(100, 100));
            personajeButtons[i].setBackground(colorBoton);
            personajesPanel.add(personajeButtons[i]);
        }

        // Panel derecho
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(300, 0));
        
        
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(fondoSecundario);

        JLabel nicknameLabel = new JLabel("Nickname: eluyuyuy");
        JLabel turnoLabel = new JLabel("Es tu turno");
        JLabel personajeLabel = new JLabel("Eres: Elastigirl");
        nicknameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        turnoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        personajeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel personajeImagen = new JLabel();
        personajeImagen.setPreferredSize(new Dimension(200, 200));
        personajeImagen.setMaximumSize(new Dimension(200, 200));
        personajeImagen.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        personajeImagen.setAlignmentX(Component.CENTER_ALIGNMENT);

        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(nicknameLabel);
        infoPanel.add(turnoLabel);
        infoPanel.add(personajeLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(personajeImagen);
        
        
        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout(5, 5));
        chatPanel.setBackground(fondoSecundario);

        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane chatScroll = new JScrollPane(chatArea);

        JPanel inputGroup = new JPanel();
        inputGroup.setLayout(new BorderLayout(5, 5));

        JTextField chatInput = new JTextField();
        JButton enviarBtn = new JButton("Enviar");

        inputGroup.add(chatInput, BorderLayout.CENTER);
        inputGroup.add(enviarBtn, BorderLayout.EAST);

        chatPanel.add(chatScroll, BorderLayout.CENTER);
        chatPanel.add(inputGroup, BorderLayout.SOUTH);

        JPanel infoYChatPanel = new JPanel();
        infoYChatPanel.setLayout(new BorderLayout(10, 10));
        infoYChatPanel.setBackground(fondoSecundario);
        infoYChatPanel.add(infoPanel, BorderLayout.NORTH);
        infoYChatPanel.add(chatPanel, BorderLayout.CENTER);

        rightPanel.add(infoYChatPanel, BorderLayout.CENTER);

        // Panel inferior
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(fondoPrincipal);
        bottomPanel.add(new JButton("Presentación"));
        bottomPanel.add(new JButton("Felicitación"));
        bottomPanel.add(new JButton("Ánimo"));

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(personajesPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelGameScreen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelGameScreen.setFont(new java.awt.Font("Bahnschrift", 0, 36)); // NOI18N
        jLabelGameScreen.setText("Pantalla de Juego");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(236, 236, 236)
                .addComponent(jLabelGameScreen, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(276, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabelGameScreen)
                .addContainerGap(400, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JFrameGameScreen());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelGameScreen;
    // End of variables declaration//GEN-END:variables
}
