package team4.finalproject.io;

import team4.finalproject.domain.Student;

import java.util.List;

public class FileHandler {
    private final String inputFilePath;
    private final String outputFilePath;

    public FileHandler(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    public List<Student> readFromFile() {
        return List.of(
                new Student(1, 4, 1001),
                new Student(1, 3.7, 1002),
                new Student(2, 3.5, 1003)
        );
    }
}
