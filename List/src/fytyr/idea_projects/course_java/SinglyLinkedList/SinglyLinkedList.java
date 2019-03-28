package fytyr.idea_projects.course_java.SinglyLinkedList;

import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    // ��������� �������� �� �������
    private ListItem<T> getNode(int index) {
        makeOutOfBoundsExceptionCheck(index);

        ListItem<T> p = head;
        for (int i = 0; i != index; i++) {
            p = p.getNext();
        }
        return p;
    }

    // ����� ������
    public int getSize() {
        return count;
    }

    //������� �������� � ������
    public void addFirstItem(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    // ��������� �������� ������� ��������
    public T getFirstElement() {
        if (head == null) {
            throw new NullPointerException("List is empty");
        }
        return head.getData();
    }

    // ��������� �������� �������� �� �������
    public T getElement(int index) {
        makeOutOfBoundsExceptionCheck(index);

        return getNode(index).getData();
    }

    // ��������� �������� �������� �� �������
    public T setElement(int index, T data) {
        makeOutOfBoundsExceptionCheck(index);

        ListItem<T> node = getNode(index);
        T element = node.getData();
        node.setData(data);
        return element;
    }

    // �������� ������� ��������
    public T removeFirst() {
        if (head == null) {
            throw new NullPointerException("List is empty");
        }
        T element = head.getData();
        head = head.getNext();
        count--;
        return element;
    }

    // �������� �������� �� �������
    public T removeNode(int index) {
        makeOutOfBoundsExceptionCheck(index);

        if (index == 0) {
            return removeFirst();
        }
        ListItem<T> p = getNode(index - 1);
        T element = p.getNext().getData();

        p.setNext(p.getNext().getNext());
        count--;
        return element;
    }

    // �������� ���� �� ��������
    public boolean remove(T data) {
        boolean isDeleted = false;

        if (Objects.equals(head.getData(), data)) {
            head = head.getNext();
            count--;
            return true;
        }

        for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (Objects.equals(p.getData(), data)) {
                prev.setNext(p.getNext());
                count--;
                isDeleted = true;
                break;
            }
        }
        return isDeleted;
    }

    // ������� �������� �� �������
    public void addItem(int index, T data) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("������ �� ����� ���� ������ 0 ��� ������ ����� ������ ����� ��� �� 1");
        }

        if (index == 0) {
            addFirstItem(data);
        } else {
            ListItem<T> newListItem = new ListItem<>(data);
            ListItem<T> p = getNode(index - 1);

            newListItem.setNext(p.getNext());
            p.setNext(newListItem);
            count++;
        }
    }

    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        list.append("{ ");
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            list.append(p.getData()).append(", ");
        }
        int endIndex = list.lastIndexOf(", ");
        list.deleteCharAt(endIndex);
        list.append("}");
        return list.toString();
    }

    // �������� ������
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

    // ����������� ������
    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> result = new SinglyLinkedList<>();
        if (head == null) {
            return result;
        }
        ListItem<T> node = new ListItem<>(getFirstElement());
        result.head = node;
        for (ListItem<T> p = head.getNext(); p != null; p = p.getNext()) {
            ListItem<T> tmp = new ListItem<>(p.getData());
            node.setNext(tmp);
            node = node.getNext();
        }
        result.count = count;
        return result;
    }

    private void makeOutOfBoundsExceptionCheck(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("� ������ ��� ���� � ���������� ��������");
        }
    }
}
