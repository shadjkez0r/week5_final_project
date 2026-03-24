package team4.finalproject.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CustomListTest {
    @Test
    @DisplayName("add и get - элементы добавляются и читаются корректно")
    void addAndGet() {
        CustomList<Student> collection = new CustomList<>();
        Student s1 = makeStudent(1, 4.0, 100);
        Student s2 = makeStudent(2, 3.5, 200);

        collection.add(s1);
        collection.add(s2);

        assertThat(collection.size()).isEqualTo(2);
        assertThat(collection.get(0)).isEqualTo(s1);
        assertThat(collection.get(1)).isEqualTo(s2);
    }

    @Test
    @DisplayName("set - элемент заменяется по индексу")
    void set() {
        CustomList<Student> collection = new CustomList<>();
        Student original = makeStudent(1, 4.0, 100);
        Student replacement = makeStudent(2, 3.0, 200);

        collection.add(original);
        collection.set(0, replacement);

        assertThat(collection.get(0)).isEqualTo(replacement);
    }

    @Test
    @DisplayName("isEmpty - true для пустой, false после добавления")
    void isEmpty() {
        CustomList<Student> collection = new CustomList<>();
        assertThat(collection.isEmpty()).isTrue();

        collection.add(makeStudent(1, 3.0, 100));

        assertThat(collection.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("Коллекция автоматически расширяется при добавлении элементов сверх capacity")
    void growBeyondInitialCapacity() {
        CustomList<Student> collection = new CustomList<>(2);

        for (int i = 1; i <= 10; i++) {
            collection.add(makeStudent(1, 3.0, i));
        }

        assertThat(collection.size()).isEqualTo(10);
        assertThat(collection.get(9).getRecordBookNumber()).isEqualTo(10);
    }

    @Test
    @DisplayName("get с невалидным индексом - бросает IndexOutOfBoundsException")
    void getInvalidIndex() {
        CustomList<Student> collection = new CustomList<>();
        collection.add(makeStudent(1, 3.0, 100));

        assertThatThrownBy(() -> collection.get(5)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> collection.get(-1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    @DisplayName("foreach работает через Iterator")
    void iteratorForEach() {
        CustomList<Student> collection = new CustomList<>();
        collection.add(makeStudent(1, 3.0, 100));
        collection.add(makeStudent(2, 4.0, 200));
        collection.add(makeStudent(3, 5.0, 300));

        int count = 0;
        for (Student student : collection) {
            count++;
        }

        assertThat(count).isEqualTo(3);
    }

    @Test
    @DisplayName("stream() - фильтрация и сбор в список")
    void stream() {
        CustomList<Student> collection = new CustomList<>();
        collection.add(makeStudent(1, 2.0, 100));
        collection.add(makeStudent(2, 4.5, 200));
        collection.add(makeStudent(3, 3.0, 300));

        List<Student> highScore = collection.stream()
                .filter(s -> s.getAverageScore() > 3.0)
                .toList();

        assertThat(highScore.size()).isEqualTo(1);
        assertThat(highScore.getFirst().getRecordBookNumber()).isEqualTo(200);
    }

    @Test
    @DisplayName("parallelStream() - подсчет элементов корректен")
    void parallelStream() {
        CustomList<Student> collection = new CustomList<>();
        for (int i = 1; i <= 1_000; i++) {
            collection.add(makeStudent(1, 3.0, i));
        }

        long count = collection.parallelStream().count();

        assertThat(count).isEqualTo(1000);
    }

    @Test
    @DisplayName("parallelStream() - подсчет вхождений элемента (доп задание 4)")
    void parallelStreamCountOccurrences() {
        CustomList<Student> collection = new CustomList<>();
        Student target = makeStudent(1, 3.0, 100);

        collection.add(target);
        collection.add(makeStudent(2, 4.0, 200));
        collection.add(target);
        collection.add(makeStudent(3, 5.0, 300));
        collection.add(target);

        long occurrences = collection.parallelStream()
                .filter(s -> s.equals(target))
                .count();

        assertThat(occurrences).isEqualTo(3);
    }

    @Test
    @DisplayName("asList() - изменения через List отражаются в коллекции")
    void asListReflectsChanges() {
        CustomList<Student> collection = new CustomList<>();
        collection.add(makeStudent(1, 3.0, 300));
        collection.add(makeStudent(2, 4.0, 100));

        List<Student> list = collection.asList();
        Student replacement = makeStudent(5, 5.0, 500);
        list.set(0, replacement);

        assertThat(collection.get(0)).isEqualTo(replacement);
    }

    @Test
    @DisplayName("asList().size() совпадает с size() коллекции")
    void asListSize() {
        CustomList<Student> collection = new CustomList<>();
        collection.add(makeStudent(1, 3.0, 100));
        collection.add(makeStudent(2, 4.0, 200));

        assertThat(collection.asList().size()).isEqualTo(collection.size());
    }


    private Student makeStudent(int group, double avgScore, int recordBook) {
        return Student.builder()
                .groupNumber(group)
                .averageScore(avgScore)
                .recordBookNumber(recordBook)
                .build();
    }
}