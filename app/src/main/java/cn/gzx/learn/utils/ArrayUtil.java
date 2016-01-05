package cn.gzx.learn.utils;

/**
 * Created by guozengxin on 2016/1/5.
 */
public class ArrayUtil {
    public static <T> boolean isValid(T[] array) {
        if (array == null || array.length == 0) {
            return false;
        }
        return true;
    }
}
