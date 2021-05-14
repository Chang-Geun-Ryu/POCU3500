package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Stack {
    Node root;

    public Stack() {
        root = null;
    }

    public void push(final int data) {
        root = LinkedList.append(root, data);
    }

    public int peek() {
        int index = 0;
        while (LinkedList.getOrNull(root, index) != null) {
            ++index;
        }
        return LinkedList.getOrNull(root, index - 1).getData();
    }

    public int pop() {
        int index = 0;
        while (LinkedList.getOrNull(root, index) != null) {
            ++index;;
        }

        int data = LinkedList.getOrNull(root, index - 1).getData();
        root = LinkedList.removeAt(root, index - 1);
        return data;
    }

    public int getSize() {
        int index = 0;
        while (LinkedList.getOrNull(root, index) != null) {
            ++index;
        }
        return index;
    }
}