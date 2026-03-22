package team4.finalproject.io;

import org.junit.jupiter.api.Test;
import team4.finalproject.domain.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {
    private final String testInputPath = "src/test/resources/test_data.txt";
    private final String testOutputPath = "src/test/resources/test_output.txt";

    @Test
    void readFromFile_ReadsValidDataAndIgnoresComments() {
        FileHandler fileHandler = new FileHandler();

        List<Student> students = fileHandler.readFromFile(testInputPath);

        assertEquals(5, students.size(), "Должно быть ровно 5 студентов");
        System.out.println("students: " + students);
    }

    @Test
    void readFromFile_ReturnsEmptyListIfFileDoesNotExist() {
        FileHandler fileHandler = new FileHandler();
        String inputPath = "src/test/resources/fake.txt";
        List<Student> students = fileHandler.readFromFile(inputPath);

        assertNotNull(students);
        assertTrue(students.isEmpty());
    }

    @Test
    void writeToFile_AppendsDataCorrectly() {
        FileHandler fileHandler = new FileHandler();

        List<Student> studentsToWrite = List.of(
                Student.builder().groupNumber(1).averageScore(4.5).recordBookNumber(100).build(),
                Student.builder().groupNumber(1).averageScore(4.9).recordBookNumber(101).build(),
                Student.builder().groupNumber(1).averageScore(5.0).recordBookNumber(102).build()
        );

        fileHandler.writeToFile(studentsToWrite, testOutputPath);

        assertTrue(Files.exists(Path.of(testOutputPath)), "Файл должен быть создан");

        List<String> writtenLines;
        try {
            writtenLines = Files.readAllLines(Path.of(testOutputPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertTrue(writtenLines.get(0).startsWith("=== Sorted Collection"));
        assertEquals("1,4.5,100", writtenLines.get(1));
        assertEquals("1,4.9,101", writtenLines.get(2));
        assertEquals("1,5.0,102", writtenLines.get(3));
        assertTrue(writtenLines.get(4).startsWith("=== End"));


    }
}
