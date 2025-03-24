import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class HttpRequest {
    private String method;
    private String url;

    private void readClientRequest(Socket socket) {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = input.readLine();
            String[] parts = response.split(" ");
            method = parts[0];
            url = parts[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    HttpRequest(Socket socket) {
        readClientRequest(socket);
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }
}
