package com.creditscore.utils;

import com.creditscore.entity.Item;

import java.util.Arrays;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Java8Example3 {

    public static void main(String[] args) {

        // 3 apple, 2 banana, other 1
        List<Item> items = Arrays.asList(
                new Item("apple", 10, new BigDecimal(1.99)),
                new Item("banana", 13, new BigDecimal(5.22)),
                new Item("orange", 45, new BigDecimal(14.32)),
                new Item("watermelon", 34, new BigDecimal(1.12)),
                new Item("papaya", 12, new BigDecimal(43.2)),
                new Item("apple", 11, new BigDecimal(2.12)),
                new Item("banana", 9, new BigDecimal(3.23)),
                new Item("orange", 8, new BigDecimal(19.23)),
                new Item("apple", 25, new BigDecimal(16.33))
        );



        Map<String, Long> counting = items.stream().collect(
                Collectors.groupingBy(Item::getName, Collectors.counting()));

        System.out.println(counting  + "Ashik");

        Map<String, Integer> sum = items.stream().collect(
                Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));

        System.out.println(sum);
    }
}
