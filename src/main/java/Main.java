import java.io.File;
import java.io.IOException;

/**
 * Main is the application entry point.
 *
 * @author Björn Forsberg
 */
public class Main {
    /**
     * @param args Program arguments used to input a file path.
     */
    public static void main(String[] args) {

        if (args.length == 0) {
            throw new IllegalArgumentException("No argument provided");
        }
        String filePath = args[0];
        Main main = new Main();
        main.run(filePath);
    }

    /**
     * @param filePath A path to the file used for input to the application.
     */
    public void run(String filePath) {
        try {
            FileInputHandler fileInputHandler = new FileInputHandler();
            File file = fileInputHandler.getFile(filePath);

            Simulation simulation = new Simulation();
            simulation.readCommand(file);

        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
        }
    }
}


