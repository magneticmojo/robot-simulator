import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Björn Forsberg
 */
class MainTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void main() {
    }

    @Test
    void run() {
    }

    @Test
    @DisplayName("Throws exception when no argument provided")
    void testNoArgumentProvided() {
        String[] args = {};
        assertThrows(IllegalArgumentException.class, () -> Main.main(args));
    }

/*    @Test
    @DisplayName("Throws exception when invalid file path provided")
    void testInvalidFilePath(@TempDir Path tempDir) throws IOException {
        File file = new File(tempDir.resolve("nonexistent.txt").toString());
        String filePath = file.getPath();
        assertThrows(FileNotFoundException.class, () -> new FileInputHandler().getFile(filePath));
        assertThrows(IOException.class, () -> new Simulation().readCommand(file));
        assertThrows(IOException.class, () -> new Main().run(filePath));
    }*/

    @Test
    @DisplayName("Runs successfully with valid file path provided")
    void testValidFilePath(@TempDir Path tempDir) throws IOException {
        String contents = "PLACE 0,0,NORTH\nMOVE\nREPORT\n";
        File file = new File(tempDir.resolve("valid.txt").toString());
        Path path = Files.write(file.toPath(), contents.getBytes());
        String filePath = path.toString();
        assertDoesNotThrow(() -> new FileInputHandler().getFile(filePath));
        assertDoesNotThrow(() -> new Simulation().readCommand(file));
        assertDoesNotThrow(() -> new Main().run(filePath));
    }
}