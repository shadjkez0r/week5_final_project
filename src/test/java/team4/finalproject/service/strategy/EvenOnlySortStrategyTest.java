package team4.finalproject.service.strategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import team4.finalproject.domain.Student;
import team4.finalproject.service.StudentComparators;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EvenOnlySortStrategyTest {
    private final BubbleSortStrategy<Student> bubbleSortStrategy = new BubbleSortStrategy();
    private final InsertionSortStrategy<Student> insertionSortStrategy = new InsertionSortStrategy();

    @Test
    @DisplayName("Сравнение по зачетным книжкам. Сортировка пузырьком. " +
            "Четные зачетки сортируются, нечетные остаются на своих местах")
    void recordBookNumber_bubbleSort_oddShouldStayInPlace() {
        // [3, 8, 5, 2, 6] → чётные (8,2,6) → (2,6,8) → [3, 2, 5, 6, 8]
        List<Student> list = makeStudentsByRecordBookNumber(3, 8, 5, 2, 6);

        new EvenOnlySortStrategy(bubbleSortStrategy, Student::getRecordBookNumber)
                .sort(list, StudentComparators.BY_RECORD_BOOK_NUMBER);

        assertThat(list).extracting(Student::getRecordBookNumber).containsExactly(3, 2, 5, 6, 8);

    }

    @Test
    @DisplayName("Сравнение по зачетным книжкам. Сортировка вставками. " +
            "Четные зачетки сортируются, нечетные остаются на своих местах")
    void recordBookNumber_insertionSort_oddShouldStayInPlace() {
        // [3, 8, 5, 2, 6] → чётные (8,2,6) → (2,6,8) → [3, 2, 5, 6, 8]
        List<Student> list = makeStudentsByRecordBookNumber(3, 8, 5, 2, 6);

        new EvenOnlySortStrategy(insertionSortStrategy, Student::getRecordBookNumber)
                .sort(list, StudentComparators.BY_RECORD_BOOK_NUMBER);

        assertThat(list).extracting(Student::getRecordBookNumber).containsExactly(3, 2, 5, 6, 8);
    }

    @Test
    @DisplayName("Сравнение по зачетным книжкам. Сортировка пузырьком. " +
            "Если все элементы четные, то вся коллекция будет отсортирована")
    void recordBookNumber_bubbleSort_allEven_entireListSorted() {
        List<Student> list = makeStudentsByRecordBookNumber(8, 2, 6, 4);
        new EvenOnlySortStrategy(bubbleSortStrategy, Student::getRecordBookNumber)
                .sort(list, StudentComparators.BY_RECORD_BOOK_NUMBER);

        assertThat(list).extracting(Student::getRecordBookNumber).containsExactly(2, 4, 6, 8);

    }

    @Test
    @DisplayName("Сравнение по зачетным книжкам. Сортировка вставками." +
            " Если все элементы четные, то вся коллекция будет отсортирована")
    void recordBookNumber_insertionSort_allEven_entireListSorted() {
        List<Student> list = makeStudentsByRecordBookNumber(8, 2, 6, 4);
        new EvenOnlySortStrategy(insertionSortStrategy, Student::getRecordBookNumber)
                .sort(list, StudentComparators.BY_RECORD_BOOK_NUMBER);

        assertThat(list).extracting(Student::getRecordBookNumber).containsExactly(2, 4, 6, 8);

    }

    @Test
    @DisplayName("Сравнение по зачетным книжкам. Сортировка пузырьком." +
            " Все элементы нечетные - порядок не меняется")
    void recordBookNumber_bubbleSort_allOdd_orderUnchanged() {
        List<Student> list = makeStudentsByRecordBookNumber(7, 3, 9, 1);

        new EvenOnlySortStrategy(bubbleSortStrategy, Student::getRecordBookNumber)
                .sort(list, StudentComparators.BY_RECORD_BOOK_NUMBER);

        assertThat(list).extracting(Student::getRecordBookNumber).containsExactly(7, 3, 9, 1);
    }

    @Test
    @DisplayName("Сравнение по зачетным книжкам. Сортировка вставками." +
            " Все элементы нечетные - порядок не меняется")
    void recordBookNumber_insertionSort_allOdd_orderUnchanged() {
        List<Student> list = makeStudentsByRecordBookNumber(7, 3, 9, 1);

        new EvenOnlySortStrategy(insertionSortStrategy, Student::getRecordBookNumber)
                .sort(list, StudentComparators.BY_RECORD_BOOK_NUMBER);

        assertThat(list).extracting(Student::getRecordBookNumber).containsExactly(7, 3, 9, 1);
    }

    @Test
    @DisplayName("Сравнение по зачетным книжкам. Сортировка пузырьком. Один элемент - не падает")
    void recordBookNumber_bubbleSort_singleElement() {
        List<Student> list = makeStudentsByRecordBookNumber(4);
        new EvenOnlySortStrategy(bubbleSortStrategy, Student::getRecordBookNumber)
                .sort(list, StudentComparators.BY_RECORD_BOOK_NUMBER);

        assertThat(list).extracting(Student::getRecordBookNumber).containsExactly(4);
    }

    @Test
    @DisplayName("Сравнение по зачетным книжкам. Сортировка вставками. Один элемент - не падает")
    void recordBookNumber_insertionSort_singleElement() {
        List<Student> list = makeStudentsByRecordBookNumber(4);
        new EvenOnlySortStrategy(insertionSortStrategy, Student::getRecordBookNumber)
                .sort(list, StudentComparators.BY_RECORD_BOOK_NUMBER);

        assertThat(list).extracting(Student::getRecordBookNumber).containsExactly(4);
    }

    @Test
    @DisplayName("Сравнение по зачетным книжкам. Сортировка пузырьком. Пустой список - не падает")
    void recordBookNumber_bubbleSort_emptyList() {
        List<Student> list = new ArrayList<>();

        new EvenOnlySortStrategy(bubbleSortStrategy, Student::getRecordBookNumber)
                .sort(list, StudentComparators.BY_RECORD_BOOK_NUMBER);

        assertThat(list).isEmpty();
    }

    @Test
    @DisplayName("Сравнение по зачетным книжкам. Сортировка вставками. Пустой список - не падает")
    void recordBookNumber_insertionSort_emptyList() {
        List<Student> list = new ArrayList<>();

        new EvenOnlySortStrategy(insertionSortStrategy, Student::getRecordBookNumber)
                .sort(list, StudentComparators.BY_RECORD_BOOK_NUMBER);

        assertThat(list).isEmpty();
    }

    @Test
    @DisplayName("Сравнение по зачетным книжкам. Сортировка пузырьком. Четные отсортированы корректно")
    void recordBookNumber_bubbleSort_worksWithBubbleSort() {
        List<Student> list = makeStudentsByRecordBookNumber(1, 10, 3, 4, 5, 2);
        new EvenOnlySortStrategy(bubbleSortStrategy, Student::getRecordBookNumber)
                .sort(list, StudentComparators.BY_RECORD_BOOK_NUMBER);

        assertThat(list).extracting(Student::getRecordBookNumber).containsExactly(1, 2, 3, 4, 5, 10);
    }

    @Test
    @DisplayName("Сравнение по зачетным книжкам. Сортировка вставками. Четные отсортированы корректно")
    void recordBookNumber_insertionSort_worksWithBubbleSort() {
        List<Student> list = makeStudentsByRecordBookNumber(1, 10, 3, 4, 5, 2);
        new EvenOnlySortStrategy(insertionSortStrategy, Student::getRecordBookNumber)
                .sort(list, StudentComparators.BY_RECORD_BOOK_NUMBER);

        assertThat(list).extracting(Student::getRecordBookNumber).containsExactly(1, 2, 3, 4, 5, 10);
    }

    @Test
    @DisplayName("Сравнение по номеру группы. Сортировка пузырьком. " +
            "Четные группы сортируются, нечетные остаются на своих местах")
    void groupNumber_bubbleSort_oddShouldStayInPlace() {
        // [5, 4, 3, 2, 1] → чётные (4,2) → (2,4) → [5, 2, 3, 4, 1]
        List<Student> list = makeStudentsByGroupNumber(5, 4, 3, 2, 1);

        new EvenOnlySortStrategy(bubbleSortStrategy, Student::getGroupNumber)
                .sort(list, StudentComparators.BY_GROUP_NUMBER);

        assertThat(list).extracting(Student::getGroupNumber).containsExactly(5, 2, 3, 4, 1);
    }

    @Test
    @DisplayName("Сравнение по номеру группы. Сортировка вставками. " +
            "Четные группы сортируются, нечетные остаются на своих местах")
    void groupNumber_insertionSort_oddShouldStayInPlace() {
        // [5, 4, 3, 2, 1] → чётные (4,2) → (2,4) → [5, 2, 3, 4, 1]
        List<Student> list = makeStudentsByGroupNumber(5, 4, 3, 2, 1);

        new EvenOnlySortStrategy(insertionSortStrategy, Student::getGroupNumber)
                .sort(list, StudentComparators.BY_GROUP_NUMBER);

        assertThat(list).extracting(Student::getGroupNumber).containsExactly(5, 2, 3, 4, 1);
    }

    @Test
    @DisplayName("Сравнение по номеру группы. Сортировка пузырьком. " +
            "Если все элементы четные, то вся коллекция будет отсортирована")
    void groupNumber_bubbleSort_allEven_entireListSorted() {
        List<Student> list = makeStudentsByGroupNumber(8, 2, 6, 4);

        new EvenOnlySortStrategy(bubbleSortStrategy, Student::getGroupNumber)
                .sort(list, StudentComparators.BY_GROUP_NUMBER);

        assertThat(list).extracting(Student::getGroupNumber).containsExactly(2, 4, 6, 8);
    }

    @Test
    @DisplayName("Сравнение по номеру группы. Сортировка вставками. " +
            "Если все элементы четные, то вся коллекция будет отсортирована")
    void groupNumber_insertionSort_allEven_entireListSorted() {
        List<Student> list = makeStudentsByGroupNumber(8, 2, 6, 4);

        new EvenOnlySortStrategy(insertionSortStrategy, Student::getGroupNumber)
                .sort(list, StudentComparators.BY_GROUP_NUMBER);

        assertThat(list).extracting(Student::getGroupNumber).containsExactly(2, 4, 6, 8);
    }

    @Test
    @DisplayName("Сравнение по номеру группы. Сортировка пузырьком. " +
            "Все элементы нечетные - порядок не меняется")
    void groupNumber_bubbleSort_allOdd_orderUnchanged() {
        List<Student> list = makeStudentsByGroupNumber(7, 3, 9, 1);

        new EvenOnlySortStrategy(bubbleSortStrategy, Student::getGroupNumber)
                .sort(list, StudentComparators.BY_GROUP_NUMBER);

        assertThat(list).extracting(Student::getGroupNumber).containsExactly(7, 3, 9, 1);
    }

    @Test
    @DisplayName("Сравнение по номеру группы. Сортировка вставками. " +
            "Все элементы нечетные - порядок не меняется")
    void groupNumber_insertionSort_allOdd_orderUnchanged() {
        List<Student> list = makeStudentsByGroupNumber(7, 3, 9, 1);

        new EvenOnlySortStrategy(insertionSortStrategy, Student::getGroupNumber)
                .sort(list, StudentComparators.BY_GROUP_NUMBER);

        assertThat(list).extracting(Student::getGroupNumber).containsExactly(7, 3, 9, 1);
    }

    @Test
    @DisplayName("Сравнение по номеру группы. Сортировка пузырьком. Один элемент - не падает")
    void groupNumber_bubbleSort_singleElement() {
        List<Student> list = makeStudentsByGroupNumber(4);

        new EvenOnlySortStrategy(bubbleSortStrategy, Student::getGroupNumber)
                .sort(list, StudentComparators.BY_GROUP_NUMBER);

        assertThat(list).extracting(Student::getGroupNumber).containsExactly(4);
    }

    @Test
    @DisplayName("Сравнение по номеру группы. Сортировка вставками. Один элемент - не падает")
    void groupNumber_insertionSort_singleElement() {
        List<Student> list = makeStudentsByGroupNumber(4);

        new EvenOnlySortStrategy(insertionSortStrategy, Student::getGroupNumber)
                .sort(list, StudentComparators.BY_GROUP_NUMBER);

        assertThat(list).extracting(Student::getGroupNumber).containsExactly(4);
    }

    @Test
    @DisplayName("Сравнение по номеру группы. Сортировка пузырьком. Пустой список - не падает")
    void groupNumber_bubbleSort_emptyList() {
        List<Student> list = new ArrayList<>();

        new EvenOnlySortStrategy(bubbleSortStrategy, Student::getGroupNumber)
                .sort(list, StudentComparators.BY_GROUP_NUMBER);

        assertThat(list).isEmpty();
    }

    @Test
    @DisplayName("Сравнение по номеру группы. Сортировка вставками. Пустой список - не падает")
    void groupNumber_insertionSort_emptyList() {
        List<Student> list = new ArrayList<>();

        new EvenOnlySortStrategy(insertionSortStrategy, Student::getGroupNumber)
                .sort(list, StudentComparators.BY_GROUP_NUMBER);

        assertThat(list).isEmpty();
    }

    @Test
    @DisplayName("Сравнение по номеру группы. Сортировка пузырьком. Четные отсортированы корректно")
    void groupNumber_bubbleSort_evenSortedCorrectly() {
        List<Student> list = makeStudentsByGroupNumber(1, 10, 3, 4, 5, 2);

        new EvenOnlySortStrategy(bubbleSortStrategy, Student::getGroupNumber)
                .sort(list, StudentComparators.BY_GROUP_NUMBER);

        assertThat(list).extracting(Student::getGroupNumber).containsExactly(1, 2, 3, 4, 5, 10);
    }

    @Test
    @DisplayName("Сравнение по номеру группы. Сортировка вставками. Четные отсортированы корректно")
    void groupNumber_insertionSort_evenSortedCorrectly() {
        List<Student> list = makeStudentsByGroupNumber(1, 10, 3, 4, 5, 2);

        new EvenOnlySortStrategy(insertionSortStrategy, Student::getGroupNumber)
                .sort(list, StudentComparators.BY_GROUP_NUMBER);

        assertThat(list).extracting(Student::getGroupNumber).containsExactly(1, 2, 3, 4, 5, 10);
    }

    private List<Student> makeStudentsByRecordBookNumber(int... recordBookNumbers) {
        List<Student> list = new ArrayList<>();
        for (int rb : recordBookNumbers) {
            list.add(Student.builder()
                    .groupNumber(1)
                    .averageScore(3.0)
                    .recordBookNumber(rb)
                    .build());
        }
        return list;
    }

    private List<Student> makeStudentsByGroupNumber(int... groupNumbers) {
        int recordBook = 1;
        List<Student> list = new ArrayList<>();
        for (int group : groupNumbers) {
            list.add(Student.builder()
                    .groupNumber(group)
                    .averageScore(3.0)
                    .recordBookNumber(recordBook++)
                    .build());
        }
        return list;
    }
}