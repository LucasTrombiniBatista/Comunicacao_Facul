import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor iniciado e aguardando conexões na porta " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Conexão estabelecida com o cliente " + clientSocket.getInetAddress());

                // Streams de entrada e saída
                InputStream input = clientSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                OutputStream output = clientSocket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                // Recebe a mensagem do cliente
                String clientMessage = reader.readLine();
                System.out.println("Mensagem recebida do cliente: " + clientMessage);

                // Envia resposta de confirmação
                writer.println("Mensagem recebida com sucesso!");

                clientSocket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
