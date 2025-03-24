import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class RequestProcessor implements Runnable {
    private Socket clientSocket;

    RequestProcessor(Socket socket) {
        this.clientSocket = socket;
    }

    private void process(Socket socket) {
        HttpContext context = new HttpContext(socket);
        HttpRequest request = context.getRequest();
        HttpResponse response = context.getResponse();
        System.out.println(request.getMethod());
        System.out.println(request.getUrl());
        File fileRequested = new File("public/" + request.getUrl().substring(1));
        if (request.getUrl().equals("/")) {
            response.ok("OK");
            response.sendFile("text/html", "public/index.html");
        } else if (fileRequested.getPath().substring(fileRequested.getPath().length() - 3).equals("css")
                && fileRequested.exists()) {
            response.ok("OK");
            response.sendFile("text/css", fileRequested.getPath());
        } else if (fileRequested.getPath().substring(fileRequested.getPath().length() - 3).equals("png")
                && fileRequested.exists()) {
            response.ok("OK");
            response.sendFile("image/png", fileRequested.getPath());
        } else {
            response.notFound("Not Found");
        }

        try {
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        process(clientSocket);
    }
}
