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
                throw new NoSuchElementException("��������� �����������");
            }
            if (currentModCount != modCount) {
                throw new ConcurrentModificationException("�� ����� ������ � ��������� ���� ��������� ��� ������� ��������");
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
            throw new IllegalArgumentException("����������� ������ ������ ���� ������ 0");
        }
        //noinspection unchecked
        items = (T[]) new Object[capacity];
    }

    public void ensureCapacity(int capacity) {
        if (size < capacity) {
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
            throw new NullPointerException("���������� ������ ����� null");
        }
        if (a.length < size) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(items, size, a.getClass());
        }
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, size);
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
            increaseCapacity();
        }
        for (T e : c) {
            items[size] = e;
            size++;
        }
        modCount++;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("���������� ������ � ������ �����������");
        }
        makeNullPointerExceptionCheck(c);
        if (c.size() == 0) {
            return false;
        }

        if (size + c.size() >= items.length) {
            increaseCapacity();
        }
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
        if (c == null || c.size() == 0) {
            clear();
            return true;
        }
        boolean isDeleted = false;
        int i = 0;
        while (i < size) {
            if (c.contains(items[i])) {
                i++;
            } else {
                remove(i);
                isDeleted = true;
            }
        }
        return isDeleted;
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
            throw new IndexOutOfBoundsException("���������� ������ � ������ �����������");
        }
        ensureCapacity(size + 10);
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
        for (int i = 0; i < items.length; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = items.length - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }
        return -1;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("{ ");
        MyListIterator iterator = new MyListIterator();
        while (iterator.hasNext()) {
            string.append(iterator.next()).append(" ");
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
            throw new IndexOutOfBoundsException("���������� ������ � ������ �����������");
        }
    }

    private void makeNullPointerExceptionCheck(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("���������� ��������� ����� null");
        }
    }
}
