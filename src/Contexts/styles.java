package Contexts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class styles implements HttpHandler {
    private String fileContext = "";
    public styles() throws IOException {
        InputStream reader = new FileInputStream("HTML/styles.css");
        while (reader.available() > 0) {
            fileContext += (char)reader.read();
        }
        reader.close();
    }
    public void handle(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, fileContext.length());
        OutputStream stream = exchange.getResponseBody();
        stream.write(fileContext.getBytes());
        stream.close();
    }
}