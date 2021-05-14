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
        root = LinkedList.insertAt(root,0, data);
    }

    public int peek() {
        return LinkedList.getOrNull(root, index).getData();
    }

    public int dequeue() {
        int data = LinkedList.getOrNull(root, index).getData();
        root = LinkedList.removeAt(root, index);
        --index;
        return data;
    }

    public int getSize() {
        return index + 1;
    }
}