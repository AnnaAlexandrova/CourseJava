package fytyr.idea_projects.course_java.hashTable;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private ArrayList<T>[] table;
    private int size = 0;
    private int modCount = 0;

    private class MyHashTableIterator implements Iterator<T> {
        private int currentListIndex = 0;
        private int currentTableIndex = 0;
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
            while (table[currentTableIndex] == null || currentListIndex == table[currentTableIndex].size()) {
                currentTableIndex++;
                currentListIndex = 0;
            }
            currentListIndex++;
            currentIndex++;
            return table[currentTableIndex].get(currentListIndex - 1);
        }
    }

    public HashTable() {
        //noinspection unchecked
        this.table = new ArrayList[10];
    }

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Вместимость списка должна быть больше 0");
        }
        //noinspection unchecked
        this.table = new ArrayList[capacity];
    }

    private int getIndex(Object o) {
        int index = 0;
        if (o != null) {
            index = Math.abs(o.hashCode() % table.length);
        }
        return index;
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
        int index = getIndex(o);
        if (table[index] == null) {
            return false;
        }
        return table[index].contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyHashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        for (T e : this) {
            array[i] = e;
            i++;
        }
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a == null) {
            throw new NullPointerException("Переданный массив равен null");
        }
        if (a.length < size) {
            //noinspection unchecked
            return ((T1[]) Arrays.copyOf(toArray(), size, a.getClass()));
        }
        int i = 0;
        for (T e : this) {
            //noinspection unchecked
            a[i] = (T1) e;
            i++;
        }
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(T t) {
        if (size >= table.length * 0.75) {
            HashTable<T> newTable = new HashTable<>(size * 2);
            newTable.addAll(this);
            table = newTable.table;
        }
        int index = getIndex(t);
        if (table[index] == null) {
            table[index] = new ArrayList<>();
        }
        table[index].add(t);
        size++;
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);
        if (table[index] == null) {
            return false;
        }
        boolean isDeleted = table[index].remove(o);
        if (isDeleted) {
            size--;
            modCount++;
        }
        return isDeleted;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        makeNullPointerExceptionCheck(c);
        for (Object e : c) {
            if (!contains(e)) {
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
        for (T e : c) {
            add(e);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        makeNullPointerExceptionCheck(c);
        if (c.size() == 0) {
            return false;
        }
        boolean isDeleted = false;
        for (ArrayList<T> node : table) {
            if (node != null) {
                int tmp = node.size();
                if (node.removeAll(c)) {
                    size -= tmp - node.size();
                    isDeleted = true;
                }
            }
        }
        if (isDeleted) {
            modCount++;
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
        boolean isDeleted = false;
        for (ArrayList<T> item : table) {
            if (item != null) {
                int currentSize = item.size();
                if (item.retainAll(c)) {
                    size -= currentSize - item.size();
                    isDeleted = true;
                }
            }
        }
        if (isDeleted) {
            modCount++;
        }
        return isDeleted;
    }

    @Override
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        size = 0;
        modCount++;
    }

    public String toString() {
        StringJoiner string = new StringJoiner(", ", "{", "}");
        for (T e : this) {
            if (e != null) {
                string.add(e.toString());
            } else {
                string.add(null);
            }
        }
        return string.toString();
    }

    private void makeNullPointerExceptionCheck(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Переданная коллекция равна null");
        }
    }
}
