package academy.pocu.comp3500.lab7;

import java.util.ArrayList;
import java.util.HashMap;

public class DecryptorHash {
    private HashMap<Integer, ArrayList<String>> hashMap = new HashMap<>();
    private int[] primes = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};

    public String[] findCandidates(String cat) {
        return new String[]{};
    }

//    public DecryptorHash(final String[] codeWords) {
//
//        for (String str : codeWords) {
//            int value = getSumAscii(str);
//            if (hashMap.containsKey(value)) {
//                hashMap.get(value).add(str);
//            } else {
//                ArrayList<String> arrString = new ArrayList<>();
//                arrString.add(str);
//                hashMap.put(getSumAscii(str), arrString);
//            }
//        }
//    }
//
//    public String[] findCandidates(final String word) {
//        if (word == null) {
//            return new String[]{};
//        }
//
//        int key = getSumAscii(word);
//
//        if (hashMap.containsKey(key)) {
//            ArrayList<String> arrString = hashMap.get(getSumAscii(word));
//            return arrString.toArray(new String[arrString.size()]);
//        } else {
//            return new String[]{};
//        }
//    }
//    
//    private String quickSort(String word) {
//        char[] chars = word.toCharArray();
//        quickSortRecursive(chars, 0, chars.length - 1);
//        return chars.toString();
//    }
//    
//    private void quickSortRecursive(char[] chars, int left, int right) {
//        if (left > right) {
//            return;
//        }
//        
//        int pivotPos = getPivot();
//        
//        quickSortRecursive(chars, left, pivotPos - 1);
//        quickSortRecursive(chars, pivotPos + 1, right);
//    }
//    
//    private int getPivot() {
//        return 0;
//    }
}