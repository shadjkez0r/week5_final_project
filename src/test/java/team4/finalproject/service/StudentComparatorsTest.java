package team4.finalproject.service;

import org.junit.jupiter.api.Test;
import team4.finalproject.domain.Student;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentComparatorsTest {
    private final List<Student> students = List.of(
            Student.builder().groupNumber(102).averageScore(4.2).recordBookNumber(1003).build(),
            Student.builder().groupNumber(101).averageScore(4.8).recordBookNumber(1001).build(),
            Student.builder().groupNumber(103).averageScore(3.9).recordBookNumber(1002).build(),
            Student.builder().groupNumber(101).averageScore(4.5).recordBookNumber(1004).build()
    );

    @Test
    void byGroupNumberComparatorShouldSortAscending() {
        List<Student> sorted = new ArrayList<>(students);
        sorted.sort(StudentComparators.BY_GROUP_NUMBER);

        assertEquals(101, sorted.get(0).getGroupNumber());
        assertEquals(101, sorted.get(1).getGroupNumber());
        assertEquals(102, sorted.get(2).getGroupNumber());
        assertEquals(103, sorted.get(3).getGroupNumber());
    }

    @Test
    void byAverageScoreDescComparatorShouldSortDescending() {
        List<Student> sorted = new ArrayList<>(students);
        sorted.sort(StudentComparators.BY_AVERAGE_SCORE_DESC);

        assertEquals(4.8, sorted.get(0).getAverageScore());
        assertEquals(4.5, sorted.get(1).getAverageScore());
        assertEquals(4.2, sorted.get(2).getAverageScore());
        assertEquals(3.9, sorted.get(3).getAverageScore());
    }

    @Test
    void byRecordBookNumberComparatorShouldSortAscending() {
        List<Student> sorted = new ArrayList<>(students);
        sorted.sort(StudentComparators.BY_RECORD_BOOK_NUMBER);

        assertEquals(1001, sorted.get(0).getRecordBookNumber());
        assertEquals(1002, sorted.get(1).getRecordBookNumber());
        assertEquals(1003, sorted.get(2).getRecordBookNumber());
        assertEquals(1004, sorted.get(3).getRecordBookNumber());
    }
}
