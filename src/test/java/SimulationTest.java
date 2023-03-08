import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Björn Forsberg
 */
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
        File tempFile = new File(tempDir, "valid.txt");
        FileWriter writer = new FileWriter(tempFile);
        writer.write("PLACE 0,0,NORTH\n");
        writer.write("MOVE\n");
        writer.write("LEFT\n");
        writer.write("RIGHT\n");
        writer.write("REPORT\n");
        writer.close();
        assertDoesNotThrow(() -> simulation.readCommand(tempFile));
    }

    @Test
    void testReadCommandWithInvalidCommand() throws IOException {
        File tempFile = new File(tempDir, "invalid.txt");
        FileWriter writer = new FileWriter(tempFile);
        writer.write("FAIL\n");
        writer.close();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> simulation.readCommand(tempFile));
        assertEquals("Invalid command: \"FAIL\" in file invalid.txt at " + tempFile.getAbsolutePath(), exception.getMessage());
    }

    @Test
    void testReadCommandWithInvalidPlaceArgs() throws IOException {
        File tempFile = new File(tempDir, "invalid_args.txt");
        FileWriter writer = new FileWriter(tempFile);
        writer.write("PLACE a,0,NORTH\n");
        writer.close();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> simulation.readCommand(tempFile));
        assertEquals("Illegal command args for PLACE command: \"a,0,NORTH\" in file invalid_args.txt at " + tempFile.getAbsolutePath(), exception.getMessage());
    }
}