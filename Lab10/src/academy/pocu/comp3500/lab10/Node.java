package academy.pocu.comp3500.lab10;

import academy.pocu.comp3500.lab10.project.Task;

import java.util.HashMap;
import java.util.LinkedList;

public class Node {
    public String circleName;
    public LinkedList<String> list;
    public HashMap<String, Task> checkedMap = new HashMap<>();

    public Node() {
        circleName = null;
        list = new LinkedList<>();
    }
}