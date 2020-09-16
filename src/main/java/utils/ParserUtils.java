package utils;

import lombok.NonNull;
import model.TopKURLResponseVo;
import model.URLInfo;
import model.URLResponseVo;

import java.util.Collection;
import java.util.Map;

/**
 * @author rajuraghuwanshi
 */
public class ParserUtils {

    public static String getMaskedURL(@NonNull String url) {
        String[] urlPath = url.split("/");
        StringBuilder urlBuilder = new StringBuilder();
        for (String path : urlPath) {
            if (isNumeric(path)) {
                urlBuilder.append("{id}/");
            } else {
                urlBuilder.append(path).append("/");
            }
        }

        if (urlBuilder.length() > 0) {
            urlBuilder.setLength(urlBuilder.length() - 1);
        }
        return urlBuilder.toString();
    }

    public static boolean isNumeric(@NonNull String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static URLResponseVo mapURLInforToURLResponseVo(@NonNull final URLInfo urlInfo) {
        return URLResponseVo.builder()
                            .methodType(urlInfo.getMethodType())
                            .URL(urlInfo.getUrl())
                            .frequency(urlInfo.getTotalHits())
                            .minResponseTime(urlInfo.getMinResponseTime())
                            .maxResponseTime(urlInfo.getMaxResponseTime())
                            .avgResponseTime(calculateAvgresponseTime(urlInfo.getTotalResponseTime(), urlInfo.getTotalHits()))
                            .build();
    }

    public static TopKURLResponseVo mapURLInforToTopKResponseVo(@NonNull final URLInfo urlInfo) {
        TopKURLResponseVo topKURLResponseVo = new TopKURLResponseVo(urlInfo.getMethodType(), urlInfo.getUrl(), urlInfo.getTotalHits());
        return topKURLResponseVo;
    }

    public static double calculateAvgresponseTime(int totalResponseTime, int totalHits) {
        return (totalResponseTime * 1.0) / totalHits;
    }

    public static boolean isDataPresent(Collection<?> value) {
        return value == null || value.isEmpty() ? true : false;
    }

    public static boolean isDataPresent(Map<?, ?> value) {
        return value == null || value.isEmpty() ? true : false;
    }

    public static <E> void printCollection(Collection<E> values) {
        for (E element : values)
            System.out.println(element);
    }
}
