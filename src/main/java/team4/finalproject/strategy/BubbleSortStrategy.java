package team4.finalproject.strategy;

import java.util.Comparator;
import java.util.List;

public class BubbleSortStrategy<T> implements SortingStrategy<T> {
    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    swap(j, j + 1, list);
                }
            }
        }
    }

    private void swap(int x, int y, List<T> list) {
        T temp = list.get(x);
        list.set(x, list.get(y));
        list.set(y, temp);
    }
}
