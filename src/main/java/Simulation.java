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

                if (command.length == 1) {
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
                        default -> throw new IllegalArgumentException(String.format(
                                "Invalid command: \"%s\" in file %s at %s", command[0], file.getName(), file.getPath()));
                    }
                } else if (command.length > 1) {

                    if (!command[1].matches("^\\d+,\\d+,(NORTH|EAST|SOUTH|WEST)$")) {
                        throw new IllegalArgumentException(String.format(
                                "Illegal command args for PLACE command: \"%s\" in file %s at %s", command[1], file.getName(), file.getPath()));
                    }
                    String[] args = command[1].split(",");

                    robot.place(args);
                }
            }

        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        }
    }
}
