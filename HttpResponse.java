import java.net.Socket;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.File;

public class HttpResponse {
    private OutputStream output;

    HttpResponse(Socket socket) {
        try {
            this.output = socket.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ok(String message) {
        try {
            output.write(String.format("HTTP/1.0 200 %s\r\n", message).getBytes());
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void notFound(String message) {
        try {
            output.write(String.format("HTTP/1.0 404 %s\r\n", message).getBytes());
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendContent(String contentType, String content) {
        try {
            output.write(("Content-Type: " + contentType + "\r\n").getBytes());
            output.write(("\r\n").getBytes());
            output.write(content.getBytes());
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendFile(String contentType, String filename) {
        try {
            File file = new File(filename);
            FileInputStream fileInput = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int bytesRead = 0;
            output.write(("Content-type: " + contentType + "\r\n").getBytes());
            output.write(("\r\n").getBytes());
            do {
                bytesRead = fileInput.read(buffer);
                output.write(buffer, 0, bytesRead);
            } while (bytesRead == 4096);
            output.flush();
            fileInput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}