package academy.pocu.comp3500.lab10;

import academy.pocu.comp3500.lab10.project.Task;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Project {
    public static List<String> findSchedule(final Task[] tasks, final boolean includeMaintenance) {
        HashMap<String, Task> discovered = new HashMap<>();
        LinkedList<String> visitTasks = new LinkedList<>();
        HashMap<String, Task> transposeTasks = new HashMap<>();
        for (var t : tasks) {
            transposeTasks.put(t.getTitle(), new Task(t.getTitle(), 0));
            if (discovered.containsKey(t.getTitle())) {
                continue;
            }
            topologicalSortRecursive(t,
                    discovered,
                    visitTasks);
        }

        for (var t : tasks) {
            for (var transpose : t.getPredecessors()) {
                transposeTasks.get(transpose.getTitle()).addPredecessor(transposeTasks.get(t.getTitle()));
            }
        }

        HashMap<String, Task> discoveredMap = new HashMap<>();
        LinkedList<String> result = new LinkedList<>();
        LinkedList<LinkedList<String>> lists = new LinkedList<>();
        for (var taskName : visitTasks) {
            Task t = transposeTasks.get(taskName);
            if (discoveredMap.containsKey(t.getTitle())) {
                continue;
            }

            LinkedList<String> list = new LinkedList<>();

            topologicalSortRecursive(t,
                    discoveredMap,
                    list);

            lists.addFirst(list);

        }

        discoveredMap.clear();
        for (var list : lists) {
            if (includeMaintenance == false && list.size() > 1) {
                continue;
            }

            if (list.size() > 1) {
                String startCircle = null;
                for (var s : list) {
                    for (var t : discovered.get(s).getPredecessors()) {
                        if (list.contains(t.getTitle()) == false) {
                            startCircle = s;
                        }
                    }
                }
                list.clear();
                Task t = transposeTasks.get(startCircle);
                topologicalSortRecursive(t,
                        discoveredMap,
                        list);

                result.addAll(list);
            } else {
                discovered.put(list.getLast(), transposeTasks.get(list.getLast()));
                result.addAll(list);
            }
        }


        return result;
    }

    private static void topologicalSortRecursive(Task task, HashMap<String, Task> discovered, LinkedList<String> visitTask) {
        discovered.put(task.getTitle(), task);

        for (var t : task.getPredecessors()) {
            if (discovered.containsKey(t.getTitle())) {
                continue;
            }

            topologicalSortRecursive(t,
                    discovered,
                    visitTask);
        }

        visitTask.addFirst(task.getTitle());
    }
}