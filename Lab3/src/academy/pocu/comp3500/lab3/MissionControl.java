package academy.pocu.comp3500.lab3;

import java.util.ArrayList;

public final class MissionControl {
    private MissionControl() {
    }

//    private static int findMaxRecursive(final int[] altitudes, int left, int right) {
//        if (left >= right) {
//            return
//        }
//        return findMaxRecursive()
//    }

    public static int findMaxAltitudeTime(final int[] altitudes) {
        if (altitudes.length == 0) {
            return -1;
        } else if (altitudes.length == 1) {
            return 0;
        }

        int maxIndex = 0;
        int left = 0;
        int right = altitudes.length - 1;
        int mid = (left + right) / 2;

        while (left < right) {
            int temp = mid + 1 <= right ? mid + 1 : right;

            if (altitudes[temp] > altitudes[mid]) {
                left = temp;
                mid = (left + right) / 2;
                maxIndex = mid;
            } else {
                right = mid;
                mid = (left + right) / 2;
                maxIndex = mid;
            }
        }

        return maxIndex;
    }

    public static ArrayList<Integer> findAltitudeTimes(final int[] altitudes, final int targetAltitude) {
        ArrayList<Integer> bounds = new ArrayList<>();
        int maxIndex = findMaxAltitudeTime(altitudes);

        if (maxIndex == 0) {
            int index = findAltitudeTime(altitudes, targetAltitude, 0, altitudes.length - 1, false);
            if (index != -1) {
                bounds.add(index);
            }
        } else if (maxIndex == altitudes.length - 1) {
            int index = findAltitudeTime(altitudes, targetAltitude, 0, altitudes.length - 1, true);
            if (index != -1) {
                bounds.add(index);
            }
        } else {
            int index = findAltitudeTime(altitudes, targetAltitude, 0, maxIndex, true);
            int index2 = findAltitudeTime(altitudes, targetAltitude, maxIndex + 1, altitudes.length - 1, false);

            if (index != -1) {
                bounds.add(index);
            }
            if (index2 != -1) {
                bounds.add(index2);
            }
        }

//        for (int index = 0; index < altitudes.length; index++) {
//            if (targetAltitude == altitudes[index]) {
//                bounds.add(index);
//            }
//        }
        return bounds;
    }

    public static int findAltitudeTime(final int[] altitudes, final int targetAltitude, int left, int right, boolean ascending) {
//        int left = 0;
//        int right = altitudes.length - 1;
        int index = (left + right) / 2;

        while (left < right) {
            if (targetAltitude == altitudes[index]) {
                return index;
            } else {
                if (ascending) {
                    if (targetAltitude > altitudes[index]) {
                        left = index + 1;
                    } else {
                        right = index;
                    }
                } else {
                    if (targetAltitude < altitudes[index]) {
                        left = index + 1;
                    } else {
                        right = index;
                    }
                }
                index = (left + right) / 2;
            }
        }

        return targetAltitude == altitudes[index] ? index : -1;
    }
}