package Contexts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class nfound implements HttpHandler {
    private String fileContext = "";
    public nfound() throws IOException {
        InputStream reader = new FileInputStream("HTML/notfound.html");
        while (reader.available() > 0) {
            fileContext += (char)reader.read();
        }
        reader.close();
    }
    public void handle(HttpExchange exchange) throws IOException {
        String request = fileContext;
        exchange.sendResponseHeaders(200, request.length());
        OutputStream stream = exchange.getResponseBody();
        stream.write(request.getBytes());
        stream.close();
    }
}