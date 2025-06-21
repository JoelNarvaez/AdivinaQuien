/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas;

import Estilos.Button;
import Estilos.MyTextField;
import Estilos.RoundedTextField;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import javax.swing.Timer;
import net.miginfocom.swing.MigLayout;
import static pantallas.PersonajeDisney.elegirPersonajesAleatorios;
import pantallas.ConexionBD; // o donde esté el método insertarPartida
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;





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
    private Cliente cliente;
    private boolean esMiTurno = false;
    private JLabel turnoLabel;
    private JTextArea areaPreguntas;
    private boolean deboResponder = false;
    private boolean esPrimerTurno = true;
    private JComboBox<String> preguntasCombo;
    private RoundedTextField preguntaInput;
    private Button enviarBtn;
    private PersonajeDisney miPersonajeSecreto;
    private int intentosRestantes = 3;
    private JLabel intentos;
    private String oponente;
    boolean gano = false;
    int cont = 0 ;// AAAA
       
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
    
   public JFrameGameScreen(Jugador jugador, PersonajeDisney[] seleccionados, Cliente cliente, String nombreOponente) {
    this.jugador = jugador;
    this.seleccionados = seleccionados;
    this.cliente = cliente;
    this.oponente = nombreOponente;
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
    continuar.setText("Continuar");
    bienvenidaPanel.add(continuar, "split 2, sizegroup btn, gapx 20, gapy 0, w 200, h 45, gapy 30");

    // Jugar YA button
    Button pantalla = new Button();
    pantalla.setFont(new Font("Century Gothic", Font.BOLD, 20));
    pantalla.setBackground(new Color(204, 102, 255));
    pantalla.setForeground(Color.WHITE);
    pantalla.setText("Escoger en tablero");
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
        miPersonajeSecreto = personajeAleatorio;
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
    miPersonajeSecreto = seleccionado;
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
    // Validar que al menos un radio button esté seleccionado
    if (!rbAleatorio.isSelected() && !rbNombre.isSelected()) {
        JOptionPane.showMessageDialog(this,
                "Debes seleccionar una opción: Aleatorio o Escoger por nombre.",
                "Opción no seleccionada",
                JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Validar que ya se haya elegido un personaje
    if (miPersonajeSecreto == null) {
        JOptionPane.showMessageDialog(this,
                "Debes confirmar tu personaje antes de continuar.",
                "Personaje no seleccionado",
                JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Pasar al juego
    CardLayout c = (CardLayout) (cards.getLayout());
    c.show(cards, "Juego");
    faseSeleccion = false;
    seleccionHecha = true;
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
   
   public void marcarMiTurno() {
    esMiTurno = true;
     SwingUtilities.invokeLater(() -> {
         turnoLabel.setText("¡Es tu turno!");
         // Sonido o animación opcional
         Toolkit.getDefaultToolkit().beep();
     });
   }

    public void marcarEspera() {
       esMiTurno = false;
       SwingUtilities.invokeLater(() -> turnoLabel.setText("Esperando turno..."));
    }

    private JPanel crearPanelJuego() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Inicializar fecha y hora
        setTitle("ADIVINA QUIÉN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);

        Color azulFuerte = new Color(0xA3, 0xD0, 0xF1);
        Color azulMedio = new Color(0xD4, 0xEB, 0xFD);
        Color celeste = new Color(0x97, 0xE9, 0xFF);
        Color lila = new Color(0xDC, 0xBB, 0xEB);
        Color btnColor = new Color (0xD4, 0xFB, 0xED);

        fechaHoraLabel = new JLabel();
        fechaHoraLabel.setFont(new Font("Arial", Font.BOLD, 16));
        fechaHoraLabel.setForeground(Color.WHITE);
        actualizarFechaHora();
        Timer timer = new Timer(1000, e -> actualizarFechaHora());
        timer.start();

        intentos = new JLabel("Intentos: " + Integer.toString(intentosRestantes));
        intentos.setFont(new Font("Century Gothic", Font.BOLD, 16));
        intentos.setForeground(Color.BLACK);
        
        cronometroLabel = new JLabel("Tiempo: 00:00");
        cronometroLabel.setFont(new Font("Arial", Font.BOLD, 16));
        cronometroLabel.setForeground(Color.WHITE);
        Timer cronometroTimer = new Timer(1000, e -> actualizarCronometro());
        cronometroTimer.start();

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(azulFuerte);
        topPanel.setPreferredSize(new Dimension(0, 70));

        Button salirBtn = new Button();
        salirBtn.setText("  ?  ");
        salirBtn.setFont(new Font("Arial", Font.BOLD, 14));
        salirBtn.setBackground(Color.RED);
        salirBtn.setForeground(Color.WHITE);

        salirBtn.addActionListener(e -> {
            JDialog dialogo = new JDialog((JFrame) SwingUtilities.getWindowAncestor(salirBtn), "Opciones del juego", true);
            dialogo.setSize(220, 370);
            dialogo.getContentPane().setBackground(btnColor);
            dialogo.setLayout(new BorderLayout());

            JPanel contenedor = new JPanel();
            contenedor.setBackground(azulMedio); 
            contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
            contenedor.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

            String[][] opciones = {
                {"musica.png", "Música"},
                {"menu.png", "Perfil"},
                {"pelota.png", "Instrucciones"},
                {"menu.png", "Menú"},
                {"cancelar.png", "Cancelar"}
            };

            for (String[] opcion : opciones) {
                String ruta = "/Imagenes/disenoPantallas/" + opcion[0];
                String texto = opcion[1];

                JPanel opcionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
                opcionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                opcionPanel.setOpaque(false);
                opcionPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // margen interno

                JButton btn = new JButton();
                btn.setPreferredSize(new Dimension(40, 40));
                btn.setMaximumSize(new Dimension(40, 40));
                btn.setMinimumSize(new Dimension(40, 40));
                btn.setContentAreaFilled(false);
                btn.setFocusPainted(false);
                btn.setBorderPainted(false);

                java.net.URL url = getClass().getResource(ruta);
                if (url != null) {
                    ImageIcon icono = new ImageIcon(url);
                    Image img = icono.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                    btn.setIcon(new ImageIcon(img));
                }

                JLabel etiqueta = new JLabel(texto);
                etiqueta.setFont(new Font("Cambria", Font.BOLD, 18));
                etiqueta.setForeground(Color.BLACK);

                opcionPanel.add(btn);
                opcionPanel.add(etiqueta);

                switch (texto) {
                    case "Música":
                        btn.addActionListener(ev -> {
                            // pausarMusica();
                            dialogo.dispose();
                        });
                        break;
                    case "Perfil":
                        btn.addActionListener(ev -> {
                            // mostrarPerfil();
                            dialogo.dispose();
                        });
                        break;
                    case "Instrucciones":
                        btn.addActionListener(ev -> {
                            // mostrarInstrucciones();
                            dialogo.dispose();
                        });
                        break;
                    case "Menú":
                        btn.addActionListener(ev -> {
                            // irAlMenuPrincipal();
                            dialogo.dispose();
                        });
                        break;
                    case "Cancelar":
                        btn.addActionListener(ev -> dialogo.dispose());
                        break;
                }

                contenedor.add(opcionPanel);
            }

            dialogo.add(contenedor, BorderLayout.CENTER);
            dialogo.setLocationRelativeTo(salirBtn);
            dialogo.setVisible(true);
        });

        JPanel leftTopPanel = new JPanel();
        leftTopPanel.setLayout(new BoxLayout(leftTopPanel, BoxLayout.Y_AXIS));
        leftTopPanel.setBackground(azulFuerte);
        leftTopPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
       
        salirBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        leftTopPanel.add(Box.createVerticalGlue());
        leftTopPanel.add(salirBtn);
        leftTopPanel.add(Box.createVerticalGlue());
        
        topPanel.add(leftTopPanel, BorderLayout.WEST);
        
        // PANEL PERSONAJES
        JPanel personajesPanel = new JPanel(new GridLayout(4, 6, 10, 10));
        personajesPanel.setBackground(azulMedio);
        personajesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < 24; i++) {
            final int idx = i;
            PersonajeDisney personaje = seleccionados[i];
            personajeButtons[i] = new JButton(); // SIEMPRE inicializa el botón aquí

            java.net.URL urlSigno = getClass().getResource("/Imagenes/disenoPantallas/signo.png");

            if (personaje == null) {
                personajeButtons[i].setText("?");
                personajeButtons[i].setToolTipText("Vacío");
            } else {
                java.net.URL url = getClass().getResource(personaje.getRutaImagen());
                if (url != null) {
                    ImageIcon icon = new ImageIcon(url);
                    Image original = icon.getImage();
                    personajeButtons[i].putClientProperty("originalImage", original); // Guardar imagen original

                    // Escalar imagen al tamaño actual del botón
                    Image scaled = original.getScaledInstance(
                        personajeButtons[i].getPreferredSize().width,
                        personajeButtons[i].getPreferredSize().height,
                        Image.SCALE_SMOOTH
                    );
                    personajeButtons[i].setIcon(new ImageIcon(scaled));

                    // Agregar listener para escalar cuando el botón cambie de tamaño
                    personajeButtons[i].addComponentListener(new ComponentAdapter() {
                        @Override
                        public void componentResized(ComponentEvent e) {
                            Image imgOriginal = (Image) personajeButtons[idx].getClientProperty("originalImage");
                            if (imgOriginal != null) {
                                int w = personajeButtons[idx].getWidth();
                                int h = personajeButtons[idx].getHeight();
                                Image scaled = imgOriginal.getScaledInstance(w, h, Image.SCALE_SMOOTH);
                                personajeButtons[idx].setIcon(new ImageIcon(scaled));
                            }
                        }
                    });
                } else {
                    personajeButtons[i].setText("?");
                }
                personajeButtons[i].setToolTipText(personaje.getNombre());
                personajeButtons[i].putClientProperty("personaje", personaje);
            }

            personajeButtons[i].setPreferredSize(new Dimension(100, 100));
            personajeButtons[i].setBackground(lila);

            personajeButtons[i].addActionListener(e -> {
                if (faseSeleccion && !seleccionHecha) {
                    if (personajeButtons[idx].getClientProperty("personaje") == null) return;
                    PersonajeDisney seleccionado = (PersonajeDisney) personajeButtons[idx].getClientProperty("personaje");
                    miPersonajeSecreto = seleccionado; // ← GUARDAR EL PERSONAJE SECRETO
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
                        if (urlSigno != null) {
                            ImageIcon iconSigno = new ImageIcon(urlSigno);
                            Image imgSigno = iconSigno.getImage().getScaledInstance(
                                personajeButtons[idx].getWidth(),
                                personajeButtons[idx].getHeight(),
                                Image.SCALE_SMOOTH
                            );
                            personajeButtons[idx].setIcon(new ImageIcon(imgSigno));
                            personajeButtons[idx].setText("");
                        } else {
                            personajeButtons[idx].setIcon(null);
                            personajeButtons[idx].setText("?");
                        }
                    } else {
                        PersonajeDisney p = (PersonajeDisney) personajeButtons[idx].getClientProperty("personaje");
                        if (p != null) {
                            java.net.URL url2 = getClass().getResource(p.getRutaImagen());
                            if (url2 != null) {
                                ImageIcon icon2 = new ImageIcon(url2);
                                Image original = icon2.getImage();
                                personajeButtons[idx].putClientProperty("originalImage", original);
                                int w = personajeButtons[idx].getWidth();
                                int h = personajeButtons[idx].getHeight();
                                Image scaled = original.getScaledInstance(w, h, Image.SCALE_SMOOTH);
                                personajeButtons[idx].setIcon(new ImageIcon(scaled));
                                personajeButtons[idx].setText("");

                                // Verifica si ya se agregó un listener para evitar duplicados
                                if (personajeButtons[idx].getComponentListeners().length == 0) {
                                    personajeButtons[idx].addComponentListener(new ComponentAdapter() {
                                        @Override
                                        public void componentResized(ComponentEvent e) {
                                            Image imgOriginal = (Image) personajeButtons[idx].getClientProperty("originalImage");
                                            if (imgOriginal != null) {
                                                int w = personajeButtons[idx].getWidth();
                                                int h = personajeButtons[idx].getHeight();
                                                Image scaled = imgOriginal.getScaledInstance(w, h, Image.SCALE_SMOOTH);
                                                personajeButtons[idx].setIcon(new ImageIcon(scaled));
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
            });

            personajesPanel.add(personajeButtons[i]);
        }

        
        JComboBox<PersonajeDisney> personajeCombo = new JComboBox<>(seleccionados);
        personajeCombo.setMaximumSize(new Dimension(250,30));
        personajeCombo.setPreferredSize(new Dimension(250,30));

        Button adivinarBtnTop = new Button();
        adivinarBtnTop.setText("Adivinar");
        adivinarBtnTop.setFont(new Font("Arial", Font.BOLD, 14));
        adivinarBtnTop.setBackground(new Color(0x55, 0x7D, 0xC2));
        adivinarBtnTop.setForeground(Color.WHITE);
        adivinarBtnTop.setPreferredSize(new Dimension(100, 30));
        adivinarBtnTop.setMaximumSize(new Dimension(100, 30));
        
        adivinarBtnTop.addActionListener(e -> {
            if (!esMiTurno || (!esPrimerTurno && !deboResponder)){
                JOptionPane.showMessageDialog(this, "Primero responde la pregunta del oponente.");
                return;
            }

            PersonajeDisney seleccionado = (PersonajeDisney) personajeCombo.getSelectedItem();
            if (seleccionado == null) {
                JOptionPane.showMessageDialog(this, "Selecciona un personaje.");
                return;
            }

            // Restar intento
            intentosRestantes--;
            intentos.setText("Intentos restantes: " + intentosRestantes);
            String nombre = seleccionado.getNombre();
            cliente.enviarMensaje("adivinar:" + nombre);
            areaPreguntas.append("Tú (adivinaste): ¿Es " + nombre + "?\n");
            
            

            // No termina turno aún, espera respuesta del oponente
            habilitarPregunta(false);
            esMiTurno = false;
            cliente.enviarMensaje("turnoFinalizado");
            
            if (intentosRestantes == 0) {
                areaPreguntas.append("Tú: Te acabste tus 3 intentos");
                mostrarPantallaAnimo();
                cliente.enviarMensaje("¡Ganaste!");
                registrarPartida(oponente, miPersonajeSecreto.getRutaImagen());
                actualizarDatosJugador(jugador, gano, intentosRestantes, miPersonajeSecreto.getNombre());
            }
        });
        
        JPanel rightTopPanel = new JPanel();
        rightTopPanel.setLayout(new BoxLayout(rightTopPanel, BoxLayout.X_AXIS));
        rightTopPanel.setBackground(azulFuerte);
        rightTopPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 20));
        rightTopPanel.add(Box.createHorizontalGlue());
        rightTopPanel.add(intentos);
        rightTopPanel.add(Box.createHorizontalStrut(23));
        rightTopPanel.add(personajeCombo);
        rightTopPanel.add(Box.createHorizontalStrut(15));
        rightTopPanel.add(adivinarBtnTop);
        rightTopPanel.add(Box.createHorizontalStrut(50));
        rightTopPanel.add(cronometroLabel);
        rightTopPanel.add(Box.createHorizontalStrut(15));
        rightTopPanel.add(fechaHoraLabel);
        rightTopPanel.add(Box.createHorizontalStrut(10));

        topPanel.add(rightTopPanel, BorderLayout.EAST);

        JPanel tituloPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        tituloPanel.setOpaque(false);

        JLabel titulo = new JLabel("¿ADIVINA QUIÉN?");
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/disneyTitulo.png"));
        Image img = logoIcon.getImage().getScaledInstance(60, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(img);
        
        JLabel logoLabel = new JLabel(scaledIcon);
       
        tituloPanel.add(titulo);
        tituloPanel.add(logoLabel);
        
        topPanel.add(tituloPanel, BorderLayout.CENTER);

        JPanel centroPanel = new JPanel(new BorderLayout());
        centroPanel.setBackground(celeste);
        centroPanel.add(personajesPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(lila);
        rightPanel.setPreferredSize(new Dimension(350, 0));

        JPanel contenidoDerecho = new JPanel();
        contenidoDerecho.setLayout(new BoxLayout(contenidoDerecho, BoxLayout.Y_AXIS));
        contenidoDerecho.setOpaque(false);
        contenidoDerecho.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        contenidoDerecho.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        rightPanel.add(contenidoDerecho, BorderLayout.NORTH);
        
        JLabel nicknameLabel = new JLabel("Nickname: " + jugador.getNickname());
        turnoLabel = new JLabel("");
        personajeLabel = new JLabel("Eres: " );
        nicknameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        turnoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        personajeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        personajeImagen = new JLabel();
        personajeImagen.setPreferredSize(new Dimension(200, 200));
        personajeImagen.setMaximumSize(new Dimension(200, 200));
        personajeImagen.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        personajeImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        Font labelFont = new Font("Arial", Font.BOLD, 18);
        Color labelColor = Color.WHITE;
        for (JLabel label : new JLabel[]{nicknameLabel, turnoLabel, personajeLabel}) {
            label.setFont(labelFont);
            label.setForeground(labelColor);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        personajeImagen.setAlignmentX(Component.CENTER_ALIGNMENT);

        preguntasCombo = new JComboBox<>();
            for (String pregunta : ConexionBD.obtenerPreguntasActivas()) {
                preguntasCombo.addItem(pregunta);
            }
        preguntasCombo.setFont(new Font("Arial", Font.BOLD, 14));
        preguntasCombo.setMaximumSize(new Dimension(300, 35));
        preguntasCombo.setAlignmentX(Component.CENTER_ALIGNMENT);
        

        // Crear el área de texto
        areaPreguntas = new JTextArea(10, 40);
        areaPreguntas.setText("Tu oponente es: " + oponente + "\n\n");
        areaPreguntas.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        areaPreguntas.setLineWrap(true);
        areaPreguntas.setWrapStyleWord(true);
        areaPreguntas.setEditable(false); // ← buena práctica para mostrar mensajes
        areaPreguntas.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));

        // Crear el scroll pane
        JScrollPane scrollPreguntas = new JScrollPane(areaPreguntas);
        scrollPreguntas.setMaximumSize(new Dimension(1000, 500));
        scrollPreguntas.setPreferredSize(new Dimension(1000, 500));
        scrollPreguntas.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPreguntas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPreguntas.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        preguntaInput = new RoundedTextField(25);
        preguntaInput.setFont(new Font("Arial", Font.PLAIN, 14));
        preguntaInput.setMaximumSize(new Dimension(180, 40));
        
        preguntasCombo.addActionListener(e -> {
            String seleccionada = (String) preguntasCombo.getSelectedItem();
            if (seleccionada != null) {
                preguntaInput.setText(seleccionada);
            }
        });

        enviarBtn = new Button();
        enviarBtn.setText("  Enviar  ");
        enviarBtn.setFont(new Font("Arial", Font.BOLD, 14));
        enviarBtn.setBackground(new Color(0xB1, 0x67, 0xD1));
        enviarBtn.setForeground(Color.WHITE);
        enviarBtn.setMaximumSize(new Dimension(110, 40));

        JPanel enviarPanel = new JPanel();
        enviarPanel.setLayout(new BoxLayout(enviarPanel, BoxLayout.X_AXIS));
        enviarPanel.setOpaque(false);
        enviarPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        enviarPanel.add(preguntaInput);
        enviarPanel.add(Box.createHorizontalStrut(10));
        enviarPanel.add(enviarBtn);

        enviarBtn.addActionListener(e -> {
    if (!esMiTurno) {
        JOptionPane.showMessageDialog(this, "Espera tu turno.");
        return;
    }

    // Solo pedir respuesta si NO es el primer turno
    if (!esMiTurno || (!esPrimerTurno && !deboResponder)){
        JOptionPane.showMessageDialog(this, "Primero debes responder la pregunta del oponente.");
        return;
    }

    String texto = preguntaInput.getText().trim();
    if (!texto.isEmpty()) {
        cliente.enviarMensaje("mensaje:" + texto);
        areaPreguntas.append("Tú (pregunta): " + texto + "\n\n"); // ← cambio aquí
        areaPreguntas.setCaretPosition(areaPreguntas.getDocument().getLength());
        preguntaInput.setText("");

        // Fin del turno
        cliente.enviarMensaje("turnoFinalizado");
        esPrimerTurno = false;
        deboResponder = false;
        habilitarPregunta(false);
    }
    });
        
        cliente.setOnMensaje(texto -> SwingUtilities.invokeLater(() -> {
            if (texto.startsWith("adivinar:")) {
                String nombre = texto.substring(9);
                areaPreguntas.append("Oponente (adivinó): ¿Es " + nombre + "?\n");

                if (miPersonajeSecreto.getNombre() != null && miPersonajeSecreto.getNombre().equalsIgnoreCase(nombre)) {
                    // Adivinó correctamente
                    System.out.println(miPersonajeSecreto);
                    gano = true;
                    areaPreguntas.append("Oponente (adivinó): Acertó \n\n");
                    cliente.enviarMensaje("¡Ganaste!");

                    mostrarPantallaAnimo();
                    registrarPartida(oponente, miPersonajeSecreto.getRutaImagen());
                    actualizarDatosJugador(jugador, gano, intentosRestantes, miPersonajeSecreto.getNombre());
                } else {
                    cliente.enviarMensaje("mensaje:No");
                    areaPreguntas.append("Oponente (adivinó): No acertó \n\n");
                    System.out.println(miPersonajeSecreto);
                    habilitarPregunta(true);
                    deboResponder = true;
                }
                return;
            }

            if (texto.equalsIgnoreCase("¡Ganaste!")) {
                areaPreguntas.append("Oponente: Gasto sus 3 oportunidades\n");
                mostrarPantallaFelicidades();
                gano = true;
                return;
            }

            if (!texto.equalsIgnoreCase("Sí") && !texto.equalsIgnoreCase("No") && !texto.equalsIgnoreCase("turnoFinalizado")) {
                areaPreguntas.append("Oponente (pregunta): " + texto + "\n");
                mostrarDialogoRespuesta(texto);
                
            } else if (texto.equalsIgnoreCase("Sí") || texto.equalsIgnoreCase("No")) {
                areaPreguntas.append("Oponente (respuesta): " + texto + "\n\n");
            }
        }));
        
        contenidoDerecho.add(nicknameLabel);
        contenidoDerecho.add(Box.createVerticalStrut(8));
        contenidoDerecho.add(turnoLabel);
        contenidoDerecho.add(Box.createVerticalStrut(8));
        contenidoDerecho.add(personajeImagen);
        contenidoDerecho.add(Box.createVerticalStrut(20));
        contenidoDerecho.add(preguntasCombo);
        contenidoDerecho.add(Box.createVerticalStrut(10));
        contenidoDerecho.add(scrollPreguntas);
        contenidoDerecho.add(Box.createVerticalStrut(10));
        contenidoDerecho.add(enviarPanel);
        contenidoDerecho.add(Box.createVerticalStrut(10));
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centroPanel, BorderLayout.CENTER);
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
    
    private void mostrarDialogoRespuesta(String pregunta) {
        JDialog dialogo = new JDialog(this, "Responder pregunta", true);
        dialogo.setSize(350, 180);
        dialogo.setLayout(new BorderLayout());
        dialogo.setLocationRelativeTo(this);
        dialogo.getContentPane().setBackground(Color.WHITE);
        dialogo.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // impide cerrar con 'X'

        // Pregunta centrada
        JLabel preguntaLabel = new JLabel("<html><center>" + pregunta + "</center></html>", SwingConstants.CENTER);
        preguntaLabel.setFont(new Font("Arial", Font.BOLD, 16));
        preguntaLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        // Panel de botones
        JPanel botonesPanel = new JPanel(new FlowLayout());
        botonesPanel.setBackground(Color.WHITE);

        JButton btnSi = new JButton("Sí");
        JButton btnNo = new JButton("No");

        btnSi.setPreferredSize(new Dimension(100, 35));
        btnNo.setPreferredSize(new Dimension(100, 35));
        btnSi.setBackground(new Color(0x74, 0xE6, 0x86));
        btnNo.setBackground(new Color(0xFF, 0x70, 0x70));
        btnSi.setFont(new Font("Arial", Font.BOLD, 14));
        btnNo.setFont(new Font("Arial", Font.BOLD, 14));

        // Acciones
        btnSi.addActionListener(e -> {
            cliente.enviarMensaje("mensaje:Sí");
            areaPreguntas.append("Tú (respuesta): Sí\n\n");  // ← cambio aquí
            areaPreguntas.setCaretPosition(areaPreguntas.getDocument().getLength());
            dialogo.dispose();
            deboResponder = true;
            habilitarPregunta(true);
        });

        btnNo.addActionListener(e -> {
            cliente.enviarMensaje("mensaje:No");
            areaPreguntas.append("Tú (respuesta): No\n\n");  // ← cambio aquí
            areaPreguntas.setCaretPosition(areaPreguntas.getDocument().getLength());
            dialogo.dispose();
            deboResponder = true;
            habilitarPregunta(true);
        });
        
        // Agrega botones al panel
        botonesPanel.add(btnSi);
        botonesPanel.add(btnNo);

        // Agrega componentes al diálogo
        dialogo.add(preguntaLabel, BorderLayout.CENTER);
        dialogo.add(botonesPanel, BorderLayout.SOUTH);

        dialogo.setVisible(true); // << IMPORTANTE
    }


    private void habilitarPregunta(boolean habilitar) {
        preguntaInput.setEnabled(habilitar);
        enviarBtn.setEnabled(habilitar);
        preguntasCombo.setEnabled(habilitar);
    }

    private void mostrarPantallaFelicidades() {
        JOptionPane.showMessageDialog(this, "¡Felicidades, Ganaste!", "Ganaste", JOptionPane.INFORMATION_MESSAGE);
        // Aquí puedes abrir una pantalla propia si tienes JFrameFelicidades
    }

    private void mostrarPantallaAnimo() {
        JOptionPane.showMessageDialog(this, "¡Ánimo! Lo Hiciste bien.", "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
        // Aquí también puedes redirigir o cerrar el juego si deseas
    }

    private void registrarPartida(String ganador, String personajeGanador) {
        Partida partida = new Partida();
        partida.setJugador1(jugador.getNickname());
        partida.setJugador2(cliente.getNombreOponente()); // <- lo tienes que tener como variable
        partida.setGanador(ganador);
        partida.setPersonajeGanador(personajeGanador);

        // Usa lo que ya tienes
        partida.setFecha(new java.sql.Date(System.currentTimeMillis())); // <-- java.sql.Date

        int minutos = crono / 60;
        int segundos = crono % 60;
        Time duracion = Time.valueOf(String.format("00:%02d:%02d", minutos, segundos));
        partida.setDuracion(duracion);

        ConexionBD.insertarPartida(partida);
    }

    private void actualizarDatosJugador(Jugador jugador, boolean gano, int intentos, String personajeGanador) {
        jugador.setJugadorVS(cliente.getNombreOponente());
        jugador.setFechaPartida(new java.sql.Date(System.currentTimeMillis()));
        jugador.setTiempo(Time.valueOf(String.format("00:%02d:%02d", crono / 60, crono % 60)));
        jugador.setIntentos((intentos - 4) * -1);

        if (gano) {
            jugador.setVictorias(jugador.getVictorias() + 1);
            jugador.setRanking(jugador.getRanking() + 50);
        } else {
            jugador.setDerrotas(jugador.getDerrotas() + 1);
            jugador.setRanking(jugador.getRanking() - 50);
        }

        boolean actualizado = ConexionBD.actualizarJugador(jugador, jugador.getId());
        if (!actualizado) {
            System.out.println("No se pudo actualizar el jugador.");
        } else {
            System.out.println("Jugador actualizado correctamente.");
        }
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
