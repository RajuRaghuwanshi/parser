import model.LogRequestVo;
import model.MethodType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rajuraghuwanshi
 */

public class CSVFileProcessor implements FileProcessor {

    public List<LogRequestVo> readFile(String filePath) throws IOException, RuntimeException {

        String line = "";
        String splitBy = ",";

        List<LogRequestVo> logRequestVoList = new ArrayList<LogRequestVo>();

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        int lineNumber = 0;
        while ((line = br.readLine()) != null)   //returns a Boolean value
        {

            if (lineNumber != 0) {
                //Order of header values (add COR for validation) timestamp	url	method	response_time	response_code
                String[] logInfo = line.split(splitBy);    // use comma as separator
                LogRequestVo logRequestVo = LogRequestVo.builder()
                                                        .timestamp(Integer.parseInt(logInfo[0]))
                                                        .url(logInfo[1])
                                                        .methodType(MethodType.valueOf(logInfo[2]))
                                                        .responseTime(Integer.parseInt(logInfo[3]))
                                                        .responseCode(Integer.parseInt(logInfo[4]))
                                                        .build();

                logRequestVoList.add(logRequestVo);
            }
            lineNumber++;
        }

        return logRequestVoList;
    }

}
