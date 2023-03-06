import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Simulation {
    private Robot robot;
    private static final int X_GRID_BOUNDARY = 5;
    private static final int Y_GRID_BOUNDARY = 5;

    public Simulation() {
        robot = new Robot(null, null, X_GRID_BOUNDARY, Y_GRID_BOUNDARY);
    }

    public void readCommand(File file) {

        // TODO catch IOEXCeption
        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] command = line.split(" ");

                //TODO END OF FILE ????


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
                                // TODO get line number + file name / path
                    }
                } else if (command[0].equals("PLACE")) {


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
