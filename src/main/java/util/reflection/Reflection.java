package util.reflection;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * @author poorguy
 * @version 0.0.1
 * @E-mail 494939649@qq.com
 * @created 2019/5/21 10:15
 * @description
 */
public class Reflection {
    public static Class getSuperClass(Object object){
        return object.getClass().getSuperclass();
    }
    public static Class getFirstSuperClassGenericParam(Object object){
        Type genericSuperType = object.getClass().getGenericSuperclass();
        if (!(genericSuperType instanceof ParameterizedType)) {
            return null;
        } else {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperType;
            return (Class)(parameterizedType.getActualTypeArguments()[0]);
        }
    }
    /**
     * 获取类的泛型参数列表
     */
    public static Class[] getSuperClassGenericParamList(Object object) {
        Type genericSuperType = object.getClass().getGenericSuperclass();
        if (!(genericSuperType instanceof ParameterizedType)) {
            return null;
        } else {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperType;
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            //非常有意思的是如果直接对数组进行强转，是会报错的，这点要注意
            Class[] classes = Arrays.copyOf(typeArguments, typeArguments.length, Class[].class);
            return classes;
        }
    }
}
