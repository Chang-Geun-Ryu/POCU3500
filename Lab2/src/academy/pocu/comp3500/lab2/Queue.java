package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Queue {
    Node tail;
    Node front;
    int index;

    public Queue() {
        tail = null;
        front = null;
        index = -1;
    }

    public void enqueue(final int data) {
        ++index;
        Node node = LinkedList.prepend(null, data);

        if (index == 0) {
            front = node;
        } else if (index == 1) {
            front.setNext(node);
        } else {
            tail.setNext(node);
            tail = tail.getNextOrNull();
        }
    }

    public int peek() {
        return front.getData();
    }

    public int dequeue() {
        int data = front.getData();
        if (index == 0) {
            tail = null;
            front = null;
        } else {
            front = front.getNextOrNull();
        }
        --index;
        return data;
    }

    public int getSize() {
        return index + 1;
    }
}
