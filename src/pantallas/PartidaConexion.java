package pantallas;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class PartidaConexion implements Runnable {
    private List<Jugador> jugadores;
    private List<ObjectOutputStream> salidas;
    private List<ObjectInputStream> entradas;

    public PartidaConexion(List<Jugador> jugadores, List<ObjectOutputStream> salidas, List<ObjectInputStream> entradas) {
        this.jugadores = jugadores;
        this.salidas = salidas;
        this.entradas = entradas;
    }

    @Override
    public void run() {
        try {
            System.out.println("Iniciando partida entre: " +
                    jugadores.get(0).getNickname() + " y " + jugadores.get(1).getNickname());

            // Enviar mensaje de inicio, personajes y nombre del oponente
            PersonajeDisney[] seleccionados = PersonajeDisney.elegirPersonajesAleatorios(24);

            for (int i = 0; i < 2; i++) {
                salidas.get(i).writeObject("start");
                salidas.get(i).writeObject(seleccionados);
                String nombreOponente = jugadores.get(1 - i).getNickname(); // nombre del otro jugador
                salidas.get(i).writeObject(nombreOponente);
                salidas.get(i).flush();
            }

            // Enviar turno inicial
            salidas.get(0).writeObject("tuTurno");
            salidas.get(0).flush();
            salidas.get(1).writeObject("espera");
            salidas.get(1).flush();

            // Escuchar mensajes de ambos jugadores
            for (int i = 0; i < 2; i++) {
                final int yo = i;
                final int rival = (i == 0) ? 1 : 0;

                new Thread(() -> {
                    try {
                        while (true) {
                            Object recibido = entradas.get(yo).readObject();

                            if (recibido instanceof String mensaje) {
                                System.out.println("[" + jugadores.get(yo).getNickname() + "] -> " + mensaje);

                                // Reenviar al rival
                                salidas.get(rival).writeObject(mensaje);
                                salidas.get(rival).flush();

                                // Control de turnos
                                if ("turnoFinalizado".equals(mensaje)) {
                                    salidas.get(rival).writeObject("tuTurno");
                                    salidas.get(rival).flush();
                                    salidas.get(yo).writeObject("espera");
                                    salidas.get(yo).flush();
                                }
                            }
                        }
                    } catch (EOFException | SocketException e) {
                        System.out.println("Jugador " + jugadores.get(yo).getNickname() + " se desconectó.");
                        try {
                            salidas.get(rival).writeObject("oponenteDesconectado");
                            salidas.get(rival).flush();
                        } catch (IOException ex) {
                            System.out.println("No se pudo notificar al jugador restante.");
                        } finally {
                            cerrarRecursos(yo);
                            cerrarRecursos(rival);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cerrarRecursos(int index) {
        try {
            if (entradas.get(index) != null) entradas.get(index).close();
        } catch (IOException e) {
            System.out.println("Error al cerrar ObjectInputStream [" + index + "]");
        }

        try {
            if (salidas.get(index) != null) salidas.get(index).close();
        } catch (IOException e) {
            System.out.println("Error al cerrar ObjectOutputStream [" + index + "]");
        }

        try {
            // Este bloque puede omitirse sin problema
            Socket socket = ((Socket) salidas.get(index).getClass()
                .getDeclaredField("out").get(salidas.get(index)));
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (Exception e) {
            // Silencio aquí
        }
    }
}
