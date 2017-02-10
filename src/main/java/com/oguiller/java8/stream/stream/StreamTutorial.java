package com.oguiller.java8.stream.stream;

import com.oguiller.java8.stream.entities.Bar;
import com.oguiller.java8.stream.entities.Foo;
import com.oguiller.java8.stream.entities.Outer;
import com.oguiller.java8.stream.entities.Person;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTutorial {

    /**
     * A stream represents a sequence of elements and supports different kind of operations to perform computations upon those elements:
     *
     */
    public static void main(String args[]) {
        List<String> myList =
                Arrays.asList("a1", "a2", "b1", "c2", "c1");

        myList
                .stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

        /**
         * <p>Stream operations are either <b>intermediate</b> or <b>terminal</b>. Intermediate operations return a stream so we can chain
         * multiple intermediate operations without using semicolons. Terminal operations are either void or return a non-stream result.
         * In the above example filter, map and sorted are intermediate operations whereas forEach is a terminal operation.
         * </p>
         *
         * <p>
         *     Most stream operations accept some kind of lambda expression parameter, a functional interface specifying the exact behavior of the operation.
         * Most of those operations must be both non-interfering and stateless.
         * </p>
         *
         * <p>
         *      A function is non-interfering when it does not modify the underlying data source of the stream,
         *  e.g. in the above example no lambda expression does modify myList by adding or removing elements from the collection.
         * </p>
         *
         * <p>A function is stateless when the execution of the operation is deterministic, e.g. in the above example no
         * lambda expression depends on any mutable variables or states from the outer scope which might change during execution.
         * </p>
         */

        Arrays.asList("a1", "a2", "a3")
                .stream()
                .findFirst()
                .ifPresent(System.out::println);


        Stream.of("a1", "a2", "a3")
                .findFirst()
                .ifPresent(System.out::println);

        /**
         * <p>
         *    Besides regular object streams Java 8 ships with special kinds of streams for working with the primitive data types int,
         * long and double. As you might have guessed it's IntStream, LongStream and DoubleStream.
         * </p>
         */
        System.out.println("----- RANGE:");
        IntStream.range(1, 4)
                .forEach(System.out::println);

        /**
         * <p>
         *     All those primitive streams work just like regular object streams with the following differences: Primitive streams use specialized lambda expressions,
         * e.g. IntFunction instead of Function or IntPredicate instead of Predicate. And primitive streams support
         * the additional terminal aggregate operations sum() and average():
         * </p>
         */
        System.out.println("----- USING AVERAGE:");
        Arrays.stream(new int[] {1, 2, 3})
                .map(n -> 2 * n + 1)
                .average()
                .ifPresent(System.out::println);  // 5.0

        /**
         * <p>
         *     Sometimes it's useful to transform a regular object stream to a primitive stream or vice versa.
         *     For that purpose object streams support the special mapping operations mapToInt(), mapToLong() and mapToDouble:
         * </p>
         */
        System.out.println("----- CONVERTING TO PRIMITIVE (MapToInt):");
        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);  // 3

        // Primitive streams can be transformed to object streams via mapToObj():

        System.out.println("----- PRIMITIVE TO OBJECT (MapToObj):");
        IntStream.range(1, 4)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);


        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);


        // PROCESSING ORDER
        /**
         * When executing this code snippet, nothing is printed to the console. That is because intermediate operations will only
         * be executed when a terminal operation is present.
         */

        System.out.println("----- TERMINAL OPERATION MISSING:");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                });

        // Let's extend the above example by the terminal operation forEach:
        System.out.println("----- ADDING TERMINAL OPERATION:");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));

        /**
         * <p>
         *     The operation anyMatch returns true as soon as the predicate applies to the given input element.
         *     This is true for the second element passed "A2". Due to the vertical execution of the stream chain, map has only to be executed twice in this case.
         * </p>
         */
        System.out.println("----- USING Map AND anyMatch:");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });
        // Why order matters

        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("forEach: " + s));

        System.out.println("----- CHANGING THE ORDER OF OPERATIONS:");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));

        /**
         * First, the sort operation is executed on the entire input collection. In other words sorted is executed horizontally.
         * So in this case sorted is called eight times for multiple combinations on every element in the input collection.
         */

        System.out.println("----- SORTING THE COLLECTION:");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));

        System.out.println("----- SORTING AND ALTERING THE ORDER:");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));

        // Reusing Streams

        /**
         * Java 8 streams cannot be reused. As soon as you call any terminal operation the stream is closed:
         */

        System.out.println("----- STREAMS REUSED:");
        Stream<String> stream =
                Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

