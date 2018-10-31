package com.oguiller.java8.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<E> implements List<E> {

    private Node<E> head = new Node<>();

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        Node last = head;

        Node newNode = new Node();
        newNode.data = e;

        while (last.next != null) {
            last = last.next;
        }

        last.next = newNode;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        Node<E> n = head;

        int i = index;

        while (i-- >= 0) {
            n = n.next;
        }

        return n.data;
    }

    @Override
    public void add(int index, E element) {
        Node before = head;
        Node newNode = new Node();
        newNode.data = element;

        int i = index - 1;
        while (i-- >= 0) {
            before = before.next;
        }

        Node after = before.next;
        newNode.next = after;
        before.next = newNode;

    }

    @Override
    public E set(int index, E element) {
        Node<E> n = head;
        int i = index;
        while (i-- >= 0) {
            n = n.next;
        }
        E old = n.data;
        n.data = element;
        return old;
    }

    @Override
    public E remove(int index) {
        Node<E> before = head;
        int i = index - 1;
        while (i-- >= 0) {
            before = before.next;
        }
        Node<E> thisOne = before.next;
        Node after = thisOne.next;
        before.next = after;
        return thisOne.data;
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MyLinkedList[");
        Node n = head;
        while (n.next != null) {
            n = n.next;
            sb.append(n.data);
            if (n.next != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private class Node<E> {
        Node<E> next;
        E data;
    }
}
