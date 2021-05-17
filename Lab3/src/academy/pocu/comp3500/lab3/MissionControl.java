package academy.pocu.comp3500.lab3;

import java.util.ArrayList;

public final class MissionControl {
    private MissionControl() {
    }

    public static int findMaxAltitudeTime(final int[] altitudes) {
        if (altitudes.length == 0) {
            return -1;
        } else if (altitudes.length == 1) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        int left = 0;
        int right = altitudes.length - 1;
        int mid = (left + right) / 2;

        if (altitudes[mid] > altitudes[left] && altitudes[mid] > altitudes[right]) {
            while (left < right) {
                if (altitudes[mid + 1] > altitudes[mid]) {
                    left = mid + 1;
                    mid = (left + right) / 2;
                    maxIndex = mid;
                } else {
                    right = mid;
                    mid = (left + right) / 2;
                    maxIndex = mid;
                }
            }

        } else if (altitudes[left] > altitudes[right]) {
            return 0;
        } else if (altitudes[right] > altitudes[left]) {
            return altitudes.length - 1;
        }

        return maxIndex;
    }

    public static ArrayList<Integer> findAltitudeTimes(final int[] altitudes, final int targetAltitude) {
        ArrayList<Integer> bounds = new ArrayList<>();
            for (int index = 0; index < altitudes.length; index++) {
                if (targetAltitude == altitudes[index]) {
                    bounds.add(index);
                }
            }
        return bounds;
    }
}