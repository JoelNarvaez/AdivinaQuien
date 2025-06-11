package componentes;

import Estilos.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

public class PanelCover extends javax.swing.JPanel {

    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private ActionListener event;
    private MigLayout layout;
    private JLabel title;
    private JLabel description;
    private JLabel description1;
    private Button button;
    private boolean isLogin;
    
    public Button getButton() {
    return button;
    }
    
    public PanelCover() {
        initComponents();
        setOpaque(false);
        layout = new MigLayout("wrap, fill", "[center]", "push[]25[]10[]25[]push");
        setLayout(layout);
        init();

    }

    private void init() {
        button = new Button();
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(new Color(255, 255, 255));
        button.setFont(new Font("Century Gothic", Font.BOLD, 18));
        button.setText("Registrate");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.actionPerformed(ae);
            }
        });
        add(button, "gapy 600, w 50%, h 50");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
  protected void paintComponent(Graphics grphcs) {
    Graphics2D g2 = (Graphics2D) grphcs;

    // Cargar la imagen de fondo
    Image backgroundImage = new ImageIcon(getClass().getResource("/Imagenes/registros/coverImage.png")).getImage();

    // Dibujar la imagen ocupando todo el tamaño del panel
    g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

    super.paintComponent(grphcs); // Pinta componentes hijos encima de la imagen
}

    public void addEvent(ActionListener event) {
        this.event = event;
    }

    public void registerLeft(double v) {
        v = Double.valueOf(df.format(v));
        login(false);
    }

    public void registerRight(double v) {
        v = Double.valueOf(df.format(v));
        login(false);
    }

    public void loginLeft(double v) {
        v = Double.valueOf(df.format(v));
        login(true);
    }

    public void loginRight(double v) {
        v = Double.valueOf(df.format(v));
        login(true);
    }

    public void login(boolean login) {
        if (this.isLogin != login) {
            if (login) {
                button.setText("Registrate");
            } else {
                button.setText("Iniciar");
            }
            this.isLogin = login;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
