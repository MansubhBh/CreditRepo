package com.creditscore.utils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JavaExample2 {

    public static void main(String[] args) {

        List<String> items = Arrays.asList("apple","apple","banana","apple"
                ,"orange","banana","papaya");

        //Collectors.groupBy => used for grouping objects by some property & storing result in a Map instance.

        Map<String, Long> result = items.stream().collect(
                Collectors.groupingBy(
                        Function.identity(),Collectors.counting()
                )
        );

            Map<String, Long> finalMap = new LinkedHashMap<>();

        //sort a map and add to finalMap

        result.entrySet().stream()
                .sorted(Map.Entry.<String, Long> comparingByValue().reversed())
                .forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

        System.out.println(finalMap);
    }


}
