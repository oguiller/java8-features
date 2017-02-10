package com.oguiller.java8.stream.doublecolon;

@FunctionalInterface
public interface BeerPredicate {

    boolean filter(Beer c);

}