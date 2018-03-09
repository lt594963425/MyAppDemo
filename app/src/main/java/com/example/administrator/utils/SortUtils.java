package com.example.administrator.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * $name
 *
 * @author ${LiuTao}
 * @date 2017/12/22/022
 */

public class SortUtils {

    public static List<String> sortByStatus(List<String> list) {
        Collections.sort(list, new Comparator<String>() {
                    @Override
                    public int compare(String data1, String data2) {
                        return data2.compareTo(data2);
                    }
                }
        );
        return list;
    }
}
