package util.string;

import com.google.common.base.CaseFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @Author poorguy.tech
 * @Date 2019/5/29 18:08
 * @Mail 494939649@qq.com / maxwangein@gmail.com
 **/
public class StringUtil {
    public static String upperUnderScoreToUpperCamel(String upperUnderScoreString) {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, upperUnderScoreString);
    }

    public static String upperUnderScoreToLowerCamel(String upperUnderScoreString) {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, upperUnderScoreString);
    }
    public static void main(String[] args){
        System.out.println(upperUnderScoreToLowerCamel("he_llo_howareyou"));
    }
}
