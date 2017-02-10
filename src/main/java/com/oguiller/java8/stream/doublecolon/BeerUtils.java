package com.oguiller.java8.stream.doublecolon;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BeerUtils {

    public static final BeerPredicate highAlcoholPercentagePredicate = (b) -> (b.getAlcohol() > 5.0);
    public static final BeerPredicate localBeerPredicate = (b) -> "Netherlands".equals(b.getCountry());

    public static List<Beer> filter(final List<Beer> inventory, final BeerPredicate p) {

        final List<Beer> result = new ArrayList<>();
        Consumer<Beer> add = result::add;
        inventory.stream().filter(p::filter).forEach((b) -> add.accept(b));

        return result;
    }

    public static void softenBeer(final Beer beer) {
        if (beer.getAlcohol() > 5) {
            beer.reduceAlcohol();
        }
    }
}