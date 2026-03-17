package team4.finalproject.io;

import team4.finalproject.domain.Student;

import java.util.List;

public class StudentStreamGenerator implements DataGenerator<Student> {
    @Override
    public List<Student> generateRandom(int size) {
        return List.of(
                new Student(1, 3.3, 1003),
                new Student(1, 4.6, 1004),
                new Student(2, 4.9, 1005)
        );
    }
}
