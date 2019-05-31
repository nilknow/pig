package util.bigDecimal;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @Date 2019/5/31 17:43
 * @Mail 494939649@qq.com
 **/
public class BigDecimalUtil {

    public static BigDecimal getBigDecimalDevision(List<BigDecimal> bigDecimalList,int numCountAfterPoint) {
        BigDecimal bigDecimalSum = bigDecimalList.stream().map(Objects::requireNonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal precisionAverage = bigDecimalSum.divide(new BigDecimal(bigDecimalList.size()), numCountAfterPoint, BigDecimal.ROUND_HALF_UP);
        return precisionAverage;
    }

}
