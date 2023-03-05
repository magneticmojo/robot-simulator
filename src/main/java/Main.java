import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // TODO Exception handling
        String filePath = args[0];
        Main main = new Main();
        main.run(filePath);
    }

    public void run(String filePath) throws FileNotFoundException {
        FileInputHandler fileInputHandler = new FileInputHandler();
        //File file = fileInputHandler.getFile("src/main/resources/3.in");
        File file = fileInputHandler.getFile(filePath);
        Simulation simulation = new Simulation();
        simulation.readCommand(file);
    }
}



