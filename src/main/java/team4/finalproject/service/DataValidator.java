//package team4.finalproject.service;
//
//import team4.finalproject.domain.Student;
//
//public class DataValidator {
//    private DataValidator() {
//    }


//    public static boolean isValidGroupNumber(int groupNumber) {
//        return groupNumber >= 1 && groupNumber <= 1_000;
//    }
//
//
//    public static boolean isValidAverageScore(double averageScore) {
//        return averageScore >= 0.0 && averageScore <= 5.0;
//    }
//
//
//    public static boolean isValidRecordBookNumber(int recordBookNumber) {
//        return recordBookNumber > 0;
//    }

//    public static boolean isValidLine(String line) {
//        if (line == null || line.isEmpty() || line.trim().startsWith("#")) {
//            return false;
//        }
//
//        String[] parts = line.trim().split(",");
//        if (parts.length != 3) {
//            return false;
//        }
//
//        try {
//            int groupNumber = Integer.parseInt(parts[0].trim());
//            double averageScore = Double.parseDouble(parts[1].trim());
//            int recordBookNumber = Integer.parseInt(parts[2].trim());
//
//            Student.builder()
//                    .groupNumber(groupNumber)
//                    .averageScore(averageScore)
//                    .recordBookNumber(recordBookNumber)
//                    .build();
//
//            return true;
//        } catch (IllegalArgumentException e) {
//            return false;
//        }
//    }
//
//    public static Student parseStudent(String line) {
//
//    }
//
//
//}
