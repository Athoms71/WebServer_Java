import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    public void run(int portNumber) {
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            while (true) {
                Socket socket = serverSocket.accept();
                RequestProcessor request = new RequestProcessor(socket);
                Thread thread = new Thread(request);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
