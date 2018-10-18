package com.oguiller.java8.generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestNaturalEvenNumbers {

    public static void main(String args[]) {
        List<EvenNumber> le = new ArrayList<>();
        le.add(new EvenNumber(2));
//        List<? extends NaturalNumber> ln = le;
        List<? extends NaturalNumber> ln = Collections.emptyList();
//        ln.add(new NaturalNumber(35));  // compile-time error
//        ln.add(new EvenNumber(33));  // compile-time error
        NaturalNumber n = ln.get(0);
        ln.add(null);
        ln.remove(1);

    }
}
