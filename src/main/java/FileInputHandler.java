import java.io.File;
import java.io.FileNotFoundException;

public class FileInputHandler {
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
