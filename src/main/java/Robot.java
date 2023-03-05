public class Robot {
    private Position position;
    private Direction direction;
    private boolean isOnTable;
    private Grid tableTop; // TODO

    public Robot(Position position, Direction direction, Grid tableTop) {
        this.position = position;
        this.direction = direction;
        this.tableTop = tableTop;
    }

    // TODO Remove if not used?
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position newPosition) {
        this.position = newPosition;
    }

    public boolean isInBoundsFor(Position newPosition) {
        return tableTop.positionInBounds(newPosition);
    }

    // TODO Remove if not used?
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isOnTable() {
        return isOnTable;
    }

    // Place
    public void place(Position newPosition, Direction newDirection) {
        if (isInBoundsFor(newPosition)) {
            setPosition(newPosition);
            setDirection(newDirection);
            isOnTable = true;
        }
    }

    // Move
    public void move() {
        Position newPosition = new Position(
                position.getX() + direction.getX(), position.getY() + direction.getY());
        if (isInBoundsFor(position)) {
            position = newPosition;
        }

    }

    // Left
    public void left() {
        setDirection(direction.left());
    }

    public void right() {
        setDirection(direction.right());
    }

    public void report() {
        // TODO vad fan va det Max sa?
        System.out.println("Output: " + position + "," + direction);
    }

    // TODO formattera snyggare?
    @Override
    public String toString() {
        String position = (this.position != null) ? this.position.toString() : "N/A";
        String direction = (this.direction != null) ? this.direction.toString() : "N/A";
        String grid = (this.tableTop != null) ? this.tableTop.toString() : "N/A";
        return "Robot [on table: " + isOnTable +
                ", position: " + position +
                ", direction: " + direction +
                ", grid dimensions: " + grid + "]";

    }

}
