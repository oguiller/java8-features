package com.oguiller.java8.generics;

public class ComparableObject extends Number implements Comparable {
    int i;

    public ComparableObject(int i) {
        this.i = i;
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public long longValue() {
        return 0;
    }

    @Override
    public float floatValue() {
        return 0;
    }

    @Override
    public double doubleValue() {
        return 0;
    }
}
