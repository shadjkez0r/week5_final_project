package team4.finalproject.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import team4.finalproject.collection.CustomList;
import team4.finalproject.domain.Student;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OccurrenceCounterTest {
    private final OccurrenceCounter counter = new OccurrenceCounter();

    @Test
    @DisplayName("Подсчет по номеру группы - находит все совпадения")
    void countByGroupNumber_findAll() {
        CustomList<Student> list = new CustomList<>();
        list.add(makeStudent(1, 3.0, 100));
        list.add(makeStudent(2, 4.0, 200));
        list.add(makeStudent(1, 5.0, 300));
        list.add(makeStudent(3, 3.5, 400));
        list.add(makeStudent(1, 4.5, 500));

        long result = counter.count(list, s -> s.getGroupNumber() == 1);
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("Подсчет по номеру зачетки - находит все совпадения")
    void countByRecordBookNumber_findAll() {
        CustomList<Student> list = new CustomList<>();
        list.add(makeStudent(1, 3.0, 100));
        list.add(makeStudent(2, 4.0, 100));
        list.add(makeStudent(1, 5.0, 300));
        list.add(makeStudent(3, 3.5, 400));
        list.add(makeStudent(1, 4.5, 500));

        long result = counter.count(list, s -> s.getRecordBookNumber() == 100);
        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("Подсчет по среднему балу - находит все совпадения")
    void countByAverageScore_findAll() {
        CustomList<Student> list = new CustomList<>();
        list.add(makeStudent(1, 3.0, 100));
        list.add(makeStudent(2, 4.0, 100));
        list.add(makeStudent(1, 3.0, 300));
        list.add(makeStudent(3, 3.5, 400));
        list.add(makeStudent(1, 4.5, 500));

        long result = counter.count(list, s -> s.getAverageScore() == 3.0);
        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("Подсчет по номеру группы - нет совпадений")
    void countByGroupNumber_noMatches() {
        CustomList<Student> list = new CustomList<>();
        list.add(makeStudent(1, 3.0, 100));
        list.add(makeStudent(2, 4.0, 200));

        long result = counter.count(list, s -> s.getGroupNumber() == 99);
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("Подсчет по номеру зачетки - нет совпадений")
    void countByRecordBookNumber_noMatches() {
        CustomList<Student> list = new CustomList<>();
        list.add(makeStudent(1, 3.0, 100));
        list.add(makeStudent(2, 4.0, 200));

        long result = counter.count(list, s -> s.getRecordBookNumber() == 99);
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("Подсчет по среднему баллу - нет совпадений")
    void countByAverageScore_noMatches() {
        CustomList<Student> list = new CustomList<>();
        list.add(makeStudent(1, 3.0, 100));
        list.add(makeStudent(2, 4.0, 200));

        long result = counter.count(list, s -> s.getAverageScore() == 5.0);
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("Подсчет по номеру группы - находит единственный")
    void countByGroupNumber_findsOne() {
        CustomList<Student> list = new CustomList<>();
        list.add(makeStudent(1, 3.0, 100));
        list.add(makeStudent(2, 3.0, 200));
        list.add(makeStudent(3, 3.0, 300));

        long result = counter.count(list, s -> s.getGroupNumber() == 3);
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Подсчет по среднему баллу - находит единственный")
    void countByAverageScore_findsOne() {
        CustomList<Student> list = new CustomList<>();
        list.add(makeStudent(1, 3.0, 100));
        list.add(makeStudent(2, 4.0, 200));
        list.add(makeStudent(3, 5.0, 300));

        long result = counter.count(list, s -> s.getAverageScore() == 3.0);
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Подсчет по номеру зачетки - находит единственный")
    void countByRecordBookNumber_findsOne() {
        CustomList<Student> list = new CustomList<>();
        list.add(makeStudent(1, 3.0, 100));
        list.add(makeStudent(2, 3.0, 200));
        list.add(makeStudent(3, 3.0, 300));

        long result = counter.count(list, s -> s.getRecordBookNumber() == 300);
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Пустая коллекция - возвращает 0")
    void emptyCollection_returnsZero() {
        CustomList<Student> list = new CustomList<>();
        long result = counter.count(list, s -> s.getRecordBookNumber() == 0);
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("Все элементы совпадают")
    void allMatch() {
        CustomList<Student> list = new CustomList<>();
        list.add(makeStudent(5, 3.0, 100));
        list.add(makeStudent(5, 3.0, 200));
        list.add(makeStudent(5, 3.0, 300));

        long result = counter.count(list, s -> s.getGroupNumber() == 5);
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("Один элемент - совпадает")
    void singleElement_match() {
        CustomList<Student> list = new CustomList<>();
        list.add(makeStudent(5, 3.0, 100));

        long result = counter.count(list, s -> s.getGroupNumber() == 5);
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Большая коллекция - parallelStream() корректно считает")
    void largeCollectionParallelCount_correct() {
        CustomList<Student> list = new CustomList<>(10_000);

        // добавляем 10_000 студентов, группы по 1-10 по кругу
        for (int i = 0; i < 10_000; i++) {
            list.add(makeStudent((i % 10) + 1, 3.0, i + 1));
        }

        // группа 1 -> индексы 0, 10, 20, ... = 1_000 штук
        long result = counter.count(list, s -> s.getGroupNumber() == 10);
        assertThat(result).isEqualTo(1_000);
    }

    private Student makeStudent(int group, double avgScore, int recordBook) {
        return Student.builder()
                .groupNumber(group)
                .averageScore(avgScore)
                .recordBookNumber(recordBook)
                .build();
    }


}