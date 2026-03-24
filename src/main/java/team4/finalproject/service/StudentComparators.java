package team4.finalproject.service;

import team4.finalproject.domain.Student;

import java.util.Comparator;

public final class StudentComparators {
    private StudentComparators() {
    }

    public static final Comparator<Student> BY_GROUP_NUMBER =
            Comparator.comparingInt(Student::getGroupNumber);

    public static final Comparator<Student> BY_AVERAGE_SCORE_DESC =
            Comparator.comparingDouble(Student::getAverageScore).reversed();

    public static final Comparator<Student> BY_RECORD_BOOK_NUMBER =
            Comparator.comparingInt(Student::getRecordBookNumber);
}