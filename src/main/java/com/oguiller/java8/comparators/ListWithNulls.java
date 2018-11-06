package com.oguiller.java8.comparators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ListWithNulls {

    public static void main(String[] args) {
        List<String> ls = new ArrayList<>(
                Arrays.asList(
                        "Sheila", null, "Fred", "Jim"
                ));
        System.out.println(ls);
        // The point here is that the nullsFirst Comparator only delegates to the regular Comparator if the fields to be compared are both non-null.
        ls.sort(Comparator.nullsFirst(Comparator.naturalOrder())); //
        System.out.println(ls);
    }
}