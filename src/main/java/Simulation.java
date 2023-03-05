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

        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] command = line.split(" ");

                // TODO --> Möjligen en samling med Commandon för att undvika kontroller

                // Other than PLACE cmd + No robot on table
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
                            String report = robot.report();
                            System.out.println(report);
                            System.out.println(robot);
                        }
                        default ->
                                throw new IllegalArgumentException("Unknown command: " + command[0]);
                    }
                } else if (command[0].equals("PLACE")) {
                    String[] coordinatesAndDirection = command[1].split(",");
                    int x = Integer.parseInt(coordinatesAndDirection[0]);
                    int y = Integer.parseInt(coordinatesAndDirection[1]);
                    String dir = coordinatesAndDirection[2];
                    robot.place(x, y, dir);

                }
            }
        }
    }
}
