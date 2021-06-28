package academy.pocu.comp3500.lab7.app;

import academy.pocu.comp3500.lab7.Decryptor;

import java.util.ArrayList;
import java.util.Random;

public class Program {
    private static final int CHAR_LEFT_LIMIT = 'a';
    private static final int CHAR_RIGHT_LIMIT = 'z';

    public static void main(String[] args) {
        mainTest();
        myTest();
    }

    private static void mainTest() {
        String[] codeWords = new String[]{"cat", "cats", "acts", "scan", "acre", "ants"};

        Decryptor decryptor = new Decryptor(codeWords);

        String[] candidates = decryptor.findCandidates("cat");

        assert (candidates.length == 1);
        assert (candidates[0].equals("cat"));

        candidates = decryptor.findCandidates("race");

        assert (candidates.length == 1);
        assert (candidates[0].equals("acre"));

        candidates = decryptor.findCandidates("ca");

        assert (candidates.length == 0);

        candidates = decryptor.findCandidates("span");

        assert (candidates.length == 0);

        candidates = decryptor.findCandidates("act");

        assert (candidates.length == 1);
        assert (candidates[0].equals("cat"));

        candidates = decryptor.findCandidates("cats");

        assert (candidates.length == 2);
        assert (candidates[0].equals("cats") || candidates[0].equals("acts"));
        assert (candidates[1].equals("cats") || candidates[1].equals("acts"));

        candidates = decryptor.findCandidates("scat");

        assert (candidates.length == 2);
        assert (candidates[0].equals("cats") || candidates[0].equals("acts"));
        assert (candidates[1].equals("cats") || candidates[1].equals("acts"));
    }

    private static void myTest() {
        Random random = new Random();
        final int upperBound = 10000;
        int wordsCount = random.nextInt(upperBound);
//        int wordsCount = upperBound;
        ArrayList<ArrayList<String>> words = new ArrayList<>();
        System.out.printf("String[] words = new String[%d];%s", wordsCount, System.lineSeparator());

        for (int i = 0; i < wordsCount; ++i) {
            System.out.println("[" + i + "]");
            String newString;
            boolean isAnagram = random.nextInt(10) == 0 && words.size() > 0;
            if (isAnagram) {
                System.out.println("isAnagram");
                int anagramIndex;
                do {
                    anagramIndex = random.nextInt(words.size());
                } while (words.get(anagramIndex).size() == words.get(anagramIndex).get(0).length());
                String anagram = getAnagram(words.get(anagramIndex).get(0));
                if (!words.get(anagramIndex).contains(anagram)) {
                    words.get(anagramIndex).add(anagram);
                    System.out.printf("words[%d] = \"%s\"; // this is anagram of %s%s", i, anagram, words.get(anagramIndex).get(0), System.lineSeparator());
                    continue;
                }
            }

            boolean hasWord;
            do {
                hasWord = false;
                int wordLength = random.nextInt(20) + 1;
                newString = generateRandomString(wordLength);

                for (ArrayList<String> word : words) {
                    if (isAnagram(newString, word.get(0))) {
                        hasWord = true;
                        break;
                    }
                }
            } while (hasWord);

            words.add(new ArrayList<>());
            words.get(words.size() - 1).add(newString);
            System.out.printf("words[%d] = \"%s\";%s", i, newString, System.lineSeparator());
        }

        for (ArrayList<String> anagrams : words) {
        for (String word : anagrams) {
        System.out.printf("%s\t", word);
        }
        System.out.println();
        }

        System.out.println();

        ArrayList<String> codeWords = new ArrayList<>();
        for (ArrayList<String> strings : words) {
            codeWords.addAll(strings);
        }

        String[] result = new String[codeWords.size()];
        for (int i = 0; i < result.length; ++i) {
            result[i] = codeWords.get(i);
        }

        Decryptor decryptor = new Decryptor(result);
        System.out.println("Decryptor decryptor = new Decryptor(words);");
        System.out.println();

        for (ArrayList<String> word : words) {
            System.out.println("{");
            String[] candidates = decryptor.findCandidates(word.get(0));
            System.out.printf("\tString[] candidates = decryptor.findCandidates(\"%s\");%s", word.get(0), System.lineSeparator());
            System.out.println("word: " + word.get(0));for (String candidate : candidates) { System.out.printf("candidate: %s%s", candidate, System.lineSeparator()); }

            System.out.printf("\tassert (candidates.length == %d);%s", word.size(), System.lineSeparator());
            assert (candidates.length == word.size());

            System.out.println("\tboolean hasCandidate = false;");
            for (String candidate : candidates) {
                boolean hasCandidate = false;
                for (String anagram : word) {
                    if (candidate.equals(anagram)) {
                        System.out.printf("\tif (\"%s\".equals(\"%s\")) {%s", candidate, anagram, System.lineSeparator());
                        System.out.println("\t\thasCandidate = true;");
                        System.out.println("\t}");
                        hasCandidate = true;
                        break;
                    }
                }

                System.out.println("\tassert hasCandidate;");
                assert hasCandidate;
            }
            System.out.println("}");
        }
    }

    private static String generateRandomString(int length) {
        return new Random().ints(CHAR_LEFT_LIMIT, CHAR_RIGHT_LIMIT + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private static boolean isAnagram(final String lhs, final String rhs) {
        if (lhs.length() != rhs.length()) {
            return false;
        }

        boolean[] tracker = new boolean[lhs.length()];
        for (int i = 0; i < lhs.length(); ++i) {
            boolean hasCharacter = false;
            for (int j = 0; j < rhs.length(); ++j) {
                if (!tracker[j] && lhs.charAt(i) == rhs.charAt(j)) {
                    tracker[j] = true;
                    hasCharacter = true;
                    break;
                }
            }
            if (!hasCharacter) {
                return false;
            }
        }

        return true;
    }

    private static String getAnagram(final String word) {
        StringBuilder newWord;
        int tryCount = 5;
        do {
            boolean[] tracker = new boolean[word.length()];
            newWord = new StringBuilder();

            boolean isDone;
            Random random = new Random();
            do {
                isDone = true;
                int randomIndex;
                do {
                    randomIndex = random.nextInt(word.length());
                } while (tracker[randomIndex]);
                newWord.append(word.charAt(randomIndex));
                tracker[randomIndex] = true;

                for (boolean track : tracker) {
                    if (!track) {
                        isDone = false;
                        break;
                    }
                }
            } while (!isDone);
        } while (newWord.toString().equals(word) && tryCount-- > 0);

        return newWord.toString();
    }
}
