package com.oguiller.java.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {

    public static List<Integer> mergesort(final List<Integer> values) {
        System.out.println("Executing MergeSort");
        printList(values);
        if (values.size() < 2) {
            return values;
        }

        final List<Integer> leftHalf = values.subList(0, values.size() / 2);
        final List<Integer> rightHalf = values.subList(values.size() / 2, values.size());
        return merge(mergesort(leftHalf), mergesort(rightHalf));
    }

    private static List<Integer> merge(final List<Integer> left, final List<Integer> right) {
        System.out.println("Executing Merge Left");
        printList(left);
        System.out.println("Executing Merge Right");
        printList(right);
        int leftPtr = 0;
        int rightPtr = 0;
        final List<Integer> merged = new ArrayList<>(left.size() + right.size());

        while (leftPtr < left.size() && rightPtr < right.size()) {
            if (left.get(leftPtr) < right.get(rightPtr)) {
                merged.add(left.get(leftPtr));
                leftPtr++;
            } else {
                merged.add(right.get(rightPtr));
                rightPtr++;
            }
        }
        while (leftPtr < left.size()) {
            merged.add(left.get(leftPtr));
            leftPtr++;
        }
        while (rightPtr < right.size()) {
            merged.add(right.get(rightPtr));
            rightPtr++;
        }
        System.out.println("Contents of MERGED");
        printList(merged);
        System.out.println("-------------------");
        return merged;
    }

    static void printList(List arr) {
        arr.forEach(x -> System.out.println(x));

    }

    public static void main(String args[]) {
        List<Integer> numbers = new ArrayList<>();
        numbers.addAll(Arrays.asList(3, 4, 5, 1, 8));

        List orderedNumbers = mergesort(numbers);
        System.out.println("----------------------------------------------");
        orderedNumbers.forEach(number -> System.out.println(number));
    }
}
