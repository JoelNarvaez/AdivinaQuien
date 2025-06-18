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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static pantallas.PersonajeDisney.elegirPersonajesAleatorios;

public class Servidor {
    private ServerSocket serverSocket;
    private ExecutorService pool;
    private List<ObjectOutputStream> clientesConectados = new ArrayList<>();

    public void iniciarServidor(int puerto) {
        try {
            serverSocket = new ServerSocket(puerto);
            pool = Executors.newFixedThreadPool(2); // solo 2 jugadores

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
                Socket clienteSocket = socket;
                ObjectOutputStream out = new ObjectOutputStream(clienteSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clienteSocket.getInputStream());
            ) {
                Jugador jugador = (Jugador) in.readObject();
                System.out.println("Jugador conectado: " + jugador.getNickname());

                synchronized (clientesConectados) {
                    clientesConectados.add(out);

                    if (clientesConectados.size() == 2) {
                        // 1. Generar personajes aleatorios solo una vez
                        PersonajeDisney[] seleccionados = elegirPersonajesAleatorios(24);

                        // 2. Enviar "start" y luego el vector a ambos jugadores
                        for (ObjectOutputStream clienteOut : clientesConectados) {
                            clienteOut.writeObject("start");
                            clienteOut.writeObject(seleccionados);
                            clienteOut.flush();
                        }

                        // 3. Limpiar para permitir otro par
                        clientesConectados.clear();
                    }
                }

                // Escuchar mensajes del cliente conectado
                while (true) {
                    Object entrada = in.readObject(); // se bloquea esperando
                    if (entrada instanceof String mensaje) {
                        System.out.println("Mensaje recibido de " + jugador.getNickname() + ": " + mensaje);
                    }
                }

            } catch (EOFException eof) {
                System.out.println("Jugador desconectado (fin de flujo)");
            } catch (SocketException se) {
                System.out.println("Jugador desconectado (socket cerrado)");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
