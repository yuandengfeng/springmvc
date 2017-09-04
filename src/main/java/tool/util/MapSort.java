package tool.util;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ShiYun on 2015/12/25 0025.
 */
public class MapSort {
    /**
     * 使用 Map按key进行排序
     *
     * @param map
     * @return
     */
    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<String, String>(
                new Comparator<String>() {
                    @Override
                    public int compare(String str1, String str2) {
                        return str1.compareTo(str2);
                    }
                });

        sortMap.putAll(map);
//        show(sortMap);
        return sortMap;
    }

    /**
     * 按key值大小升序排序
     *
     * @param map
     * @return
     */
    public static Map<String, String> sortMapByIntKeyAsc(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<String, String>(
                new Comparator<String>() {
                    @Override
                    public int compare(String str1, String str2) {
                        Integer a = Integer.parseInt(str1);
                        Integer b = Integer.parseInt(str2);
                        return a - b;
                    }
                });
        sortMap.putAll(map);
        return sortMap;
    }

    /**
     * 按key值大小降序排序
     *
     * @param map
     * @return
     */
    public static Map<String, String> sortMapByIntKeyDesc(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<String, String>(
                new Comparator<String>() {
                    @Override
                    public int compare(String str1, String str2) {
                        Integer a = Integer.parseInt(str1);
                        Integer b = Integer.parseInt(str2);
                        return b - a;
                    }
                });
        sortMap.putAll(map);
        return sortMap;
    }

    public static void show(Map<String, String> map) {
        System.out.println("#####################################");
        for (String str : map.keySet()) {
            System.out.println("key:" + str + ",value:" + map.get(str));
        }
        System.out.println("#####################################");
    }
}
