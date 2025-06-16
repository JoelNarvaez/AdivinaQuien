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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {
    private ServerSocket serverSocket;
    private ExecutorService pool;

    public void iniciarServidor(int puerto) {
        try {
            serverSocket = new ServerSocket(puerto);
            pool = Executors.newFixedThreadPool(2); // por ahora solo 2 jugadores

            System.out.println("Servidor iniciado en puerto " + puerto);

            while (true) {
                Socket cliente = serverSocket.accept();
                pool.execute(new ManejadorCliente(cliente));
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }

    private class ManejadorCliente implements Runnable {
        private Socket socket;

        public ManejadorCliente(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try (
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ) {
                Jugador jugador = (Jugador) in.readObject();
                System.out.println("Jugador conectado: " + jugador.getNickname());
                // Aquí podrías guardarlo en lista si luego manejas turnos, etc.

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
