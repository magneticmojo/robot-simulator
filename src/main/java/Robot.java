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

    private boolean isInBoundsFor(Position newPosition) {
        int x = newPosition.x();
        int y = newPosition.y();
        return x >= 0 && x <= xGridBoundary && y >= 0 && y <= yGridBoundary;
    }

    public boolean isOnTable() {
        return isOnTable;
    }

    // Place
    public void place(String[] args) {
        Position newPosition = new Position(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        if (isInBoundsFor(newPosition)) {
            position = newPosition;
            direction = Direction.valueOf(args[2]);
            isOnTable = true;
        }
    }

    // Move
    public void move() {
        Position newPosition = new Position(
                position.x() + direction.getX(), position.x() + direction.getY());
        if (isInBoundsFor(newPosition)) {
            position = newPosition;
        }
    }

    // Left
    public void left() {
        direction = direction.left();
    }

    // Right
    public void right() {
        direction = direction.right();
    }

    public String report() {
        return "Output: " + position.x() + "," + position.y() + "," + direction;
    }

    @Override
    public String toString() {
        String pos = (position != null) ? position.x() + "," + position.y() : "N/A";
        String dir = (direction != null) ? direction.toString() : "N/A";
        return "Robot [on table: " + isOnTable +
                ", position: " + pos +
                ", direction: " + dir +
                ", grid dimensions: " + xGridBoundary + "x" + yGridBoundary + " units]";

    }

}
