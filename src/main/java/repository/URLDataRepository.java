package repository;

import lombok.Getter;
import model.LogRequestVo;
import model.MethodType;
import model.URLInfo;
import utils.ParserUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rajuraghuwanshi
 */
@Getter
public class URLDataRepository {

    private Map<MethodType, Map<String, URLInfo>> urlMapping;

    private URLDataRepository() {

        this.urlMapping = new HashMap<>();
    }

    private static URLDataRepository single_instance = null;

    public static URLDataRepository getInstance() {
        if (single_instance == null) {
            single_instance = new URLDataRepository();
        }
        return single_instance;
    }

    public boolean save(List<LogRequestVo> logRequestVoList) {

        for (LogRequestVo logRequestVo : logRequestVoList) {
            processURLRequest(logRequestVo);

        }

        return true;
    }

    public boolean save(LogRequestVo logRequestVo) {

        processURLRequest(logRequestVo);
        return true;
    }

    private void processURLRequest(LogRequestVo logRequestVo) {

        final String maskedURL = ParserUtils.getMaskedURL(logRequestVo.getUrl());

        if (urlMapping.containsKey(logRequestVo.getMethodType())) {

            Map<String, URLInfo> urlInfoMap = urlMapping.get(logRequestVo.getMethodType());

            if (urlInfoMap.containsKey(maskedURL)) {

                URLInfo urlInfo = urlInfoMap.get(maskedURL);
                updateURLInfoDetails(urlInfo, logRequestVo);

            } else {

                final URLInfo urlInfo = buildURLInfo(maskedURL, logRequestVo.getMethodType(), logRequestVo.getResponseTime(), logRequestVo.getResponseTime(), 1, logRequestVo.getResponseTime());

                urlInfoMap.put(maskedURL, urlInfo);
            }

        } else {
            Map<String, URLInfo> mappingForNewMaskedURL = new HashMap<>();

            final URLInfo urlInfo = buildURLInfo(maskedURL, logRequestVo.getMethodType(), logRequestVo.getResponseTime(), logRequestVo.getResponseTime(), 1, logRequestVo.getResponseTime());


            mappingForNewMaskedURL.put(maskedURL, urlInfo);

            urlMapping.put(logRequestVo.getMethodType(), mappingForNewMaskedURL);

        }

    }

    private URLInfo buildURLInfo(String maskedURL, MethodType methodType, int minResponseTime, int maxResponseTime, int totalHits, int totalResponseTime) {

        return URLInfo.builder()
                      .url(maskedURL)
                      .methodType(methodType)
                      .minResponseTime(minResponseTime)
                      .maxResponseTime(maxResponseTime)
                      .totalHits(totalHits)
                      .totalResponseTime(totalResponseTime)
                      .build();
    }

    private URLInfo updateURLInfoDetails(URLInfo urlInfo, LogRequestVo logRequestVo) {

        urlInfo.setTotalHits(urlInfo.getTotalHits() + 1);

        if (urlInfo.getMaxResponseTime() < logRequestVo.getResponseTime()) {
            urlInfo.setMaxResponseTime(logRequestVo.getResponseTime());
        } else if (urlInfo.getMinResponseTime() > logRequestVo.getResponseTime()) {
            urlInfo.setMinResponseTime(logRequestVo.getResponseTime());
        }
        urlInfo.setTotalResponseTime(urlInfo.getTotalResponseTime() + logRequestVo.getResponseTime());

        return urlInfo;
    }

}
