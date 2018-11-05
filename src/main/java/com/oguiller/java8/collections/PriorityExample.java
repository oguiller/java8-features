package com.oguiller.java8.collections;

import java.util.PriorityQueue;
import java.util.Queue;

class ComparableStudent implements Comparable<ComparableStudent> {
    private String name;
    private String grade;

    public ComparableStudent(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "ComparableStudent{" + "name=" + name + ", grade=" + grade + '}';
    }

    @Override
    public int compareTo(ComparableStudent o) {
        return grade.compareTo(o.grade);
    }
}

public class PriorityExample {
    public static void main(String[] args) {
        Queue<ComparableStudent> qs = new PriorityQueue<>();
        qs.add(new ComparableStudent("Fred", "F"));
        qs.add(new ComparableStudent("Jim", "E"));
        qs.add(new ComparableStudent("Sheila", "A"));
        qs.add(new ComparableStudent("Alice", "B"));
        qs.add(new ComparableStudent("William", "B"));
        qs.add(new ComparableStudent("Toni", "A"));
        qs.add(new ComparableStudent("Jo", "E"));
        System.out.println(qs);
        while (qs.size() > 0) {
            System.out.println(qs.remove());
        }

    }
}