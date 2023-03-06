import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
// TODO göra manuellt sådant som inte sköts automatiskt i applicationen? Tex isOnTable = true;
// TODO använda setOnTable och report()????
class RobotTest {

    private Robot robotNotOnTable;
    private Robot robotOnTable;
    private String[] inBoundsPlacement = {"5", "5", "NORTH"};
    private String[] outOfBoundsPlacement = {"10", "10", "NORTH"};
    private String[] newValidPosition = {"3", "3", "SOUTH"};
    private Position originPosition = new Position(0,0);
    private Direction initialDirection = Direction.NORTH;
    private int xGridBoundary = 5;
    private int yGridBoundary = 5;

    @BeforeEach
    void setUp() {
        robotNotOnTable = new Robot(null, null, xGridBoundary, yGridBoundary);
        robotOnTable = new Robot(originPosition, initialDirection, xGridBoundary,yGridBoundary);
        // TODO kan bara sätta isOnTable genom ett kommando

    }

    @AfterEach
    void tearDown() {
        robotOnTable = null;
        robotNotOnTable = null;
    }

    @Test
    void isOnTable() {
    }

    @Test
    void testPlaceInBoundsForRobotNotOnTable() {
        assertFalse(robotNotOnTable.isOnTable());
        robotNotOnTable.place(inBoundsPlacement);
        assertEquals("Output: 5,5,NORTH", robotNotOnTable.report());
        assertTrue(robotNotOnTable.isOnTable());
    }

    @Test
    void testRobotNotOnTableForPlaceOutOfBoundsForRobotNotOnTable() {
        assertFalse(robotNotOnTable.isOnTable());
        robotNotOnTable.place(outOfBoundsPlacement);
        // TODO java.lang.NullPointerException: Cannot invoke "Position.x()" because "this.position" is null
        // TODO --> Ska man kunna anropa report? Fel att initiera med null?
        //assertEquals("", robotNotOnTable.report());
        assertEquals("Robot [on table: false; position: N/A; direction: N/A; grid dimensions: 5x5 units]", robotNotOnTable.toString());
        assertFalse(robotNotOnTable.isOnTable());
    }

    @Test
    void testPlaceInBoundsForRobotOnTable() {
        // TODO ska man kunna sätta isOntable() ??
        robotOnTable.setOnTable();
        assertTrue(robotOnTable.isOnTable());
        robotOnTable.place(inBoundsPlacement);
        assertEquals("Output: 5,5,NORTH", robotOnTable.report());
        assertTrue(robotOnTable.isOnTable());
    }

    @Test
    void testIfRobotRemainsAtPositionWithOutOfBoundsPlacementPassedToPlace() {
        String beforePlaceCommand = robotOnTable.report();
        robotOnTable.place(outOfBoundsPlacement);
        String afterPlaceCommand = robotOnTable.report();
        assertEquals(beforePlaceCommand, afterPlaceCommand);
    }

    @Test
    void testPlaceAtNewValidPosition() {
        String currentPositionAndDirection = robotOnTable.report();
        robotOnTable.place(newValidPosition);
        String newPositionAndDirection = robotOnTable.report();
        assertNotEquals(currentPositionAndDirection, newPositionAndDirection);
    }

    @Test
    void testMoveOneUnitInBounds() {
        // Initial Position == 0,0 and Initial Direction == NORTH
        robotOnTable.move();
        assertEquals("Output: 0,1,NORTH", robotOnTable.report());
    }

    @Test
    void testMoveWithoutCrossingUpperXBoundary() {
        Robot robotMovingEast = new Robot(originPosition, Direction.EAST, xGridBoundary, yGridBoundary);
        // TODO bad practice to call another method???
        robotMovingEast.setOnTable();
        for (int i = 0; i <= xGridBoundary + 1; i++) {
            robotMovingEast.move();
        }
        assertEquals("Output: 5,0,EAST", robotMovingEast.report());
    }

