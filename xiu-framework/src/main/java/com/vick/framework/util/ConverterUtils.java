package com.vick.framework.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zyz
 * @since 2019-11-25
 */
public class ConverterUtils {

    public static <E, T> List<T> convert(List<E> collections, Class<T> clazz) {
        List<T> list = new ArrayList();
        collections.stream().forEach(e -> {
            try {
                T instance = clazz.newInstance();
                BeanUtils.copyProperties(e, instance);
                list.add(instance);
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        });
        return list;
    }
}
