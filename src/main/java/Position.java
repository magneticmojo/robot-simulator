/**
 * The Position record represents a point in 2D space.
 *
 * @author Björn Forsberg
 */
record Position(int x, int y) {
    @Override
    public String toString() {
        return String.format("%d,%d", x, y);
    }
}
