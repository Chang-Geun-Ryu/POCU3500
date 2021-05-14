package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Queue {
    Node root;

    public Queue() {
        root = null;
    }

    public void enqueue(final int data) {
        root = LinkedList.append(root, data);
    }

    public int peek() {
        return LinkedList.getOrNull(root, 0).getData();
    }

    public int dequeue() {
        int data = LinkedList.getOrNull(root, 0).getData();
        root = LinkedList.removeAt(root, 0);
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
