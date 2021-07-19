package academy.pocu.comp3500.lab10;

import academy.pocu.comp3500.lab10.project.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Project {
    public static List<String> findSchedule(final Task[] tasks, final boolean includeMaintenance) {

        HashMap<String, Task> discovered = new HashMap<>();
        LinkedList<String> sortedList = new LinkedList<>();

        for (var t : tasks) {
            if (discovered.containsKey(t.getTitle())) {
                continue;
            }
            LinkedList<String> list = new LinkedList<>();
            boolean temp = topologicalSortRecursive(0,
                    t,
                    t,
                    discovered,
                    list);

            if (includeMaintenance) {
                sortedList.addAll(list);
            } else if (temp){
                sortedList.addAll(list);
            }
        }

        return sortedList;

    }

    private static boolean topologicalSortRecursive(int depth, Task startTask, Task task, HashMap<String, Task> discovered, LinkedList<String> linkedList) {
        if (depth  > 0 && startTask.getTitle() == task.getTitle()) {
            linkedList.addLast(task.getTitle());
            return false;
        }
        if (discovered.containsKey(task.getTitle())) {
            return true;
        }


        discovered.put(task.getTitle(), task);

        boolean isAdd = true;
        for (var t : task.getPredecessors()) {
            boolean temp = topologicalSortRecursive(depth + 1,
                    startTask,
                    t,
                    discovered,
                    linkedList);
            if (temp == false) {
                isAdd = false;
            }
//            linkedList.addLast(t.getTitle());
        }

        if (linkedList.size() > 0 && linkedList.getFirst() == task.getTitle()) {
            int i = 0;
        } else {
            linkedList.addLast(task.getTitle());
        }
//        } else {
//            linkedList.addLast(task.getTitle());
//        }


        return isAdd;
    }

}