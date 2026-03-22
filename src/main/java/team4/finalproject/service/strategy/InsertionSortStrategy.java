package team4.finalproject.service.strategy;

import java.util.Comparator;
import java.util.List;

public class InsertionSortStrategy<T> implements SortingStrategy<T> {
    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        for (int i = 1; i < list.size(); i++) {
            T current = list.get(i);
            int x = i - 1;

            while (x >= 0 && comparator.compare(current, list.get(x)) < 0) {
                list.set(x + 1, list.get(x));
                x--;
            }
            list.set(x + 1, current);
        }
    }
}
