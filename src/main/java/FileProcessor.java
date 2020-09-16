import model.LogRequestVo;

import java.io.IOException;
import java.util.List;

/**
 * @author rajuraghuwanshi
 */
public interface FileProcessor {

    List<LogRequestVo> readFile(String filePath) throws IOException;
}
