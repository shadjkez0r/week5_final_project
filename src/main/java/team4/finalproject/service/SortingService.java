package team4.finalproject.service;

import team4.finalproject.domain.Student;
import team4.finalproject.strategy.SortingStrategy;

import java.util.Comparator;
import java.util.List;

public class SortingService {
    private SortingStrategy<Student> strategy;

    public SortingService(SortingStrategy<Student> strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SortingStrategy<Student> strategy) {
        this.strategy = strategy;
    }

    public void sort(List<Student> students, Comparator<Student> comparator) {
        strategy.sort(students, comparator);
    }


}
