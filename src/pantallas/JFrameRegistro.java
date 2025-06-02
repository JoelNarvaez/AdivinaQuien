/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Joel
 */
public class JFrameRegistro extends javax.swing.JFrame {

    private JTextField nicknameField;
    private JTextField edadField;
    private JLabel selectedAvatarLabel;
    private String selectedAvatar = null;

    private final String[] avatarFiles = {
        "/Imagenes/profileIcons/boss.png",
        "/Imagenes/profileIcons/lilo.png",
        "/Imagenes/profileIcons/mcqueen.jpg",
        "/Imagenes/profileIcons/mike.jpg",
        "/Imagenes/profileIcons/nemo.jpg"
    };

    private int startIndex = 0;
    private final int VISIBLE_COUNT = 3;
    private final JButton[] avatarSlotButtons = new JButton[VISIBLE_COUNT];
    private JButton leftArrowBtn;
    private JButton rightArrowBtn;

    public JFrameRegistro() {
        setTitle("Adivina Quién");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(220, 220, 230));

        JLabel nickLabel = new JLabel("Nickname:");
        nickLabel.setBounds(100, 50, 150, 40);
        nickLabel.setFont(new Font("Arial", Font.BOLD, 22));
        add(nickLabel);

        nicknameField = new JTextField();
        nicknameField.setBounds(250, 50, 250, 40);
        nicknameField.setFont(new Font("Arial", Font.PLAIN, 20));
        add(nicknameField);

        JLabel edadLabel = new JLabel("Edad:");
        edadLabel.setBounds(100, 110, 150, 40);
        edadLabel.setFont(new Font("Arial", Font.BOLD, 22));
        add(edadLabel);

        edadField = new JTextField();
        edadField.setBounds(250, 110, 100, 40);
        edadField.setFont(new Font("Arial", Font.PLAIN, 20));
        add(edadField);

        JLabel avatarLabel = new JLabel("Escoge un avatar:");
        avatarLabel.setBounds(100, 180, 300, 40);
        avatarLabel.setFont(new Font("Arial", Font.BOLD, 22));
        avatarLabel.setForeground(new Color(128, 0, 128));
        add(avatarLabel);

        JPanel carouselPanel = new JPanel();
        carouselPanel.setLayout(null);
        carouselPanel.setBounds(100, 240, 450, 120);
        carouselPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        carouselPanel.setBackground(new Color(240, 240, 250));
        add(carouselPanel);

        leftArrowBtn = new JButton("◄");
        leftArrowBtn.setBounds(0, 35, 50, 50);
        leftArrowBtn.setFont(new Font("Arial", Font.BOLD, 20));
        leftArrowBtn.setEnabled(false);
        carouselPanel.add(leftArrowBtn);

        rightArrowBtn = new JButton("►");
        rightArrowBtn.setBounds(400, 35, 50, 50);
        rightArrowBtn.setFont(new Font("Arial", Font.BOLD, 20));
        rightArrowBtn.setEnabled(avatarFiles.length > VISIBLE_COUNT);
        carouselPanel.add(rightArrowBtn);

        for (int i = 0; i < VISIBLE_COUNT; i++) {
            JButton slot = new JButton();
            slot.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            slot.setBounds(65 + i * 110, 10, 100, 100);
            final int slotIndex = i;
            slot.addActionListener(e -> {
                int avatarIndex = startIndex + slotIndex;
                if (avatarIndex >= 0 && avatarIndex < avatarFiles.length) {
                    selectedAvatar = avatarFiles[avatarIndex];
                    ImageIcon icon = new ImageIcon(getClass().getResource(selectedAvatar));
                    Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                    selectedAvatarLabel.setIcon(new ImageIcon(img));
                }
            });
            avatarSlotButtons[i] = slot;
            carouselPanel.add(slot);
        }

        selectedAvatarLabel = new JLabel();
        selectedAvatarLabel.setBounds(560, 230, 180, 180);
        add(selectedAvatarLabel);

        JButton crearPerfilBtn = new JButton("crear perfil");
        crearPerfilBtn.setBounds(100, 400, 200, 50);
        crearPerfilBtn.setFont(new Font("Arial", Font.BOLD, 18));
        crearPerfilBtn.setBackground(new Color(255, 153, 0));
        crearPerfilBtn.setForeground(Color.white);
        add(crearPerfilBtn);

        crearPerfilBtn.addActionListener(e -> {
            String nickname = nicknameField.getText().trim();
            String edad = edadField.getText().trim();
            if (nickname.isEmpty() || edad.isEmpty() || selectedAvatar == null) {
                JOptionPane.showMessageDialog(
                    this,
                    "Por favor, completa todos los campos y selecciona un avatar.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            String mensaje = String.format(
                "Perfil creado:\nNick: %s\nEdad: %s\nAvatar: %s",
                nickname, edad, selectedAvatar
            );
            JOptionPane.showMessageDialog(this, mensaje, "¡Éxito!", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton jugarBtn = new JButton("Jugar YA!");
        jugarBtn.setBounds(100, 470, 200, 50);
        jugarBtn.setFont(new Font("Arial", Font.BOLD, 18));
        jugarBtn.setBackground(new Color(204, 102, 255));
        jugarBtn.setForeground(Color.white);
        add(jugarBtn);

        jugarBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                this,
                "Aquí podrías lanzar la lógica del juego.",
                "Jugar YA",
                JOptionPane.INFORMATION_MESSAGE
            );
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
                Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Pnatlla de registros");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(178, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(398, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameRegistro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
