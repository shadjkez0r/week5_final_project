package team4.finalproject.collection;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CustomList<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 16;

    private Object[] elements;
    private int size;

    public CustomList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public CustomList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity must be >= 0, but got: " + initialCapacity);
        }
        this.elements = new Object[initialCapacity];
        this.size = 0;
    }

    public void add(T element) {
        if (size == elements.length) {
            grow();
        }
        elements[size++] = element;
    }


    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }


    public void set(int index, T element) {
        checkIndex(index);
        elements[index] = element;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public List<T> asList() {
        return new ListAdapter();
    }

    public Stream<T> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    public Stream<T> parallelStream() {
        return StreamSupport.stream(this.spliterator(), true);
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomIterator();
    }

    @Override
    public Spliterator<T> spliterator() {
        return new CustomSpliterator(0, size);
    }

    private void grow() {
        int newCapacity = this.elements.length == 0 ? DEFAULT_CAPACITY : this.elements.length * 2;
        elements = Arrays.copyOf(elements, newCapacity);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
    }

    private class ListAdapter extends AbstractList<T> {
        @Override
        public T get(int index) {
            return CustomList.this.get(index);
        }

        @Override
        public T set(int index, T element) {
            T old = get(index);
            CustomList.this.set(index, element);
            return old;
        }

        @Override
        public int size() {
            return CustomList.this.size();
        }


    }

    private class CustomIterator implements Iterator<T> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) elements[cursor++];
        }
    }

    private class CustomSpliterator implements Spliterator<T> {
        private int current;
        private final int end;

        public CustomSpliterator(int start, int end) {
            this.current = start;
            this.end = end;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean tryAdvance(Consumer<? super T> action) {
            if (current < end) {
                action.accept((T) elements[current++]);
                return true;
            }
            return false;
        }

        @Override
        public Spliterator<T> trySplit() {
            int remaining =  end - current;
            if (remaining < 2) {
                return null;
            }
            int mid = current + remaining / 2;
            Spliterator<T> left = new CustomSpliterator(current, mid);
            current = mid;
            return left;
        }

        @Override
        public long estimateSize() {
            return end - current;
        }

        @Override
        public int characteristics() {
            return SIZED | SUBSIZED | ORDERED;
        }
    }
}
