package com.oguiller.java8.collections;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueExample1 {
    public static void main(String[] args) {
        Queue<String> q = new ArrayDeque<>();
        q.add("Fred");
        q.add("Jim");
        q.add("Sheila");
        System.out.println(q);
        System.out.println("item> " + q.remove());
        System.out.println("item> " + q.remove());
        System.out.println(q);
        q.add("Toni");
        q.add("Alice");
        q.add("William");
        System.out.println(q);
        System.out.println("item> " + q.remove());
        System.out.println("item> " + q.remove());
        System.out.println("item> " + q.remove());
        System.out.println("item> " + q.remove());
        System.out.println(q);
        System.out.println(q.size());
        System.out.println("q.poll gives: " + q.poll());
        q.remove();
    }
}
