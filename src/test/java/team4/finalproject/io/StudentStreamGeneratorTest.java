package team4.finalproject.io;

import org.junit.jupiter.api.Test;
import team4.finalproject.domain.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentStreamGeneratorTest {

    private final StudentStreamGenerator generator = new StudentStreamGenerator();

    @Test
    void generateRandomReturnsListWithRequestedSize() {
        int size = 15;

        List<Student> studentTests = generator.generateRandom(size);

        assertEquals(size, studentTests.size());
    }


    @Test
    void generateRandomCreatesStudentsWithValidFieldRanges() {
        List<Student> studentTests = generator.generateRandom(100);

        assertTrue(studentTests.stream().allMatch(this::hasValidFields));
    }

    @Test
    void generateRandomThrowsExceptionForNegativeSize() {
        assertThrows(IllegalArgumentException.class, () -> generator.generateRandom(-1));
    }

    @Test
    void generateRandomThrowsExceptionForZeroSize() {
        assertThrows(IllegalArgumentException.class, () -> generator.generateRandom(0));
    }

    private boolean hasValidFields(Student studentTest) {
        return studentTest.getGroupNumber() >= 1
                && studentTest.getGroupNumber() <= 10
                && studentTest.getAverageScore() >= 0.0
                && studentTest.getAverageScore() <= 5.0
                && studentTest.getRecordBookNumber() >= 100000
                && studentTest.getRecordBookNumber() <= 999999;
    }
}
