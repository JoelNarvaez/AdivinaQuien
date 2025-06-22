/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pantallas;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author marie
 */
public class JFrameAnimo extends javax.swing.JFrame {
    
    private JFrameGameScreen gamescreen;
    private final String nombreJugador = "Ana Lorena";

    public void setGamescreen(JFrameGameScreen gamescreen) {
        this.gamescreen = gamescreen;
    }

    
    /**
     * Creates new form JFrameAnimo
     */
    public JFrameAnimo(String nombreJugador) {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla completa
        ajustarComponentes(nombreJugador);
        this.setLocationRelativeTo(null);
    }
    
    private void ajustarComponentes(String nombreJugador) {
    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    int anchoPantalla = pantalla.width;
    int altoPantalla = pantalla.height - 70;

    // Escalado de fuente
    int fontSize110 = altoPantalla * 110 / 1080;
    int fontSize90 = altoPantalla * 90 / 1080;

    Font fuente110 = new Font("Comic Sans MS", Font.BOLD, fontSize110);
    Font fuente90 = new Font("Comic Sans MS", Font.BOLD, fontSize90);

    jLabelAnimo.setFont(fuente110);
    jLabelAnimoSombra.setFont(fuente110);

    jLabelJugador.setFont(fuente90);
    
    jLabelJugadorSombra.setFont(fuente90);
    jLabelJugadorSombra.setText(nombreJugador);
    jLabelJugador.setText(nombreJugador);

    

    // Posicionar todos los labels con proporciones
    jLabelNubes.setBounds((int)(0.0031 * anchoPantalla), 0, anchoPantalla, (int)(0.4907 * altoPantalla));
    jLabelParaguasVolverJugar.setBounds((int)(0.0729 * anchoPantalla), (int)(0.5926 * altoPantalla), (int)(0.2135 * anchoPantalla), (int)(0.3056 * altoPantalla));
    jLabelBarcoSalir.setBounds((int)(0.2917 * anchoPantalla), (int)(0.6574 * altoPantalla), (int)(0.2604 * anchoPantalla), (int)(0.2685 * altoPantalla));
    jLabelCharco.setBounds((int)(0.1406 * anchoPantalla), (int)(0.8379 * altoPantalla), (int)(0.8281 * anchoPantalla), (int)(0.1944 * altoPantalla));
    jLabelAnimo.setBounds((int)(0.1979 * anchoPantalla), (int)(0.3333 * altoPantalla), (int)(0.2760 * anchoPantalla), (int)(0.1435 * altoPantalla));
    jLabelAnimoSombra.setBounds((int)(0.1979 * anchoPantalla), (int)(0.3426 * altoPantalla), (int)(0.2760 * anchoPantalla), (int)(0.1435 * altoPantalla));
    jLabelJugador.setBounds((int)(0.0521 * anchoPantalla), (int)(0.4815 * altoPantalla), (int)(0.5625 * anchoPantalla), (int)(0.1176 * altoPantalla));
    jLabelJugadorSombra.setBounds((int)(0.0521 * anchoPantalla), (int)(0.4907 * altoPantalla), (int)(0.5625 * anchoPantalla), (int)(0.1176 * altoPantalla));

    jLabelLluvia1.setBounds((int)(0.0729 * anchoPantalla), (int)(0.25 * altoPantalla), (int)(0.2760 * anchoPantalla), (int)(0.6759 * altoPantalla));
    jLabelLluvia2.setBounds((int)(0.0156 * anchoPantalla), (int)(0.3333 * altoPantalla), (int)(0.2448 * anchoPantalla), (int)(0.5185 * altoPantalla));
    jLabelLluvia3.setBounds((int)(0.3281 * anchoPantalla), (int)(0.2685 * altoPantalla), (int)(0.3281 * anchoPantalla), (int)(0.6019 * altoPantalla));

    
    
    
    ImageIcon imgNubes = new ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/nubesAzules.png"));
    Image imgNubesRedimensionada = imgNubes.getImage().getScaledInstance(jLabelNubes.getWidth(), jLabelNubes.getHeight(), Image.SCALE_SMOOTH);
    jLabelNubes.setIcon(new ImageIcon(imgNubesRedimensionada));
    
    ImageIcon imgCharco = new ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/charco.png"));
    Image imgCharcoRedimensionada = imgCharco.getImage().getScaledInstance(jLabelCharco.getWidth(), jLabelCharco.getHeight(), Image.SCALE_SMOOTH);
    jLabelCharco.setIcon(new ImageIcon(imgCharcoRedimensionada));
    
    ImageIcon imgParaguas = new ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/paraguasVolverJugar.png"));
    Image imgParaguasRedimensionada = imgParaguas.getImage().getScaledInstance(jLabelParaguasVolverJugar.getWidth(), jLabelParaguasVolverJugar.getHeight(), Image.SCALE_SMOOTH);
    jLabelParaguasVolverJugar.setIcon(new ImageIcon(imgParaguasRedimensionada));
    
    // Cargar directamente los GIFs al tamaño del JLabel
    jLabelTristeza.setIcon(new ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/tristeza.gif")));
    jLabelBarcoSalir.setIcon(new ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/barcoSalir.gif")));
    /*
    jLabelLluvia1.setIcon(new ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/lluviaGif.gif")));
    jLabelLluvia2.setIcon(new ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/lluviaGif.gif")));
    jLabelLluvia3.setIcon(new ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/lluviaGif.gif")));
    jLabelLluvia4.setIcon(new ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/lluviaGif.gif")));*/
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPrincipal = new javax.swing.JPanel();
        jLabelJugador = new javax.swing.JLabel();
        jLabelParaguasVolverJugar = new javax.swing.JLabel();
        jLabelAnimo = new javax.swing.JLabel();
        jLabelTristeza = new javax.swing.JLabel();
        jLabelLluvia1 = new javax.swing.JLabel();
        jLabelLluvia2 = new javax.swing.JLabel();
        jLabelLluvia3 = new javax.swing.JLabel();
        jLabelCharco = new javax.swing.JLabel();
        jLabelBarcoSalir = new javax.swing.JLabel();
        jLabelAnimoSombra = new javax.swing.JLabel();
        jLabelNubes = new javax.swing.JLabel();
        jLabelJugadorSombra = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1920, 1080));

        jPanelPrincipal.setBackground(new java.awt.Color(232, 240, 244));
        jPanelPrincipal.setForeground(new java.awt.Color(232, 240, 244));
        jPanelPrincipal.setPreferredSize(new java.awt.Dimension(1920, 1080));
        jPanelPrincipal.setLayout(null);

        jLabelJugador.setFont(new java.awt.Font("Comic Sans MS", 1, 90)); // NOI18N
        jLabelJugador.setForeground(new java.awt.Color(163, 208, 241));
        jLabelJugador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelJugador.setText("jLabel1");
        jPanelPrincipal.add(jLabelJugador);
        jLabelJugador.setBounds(100, 520, 1080, 127);

        jLabelParaguasVolverJugar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/paraguasVolverJugar.png"))); // NOI18N
        jLabelParaguasVolverJugar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelParaguasVolverJugarMouseClicked(evt);
            }
        });
        jPanelPrincipal.add(jLabelParaguasVolverJugar);
        jLabelParaguasVolverJugar.setBounds(140, 640, 410, 330);

        jLabelAnimo.setFont(new java.awt.Font("Comic Sans MS", 1, 110)); // NOI18N
        jLabelAnimo.setForeground(new java.awt.Color(163, 208, 241));
        jLabelAnimo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAnimo.setText("Perdiste");
        jPanelPrincipal.add(jLabelAnimo);
        jLabelAnimo.setBounds(380, 360, 530, 155);

        jLabelTristeza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/tristeza.gif"))); // NOI18N
        jPanelPrincipal.add(jLabelTristeza);
        jLabelTristeza.setBounds(590, 290, 1230, 690);

        jLabelLluvia1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/lluviaGif.gif"))); // NOI18N
        jPanelPrincipal.add(jLabelLluvia1);
        jLabelLluvia1.setBounds(140, 270, 530, 730);

        jLabelLluvia2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/lluviaGif.gif"))); // NOI18N
        jPanelPrincipal.add(jLabelLluvia2);
        jLabelLluvia2.setBounds(30, 360, 470, 560);

        jLabelLluvia3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/lluviaGif.gif"))); // NOI18N
        jPanelPrincipal.add(jLabelLluvia3);
        jLabelLluvia3.setBounds(630, 290, 630, 650);

        jLabelCharco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/charco.png"))); // NOI18N
        jPanelPrincipal.add(jLabelCharco);
        jLabelCharco.setBounds(270, 870, 1590, 210);

        jLabelBarcoSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/barcoSalir.gif"))); // NOI18N
        jLabelBarcoSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBarcoSalirMouseClicked(evt);
            }
        });
        jPanelPrincipal.add(jLabelBarcoSalir);
        jLabelBarcoSalir.setBounds(560, 710, 500, 290);

        jLabelAnimoSombra.setFont(new java.awt.Font("Comic Sans MS", 1, 110)); // NOI18N
        jLabelAnimoSombra.setForeground(new java.awt.Color(50, 87, 169));
        jLabelAnimoSombra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAnimoSombra.setText("Perdiste");
        jPanelPrincipal.add(jLabelAnimoSombra);
        jLabelAnimoSombra.setBounds(380, 370, 530, 155);

        jLabelNubes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/nubesAzules.png"))); // NOI18N
        jLabelNubes.setPreferredSize(new java.awt.Dimension(1920, 530));
        jPanelPrincipal.add(jLabelNubes);
        jLabelNubes.setBounds(6, 0, 1920, 530);

        jLabelJugadorSombra.setFont(new java.awt.Font("Comic Sans MS", 1, 90)); // NOI18N
        jLabelJugadorSombra.setForeground(new java.awt.Color(50, 87, 169));
        jLabelJugadorSombra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelJugadorSombra.setText("jLabel1");
        jPanelPrincipal.add(jLabelJugadorSombra);
        jLabelJugadorSombra.setBounds(100, 530, 1080, 127);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 1011, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 69, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelParaguasVolverJugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelParaguasVolverJugarMouseClicked
        // TODO add your handling code here:
        JFramePresentacion pantallaPresentacion = new JFramePresentacion();
        pantallaPresentacion.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabelParaguasVolverJugarMouseClicked

    private void jLabelBarcoSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBarcoSalirMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabelBarcoSalirMouseClicked

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
            java.util.logging.Logger.getLogger(JFrameAnimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameAnimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameAnimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameAnimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        //java.awt.EventQueue.invokeLater(new Runnable() {
            //public void run() {
                //new JFrameAnimo().setVisible(true);
            //}
            SwingUtilities.invokeLater(() -> {
            String nombreJugador = "AnaLorena";
            JFrameAnimo ventana = new JFrameAnimo(nombreJugador);
            ventana.setVisible(true);
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelAnimo;
    private javax.swing.JLabel jLabelAnimoSombra;
    private javax.swing.JLabel jLabelBarcoSalir;
    private javax.swing.JLabel jLabelCharco;
    private javax.swing.JLabel jLabelJugador;
    private javax.swing.JLabel jLabelJugadorSombra;
    private javax.swing.JLabel jLabelLluvia1;
    private javax.swing.JLabel jLabelLluvia2;
    private javax.swing.JLabel jLabelLluvia3;
    private javax.swing.JLabel jLabelNubes;
    private javax.swing.JLabel jLabelParaguasVolverJugar;
    private javax.swing.JLabel jLabelTristeza;
    private javax.swing.JPanel jPanelPrincipal;
    // End of variables declaration//GEN-END:variables
}
