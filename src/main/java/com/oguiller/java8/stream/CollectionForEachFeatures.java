package com.oguiller.java8.stream;

import java.util.Arrays;
import java.util.List;

public class CollectionForEachFeatures {


    /**
     * <ul>
     *     <li>forEach() method is defined at two places, on Iterable interface as well as on Stream class. which means list.forEach() and list.stream.forEach() both are valid.</li>
     *      <li>Prefer using forEach() with streams because streams are lazy and not evaluated until a terminal operation is called. </li>
     *      <li>forEach() is a terminal operation, you cannot call any method on stream after this.</li>
     *      <li>When you run forEach() method on parallel stream the order on which elements are processed is not guaranteed, though you can use forEachOrdered() to impose ordering.</li>
     *      <li><forEach() method accepts a ConsumerTest instance, which is a functional interface, that's why you can pass a lambda expression to it./li>
     * </ul>
     */

    public static void main(String args[]) {
        List<Integer> listOfPrimes = Arrays.asList(2, 3, 5, 7, 11, 3, 17);

        listOfPrimes.stream().forEach((i) -> { System.out.println(i); });

        listOfPrimes.stream().forEach(System.out::println);

        listOfPrimes.stream().filter(i -> i%2 == 0).forEach(System.out::println);
    }
}
