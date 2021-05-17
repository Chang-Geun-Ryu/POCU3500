package academy.pocu.comp3500.lab3;

import java.util.ArrayList;

public final class MissionControl {
    private MissionControl() {
    }

    public static int findMaxAltitudeTime(final int[] altitudes) {
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int index = 0; index < altitudes.length / 2; index++) {
            if (max < altitudes[index]) {
                maxIndex = index;
                max = altitudes[index];
            }

            if (max < altitudes[altitudes.length - 1 - index]) {
                maxIndex = altitudes.length - 1 - index;
                max = altitudes[altitudes.length - 1 - index];
            }
        }
        return maxIndex;
    }

    public static ArrayList<Integer> findAltitudeTimes(final int[] altitudes, final int targetAltitude) {
        ArrayList<Integer> bounds = new ArrayList<>();
        for (int index = 0; index < altitudes.length; ++index) {
            if (targetAltitude == altitudes[index]) {
                bounds.add(index);
            }
        }
        return bounds;
    }
}