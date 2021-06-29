package academy.pocu.comp3500.lab7;

import java.util.HashMap;

public class TrieNode {
    HashMap<Character, TrieNode> childMap = new HashMap<>();
    private boolean endWord;
    private Character value;
    HashMap<String, String> words = new HashMap<>();

    public TrieNode(char value, boolean endWord) {
        this.value = value;
        this.endWord = endWord;
    }

    public char getValue() {
        return this.value;
    }

    public boolean isEndWord() {
        return this.endWord;
    }

    public void setEndWord(boolean isEnd) {
        this.endWord = isEnd;
    }

    public void addWord(String words) {
        this.words.put(words, words);
    }

    public TrieNode getChild(char value) {
        return childMap.get(value);
    }

    public void addChildNode(TrieNode node) {
        if (childMap.containsKey(node.value) == false) {
            childMap.put(node.value, node);
        }
    }


}
