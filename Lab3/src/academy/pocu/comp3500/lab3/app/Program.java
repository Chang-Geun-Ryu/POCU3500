package academy.pocu.comp3500.lab3.app;

import academy.pocu.comp3500.lab3.MissionControl;

import java.util.ArrayList;

public class Program {

    public static int findSecondMinimumInRotatedArray(int[] arr, int st, int end) {
        return (findMinimumInRotatedArray(arr,st,end) + 1)%arr.length;
    }
    public static int findMinimumInRotatedArray(int[] arr, int st, int end) {
        if (st > end) {
            return -1;
        }
        int mid = (st + end) / 2;
        // 시작 ~ 중간이 정렬되어있음
        if (arr[st] < arr[mid]) {
            // 튀는값이 중간값 이후에서 발생
            // 최소값은 중간값 이후에 존재할 것
            if (arr[mid] > arr[end]) {
                return findMinimumInRotatedArray(arr, mid + 1, end);
            }
            // 시작 ~ 끝 까지 정렬되어 있는 꼴
            return st;
        }
        // 중간 ~ 끝만이 정렬되어있음
        // 최소값은 중간값 아니면 중간 이전일 것
        if (arr[mid] > arr[mid - 1]) {
            return findMinimumInRotatedArray(arr, st, mid);
        }
        return mid;
    }

//    public static int search(int[] arr, int left, int right) {
//        if (left + 1 > right) {
//            return -1;
//        }
//
//        int mid = (left + right) / 2;
//
//        if ()
//
//        if (arr[left] < )
//    }

    public static int foo(int num) {
        if (num < 5) {
            return 10 - num;
        }
        int x = foo(num-1) - num;
        return x;

    }

    public static int foo1(int num, int value) {

    	if (num < 5) {
    		return 10 - num + value;
    	}
    	return foo1(num-1, value - num);

    }

    public static int bar1 (String str, int value) {
        int length = str.length();
        if (length <= 1) {
            return length + value;
        }
        return bar1(str.substring(1), value + length);
    }

    public static int bar (String str) {
        int length = str.length();
        if (length <= 1) {
            return length;
        }
        String subStr = str.substring(1);

        return length + bar(subStr);
    }

    public static int searchSecond(int[] arr) {
    	if (arr.length <= 1) {
    		return -1;
    	} else if (arr.length == 2) {
    		return arr[0] > arr[1] ? arr[0] : arr[1];
    	}
    	return arr[(searchMinRecursive(arr, 0, arr.length - 1) + 1)%arr.length];
    }

    public static int searchMinRecursive(int[] arr, int left, int right) {
    	if (left > right) {
    		return left;
    	}

        int mid = (left + right) / 2;
    	if (arr[left] > arr[right]) {
            if (arr[left] > arr[mid]) {
                return searchMinRecursive(arr, left, mid);
            } else {
                return searchMinRecursive(arr, mid+1, right);
            }
        }

    	return left;
    }


