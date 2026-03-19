package team4.finalproject.io;

import org.junit.jupiter.api.Test;
import team4.finalproject.domain.Student;
import team4.finalproject.domain.StudentTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentTestStreamGeneratorTest {

    private final StudentStreamGenerator generator = new StudentStreamGenerator();

    @Test
    void generateRandomReturnsListWithRequestedSize() {
        int size = 15;

        List<Student> studentTests = generator.generateRandom(size);

        assertEquals(size, studentTests.size());
    }

    @Test
    void generateRandomReturnsEmptyListForZeroSize() {
        List<Student> studentTests = generator.generateRandom(0);

        assertTrue(studentTests.isEmpty());
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

    private boolean hasValidFields(Student studentTest) {
        return studentTest.getGroupNumber() >= 1
                && studentTest.getGroupNumber() <= 10
                && studentTest.getAverageScore() >= 2.0
                && studentTest.getAverageScore() <= 5.0
                && studentTest.getRecordBookNumber() >= 100000
                && studentTest.getRecordBookNumber() <= 999999;
    }
}
