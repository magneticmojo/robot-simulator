import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Simulation {
    private Robot robot;
    private final int X_GRID_BOUNDARY = 5;
    private final int Y_GRID_BOUNDARY = 5;

    public Simulation() {
        robot = new Robot(null, null, X_GRID_BOUNDARY, Y_GRID_BOUNDARY);
    }

    public void readCommand(File file) throws FileNotFoundException {

        // TODO catch IOEXCeption
        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] command = line.split(" ");

                if (robot.isOnTable() && !command[0].equals("PLACE")) {
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
                            System.out.println(robot.report());
                        }
                        default ->
                                throw new IllegalArgumentException("Unknown command: " + command[0]);
                    }
                } else if (command[0].equals("PLACE")) {
                    String[] args = command[1].split(",");
                    if (args.length != 3) {
                        throw new IllegalArgumentException();
                    }

                    robot.place(args);

                }
            }
        }
    }
}
