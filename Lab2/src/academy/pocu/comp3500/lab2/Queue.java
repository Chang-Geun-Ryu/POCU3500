package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Queue {
    Node root;
    int index;

    public Queue() {
        root = null;
        index = -1;
    }

    public void enqueue(final int data) {
        ++index;
        root = LinkedList.append(root, data);
    }

    public int peek() {
        return LinkedList.getOrNull(root, 0).getData();
    }

    public int dequeue() {
        int data = LinkedList.getOrNull(root, 0).getData();
        root = LinkedList.removeAt(root, 0);
        --index;
        return data;
    }

    public int getSize() {
        return index + 1;
    }
}
