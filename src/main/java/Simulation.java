import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Simulation {
    private Robot robot;
    private static final int TABLETOP_X_GRID_BOUNDARY = 5;
    private static final int TABLETOP_Y_GRID_BOUNDARY = 5;

    public Simulation() {
        robot = new Robot(null, null, TABLETOP_X_GRID_BOUNDARY, TABLETOP_Y_GRID_BOUNDARY);
    }

    public void readCommand(File file) throws IOException {

        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] command = line.split(" ");
                int lineNumber = 0;

                for (String s : command) {
                    System.out.println("CMD:" + s + "*");
                }

                if (!command[0].equals("PLACE")) {
                    switch (command[0]) {
                        case "MOVE" -> {
                            robot.move();
                        }
                        case "LEFT" -> {
                            robot.left();
                        }
                        case "RIGHT" -> {
                            robot.right();
                        }
                        case "REPORT" -> {
                            robot.report();
                        }
                        default ->
                                throw new IllegalArgumentException(String.format("Unknown command: \"%s\" in file %s at %s", command[0], file.getName(), file.getPath()));
                    }
                } else {

                    String[] args = command[1].split(",");
/*                    System.out.println(command[1]);
                    if (!command[1].matches("\\d,[\\d-],(NORTH|EAST|SOUTH|WEST)")) {
                        throw new IllegalArgumentException("Illegal command args for PLACE command");
                        // TODO --> Bra sätt att hantera??
                    }*/

                    // TODO MÅSTE HANTERA FELAKTIGA VÄRDEN PÅ X,Y, -> DIRECTION
                    // TODO MÅSTE HANTERA CHECK PÅ LÄNGDEN PÅ ARRAYEN

                    /* Will not place robot if args for position is out of bounds for X_GRID_BOUNDARY or Y_GRID_BOUNDARY*/
                    robot.place(args);
                }
            }
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        }
    }
}