    public static int algorithm(final int[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right && array[left] > array[right]) {
//            int mid = left + (right - left) / 2;
            int mid = (left + right) / 2;
            if (array[mid] < array[left]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        int idx = (left + 1) % array.length;
        return array[idx];
    }

    public static void sort(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            int swapIndex = i;
            while (swapIndex > 0 && nums[swapIndex] > nums[swapIndex - 1]) {
                int temp = nums[swapIndex];
                nums[swapIndex] = nums[swapIndex - 1];
                nums[swapIndex - 1] = temp;
                --swapIndex;
            }
        }
    }


    public static void main(String[] args) {
	    // write your code here

        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};

        sort(nums);


        int aa1 = bar("12345");
        int bb1 = bar1("12345", 0);

        int xx = foo(100);
        int yy = foo1(100, 0);

        {
            int[][] arrs = new int[][]{
                    {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17},
                    {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 1},
                    {3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 1, 2},
                    {4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 1, 2, 3},
                    {5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 1, 2, 3, 4},
                    {6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 1, 2, 3, 4, 5},
                    {7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 1, 2, 3, 4, 5, 6},
                    {8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 1, 2, 3, 4, 5, 6, 7},
                    {9, 10, 11, 12, 13, 14, 15, 16, 17, 1, 2, 3, 4, 5, 6, 7, 8},
                    {10, 11, 12, 13, 14, 15, 16, 17, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                    {11, 12, 13, 14, 15, 16, 17, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                    {12, 13, 14, 15, 16, 17, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
                    {13, 14, 15, 16, 17, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12},
                    {14, 15, 16, 17, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13},
                    {15, 16, 17, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14},
                    {16, 17, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15},
                    {17, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16},
                    {17},
                    {17, 18, 1, 6, 7},
                    {1, 2, 17, 18, 20},
                    {11, 12, 13, 14, 15, 16, 17, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            };
            for (int[] arr : arrs) {
//                System.out.println(findSecondMinimumInRotatedArray(arr, 0, arr.length-1));
                System.out.println(searchSecond(arr));
//                System.out.println(algorithm(arr));
            }
        }

        {
            final int[] altitudes = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            int a = MissionControl.findAltitudeTime(altitudes, 1, 0, altitudes.length - 1, true);
            assert (a == 0);

            final int[] altitudes3 = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
            int aa = MissionControl.findAltitudeTime(altitudes3, 10, 0, altitudes.length - 1, false);
            assert (aa == 0);

            final int[] altitudes9 = new int[]{5};
            final int maxAltitudeTime9 = MissionControl.findAltitudeTime(altitudes9, 0, 0, altitudes9.length - 1, true);
            assert (maxAltitudeTime9 == -1);
        }
        {
            final int[] altitudes = new int[]{1, 2, 3, 4, 5, 6, 7, 4, 3, 2};
            final int maxAltitudeTime = MissionControl.findMaxAltitudeTime(altitudes);
            assert (maxAltitudeTime == 6);
            final int[] altitudes2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

            final int maxAltitudeTime2 = MissionControl.findMaxAltitudeTime(altitudes2);
            assert (maxAltitudeTime2 == 9);
            final int[] altitudes3 = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
            final int maxAltitudeTime3 = MissionControl.findMaxAltitudeTime(altitudes3);
            assert (maxAltitudeTime3 == 0);
            final int[] altitudes4 = new int[]{3, 5, 9, 8, 7, 5, 5, 4, 3, 1};
            final int maxAltitudeTime4 = MissionControl.findMaxAltitudeTime(altitudes4);
            assert (maxAltitudeTime4 == 2);
            final int[] altitudes5 = new int[]{3, 5, 9};
            final int maxAltitudeTime5 = MissionControl.findMaxAltitudeTime(altitudes5);
            assert (maxAltitudeTime5 == 2);
            final int[] altitudes6 = new int[]{9, 7, 3};
            final int maxAltitudeTime6 = MissionControl.findMaxAltitudeTime(altitudes6);
            assert (maxAltitudeTime6 == 0);
            final int[] altitudes7 = new int[]{3, 5};
            final int maxAltitudeTime7 = MissionControl.findMaxAltitudeTime(altitudes7);
            assert (maxAltitudeTime7 == 1);
            final int[] altitudes8 = new int[]{5, 3};
            final int maxAltitudeTime8 = MissionControl.findMaxAltitudeTime(altitudes8);
            assert (maxAltitudeTime8 == 0);
            final int[] altitudes9 = new int[]{5};
            final int maxAltitudeTime9 = MissionControl.findMaxAltitudeTime(altitudes9);
            assert (maxAltitudeTime9 == 0);
        }
        {
            final int[] altitudes = new int[] { 1, 8, 4, 3, 2, 1 };

            final int maxAltitudeTime = MissionControl.findMaxAltitudeTime(altitudes);

            assert (maxAltitudeTime == 1);
        }

        {
            final int[] altitudes = new int[] { 1, 2, 3, 4, 5, 6, 7};

            final int maxAltitudeTime = MissionControl.findMaxAltitudeTime(altitudes);

            assert (maxAltitudeTime == 6);
        }

        {
            final int[] altitudes = new int[] { 7,6,5,4,3,2,1};

            final int maxAltitudeTime = MissionControl.findMaxAltitudeTime(altitudes);

            assert (maxAltitudeTime == 0);
        }

        {
            final int[] altitudes = new int[] { 7};

            final int maxAltitudeTime = MissionControl.findMaxAltitudeTime(altitudes);

            assert (maxAltitudeTime == 0);
        }

        {
            final int[] altitudes = new int[] { 1, 2, 3, 4, 5, 6, 7, 4, 3, 2 };

            ArrayList<Integer> bounds = MissionControl.findAltitudeTimes(altitudes, 2);

            assert (bounds.size() == 2);

            assert (bounds.get(0) == 1);
            assert (bounds.get(1) == 9);

            bounds = MissionControl.findAltitudeTimes(altitudes, 5);

            assert (bounds.size() == 1);
            assert (bounds.get(0) == 4);
        }
        {
            final int[] altitudes = new int[]{1, 2, 3, 4, 5, 6, 7, 4, 3, 2};
            ArrayList<Integer> bounds = MissionControl.findAltitudeTimes(altitudes, 2);
            assert (bounds.size() == 2);
            assert (bounds.get(0) == 1);
            assert (bounds.get(1) == 9);
            bounds = MissionControl.findAltitudeTimes(altitudes, 5);
            assert (bounds.size() == 1);
            assert (bounds.get(0) == 4);
            final int[] altitudes2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            ArrayList<Integer> bounds2 = MissionControl.findAltitudeTimes(altitudes2, 3);
            assert (bounds2.size() == 1);
            assert (bounds2.get(0) == 2);
            bounds2 = MissionControl.findAltitudeTimes(altitudes2, 10);
            assert (bounds2.size() == 1);
            assert (bounds2.get(0) == 9);
            bounds2 = MissionControl.findAltitudeTimes(altitudes2, 1);
            assert (bounds2.size() == 1);
            assert (bounds2.get(0) == 0);
            bounds2 = MissionControl.findAltitudeTimes(altitudes2, 11);
            assert (bounds2.size() == 0);
            bounds2 = MissionControl.findAltitudeTimes(altitudes2, 0);
            assert (bounds2.size() == 0);
            final int[] altitudes3 = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
            ArrayList<Integer> bounds3 = MissionControl.findAltitudeTimes(altitudes3, 3);
            assert (bounds3.size() == 1);
            assert (bounds3.get(0) == 7);
            bounds3 = MissionControl.findAltitudeTimes(altitudes3, 10);
            assert (bounds3.size() == 1);
            assert (bounds3.get(0) == 0);
            bounds3 = MissionControl.findAltitudeTimes(altitudes3, 1);
            assert (bounds3.size() == 1);
            assert (bounds3.get(0) == 9);
            bounds3 = MissionControl.findAltitudeTimes(altitudes3, 11);
            assert (bounds3.size() == 0);
            bounds3 = MissionControl.findAltitudeTimes(altitudes3, 0);
            assert (bounds3.size() == 0);
            bounds3 = MissionControl.findAltitudeTimes(altitudes3, 8);
            assert (bounds3.size() == 1);
            assert (bounds3.get(0) == 2);
            final int[] altitudes4 = new int[]{3, 5, 3};
            ArrayList<Integer> bounds4 = MissionControl.findAltitudeTimes(altitudes4, 5);
            assert (bounds4.size() == 1);
            assert (bounds4.get(0) == 1);
            bounds4 = MissionControl.findAltitudeTimes(altitudes4, 3);
            assert (bounds4.size() == 2);
            assert (bounds4.get(0) == 0);
            assert (bounds4.get(1) == 2);
            final int[] altitudes5 = new int[]{3, 5};
            ArrayList<Integer> bounds5 = MissionControl.findAltitudeTimes(altitudes5, 5);
            assert (bounds5.size() == 1);
            assert (bounds5.get(0) == 1);
            bounds5 = MissionControl.findAltitudeTimes(altitudes5, 4);
            assert (bounds5.size() == 0);
            final int[] altitudes6 = new int[]{3};
            ArrayList<Integer> bounds6 = MissionControl.findAltitudeTimes(altitudes6, 5);
            assert (bounds6.size() == 0);
            bounds6 = MissionControl.findAltitudeTimes(altitudes6, 3);
            assert (bounds6.size() == 1);
            final int[] altitudes7 = new int[]{1, 2, 3, 4, 5, 4, 3, 2};
            ArrayList<Integer> bounds7 = MissionControl.findAltitudeTimes(altitudes7, 2);
            assert (bounds7.size() == 2);
            assert (bounds7.get(0) == 1);
            assert (bounds7.get(1) == 7);
            bounds7 = MissionControl.findAltitudeTimes(altitudes7, 1);
            assert (bounds7.size() == 1);
            assert (bounds7.get(0) == 0);
            bounds7 = MissionControl.findAltitudeTimes(altitudes7, 5);
            assert (bounds7.size() == 1);
            assert (bounds7.get(0) == 4);
            bounds7 = MissionControl.findAltitudeTimes(altitudes7, 4);
            assert (bounds7.size() == 2);
            assert (bounds7.get(0) == 3);
            assert (bounds7.get(1) == 5);
            final int[] altitudes8 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 7, 6, 5};
            ArrayList<Integer> bounds8 = MissionControl.findAltitudeTimes(altitudes8, 7);
            assert (bounds8.size() == 2);
            assert (bounds8.get(0) == 6);
            assert (bounds8.get(1) == 8);
            bounds8 = MissionControl.findAltitudeTimes(altitudes8, 5);
            assert (bounds8.size() == 2);
            assert (bounds8.get(0) == 4);
            assert (bounds8.get(1) == 10);
        }
    }
}
