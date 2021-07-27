package academy.pocu.comp3500.assignment4;

import academy.pocu.comp3500.assignment4.project.Task;

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
        for (var t : tasks) {
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

    private LinkedList<LinkedList<String>> getSCC(final String task)  {
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
        var lists = getSCC(task);
        int manMonth = 0;

        for (var list : lists) {
            if (list.size() == 1) {
                manMonth += taskMap.get(list.getFirst()).getEstimate();
            }
        }

        return manMonth;
    }

    public int findMinDuration(final String task) {
        System.out.println("findMinDuration ///////////////////////////============> " + task);
        return 0;
    }

    public int findMaxBonusCount(final String task) {
        return -1;
    }
}