import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import Contexts.icon;
import Contexts.index;
import Contexts.logo;
import Contexts.redirect;
import Contexts.styles;

public class Server {
    private static HttpServer server;                                                                       //Server Variable
    private static Watchdog Watchdog_TH;                                                                    //Watchdog Variable

    public static void main(String[] args) {                                                                //Launch method
        Watchdog_TH = new Watchdog();                                                                       //Create Watchdog
        Watchdog_TH.start();                                                                                //Start Watchdog
        try {                                                                                               //Try:
            run();                                                                                          //Start Main Methdod
        } catch (Exception e) {                                                                             //When Exeption Throwed:
            onExeception(e);                                                                                //Handle Exeception
        }
    }
    private static void run() throws Exception {
        System.out.print("Enter Server Port: ");                                                          //console output
        server = HttpServer.create(                                                                         //create Server
            new InetSocketAddress(                                                                          //create socket address
                Integer.parseInt(System.console().readLine()))                                              //console input
            , 0);                                                                                   //
        System.out.println("Server initializing sucsesful!");                                             //console output
        System.out.println("Seting Executor...");                                                         //console output
        server.setExecutor(null);                                                                  //set executor
        System.out.println("Executor set!");                                                              //console output
        System.out.println("Done with Configuration!");                                                   //console output
        System.out.println("Creating Contexts");                                                          //console output
        createContexts();                                                                                   //create contexts
        System.out.println("Creation sucsesful!");                                                        //console output
        System.out.println("Starting Server...");                                                         //console output
        server.start();                                                                                     //start server
        System.out.println("sucsesful! Go to Main Loop...");                                              //console output
        StartLoop();                                                                                        //Start Main Loop
    }                                                                                                       //Method End
    private static void onExeception(Exception e) {                                                         //When Exception Thrown
        Watchdog_TH.execepted();                                                                            //Trigger Watchdog
        throw new RuntimeException("Runtime Exception", e);                                         //Throw Runtime Exeption
    }                                                                                                       //Method End
    private static void createContexts() throws IOException {                                               //create contexts:
        server.createContext("/index", new index());                                                   //create /index context
        server.createContext("/styles.css", new styles());                                             //create /styles.css
        server.createContext("/img/logo.png", new logo());                                             //create /img/logo.png
        server.createContext("/img/icon.png", new icon());                                             //create /img/icon.png
        server.createContext("/", new redirect());                                                     //create redirection
    }
    private static void StartLoop() {
        try {                                                                                               //Try:
            Loop();                                                                                         //go to Loop
        } catch (Exception e) {                                                                             //On Exception:
            onExeception(e);                                                                                //Handle Exeception
        }
    }
    private static void Loop() throws Exception {                                                         //Main Loop
        while (true) {                                                                                    //Repeat Forever
            System.console().readLine();
            server.stop(100);
            System.exit(0);
        }
    }
}
