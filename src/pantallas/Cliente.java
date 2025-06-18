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

    public boolean conectar(String host, int puerto, Jugador jugador) {
    try {
        socket = new Socket(host, puerto);
        out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(jugador);  // Enviamos el objeto Jugador al servidor
        out.flush();
        socket.close(); // <- Cierra conexión después de enviar
        System.out.println("Jugador enviado: " + jugador.getNickname());
        return true;
    } catch (IOException e) {
        e.printStackTrace(); // <-- importante para ver el error real
        return false;
    }
}

    public void cerrarConexion() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}