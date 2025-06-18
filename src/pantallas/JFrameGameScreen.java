/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas;

import Estilos.Button;
import Estilos.MyTextField;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.Timer;
import net.miginfocom.swing.MigLayout;
import static pantallas.PersonajeDisney.elegirPersonajesAleatorios;



public class JFrameGameScreen extends javax.swing.JFrame {
    private JLabel fechaHoraLabel;
    private JButton[] personajeButtons = new JButton[24];
    private JLabel cronometroLabel;
    private int crono = 0;
    PersonajeDisney[] seleccionados;
    private JLabel personajeImagen;
    private boolean seleccionHecha = false;
    private boolean faseSeleccion = true; // Al principio se está escogiendo personaje
    private boolean[] cartaGirada = new boolean[24];
    private boolean personajeYaSeleccionadoEnBienvenida = false;
    private Jugador jugador;
    private JLabel personajeLabel;      

       
    public JFrameGameScreen(){
        
    }
   
    private void mostrarImagenSeleccionada(PersonajeDisney personaje, JLabel label, int w, int h) {
    if (personaje != null && personaje.getRutaImagen() != null) {
        java.net.URL imageURL = getClass().getResource(personaje.getRutaImagen());
        if (imageURL != null) {
            ImageIcon icon = new ImageIcon(imageURL);
            Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
            label.setText("");
        } else {
            label.setIcon(null);
            label.setText("Sin imagen");
        }
    } else {
        label.setIcon(null);
        label.setText("Sin imagen");
    }
}
    
    public JFrameGameScreen(Jugador jugador, PersonajeDisney[] personajes) { //añadir ademas de jugador un vector de PersonajeDisney de tamaño 24 que sea igual en los dos jugadores (supongo generalo en servidor)
    this.jugador = jugador;
    this.seleccionados = personajes; //aqui solo se hizo eso porque antes se utilizaba seleccionados pero local ahora se renueva con el constructor
    setTitle("Adivina Quién"); 
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setExtendedState(JFrame.MAXIMIZED_BOTH);

    // 1. PANEL DE BIENVENIDA
    JPanel bienvenidaPanel = new JPanel();
    bienvenidaPanel.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]10[]10[]push"));
    bienvenidaPanel.setBackground(new Color(240, 240, 250));

    // Título
    JLabel label = new JLabel("¿Cómo quieres escoger al personaje que van a adivinar?");
    label.setFont(new Font("Century Gothic", Font.BOLD, 26));
    label.setForeground(new Color(25, 118, 210));
    bienvenidaPanel.add(label);

    // Radio buttons
    JRadioButton rbAleatorio = new JRadioButton("Aleatorio");
    rbAleatorio.setFont(new Font("Century Gothic", Font.PLAIN, 20));
    rbAleatorio.setBackground(new Color(240, 240, 250));

    JRadioButton rbNombre = new JRadioButton("Escoger por nombre");
    rbNombre.setFont(new Font("Century Gothic", Font.PLAIN, 20));
    rbNombre.setBackground(new Color(240, 240, 250));

    ButtonGroup grupoOpciones = new ButtonGroup();
    grupoOpciones.add(rbAleatorio);
    grupoOpciones.add(rbNombre);

    bienvenidaPanel.add(rbAleatorio, "align center");
    bienvenidaPanel.add(rbNombre, "align center");

    // Label: escogiendo personaje aleatorio
    JLabel lblEscogiendo = new JLabel("Escogiendo personaje aleatoriamente...");
    lblEscogiendo.setFont(new Font("Century Gothic", Font.PLAIN, 18));
    lblEscogiendo.setForeground(new Color(120, 120, 120));
    lblEscogiendo.setVisible(false);
    
    JLabel lblEscoge = new JLabel("Escoge un personaje de la lista");
    lblEscoge.setFont(new Font("Century Gothic", Font.PLAIN, 18));
    lblEscoge.setForeground(new Color(120, 120, 120));
    lblEscoge.setVisible(false);

    bienvenidaPanel.add(lblEscogiendo, "align center, gapy 10");

