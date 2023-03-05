import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Simulation {
    Robot robot;

    // TODO new Grid instansierad här???
    Simulation() {
        robot = new Robot(null, null, new Grid(5, 5));
    }

    public void readCommand(File file) throws FileNotFoundException {

        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] command = line.split(" ");

                // Other than PLACE cmd + No robot on table
                if (robot.isOnTable()) {
                    switch (command[0]) {
                        case "MOVE" -> {
                            robot.move();
                            System.out.println("MOVE --> " + robot);
                        }
                        case "LEFT" -> {
                            System.out.println(command[0]);
                            robot.left();
                            System.out.println(robot);
                        }
                        case "RIGHT" -> {
                            System.out.println(command[0]);
                            robot.right();
                            System.out.println(robot);
                        }
                        case "REPORT" -> {
                            System.out.println(command[0]);
                            robot.report();
                            System.out.println("Output: " + robot);
                        }
                        default ->
                                //TODO MalformedFileException not exist
                                throw new IllegalArgumentException("Unknown command: " + command[0]);

                    }
                } else if (command[0].equals("PLACE")) {
                    // TODO saknas det en klass emellan Simulation och Robot?
                    System.out.print(command[0] + " ");
                    System.out.println(command[1]);

                    String[] coordinatesAndDirection = command[1].split(",");
                    int x = Integer.parseInt(coordinatesAndDirection[0]);
                    int y = Integer.parseInt(coordinatesAndDirection[1]);

                    Position newPosition = new Position(x, y);
                    Direction newDirection = Direction.valueOf(coordinatesAndDirection[2]);
                    // TODO
                    // Visat inte tydligt att kommandot kan ignoreras
                    // isOnTable sätts alltid till true
                    robot.place(newPosition, newDirection);

                }
            }
        }
    }
}
