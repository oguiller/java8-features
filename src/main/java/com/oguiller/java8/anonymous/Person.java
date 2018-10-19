package com.oguiller.java8.anonymous;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Person {

    String name;
    LocalDate birthday;
    Sex gender;
    String emailAddress;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Sex getGender() {
        return gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void printPerson() {
        // ...
    }

    public enum Sex {
        MALE, FEMALE
    }

}
