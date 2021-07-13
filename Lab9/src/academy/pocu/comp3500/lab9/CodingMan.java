package academy.pocu.comp3500.lab9;

import academy.pocu.comp3500.lab9.data.Task;
import academy.pocu.comp3500.lab9.data.VideoClip;

public class CodingMan {
    public static int findMinClipsCount(final VideoClip[] clips, int time) {
        if (clips.length <= 0) {
            return -1;
        }

        quickSort(clips);

        if (clips[clips.length - 1].getEndTime() - clips[0].getStartTime() < time) {
            return -1;
        }

        int preClripIndex = 0;
        int clipCount = 1;
        int partStart = clips[0].getStartTime();
        int partEnd = clips[0].getEndTime();

        if (time <= partEnd) {
            return clipCount;
        }

        for (int i = 1; i < clips.length; ++i) {

            if (partEnd < clips[i].getStartTime()) {
                continue;
            }

            if (partStart >= clips[i].getStartTime() && partEnd <= clips[i].getEndTime()) {
                partEnd = clips[i].getEndTime();
                continue;
            }

            partStart = partEnd + 1;
            partEnd = clips[i].getEndTime();
            clipCount += 1;

            if (time <= partEnd) {
                return clipCount;
            }
        }

        return time <= 0 ? clipCount : -1;
    }


    private static void quickSort(final VideoClip[] clips) {
        quickSortRecursive(clips, 0, clips.length - 1);
    }

    private static void quickSortRecursive(VideoClip[] clips, int left, int right) {
        if (left > right) {
            return;
        }

        int pivot = getPivot(clips, left, right);

        quickSortRecursive(clips, left, pivot - 1);
        quickSortRecursive(clips, pivot + 1, right);
    }

    private static int getPivot(VideoClip[] clips, int left, int right) {
        int i = left - 1;

        for (int index = left; index < right; ++index) {
            if (clips[right].getEndTime() > clips[index].getEndTime()) {
                swap(clips, ++i, index);
            }
        }

        swap(clips, ++i, right);

        return i;
    }

    private static void swap(VideoClip[] clips, int pos1, int pos2) {
        VideoClip temp = clips[pos1];
        clips[pos1] = clips[pos2];
        clips[pos2] = temp;
    }
}