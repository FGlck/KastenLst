import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import Contexts.index;
import Contexts.logo;
import Contexts.redirect;

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
                readInteger())                                                                              //console input
            , 0);                                                                                   //???
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
    private static int readInteger() throws IOException {
        String input = "";
        while (true) {
            if (System.in.available() > 0) {
                char i = (char) System.in.read();
                if (i == '\n') {
                    return Integer.parseInt(input.trim());
                } else {
                    input += i;
                }
            }
        }
    }
    private static void createContexts() throws IOException {                                               //create contexts:
        server.createContext("/index", new index());                                                   //create /index context
        server.createContext("/img/logo.png", new logo());                                             //create /img/logo.png
        server.createContext("/", new redirect());
    }
    private static void StartLoop() {
        try {                                                                                               //Try:
            Loop();                                                                                         //go to Loop
        } catch (Exception e) {                                                                             //On Exception:
            onExeception(e);                                                                                //Handle Exeception
        }
    }
    private static void Loop() throws IOException {                                                         //Main Loop
        String input = "";                                                                                  //Input String
        while (true) {                                                                                      //Repeat Forever
            if (System.in.available() > 0) {
                char i = (char) System.in.read();
                input += i;
                if (i == '\n') {
                    System.out.println(input);
                    input = "";
                }
            }
        }
    }
}
