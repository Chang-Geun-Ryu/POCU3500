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

        time -= clips[preClripIndex].getEndTime() - clips[preClripIndex].getStartTime();

        for (int i = 1; i < clips.length; ++i) {
            if (time <= 0) {
                return clipCount;
            }

            if (clips[preClripIndex].getStartTime() >= clips[i].getStartTime()) {
                time += clips[preClripIndex].getEndTime() - clips[preClripIndex].getStartTime();
                --clipCount;
            }

            if (clips[preClripIndex].getEndTime() < clips[i].getStartTime()) {
                continue;
            }

            time -= clips[i].getEndTime() - (clips[i].getStartTime() > clips[preClripIndex].getEndTime() ? clips[i].getStartTime() : clips[preClripIndex].getEndTime());
            ++clipCount;
            preClripIndex = i;
//            if (clips[i].getStartTime() <= clips[preClripIndex].getStartTime() && clips[i].getEndTime() >= clips[preClripIndex].getEndTime()) {
//                time += clips[preClripIndex].getEndTime() - clips[preClripIndex].getStartTime();
//                time -= clips[i].getEndTime() - clips[i].getStartTime();
//            } else
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