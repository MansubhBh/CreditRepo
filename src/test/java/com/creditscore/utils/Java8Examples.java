package com.creditscore.utils;


import com.sun.tools.javac.util.List;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

// ADD SORTING(USING JAVA8)


public class Java8Examples {

    public static void main(String[] args) {

        java.util.List<String> items = Arrays.asList("apple", "apple", "banana",
                "apple", "orange", "banana","papaya");

        Map<String, Long> result = items.stream().collect(
                Collectors.groupingBy(Function.identity(), Collectors.counting())
        );

        Map<String, Long> finalMap = new LinkedHashMap<>();

        //sort a map and add to finalMap

                result.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue()
                .reversed()).forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

        System.out.println(finalMap);


    }

}
