import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Main main = new Main();
        main.run();
    }

    public void run() throws FileNotFoundException {
        FileInputHandler fileInputHandler = new FileInputHandler();
        File file = fileInputHandler.getFile("src/main/resources/3.in");

        Simulation simulation = new Simulation();
        simulation.readCommand(file);
    }
}

class FileInputHandler {

    public File getFile(String filePath) throws IllegalArgumentException, FileNotFoundException {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("Invalid file path");
        }
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found " + filePath);
        }
        //Option to check if user has permition to read file with file.canRead()
        return file;
    }
}

class Simulation {
    TableTop tableTop;
    Robot robot;

    Simulation() {
        tableTop = new TableTop();
        robot = new Robot();
    }

    public void readCommand(File file) throws FileNotFoundException {

        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] command = line.split(" ");

                if (command[0].equals("PLACE")) {
                    System.out.print(command[0] + " ");
                    System.out.println(command[1]);
                    String[] coordinatesAndDirection = command[1].split(",");
                    int x = Integer.parseInt(coordinatesAndDirection[0]);
                    int y = Integer.parseInt(coordinatesAndDirection[1]);

                    if (tableTop.positionOutOfBounds(x, y)) {
                        continue;
                    }

                    robot.setDirection(Direction.valueOf(coordinatesAndDirection[2]));
                    robot.placeOnTable(x, y);

                } else {
                    if (robot.isOnTable) {
                        switch (command[0]) {
                            case "MOVE" -> {
                                Direction currentDirection = robot.getDirection();
                                if (currentDirection.name().equals("NORTH")) {
                                    int newY = robot.getPosition().getY() + 1;
                                    if (!tableTop.yOutOfBounds(newY)) {
                                        robot.setY(newY);
                                    }
                                } else if (currentDirection.name().equals("EAST")) {
                                    int newX = robot.getPosition().getX() + 1;
                                    if (!tableTop.xOutOfBounds(newX)) {
                                        robot.setX(newX);
                                    }
                                } else if (currentDirection.name().equals("SOUTH")) {
                                    int newY = robot.getPosition().getY() - 1;
                                    if (!tableTop.yOutOfBounds(newY)) {
                                        robot.setY(newY);
                                    }
                                } else if (currentDirection.name().equals("WEST")) {
                                    int newX = robot.getPosition().getX() - 1;
                                    if (!tableTop.xOutOfBounds(newX)) {
                                        robot.setX(newX);
                                    }
                                }
                                System.out.println("MOVE --> " + robot);
                            }
                            case "LEFT" -> {
                                System.out.println(command[0]);
                                Direction currentDirection = robot.getDirection();
                                Direction newDirection = currentDirection.left();
                                robot.setDirection(newDirection);
                                System.out.println(robot);
                            }
                            // TODO change value of Direction
                            case "RIGHT" -> {
                                System.out.println(command[0]);
                                Direction currentDirection = robot.getDirection();
                                Direction newDirection = currentDirection.right();
                                robot.setDirection(newDirection);
                                System.out.println(robot);
                            }
                            // TODO change value of Direction
                            case "REPORT" -> {
                                System.out.println(command[0]);
                                System.out.println("Output: " + robot);
                            }
                            // TODO SOUT Position + Direction
                            default ->
                                    // Invalid command
                                    System.out.println("Invalid command");
                        }
                    }
                }
            }
        }
    }
}

class Grid {
    int xBoundary;
    int yBoundary;

    public Grid(int xBoundary, int yBoundary) {
        this.xBoundary = xBoundary;
        this.yBoundary = yBoundary;
    }

/*    public int getxBoundary() {
        return xBoundary;
    }

    public int getyBoundary() {
        return yBoundary;
    }*/

    public boolean positionOutOfBounds(int x, int y) {
        return x < 0 || x > xBoundary || y < 0 || y > yBoundary;
    }

    public boolean xOutOfBounds(int x) {
        return x < 0 || x > xBoundary;
    }

    public boolean yOutOfBounds(int y) {
        return y < 0 || y > yBoundary;
    }
}

class TableTop extends Grid {
    public TableTop() {
        super(5, 5);
    }
}

class Robot {
    Position position = new Position();
    Direction facing;
    boolean isOnTable = false;

    public Position getPosition() {
        return position;
    }

    // TODO "a robot that is not on the table can chose to ignore MOVE, LEFT, RIGHT, and REPORT"
    // Move horizontally
    public void setX(int x) {
        if (isOnTable) {
            position.setX(x);
        }
    }

    // Move vertically
    public void setY(int y) {
        position.setY(y);
    }

    public void placeOnTable(int x, int y) {
        position.setX(x);
        position.setY(y);
        setOnTable();
    }

    public Direction getDirection() {
        return facing;
    }

    public void setDirection(Direction facing) {
        this.facing = facing;
    }

    private void setOnTable() {
        this.isOnTable = true;
    }

    @Override
    public String toString() {
        if (isOnTable) {
            return position + "," + facing.toString();
        }
        return "Robot not on table";
    }
}

class Position {
    int x;
    int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}

enum Direction {
    NORTH, EAST, SOUTH, WEST;

    public Direction left() {
        // ordinal value of the enum constant on which the left() was called
        return values()[(ordinal() + 3) % 4];
    }

    public Direction right() {
        return values()[(ordinal() + 1) % 4];
    }
}

// TODO FileInputHandler implements InputHandler
// TODO FileCommandHandler implements CommandHandler



