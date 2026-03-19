package team4.finalproject.domain;

import java.util.Objects;

public class Student {
    private final int groupNumber;
    private final double averageScore;
    private final int recordBookNumber;

    private Student(Builder builder) {
        this.groupNumber = builder.groupNumber;
        this.averageScore = builder.averageScore;
        this.recordBookNumber = builder.recordBookNumber;
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

    @Override
    public String toString() {
        return "Student{" +
                "groupNumber=" + groupNumber +
                ", averageScore=" + averageScore +
                ", recordBookNumber=" + recordBookNumber +
                '}';
    }

    public static class Builder {
        private int groupNumber;
        private double averageScore;
        private int recordBookNumber;

        public Builder groupNumber(int groupNumber) {
            this.groupNumber = groupNumber;
            return this;
        }

        public Builder averageScore(double averageScore) {
            this.averageScore = averageScore;
            return this;
        }

        public Builder recordBookNumber(int recordBookNumber) {
            this.recordBookNumber = recordBookNumber;
            return this;
        }

        public Student build() {
            validate();
            return new Student(this);
        }

        private void validate() {
            if (groupNumber <= 0) {
                throw new IllegalArgumentException("Group number must be positive");
            }
            if (averageScore < 2.0 || averageScore > 5.0) {
                throw new IllegalArgumentException("Average score must be between 2.0 and 5.0");
            }
            if (recordBookNumber <= 0) {
                throw new IllegalArgumentException("Record book number must be positive");
            }
        }
    }
}