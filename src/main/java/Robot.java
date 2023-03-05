public class Robot {
    private Position position;
    private Direction direction;
    private boolean isOnTable;
    private int xGridBoundary;
    private int yGridBoundary;

    public Robot(Position position, Direction direction, int xGridBoundary, int yGridBoundary) {
        this.position = position;
        this.direction = direction;
        this.xGridBoundary = xGridBoundary;
        this.yGridBoundary = yGridBoundary;
    }

    private boolean isInBoundsFor(Position newPosition) {
        int x = newPosition.getX();
        int y = newPosition.getY();
        return x >= 0 && x <= xGridBoundary && y >= 0 && y <= yGridBoundary;
    }

    public boolean isOnTable() {
        return isOnTable;
    }

    // Place
    public void place(int x, int y, String dir) {
        Position newPosition = new Position(x, y);

        if (isInBoundsFor(newPosition)) {
            position = newPosition;
            direction = Direction.valueOf(dir);
            isOnTable = true;
        }
    }

    // Move
    public void move() {
        Position newPosition = new Position(
                position.getX() + direction.getX(), position.getY() + direction.getY());
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
        return "Output: " + position + "," + direction;
    }

    @Override
    public String toString() {
        String pos = (position != null) ? position.toString() : "N/A";
        String dir = (direction != null) ? direction.toString() : "N/A";
        return "Robot [on table: " + isOnTable +
                ", position: " + pos +
                ", direction: " + dir +
                ", grid dimensions: " + xGridBoundary + "x" + yGridBoundary + " units]";

    }

}
