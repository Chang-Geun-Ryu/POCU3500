package academy.pocu.comp3500.lab7;

import java.util.ArrayList;
import java.util.HashMap;

public class Decryptor {
    private HashMap<String, ArrayList<String>> hashMap = new HashMap<>();

    public Decryptor(final String[] codeWords) {
        for (String str : codeWords) {
            str = str.toLowerCase();
            String sortWord = quickSort(str);

            if (hashMap.containsKey(sortWord)) {
                hashMap.get(sortWord).add(str);
            } else {
                ArrayList<String> arrString = new ArrayList<>();
                arrString.add(str);
                hashMap.put(sortWord, arrString);
            }
        }
    }

    public String[] findCandidates(final String word) {
        if (word == null) {
            return new String[]{};
        }

        String sortWord = quickSort(word.toLowerCase());

        if (hashMap.containsKey(sortWord)) {
            ArrayList<String> arrString = hashMap.get(sortWord);
            return arrString.toArray(new String[arrString.size()]);
        } else {
            return new String[]{};
        }
    }


    private String quickSort(String word) {
        char[] chars = word.toCharArray();
        quickSortRecursive(chars, 0, chars.length - 1);
        return String.valueOf(chars);
    }

    private void quickSortRecursive(char[] chars, int left, int right) {
        if (left > right) {
            return;
        }

        int pivotPos = getPivot(chars, left, right);

        quickSortRecursive(chars, left, pivotPos - 1);
        quickSortRecursive(chars, pivotPos + 1, right);
    }

    private int getPivot(char[] chars, int left, int right) {
//        int i = left - 1;
//
//        for (int index = left; index < right; ++index) {
//            if (chars[right] > chars[index]) {
//                swap(chars, index, ++i);
//            }
//        }
//
//        swap(chars, right, i);
//        return i;
        int pivot = right;
        int i = left;

        for (int index = left; index < right; ++index) {
            if (chars[pivot] > chars[index]) {
                char temp = chars[index];
                chars[index] = chars[i];
                chars[i++] = temp;
            }
        }

        char temp = chars[pivot];
        chars[pivot] = chars[i];
        chars[i] = temp;

        return i;
    }

    private void swap(char[] chars, int pos1, int pos2) {
        char temp = chars[pos1];
        chars[pos1] = chars[pos2];
        chars[pos2] = temp;
    }
}