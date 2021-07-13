package academy.pocu.comp3500.lab9;

import academy.pocu.comp3500.lab9.data.Task;

public class ProfitCalculator {
    public static int findMaxProfit(final Task[] tasks, final int[] skillLevels) {
        quickSort(tasks);

        int profit = 0;

        for (int i = 0; i < skillLevels.length; ++i) {
            for (int j = 0; j < tasks.length; ++j) {
                if (tasks[j].getDifficulty() <= skillLevels[i]) {
                    profit += tasks[j].getProfit();
                    break;
                }
            }
        }

        return profit;
    }


    private static void quickSort(final Task[] tasks) {
        quickSortRecursive(tasks, 0, tasks.length - 1);
    }

    private static void quickSortRecursive(Task[] tasks, int left, int right) {
        if (left > right) {
            return;
        }

        int pivot = getPivot(tasks, left, right);

        quickSortRecursive(tasks, left, pivot - 1);
        quickSortRecursive(tasks, pivot + 1, right);
    }

    private static int getPivot(Task[] tasks, int left, int right) {
        int i = left - 1;

        for (int index = left; index < right; ++index) {
            if (tasks[right].getProfit() < tasks[index].getProfit()) {
                swap(tasks, ++i, index);
            }
        }

        swap(tasks, ++i, right);

        return i;
    }

    private static void swap(Task[] tasks, int pos1, int pos2) {
        Task temp = tasks[pos1];
        tasks[pos1] = tasks[pos2];
        tasks[pos2] = temp;
    }
}