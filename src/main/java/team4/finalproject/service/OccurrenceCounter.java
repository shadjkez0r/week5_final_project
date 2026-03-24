package team4.finalproject.service;

import team4.finalproject.collection.CustomList;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

public class OccurrenceCounter {
    public <T> long count(CustomList<T> collection, Predicate<T> predicate) {
        Set<String> threadNames = ConcurrentHashMap.newKeySet();

        long result = collection.parallelStream()
                .filter(element -> {
                    threadNames.add(Thread.currentThread().getName());
                    return predicate.test(element);
                })
                .count();

        System.out.println("Threads used: " + threadNames);
        System.out.println("Occurrences found: " + result);
        return result;
    }
}
