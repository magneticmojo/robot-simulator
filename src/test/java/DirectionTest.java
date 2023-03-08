import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Björn Forsberg
 */
class DirectionTest {

    private Direction[] directions;
    private Direction north = Direction.NORTH;

    @BeforeEach
    void setUp() {
        directions = Direction.values();
    }

    @AfterEach
    void tearDown() {
        directions = null;
    }

    @Test
    void testGetXAndGetYForNORTH() {
        Direction dir = Direction.NORTH;
        assertEquals(0, dir.getX());
        assertEquals(1, dir.getY());
    }

    @Test
    void testGetYAndGetXForEAST() {
        Direction dir = Direction.EAST;
        assertEquals(1, dir.getX());
        assertEquals(0, dir.getY());
    }

    @Test
    void testGetXAndGetYForSOUTH() {
        Direction dir = Direction.SOUTH;
        assertEquals(0, dir.getX());
        assertEquals(-1, dir.getY());
    }

    @Test
    void testGetYAndGetXForWEST() {
        Direction dir = Direction.WEST;
        assertEquals(-1, dir.getX());
        assertEquals(0, dir.getY());
    }

    @Test
    void testLeftSingleRotation() {
        assertEquals(Direction.WEST, north.left());
    }

    @Test
    void testLeftDubbleRotation() {
        assertEquals(Direction.SOUTH, north.left().left());
    }

    @Test
    void testLeftTripleRotation() {
        assertEquals(Direction.EAST, north.left().left().left());
    }

    @Test
    void testLeftFullRotation() {
        assertEquals(Direction.NORTH, north.left().left().left().left());
    }

    @Test
    void testRightSingleRotation() {
        assertEquals(Direction.EAST, north.right());
    }

    @Test
    void testRightDubbleRotation() {
        assertEquals(Direction.SOUTH, north.right().right());
    }

    @Test
    void testRightTripleRotation() {
        assertEquals(Direction.WEST, north.right().right().right());
    }

    @Test
    void testRightFullRotation() {
        assertEquals(Direction.NORTH, north.right().right().right().right());
    }

    @Test
    void testLengthOfDirectionsArray() {
        assertEquals(4, directions.length);
    }

    @Test
    void testEnumConstantsIndicesInValuesArray() {
        assertEquals(Direction.NORTH, directions[0]);
        assertEquals(Direction.EAST, directions[1]);
        assertEquals(Direction.SOUTH, directions[2]);
        assertEquals(Direction.WEST, directions[3]);
    }

    @Test
    void testIdentifiersForEnumConstantsWithValueOf() {
        assertEquals(Direction.NORTH, Direction.valueOf("NORTH"));
        assertEquals(Direction.EAST, Direction.valueOf("EAST"));
        assertEquals(Direction.SOUTH, Direction.valueOf("SOUTH"));
        assertEquals(Direction.WEST, Direction.valueOf("WEST"));
    }
}
