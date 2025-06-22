/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package canciones;

import javax.sound.sampled.*;
import java.io.File;

public class ReproductorMusica {
    private static ReproductorMusica instancia;
    private Clip clip;
    private boolean reproduciendo = false;
    private long posicionPausa = 0;
    private int indiceCancion = 0;
    
    private String[] canciones = {
        "src/canciones/You've Got a Friend in Me.wav",
        "src/canciones/Beauty and the Beast.wav",
        "src/canciones/Married Life.wav",
        "src/canciones/A Whole New World.wav"
    };
    
    private ReproductorMusica(){}
    
    public static ReproductorMusica getInstancia(){
        if(instancia == null){
            instancia = new ReproductorMusica();
        }
        return instancia;
    }
        
    public void cargarMusica(String ruta) {
        try {
            if (clip != null && clip.isOpen()) {
                clip.stop();
                clip.close();
            }
            File archivo = new File(ruta);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(archivo);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            reproduciendo = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void alternarMusica() {
        if (indiceCancion < canciones.length) {
            cargarMusica(canciones[indiceCancion]);
            indiceCancion++;
        } else {
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }
            reproduciendo = false;
            indiceCancion = 0; // Reiniciar para empezar desde el principio en el siguiente clic
        }
    }
    
    public void pausarOReanudar() {
        if (clip != null) {
            if (clip.isRunning()) {
                posicionPausa = clip.getMicrosecondPosition();
                clip.stop();
            } else {
                clip.setMicrosecondPosition(posicionPausa);
                clip.start();
            }
        }
    }

    public void detenerMusica() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
        reproduciendo = false;
    }

    public boolean estaReproduciendo() {
        return reproduciendo && clip != null && clip.isRunning();
    }
    
}
