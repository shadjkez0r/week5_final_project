package team4.finalproject.service;

import team4.finalproject.domain.Student;

public class DataValidator {

    private DataValidator() {}

    public static boolean isValidGroupNumber(int groupNumber) {
        return groupNumber > 0;
    }

    public static boolean isValidAverageScore(double averageScore) {
        return averageScore > 0 && averageScore <= 5;
    }

    public static boolean isValidRecordBookNumber(int recordBookNumber) {
        return recordBookNumber > 0;
    }

    public static boolean isValidLine(String line) {

        String[] box = line.trim().split(",");
        int groupNumber = Integer.parseInt(box[0].trim());
        double averageScore = Double.parseDouble(box[1].trim());
        int recordBookNumber = Integer.parseInt(box[2].trim());

        return  !line.trim().isEmpty() &&
                isValidGroupNumber(groupNumber) &&
                isValidAverageScore(averageScore) &&
                isValidRecordBookNumber(recordBookNumber);
    }

    public static Student parseStudent(String line) {

        String[] box = line.trim().split(",");
        int groupNumber = Integer.parseInt(box[0].trim());
        double averageScore = Double.parseDouble(box[1].trim());
        int recordBookNumber = Integer.parseInt(box[2].trim());

        return new Student(groupNumber, averageScore, recordBookNumber);
    }

}
