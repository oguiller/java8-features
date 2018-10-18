package com.oguiller.java8.generics;

public class Test {

    public static void main(String args[]) {
        Box box = new Box();
        box.set("Alo");
        System.out.println(box.get());
        GenericBox<Box> genericBox = new GenericBox<>();
        genericBox.set(box);
        System.out.println("Generic Box says: " + genericBox.get().get());
    }
}
