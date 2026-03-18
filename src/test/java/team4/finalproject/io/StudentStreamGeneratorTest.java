package team4.finalproject.io;

import org.junit.jupiter.api.Test;
import team4.finalproject.domain.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentStreamGeneratorTest {

    private final StudentStreamGenerator generator = new StudentStreamGenerator();

    @Test
    void generateRandomReturnsListWithRequestedSize() {
        int size = 15;

        List<Student> students = generator.generateRandom(size);

        assertEquals(size, students.size());
    }

    @Test
    void generateRandomReturnsEmptyListForZeroSize() {
        List<Student> students = generator.generateRandom(0);

        assertTrue(students.isEmpty());
    }

    @Test
    void generateRandomCreatesStudentsWithValidFieldRanges() {
        List<Student> students = generator.generateRandom(100);

        assertTrue(students.stream().allMatch(this::hasValidFields));
    }

    @Test
    void generateRandomThrowsExceptionForNegativeSize() {
        assertThrows(IllegalArgumentException.class, () -> generator.generateRandom(-1));
    }

    private boolean hasValidFields(Student student) {
        return student.getGroupNumber() >= 1
                && student.getGroupNumber() <= 10
                && student.getAverageScore() >= 2.0
                && student.getAverageScore() <= 5.0
                && student.getRecordBookNumber() >= 100000
                && student.getRecordBookNumber() <= 999999;
    }
}