    // Imagen grande para modo aleatorio
    JLabel lblImagen = new JLabel();
    lblImagen.setPreferredSize(new Dimension(200, 200));
    lblImagen.setHorizontalAlignment(JLabel.CENTER);
    lblImagen.setVerticalAlignment(JLabel.CENTER);
    lblImagen.setVisible(false);

    // Combo pequeño
    JComboBox<PersonajeDisney> comboPersonajes = new JComboBox<>(seleccionados); //aqui esta bien todo aqui recibiria el DisneyPersonajes de 24 personajes que se recibio en los parametros
    comboPersonajes.setFont(new Font("Century Gothic", Font.PLAIN, 16));
    comboPersonajes.setPreferredSize(new Dimension(170, 38));
    comboPersonajes.setMaximumSize(new Dimension(170, 38));
    comboPersonajes.setVisible(false);

    // Label imagen debajo del combo
    JLabel lblImagenCombo = new JLabel();
    lblImagenCombo.setPreferredSize(new Dimension(250, 250));
    lblImagenCombo.setHorizontalAlignment(JLabel.CENTER);
    lblImagenCombo.setVerticalAlignment(JLabel.CENTER);
    lblImagenCombo.setVisible(false);

    // Panel vertical combo + imagen
    JPanel panelCombo = new JPanel();
    panelCombo.setOpaque(false);
    panelCombo.setLayout(new BoxLayout(panelCombo, BoxLayout.Y_AXIS));
    comboPersonajes.setAlignmentX(Component.CENTER_ALIGNMENT);
    lblImagenCombo.setAlignmentX(Component.CENTER_ALIGNMENT);
    lblEscoge.setAlignmentX(Component.CENTER_ALIGNMENT);
    panelCombo.add(lblEscoge);
    panelCombo.add(Box.createVerticalStrut(20)); // <-- Espacio de 15 píxeles
    panelCombo.add(comboPersonajes);
    panelCombo.add(Box.createVerticalStrut(10));
    panelCombo.add(lblImagenCombo);

    // CardLayout para mostrar imagen O combo+imagen
    JPanel panelContenedor = new JPanel(new CardLayout());
    panelContenedor.setOpaque(false);
    panelContenedor.setPreferredSize(new Dimension(300, 300));
    panelContenedor.add(lblImagen, "IMAGEN");
    panelContenedor.add(panelCombo, "COMBO");

    bienvenidaPanel.add(panelContenedor, "align center, gapy -65!");

   // Crear perfil button
    Button continuar = new Button();
    continuar.setFont(new Font("Century Gothic", Font.BOLD, 24));
    continuar.setBackground(new Color(255, 153, 0));
    continuar.setForeground(Color.WHITE);
    continuar.setText("Contunuar");
    bienvenidaPanel.add(continuar, "split 2, sizegroup btn, gapx 20, gapy 0, w 200, h 45, gapy 30");

    // Jugar YA button
    Button pantalla = new Button();
    pantalla.setFont(new Font("Century Gothic", Font.BOLD, 20));
    pantalla.setBackground(new Color(204, 102, 255));
    pantalla.setForeground(Color.WHITE);
    pantalla.setText("Ecoger en tablero");
    bienvenidaPanel.add(pantalla, "sizegroup btn, w 200, h 45, gapy 30");

    // --------- Lógica ---------
    CardLayout cl = (CardLayout) (panelContenedor.getLayout());

    rbAleatorio.addActionListener(e -> {
        Random rand = new Random();
    int idxAleatorio = rand.nextInt(seleccionados.length);
    PersonajeDisney personajeAleatorio = seleccionados[idxAleatorio];
    mostrarImagenSeleccionada(personajeAleatorio, lblImagen, 250, 250);
    mostrarImagenSeleccionada(personajeAleatorio, personajeImagen, 200, 200);
    personajeLabel.setText("Eres: " + personajeAleatorio.getNombre());
    personajeYaSeleccionadoEnBienvenida = true; // <--- NUEVO

    lblEscogiendo.setVisible(true);
    cl.show(panelContenedor, "IMAGEN");
    lblImagen.setVisible(true);
    comboPersonajes.setVisible(false);
    lblImagenCombo.setVisible(false);
    });

