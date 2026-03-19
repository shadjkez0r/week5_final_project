package team4.finalproject.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team4.finalproject.domain.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class BubbleSortStrategyTest {
    private BubbleSortStrategy<Student> strategy;

    @BeforeEach
    void setUp() {
        strategy = new BubbleSortStrategy<>();
    }

    // Проверка на пустые входные данные
    @Test
    void emptyList() {
        List<Student> input = new ArrayList<>();
        List<Student> expected = new ArrayList<>();

        Comparator<Student> comparator = Comparator.naturalOrder();
        strategy.sort(input, comparator);

        assertIterableEquals(input, expected);
    }

    // Сравнение через средний балл
    @Test
    void groupByAverageScore() {
        List<Student> input = new ArrayList<>();

        input.add(new Student(4, 3.45, 15));
        input.add(new Student(3, 3.06, 9));
        input.add(new Student(2, 4.15, 9));

        List<Student> expected = List.of(
                new Student(3, 3.06, 9),
                new Student(4, 3.45, 15),
                new Student(2, 4.15, 9)
        );

        Comparator<Student> comparator = Comparator.comparingDouble(Student::getAverageScore);

        strategy.sort(input, comparator);

        assertIterableEquals(input, expected);
    }

    // Сравнение через номер группы
    @Test
    void groupByGroupNumber() {
        List<Student> input = new ArrayList<>();

        input.add(new Student(4, 3.45, 15));
        input.add(new Student(3, 3.06, 9));
        input.add(new Student(2, 4.15, 9));
        input.add(new Student(1, 4.01, 11));

        List<Student> expected = List.of(
                new Student(1, 4.01, 11),
                new Student(2, 4.15, 9),
                new Student(3, 3.06, 9),
                new Student(4, 3.45, 15)
        );

        Comparator<Student> comparator = (x2, x1) -> Integer.compare(x2.getGroupNumber(), x1.getGroupNumber());

        strategy.sort(input, comparator);

        assertIterableEquals(input, expected);
    }

    // Сравнение через номер книги
    @Test
    void groupByRecordBookNumber() {
        List<Student> input = new ArrayList<>();

        input.add(new Student(4, 3.45, 15));
        input.add(new Student(3, 3.06, 9));
        input.add(new Student(2, 4.15, 9));
        input.add(new Student(1, 4.01, 11));
        input.add(new Student(3, 4.44, 23));

        List<Student> expected = List.of(
                new Student(3, 4.44, 23),
                new Student(4, 3.45, 15),
                new Student(1, 4.01, 11),
                new Student(2, 4.15, 9),
                new Student(3, 3.06, 9)
        );

        Comparator<Student> comparator = (x1, x2) -> Integer.compare(x2.getRecordBookNumber(), x1.getRecordBookNumber());

        strategy.sort(input, comparator);

        assertIterableEquals(input, expected);
    }

}
