package com.github.achuzhmarov.tutorial.builder;

import org.assertj.core.util.Lists;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonBuilder {
    public static BigDecimal number(String number) {
        return new BigDecimal(number);
    }

    public static <T> List<T> list(T... objects) {
        return Lists.newArrayList(objects);
    }

    public static <K,V> Map<K, V> mapOf(K key, V value) {
        Map<K, V> result = new HashMap<>();
        result.put(key, value);
        return result;
    }

    public static <K,V> Map<K, V> mapOf(K key1, V value1, K key2, V value2) {
        Map<K, V> result = new HashMap<>();
        result.put(key1, value1);
        result.put(key2, value2);
        return result;
    }
}
