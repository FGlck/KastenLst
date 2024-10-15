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
        server = HttpServer.create(                                                                         //create Server
            new InetSocketAddress(                                                                          //create socket address
                scanner.nextInt())                                                                          //console input
            , 0);                                                                                   //backlog
        System.out.println("Server initializing sucsesful!");                                             //console output
        System.out.println("Seting Executor...");                                                         //console output
        server.setExecutor(null);                                                                  //set executor
        System.out.println("Executor set!");                                                              //console output
        scanner.close();                                                                                    //close Scanner
        System.out.println("Done with Configuration!");                                                   //console output
        System.out.println("Creating Contexts");                                                          //console output
        createContexts();                                                                                   //create contexts
        System.out.println("Creation sucsesful!");                                                        //console output
        System.out.println("Starting Server...");                                                         //console output
        server.start();                                                                                     //start server
        System.out.println("sucsesful! Go to Main Loop...");                                              //console output
        Loop();                                                                                             //go to Main Loop
    }
    private static void onExeception(Exception e) {                                                         //When Exception Thrown
        System.err.print("Programm crashed with following Exeception:");                                  //console output
        System.err.println(e.getMessage());                                                                 //console output
        System.err.println("StackTrace:");                                                                //console output
        System.err.println(e.getStackTrace());                                                              //console output
        System.err.println("Exit Programm...");                                                           //console output
        System.exit(2);                                                                              //exit programm
    }
    private static void createContexts() throws IOException {                                               //create contexts:
        server.createContext("/index", new Contexts.index());                                          //create /index context
        server.createContext("/img/logo.png", new Contexts.logo());                                    //create /img/logo.png
    }
    private static void Loop() {                                                                            //Main Loop
        while (true) {                                                                                      //Repeat Forever

        }
    }
}
