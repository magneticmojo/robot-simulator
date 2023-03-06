import java.io.File;
import java.io.FileNotFoundException;

// TODO finns förbättringar att göra!!!

public class FileInputHandler {
    public File getFile(String filePath) throws IllegalArgumentException, FileNotFoundException {
        // TODO VAD MER BEHÖVER JAG KONTROLLERA?
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("Invalid file path");
        }
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found " + filePath);
        }
        //Option to check if user has permition to read file with file.canRead()
        return file;
    }
}
