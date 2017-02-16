package com.oguiller.java8.stream.defaultInterfaceMethods;

public class FormulaExample {

    public static void main(String... args) {

        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };

        formula.calculate(100);     // 100.0
        formula.sqrt(16);
    }
}
