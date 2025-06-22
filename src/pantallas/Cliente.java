package pantallas;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Cliente {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private PersonajeDisney[] personajesRecibidos;

    private Runnable onStart;
    private Runnable onTuTurno;
    private Runnable onEspera;
    private Consumer<String> onMensaje;
    private String nombreOponente;
    private final Map<String, Consumer<Object>> handlersObjeto = new HashMap<>();

public String getNombreOponente() {
    return nombreOponente;
}

    public void setOnMensaje(Consumer<String> onMensaje) {
        this.onMensaje = onMensaje;
    }

    public boolean conectar(String host, int puerto, Jugador jugador) {
        try {
            socket = new Socket(host, puerto);

            // IMPORTANTE: primero ObjectOutputStream, luego flush, luego ObjectInputStream
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            // Enviar datos del jugador
            out.writeObject(jugador);
            out.flush();

            return true;
        } catch (IOException e) {
            System.out.println("Error al conectar con el servidor.");
            e.printStackTrace();
            return false;
        }
    }

public void escucharMensajes(Runnable onStart, Runnable onTuTurno, Runnable onEspera) {
    this.onStart = onStart;
    this.onTuTurno = onTuTurno;
    this.onEspera = onEspera;

    new Thread(() -> {
        try {
            while (true) {
                Object mensaje = in.readObject();

                if ("start".equals(mensaje)) {
                    System.out.println("Recibido mensaje 'start' del servidor.");

                    Object personajes = in.readObject();
                    if (personajes instanceof PersonajeDisney[]) {
                        personajesRecibidos = (PersonajeDisney[]) personajes;
                        System.out.println("Personajes recibidos: " + personajesRecibidos.length);
                    } else {
                        System.err.println("Error: se esperaba un arreglo de PersonajeDisney");
                    }

                    Object oponente = in.readObject();
                    if (oponente instanceof String) {
                        nombreOponente = (String) oponente;
                        System.out.println("Oponente recibido: " + nombreOponente);
                    }

                    if (onStart != null) onStart.run();
                }

                else if ("tuTurno".equals(mensaje)) {
                    System.out.println("¡Es tu turno!");
                    if (onTuTurno != null) onTuTurno.run();
                }

                else if ("espera".equals(mensaje)) {
                    System.out.println("Espera tu turno...");
                    if (onEspera != null) onEspera.run();
                }

                else if (mensaje instanceof String str) {
                    if (str.startsWith("mensaje:")) {
                        String texto = str.substring(8); // quitar "mensaje:"
                        if (onMensaje != null) onMensaje.accept(texto);
                    } else if (str.startsWith("adivinar:")) {
                        String texto = str.substring(9); // quitar "adivinar:"
                        if (onMensaje != null) onMensaje.accept("adivinar:" + texto);
                    } else if (str.equals("¡Ganaste!") || str.equals("¡Ánimo!")) {
                        if (onMensaje != null) onMensaje.accept(str);
                    } else if (str.startsWith("Info:")) {
                        if (onMensaje != null) onMensaje.accept(str);
                    }
                }

                else if ("oponenteDesconectado".equals(mensaje)) {
                    System.out.println("Tu oponente se desconectó.");
                    // Aquí puedes cerrar el juego o mostrar una alerta
                }
                
                // Otros posibles mensajes
            }
        } catch (EOFException e) {
            System.out.println("El servidor cerró la conexión (EOF).");
            cerrarConexion();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al recibir mensaje del servidor.");
            e.printStackTrace();
            cerrarConexion();
        }
    }).start();
}

    public void enviarMensaje(String mensaje) {
        try {
            if (out != null) {
                out.writeObject(mensaje);
                out.flush();
            }
        } catch (IOException e) {
            System.out.println("Error al enviar mensaje al servidor.");
            e.printStackTrace();
        }
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
