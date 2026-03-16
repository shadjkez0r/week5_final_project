package team4.finalproject.service;

import team4.finalproject.domain.Student;

import java.util.Comparator;

public class StudentComparators {
    private StudentComparators() {}

    public static final Comparator<Student> BY_GROUP_NUMBER = new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            return 0;
        }
    };

    public static final Comparator<Student> BY_AVERAGE_SCORE_DESC = new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            return 0;
        }
    };

    public static final Comparator<Student> BY_RECORD_BOOK_NUMBER = new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            return 0;
        }
    };

}
