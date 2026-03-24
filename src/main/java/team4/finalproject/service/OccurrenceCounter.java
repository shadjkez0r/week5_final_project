package team4.finalproject.service;

import team4.finalproject.collection.CustomList;

import java.util.function.Predicate;

public class OccurrenceCounter {
    public <T> long count(CustomList<T> collection, Predicate<T> predicate) {
        long result = collection.parallelStream()
                .filter(predicate)
                .count();

        System.out.println("[" + Thread.currentThread().getName() + "] "
                + "Occurrences found: " + result);
        return result;
    }
}
