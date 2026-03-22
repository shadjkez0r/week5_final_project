package team4.finalproject.service;

import team4.finalproject.domain.Student;
import team4.finalproject.service.strategy.SortingStrategy;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class SortingService {
    private SortingStrategy<Student> strategy;

    public SortingService(SortingStrategy<Student> strategy) {
        this.strategy = Objects.requireNonNull(strategy, "Strategy must not be null");
    }

    public void setStrategy(SortingStrategy<Student> strategy) {
        this.strategy = Objects.requireNonNull(strategy, "Strategy must not be null");
    }

    public void sort(List<Student> students, Comparator<Student> comparator) {
        Objects.requireNonNull(students, "Students list must not be null");
        Objects.requireNonNull(comparator, "Comparator must not be null");
        Objects.requireNonNull(strategy, "Strategy must not be null");
        strategy.sort(students, comparator);
    }
}
