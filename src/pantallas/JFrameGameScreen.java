/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;



public class JFrameGameScreen extends javax.swing.JFrame {
    private JLabel fechaHoraLabel;
    private JButton[] personajeButtons = new JButton[24];
    private JLabel cronometroLabel;
    private int crono = 0;


    public JFrameGameScreen() {
        setTitle("Adivina Quién");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla completa
        
        // Inicializar etiqueta de fecha y hora
        fechaHoraLabel = new JLabel();
        fechaHoraLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        fechaHoraLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        actualizarFechaHora(); // Muestra la hora inicial

        // Timer para actualizar hora cada segundo
        Timer timer = new Timer(1000, e -> actualizarFechaHora());
        timer.start();
        
        // Inicializar etiqueta de cronómetro
        cronometroLabel = new JLabel("Cronómetro: 00:00");
        cronometroLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        cronometroLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // Timer para el cronómetro
        Timer cronometroTimer = new Timer(1000, e -> actualizarCronometro());
        cronometroTimer.start();


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
        // Panel de la derecha para la hora
        JPanel rightTopPanel = new JPanel();
        rightTopPanel.setLayout(new BoxLayout(rightTopPanel, BoxLayout.Y_AXIS));
        rightTopPanel.setBackground(fondoPrincipal);
        rightTopPanel.add(fechaHoraLabel);
        rightTopPanel.add(cronometroLabel);

        topPanel.add(rightTopPanel, BorderLayout.EAST);


        JPanel leftTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftTopPanel.setBackground(fondoPrincipal);
        JButton salirBtn = new JButton("Salir");
        leftTopPanel.add(salirBtn);
        
        JPanel adivinarPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        adivinarPanel.setBackground(fondoPrincipal);
        JComboBox<String> personajeCombo = new JComboBox<>(new String[]{"Elsa", "Aladdin", "Valiente", "Elastigirl"});
        JButton adivinarBtn = new JButton("Adivinar");
        adivinarPanel.add(personajeCombo);
        adivinarPanel.add(adivinarBtn);

        // Contenedor vertical: adivinarPanel arriba, personajesPanel abajo
        JPanel centroContenedor = new JPanel();
        centroContenedor.setLayout(new BorderLayout());
        centroContenedor.setBackground(fondoPrincipal);

        mainPanel.add(centroContenedor, BorderLayout.CENTER);
        
        topPanel.add(leftTopPanel, BorderLayout.WEST);
        topPanel.add(leftTopPanel, BorderLayout.WEST);
      
        // Panel personajes
        JPanel personajesPanel = new JPanel(new GridLayout(4, 6, 10, 10));
        personajesPanel.setBackground(fondoSecundario);
        for (int i = 0; i < 24; i++) {
            personajeButtons[i] = new JButton("?");
            personajeButtons[i].setPreferredSize(new Dimension(100, 100));
            personajeButtons[i].setBackground(colorBoton);
            personajesPanel.add(personajeButtons[i]);
        }
        
        centroContenedor.add(adivinarPanel, BorderLayout.NORTH);  // Parte superior del centro
        centroContenedor.add(personajesPanel, BorderLayout.CENTER); // Tablero de personajes

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

        // NUEVO: ComboBox con lista de preguntas
        JComboBox<String> preguntasComboBox = new JComboBox<>(new String[]{
            "¿Tu personaje tiene gafas?",
            "¿Es mujer?",
            "¿Tiene sombrero?",
            "¿Es rubio?",
            "¿Lleva accesorios?"
        });
        chatPanel.add(preguntasComboBox, BorderLayout.NORTH); // Lo añadimos arriba del área de chat

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

        // NUEVO panel debajo del input con botones "Sí" y "No"
        JPanel respuestaPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton siBtn = new JButton("Sí");
        JButton noBtn = new JButton("No");
        respuestaPanel.add(siBtn);
        respuestaPanel.add(noBtn);

        JPanel inputYRespuestaPanel = new JPanel();
        inputYRespuestaPanel.setLayout(new BoxLayout(inputYRespuestaPanel, BoxLayout.Y_AXIS));
        
        inputGroup.setMaximumSize(new Dimension(Integer.MAX_VALUE, inputGroup.getPreferredSize().height));
        respuestaPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, respuestaPanel.getPreferredSize().height));

        inputYRespuestaPanel.add(inputGroup);
        inputYRespuestaPanel.add(respuestaPanel);
        
        chatPanel.add(inputYRespuestaPanel, BorderLayout.SOUTH);


        JPanel infoYChatPanel = new JPanel();
        infoYChatPanel.setLayout(new BorderLayout(10, 10));
        infoYChatPanel.setBackground(fondoSecundario);
        infoYChatPanel.add(infoPanel, BorderLayout.NORTH);
        infoYChatPanel.add(chatPanel, BorderLayout.CENTER);

        rightPanel.add(infoYChatPanel, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(rightPanel, BorderLayout.EAST);

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
    private void actualizarFechaHora() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaHoraActual = sdf.format(new Date());
        fechaHoraLabel.setText(fechaHoraActual);
    }
    
    private void actualizarCronometro() {
    crono++;
    int minutos = crono / 60;
    int segundos = crono % 60;
    String tiempo = String.format("Tiempo: %02d:%02d", minutos, segundos);
    cronometroLabel.setText(tiempo);
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JFrameGameScreen());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelGameScreen;
    // End of variables declaration//GEN-END:variables
}
