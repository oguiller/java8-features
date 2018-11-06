package com.oguiller.java8.comparators;

import java.util.*;

public class Reverser {

    public static void main(String[] args) {
        List<String> ls = new ArrayList<>(
                Arrays.asList("Guille", "Pichi", "Arian"));
        System.out.println(ls);
        Comparator rev = Collections.reverseOrder();
        ls.sort(rev);
        System.out.println(ls);

        List<Integer> li = new ArrayList<>(Arrays.asList(9, 3, 4, 1, 8, 6, 2, 5, 7));
        li.sort(rev);
        System.out.println(li);

        Comparator<String> length = (a, b) -> a.length() - b.length();
        ls.sort(length);
        System.out.println(ls);
//        Comparator<String> revLength = Collections.reverseOrder(length);
        Comparator<String> revLength = length.reversed();
        ls.sort(revLength);
        System.out.println(ls);
    }
}
