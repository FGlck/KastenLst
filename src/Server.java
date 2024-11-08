import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import Contexts.background;
import Contexts.font;
import Contexts.icon;
import Contexts.index;
import Contexts.logo;
import Contexts.nfound;
import Contexts.randchatbtn;
import Contexts.search;
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
            , 0);                                                                                   //backlog
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
        server.createContext("/index.html", new index());                                              //create /index.html context
        server.createContext("/styles.css", new styles());                                             //create /styles.css
        server.createContext("/font.ttf", new font());                                                 //create /font.ttf
        server.createContext("/img/logo.png", new logo());                                             //create /img/logo.png
        server.createContext("/img/icon.png", new icon());                                             //create /img/icon.png
        server.createContext("/img/search.png", new search());                                         //create /img/search.png
        server.createContext("/img/randchatbtn.png", new randchatbtn());                               //create /img/randchatbtn.png
        server.createContext("/img/background.mp4", new background());                                 //create /img/background.mp4
        server.createContext("/", new nfound());                                                       //create not found
    }
    private static void removeContexts() throws IOException {                                               //remove contexts:
        server.removeContext("/index.html");                                                           //remove /index.html context
        server.removeContext("/styles.css");                                                           //remove /styles.css
        server.removeContext("/font.ttf");                                                             //remove /font.ttf
        server.removeContext("/img/logo.png");                                                         //remove /img/logo.png
        server.removeContext("/img/icon.png");                                                         //remove /img/icon.png
        server.removeContext("/img/search.png");                                                       //remove /img/search.png
        server.removeContext("/img/randchatbtn.png");                                                  //remove /img/randchatbtn.png
        server.removeContext("/img/background.mp4");                                                   //remove /img/background.mp4
        server.removeContext("/");                                                                     //remove not found
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
            System.out.print(">");                                                                      //console output
            String input = System.console().readLine();                                                   //console input
            input = input.trim();                                                                         //trim input
            if (input.startsWith("rl") || input.startsWith("reload")) {                     //check input
                System.out.println("Reloading Server...");                                              //console output
                System.out.println("Removing Contexts...");                                             //console output
                removeContexts();                                                                         //remove contexts
                System.out.println("Regenerating Contexts...");                                         //console output
                createContexts();                                                                         //create contexts
                System.out.println("Done!");                                                            //console output
            } else if (input.startsWith("s") || input.startsWith("stop")) {                 //check input
                System.out.println("Stopping Server...");                                               //console output
                server.stop(0);                                                                     //stop Server
                System.out.println("Exiting...");                                                       //console output
                System.exit(0);                                                                    //exit
            } else if (input.startsWith("a") || input.startsWith("address")) {              //check input
                System.out.println("Server Port: " + server.getAddress().getPort());                      //console output
                System.out.println("Server IP: " + InetAddress.getLocalHost().getHostAddress());          //console output
            } else if (input.startsWith("h") || input.startsWith("help")) {                 //check input
                System.out.println("Available Commands:");                                              //console output
                System.out.println("\"reload\" / \"rl\": Reload Contexts");                             //console output
                System.out.println("\"help\" / \"h\": Print this Help");                                //console output
                System.out.println("\"stop\" / \"s\": Stop Server and Exit");                           //console output
                System.out.println("\"address\" / \"a\": Print Server address");                        //console output
            } else {
                System.err.println("Invalid Command, type \"help\" to see available commands.");        //console output
            }
        }
    }
}
