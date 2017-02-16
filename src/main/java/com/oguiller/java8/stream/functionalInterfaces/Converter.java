package com.oguiller.java8.stream.functionalInterfaces;

/**
 * ]
 * Functional Interface or Single Abstract Method interfaces (SAM Interfaces)
 * More examples can be found in the JDK like java.util.Comparator, java.lang.Runnable etc.
 */

@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}