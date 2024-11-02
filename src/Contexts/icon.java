package Contexts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class icon implements HttpHandler {
    private byte[] bytes;
    public icon() throws IOException {
        InputStream reader = new FileInputStream("img/icon.png");
        bytes = reader.readAllBytes();
        reader.close();
    }
    public void handle(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, bytes.length);
        OutputStream stream = exchange.getResponseBody();
        stream.write(bytes);
        stream.close();
    }
}