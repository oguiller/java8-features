package com.oguiller.java8.collections;

import java.util.ArrayDeque;
import java.util.Deque;

public class Deque2 {
    public static void main(String[] args) {
        Deque<String> deck = new ArrayDeque<>();
        deck.add("3 of Clubs");
        deck.add("9 of Diamonds");
        deck.add("5 of Clubs");
        deck.add("2 of Clubs");
        deck.add("Ace of Spades");
        deck.add("Ace of Clubs");
        deck.add("Ace of Diamonds");
        deck.add("Ace of Hearts");
        System.out.println("Player one gets " + deck.removeFirst());
        System.out.println("          Player two gets " + deck.removeLast());
        System.out.println("Player one gets " + deck.removeFirst());
        System.out.println("          Player two gets " + deck.removeLast());
        System.out.println("Player one gets " + deck.removeFirst());
        System.out.println("          Player two gets " + deck.removeLast());
        System.out.println("Player one gets " + deck.removeFirst());
        System.out.println("          Player two gets " + deck.removeLast());
    }
}