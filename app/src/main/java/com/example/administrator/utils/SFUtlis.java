package com.example.administrator.utils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * $name
 *
 * @author ${LiuTao}
 * @date 2017/12/21/021
 */

public class SFUtlis {
    private static Map<Integer, String> sStringMap = new LinkedHashMap<>();

    /**
     * 添加数据
     */
    private static void addDate() {
        sStringMap.put(2, "哇哇哇哇哇");
        sStringMap.put(3, "噩噩噩噩噩");
        sStringMap.put(4, "啦啦啦啦啦");
        sStringMap.put(7, "去去去去去");
    }

    /**
     * 根据键找值
     */
    private static String getKey(int key) {
        Set<Map.Entry<Integer, String>> set = sStringMap.entrySet();
        Iterator<Map.Entry<Integer, String>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> mapEntry = iterator.next();
            if (mapEntry.getKey().equals(key)) {
                return mapEntry.getValue();
            }
        }
        return "";
    }

    /**
     * 根据值找键
     */
    private static int getValue(String value) {
        Set<Map.Entry<Integer, String>> set = sStringMap.entrySet();
        Iterator<Map.Entry<Integer, String>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> mapEntry = iterator.next();
            if (mapEntry.getValue().equals(value)) {
                return mapEntry.getKey();
            }
        }
        return -1;
    }

    private static void OtherWay() {
        //第一种 用for循环的方式
        for (Map.Entry<Integer, String> m : sStringMap.entrySet()) {
            System.out.println(m.getKey() + "\t" + m.getValue());
        }
        //利用迭代 （Iterator）
        Set<Map.Entry<Integer, String>> set = sStringMap.entrySet();
        Iterator<Map.Entry<Integer, String>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> enter = iterator.next();
            System.out.println(enter.getKey() + "\t" + enter.getValue());
        }
        //利用KeySet 迭代
        Iterator<Integer> it = sStringMap.keySet().iterator();
        while (it.hasNext()) {
            String key;
            String value;
            key = it.next().toString();
            value = (String) sStringMap.get(key);
            System.out.println(key + "--" + value);
        }
        //利用EnterySet迭代
        Iterator<Map.Entry<Integer, String>> i = sStringMap.entrySet().iterator();
        System.out.println(sStringMap.entrySet().size());
        String key;
        String value;
        while (i.hasNext()) {
            Map.Entry<Integer, String> entry = i.next();
            key = entry.getKey().toString();
            value = entry.getValue().toString();
            System.out.println(key + "====" + value);
        }
    }
}
