enum Direction {
    NORTH(0, 1), EAST(1, 0), SOUTH(0, -1), WEST(-1, 0);

    private final int x;
    private final int y;
    private final int leftRotation;
    private final int rightRotation;
    private final int DIRECTION_VALUES_SIZE = 4;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
        this.leftRotation = (ordinal() + 3) % DIRECTION_VALUES_SIZE;
        this.rightRotation = (ordinal() + 1) % DIRECTION_VALUES_SIZE;
        // TODO ta bort magic numbers --> values().size
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction left() { return values()[leftRotation]; }

    public Direction right() {
        return values()[rightRotation];
    }
}
