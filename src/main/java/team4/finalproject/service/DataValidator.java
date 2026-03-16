package team4.finalproject.service;

import team4.finalproject.domain.Student;

public class DataValidator {
    private DataValidator() {}

    public static boolean isValidGroupNumber(int groupNumber) {
        return true;
    }

    public static boolean isValidAverageScore(double averageScore) {
        return true;
    }

    public static boolean isValidRecordBookNumber(int recordBookNumber) {
        return true;
    }

    public static boolean isValidLine(String line) {
        return true;
    }

    public static Student parseStudent(String line) {
        return new Student(1, 4.3, 1001);
    }
}
