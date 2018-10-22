package com.oguiller.java8.future;

/**
 * Code example inspired by Programming Promises in Java 8 Using Completable Futures bt Raoul-Gabriel UrmaRichard Warburton
 */
public class ExchangeService {

    public double exchangeCurrency(Currency source, Currency destination, double value) {
        double rate = lookupExchangeRate(source, destination);
        return exchange(value, rate);
    }

    public double exchange(double value, double rate) {
        return Utils.round(value * rate);
    }

    public double lookupExchangeRate(Currency source, Currency destination) {
        return getRateWithDelay(source, destination);
    }

    private double getRateWithDelay(Currency source, Currency destination) {
        Utils.randomDelay();
        return source.rate / destination.rate;
    }

}
