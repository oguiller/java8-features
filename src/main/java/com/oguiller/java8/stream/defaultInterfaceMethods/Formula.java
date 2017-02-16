package com.oguiller.java8.stream.defaultInterfaceMethods;

/**
 * Default methods allows us to add new functionalities to interfaces without breaking
 * the classes that implements that interface.
 * <p>
 * It was a way to introduce backwards compatibility to update the Collections classes
 */
interface Formula {
    double calculate(int a);

    // We have a default implementation for one of the methods in our interface
    // This feature is also known as Extension Methods.
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}