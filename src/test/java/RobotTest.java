import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

    private Robot robotNotOnTable;
    private Robot robot;
    private String[] inBoundsPlacement = {"5", "5", "NORTH"};
    private String[] outOfBoundsPlacement = {"10", "10", "NORTH"};
    private String[] newValidPosition = {"3", "3", "SOUTH"};
    private String[] originNorth = {"0", "0", "NORTH"};
    private int xGridBoundary = 5;
    private int yGridBoundary = 5;

    @BeforeEach
    void setUp() {
        robotNotOnTable = new Robot(null, null, xGridBoundary, yGridBoundary);
        robot = new Robot(null, null, xGridBoundary,yGridBoundary);
        robot.place(originNorth);

    }

    @AfterEach
    void tearDown() {
        robot = null;
        robotNotOnTable = null;
    }

    @Test
    void testPlaceInBoundsForRobotNotOnTable() {
        robotNotOnTable.place(inBoundsPlacement);
        assertEquals("Robot [on table: true; position: 5,5; direction: NORTH; grid dimensions: 5x5 units]", robotNotOnTable.toString());
    }

    @Test
    void testRobotNotOnTableForPlaceOutOfBoundsForRobotNotOnTable() {
        robotNotOnTable.place(outOfBoundsPlacement);
        assertEquals("Robot [on table: false; position: N/A; direction: N/A; grid dimensions: 5x5 units]", robotNotOnTable.toString());
    }

    @Test
    void testPlaceInBoundsForRobotOnTable() {
        robot.place(inBoundsPlacement);
        assertEquals("Robot [on table: true; position: 5,5; direction: NORTH; grid dimensions: 5x5 units]", robot.toString());
    }

    @Test
    void testIfRobotRemainsAtPositionWithOutOfBoundsPlacementPassedToPlace() {
        String beforePlaceCommand = robot.toString();
        robot.place(outOfBoundsPlacement);
        String afterPlaceCommand = robot.toString();
        assertEquals(beforePlaceCommand, afterPlaceCommand);
    }

    @Test
    void testPlaceAtNewValidPosition() {
        String currentPositionAndDirection = robot.toString();
        robot.place(newValidPosition);
        String newPositionAndDirection = robot.toString();
        assertNotEquals(currentPositionAndDirection, newPositionAndDirection);
    }

    @Test
    void testMoveOneUnitInBounds() {
        String initialPosition = robot.toString();
        robot.move();
        assertNotEquals(initialPosition, robot.toString());
    }

    @Test
    void testMoveWithoutCrossingUpperXBoundary() {
        robot.place(new String[] {"0","0","EAST"});
        Robot robotMovingEast = robot;
        for (int i = 0; i <= xGridBoundary + 1; i++) {
            robotMovingEast.move();
        }
        assertEquals("Robot [on table: true; position: 5,0; direction: EAST; grid dimensions: 5x5 units]", robotMovingEast.toString());
    }

    @Test
    void testMoveWithoutCrossingLowerXBoundary() {
        robot.place(new String[] {"0","0","WEST"});
        Robot robotMovingWest = robot;
        robotMovingWest.move();
        assertEquals("Robot [on table: true; position: 0,0; direction: WEST; grid dimensions: 5x5 units]", robotMovingWest.toString());
    }

    @Test
    void testMoveWithoutCrossingUpperYBoundary() {
        robot.place(new String[] {"0","0","NORTH"});
        Robot robotMovingNorth = robot;
        for (int i = 0; i <= yGridBoundary + 1; i++) {
            robotMovingNorth.move();
        }
        assertEquals("Robot [on table: true; position: 0,5; direction: NORTH; grid dimensions: 5x5 units]", robotMovingNorth.toString());
    }

    @Test
    void testMoveWithoutCrossingLowerYBoundary() {
        robot.place(new String[] {"0","0","SOUTH"});
        Robot robotMovingSouth = robot;
        robotMovingSouth.move();
        assertEquals("Robot [on table: true; position: 0,0; direction: SOUTH; grid dimensions: 5x5 units]", robotMovingSouth.toString());
    }

    @Test
    void testRotate90DegreesLeft() {
        robot.left();
        assertEquals("Robot [on table: true; position: 0,0; direction: WEST; grid dimensions: 5x5 units]", robot.toString());
    }

    @Test
    void testRotate180DegreesLeft() {
        robot.left();
        robot.left();
        assertEquals("Robot [on table: true; position: 0,0; direction: SOUTH; grid dimensions: 5x5 units]", robot.toString());
    }

    @Test
    void testRotate270DegreesLeft() {
        robot.left();
        robot.left();
        robot.left();
        assertEquals("Robot [on table: true; position: 0,0; direction: EAST; grid dimensions: 5x5 units]", robot.toString());
    }

    @Test
    void testRotate360DegreesLeft() {
        robot.left();
        robot.left();
        robot.left();
        robot.left();
        assertEquals("Robot [on table: true; position: 0,0; direction: NORTH; grid dimensions: 5x5 units]", robot.toString());
    }

    @Test
    void testRotate90DegreesRight() {
        robot.right();
        assertEquals("Robot [on table: true; position: 0,0; direction: EAST; grid dimensions: 5x5 units]", robot.toString());
    }

    @Test
    void testRotate180DegreesRight() {
        robot.right();
        robot.right();
        assertEquals("Robot [on table: true; position: 0,0; direction: SOUTH; grid dimensions: 5x5 units]", robot.toString());
    }

    @Test
    void testRotate270DegreesRight() {
        robot.right();
        robot.right();
        robot.right();
        assertEquals("Robot [on table: true; position: 0,0; direction: WEST; grid dimensions: 5x5 units]", robot.toString());
    }

    @Test
    void testRotate360DegreesRight() {
        robot.right();
        robot.right();
        robot.right();
        robot.right();
        assertEquals("Robot [on table: true; position: 0,0; direction: NORTH; grid dimensions: 5x5 units]", robot.toString());
    }

    @Test
    void testReportString() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        robot.place(originNorth);
        robot.report();
        assertEquals("Output: 0,0,NORTH", outContent.toString().trim());
        robot.move();
        System.setOut(System.out);
    }

    @Test
    void testReportStringAfterCommandsExecuted() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        robot.place(originNorth);
        robot.move();
        robot.move();
        robot.right();
        robot.move();
        robot.left();
        robot.left();
        robot.report();
        assertEquals("Output: 1,2,WEST", outContent.toString().trim());
        robot.move();
        System.setOut(System.out);
    }

    @Test
    void testToStringOnRobotNotOnTable() {
        assertEquals("Robot [on table: false; position: N/A; direction: N/A; grid dimensions: 5x5 units]", robotNotOnTable.toString());
    }

    @Test
    void testToStringOnRobotPlacedOnTable() {
        assertEquals("Robot [on table: true; position: 0,0; direction: NORTH; grid dimensions: 5x5 units]", robot.toString());

    }

/*    *//*
    * Access needs to be set to public for isInBoundFor(Position position) for tests to work;
    * *//*
    @Test
    void testIsInBoundFor() {
        assertFalse(robot.isInBoundsFor(new Position(10,10)));
        assertFalse(robot.isInBoundsFor(new Position(-1,-1)));
        assertTrue(robot.isInBoundsFor(new Position(3,3)));
        assertTrue(robot.isInBoundsFor(new Position(0,0)));
        assertTrue(robot.isInBoundsFor(new Position(0,5)));
        assertTrue(robot.isInBoundsFor(new Position(5,0)));
        assertTrue(robot.isInBoundsFor(new Position(5,5)));
    }*/
}