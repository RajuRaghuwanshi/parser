package model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rajuraghuwanshi
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopKURLResponseVo {
    private MethodType methodType;
    private String URL;
    private int frequency;
}
