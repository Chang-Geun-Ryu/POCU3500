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
        int left = altitudes[0];
        int right = altitudes[altitudes.length - 1];
        int mid = altitudes[altitudes.length / 2];

        if (mid > left && mid > right) {
            maxIndex = altitudes.length / 2;
            if (altitudes[altitudes.length / 2] > altitudes[altitudes.length / 2 + 1]) {
                for (int index = altitudes.length / 2; index >= 0; index-- ) {
                    if (max < altitudes[index]) {
                        maxIndex = index;
                        max = altitudes[index];
                    } else {
                        return maxIndex;
                    }
                }
            } else {
                for (int index = altitudes.length / 2; index < altitudes.length; index++) {
                    if (max < altitudes[index]) {
                        maxIndex = index;
                        max = altitudes[index];
                    } else {
                        return maxIndex;
                    }
                }
            }
        } else if (left > right) {
            return 0;
        } else if (right > left) {
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