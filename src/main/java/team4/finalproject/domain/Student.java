package team4.finalproject.domain;

import java.util.Objects;

public class Student implements Comparable<Student> {
    private final int groupNumber;
    private final double averageScore;
    private final int recordBookNumber;

    public Student(int  groupNumber, double averageScore, int recordBookNumber) {
        this.groupNumber = groupNumber;
        this.averageScore = averageScore;
        this.recordBookNumber = recordBookNumber;
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.recordBookNumber, other.recordBookNumber);
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public int getRecordBookNumber() {
        return recordBookNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "groupNumber=" + groupNumber +
                ", averageScore=" + averageScore +
                ", recordBookNumber=" + recordBookNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return recordBookNumber == student.recordBookNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordBookNumber);
    }


}