    rbNombre.addActionListener(e -> {
    lblEscoge.setVisible(true);
    lblEscogiendo.setVisible(false);
    cl.show(panelContenedor, "COMBO");
    comboPersonajes.setVisible(true);
    lblImagen.setVisible(false);
    lblImagenCombo.setVisible(true);

    });


   comboPersonajes.addActionListener(e -> {
    PersonajeDisney seleccionado = (PersonajeDisney) comboPersonajes.getSelectedItem();
    mostrarImagenSeleccionada(seleccionado, lblImagenCombo, 250, 250);
    mostrarImagenSeleccionada(seleccionado, personajeImagen, 200, 200);
    personajeLabel.setText("Eres: " + seleccionado.getNombre());
    personajeYaSeleccionadoEnBienvenida = true; // <--- NUEVO
    });

    // 2. PANEL DEL JUEGO COMPLETO
    JPanel juegoPanel = crearPanelJuego();

    // 3. CARDLAYOUT: Contenedor de paneles (bienvenida y juego)
    JPanel cards = new JPanel(new CardLayout());
    cards.add(bienvenidaPanel, "Bienvenida");
    cards.add(juegoPanel, "Juego");

    // Acción para cambiar de pantalla al tablero
    continuar.addActionListener(e -> {
        CardLayout c = (CardLayout) (cards.getLayout());
        c.show(cards, "Juego");
        // Ya se eligió personaje, no pedir selección ni permitir seleccionar en el tablero
        faseSeleccion = false;
        seleccionHecha = true; // así el tablero ya no permite escoger personaje
    });
    
    pantalla.addActionListener(e -> {
        CardLayout c = (CardLayout) (cards.getLayout());
        c.show(cards, "Juego");
        // Solo mostrar JOptionPane si NO se eligió personaje por otro método
            JOptionPane.showMessageDialog(this,
                "Escoge tu personaje haciendo clic en una carta del tablero",
                "Selecciona tu personaje",
                JOptionPane.INFORMATION_MESSAGE);
            // Solo en este caso, faseSeleccion debe ser true
            faseSeleccion = true;
            seleccionHecha = false;
    });

