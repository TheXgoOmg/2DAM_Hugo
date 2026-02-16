import java.io.*;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Servidor {
     static int Port = 8888;
     static ExecutorService pool = Executors.newFixedThreadPool(10);
     static AtomicInteger jugadores = new AtomicInteger(0);
     static AtomicInteger dineroEnJuego = new AtomicInteger(0);
     static File logs = new File("logs.csv");
     static FileOutputStream fout;

    public Servidor() throws IOException {
        try (
            ServerSocket skSocket = new ServerSocket(Port);
        )
        {
            System.out.println("Servidor iniciado, puerto: " + Port);

            fout = new FileOutputStream(logs);
            EscribirLog("ID,CANTIDAD,NUMERO,GANADOR,GANANCIA");

            for (int i = 1; true; i++) {
                Socket sClient = skSocket.accept();
                jugadores.incrementAndGet();

                int id = i;
                pool.execute(() -> ManejarCliente(sClient, id));
            }
        } catch(Exception e) {
            System.err.println("Error del servidor: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        new Servidor();
    }

    public static void EscribirLog(String... datos) throws IOException {
        String lineaLog = String.join(",", datos) + "\n";
        fout.write(lineaLog.getBytes());
    }

    public void ManejarCliente(Socket socket, int id) {
        System.out.printf("Jugador %d conectado%n", id);

        int saldoInicial = 1000;
        int saldo = saldoInicial;

        try (
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        ) {
            dos.writeUTF("BIENVENIDO:" + saldoInicial);

            int nApuesta = 0;
            int cApuesta = 0;

            boolean salir = false;
            while (!salir) {
                String mensaje = dis.readUTF();
                if (mensaje.equals("SALDO")) {
                    dos.writeUTF("SALDO:" + saldo);
                }
                else if (mensaje.startsWith("APOSTAR:")) {
                    try {
                        int cIntroducido = Integer.parseInt(mensaje.split(":")[1]);
                        int nIntroducido = Integer.parseInt(mensaje.split(":")[2]);


                        if (cIntroducido < 10 && cIntroducido != 1) {
                            dos.writeUTF("APUESTA_ERROR:Apuesta mínima 10");
                        } else if (cIntroducido > saldo) {
                            dos.writeUTF("APUESTA_ERROR:Saldo insuficiente");
                        } else {
                            int apuestaAntigua = cApuesta;
                            cApuesta = cIntroducido;

                            if (nIntroducido >= 0 && nIntroducido <= 36) {
                                nApuesta = nIntroducido;
                                dineroEnJuego.addAndGet(cApuesta-apuestaAntigua);
                                dos.writeUTF("APUESTA_OK");
                            } else {
                                dos.writeUTF("APUESTA_ERROR:Número apostado inválido");
                            }
                        }
                    } catch (NumberFormatException e) {
                        dos.writeUTF("APUESTA_ERROR:Formato inválido");
                    } catch (Exception e) {
                        dos.writeUTF("APUESTA_ERROR:Comando inválido");
                    }
                } else if (mensaje.startsWith("GIRAR")) {
                    if (cApuesta == 1 && nApuesta == 10) {
                        dos.writeUTF("SORPRESA");
                    }

                    if (saldo < cApuesta) {
                        dos.writeUTF("APUESTA_ERROR:Saldo insuficiente");
                        continue;
                    }
                    Random random = new Random();
                    int nRandom = random.nextInt(37);

                    int ganancia;

                    if (nRandom == nApuesta) {
                        ganancia = cApuesta * 36;
                        saldo += ganancia;
                    } else {
                        ganancia = 0;
                        saldo -= cApuesta;
                    }

                    dos.writeUTF("RESULTADO:"+nRandom+":"+ganancia);

                    EscribirLog(String.valueOf(id), String.valueOf(cApuesta), String.valueOf(nApuesta), String.valueOf(nRandom), String.valueOf(ganancia));
                }
                else if (mensaje.equals("ESTADISTICAS")) {
                    dos.writeUTF("JUGADORES:"+jugadores.get()+",DINERO_EN_JUEGO:"+dineroEnJuego.get());
                }
                else if (mensaje.equals("ALINA")) {
                    dos.writeUTF("Esa es mi amorcito. Y LA AMOO COMO A NADIEEEE");
                }
                else if (mensaje.equals("LOGS")) {
                    StringBuilder lineaLogs = new StringBuilder();

                    lineaLogs.append("ULTIMOS LOGS").append("\n");

                    try (
                            FileInputStream fis = new FileInputStream(logs)
                    ) {
                        int byteLeido = 0;

                        // Leer y mostrar la cabecera
                        while ((byteLeido = fis.read()) != '\n') {
                            lineaLogs.append((char) byteLeido);
                        }
                        lineaLogs.append("\n");

                        ArrayList<String> logs = new ArrayList<>();

                        // Leer y mostrar los logs
                        StringBuilder linea = new StringBuilder();
                        while ((byteLeido = fis.read()) != -1) {
                            if (byteLeido == '\n') {
                                logs.add(linea.toString());
                                linea = new StringBuilder();
                            }
                            linea.append((char) byteLeido);
                        }

                        while (!logs.isEmpty()) {
                            lineaLogs.append(logs.getLast()).append("\n");
                            logs.remove(logs.getLast());
                        }

                        dos.writeUTF(lineaLogs.toString());
                    }
                }
                else if (mensaje.equals("SALIR")) {
                    dos.writeUTF("ADIOS");
                    System.out.printf("Jugador %d desconectado%n", id);
                    jugadores.decrementAndGet();
                    dineroEnJuego.addAndGet(-cApuesta);
                    salir = true;
                } else {
                    dos.writeUTF("ERROR:Comando invalido");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}