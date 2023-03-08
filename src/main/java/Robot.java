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

    public void place(String[] args) {
        Position newPosition = new Position(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        if (isInBoundsFor(newPosition)) {
            position = newPosition;
            direction = Direction.valueOf(args[2]);
            isOnTable = true;
        }
    }

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
