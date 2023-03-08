import java.io.File;
import java.io.FileNotFoundException;

/**
 * The FileInputHandler checks for valid file path input.
 *
 * @author Björn Forsberg
 */
public class FileInputHandler {
    /**
     * @param filePath A path to the file used for input to the application.
     * @return a file object containing the file specified in the path.
     * @throws IllegalArgumentException if filePath is null or empty.
     * @throws FileNotFoundException    if file path is invalid or file is empty.
     */
    public File getFile(String filePath) throws IllegalArgumentException, FileNotFoundException {

        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("Invalid file path");
        }

        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found " + filePath);
        }

        if (file.length() == 0) {
            throw new FileNotFoundException("File is empty " + filePath);
        }

        return file;
    }
}
