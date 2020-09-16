package model;
import lombok.Builder;
import lombok.Data;
import model.MethodType;


/**
 * @author rajuraghuwanshi
 */

@Data
@Builder
public class URLInfo {
    private MethodType methodType;
    private String url;
    private int minResponseTime;
    private int maxResponseTime;
    private int totalResponseTime;
    private int totalHits;
}
