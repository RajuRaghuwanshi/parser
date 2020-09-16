import exception.InValidFileTypeException;

/**
 * @author rajuraghuwanshi
 */
public class LogFileFactory {

    public static FileProcessor getFileProcessor(String fileType) {
        if (fileType == null) {
            throw new InValidFileTypeException("Invalid file format");
        }
        if (fileType.equalsIgnoreCase("CSV")) {
            return new CSVFileProcessor();

        }

        throw new InValidFileTypeException("Invalid file format");
    }
}
