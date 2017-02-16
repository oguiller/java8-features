package com.oguiller.java8.stream.functionalInterfaces;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FunctionalInterfaceExample {

    public static void main(String[] args) {
        Converter<String, Integer> converter = Integer::valueOf;  // The implementation done in one line with lambda expression
        Integer converted = converter.convert("123");
        System.out.println(converted);    // 123

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        System.out.println("Print all numbers:");

        //pass n as parameter
        eval(list, n -> n % 2 == 0);
    }

    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {

            if (predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }
}
