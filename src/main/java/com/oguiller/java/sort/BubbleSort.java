package com.oguiller.java.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://en.wikipedia.org/wiki/Bubble_sort
 * <p>
 * Bubble sort, sometimes referred to as sinking sort, is a simple sorting algorithm that repeatedly steps through the list,
 * compares adjacent pairs and swaps them if they are in the wrong order. The pass through the list is repeated until
 * the list is sorted. The algorithm, which is a comparison sort, is named for the way smaller or larger elements
 * "bubble" to the top of the list. Although the algorithm is simple, it is too slow and impractical for most problems
 * even when compared to insertion sort.[2] Bubble sort can be practical if the input is in mostly sorted order with
 * some out-of-order elements nearly in position.
 */
public class BubbleSort {

    public static <E extends Comparable> List<E> bubbleSort(List<E> toBeSorted) {
        boolean switchElements = false;

        do {
            switchElements = false;
            for (int i = 0; i < toBeSorted.size() - 1; i++) {
                E comparingElement = toBeSorted.get(i);
                E followingComparingElement = toBeSorted.get(i + 1);
                if (followingComparingElement.compareTo(comparingElement) < 0) {
                    System.out.printf("%s vs %s\n", comparingElement, followingComparingElement);
                    Collections.swap(toBeSorted, i + 1, i);
                    switchElements = true;
                }
            }
        } while (switchElements);

        return toBeSorted;
    }

    public static void main(String args[]) {
        List<Integer> numbers = new ArrayList<>();
        numbers.addAll(Arrays.asList(3, 4, 5, 1, 8));
        List<Integer> sortedNumbers = bubbleSort(numbers);

        System.out.println("Sorted List!!");
        sortedNumbers.forEach(x -> System.out.println(x));
    }
}
