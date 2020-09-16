package model;

import lombok.Builder;
import lombok.Data;

/**
 * @author rajuraghuwanshi
 */
@Data
@Builder
public class URLResponseVo {
    private MethodType methodType;
    private String URL;
    private int frequency;
    private int minResponseTime;
    private int maxResponseTime;
    private double avgResponseTime;
}
