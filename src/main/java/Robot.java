/**
 * The Robot class represents a robot on a 2D tabletop grid that can be placed, moved, and rotated
 * based on commands. The robot's position is tracked as an (x, y) coordinate pair, and its
 * direction is tracked as one of four cardinal directions (north, east, south or west). The grid is
 * defined by two boundaries, an x boundary and a y boundary, and the robot cannot move or be placed
 * outside these bounds. The robot can report its current position and direction.
 *
 * @author Björn Forsberg
 */
public class Robot {

    private Position position;
    private Direction direction;
    private boolean isOnTable;
    private final int xGridBoundary;
    private final int yGridBoundary;

    public Robot(Position position, Direction direction, int xGridBoundary, int yGridBoundary) {
        this.position = position;
        this.direction = direction;
        this.xGridBoundary = xGridBoundary;
        this.yGridBoundary = yGridBoundary;
    }

    /**
     * Places the robot on the tabletop at the given position and direction, if the position is within the grid boundaries.
     *
     * @param args An array of String arguments containing the x-coordinate, y-coordinate, and direction values.
     */
    public void place(String[] args) {
        Position newPosition = new Position(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        if (isInBoundsFor(newPosition)) {
            position = newPosition;
            direction = Direction.valueOf(args[2]);
            isOnTable = true;
        }
    }

    /**
     * Moves the robot one unit in its current direction, if the robot
     * is currently on the tabletop and the new position is within the grid boundaries.
     */
    public void move() {
        if (isOnTable) {
            Position newPosition = new Position(
                    position.x() + direction.getX(), position.y() + direction.getY());
            if (isInBoundsFor(newPosition)) {
                position = newPosition;
            }
        }
    }

    private boolean isInBoundsFor(Position newPosition) {
        int x = newPosition.x();
        int y = newPosition.y();
        return x >= 0 && x <= xGridBoundary && y >= 0 && y <= yGridBoundary;
    }

    public void left() {
        if (isOnTable) {
            direction = direction.left();
        }
    }

    public void right() {
        if (isOnTable) {
            direction = direction.right();
        }
    }

    public void report() {
        if (isOnTable) {
            System.out.printf("Output: %d,%d,%s%n", position.x(), position.y(), direction);
        }
    }

    @Override
    public String toString() {
        String pos = (position != null) ? position.toString() : "N/A";
        String dir = (direction != null) ? direction.toString() : "N/A";
        return String.format("Robot [on table: %b; position: %s; direction: %s; grid dimensions: %dx%d units]",
                isOnTable, pos, dir, xGridBoundary, yGridBoundary);

    }
}
