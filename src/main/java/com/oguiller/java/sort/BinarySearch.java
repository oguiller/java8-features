package com.oguiller.java.sort;

import java.util.List;

/**
 * When searching through a list, unless the list is sorted in some fashion, the only sure way to find a given value is
 * to look at every value in the list.
 * <p>
 * But if you are given a sorted list, or if you sort the list before searching, a binary search is a very efficient method
 * to see if a given value is in the list:
 * <p>
 * The beauty of this algorithm is that you use the property of the sorted list to your advantage.
 */

public class BinarySearch {

    public static boolean binarySearch(final List<Integer> numbers,
                                       final Integer value) {
        if (numbers == null || numbers.isEmpty()) {
            return false;
        }

        final Integer comparison = numbers.get(numbers.size() / 2);

        if (value.equals(comparison)) {
            return true;
        }

        if (value < comparison) {
            return binarySearch(
                    numbers.subList(0, numbers.size() / 2),
                    value);
        } else {
            return binarySearch(
                    numbers.subList(numbers.size() / 2 + 1, numbers.size()),
                    value);
        }
    }
}
