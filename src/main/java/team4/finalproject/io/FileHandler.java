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
                 Student.builder().groupNumber(1).averageScore(4).recordBookNumber(1).build(),
                 Student.builder().groupNumber(1).averageScore(4).recordBookNumber(2).build(),
                 Student.builder().groupNumber(1).averageScore(4).recordBookNumber(3).build()
        );
    }
}