//        stream.anyMatch(s -> true);    // ok
//        stream.noneMatch(s -> true);   // exception


        /**
         * To overcome this limitation we have to to create a new stream chain for every terminal operation we want to execute,
         * e.g. we could create a stream supplier to construct a new stream with all intermediate operations already set up:
         */
        System.out.println("----- STREAM SUPPLIER:");
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        streamSupplier.get().anyMatch(s -> true);   // ok
        streamSupplier.get().noneMatch(s -> true);  // ok

        // Each call to get() constructs a new stream on which we are save to call the desired terminal operation.


        // Advanced Operations


        List<Person> persons =
                Arrays.asList(
                        new Person("Guille", 34),
                        new Person("Pau", 34),
                        new Person("Alex", 36),
                        new Person("Ivan", 35));

        /**
         * Collect is an extremely useful terminal operation to transform the elements of the stream into a different kind of result, e.g. a List, Set or Map.
         * Collect accepts a Collector which consists of four different operations: a supplier, an accumulator, a combiner and a finisher.
         */

        List<Person> filtered =
                persons
                        .stream()
                        .filter(p -> p.getName().startsWith("P"))
                        .collect(Collectors.toList());

        System.out.println(filtered);


        System.out.println("----- GROUPING BY:");
        Map<Integer, List<Person>> personsByAge = persons
                .stream()
                .collect(Collectors.groupingBy(p -> p.getAge()));

        personsByAge
                .forEach((age, p) -> System.out.format("age %s: %s\n", age, p));


        /**
         * Collectors are extremely versatile. You can also create aggregations on the elements of the stream, e.g. determining the average age of all persons:
         */
        System.out.println("----- USING AVERAGE:");
        Double averageAge = persons
                .stream()
                .collect(Collectors.averagingInt(p -> p.getAge()));

        System.out.println(averageAge);

        // Summarizing collectors return a special built-in summary statistics object.
        System.out.println("----- IntSummaryStatistics:");
        IntSummaryStatistics ageSummary =
                persons
                        .stream()
                        .collect(Collectors.summarizingInt(p -> p.getAge()));

        System.out.println(ageSummary);


        System.out.println("----- COLLECTORS JOINING:");
        String phrase = persons
                .stream()
                .filter(p -> p.getAge() >= 18)
                .map(p -> p.getName())
                .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));

        System.out.println(phrase);

        /**
         * <p>
         *     In order to transform the stream elements into a map, we have to specify how both the keys and the values should be mapped.
         *     Keep in mind that the mapped keys must be unique, otherwise an IllegalStateException is thrown. You can optionally pass a merge function
         *     as an additional parameter to bypass the exception:
         * </p>
         */

        System.out.println("----- GENERATING MAP:");
        Map<Integer, String> map = persons
                .stream()
                .collect(Collectors.toMap(
                        p -> p.getAge(),
                        p -> p.getName(),
                        (name1, name2) -> name1 + ";" + name2));

        System.out.println(map);

        /**
         * <p>
         *      Since strings in Java are immutable, we need a helper class like StringJoiner to let the collector construct our string.
         * The supplier initially constructs such a StringJoiner with the appropriate delimiter.
         * The accumulator is used to add each persons upper-cased name to the StringJoiner.
         * The combiner knows how to merge two StringJoiners into one. In the last step the finisher constructs the desired String from the StringJoiner.
         * </p>
         */
        System.out.println("----- CREATING OUR OWN COLLECTOR:");
        Collector<Person, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> new StringJoiner(" | "),          // supplier
                        (j, p) -> j.add(p.getName().toUpperCase()),  // accumulator
                        (j1, j2) -> j1.merge(j2),               // combiner
                        StringJoiner::toString);                // finisher

        String names = persons
                .stream()
                .collect(personNameCollector);

        System.out.println(names);  // MAX | PETER | PAMELA | DAVID

        // FlatMap

        List<Foo> foos = new ArrayList<>();

        // create foos
        IntStream
                .range(1, 4)
                .forEach(i -> foos.add(new Foo("Foo" + i)));

        // create bars
        foos.forEach(f ->
                IntStream
                        .range(1, 4)
                        .forEach(i -> f.getBars().add(new Bar("Bar" + i + " <- " + f.getName()))));

        /**
         * FlatMap accepts a function which has to return a stream of objects.
         * So in order to resolve the bar objects of each foo, we just pass the appropriate function:
         */
        System.out.println("----- FlatMap:");
        foos.stream()
                .flatMap(f -> f.getBars().stream())
                .forEach(b -> System.out.println(b.getName()));

        IntStream.range(1, 4)
                .mapToObj(i -> new Foo("Foo" + i))
                .peek(f -> IntStream.range(1, 4)
                        .mapToObj(i -> new Bar("Bar" + i + " <- " + f.getName()))
                        .forEach(f.getBars()::add))
                .flatMap(f -> f.getBars().stream())
                .forEach(b -> System.out.println(b.getName()));


        Outer outer = new Outer();
        if (outer != null && outer.getNested() != null && outer.getNested().getInner() != null) {
            System.out.println(outer.getNested().getInner().getFoo());
        }


        System.out.println("----- FlatMap to check for NULL:");
        // Checking NULL
        Optional.of(new Outer())
                .flatMap(o -> Optional.ofNullable(o.getNested()))
                .flatMap(n -> Optional.ofNullable(n.getInner()))
                .flatMap(i -> Optional.ofNullable(i.getFoo()))
                .ifPresent(System.out::println);


        // REDUCE

        /**
         * <p>
         *     The reduction operation combines all elements of the stream into a single result. Java 8 supports three different kind of reduce methods.
         *     The first one reduces a stream of elements to exactly one
         *     element of the stream. Let's see how we can use this method to determine the oldest person:
         *
         * </p>
         */

        System.out.println("----- REDUCE with a BiFinction:");
        persons
                .stream()
                .reduce((p1, p2) -> p1.getAge() > p2.getAge() ? p1 : p2)
                .ifPresent(System.out::println);

        /**
         * <p>
         *     The reduce method accepts a BinaryOperator accumulator function. That's actually a BiFunction where both operands share the same type, in that case Person.
         *     BiFunctions are like Function but accept two arguments.
         *     The example function compares both persons ages in order to return the person with the maximum age.
         * </p>
         */


        /**
         * <p>
         *     The second reduce method accepts both an identity value and a BinaryOperator accumulator. This method can be utilized to construct a new Person with the aggregated names and ages
         *     from all other persons in the stream:
         * </p>
         */

        System.out.println("----- REDUCE (BiFunction & Accumulator):");
        Person result =
                persons
                        .stream()
                        .reduce(new Person("", 0), (p1, p2) -> {
                            p1.age += p2.getAge();
                            p1.name += p2.getName();
                            return p1;
                        });

        System.out.format("name=%s; age=%s", result.getName(), result.getAge());

        /**
         * <p>
         *     The third reduce method accepts three parameters: an identity value, a BiFunction accumulator and a combiner function of type BinaryOperator.
         *     Since the identity values type is not restricted to the Person type, we can utilize this reduction to determine the sum of ages from all persons:
         * </p>
         */


        System.out.println("----- REDUCE (BiFunction & Accumulator) AgedSum:");
        Integer ageSum = persons
                .stream()
                .reduce(0, (sum, p) -> sum += p.age, (sum1, sum2) -> sum1 + sum2);

        System.out.println(ageSum);


        Integer ageSum2 = persons
                .stream()
                .reduce(0,
                        (sum, p) -> {
                            System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
                            return sum += p.age;
                        },
                        (sum1, sum2) -> {
                            System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
                            return sum1 + sum2;
                        });

        System.out.println(ageSum2);

        /**
         * <p>
         *     As you can see the accumulator function does all the work. It first get called with the initial identity value 0 and the first person Max.
         *     In the next three steps sum continually increases by the age of the last steps person up to a total age of 76.
         * </p>
         */
        Integer ageSumParallel = persons
                .parallelStream()
                .reduce(0,
                        (sum, p) -> {
                            System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
                            return sum += p.age;
                        },
                        (sum1, sum2) -> {
                            System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
                            return sum1 + sum2;
                        });

        /**
         * Executing this stream in parallel results in an entirely different execution behavior. Now the combiner is actually called. Since the accumulator is called in
         * parallel, the combiner is needed to sum up the separate accumulated values.
         */

        // PARALLEL STREAMS

        /**
         * Streams can be executed in parallel to increase runtime performance on large amount of input elements. Parallel streams use a common ForkJoinPool available via the static ForkJoinPool.commonPool() method.
         * The size of the underlying thread-pool uses up to five threads - depending on the amount of available physical CPU cores:
         */

        System.out.println("----- NUMBER OF PARALLEL THREADS:");
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println(commonPool.getParallelism());

        // Can be updated via -Djava.util.concurrent.ForkJoinPool.common.parallelism=5

        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                        s, Thread.currentThread().getName()));


        System.out.println("----- PARALLEL WITH SORT:");
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .sorted((s1, s2) -> {
                    System.out.format("sort: %s <> %s [%s]\n",
                            s1, s2, Thread.currentThread().getName());
                    return s1.compareTo(s2);
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                        s, Thread.currentThread().getName()));

    }
}
