package com.meandi.Greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Greedy {
    public static void main(String[] args) {
        // 存放广播电台
        HashMap<String, HashSet<String>> broadCasts = new HashMap<>();

        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        broadCasts.put("k1", hashSet1);
        broadCasts.put("k2", hashSet2);
        broadCasts.put("k3", hashSet3);
        broadCasts.put("k4", hashSet4);
        broadCasts.put("k5", hashSet5);

        // 存放所有区域
        HashSet<String> allCities = new HashSet<>();
        allCities.add("北京");
        allCities.add("上海");
        allCities.add("天津");
        allCities.add("大连");
        allCities.add("杭州");
        allCities.add("深圳");
        allCities.add("广州");
        allCities.add("成都");

        // 存放选择的电台
        ArrayList<String> selects = new ArrayList<>();

        HashSet<String> tempSet = new HashSet<>();

        String maxKey = null;

        while (allCities.size() != 0) {
            // 每次循环完都要置空
            maxKey=null;
            for (String key : broadCasts.keySet()) {
                // 每次for要清空tempSet
                tempSet.clear();

                HashSet<String> cities = broadCasts.get(key);
                tempSet.addAll(cities);
                // 求交集，选最大的，交集会赋给tempSet
                tempSet.retainAll(allCities);
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadCasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            // maxKey!=null，就应该加入selects
            if (maxKey != null) {
                selects.add(maxKey);
                // 将maxKey覆盖的区域从allCities去除
                allCities.removeAll(broadCasts.get(maxKey));
            }
        }
        System.out.println(selects);
    }
}
