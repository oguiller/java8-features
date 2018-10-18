package com.oguiller.java8.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Exercise {

    public static <E extends Number> int countEvenNumbers(Collection<E> numbers) {
        int count = 0;
        for (Number n : numbers) {
            if (n.intValue() % 2 == 0) count++;
        }
        return count;
    }

    public static void main(String args[]) {
        List<ComparableObject> test = new ArrayList<>();
        test.add(new ComparableObject(3));
        test.add(new ComparableObject(4));

        System.out.println(countEvenNumbers(test));
    }
}
