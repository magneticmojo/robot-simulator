import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {
    @TempDir
    File tempDir;

    private Simulation simulation;

    @BeforeEach
    void setUp() {
        simulation = new Simulation();
    }

    @Test
    void testReadCommandWithValidFile() throws IOException {
        // Create a temporary file with valid commands
        File tempFile = new File(tempDir, "valid.txt");
        FileWriter writer = new FileWriter(tempFile);
        writer.write("PLACE 0,0,NORTH\n");
        writer.write("MOVE\n");
        writer.write("LEFT\n");
        writer.write("RIGHT\n");
        writer.write("REPORT\n");
        writer.close();

        // Read the commands from the file and assert that they are executed without errors
        assertDoesNotThrow(() -> simulation.readCommand(tempFile));
    }

    @Test
    void testReadCommandWithInvalidCommand() throws IOException {
        // Create a temporary file with an invalid command
        File tempFile = new File(tempDir, "invalid.txt");
        FileWriter writer = new FileWriter(tempFile);
        writer.write("JUMP\n");
        writer.close();

        // Read the commands from the file and assert that an IllegalArgumentException is thrown
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> simulation.readCommand(tempFile));
        assertEquals("Invalid command: \"JUMP\" in file invalid.txt at " + tempFile.getAbsolutePath(), exception.getMessage());
    }

    @Test
    void testReadCommandWithInvalidPlaceArgs() throws IOException {
        // Create a temporary file with a PLACE command with invalid arguments
        File tempFile = new File(tempDir, "invalid_args.txt");
        FileWriter writer = new FileWriter(tempFile);
        writer.write("PLACE a,0,NORTH\n");
        writer.close();

        // Read the commands from the file and assert that an IllegalArgumentException is thrown
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> simulation.readCommand(tempFile));
        assertEquals("Illegal command args for PLACE command: \"a,0,NORTH\" in file invalid_args.txt at " + tempFile.getAbsolutePath(), exception.getMessage());
    }

    @Test
    void testReadCommandWithMissingFile() {
        // Try to read commands from a non-existent file and assert that a FileNotFoundException is thrown
        FileNotFoundException exception = assertThrows(FileNotFoundException.class, () -> simulation.readCommand(new File("nonexistent.txt")));
        assertEquals("File not found nonexistent.txt", exception.getMessage());
    }

    @Test
    void testReadCommandWithEmptyFile() throws IOException {
        // Create a temporary empty file and assert that a FileNotFoundException is thrown
        File tempFile = new File(tempDir, "empty.txt");
        tempFile.createNewFile();

        FileNotFoundException exception = assertThrows(FileNotFoundException.class, () -> simulation.readCommand(tempFile));
        assertEquals("File is empty empty.txt at " + tempFile.getAbsolutePath(), exception.getMessage());
    }

}