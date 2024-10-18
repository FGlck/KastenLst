public class Watchdog extends Thread {
    private boolean fine = true;
    public void run() {
        if (fine == false) {
            System.exit(2);
        }
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            System.err.println("Unknown Error in Watchdog Thread!");
            System.exit(2);
        }
    }
    public void execepted() {
        fine = false;
    }
}
