package team4.finalproject.io;

import java.util.List;

public interface DataGenerator<T> {
    List<T> generateRandom(int size);
}
