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
public class JFrameFelicitacion extends javax.swing.JFrame {
    
    private JFrameRegistro pantallaRegistro;
    private String jugadorGanador;

    public void setPantallaRegistro(JFrameRegistro pantallaRegistro) {
        this.pantallaRegistro = pantallaRegistro;
    }
    
    

    /**
     * Creates new form JFrameFelicitacion
     */
    public JFrameFelicitacion(String jugadorGanador) {
        this.jugadorGanador = "Ana Lorena";
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla completa
        ajustarComponentes(jugadorGanador);
        this.setLocationRelativeTo(null);
    }
    
     private void ajustarComponentes(String jugadorGanador) {
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();   // Calcular el tamaño total de la pantalla
        int anchoPantalla = pantalla.width;
        int altoPantalla = pantalla.height - 60;
        
        jLabelFondo.setBounds(0,0, anchoPantalla, altoPantalla);
        ImageIcon imgFondo = new ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/fondoPlaya.png"));
        Image imgFondoRedimensionada = imgFondo.getImage().getScaledInstance(jLabelFondo.getWidth(), jLabelFondo.getHeight(), Image.SCALE_SMOOTH);
        jLabelFondo.setIcon(new ImageIcon(imgFondoRedimensionada));

        
        // Proporciones
        jLabelCubetaSalir.setBounds((int)(0.2292 * anchoPantalla), (int)(0.5 * altoPantalla),(int)(0.1589 * anchoPantalla), (int)(0.4130 * altoPantalla));
        jLabelCocoJugar.setBounds((int)(0.0417 * anchoPantalla), (int)(0.5556 * altoPantalla),(int)(0.1635 * anchoPantalla), (int)(0.3639 * altoPantalla));
        jLabelStitch.setBounds((int)(0.6250 * anchoPantalla), (int)(0.2778 * altoPantalla),(int)(0.2865 * anchoPantalla), (int)(0.6852 * altoPantalla));
        jLabelFelicitacion.setBounds((int)(0.2083 * anchoPantalla), (int)(0.1111 * altoPantalla),(int)(0.2656 * anchoPantalla), (int)(0.1204 * altoPantalla));
        jLabelJugadorGanador.setBounds((int)(0.1302 * anchoPantalla), (int)(0.2407 * altoPantalla),(int)(0.4167 * anchoPantalla), (int)(0.1481 * altoPantalla));

        // Fuente escalada
        int fontSize = altoPantalla * 110 / 1080;
        Font fuenteTitulo = new Font("Comic Sans MS", Font.BOLD, fontSize);
        jLabelFelicitacion.setFont(fuenteTitulo);
        jLabelJugadorGanador.setFont(fuenteTitulo);
        jLabelJugadorGanador.setText(jugadorGanador);
        
        // Escalado de imágenes
        ImageIcon imgCoco = new ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/cocoVolverJugar.png"));
        Image imgCocoRedimensionada = imgCoco.getImage().getScaledInstance(jLabelCocoJugar.getWidth(), jLabelCocoJugar.getHeight(), Image.SCALE_SMOOTH);
        jLabelCocoJugar.setIcon(new ImageIcon(imgCocoRedimensionada));
        
        ImageIcon imgCubeta = new ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/cubetaSalir.png"));
        Image imgCubetaRedimensionada = imgCubeta.getImage().getScaledInstance(jLabelCubetaSalir.getWidth(), jLabelCubetaSalir.getHeight(), Image.SCALE_SMOOTH);
        jLabelCubetaSalir.setIcon(new ImageIcon(imgCubetaRedimensionada));
        
        ImageIcon gifStitch = new ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/StitchGif.gif"));
        jLabelStitch.setIcon(gifStitch);
        /*int anchoGif = gifStitch.getIconWidth();
        int altoGif = gifStitch.getIconHeight();
        jLabelStitch.setBounds(1200, 300, anchoGif, altoGif);*/
        jLabelStitch.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelStitch.setVerticalAlignment(SwingConstants.CENTER); 
        
       /* Posición y tamaño del label en proporción a pantalla
        int x = (int)(0.6250 * anchoPantalla);
        int y = (int)(0.2778 * (altoPantalla+60));
        int w = (int)(0.2865 * anchoPantalla);
        int h = (int)(0.6852 * (altoPantalla+60));
        jLabelStitch.setBounds(x, y, w, h);

        // Cargar el GIF
        ImageIcon gifOriginal = new ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/StitchGif.gif"));
        Image imagenGif = gifOriginal.getImage();

        // Mantener proporciones del GIF
        double proporcionOriginal = (double) gifOriginal.getIconWidth() / gifOriginal.getIconHeight();
        int nuevoAncho = w;
        int nuevoAlto = (int)(nuevoAncho / proporcionOriginal);

        if (nuevoAlto > h) {
            nuevoAlto = h;
            nuevoAncho = (int)(nuevoAlto * proporcionOriginal);
        }

        // Escalar imagen
        Image imgEscalada = imagenGif.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_DEFAULT);
        jLabelStitch.setIcon(new ImageIcon(imgEscalada));

        // Centrarla dentro del label
        jLabelStitch.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelStitch.setVerticalAlignment(SwingConstants.CENTER);*/


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
        jLabelFelicitacion = new javax.swing.JLabel();
        jLabelJugadorGanador = new javax.swing.JLabel();
        jLabelCocoJugar = new javax.swing.JLabel();
        jLabelCubetaSalir = new javax.swing.JLabel();
        jLabelStitch = new javax.swing.JLabel();
        jLabelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setSize(new java.awt.Dimension(1920, 1080));
        getContentPane().setLayout(null);

        jPanelPrincipal.setLayout(null);

        jLabelFelicitacion.setFont(new java.awt.Font("Comic Sans MS", 1, 110)); // NOI18N
        jLabelFelicitacion.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFelicitacion.setText("¡Ganaste!");
        jPanelPrincipal.add(jLabelFelicitacion);
        jLabelFelicitacion.setBounds(400, 120, 510, 130);

        jLabelJugadorGanador.setFont(new java.awt.Font("Comic Sans MS", 1, 110)); // NOI18N
        jLabelJugadorGanador.setForeground(new java.awt.Color(255, 255, 255));
        jLabelJugadorGanador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelJugadorGanador.setText("jLabel1");
        jPanelPrincipal.add(jLabelJugadorGanador);
        jLabelJugadorGanador.setBounds(250, 260, 800, 160);

        jLabelCocoJugar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/cocoVolverJugar.png"))); // NOI18N
        jLabelCocoJugar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCocoJugarMouseClicked(evt);
            }
        });
        jPanelPrincipal.add(jLabelCocoJugar);
        jLabelCocoJugar.setBounds(80, 600, 314, 393);

        jLabelCubetaSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/cubetaSalir.png"))); // NOI18N
        jLabelCubetaSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCubetaSalirMouseClicked(evt);
            }
        });
        jPanelPrincipal.add(jLabelCubetaSalir);
        jLabelCubetaSalir.setBounds(440, 540, 305, 446);

        jLabelStitch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelStitch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/StitchGif.gif"))); // NOI18N
        jPanelPrincipal.add(jLabelStitch);
        jLabelStitch.setBounds(1200, 300, 550, 740);

        jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disenoPantallas/fondoPlaya.png"))); // NOI18N
        jLabelFondo.setText("Ana");
        jLabelFondo.setPreferredSize(new java.awt.Dimension(1920, 1080));
        jPanelPrincipal.add(jLabelFondo);
        jLabelFondo.setBounds(0, 0, 1920, 1080);

        getContentPane().add(jPanelPrincipal);
        jPanelPrincipal.setBounds(0, 0, 1920, 1080);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelCocoJugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCocoJugarMouseClicked
        // TODO add your handling code here:
        JFramePresentacion pantallaPresentacion = new JFramePresentacion();
        pantallaPresentacion.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabelCocoJugarMouseClicked

    private void jLabelCubetaSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCubetaSalirMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabelCubetaSalirMouseClicked

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
            java.util.logging.Logger.getLogger(JFrameFelicitacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameFelicitacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameFelicitacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameFelicitacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String jugador = "Ana Lorena";
                new JFrameFelicitacion(jugador).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelCocoJugar;
    private javax.swing.JLabel jLabelCubetaSalir;
    private javax.swing.JLabel jLabelFelicitacion;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JLabel jLabelJugadorGanador;
    private javax.swing.JLabel jLabelStitch;
    private javax.swing.JPanel jPanelPrincipal;
    // End of variables declaration//GEN-END:variables
}
