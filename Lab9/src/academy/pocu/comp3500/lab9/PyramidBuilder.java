package academy.pocu.comp3500.lab9;

public class PyramidBuilder {
    public static int findMaxHeight(final int[] widths, int statue) {
        quickSort(widths);

        int height = 0;
        int temp = 0;
        int preBlockCount = 1;
        int currentBlockCount = 0;

        for (int i = 0; i < widths.length; ++i) {
            temp += widths[i];
            ++currentBlockCount;
            if (preBlockCount > 1 && statue < temp && preBlockCount < currentBlockCount) {
                statue = temp;
                preBlockCount = currentBlockCount;
                currentBlockCount = 0;
                temp = 0;
                ++height;
            } else if (preBlockCount == 1 && statue < temp && preBlockCount < currentBlockCount) {
                statue = temp;
                preBlockCount = currentBlockCount;
                currentBlockCount = 0;
                temp = 0;
                ++height;
            }
        }


        return height;
    }

    private static void quickSort(final int[] widths) {
        quickSortRecursive(widths, 0, widths.length - 1);
    }

    private static void quickSortRecursive(int[] widths, int left, int right) {
        if (left > right) {
            return;
        }

        int pivot = getPivot(widths, left, right);

        quickSortRecursive(widths, left, pivot - 1);
        quickSortRecursive(widths, pivot + 1, right);
    }

    private static int getPivot(int[] widths, int left, int right) {
        int mid = (right + left) / 2;
        int i = left - 1;

        for (int index = left; index < right; ++index) {
            if (widths[right] > widths[index]) {
                swap(widths, ++i, index);
            }
        }

        swap(widths, ++i, right);

        return i;
    }

    private static void swap(int[] widths, int pos1, int pos2) {
        int temp = widths[pos1];
        widths[pos1] = widths[pos2];
        widths[pos2] = temp;
    }
}