package team4.finalproject.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import team4.finalproject.service.StudentComparators;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void builderShouldCreateStudentWithValidData() {
        Student student = new Student.Builder()
                .groupNumber(101)
                .averageScore(4.5)
                .recordBookNumber(12345)
                .build();

        assertEquals(101, student.getGroupNumber());
        assertEquals(4.5, student.getAverageScore());
        assertEquals(12345, student.getRecordBookNumber());
    }

    @Test
    void builderShouldThrowExceptionForInvalidGroupNumber() {
        Executable build = () -> new Student.Builder()
                .groupNumber(0)
                .averageScore(4.5)
                .recordBookNumber(12345)
                .build();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, build);
        assertEquals("Group number must be positive", exception.getMessage());
    }

    @Test
    void builderShouldThrowExceptionForInvalidAverageScoreLow() {
        Executable build = () -> new Student.Builder()
                .groupNumber(101)
                .averageScore(1.9)
                .recordBookNumber(12345)
                .build();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, build);
        assertEquals("Average score must be between 2.0 and 5.0", exception.getMessage());
    }

    @Test
    void builderShouldThrowExceptionForInvalidAverageScoreHigh() {
        Executable build = () -> new Student.Builder()
                .groupNumber(101)
                .averageScore(5.1)
                .recordBookNumber(12345)
                .build();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, build);
        assertEquals("Average score must be between 2.0 and 5.0", exception.getMessage());
    }

    @Test
    void builderShouldThrowExceptionForInvalidRecordBookNumber() {
        Executable build = () -> new Student.Builder()
                .groupNumber(101)
                .averageScore(4.5)
                .recordBookNumber(-1)
                .build();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, build);
        assertEquals("Record book number must be positive", exception.getMessage());
    }
}

class StudentComparatorsTest {

    private final List<Student> students = List.of(
            new Student.Builder().groupNumber(102).averageScore(4.2).recordBookNumber(1003).build(),
            new Student.Builder().groupNumber(101).averageScore(4.8).recordBookNumber(1001).build(),
            new Student.Builder().groupNumber(103).averageScore(3.9).recordBookNumber(1002).build(),
            new Student.Builder().groupNumber(101).averageScore(4.5).recordBookNumber(1004).build()
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
