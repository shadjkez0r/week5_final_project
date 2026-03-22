package team4.finalproject.io;

import team4.finalproject.domain.Student;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class StudentStreamGenerator implements DataGenerator<Student> {
    private static final int MIN_GROUP_NUMBER = 1;
    private static final int MAX_GROUP_NUMBER = 1_000;
    private static final double MIN_AVERAGE_SCORE = 0;
    private static final double MAX_AVERAGE_SCORE = 5.0;
    private static final int MIN_RECORD_BOOK_NUMBER = 100_000;
    private static final int MAX_RECORD_BOOK_NUMBER = 999_999;

    @Override
    public List<Student> generateRandom(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Size must be >= 1, got: " + size);
        }

        return IntStream.range(0, size)
                .mapToObj(index -> Student.builder()
                        .averageScore(randomAverageScore())
                        .recordBookNumber(randomRecordBookNumber())
                        .groupNumber(randomGroupNumber())
                        .build()

                )
                .toList();
    }

    private int randomGroupNumber() {
        return ThreadLocalRandom.current().nextInt(MIN_GROUP_NUMBER, MAX_GROUP_NUMBER + 1);
    }

    private double randomAverageScore() {
        double value = ThreadLocalRandom.current().nextDouble(MIN_AVERAGE_SCORE, MAX_AVERAGE_SCORE + 0.01);
        return Math.round(value * 100.0) / 100.0;
    }

    private int randomRecordBookNumber() {
        return ThreadLocalRandom.current().nextInt(MIN_RECORD_BOOK_NUMBER, MAX_RECORD_BOOK_NUMBER + 1);
    }
}
