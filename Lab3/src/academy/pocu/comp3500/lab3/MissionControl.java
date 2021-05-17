package academy.pocu.comp3500.lab3;

import java.util.ArrayList;

public final class MissionControl {
    private MissionControl() {
    }

    public static int findMaxAltitudeTime(final int[] altitudes) {
        int max = -1;
        for (int index = 0; index < altitudes.length; ++index) {
            if (max < altitudes[index]) {
                max = index;
            }
        }
        return max;
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