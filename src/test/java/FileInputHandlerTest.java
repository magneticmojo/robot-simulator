import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Björn Forsberg
 */
class FileInputHandlerTest {

    FileInputHandler fileInputHandler = new FileInputHandler();

    @Test
    void testGetFileWithNullArgument() {
        assertThrows(IllegalArgumentException.class, () -> fileInputHandler.getFile(null));
    }

    @Test
    public void testGetFileWithEmptyFilePathThrowsIllegalArgumentException() {
        String filePath = "";
        assertThrows(IllegalArgumentException.class, () -> fileInputHandler.getFile(filePath));
    }

    @Test
    public void testGetFileWithNonexistentFilePathThrowsFileNotFoundException() {
        String filePath = "nonexistent_file.txt";
        assertThrows(FileNotFoundException.class, () -> fileInputHandler.getFile(filePath));
    }

    @Test
    public void testGetFileWithEmptyFileThrowsFileNotFoundException() {
        String filePath = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "empty_file.in";
        assertThrows(FileNotFoundException.class, () -> fileInputHandler.getFile(filePath));
    }

    @Test
    public void testGetFileWithValidFilePathReturnsFile() throws FileNotFoundException {
        String filePath = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "1.in";
        File file = fileInputHandler.getFile(filePath);
        assertTrue(file.exists());
    }
}