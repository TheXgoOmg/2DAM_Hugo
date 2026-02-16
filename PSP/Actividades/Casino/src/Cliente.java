import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    static final String Host = "localhost";
    static final int Port = 8888;

    public Cliente() throws IOException {
        Socket socket = new Socket(Host,Port);
        try (
                Scanner sc = new Scanner(System.in);
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        ) {
            boolean salir = false;
            while (!salir) {
                String mensajeServer = dis.readUTF();

                if (mensajeServer.startsWith("SORPRESA")) {
                    Sorpresa();
                } else {
                    System.out.println("[Servidor] "+mensajeServer);
                }

                if (mensajeServer.equals("ADIOS")) {
                    salir = true;
                    continue;
                }

                System.out.print("> ");
                String mensaje;
                mensaje = sc.nextLine();

                if (!mensaje.isEmpty()) {
                    dos.writeUTF(mensaje);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Cliente();
    }

    public static void Sorpresa(){
        Scanner sc = new Scanner(System.in);

        System.out.print("[HUGO (tu chikistrikis)] ");
        System.out.println("Hola mi amorcito.\n");
        Salto(sc);
        System.out.println("Te amo como a nadie.\nTodo lo que siento cuando estoy contigo.\n");
        Salto(sc);
        System.out.println("La paz que me haces sentir.\nLo mucho que me cuidas.\n");
        Salto(sc);
        System.out.println("Lo cual espero poder devolvértelo lo más pronto posible.\nNunca pararé de intentar darte lo mejor de mí.\n\n");
        Salto(sc);
        System.out.println("Por ello quiero pedirtelo.\nDe la manera más mía que sé.\n");
        Salto(sc);
        System.out.println(
                """
                        
                        
                        
                        ¿Quieres ser mi San Valentín para hoy y para todos los días de San Valentín que nos quedan vivir?
                        
                        ₊      ・      ₊               ₊            °        ☆
                             ☆    ₊          ⋆.       ₊        ★           ⊹   \s
                                      ⟡     ⊹             .                     ☾
                         ⋆      .                  ⟡      .         ₊         .     \s
                                           ˖                   ˖           ° \s
                        ☾     ｡   ∩―――――――――――――∩  ₊        ⊹         ☆
                                  || ∧,,∧ ∧,,,∧ ||       .            ⋆
                            .  ⋆  ||(˶´ ｰ(˶-ω-˶)||  \uD835\uDCC2\uD835\uDCCC\uD835\uDCB6 \uD83D\uDC96       ₊
                          ☆       |ﾉ￣ づ   ⌒⌒   ＼     ⋆         .\s
                        .      .  (　 ノ　 ⌒⌒ ヽ    ＼      ₊         ☾
                              ₊   ＼   ノ||￣￣￣￣￣||         ₊ \s
                        　           ＼,ﾉ||￣￣￣￣￣||
                        
                        """);

    }

    public static void Salto(Scanner sc){
        sc.nextLine();
    }
}
