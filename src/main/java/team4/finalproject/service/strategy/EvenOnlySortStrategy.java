package team4.finalproject.service.strategy;

import team4.finalproject.domain.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

public class EvenOnlySortStrategy implements SortingStrategy<Student> {
    private final SortingStrategy<Student> delegate;
    private final ToIntFunction<Student> fieldForNaturalOrder;

    public EvenOnlySortStrategy(SortingStrategy<Student> delegate, ToIntFunction<Student> fieldForNaturalOrder) {
        this.delegate = delegate;
        this.fieldForNaturalOrder = fieldForNaturalOrder;
    }

    @Override
    public void sort(List<Student> list, Comparator<Student> comparator) {
        List<Integer> evenIndices = new ArrayList<>();
        List<Student> evenElements = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (fieldForNaturalOrder.applyAsInt(list.get(i)) % 2 == 0) {
                evenIndices.add(i);
                evenElements.add(list.get(i));
            }
        }

        delegate.sort(evenElements, comparator);

        for (int i = 0; i < evenIndices.size(); i++) {
            list.set(evenIndices.get(i), evenElements.get(i));
        }

    }
}
