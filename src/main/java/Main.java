import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No argument provided");
        }
        String filePath = args[0];
        Main main = new Main();
        main.run(filePath);
    }

    public void run(String filePath) {
        try {
            FileInputHandler fileInputHandler = new FileInputHandler();
            File file = fileInputHandler.getFile(filePath);

            Simulation simulation = new Simulation();
            simulation.readCommand(file);

        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
            // TODO skicka med meddelande?
            // TODO throws och try och catch ok?
        }
    }
}


