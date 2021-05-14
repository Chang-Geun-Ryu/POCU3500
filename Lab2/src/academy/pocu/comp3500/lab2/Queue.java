package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Queue {
    Node root;
    Node front;
    int index;

    public Queue() {
        root = null;
        front = null;
        index = -1;
    }

    public void enqueue(final int data) {
        ++index;
        root = LinkedList.prepend(root, data);
        if (index == 0) {
            front = root;
        } else {
            front.setNext(root);
        }
    }

    public int peek() {
//        return LinkedList.getOrNull(root, index).getData();
        return front.getData();
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
