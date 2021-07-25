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

//        if (includeMaintenance) {
//            return visitTasks;
//        }


        for (var t : tasks) {
            for (var transpose : t.getPredecessors()) {
                transposeTasks.get(transpose.getTitle()).addPredecessor(transposeTasks.get(t.getTitle()));
            }
        }

        HashMap<String, Task> discoveredMap = new HashMap<>();
        LinkedList<LinkedList<String>> result1 = new LinkedList<>();
        LinkedList<String> result = new LinkedList<>();
        Iterator<String> iterator = visitTasks.descendingIterator();
        for (var taskName : visitTasks) {
//        while (iterator.hasNext()) {
//            Task t = transposeTasks.get(iterator.next());
            Task t = transposeTasks.get(taskName);
            if (discoveredMap.containsKey(t.getTitle())) {
                continue;
            }

            LinkedList<String> list = new LinkedList<>();

            topologicalSortRecursive(t,
                    discoveredMap,
                    list);

            if (includeMaintenance) {
//                result.addAll(list);
                result.addAll(0, list);
            } else if (list.size() == 1){
                result.addFirst(t.getTitle());
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