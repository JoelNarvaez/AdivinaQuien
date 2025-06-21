package pantallas;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import static pantallas.PersonajeDisney.elegirPersonajesAleatorios;

public class Servidor {
    private ServerSocket serverSocket;
    private ExecutorService pool;

    // Listas de espera para emparejar jugadores
    private List<Jugador> jugadoresEnEspera = new ArrayList<>();
    private List<ObjectOutputStream> salidasEnEspera = new ArrayList<>();
    private List<ObjectInputStream> entradasEnEspera = new ArrayList<>();

    public void iniciarServidor(int puerto) {
        try {
            serverSocket = new ServerSocket(puerto);
            pool = Executors.newCachedThreadPool();  // permite múltiples partidas
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
            ObjectOutputStream out = null;
            ObjectInputStream in = null;

            try {
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());

                Object recibido = in.readObject();
                if (!(recibido instanceof Jugador jugador)) {
                    System.out.println("Error: objeto recibido no es un Jugador.");
                    socket.close();
                    return;
                }

                System.out.println("Jugador conectado: " + jugador.getNickname());

                synchronized (Servidor.this) {
                    jugadoresEnEspera.add(jugador);
                    salidasEnEspera.add(out);
                    entradasEnEspera.add(in);

                    if (jugadoresEnEspera.size() >= 2) {
                        PersonajeDisney[] seleccionados = elegirPersonajesAleatorios(24);

                        List<Jugador> jugadoresPartida = new ArrayList<>();
                        List<ObjectOutputStream> salidasPartida = new ArrayList<>();
                        List<ObjectInputStream> entradasPartida = new ArrayList<>();

                        jugadoresPartida.add(jugadoresEnEspera.remove(0));
                        jugadoresPartida.add(jugadoresEnEspera.remove(0));
                        salidasPartida.add(salidasEnEspera.remove(0));
                        salidasPartida.add(salidasEnEspera.remove(0));
                        entradasPartida.add(entradasEnEspera.remove(0));
                        entradasPartida.add(entradasEnEspera.remove(0));

                        // Aquí NO cerramos sockets. La clase PartidaConexion manejará todo.
                        PartidaConexion nuevaPartida = new PartidaConexion(jugadoresPartida, salidasPartida, entradasPartida);
                        new Thread(nuevaPartida).start();
                    }
                }

            } catch (EOFException | SocketException eof) {
                System.out.println("Jugador desconectado prematuramente.");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // ⚠️ Ya NO se cierra el socket aquí si el jugador está en espera o fue emparejado.
            // El cierre debe ser responsabilidad de la clase PartidaConexion
        }
    }
}