    setLayout(new BorderLayout());
    add(cards, BorderLayout.CENTER);
    setVisible(true);

    }

    private JPanel crearPanelJuego() {
        // *** AQUÍ VA TODO TU CÓDIGO DEL LAYOUT DEL JUEGO ***
        // Pego aquí sólo la estructura principal, pon tu código aquí (o usa lo que tienes arriba)
        // Reutiliza las variables: fechaHoraLabel, personajeButtons, cronometroLabel

        // Inicializar fecha y hora
        fechaHoraLabel = new JLabel();
        fechaHoraLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        fechaHoraLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        actualizarFechaHora();

        Timer timer = new Timer(1000, e -> actualizarFechaHora());
        timer.start();

        cronometroLabel = new JLabel("Tiempo: 00:00");
        cronometroLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        cronometroLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        Timer cronometroTimer = new Timer(1000, e -> actualizarCronometro());
        cronometroTimer.start();

        // Colores
        Color fondoPrincipal = new Color(245, 245, 255);
        Color fondoSecundario = new Color(200, 220, 255);
        Color colorBoton = new Color(180, 200, 255);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(fondoPrincipal);

        // PANEL SUPERIOR
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(fondoPrincipal);

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
        JComboBox<PersonajeDisney> personajeCombo = new JComboBox<>(seleccionados);
        JButton adivinarBtn = new JButton("Adivinar");
        adivinarPanel.add(personajeCombo);
        adivinarPanel.add(adivinarBtn);

        JPanel centroContenedor = new JPanel(new BorderLayout());
        centroContenedor.setBackground(fondoPrincipal);

        mainPanel.add(centroContenedor, BorderLayout.CENTER);

        topPanel.add(leftTopPanel, BorderLayout.WEST);

        // PANEL PERSONAJES

        JPanel personajesPanel = new JPanel(new GridLayout(4, 6, 10, 10));
        personajesPanel.setBackground(fondoSecundario);

   
        for (int i = 0; i < 24; i++) {
            PersonajeDisney personaje = seleccionados[i];
            personajeButtons[i] = new JButton(); // SIEMPRE inicializa el botón aquí

            if (personaje == null) {
                personajeButtons[i].setText("?");
                personajeButtons[i].setToolTipText("Vacío");
            } else {
                java.net.URL url = getClass().getResource(personaje.getRutaImagen());
                if (url != null) {
                    ImageIcon icon = new ImageIcon(url);
                    Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    personajeButtons[i].setIcon(new ImageIcon(img));
                } else {
                    personajeButtons[i].setText("?");
                }
                personajeButtons[i].setToolTipText(personaje.getNombre());
                personajeButtons[i].putClientProperty("personaje", personaje);
            }

            personajeButtons[i].setPreferredSize(new Dimension(100, 100));
            personajeButtons[i].setBackground(colorBoton);

            final int idx = i;
            personajeButtons[i].addActionListener(e -> {
                if (faseSeleccion && !seleccionHecha) {
                    if (personajeButtons[idx].getClientProperty("personaje") == null) return;
                    PersonajeDisney seleccionado = (PersonajeDisney) personajeButtons[idx].getClientProperty("personaje");
                    mostrarImagenSeleccionada(seleccionado, personajeImagen, 200, 200);
                    personajeLabel.setText("Eres: " + seleccionado.getNombre());
                    for (JButton btn : personajeButtons) btn.setEnabled(false);
                    seleccionHecha = true;
                    SwingUtilities.invokeLater(() -> {
                        faseSeleccion = false;
                        seleccionHecha = false;
                        for (JButton btn : personajeButtons) btn.setEnabled(true);
                    });
                } else if (!faseSeleccion) {
                    cartaGirada[idx] = !cartaGirada[idx];
                    if (cartaGirada[idx]) {
                        personajeButtons[idx].setIcon(null);
                        personajeButtons[idx].setText("?");
                    } else {
                        PersonajeDisney p = (PersonajeDisney) personajeButtons[idx].getClientProperty("personaje");
                        if (p != null) {
                            java.net.URL url2 = getClass().getResource(p.getRutaImagen());
                            if (url2 != null) {
                                ImageIcon icon2 = new ImageIcon(url2);
                                Image img2 = icon2.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                                personajeButtons[idx].setIcon(new ImageIcon(img2));
                                personajeButtons[idx].setText("");
                            }
                        }
                    }
                }
            });

            personajesPanel.add(personajeButtons[i]);
        }

        centroContenedor.add(adivinarPanel, BorderLayout.NORTH);
        centroContenedor.add(personajesPanel, BorderLayout.CENTER);

        // PANEL DERECHO
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(300, 0));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(fondoSecundario);

        JLabel nicknameLabel = new JLabel("Nickname: " + jugador.getNickname());
        JLabel turnoLabel = new JLabel("Es tu turno");
        personajeLabel = new JLabel("Eres: " );
        nicknameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        turnoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        personajeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        personajeImagen = new JLabel();
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

        JPanel chatPanel = new JPanel(new BorderLayout(5, 5));
        chatPanel.setBackground(fondoSecundario);

        JComboBox<String> preguntasComboBox = new JComboBox<>();
        for (String pregunta : ConexionBD.obtenerPreguntasActivas()) {
            preguntasComboBox.addItem(pregunta);
        }
        chatPanel.add(preguntasComboBox, BorderLayout.NORTH);

        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane chatScroll = new JScrollPane(chatArea);

        JPanel inputGroup = new JPanel(new BorderLayout(5, 5));

        JTextField chatInput = new JTextField();
        JButton enviarBtn = new JButton("Enviar");

        inputGroup.add(chatInput, BorderLayout.CENTER);
        inputGroup.add(enviarBtn, BorderLayout.EAST);

        chatPanel.add(chatScroll, BorderLayout.CENTER);

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

        JPanel infoYChatPanel = new JPanel(new BorderLayout(10, 10));
        infoYChatPanel.setBackground(fondoSecundario);
        infoYChatPanel.add(infoPanel, BorderLayout.NORTH);
        infoYChatPanel.add(chatPanel, BorderLayout.CENTER);

        rightPanel.add(infoYChatPanel, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        return mainPanel;
    }

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

    // Métodos de utilería para fecha/hora y cronómetro
   
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
