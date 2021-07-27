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

    private LinkedList<LinkedList<String>> korsajus(Task task) {
        LinkedList<String> visitTasks = new LinkedList<>();
        HashMap<String, Task> discovered = new HashMap<>();
        LinkedList<LinkedList<String>> cycleLists = new LinkedList<>();
//
//        topologicalSortRecursive(task,
//                discovered,
//                visitTasks);
//
//        discovered.clear();
//        HashMap<String, Task> discoveredMap = new HashMap<>();
//        for (var taskName : visitTasks) {
//            Task transposeTask = transposeMap.get(taskName);
//            if (discoveredMap.containsKey(transposeTask.getTitle())) {
//                continue;
//            }
//
//            LinkedList<String> list = new LinkedList<>();
//
//            topologicalSortRecursive(transposeTask,
//                    discoveredMap,
//                    list);
//
//
//            if (list.size() > 1) {
//                String startCircle = null;
//                for (var s : list) {
//                    for (var t : taskMap.get(s).getPredecessors()) {
//                        if (list.contains(t.getTitle()) == false) {
//                            startCircle = s;
//                            break;
//                        }
//                    }
//                    if (startCircle != null) {
//                        break;
//                    }
//                }
//
//                list.clear();
//                topologicalSortRecursive(transposeMap.get(startCircle),
//                        discovered,
//                        list);
//
//                cycleLists.addFirst(list);
//            }
//        }

        return cycleLists;
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

//    public int findTotalManMonths1(final String task) {
//        if (taskMap.containsKey(task) == false) {
//            return -1;
//        }
//
//        int manMonths = 0;
//
//        LinkedList<String> visitTasks = new LinkedList<>();
//        HashMap<String, Task> discovered = new HashMap<>();
//        LinkedList<LinkedList<String>> cycleLists = new LinkedList<>();
//
//        topologicalSortRecursive(taskMap.get(task),
//                discovered,
//                null,
//                visitTasks);
//
//        discovered.clear();
//        HashMap<String, Task> discoveredMap = new HashMap<>();
//        for (var taskName : visitTasks) {
//            Task transposeTask = transposeMap.get(taskName);
//            if (task == transposeTask.getTitle()) {
//                manMonths += transposeTask.getEstimate();
//                discoveredMap.put(transposeTask.getTitle(), transposeTask);
//                LinkedList<String> list = new LinkedList<>();
//                list.add(transposeTask.getTitle());
//                cycleLists.addFirst(list);
//                continue;
//            }
//            if (discoveredMap.containsKey(transposeTask.getTitle())) {
//                continue;
//            }
//
//            LinkedList<String> list = new LinkedList<>();
//
//            topologicalSortRecursive(transposeTask,
//                    discoveredMap,
//                    list);
//
//            cycleLists.addFirst(list);
//
//            if (list.size() == 1) {
//                manMonths += taskMap.get(list.getFirst()).getEstimate();
//            }
//        }
//        return manMonths;
//    }

    public int findTotalManMonths(final String task) {
        HashMap<String, Task> discovered = new HashMap<>();
        LinkedList<String> visitTasks = new LinkedList<>();
        int manMonth = 0;

        topologicalSortRecursive(taskMap.get(task),
                discovered,
                null,
                visitTasks);

        HashMap<String, Task> discoveredMap = new HashMap<>();
        LinkedList<String> result = new LinkedList<>();
        LinkedList<LinkedList<String>> lists = new LinkedList<>();
        for (var taskName : visitTasks) {
            Task t = transposeMap.get(taskName);
            if (discoveredMap.containsKey(t.getTitle())) {
                continue;
            }
            if (discovered.containsKey(t.getTitle()) == false) {
                continue;
            }

            LinkedList<String> list = new LinkedList<>();

            topologicalSortRecursive(t,
                    discoveredMap,
                    discovered,
                    list);

            lists.addFirst(list);

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