package team4.finalproject.strategy;

import java.util.Comparator;
import java.util.List;

public interface SortingStrategy<T> {
    void sort(List<T> list, Comparator<T> comparator);
}
