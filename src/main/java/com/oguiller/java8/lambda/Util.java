package com.oguiller.java8.lambda;

import com.oguiller.java8.anonymous.Person;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


// https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html

public class Util {
    /**
     * This approach can potentially make your application brittle, which is the likelihood of an application not working because of the introduction of updates (such as newer data types).
     * Suppose that you upgrade your application and change the structure of the Person class such that it contains different member variables; perhaps the class records and measures ages
     * with a different data type or algorithm. You would have to rewrite a lot of your API to accommodate this change.
     * In addition, this approach is unnecessarily restrictive; what if you wanted to print members younger than a certain age, for example?
     *
     * @param roster
     * @param age
     */
    public static void printPersonsOlderThan(List<Person> roster, int age) {
        for (Person p : roster) {
            if (p.getAge() >= age) {
                p.printPerson();
            }
        }
    }

    /**
     * What if you want to print members of a specified sex, or a combination of a specified gender and age range?
     * What if you decide to change the Person class and add other attributes such as relationship status or geographical
     * location? Although this method is more generic than printPersonsOlderThan, trying to create a separate method for
     * each possible search query can still lead to brittle code. You can instead separate the code that specifies the
     * criteria for which you want to search in a different class.
     *
     * @param roster
     * @param low
     * @param high
     */
    public static void printPersonsWithinAgeRange(
            List<Person> roster, int low, int high) {
        for (Person p : roster) {
            if (low <= p.getAge() && p.getAge() < high) {
                p.printPerson();
            }
        }
    }


    public static void printPersons(
            List<Person> roster, CheckPerson tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    public static void printPersonsWithPredicate(
            List<Person> roster, Predicate<Person> tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    public static void processPersons(
            List<Person> roster,
            Predicate<Person> tester,
            Consumer<Person> block) {
        for (Person p : roster) {
            if (tester.test(p)) {
                block.accept(p);
            }
        }
    }

    /**
     * What if you want to do more with your members' profiles than printing them out. Suppose that you want to validate
     * the members' profiles or retrieve their contact information? In this case, you need a functional interface that
     * contains an abstract method that returns a value. The Function<T,R> interface contains the method R apply(T t).
     * The following method retrieves the data specified by the parameter mapper, and then performs an action on it
     * specified by the parameter block:
     *
     * @param roster
     * @param tester
     * @param mapper
     * @param block
     */
    public static void processPersonsWithFunction(
            List<Person> roster,
            Predicate<Person> tester,
            Function<Person, String> mapper,
            Consumer<String> block) {
        for (Person p : roster) {
            if (tester.test(p)) {
                String data = mapper.apply(p);
                block.accept(data);
            }
        }
    }


    // Reconsider the method processPersonsWithFunction. The following is a generic version of it that accepts,
    // as a parameter, a collection that contains elements of any data type:
    public static <X, Y> void processElements(
            Iterable<X> source,
            Predicate<X> tester,
            Function<X, Y> mapper,
            Consumer<Y> block) {
        for (X p : source) {
            if (tester.test(p)) {
                Y data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    public static void main(String... args) {

        LocalDate birthday = LocalDate.of(1982, Month.JULY, 17);
        Person guille = new Person();
        guille.setName("Guille");
        guille.setBirthday(birthday);
        System.out.println("Guille is: " + guille.getAge() + " years old");
        List<Person> roster = new ArrayList<>(Arrays.asList(guille));

        // To use this class, you create a new instance of it and invoke the printPersons method:
        printPersons(
                roster, new CheckPersonEligibleForSelectiveService());

        // Specify Search Criteria Code in an Anonymous Class

        printPersons(
                roster,
                new CheckPerson() {
                    public boolean test(Person p) {
                        return p.getGender() == Person.Sex.MALE
                                && p.getAge() >= 18
                                && p.getAge() <= 25;
                    }
                }
        );

        // Specify Search Criteria Code with a Lambda Expression

        printPersons(
                roster,
                (Person p) -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25
        );

        //  Use Standard Functional Interfaces with Lambda Expressions
        // For example, you can use the Predicate<T> interface in place of CheckPerson. This interface contains the method boolean test(T t):


        printPersonsWithPredicate(
                roster,
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25
        );

        // Reconsider the method printPersonsWithPredicate to see where else you could use lambda expressions:

        /**
         * Instead of invoking the method printPerson, you can specify a different action to perform on those Person
         * instances that satisfy the criteria specified by tester. You can specify this action with a lambda expression.
         * Suppose you want a lambda expression similar to printPerson, one that takes one argument (an object of type Person)
         * and returns void. Remember, to use a lambda expression, you need to implement a functional interface. In this case,
         * you need a functional interface that contains an abstract method that can take one argument of type Person and
         * returns void. The Consumer<T> interface contains the method void accept(T t), which has these characteristics.
         * The following method replaces the invocation p.printPerson() with an instance of Consumer<Person> that invokes
         * the method accept:
         */

        processPersons(
                roster,
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25,
                p -> p.printPerson()
        );

        // The following method retrieves the email address from each member contained in roster who is eligible
        // for Selective Service and then prints it:

        processPersonsWithFunction(
                roster,
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25,
                p -> p.getEmailAddress(),
                email -> System.out.println(email)
        );

        processElements(
                roster,
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25,
                p -> p.getEmailAddress(),
                email -> System.out.println(email)
        );

        //Use Aggregate Operations That Accept Lambda Expressions as Parameters

        roster
                .stream()
                .filter(
                        p -> p.getGender() == Person.Sex.MALE
                                && p.getAge() >= 18
                                && p.getAge() <= 25)
                .map(p -> p.getEmailAddress())
                .forEach(email -> System.out.println(email));

    }
}

