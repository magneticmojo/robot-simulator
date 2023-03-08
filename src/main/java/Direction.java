/**
 * The Direction enum represents the four cardinal directions.
 * Each direction has an x and y coordinate, as well as methods for rotating left and right.
 *
 * @author Björn Forsberg
 */
enum Direction {

    NORTH(0, 1), EAST(1, 0), SOUTH(0, -1), WEST(-1, 0);

    private static final int DIRECTION_VALUES_SIZE = 4;
    private static final int LEFT_ROTATION_INCREMENT = 3;
    private static final int RIGHT_ROTATION_INCREMENT = 1;
    private final int x;
    private final int y;
    private final int leftRotation;
    private final int rightRotation;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
        this.leftRotation = (ordinal() + LEFT_ROTATION_INCREMENT) % DIRECTION_VALUES_SIZE;
        this.rightRotation = (ordinal() + RIGHT_ROTATION_INCREMENT) % DIRECTION_VALUES_SIZE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction left() {
        return values()[leftRotation];
    }

    public Direction right() {
        return values()[rightRotation];
    }
}
