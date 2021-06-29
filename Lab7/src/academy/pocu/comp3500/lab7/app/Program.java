package academy.pocu.comp3500.lab7.app;

import academy.pocu.comp3500.lab7.Decryptor;

import java.util.ArrayList;
import java.util.Random;

public class Program {
    private static final int CHAR_LEFT_LIMIT = 'a';
    private static final int CHAR_RIGHT_LIMIT = 'z';

    private static ArrayList<String> words = new ArrayList<>();

    public static void main(String[] args) {
        mainTest();
        for (int a = 0; a < 1000; ++a) {
            randTest();
        }

    }

    private static void randTest() {
        Random rand = new Random();

        int test = 1;
        // Generate random integers in range 0 to 999
        for (int i = 0; i < test; ++i) {
            char rand_int1 = (char)(rand.nextInt(95) + 32);

            words.add(String.valueOf(rand_int1));
        }

        for (int i = 0; i < test; ++i) {
            String[] codeWords = words.toArray(new String[words.size()]);
        }


    }

    private static void mainTest() {
        String[] codeWords = new String[]{"cat", "cats", "acts", "scan", "acre", "ants", "test blank", "bAAaCdE", "iiidinieaede", "naeenenaeiia"};

        Decryptor decryptor = new Decryptor(codeWords);

        String[] candidates = decryptor.findCandidates("cat");

        assert (candidates.length == 1);
        assert (candidates[0].equals("cat"));

        candidates = decryptor.findCandidates("naeenenaeiia");

        assert (candidates.length == 1);
        assert (candidates[0].equals("naeenenaeiia"));

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

        candidates = decryptor.findCandidates("test blank");
        assert (candidates.length == 1);
        assert (candidates[0].equals("test blank"));
        candidates = decryptor.findCandidates("testblank ");
        assert (candidates.length == 1);
        assert (candidates[0].equals("test blank"));
        candidates = decryptor.findCandidates(" testblank");
        assert (candidates.length == 1);
        assert (candidates[0].equals("test blank"));
        candidates = decryptor.findCandidates("t estblank");
        assert (candidates.length == 1);
        assert (candidates[0].equals("test blank"));

        candidates = decryptor.findCandidates("bAAaCdE");
        assert (candidates.length == 1);
        assert (candidates[0].equals("bAAaCdE"));
    }
}
