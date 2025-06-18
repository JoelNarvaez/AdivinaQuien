package componentes;

import Estilos.Button;
import Estilos.MyTextField;
import Estilos.RoundedTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;
import pantallas.Cliente; //Lorenzo
import pantallas.ConexionBD;
import pantallas.JFrameGameScreen;
import pantallas.JFrameRegistro;
import pantallas.Jugador;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {
    private Jugador jugador;
    private MyTextField nicknameField;
    private MyTextField edadField;
    private JLabel selectedAvatarLabel;
    private String selectedAvatar = null;

    private final String[] avatarFiles = {
        "/Imagenes/profileIcons/boss.png",
        "/Imagenes/profileIcons/lilo.png",
        "/Imagenes/profileIcons/mcqueen.jpg",
        "/Imagenes/profileIcons/mike.jpg",
        "/Imagenes/profileIcons/nemo.jpg",
        "/Imagenes/profileIcons/walle.jpg",
        "/Imagenes/profileIcons/enojo.jpg"
    };

private int startIndex = 0;
private final int VISIBLE_COUNT = 3;
private JButton[] avatarSlotButtons = new JButton[VISIBLE_COUNT];
private JButton leftArrowBtn;
private JButton rightArrowBtn;
private PanelCover panelCover; // Agrega esta variable

    public PanelLoginAndRegister() {
        initComponents();
        initRegister();
        initLogin();
        login.setVisible(false);
        register.setVisible(true);
    }
    
    public void setPanelCover(PanelCover panelCover) {
    this.panelCover = panelCover;
    }


  private void initRegister() {
    register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
    register.setBackground(Color.WHITE);
    
    JLabel registro = new JLabel("Registro de jugadores");
    registro.setFont(new Font("Century Gothic", 1, 28));
    registro.setForeground(new Color(135, 3, 251));
    register.add(registro);

    // Nickname
    JPanel nickPanel = new JPanel(new MigLayout("wrap 1", "[grow]", ""));
    nickPanel.setOpaque(false);

    JLabel nickLabel = new JLabel("Nickname:");
    nickLabel.setFont(new Font("Century Gothic", Font.BOLD, 28));
    nickPanel.add(nickLabel);

    nicknameField = new MyTextField();
    nicknameField.setFont(new Font("Century Gothic", Font.PLAIN, 28));
    nickPanel.add(nicknameField, "growx");

    register.add(nickPanel, "growx, align center");

    // Edad
    JPanel edadPanel = new JPanel(new MigLayout("wrap 1", "[grow]", "")); // wrap 1: un solo elemento por fila
    edadPanel.setOpaque(false);

    JLabel edadLabel = new JLabel("Edad:");
    edadLabel.setFont(new Font("Arial", Font.BOLD, 28));
    edadPanel.add(edadLabel);

    edadField = new MyTextField();
    edadField.setFont(new Font("Arial", Font.PLAIN, 24));
    edadPanel.add(edadField, "growx");

    register.add(edadPanel, "growx, align center");


    // Avatar Label
    JLabel avatarLabel = new JLabel("Escoge un avatar:");
    avatarLabel.setFont(new Font("Century Gothic", Font.BOLD, 28));
    avatarLabel.setForeground(new Color(128, 0, 128));
    register.add(avatarLabel, "align left");

    // Carousel
    JPanel carouselPanel = new JPanel(new MigLayout("insets 0", "[][grow][]", ""));
    carouselPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    carouselPanel.setBackground(new Color(255,255,255));

    leftArrowBtn = new JButton("◄");
    leftArrowBtn.setFont(new Font("Arial", Font.BOLD, 28));
    leftArrowBtn.setBackground(new Color(255,255,255));
    leftArrowBtn.setEnabled(false);
    carouselPanel.add(leftArrowBtn);

    JPanel slotsPanel = new JPanel(new MigLayout("insets 0", "[]10[]10[]", ""));
    slotsPanel.setOpaque(false);
    avatarSlotButtons = new JButton[VISIBLE_COUNT];
    for (int i = 0; i < VISIBLE_COUNT; i++) {
        JButton slot = new JButton();
        slot.setBackground(new Color(255,255,255));
        slot.setPreferredSize(new Dimension(180, 180));
        final int slotIndex = i;
        slot.addActionListener(e -> {
            int avatarIndex = startIndex + slotIndex;
            if (avatarIndex >= 0 && avatarIndex < avatarFiles.length) {
                selectedAvatar = avatarFiles[avatarIndex];
                ImageIcon icon = new ImageIcon(getClass().getResource(selectedAvatar));
                Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                selectedAvatarLabel.setIcon(new ImageIcon(img));
                selectedAvatarLabel.setBackground(new Color(255,255,255));
            }
        });
        avatarSlotButtons[i] = slot;
        slotsPanel.add(slot);
    }

    carouselPanel.add(slotsPanel, "growx");
    rightArrowBtn = new JButton("►");
    rightArrowBtn.setFont(new Font("Arial", Font.BOLD, 28));
    rightArrowBtn.setBackground(Color.WHITE);
    rightArrowBtn.setEnabled(avatarFiles.length > VISIBLE_COUNT);
    carouselPanel.add(rightArrowBtn);

    register.add(carouselPanel, "growx, align center");

    // Selected Avatar Preview
    selectedAvatarLabel = new JLabel();
    ImageIcon originalIcon = new ImageIcon("ruta/a/tu/avatar.png");
    Image scaledImage = originalIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
    ImageIcon resizedIcon = new ImageIcon(scaledImage);
    register.add(selectedAvatarLabel, "align center,gapy 40");

    // Crear perfil button
    Button crearPerfilBtn = new Button();
    crearPerfilBtn.setFont(new Font("Century Gothic", Font.BOLD, 28));
    crearPerfilBtn.setBackground(new Color(255, 153, 0));
    crearPerfilBtn.setForeground(Color.WHITE);
    crearPerfilBtn.setText("Crear Perfil");
    register.add(crearPerfilBtn, "split 2, sizegroup btn, gapx 70, gapy 0, w 200, h 50");

    // Jugar YA button
    Button jugarBtn = new Button();
    jugarBtn.setFont(new Font("Century Gothic", Font.BOLD, 28));
    jugarBtn.setBackground(new Color(204, 102, 255));
    jugarBtn.setForeground(Color.WHITE);
    jugarBtn.setText("Jugar YA!");
    register.add(jugarBtn, "sizegroup btn, w 200, h 50");
    crearPerfilBtn.addActionListener(e -> {
    String nickname = nicknameField.getText().trim();
    String edad = edadField.getText().trim();

    if (nickname.isEmpty() || edad.isEmpty() || selectedAvatar == null) {
        JOptionPane.showMessageDialog(
            register,
            "Por favor, completa todos los campos y selecciona un avatar.",
            "Datos incompletos",
            JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    // Validar que edad sea un número válido y positivo
    int edadNumero;
    try {
        edadNumero = Integer.parseInt(edad);
        if (edadNumero <= 0) {
            JOptionPane.showMessageDialog(
                register,
                "La edad debe ser un número mayor a 0.",
                "Edad inválida",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(
            register,
            "Por favor, escribe un número válido en el campo de edad.",
            "Edad inválida",
            JOptionPane.WARNING_MESSAGE
        );
        return;
    }
    // Buscar si ya existe el nickname
    Jugador jugadorExistente = ConexionBD.buscarPorNombre(nickname);
    if (jugadorExistente == null) {
        Jugador nuevoJugador = new Jugador(nickname, selectedAvatar, Integer.parseInt(edad));
        boolean exito = ConexionBD.insertarJugador(nuevoJugador);
        if (exito) {
            String mensaje = String.format(
                "Gracias por registrarte.\nPerfil creado:\nNickname: %s",
                nickname
            );
            JOptionPane.showMessageDialog(register, mensaje, "¡Éxito!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(
                register,
                "Ocurrió un error al registrar el jugador. Intenta de nuevo.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    } else {
        JOptionPane.showMessageDialog(
            register,
            "Ese nickname ya se encuentra en uso.",
            "Nickname repetido",
            JOptionPane.WARNING_MESSAGE
        );
    }   
    });

    jugarBtn.addActionListener(e -> {
        //cambios de lore
        String nickname = nicknameField.getText().trim();
        jugador = ConexionBD.buscarPorNombre(nickname);

        if (jugador == null) {
            JOptionPane.showMessageDialog(register, "Jugador no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = new Cliente();
        String host = "192.168.100.218"; // Cambia si necesario
        int puerto = 12345;

        boolean conectado = cliente.conectar(host, puerto, jugador);

        if (!conectado) {
            JOptionPane.showMessageDialog(register, "No se pudo conectar al servidor", "Error de conexión", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Mostrar pantalla de juego
        JFrameGameScreen gameScreen = new JFrameGameScreen(jugador);
        gameScreen.setVisible(true);

        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) window.dispose();
    });

    leftArrowBtn.addActionListener(e -> {
        if (startIndex > 0) {
            startIndex--;
            updateAvatars();
        }
    });

    rightArrowBtn.addActionListener(e -> {
        if (startIndex + VISIBLE_COUNT < avatarFiles.length) {
            startIndex++;
            updateAvatars();
        }
    });

    updateAvatars();
}

private void updateAvatars() {
    for (int slot = 0; slot < VISIBLE_COUNT; slot++) {
        int avatarIndex = startIndex + slot;
        JButton btn = avatarSlotButtons[slot];

        if (avatarIndex < avatarFiles.length) {
            ImageIcon icon = new ImageIcon(getClass().getResource(avatarFiles[avatarIndex]));
            Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(img));
            btn.setEnabled(true);
        } else {
            btn.setIcon(null);
            btn.setEnabled(false);
        }
    }

    leftArrowBtn.setEnabled(startIndex > 0);
    rightArrowBtn.setEnabled(startIndex + VISIBLE_COUNT < avatarFiles.length);
}



    private void initLogin() {
        //ImageIcon icono = new ImageIcon(getClass().getResource("/Imagenes/registros/disney.png"));
        //JLabel imagenLabel = new JLabel(icono);
        //login.add(imagenLabel, "w 100!, h 100!"); // ajusta tamaño a lo que necesites
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Escribe tu Nickname");
        label.setFont(new Font("Century Gothic", 1, 28));
        label.setForeground(new Color(25, 118, 210));
        login.add(label);
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/Imagenes/registros/user.png")));
        txtEmail.setHint("Nickname");
        login.add(txtEmail, "w 50%, h 5%");
        Button cmd = new Button();
        cmd.setFont(new Font("Century Gothic", Font.BOLD, 18));
        cmd.setBackground(new Color(255, 111, 0));
        cmd.setForeground(new Color(255, 255, 255));
        cmd.setText("DESCUBRIR");
        login.add(cmd, "Gapy 30, w 30%, h 50");
        cmd.addActionListener(e -> {
        //cambios de lore
        String nickname = txtEmail.getText().trim();
        jugador = ConexionBD.buscarPorNombre(nickname);

        if (nickname.isEmpty()) {
            JOptionPane.showMessageDialog(
                login,
                "Por favor, ingresa tu Nickname.",
                "Campo vacío",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (jugador == null) {
            int opcion = JOptionPane.showConfirmDialog(
                login,
                "No te encuentras registrado.\n¿Quieres registrarte?",
                "Registro",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );

            if (opcion == JOptionPane.YES_OPTION && panelCover != null) {
                panelCover.getButton().doClick();
            } else {
                JOptionPane.showMessageDialog(
                    login,
                    "Este nickname no existe.",
                    "Intenta de nuevo.",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
            return;
        }

        // ✅ Conexión al servidor
        Cliente cliente = new Cliente();
        String host = "192.168.100.218";  // ← Cambia por la IP real del servidor si es necesario
        int puerto = 12345;

        boolean conectado = cliente.conectar(host, puerto, jugador);

        if (!conectado) {
            JOptionPane.showMessageDialog(login, "No se pudo conectar al servidor", "Error de conexión", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(
            login,
            "¡Hola, " + jugador.getNickname() + "! \n Disfruta el juego",
            "Bienvenido",
            JOptionPane.INFORMATION_MESSAGE
        );

        JFrameGameScreen gameScreen = new JFrameGameScreen(jugador);
        gameScreen.setVisible(true);
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) window.dispose();
});
    }

    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
