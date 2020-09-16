package model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import model.MethodType;

/**
 * @author rajuraghuwanshi
 */
@Builder
@ToString
@Getter
public class LogRequestVo {
    private MethodType methodType;
    private String url;
    private int timestamp;
    private int responseTime;
    private int responseCode;
}
