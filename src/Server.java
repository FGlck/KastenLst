import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import java.util.Scanner;

public class Server {
    private static HttpServer server;

    public static void main(String[] args) {                                                                //Launch method
        try {                                                                                               //Try:
            run();                                                                                          //Start Main Methdod
        } catch (Exception e) {                                                                             //When Exeption Throwed:
            onExeception(e);                                                                                //Handle Exeception
        }
    }

    private static void run() throws Exception {
        Scanner scanner = new Scanner(System.in);                                                           //create Scanner
        System.out.print("Enter Server Port: ");                                                          //console output
        server = HttpServer.create(new InetSocketAddress(scanner.nextInt()/*console input*/), 0);   //create Server
        System.out.println("Server initializing sucsesful!");                                             //console output
        scanner.close();                                                                                    //close Scanner
        System.out.println("Done with Configuration!");                                                   //console output
        System.out.println("Creating Contexts");                                                          //console output
        createContexts();                                                                                   //create contexts
        System.out.println("Creation sucsesful!");                                                        //console output
        System.out.println("Starting Server...");                                                         //console output
        server.start();                                                                                     //start server
        System.out.println("sucsesful! Go to Main Loop...");                                              //console output
        Loop();                                                                                             //go to Loop
    }
    private static void onExeception(Exception e) {
        System.err.print("Programm crashed with following Exeception:");
        System.err.println(e.getMessage());
        System.err.println("StackTrace:");
        System.err.println(e.getStackTrace());
        System.err.println("Exit Programm...");
        System.exit(2);
    }
    private static void createContexts() {
    }
    private static void Loop() {
        while (true) {
            
        }
    }
}
