import java.net.Socket;

public class HttpContext {
    private Socket socket;

    HttpContext(Socket socket) {
        this.socket = socket;
    }

    public HttpRequest getRequest() {
        return new HttpRequest(socket);
    }

    public HttpResponse getResponse() {
        return new HttpResponse(socket);
    }

    public void close() {
        try {
            this.socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
