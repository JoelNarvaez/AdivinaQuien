/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

/**
 *
 * @author loren
 */
import java.io.*;
import java.net.*;

public class Cliente {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private PersonajeDisney[] personajesRecibidos;

    public boolean conectar(String host, int puerto, Jugador jugador) {
        try {
            socket = new Socket(host, puerto);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            // Enviar jugador al servidor
            out.writeObject(jugador);
            out.flush();

            // Esperar el mensaje "start"
            Object respuesta = in.readObject();
            if ("start".equals(respuesta)) {
                System.out.println("Comienza el juego");

                // Esperar el vector de personajes
                Object personajes = in.readObject();
                if (personajes instanceof PersonajeDisney[]) {
                    personajesRecibidos = (PersonajeDisney[]) personajes;
                    System.out.println("Personajes recibidos: " + personajesRecibidos.length);
                    return true;
                } else {
                    System.err.println("Error: se esperaba un arreglo de PersonajeDisney");
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public PersonajeDisney[] getPersonajesRecibidos() {
        return personajesRecibidos;
    }

    public void cerrarConexion() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null && !socket.isClosed()) socket.close();
            System.out.println("Conexión cerrada.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
