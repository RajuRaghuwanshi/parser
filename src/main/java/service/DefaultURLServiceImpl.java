package service;

import exception.InValidInputException;
import exception.NoDataFoundException;
import lombok.NonNull;
import model.*;
import repository.URLDataRepository;
import utils.ParserUtils;

import java.util.*;

import static utils.ParserUtils.isDataPresent;

/**
 * @author rajuraghuwanshi
 */
public class DefaultURLServiceImpl implements URLService {

    private PriorityQueue<URLInfo> topKURL;
    private URLInfoComparator urlInfoComparator;
    private int topKValue;
    URLDataRepository urlDataRepository;

    public DefaultURLServiceImpl(int topKValue, URLInfoComparator urlInfoComparator) {
        this.topKValue = topKValue;
        this.urlDataRepository = URLDataRepository.getInstance();
        this.urlInfoComparator = urlInfoComparator;
    }

    @Override
    public List<TopKURLResponseVo> getTopKURL() {


        final Map<MethodType, Map<String, URLInfo>> urlMapping = urlDataRepository.getUrlMapping();

        if (isDataPresent(urlMapping)) {
            throw new NoDataFoundException("No Data found Exception");
        }

        topKURL = computeTopKUrl(urlMapping);
        final List<TopKURLResponseVo> topKURLResponseVos = mapTopKURLToResponseVo(topKURL);
        return topKURLResponseVos;
    }

    @Override
    public List<URLResponseVo> getAllURlInfo() {

        final Map<MethodType, Map<String, URLInfo>> urlMapping = urlDataRepository.getUrlMapping();

        if (isDataPresent(urlMapping)) {
            throw new NoDataFoundException("No Data found Exception");
        }

        List<URLResponseVo> urlResponseVoList = new ArrayList<>();

        urlMapping.forEach((methodType, maskedURL) -> {
            maskedURL.forEach((url, urlInfo) -> {

                final URLResponseVo urlResponseVo = ParserUtils.mapURLInforToURLResponseVo(urlInfo);

                urlResponseVoList.add(urlResponseVo);
            });
        });

        return urlResponseVoList;
    }

    @Override
    public boolean saveAllURLs(List<LogRequestVo> logRequestVos) {
        if (isDataPresent(logRequestVos)) {
            throw new InValidInputException("Invalid Input exception");
        }
        return urlDataRepository.save(logRequestVos);
    }

    @Override
    public boolean saveURL(@NonNull LogRequestVo logRequestVo) {
        return urlDataRepository.save(logRequestVo);
    }

    private PriorityQueue<URLInfo> computeTopKUrl(final Map<MethodType, Map<String, URLInfo>> urlMapping) {

        PriorityQueue<URLInfo> topKURL = new PriorityQueue<>(topKValue, urlInfoComparator);

        urlMapping.forEach((methodType, maskedURLMapping) -> {
            maskedURLMapping.forEach((maskedURL, urlInfo) -> {
                if (topKURL.size() < topKValue) {
                    topKURL.add(urlInfo);
                } else if (topKURL.peek().getTotalHits() < urlInfo.getTotalHits()) {
                    topKURL.poll();
                    topKURL.add(urlInfo);
                }

            });
        });

        return topKURL;
    }


    private List<TopKURLResponseVo> mapTopKURLToResponseVo(PriorityQueue<URLInfo> topKURL) {

        List<TopKURLResponseVo> topKURLResponseVoList = new ArrayList<>();
        for (URLInfo urlInfo : topKURL) {
            final TopKURLResponseVo topKURLResponseVo = ParserUtils.mapURLInforToTopKResponseVo(urlInfo);
            topKURLResponseVoList.add((topKURLResponseVo));
        }
        Collections.reverse(topKURLResponseVoList);
        return topKURLResponseVoList;
    }
}
