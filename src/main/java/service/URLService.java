package service;

import model.LogRequestVo;
import model.TopKURLResponseVo;
import model.URLResponseVo;

import java.util.List;

/**
 * @author rajuraghuwanshi
 */
public interface URLService {

    List<TopKURLResponseVo> getTopKURL();
    List<URLResponseVo>getAllURlInfo();
    boolean saveAllURLs(List<LogRequestVo> logRequestVos);
    boolean saveURL(LogRequestVo logRequestVo);
}
