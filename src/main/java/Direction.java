// Type-safe --> Other than enum constant or null ==> compile error
enum Direction {
    NORTH(0, 1), EAST(1, 0), SOUTH(0, -1), WEST(-1, 0);

    // TODO final??? --> Sök på nätet --> Är de privata från början?
    private final int x;
    private final int y;
    private final int leftRotation;
    private final int rightRotation;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
        this.leftRotation = (ordinal() + 3) % 4;
        this.rightRotation = (ordinal() + 1) % 4;
        // TODO ta bort magic numbers --> values().size
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction left() {
        // ordinal value of the enum constant on which the left() was called
        return values()[leftRotation];
    }

    public Direction right() {
        return values()[rightRotation];
    }
}
