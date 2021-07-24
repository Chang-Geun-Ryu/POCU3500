package academy.pocu.comp3500.lab10;

import academy.pocu.comp3500.lab10.project.Task;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Project {
    public static List<String> findSchedule(final Task[] tasks, final boolean includeMaintenance) {
        HashMap<String, Task> discovered = new HashMap<>();
        LinkedList<String> visitTasks = new LinkedList<>();

        for (var t : tasks) {
            if (discovered.containsKey(t.getTitle())) {
                continue;
            }

            discovered.put(t.getTitle(), new Task(t.getTitle(), 0));

            kosarajusRecursive(t,
                    discovered,
                    visitTasks);
        }

        HashMap<String, Task> discoveredMap = new HashMap<>();
        LinkedList<String> result = null;
        for (var taskName : visitTasks) {
            if (discoveredMap.containsKey(taskName)) {
                continue;
            }
            Task task = discovered.get(taskName);

            discoveredMap.put(taskName, task);

            LinkedList<String> list = new LinkedList<>();

            topologicalSortRecursive(task,
                    discoveredMap,
                    list);

            if (includeMaintenance == false && list.size() > 1) {
                continue;
            } else if (result == null) {
                result = list;
            } else {
                list.addAll(result);
                result = list;
            }
        }

        return result;
    }

    private static void kosarajusRecursive(Task task, HashMap<String, Task> discovered, LinkedList<String> visitTask) {
        for (var t : task.getPredecessors()) {
            if (discovered.containsKey(t.getTitle())) {
                if (discovered.containsKey(task.getTitle())) {
                    discovered.get(t.getTitle()).addPredecessor(discovered.get(task.getTitle()));
                } else {
                    discovered.get(t.getTitle()).addPredecessor(new Task(task.getTitle(), 0));
                }
                continue;
            }

            Task tempT = new Task(t.getTitle(), 0);
            tempT.addPredecessor(task);
            discovered.put(t.getTitle(), tempT);

            kosarajusRecursive(t,
                    discovered,
                    visitTask);
        }

        visitTask.addFirst(task.getTitle());
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