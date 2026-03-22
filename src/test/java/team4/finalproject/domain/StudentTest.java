package team4.finalproject.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    void builderShouldCreateStudentWithValidData() {
        Student student = Student.builder()
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
        Executable build = () -> Student.builder()
                .groupNumber(0)
                .averageScore(4.5)
                .recordBookNumber(12345)
                .build();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, build);
        assertEquals("Group number must be positive", exception.getMessage());
    }

    @Test
    void builderShouldThrowExceptionForInvalidAverageScoreLow() {
        Executable build = () -> Student.builder()
                .groupNumber(101)
                .averageScore(-1)
                .recordBookNumber(12345)
                .build();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, build);
        assertEquals("Average score must be between 0 and 5.0", exception.getMessage());
    }

    @Test
    void builderShouldThrowExceptionForInvalidAverageScoreHigh() {
        Executable build = () -> Student.builder()
                .groupNumber(101)
                .averageScore(5.1)
                .recordBookNumber(12345)
                .build();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, build);
        assertEquals("Average score must be between 0 and 5.0", exception.getMessage());
    }

    @Test
    void builderShouldThrowExceptionForInvalidRecordBookNumber() {
        Executable build = () -> Student.builder()
                .groupNumber(101)
                .averageScore(4.5)
                .recordBookNumber(-1)
                .build();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, build);
        assertEquals("Record book number must be positive", exception.getMessage());
    }
}


