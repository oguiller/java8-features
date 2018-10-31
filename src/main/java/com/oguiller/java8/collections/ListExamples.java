package com.oguiller.java8.collections;

import java.util.List;

public class ListExamples {
    public static void main(String[] args) {
//        List<String> ls = new ArrayList<>();
//        List<String> ls = new MyArrayList<>();
        List<String> ls = new MyLinkedList<>();
        ls.add("Fred");
        ls.add("Sheila");
        System.out.println(ls);
        ls.add(1, "Jim");
        System.out.println(ls);
        System.out.println(ls.get(0));
        ls.set(1, "Jimmy");
        System.out.println(ls);
        ls.remove(1);
        System.out.println(ls);
        ls.add(1, "Jim");
        System.out.println(ls);
//        ls.remove("Jim");
//        System.out.println(ls);
        ls.add("Felicity");
        ls.add("Mary");
        ls.add("Algernon");
        ls.add("Fred");
        ls.add("Tina");
        System.out.println(ls);
//        List<String> subList = ls.subList(2, 4);
//        System.out.println(subList);
//        
//        System.out.println(ls.indexOf("Fred"));
//        System.out.println(ls.lastIndexOf("Fred"));
    }
}
