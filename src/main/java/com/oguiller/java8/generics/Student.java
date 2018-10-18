package com.oguiller.java8.generics;

public class Student extends Person implements Comparable<Person> {

    @Override
    public int compareTo(Person o) {
        return 0;
    }
}
