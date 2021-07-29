package academy.pocu.comp3500.assignment4;

import academy.pocu.comp3500.assignment4.project.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public final class Project {
    private HashMap<String, Task> taskMap = new HashMap<>();
    private HashMap<String, Node> transposeMap = new HashMap<>();
    private ArrayList<String> startList = new ArrayList<>();

    public Project(final Task[] tasks) {
        for (var t : tasks) {
            taskMap.put(t.getTitle(), t);
            transposeMap.put(t.getTitle(), new Node(t.getTitle(), t.getEstimate()));
            if (t.getPredecessors().size() < 1) {
                startList.add(t.getTitle());
            }
        }
        for (var t : taskMap.values()) {
            for (var transpose : t.getPredecessors()) {
                transposeMap.get(transpose.getTitle()).addPredecessor(transposeMap.get(t.getTitle()));
            }
        }
    }

    private void topologicalSortRecursive(Task task, HashMap<String, Task> discovered, HashMap<String, Task> searchMap, LinkedList<String> visitTask) {
        discovered.put(task.getTitle(), task);

        for (var t : task.getPredecessors()) {
            if (discovered.containsKey(t.getTitle())) {
                continue;
            }

            if (searchMap != null && searchMap.containsKey(t.getTitle()) == false) {
                continue;
            }

            topologicalSortRecursive(t,
                    discovered,
                    searchMap,
                    visitTask);
        }

        visitTask.addFirst(task.getTitle());
    }

    private void nodeTopologicalSortRecursive(Node node, HashMap<String, Node> discovered, HashMap<String, Task> searchMap, LinkedList<String> visitTask) {
        discovered.put(node.getTitle(), node);

        for (var t : node.getPredecessors()) {
            if (discovered.containsKey(t.getTitle())) {
                continue;
            }

            if (searchMap != null && searchMap.containsKey(t.getTitle()) == false) {
                continue;
            }

            nodeTopologicalSortRecursive(t,
                    discovered,
                    searchMap,
                    visitTask);
        }

        visitTask.addFirst(node.getTitle());
    }

    private LinkedList<LinkedList<String>> getScc(final String task) {
        HashMap<String, Task> searchChild = new HashMap<>();
        LinkedList<String> visitTasks = new LinkedList<>();

        topologicalSortRecursive(taskMap.get(task),
                searchChild,
                null,
                visitTasks);

        HashMap<String, Node> discoveredMap = new HashMap<>();
        LinkedList<LinkedList<String>> lists = new LinkedList<>();
        for (var taskName : visitTasks) {
            Node t = transposeMap.get(taskName);
            if (discoveredMap.containsKey(t.getTitle())) {
                continue;
            }
            if (searchChild.containsKey(t.getTitle()) == false) {
                continue;
            }

            LinkedList<String> list = new LinkedList<>();

            nodeTopologicalSortRecursive(t,
                    discoveredMap,
                    searchChild,
                    list);

            lists.addFirst(list);
        }
        return lists;
    }

    public int findTotalManMonths(final String task) {
        var lists = getScc(task);
        int manMonth = 0;

        for (var list : lists) {
            if (list.size() == 1) {
                manMonth += taskMap.get(list.getFirst()).getEstimate();
            }
        }

        return manMonth;
    }

    public int findMinDuration(final String task) {
        var lists = getScc(task);
        HashMap<String, Task> circleTask = new HashMap<>();

        for (var list : lists) {
            if (list.size() > 1) {
                for (var name : list) {
                    circleTask.put(name, taskMap.get(name));
                }
            }
        }

        HashMap<String, Task> discovered = new HashMap<>();
        int duration = getDurationRecursive(taskMap.get(task),
                discovered,
                circleTask);

        return duration;
    }

    private int getDurationRecursive(Task task, HashMap<String, Task> discovered, HashMap<String, Task> circleMap) {
        discovered.put(task.getTitle(), task);
        int max = 0;

        for (var t : task.getPredecessors()) {
            if (circleMap.containsKey(t.getTitle())) {
                continue;
            }

            int temp = getDurationRecursive(t,
                    discovered,
                    circleMap);

            if (temp > max) {
                max = temp;
            }
        }

        return max + task.getEstimate();
    }

    public int findMaxBonusCount(final String task) {
        LinkedList<String> queue = new LinkedList<>();
        HashMap<String, String> route = new HashMap<>();
        HashMap<String, Node> discovered = new HashMap<>();
        boolean isFind = true;

//        for (var s : startList) {
//            while (isFind) {
//                route.clear();
//                discovered.clear();
//                isFind = bfs(s, queue, route, discovered, task);
//            }
//            isFind = true;
//        }

        return -1;
    }

    private boolean bfs(String start, LinkedList<String> queue, HashMap<String, String> route, HashMap<String, Node> discovered, String destinationTask) {
        queue.addFirst(start);
        while (queue.size() > 0) {
            String pop = queue.removeFirst();

            if (destinationTask == pop) {
                String temp = destinationTask;
                int min = Integer.MAX_VALUE;
                while (route.containsKey(temp)) {
                    min = Math.min(transposeMap.get(temp).canFlowVolume(), min);
                    temp = route.get(temp);
                }
                min = Math.min(transposeMap.get(temp).canFlowVolume(), min);

                temp = destinationTask;
                while (route.containsKey(temp)) {
                    transposeMap.get(temp).addFlowVolume(min);
                    temp = route.get(temp);
                }
                transposeMap.get(temp).addFlowVolume(min);

                System.out.println(start + " --> " + destinationTask + " : " + min);
                return true;
            }

            for (var t : transposeMap.get(pop).getPredecessors()) {
                if (discovered.containsKey(t.getTitle())) {
                    continue;
                }

                if (t.canFlowVolume() < 1) {
                    continue;
                }

                route.put(t.getTitle(), pop);
                queue.addLast(t.getTitle());
                discovered.put(t.getTitle(), t);
            }
        }

        return false;
    }
}