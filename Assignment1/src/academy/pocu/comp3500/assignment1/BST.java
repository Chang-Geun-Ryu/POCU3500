package academy.pocu.comp3500.assignment1;

public class BST {
    private Node root;

    public BST() {
        root = null;
    }

    public void insertData(int data) {
        if (root == null) {
            root = new Node(data);
            return;
        }

        insertRecursive(root, data);
    }

    private void insertRecursive(Node node, int data) {
        if (node.data < data) {
            if (node.right != null) {
                insertRecursive(node.right, data);
            } else {
                node.right = new Node(data);
            }
        } else {
            if (node.left != null) {
                insertRecursive(node.left, data);
            } else {
                node.left = new Node(data);
            }
        }
    }

    public boolean findData(int data) {
        return findRecursive(root, data);
    }

    public boolean findRecursive(Node node, int data) {
        if (node == null) {
            return false;
        }

        if (node.data == data) {
            return true;
        } else if (node.data > data) {
            return findRecursive(node.left, data);
        } else {
            return findRecursive(node.right, data);
        }
    }

//    public boolean deleteData(int data) {
//        if (root == null) {
//            return false;
//        }
//
//        return deleteRecursive(root, data);
//    }
//
//    private boolean deleteRecursive(Node node, int data) {
//        if (node == null) {
//            return false;
//        }
//
//        if (node.data == data) {
//            return true;
//        }
//    }
}