    @Test
    void testMoveWithoutCrossingLowerXBoundary() {
        Robot robotMovingWest = new Robot(originPosition, Direction.WEST, xGridBoundary, yGridBoundary);
        // TODO bad practice to call another method???
        robotMovingWest.setOnTable();
        robotMovingWest.move();
        assertEquals("Output: 0,0,WEST", robotMovingWest.report());
    }

    @Test
    void testMoveWithoutCrossingUpperYBoundary() {
        Robot robotMovingNorth = new Robot(originPosition, Direction.NORTH, xGridBoundary, yGridBoundary);
        // TODO bad practice to call another method???
        robotMovingNorth.setOnTable();
        for (int i = 0; i <= yGridBoundary + 1; i++) {
            robotMovingNorth.move();
        }
        assertEquals("Output: 0,5,NORTH", robotMovingNorth.report());
    }

    @Test
    void testMoveWithoutCrossingLowerYBoundary() {
        Robot robotMovingSouth = new Robot(originPosition, Direction.SOUTH, xGridBoundary, yGridBoundary);
        // TODO bad practice to call another method???
        robotMovingSouth.setOnTable();
        robotMovingSouth.move();
        assertEquals("Output: 0,0,SOUTH", robotMovingSouth.report());
    }

    @Test
    void testRotate90DegreesLeft() {
        robotOnTable.left();
        // TODO här hade man ju velat ha getDirection() !!!
        assertEquals("Output: 0,0,WEST", robotOnTable.report());
    }

    @Test
    void testRotate180DegreesLeft() {
        robotOnTable.left();
        robotOnTable.left();
        // TODO här hade man ju velat ha getDirection() !!!
        assertEquals("Output: 0,0,SOUTH", robotOnTable.report());
    }

    @Test
    void testRotate270DegreesLeft() {
        robotOnTable.left();
        robotOnTable.left();
        robotOnTable.left();
        // TODO här hade man ju velat ha getDirection() !!!
        assertEquals("Output: 0,0,EAST", robotOnTable.report());
    }

    @Test
    void testRotate360DegreesLeft() {
        robotOnTable.left();
        robotOnTable.left();
        robotOnTable.left();
        robotOnTable.left();
        // TODO här hade man ju velat ha getDirection() !!!
        assertEquals("Output: 0,0,NORTH", robotOnTable.report());
    }

    @Test
    void testRotate90DegreesRight() {
        robotOnTable.right();
        // TODO här hade man ju velat ha getDirection() !!!
        assertEquals("Output: 0,0,EAST", robotOnTable.report());
    }

    @Test
    void testRotate180DegreesRight() {
        robotOnTable.right();
        robotOnTable.right();
        // TODO här hade man ju velat ha getDirection() !!!
        assertEquals("Output: 0,0,SOUTH", robotOnTable.report());
    }

    @Test
    void testRotate270DegreesRight() {
        robotOnTable.right();
        robotOnTable.right();
        robotOnTable.right();
        // TODO här hade man ju velat ha getDirection() !!!
        assertEquals("Output: 0,0,WEST", robotOnTable.report());
    }

    @Test
    void testRotate360DegreesRight() {
        robotOnTable.right();
        robotOnTable.right();
        // TODO här hade man ju velat ha getDirection() !!!
        assertEquals("Output: 0,0,NORTH", robotOnTable.report());
    }

    @Test
    void testReportForRobotNotOnTable() {
        assertThrows(NullPointerException.class, () -> robotNotOnTable.report());
    }

    @Test
    void testReportForRobotOnTable() {
        assertDoesNotThrow(() -> {robotOnTable.report();});
    }

    @Test
    void testToStringOnRobotNotOnTable() {
        assertEquals("Robot [on table: false; position: N/A; direction: N/A; grid dimensions: 5x5 units]", robotNotOnTable.toString());
    }

    @Test
    void testToStringOnRobotPlacedOnTable() {
        assertEquals("Robot [on table: false; position: x=0, y=0; direction: NORTH; grid dimensions: 5x5 units]", robotOnTable.toString());

    }
}