package team4.finalproject.io;
import org.junit.jupiter.api.Test;
import team4.finalproject.domain.Student;
import java.util.List;

public class FileHandlerTest {

    @Test
    void readFromFileTest() {
        studentsTest = fileHandler.readFromFile(); // читаем из файла
    }

    @Test
    void writeToFileTest() {
        fileHandler.writeToFile(studentsTest); // пишем в файл
    }


    private final FileHandler fileHandler = new FileHandler("src/test/resources/students from file", "src/test/resources/students from file(output)");

    List<Student> studentsTest;

}