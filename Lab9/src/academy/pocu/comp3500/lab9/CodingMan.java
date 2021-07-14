package academy.pocu.comp3500.lab9;

import academy.pocu.comp3500.lab9.data.VideoClip;

import java.util.LinkedList;
import java.util.Stack;

public class CodingMan {
    public static int findMinClipsCount(final VideoClip[] clips, int time) {
        if (clips.length <= 0) {
            return -1;
        }

        quickSort(clips);

        LinkedList<VideoClip> list = new LinkedList<>();
        list.add(clips[0]);

        if (list.getLast().getStartTime() == 0 && list.getLast().getEndTime() >= time) {
            return list.size();
        }

        if (list.getFirst().getStartTime() != 0) {
            return -1;
        }

        for (int i = 1; i < clips.length; ++i) {
            if (list.getLast().getEndTime() < clips[i].getStartTime()) {
                break;
            } else if (list.getLast().getEndTime() >= clips[i].getEndTime()) {
                continue;
            }

            list.addLast(clips[i]);

            if (list.getFirst().getStartTime() == 0 && list.getLast().getEndTime() >= time) {
                return list.size();
            }
        }

        return -1;
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
            if (clips[right].getStartTime() > clips[index].getStartTime()) {
                swap(clips, ++i, index);
            } else if (clips[right].getStartTime() == clips[index].getStartTime() && clips[right].getEndTime() - clips[right].getStartTime() < clips[index].getEndTime() - clips[index].getStartTime()) {
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

    public static int findMinClipsCount1(final VideoClip[] clips, int time) {
        if (clips.length <= 0) {
            return -1;
        }

        quickSort(clips);

        Stack<VideoClip> stack = new Stack<>();
        stack.push(clips[0]);

        if (stack.peek().getStartTime() == 0 && stack.peek().getEndTime() >= time) {
            return stack.size();
        }

        for (int i = 1; i < clips.length; ++i) {
            while (stack.size() > 0 && stack.peek().getStartTime() >= clips[i].getStartTime()) {
                stack.pop();
            }

            if (stack.size() == 0) {
                stack.push(clips[i]);
                continue;
            }

            if (stack.peek().getEndTime() >= clips[i].getStartTime() && stack.peek().getEndTime() < time) {
                stack.push(clips[i]);
            }

            if (stack.size() == 1 && stack.peek().getEndTime() >= time) {
                return 1;
            }
        }

        return stack.peek().getEndTime() >= time ? stack.size() : -1;
    }
    public static int findMinClipsCount2(final VideoClip[] clips, int time) {
        if (clips.length <= 0) {
            return -1;
        }

        quickSort(clips);

        LinkedList<VideoClip> list = new LinkedList<>();
        list.add(clips[0]);

        if (list.getLast().getStartTime() == 0 && list.getLast().getEndTime() >= time) {
            return list.size();
        }

        for (int i = 1; i < clips.length; ++i) {
            while (list.size() > 0 && list.getLast().getStartTime() >= clips[i].getStartTime()) {
                list.removeLast();
            }

            if (list.size() == 0) {
                list.addLast(clips[i]);
                continue;
            }

            if (list.getLast().getEndTime() >= clips[i].getStartTime() && list.getLast().getEndTime() < time) {
                list.addLast(clips[i]);
            }

            if (list.size() == 1 && list.getLast().getEndTime() >= time) {
                return 1;
            }
        }

        if (list.getFirst().getStartTime() != 0) {
            return -1;
        }

        return list.getLast().getEndTime() >= time ? list.size() : -1;
    }
}