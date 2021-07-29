package academy.pocu.comp3500.assignment4;

import academy.pocu.comp3500.assignment4.project.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public final class Project {
    private HashMap<String, Task> taskMap = new HashMap<>();
    private HashMap<String, Task> transposeMap = new HashMap<>();

    public Project(final Task[] tasks) {
        for (var t : tasks) {
            taskMap.put(t.getTitle(), t);
            transposeMap.put(t.getTitle(), new Task(t.getTitle(), t.getEstimate()));
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

    private LinkedList<LinkedList<String>> getScc(final String task) {
        HashMap<String, Task> searchChild = new HashMap<>();
        LinkedList<String> visitTasks = new LinkedList<>();

        topologicalSortRecursive(taskMap.get(task),
                searchChild,
                null,
                visitTasks);

        HashMap<String, Task> discoveredMap = new HashMap<>();
        LinkedList<LinkedList<String>> lists = new LinkedList<>();
        for (var taskName : visitTasks) {
            Task t = transposeMap.get(taskName);
            if (discoveredMap.containsKey(t.getTitle())) {
                continue;
            }
            if (searchChild.containsKey(t.getTitle()) == false) {
                continue;
            }

            LinkedList<String> list = new LinkedList<>();

            topologicalSortRecursive(t,
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
        HashMap<String, Task> discovered = new HashMap<>();
        HashMap<String, Node> nodeMap = new HashMap<>();
        boolean isFind = true;
        ArrayList<String> startList = new ArrayList<>();

        findStartRecursive(taskMap.get(task), discovered, nodeMap, startList);

        for (var s : startList) {
            while (isFind) {
                route.clear();
                discovered.clear();
                isFind = bfs(s, queue, route, discovered, nodeMap, task);
            }
            isFind = true;
        }

        return nodeMap.get(task).canBackVolume();
    }

    private boolean bfs(String start, LinkedList<String> queue, HashMap<String, String> route, HashMap<String, Task> discovered, HashMap<String, Node> nodeMap, String destinationTask) {
        queue.addFirst(start);
        discovered.put(start, transposeMap.get(start));

        while (queue.size() > 0) {
            String pop = queue.removeFirst();

            if (destinationTask == pop) {
                addCalc(destinationTask, route, nodeMap);
                return true;
            }

            if (nodeMap.get(pop).canFlowVolume() > 0) {
                for (var t : transposeMap.get(pop).getPredecessors()) {
                    if (discovered.containsKey(t.getTitle())) {
                        continue;
                    }

                    if (nodeMap.containsKey(t.getTitle()) == false) {
                        continue;
                    }

                    if (nodeMap.get(t.getTitle()).canFlowVolume() < 1) {
                        continue;
                    }

                    route.put(t.getTitle(), pop);
                    queue.addLast(t.getTitle());
                    discovered.put(t.getTitle(), t);
                }
            }
            if (nodeMap.get(pop).canBackVolume() > 0) {
                for (var t : taskMap.get(pop).getPredecessors()) {
                    if (discovered.containsKey(t.getTitle())) {
                        continue;
                    }
                    if (nodeMap.containsKey(t.getTitle()) == false) {
                        continue;
                    }
                    if (nodeMap.get(t.getTitle()).canBackVolume() < 1) {
                        continue;
                    }

                    route.put(t.getTitle(), pop);
                    queue.addLast(t.getTitle());
                    discovered.put(t.getTitle(), t);
                }
            }
        }

        return false;
    }

    private void addCalc(String dest, HashMap<String, String> route, HashMap<String, Node> nodeMap) {
        String temp = dest;
        int min = Integer.MAX_VALUE;
        while (route.containsKey(temp)) {
            min = Math.min(nodeMap.get(temp).canFlowVolume(), min);
            temp = route.get(temp);
        }
        min = Math.min(nodeMap.get(temp).canFlowVolume(), min);

        temp = dest;
        while (route.containsKey(temp)) {
            nodeMap.get(temp).addFlowVolume(min);
            temp = route.get(temp);
        }
        nodeMap.get(temp).addFlowVolume(min);

//        System.out.println(temp + " --> " + dest + " : " + min);
    }


    private void findStartRecursive(Task task, HashMap<String, Task> discovered, HashMap<String, Node> nodeMap, ArrayList<String> startList) {
        discovered.put(task.getTitle(), task);

        for (var t : task.getPredecessors()) {
            if (discovered.containsKey(t.getTitle())) {
                continue;
            }

            findStartRecursive(t,
                    discovered,
                    nodeMap,
                    startList);
        }

        if (task.getPredecessors().size() < 1) {
            startList.add(task.getTitle());
        }
        nodeMap.put(task.getTitle(), new Node(transposeMap.get(task.getTitle())));
    }

}