package fytyr.idea_projects.course_java.SinglyLinkedList;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    // ѕолучени€ элемента по индексу
    private ListItem<T> getNode(int index) {
        isOutOfBoundsException(index);

        ListItem<T> p = head;
        for (int i = 0; i != index; i++) {
            p = p.getNext();
        }
        return p;
    }

    // длина списка
    public int getSize() {
        return count;
    }

    //вставка элемента в начало
    public void addFirstItem(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    // получение значени€ первого элемента
    public T getFirstElement() {
        return head.getData();
    }

    // получение значени€ элемента по индексу
    public T getElement(int index) {
        isOutOfBoundsException(index);

        return getNode(index).getData();
    }

    // установка значени€ элемента по индексу
    public T setElement(int index, T data) {
        isOutOfBoundsException(index);

        T element = getNode(index).getData();
        getNode(index).setData(data);
        return element;
    }

    // удаление первого элемента
    public T removeFirst() {
        T element = head.getData();
        head = head.getNext();
        count--;
        return element;
    }

    // удаление элемента по индексу
    public T removeNode(int index) {
        isOutOfBoundsException(index);

        if (index == 0) {
            return removeFirst();
        }
        ListItem<T> p = getNode(index);
        T element = p.getData();
        for (ListItem<T> prev = head; prev != null; prev = prev.getNext()) {
            if (prev.getNext() == p) {
                prev.setNext(p.getNext());
            }
        }
        count--;
        return element;
    }

    // удаление узла по значению
    public boolean remove(T data) {
        boolean isDeleted = false;
        for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (p.getData() == data) {
                prev.setNext(p.getNext());
                count--;
                isDeleted = true;
                break;
            }
        }
        return isDeleted;
    }

    // вставка элемента по индексу
    public void addItem(int index, T data) {
        isOutOfBoundsException(index);

        if (index == 0) {
            addFirstItem(data);
        } else {
            ListItem<T> newListItem = new ListItem<>(data);
            ListItem<T> p = getNode(index);
            for (ListItem<T> prev = head; prev != null; prev = prev.getNext()) {
                if (prev.getNext() == p) {
                    newListItem.setNext(p);
                    prev.setNext(newListItem);
                    count++;
                    break;
                }
            }
        }
    }

    public void print() {
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            System.out.print(p.getData() + " ");
        }
        System.out.println();
    }

    // разворот списка
    public void reverse() {
        ListItem<T> current = head;
        ListItem<T> previous = null;
        while (current != null) {
            ListItem<T> next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }
        head = previous;
    }

    //  опирование списка
    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> result = new SinglyLinkedList<>();
        result.head = head;
        for (ListItem<T> p = head, q = result.head; p != null; p = p.getNext(), q = q.getNext()) {
            q.setData(p.getData());
            q.setNext(p.getNext());
            result.count++;
        }
        return result;
    }

    private void isOutOfBoundsException(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("¬ списке нет узла с переданным индексом");
        }
    }
}
