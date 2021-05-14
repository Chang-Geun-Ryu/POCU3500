package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Stack {
    Node root;
    int index;

    public Stack() {
        root = null;
        index = -1;
    }

    public void push(final int data) {
        ++index;
        root = LinkedList.append(root, data);
    }

    public int peek() {
        return LinkedList.getOrNull(root, index).getData();
    }

    public int pop() {
        int data = LinkedList.getOrNull(root, index).getData();
        root = LinkedList.removeAt(root, index);
        --index;
        return data;
    }

    public int getSize() {
        return index + 1;
    }
}