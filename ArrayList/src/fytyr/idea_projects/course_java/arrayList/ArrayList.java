package fytyr.idea_projects.course_java.arrayList;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private T[] items;
    private int size;
    private int modCount = 0;

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int currentModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась");
            }
            if (currentModCount != modCount) {
                throw new ConcurrentModificationException("За время обхода в коллекцию были добавлены или удалены элементы");
            }
            ++currentIndex;
            return items[currentIndex];
        }
    }

    public ArrayList() {
        //noinspection unchecked
        items = (T[]) new Object[10];
    }

    public ArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Вместимость списка должна быть больше 0");
        }
        //noinspection unchecked
        items = (T[]) new Object[capacity];
    }

    public void ensureCapacity(int capacity) {
        if (items.length < capacity) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    public void trimToSize() {
        if (size < items.length) {
            items = Arrays.copyOf(items, size);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a == null) {
            throw new NullPointerException("Переданный массив равен null");
        }
        if (a.length < size) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(items, size, a.getClass());
        }
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(T t) {
        if (size >= items.length) {
            increaseCapacity();
        }
        items[size] = t;
        size++;
        modCount++;
        return true;
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        makeNullPointerExceptionCheck(c);
        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        makeNullPointerExceptionCheck(c);
        if (c.size() == 0) {
            return false;
        }
        if (size + c.size() >= items.length) {
            ensureCapacity(size + c.size());
        }

        int i = size;
        for (T e : c) {
            items[i] = e;
            i++;
        }
        size += c.size();
        modCount++;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Переданный индекс в списке отсутствует");
        }
        makeNullPointerExceptionCheck(c);
        if (c.size() == 0) {
            return false;
        }

        ensureCapacity(size + c.size());

        if (index < size) {
            System.arraycopy(items, index, items, index + c.size(), size - index);
        }

        int i = index;
        for (T e : c) {
            items[i] = e;
            i++;
        }
        size += c.size();
        modCount++;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        makeNullPointerExceptionCheck(c);
        if (c.size() == 0) {
            return false;
        }

        boolean isDeleted = false;
        int i = 0;

        while (i < size) {
            if (c.contains(items[i])) {
                remove(i);
                isDeleted = true;
            } else {
                i++;
            }
        }
        return isDeleted;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        makeNullPointerExceptionCheck(c);
        if (c.size() == 0) {
            if (size == 0) {
                return false;
            }
            clear();
            return true;
        }

        int currentSize = size;
        int i = 0;
        while (i < size) {
            if (c.contains(items[i])) {
                i++;
            } else {
                remove(i);
            }
        }
        return currentSize != size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }
        modCount++;
        size = 0;
    }

    @Override
    public T get(int index) {
        makeOutOfBoundsCheck(index);
        return items[index];
    }

    @Override
    public T set(int index, T element) {
        makeOutOfBoundsCheck(index);
        T removedItem = items[index];
        this.items[index] = element;
        return removedItem;
    }

    @Override
    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Переданный индекс в списке отсутствует");
        }
        if (size >= items.length) {
            increaseCapacity();
        }
        if (index < size) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }
        items[index] = element;
        size++;
        modCount++;
    }

    @Override
    public T remove(int index) {
        makeOutOfBoundsCheck(index);
        T removedElement = get(index);

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }
        items[size - 1] = null;
        size--;
        modCount++;
        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }
        return -1;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("{ ");

        int i = 0;
        for (T e : items) {
            if (i == size) {
                break;
            }
            string.append(e).append(" ");
            i++;
        }
        string.append("}");
        return string.toString();
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    private void makeOutOfBoundsCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Переданный индекс в списке отсутствует");
        }
    }

    private void makeNullPointerExceptionCheck(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Переданная коллекция равна null");
        }
    }
}
