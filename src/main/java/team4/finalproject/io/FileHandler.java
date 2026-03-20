package team4.finalproject.io;

import team4.finalproject.domain.Student;
import team4.finalproject.service.DataValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private final String inputFilePath;
    private final String outputFilePath;

    public FileHandler(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }



    public List<Student> readFromFile() {
        List<Student> students = new ArrayList<>();

        try {BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            String line = reader.readLine();
            while(line != null) {
                if (DataValidator.isValidLine(line)) {
                    students.add(DataValidator.parseStudent(line));
                } else {
                    System.out.println("Ошибка в строке");
                }
                line = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("File is not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public void writeToFile(List<Student> students) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
            students.forEach(x -> {
                try {
                    System.out.println(x);
                    writer.write(x.toString() + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
