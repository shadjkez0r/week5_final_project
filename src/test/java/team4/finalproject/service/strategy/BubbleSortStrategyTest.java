package team4.finalproject.service.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team4.finalproject.domain.Student;
import team4.finalproject.service.StudentComparators;
import team4.finalproject.service.strategy.BubbleSortStrategy;

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

        Comparator<Student> comparator = StudentComparators.BY_RECORD_BOOK_NUMBER;
        strategy.sort(input, comparator);

        assertIterableEquals(input, expected);
    }

    // Сравнение через средний балл
    @Test
    void groupByAverageScore() {
        List<Student> input = new ArrayList<>();

        input.add(Student.builder().groupNumber(4).averageScore(3.45).recordBookNumber(15).build());
        input.add(Student.builder().groupNumber(3).averageScore(3.06).recordBookNumber(9).build());
        input.add(Student.builder().groupNumber(2).averageScore(4.15).recordBookNumber(10).build());

        List<Student> expected = List.of(
                Student.builder().groupNumber(2).averageScore(4.15).recordBookNumber(10).build(),
                Student.builder().groupNumber(4).averageScore(3.45).recordBookNumber(15).build(),
                Student.builder().groupNumber(3).averageScore(3.06).recordBookNumber(9).build()
        );

        Comparator<Student> comparator = StudentComparators.BY_AVERAGE_SCORE_DESC;

        strategy.sort(input, comparator);

        assertIterableEquals(input, expected);
    }

    // Сравнение через номер группы
    @Test
    void groupByGroupNumber() {
        List<Student> input = new ArrayList<>();

        input.add(Student.builder().groupNumber(4).averageScore(3.45).recordBookNumber(15).build());
        input.add(Student.builder().groupNumber(2).averageScore(4.15).recordBookNumber(9).build());
        input.add(Student.builder().groupNumber(1).averageScore(4.01).recordBookNumber(11).build());


        List<Student> expected = List.of(
                Student.builder().groupNumber(1).averageScore(4.01).recordBookNumber(11).build(),
                Student.builder().groupNumber(2).averageScore(4.15).recordBookNumber(9).build(),
                Student.builder().groupNumber(4).averageScore(3.45).recordBookNumber(15).build()
        );

        Comparator<Student> comparator = StudentComparators.BY_GROUP_NUMBER;

        strategy.sort(input, comparator);

        assertIterableEquals(input, expected);
    }

    // Сравнение через номер книги
    @Test
    void groupByRecordBookNumber() {
        List<Student> input = new ArrayList<>();

        input.add(Student.builder().groupNumber(4).averageScore(3.45).recordBookNumber(15).build());
        input.add(Student.builder().groupNumber(2).averageScore(4.15).recordBookNumber(9).build());
        input.add(Student.builder().groupNumber(1).averageScore(4.01).recordBookNumber(11).build());
        input.add(Student.builder().groupNumber(3).averageScore(4.44).recordBookNumber(23).build());


        List<Student> expected = List.of(
                Student.builder().groupNumber(2).averageScore(4.15).recordBookNumber(9).build(),
                Student.builder().groupNumber(1).averageScore(4.01).recordBookNumber(11).build(),
                Student.builder().groupNumber(4).averageScore(3.45).recordBookNumber(15).build(),
                Student.builder().groupNumber(3).averageScore(4.44).recordBookNumber(23).build()

        );

        Comparator<Student> comparator = StudentComparators.BY_RECORD_BOOK_NUMBER;

        strategy.sort(input, comparator);

        assertIterableEquals(input, expected);
    }

}
