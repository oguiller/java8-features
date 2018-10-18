package com.oguiller.java8.generics;

import java.util.ArrayList;
import java.util.List;

public final class Algorithm {

    public static <T extends Object & Comparable<? super T>>
    T max(List<? extends T> list, int begin, int end) {

        T maxElem = list.get(begin);

        for (++begin; begin < end; ++begin)
            if (maxElem.compareTo(list.get(begin)) < 0)
                maxElem = list.get(begin);
        return maxElem;
    }

    public static <T extends Comparable<? super T>> T test(T a, T b) {
        if (a.compareTo(b) > 0) return a;
        return b;
    }

    public static void main(String args[]) {
        List<Integer> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);

        Student student = new Student();

        test(student, student);

    }
}