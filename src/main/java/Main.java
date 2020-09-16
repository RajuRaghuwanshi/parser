import model.LogRequestVo;
import model.TopKURLResponseVo;
import model.URLResponseVo;
import service.DefaultURLInfoComparator;
import service.DefaultURLServiceImpl;
import service.URLInfoComparator;
import service.URLService;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static utils.ParserUtils.printCollection;

/**
 * @author rajuraghuwanshi
 */
public class Main {

    private static final String filePath = "/Users/rajuraghuwanshi/Downloads/input.csv";
    private static final int topKValue = 5;

    public static void main(String[] args) throws IOException {

        final FileProcessor fileProcessor = LogFileFactory.getFileProcessor("CSV");

        final List<LogRequestVo> logRequestVos = fileProcessor.readFile(filePath);

        URLInfoComparator urlInfoComparator = new DefaultURLInfoComparator();

        URLService urlService = new DefaultURLServiceImpl(topKValue, urlInfoComparator);


        urlService.saveAllURLs(logRequestVos);


        final List<URLResponseVo> allURlInfo = urlService.getAllURlInfo();

        printCollection(allURlInfo);

        final List<TopKURLResponseVo> topKURL = urlService.getTopKURL();

        printCollection(topKURL);

    }


}
