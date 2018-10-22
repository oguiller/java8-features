package com.oguiller.java8.future;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;

public class Test {

    private static final ExchangeService exchangeService = new ExchangeService();

    public static List<Double> getExchangeRatesSequencially(List<Currency> currencies) {
        List<Double> exchangeRates = new ArrayList<>();

        return currencies.stream().map(currency -> exchangeService.lookupExchangeRate(Currency.USD, currency)).collect(toList());
    }

    public static List<CompletableFuture<Double>> getExchangeRatesFutures(List<Currency> currencies) {
        List<CompletableFuture> exchangeRatesFutures = new ArrayList<>();

        return currencies
                .stream()
                .map(currency -> CompletableFuture.supplyAsync(() -> exchangeService.lookupExchangeRate(Currency.USD, currency))).collect(toList());

    }

    private static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
        CompletableFuture<Void> allFuturesDone =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        return allFuturesDone.thenApply(v ->
                futures.stream()
                        .map(CompletableFuture::join)
                        .collect(toList()));
    }

    public static void main(String args[]) {

        List<Currency> currencies = Arrays.asList(Currency.values());

        long time = System.currentTimeMillis();
        List<Double> ratesSeq = getExchangeRatesSequencially(currencies);
        ratesSeq.stream().forEach(rate -> System.out.println("SEQUENTIAL RATE: " + rate));
        System.out.printf("It took the SEQUENTIAL version %d ms to calculate this\n", System.currentTimeMillis() - time);

        time = System.currentTimeMillis();

        List<CompletableFuture<Double>> futureAsynchRates = getExchangeRatesFutures(currencies);
        CompletableFuture<List<Double>> futureAsynchRatesCompleted = sequence(futureAsynchRates);

        futureAsynchRatesCompleted.thenAccept((asynchRates) -> {
                    asynchRates.stream().forEach(rate -> System.out.println("ASYNCH RATE: " + rate));
                }
        );
        futureAsynchRatesCompleted.join();

        System.out.printf("It took the ASYNCHRONOUS version %d ms to calculate this\n", System.currentTimeMillis() - time);

    }
}
