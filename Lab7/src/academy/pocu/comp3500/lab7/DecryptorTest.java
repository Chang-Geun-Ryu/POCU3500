package academy.pocu.comp3500.lab7;

import java.util.HashMap;

public class DecryptorTest {

    private TrieNode root = new TrieNode('\0', false);

    public DecryptorTest(final String[] codeWords) {

        for (String str : codeWords) {
            str = str.toLowerCase();
            String sortedWord = quickSort(str);

            int index = 0;
            addChars(root, sortedWord, str, 0);
        }
    }

    private void addChars(TrieNode node, String sortedWord, String word, int index) {
        if (sortedWord.length() == index) {
            return;
        }

        char value = sortedWord.charAt(index);
        boolean isEnd = index == sortedWord.length() - 1;

        TrieNode child = node.getChild(value);
        if (child == null) {
            child = new TrieNode(value, isEnd);
            node.addChildNode(child);
        }

        if (isEnd) {
            child.addWord(word);
        }

        addChars(child, sortedWord, word, ++index);
    }

    public String[] findCandidates(final String word) {
        if (word == null) {
            return new String[]{};
        }

        String sortWord = quickSort(word);

        HashMap<String, String> map = findWords(root, sortWord, 0);

        if (map == null) {
            return new String[]{};
        }


        return new String[]{};
    }

    private HashMap<String, String> findWords(TrieNode node, String words, int index) {
        if (node.childMap.isEmpty()) {
            return null;
        } else if (index >= words.length()) {
            return null;
        }

        char value = words.charAt(index);
        TrieNode child = node.childMap.get(value);
        if (child == null) {
            return null;
        }

        if (index == words.length() - 1 && child.isEndWord()) {
            return child.words;
        }

        return findWords(child, words, ++index);
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
        int pivot = right;
        int i = left - 1;

        for (int index = left; index < right; ++index) {
            if (chars[pivot] > chars[index]) {
                char temp = chars[index];
                chars[index] = chars[++i];
                chars[i] = temp;
            }
        }

        char temp = chars[pivot];
        chars[pivot] = chars[++i];
        chars[i] = temp;

        return i;
    }

    private void swap(char[] chars, int pos1, int pos2) {
        char temp = chars[pos1];
        chars[pos1] = chars[pos2];
        chars[pos2] = temp;
    }
}
