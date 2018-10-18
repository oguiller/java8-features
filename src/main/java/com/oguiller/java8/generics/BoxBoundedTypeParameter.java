package com.oguiller.java8.generics;

/**
 * To declare a bounded type parameter, list the type parameter's name, followed by the extends keyword, followed by its upper bound, which in this example is Number.
 * Note that, in this context, extends is used in a general sense to mean either "extends" (as in classes) or "implements" (as in interfaces).
 */
public class BoxBoundedTypeParameter<T> {

    private T t;

    public static void main(String[] args) {
        BoxBoundedTypeParameter<Integer> integerBox = new BoxBoundedTypeParameter<Integer>();
        integerBox.set(new Integer(10));
        // integerBox.inspect("some text"); // error: this is still String!
    }

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public <U extends Number> void inspect(U u) {
        System.out.println("T: " + t.getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }

    /**
     * Comparisson and bounded type limitations: https://docs.oracle.com/javase/tutorial/java/generics/boundedTypeParams.html
     */

    /**
     * <p>
     *      What type of argument does it accept? By looking at its signature, you can see that it accepts a single argument whose type is Box<Number>.
     *      But what does that mean? Are you allowed to pass in Box<Integer> or Box<Double>, as you might expect? The answer is "no",
     *      because Box<Integer> and Box<Double> are not subtypes of Box<Number>.This is a common misunderstanding when it comes to programming with
     *      generics, but it is an important concept to learn.
     *
     *      https://docs.oracle.com/javase/tutorial/java/generics/inheritance.html
     * </p>
     */
}
