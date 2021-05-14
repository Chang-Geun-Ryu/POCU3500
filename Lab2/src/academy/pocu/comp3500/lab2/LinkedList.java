package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class LinkedList {

    private LinkedList() {
    }

    public static Node append(final Node rootOrNull, final int data) {
        if (rootOrNull == null) {
            return new Node(data);
        } else {
            Node node = rootOrNull;
            while (node.getNextOrNull() != null) {
                node = node.getNextOrNull();
            }
            node.setNext(new Node(data));
        }
        return rootOrNull;
    }

    public static Node prepend(final Node rootOrNull, final int data) {
        if (rootOrNull == null) {
            return new Node(data);
        } else {
            Node node = new Node(data);
            node.setNext(rootOrNull);
            return node;
        }
    }

    public static Node insertAt(final Node rootOrNull, final int index, final int data) {
        int i = 0;
        Node node = rootOrNull;
        Node preNode = null;

        do {
            if (i == index) {
                Node newNode = new Node(data);
                if (preNode == null) {
                    newNode.setNext(node);
                    return newNode;
                } else {
                    preNode.setNext(newNode);
                    newNode.setNext(node);
                }
                break;
            } else if (node == null) {
                return rootOrNull;
            }

            preNode = node;
            node = node.getNextOrNull();
            i++;
        } while (preNode != null && i <= index);

        return rootOrNull;
    }

    public static Node removeAt(final Node rootOrNull, final int index) {
        int i = 0;
        Node node = rootOrNull;
        Node preNode = null;

        while (node != null && i <= index) {
            if (i == index) {
                if (preNode == null) {
                    return node.getNextOrNull();
                } else {
                    preNode.setNext(node.getNextOrNull());
                }
                break;
            }

            preNode = node;
            node = node.getNextOrNull();
            i++;
        }

        return rootOrNull;
    }

    public static int getIndexOf(final Node rootOrNull, final int data) {
        int i = 0;
        Node node = rootOrNull;
        while (node != null) {
            if (node.getData() == data) {
                return i;
            }

            node = node.getNextOrNull();
            i++;
        }
        return -1;
    }

    public static Node getOrNull(final Node rootOrNull, final int index) {
        int i = 0;
        Node node = rootOrNull;
        while (node != null) {
            if (i == index) {
                return node;
            }

            node = node.getNextOrNull();
            i++;
        }
        return null;
    }

    public static Node reverse(final Node rootOrNull) {
        Node node = rootOrNull;
        Node preNode = null;
        Node nextNode = null;
        while (node != null) {
            nextNode = node.getNextOrNull();
            node.setNext(preNode);

            preNode = node;
            node = nextNode;
        }
        return preNode;
    }

    public static Node interleaveOrNull(final Node root0OrNull, final Node root1OrNull) {
        Node root = null;
        Node node = null;
        Node nextNode = null;
        Node temp = null;

        if (root1OrNull == null) {
            root = root0OrNull;
            return root;
        } else if (root0OrNull == null) {
            root = root1OrNull;
            return root;
        } else {
            root = root0OrNull;
            temp = root1OrNull;
        }

        node = root;

        while (node != null && temp != null) {
            nextNode = node.getNextOrNull();
            node.setNext(temp);
            node = node.getNextOrNull();
            temp = nextNode;
        }

        return root;
    }
}