package com.oguiller.java8.stream.doublecolon;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class TestDoubleColon {

    public static void main(String args[]) {
        List<Beer> beers = Arrays.asList(new Beer(1l, "IPA", 8.0f, "Netherlands"),
                new Beer(2l, "Witte", 5.0f, "Netherlands"),
                new Beer(3l, "Stout", 8.0f, "Netherlands"));

        // Using the double colon to reference a static method
        beers.forEach(BeerUtils::softenBeer);

        // Referencing a method of an existing object instance.
       // PrintStream out = System.out;
        beers.forEach(System.out::println);

        // An Instance Method of an Arbitrary Object of a Particular Type
        // We are using the type itself here :)
        beers.forEach(Beer::pourBeer);


        // Using FunctionalInterfaces and DoubleColon to creat instances of our objects
        TriFunction<Long, String, Float, Beer> creator = Beer::new;
        Beer beer = creator.apply(4l, "Red Ale",6.0f);

        // Create an Array
        Function<Integer, Beer[]> beerCreator = Beer[]::new;
        Beer[] beerArray = beerCreator.apply(10);

    }
}
