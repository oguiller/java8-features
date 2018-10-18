package com.oguiller.java8.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoxDemo {

    public static <U> void addBox(U u,
                                  java.util.List<GenericBox<U>> boxes) {
        GenericBox<U> box = new GenericBox<>();
        box.set(u);
        boxes.add(box);
    }

    public static <U> void outputBoxes(java.util.List<GenericBox<U>> boxes) {
        int counter = 0;
        for (GenericBox<U> box : boxes) {
            U boxContents = box.get();
            System.out.println("Box #" + counter + " contains [" +
                    boxContents.toString() + "]");
            counter++;
        }
    }


    /**
     * Although Integer is a subtype of Number, List<Integer> is not a subtype of List<Number> and, in fact, these two types are not related.
     * The common parent of List<Number> and List<Integer> is List<?>.
     *
     * @param list
     */
    public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        java.util.ArrayList<GenericBox<Integer>> listOfIntegerBoxes =
                new java.util.ArrayList<>();
        BoxDemo.<Integer>addBox(Integer.valueOf(10), listOfIntegerBoxes);
        BoxDemo.addBox(Integer.valueOf(20), listOfIntegerBoxes);
        BoxDemo.addBox(Integer.valueOf(30), listOfIntegerBoxes);
        BoxDemo.outputBoxes(listOfIntegerBoxes);
        Long x = new Long(1);
        addNumbers(new ArrayList<Number>(Arrays.asList(x, 1.6f, 1.7f)));
    }
}