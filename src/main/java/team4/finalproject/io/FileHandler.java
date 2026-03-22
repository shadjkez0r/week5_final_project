package team4.finalproject.io;

import team4.finalproject.domain.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileHandler {
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public List<Student> readFromFile(String inputPath) {
        List<Student> students = new ArrayList<>();
        Path path = Path.of(inputPath);

        if (!Files.exists(path)) {
            System.out.println("Input file does not exist: " + inputPath);
            return students;
        }

        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> {
                if (line == null || line.trim().isEmpty() || line.trim().startsWith("#")) {
                    return;
                }

                String[] parts = line.trim().split(",");
                if (parts.length != 3) {
                    System.out.println("Format line error, need 3 values, we got: " + line);
                    return;
                }

                try {
                    Student student = Student.builder()
                            .groupNumber(Integer.parseInt(parts[0].trim()))
                            .averageScore(Double.parseDouble(parts[1].trim()))
                            .recordBookNumber(Integer.parseInt(parts[2].trim()))
                            .build();
                    students.add(student);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid data at line: " + line + " " + e.getMessage());
                }


            });

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        System.out.println("Successfully read student data: " + students.size());
        return students;
    }

    public void writeToFile(List<Student> students, String outputPath) {
        Path path = Path.of(outputPath);
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);

        List<String> studentLines = students.stream()
                .map(student -> student.getGroupNumber() + ","
                        + student.getAverageScore() + ","
                        + student.getRecordBookNumber())
                .toList();

        List<String> finalLines = new ArrayList<>();
        finalLines.add("=== Sorted Collection - " + timestamp + " ===");
        finalLines.addAll(studentLines);
        finalLines.add("=== End (" + students.size() + " records) ===");
        finalLines.add("");

        try {
            Files.write(path, finalLines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Successfully wrote student data: " + (finalLines.size()-3));
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }


    }
}
