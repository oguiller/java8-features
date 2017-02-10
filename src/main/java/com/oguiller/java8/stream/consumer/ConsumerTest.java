package com.oguiller.java8.stream.consumer;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

public class ConsumerTest {

    /**
     * java.util.function.Consumer is a functional interface. It accepts an input and returns no result.
     * We instantiate it as follows for the given object.
     *
     * Consumer can be used in all contexts where an object needs to be consumed,i.e. taken as input, and some operation is
     * to be performed on the object without returning any result. Common example of such an operation is printing where an
     * object is taken as input to the printing function and the value of the object is printed
     *
     */
    public static void main(String args[]) {
        Consumer<String> consumer = ConsumerTest::printNames;

        consumer.accept("Guille");
        consumer.accept("Pau");
        consumer.accept("Choni");
        consumer.accept("Cesar");

        List<String> listOfPrimes = Arrays.asList("Guille","Pau", "Cesar", "Choni", "Ivan", "Alex");

        listOfPrimes.stream().forEach(consumer);

        /**
         * Java 8 also provides consumer for primitive data types such as IntConsumer, LongConsumer and DoubleConsumer.
         */

        System.out.println("--IntConsumer--");
        int[] intNum = {3,5,6,10,15};
        IntConsumer intCon = i -> System.out.print(i+" ");
        Arrays.stream(intNum).forEach(intCon);

        System.out.println("\n--LongConsumer--");
        long[] longNum = {13l,9l,6l,10l,15l};
        LongConsumer longCon = l -> System.out.print(l+" ");
        Arrays.stream(longNum).forEach(longCon);

        System.out.println("\n--DoubleConsumer--");
        double[] dbNum = {13.4d,9.1d,6.5d,10.3d,15.3d};
        DoubleConsumer dbCon = d -> System.out.print(d+" ");
        Arrays.stream(dbNum).forEach(dbCon);

    }

    private static void printNames(String name) {
        System.out.println(name);
    }
}
