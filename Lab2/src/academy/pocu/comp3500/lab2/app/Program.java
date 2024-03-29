package academy.pocu.comp3500.lab2.app;

import academy.pocu.comp3500.lab2.LinkedList;
import academy.pocu.comp3500.lab2.Queue;
import academy.pocu.comp3500.lab2.Stack;
import academy.pocu.comp3500.lab2.datastructure.Node;

public class Program {

    public static void main(String[] args) {
	    // write your code here
        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11); // root: 10, list: 10 -> 11
            root = LinkedList.append(root, 12); // root: 10, list: 10 -> 11 -> 12
            Node node = root;
            while (node != null) {
                System.out.println(node.getData());
                node = node.getNextOrNull();
            }
        }
        {
            System.out.println("////");
            Node root = LinkedList.append(null, 10);

            root = LinkedList.prepend(root, 11); // root: 11, list: 11 -> 10
            root = LinkedList.prepend(root, 12); // root: 12, list: 12 -> 11 -> 10
            Node node = root;
            while (node != null) {
                System.out.println(node.getData());
                node = node.getNextOrNull();
            }
        }
        {
            System.out.println("////");
            Node root = null;// = LinkedList.append(null, 10);

            root = LinkedList.insertAt(root, 0, 11); // root: 11, list: 11 -> 10
            root = LinkedList.insertAt(root, 1, 12); // root: 11, list: 11 -> 12 -> 10
            root = LinkedList.insertAt(root, -1, 100);

            Node node = root;
            while (node != null) {
                System.out.println(node.getData());
                node = node.getNextOrNull();
            }
        }
        {
            System.out.println("////");
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11);
            root = LinkedList.append(root, 12);
            root = LinkedList.append(root, 13);

            Node node = root;
            while (node != null) {
                System.out.println(node.getData());
                node = node.getNextOrNull();
            }

            root = LinkedList.removeAt(root, 0); // root: 11, list: 11 -> 12 -> 13
            root = LinkedList.removeAt(root,  1); // root: 11, list: 11 -> 13
            System.out.println("//// removed");
            node = root;
            while (node != null) {
                System.out.println(node.getData());
                node = node.getNextOrNull();
            }
        }
        {
            System.out.println("////");
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11);

            int index = LinkedList.getIndexOf(root, 10); // index: 0
            System.out.println(index);
            index = LinkedList.getIndexOf(root, 11); // index: 1
            System.out.println(index);
        }
        {
            System.out.println("////");
            Node root = LinkedList.append(null, 10);
            root = LinkedList.append(root, 11);

            Node node = LinkedList.getOrNull(root, 0); // node: 10
            System.out.println(node);
            node = LinkedList.getOrNull(root, 2); // node: 11
            System.out.println(node);
        }
        {
            System.out.println("//// reverse");
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11);
            root = LinkedList.append(root, 12);
            root = LinkedList.append(root, 13);
            root = LinkedList.append(root, 14);

            Node node = root;
            while (node != null) {
                System.out.println(node.getData());
                node = node.getNextOrNull();
            }
            root = LinkedList.reverse(root); // root: 14, list: 14 -> 13 -> 12 -> 11 -> 10

            System.out.println("//// reverse!!!!!!");
            node = root;
            while (node != null) {
                System.out.println(node.getData());
                node = node.getNextOrNull();
            }
        }
        {
            System.out.println("//// interleaveOrNull");
            Node root1 = LinkedList.append(null, 10);

            root1 = LinkedList.append(root1, 11);
            root1 = LinkedList.append(root1, 12);

            Node root2 = new Node(13);

//            root2 = LinkedList.append(root2, 14);
//            root2 = LinkedList.append(root2, 15);
//            root2 = LinkedList.append(root2, 16);

            Node newRoot = LinkedList.interleaveOrNull(root1, root2); // newRoot: 10, list: 10 -> 13 -> 11 -> 14 -> 12 -> 15
            Node node = newRoot;
            while (node != null) {
                System.out.println(node.getData());
                node = node.getNextOrNull();
            }
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11);
            root = LinkedList.append(root, 12);

            assert (root.getData() == 10);

            Node next = root.getNextOrNull();

            assert (next.getData() == 11);

            next = next.getNextOrNull();

            assert (next.getData() == 12);
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.prepend(root, 11);

            assert (root.getData() == 11);

            root = LinkedList.prepend(root, 12);

            assert (root.getData() == 12);

            Node next = root.getNextOrNull();

            assert (next.getData() == 11);

            next = next.getNextOrNull();

            assert (next.getData() == 10);
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.insertAt(root, 0, 11);

            assert (root.getData() == 11);

            root = LinkedList.insertAt(root, 1, 12);

            assert (root.getData() == 11);

            Node next = root.getNextOrNull();

            assert (next.getData() == 12);

            next = next.getNextOrNull();

            assert (next.getData() == 10);
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11);
            root = LinkedList.append(root, 12);
            root = LinkedList.append(root, 13);

            root = LinkedList.removeAt(root, 0);

            assert (root.getData() == 11);

            root = LinkedList.removeAt(root, 1);

            assert (root.getData() == 11);

            Node next = root.getNextOrNull();

            assert (next.getData() == 13);
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11);

            int index = LinkedList.getIndexOf(root, 10);

            assert (index == 0);

            index = LinkedList.getIndexOf(root, 11);

            assert (index == 1);
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11);

            Node node = LinkedList.getOrNull(root, 0);

            assert (node.getData() == 10);

            node = LinkedList.getOrNull(root, 1);

            assert (node.getData() == 11);
        }

        {
            Node root1 = LinkedList.append(null, 10);

            root1 = LinkedList.append(root1, 11);
            root1 = LinkedList.append(root1, 12);

            Node root2 = LinkedList.append(null, 13);

            root2 = LinkedList.append(root2, 14);
            root2 = LinkedList.append(root2, 15);

            Node newRoot = LinkedList.interleaveOrNull(root1, root2); // newRoot: 10, list: 10 -> 13 -> 11 -> 14 -> 12 -> 15

            assert (newRoot.getData() == 10);

            Node next = newRoot.getNextOrNull();

            assert (next.getData() == 13);

            next = next.getNextOrNull();

            assert (next.getData() == 11);

            next = next.getNextOrNull();

            assert (next.getData() == 14);

            next = next.getNextOrNull();

            assert (next.getData() == 12);

            next = next.getNextOrNull();

            assert (next.getData() == 15);
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11);
            root = LinkedList.append(root, 12);
            root = LinkedList.append(root, 13);
            root = LinkedList.append(root, 14);

            root = LinkedList.reverse(root); // root: 14, list: 14 -> 13 -> 12 -> 11 -> 10

            assert (root.getData() == 14);

            Node next = root.getNextOrNull();

            assert (next.getData() == 13);

            next = next.getNextOrNull();

            assert (next.getData() == 12);

            next = next.getNextOrNull();

            assert (next.getData() == 11);

            next = next.getNextOrNull();

            assert (next.getData() == 10);
        }

        {
            Stack stack = new Stack();

            stack.push(20);
            stack.push(21); // stack: 21
            //        20

            int data = stack.pop();

            assert (data == 21);

            data = stack.pop();

            assert (data == 20);
        }

        {
            Stack stack = new Stack();

            stack.push(20); // stack: 20

            assert (stack.peek() == 20);

            stack.push(21); // stack: 21
            //        20

            assert (stack.peek() == 21);
        }

        {
            Stack stack  = new Stack();

            stack.push(20);
            stack.push(21);

            assert (stack.getSize() == 2);
        }
        {
            Queue queue = new Queue();

            queue.enqueue(20);

            assert (queue.peek() == 20);

            queue.enqueue(21);

            assert (queue.peek() == 20);
        }

        {
            Queue queue = new Queue();

            queue.enqueue(20);
            queue.enqueue(21);

            int data = queue.dequeue();

            assert (data == 20);

            data = queue.dequeue();

            assert (data == 21);
        }

        {
            Queue queue = new Queue();

            queue.enqueue(20);
            queue.enqueue(21);

            assert (queue.getSize() == 2);
        }
    }
}
