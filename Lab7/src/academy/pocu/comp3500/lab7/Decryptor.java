package academy.pocu.comp3500.lab7;

import java.util.ArrayList;
import java.util.HashMap;

public class Decryptor {
    private HashMap<Integer, ArrayList<String>> hashMap = new HashMap<>();

    public Decryptor(final String[] codeWords) {
        for (String str : codeWords) {
            int value = getSumAscii(str);
            if (hashMap.containsKey(value)) {
                hashMap.get(value).add(str);
            } else {
                ArrayList<String> arrString = new ArrayList<>();
                arrString.add(str);
                hashMap.put(getSumAscii(str), arrString);
            }
        }
    }

    public String[] findCandidates(final String word) {
        if (word == null) {
            return new String[]{};
        }

        int key = getSumAscii(word);

        if (hashMap.containsKey(key)) {
            ArrayList<String> arrString = hashMap.get(getSumAscii(word));
            return arrString.toArray(new String[arrString.size()]);
        } else {
            return new String[]{};
        }
    }

    private int getSumAscii(String str) {
        int asciiSum = 0;
        for (int i = 0; i < str.length(); i++) {
            asciiSum += (int) str.charAt(i);
        }
        return asciiSum;
    }
}