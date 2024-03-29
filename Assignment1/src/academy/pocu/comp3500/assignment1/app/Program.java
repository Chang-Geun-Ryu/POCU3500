package academy.pocu.comp3500.assignment1.app;

import academy.pocu.comp3500.assignment1.PocuBasketballAssociation;
import academy.pocu.comp3500.assignment1.pba.Player;
import academy.pocu.comp3500.assignment1.pba.GameStat;

import java.util.HashMap;

public class Program {

    public static void quickSort(int[] arr) {
        quickSortRecursive(arr, 0, arr.length - 1);
    }

    public static void quickSortRecursive(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int pos = pivotPos(arr, left, right);

        quickSortRecursive(arr, left, pos - 1);
        quickSortRecursive(arr, pos + 1, right);
    }

    public static int pivotPos(int[] arr, int left, int right) {
        int pivot = right;
        int i = left;

        for (int index = left; index < right; ++index) {
            if (arr[pivot] > arr[index]) {
                int temp = arr[index];
                arr[index] = arr[i];
                arr[i++] = temp;
            }
        }

        int temp = arr[pivot];
        arr[pivot] = arr[i];
        arr[i] = temp;

        return i;
    }

    public static void insertSort(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            int index = i;

            while (index > 0 && arr[index - 1] > arr[index]) {
                    int temp = arr[index];
                    arr[index] = arr[index - 1];
                    arr[index - 1] = temp;
                    index -= 1;
            }
        }
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; ++i) {
            for (int j = 0; j < arr.length - i - 1; ++j) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            int swapIndex = i;
            for (int j = i; j < arr.length; ++j) {
                if (arr[swapIndex] > arr[j]) {
                    swapIndex = j;
                }
            }
            swap(arr, swapIndex, i);
        }
    }

    public static void swap(int[] arr, int pos1, int pos2) {
        int temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }

    public static void sort(int[] nums) {

    	for (int i = 0; i < nums.length; ++i) {

    		int swapIndex = i;

    		for (int j = i; j < nums.length; ++j) {

    			if (nums[swapIndex] > nums[j]) {

    				swapIndex = j;

    			}

    		}

    		int temp = nums[i];

    		nums[i] = nums[swapIndex];

    		nums[swapIndex] = temp;

    	}

    }

    public static void main(String[] args) {
        int[] arr = { 12, 8, 3, 4, 9, 1, 7};

        insertSort(arr);
//        quickSort(arr);
//        bubbleSort(arr);
//        selectSort(arr);
//        sort(arr);
        System.out.println(arr);

//        {
//            {
//                GameStat[] gameStats = new GameStat[]{
//                        new GameStat("Player 2", 11, 5, 2, 5, 0, 10),
//                        new GameStat("Player 2", 4, 3, 1, 3, 12, 2),
//                        new GameStat("Player 2", 3, 9, 3, 3, 1, 11),
//                        new GameStat("Player 2", 7, 5, 1, 3, 1, 9),
//                };
//                Player[] players = new Player[]{
//                        new Player()
//                };
//                PocuBasketballAssociation.processGameStats(gameStats, players);
//                Player player = getPlayerOrNull(players, "Player 2");
//                assert (player != null);
//                assert (player.getPointsPerGame() == 5);
//                assert (player.getAssistsPerGame() == 3);
//                assert (player.getPassesPerGame() == 8);
//                assert (player.getShootingPercentage() == 50);
//            }
//
//        }
//        {
//            GameStat[] gameStats = new GameStat[] {
//                    new GameStat("Player 1", 1, 13, 5, 6, 10, 1),
////                    new GameStat("Player 2", 2, 5, 2, 5, 0, 10),
//                    new GameStat("Player 1", 3, 12, 6, 9, 8, 5),
////                    new GameStat("Player 3", 1, 31, 15, 40, 5, 3),
////                    new GameStat("Player 2", 1, 3, 1, 3, 12, 2),
////                    new GameStat("Player 1", 2, 11, 6, 11, 9, 3),
////                    new GameStat("Player 2", 3, 9, 3, 3, 1, 11),
////                    new GameStat("Player 3", 4, 32, 15, 51, 4, 2),
////                    new GameStat("Player 4", 3, 44, 24, 50, 1, 1),
////                    new GameStat("Player 1", 4, 11, 5, 14, 8, 3),
////                    new GameStat("Player 2", 4, 5, 1, 3, 1, 9),
//            };
//
//            Player[] players = new Player[] {
//                    new Player(),
//                    new Player(),
//                    new Player(),
//                    new Player()
//            };
//
//            PocuBasketballAssociation.processGameStats(gameStats, players);
//
//            for (Player p: players) {
//                System.out.print("getName: " + p.getName());
//                System.out.print(", getPointsPerGame: " + p.getPointsPerGame());
//                System.out.print(", getAssistsPerGame: " + p.getAssistsPerGame());
//                System.out.print(", getPassesPerGame: " + p.getPassesPerGame());
//                System.out.println(", getShootingPercentage: " + p.getShootingPercentage());
//            }
//
//        }
//
//        {
//            Player[] players = new Player[] {
//                    new Player("Player 1", 1, 5, 1, 60),
//                    new Player("Player 2", 5, 2, 11, 31),
//                    new Player("Player 3", 7, 4, 7, 44),
//                    new Player("Player 4", 10, 10, 15, 25),
//                    new Player("Player 5", 11, 12, 6, 77),
//                    new Player("Player 6", 15, 0, 12, 61),
//                    new Player("Player 7", 16, 8, 2, 70)
//            };
//
//            Player player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 12); // player: Player 5
////            System.out.println("playerName: " + player.getName() + " , pointperGame: " + player.getPointsPerGame());
//            player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 5); // player: Player 2
////            System.out.println("playerName: " + player.getName() + " , pointperGame: " + player.getPointsPerGame());
//            player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 13); // player: Player 6
////            System.out.println("playerName: " + player.getName() + " , pointperGame: " + player.getPointsPerGame());
//        }
//        {
//            Player[] players = new Player[] {
//                    new Player("Player 4", 10, 10, 15, 25),
//                    new Player("Player 2", 5, 2, 11, 31),
//                    new Player("Player 3", 7, 4, 7, 44),
//                    new Player("Player 1", 1, 5, 1, 60),
//                    new Player("Player 6", 15, 0, 12, 61),
//                    new Player("Player 7", 16, 8, 2, 70),
//                    new Player("Player 5", 11, 12, 6, 77)
//            };
//
//            Player player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 28); // player: Player 2
////            System.out.println("playerName: " + player.getName() + " , pointperGame: " + player.getShootingPercentage());
//            player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 58); // player: Player 1
////            System.out.println("playerName: " + player.getName() + " , pointperGame: " + player.getShootingPercentage());
//            player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 72); // player: Player 7\
////            System.out.println("playerName: " + player.getName() + " , pointperGame: " + player.getShootingPercentage());
//        }
//

        {
            Player player111111 = new Player("Player 2", 5, 12, 14, 50);
            HashMap<String, Player> hashMap = new HashMap<>();
            hashMap.put(player111111.getName(), player111111);

            Player p = hashMap.remove(player111111.getName());


            if (p != null) {
                int i = 0;
            }
        }
        {
            Player[] players = new Player[] {
                    new Player("Player 2", 5, 12, 14, 50),
                    new Player("Player 6", 15, 2, 5, 40),
                    new Player("Player 5", 11, 1, 11, 54),
                    new Player("Player 4", 10, 3, 51, 88),
                    new Player("Player 7", 16, 8, 5, 77),
                    new Player("Player 1", 1, 15, 2, 22),
                    new Player("Player 3", 7, 5, 8, 66)
            };

            Player[] outPlayers = new Player[3];
            Player[] scratch = new Player[3];

            long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch); // maxTeamwork: 219, outPlayers: [ Player 4, Player 2, Player 3 ]
            for (Player p: players) {
                System.out.println("getName" + p.getName() + ", Assists: " +p.getAssistsPerGame() + ", Passe: " + p.getPassesPerGame() + ", score: " + p.getAssistsPerGame()*p.getPassesPerGame());
            }

            System.out.println("maxTeamwork: " + maxTeamwork);
            for (Player p: outPlayers) {
                System.out.println("name" + p.getName());
            }
        }
        {
            Player[] players = new Player[] {
                    new Player("Player 2", 5, 5, 17, 50),
                    new Player("Player 6", 15, 4, 10, 40),
                    new Player("Player 5", 11, 3, 25, 54),
                    new Player("Player 4", 10, 9, 1, 88),
                    new Player("Player 7", 16, 7, 5, 77),
                    new Player("Player 1", 1, 2, 8, 22),
                    new Player("Player 9", 42, 15, 4, 56),
                    new Player("Player 8", 33, 11, 3, 72),
            };

            int k = 4;
            Player[] outPlayers = new Player[4];
            Player[] scratch = new Player[k];

            long maxTeamwork = PocuBasketballAssociation.findDreamTeam(players, k, outPlayers, scratch);
            // maxTeamwork: 171, outPlayers: [ Player 6, Player 5, Player 2, Player 7 ]

            for (Player p: players) {
                System.out.println("getName" + p.getName() + ", Assists: " +p.getAssistsPerGame() + ", Passe: " + p.getPassesPerGame() + ", score: " + p.getAssistsPerGame()*p.getPassesPerGame());
            }

            System.out.println("maxTeamwork: " + maxTeamwork);
            for (Player p: outPlayers) {
                System.out.println("name" + p.getName());
            }
        }
        {
            Player[] players = new Player[] {
                    new Player("Player 1", 2, 5, 10, 78),
                    new Player("Player 2", 10, 4, 5, 66),
                    new Player("Player 3", 3, 3, 2, 22),
                    new Player("Player 4", 1, 9, 8, 12),
                    new Player("Player 5", 11, 1, 12, 26),
                    new Player("Player 6", 7, 2, 10, 15),
                    new Player("Player 7", 8, 15, 3, 11),
                    new Player("Player 8", 5, 7, 13, 5),
                    new Player("Player 9", 8, 2, 7, 67),
                    new Player("Player 10", 1, 11, 0, 29),
                    new Player("Player 11", 2, 6, 9, 88)
            };

            Player[] scratch = new Player[players.length];

            int k = PocuBasketballAssociation.findDreamTeamSize(players, scratch); // k: 6

            System.out.println("K:" + k);
        }
        {
            GameStat[] gameStats = new GameStat[] {
                    new GameStat("Player 1", 1, 13, 5, 6, 10, 1),
                    new GameStat("Player 2", 2, 5, 2, 5, 0, 10),
                    new GameStat("Player 1", 3, 12, 6, 9, 8, 5),
                    new GameStat("Player 3", 1, 31, 15, 40, 5, 3),
                    new GameStat("Player 2", 1, 3, 1, 3, 12, 2),
                    new GameStat("Player 1", 2, 11, 6, 11, 9, 3),
                    new GameStat("Player 2", 3, 9, 3, 3, 1, 11),
                    new GameStat("Player 3", 4, 32, 15, 51, 4, 2),
                    new GameStat("Player 4", 3, 44, 24, 50, 1, 1),
                    new GameStat("Player 1", 4, 11, 5, 14, 8, 3),
                    new GameStat("Player 2", 4, 5, 1, 3, 1, 9),
            };

            Player[] players = new Player[] {
                    new Player(),
                    new Player(),
                    new Player(),
                    new Player()
            };

            PocuBasketballAssociation.processGameStats(gameStats, players);

            Player player = getPlayerOrNull(players, "Player 1");
            assert (player != null);
            assert (player.getPointsPerGame() == 11);
            assert (player.getAssistsPerGame() == 8);
            assert (player.getPassesPerGame() == 3);
            assert (player.getShootingPercentage() == 55);

            player = getPlayerOrNull(players, "Player 2");
            assert (player != null);
            assert (player.getPointsPerGame() == 5);
            assert (player.getAssistsPerGame() == 3);
            assert (player.getPassesPerGame() == 8);
            assert (player.getShootingPercentage() == 50);

            player = getPlayerOrNull(players, "Player 3");
            assert (player != null);
            assert (player.getPointsPerGame() == 31);
            assert (player.getAssistsPerGame() == 4);
            assert (player.getPassesPerGame() == 2);
            assert (player.getShootingPercentage() == 32);

            player = getPlayerOrNull(players, "Player 4");
            assert (player != null);
            assert (player.getPointsPerGame() == 44);
            assert (player.getAssistsPerGame() == 1);
            assert (player.getPassesPerGame() == 1);
            assert (player.getShootingPercentage() == 48);
        }

        {
            Player[] players = new Player[] {
                    new Player("Player 1", 1, 5, 1, 60),
                    new Player("Player 2", 5, 2, 11, 31),
                    new Player("Player 3", 7, 4, 7, 44),
                    new Player("Player 4", 10, 10, 15, 25),
                    new Player("Player 5", 11, 12, 6, 77),
                    new Player("Player 6", 15, 0, 12, 61),
                    new Player("Player 7", 16, 8, 2, 70)
            };

            Player player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 12);
            assert (player.getName().equals("Player 5"));

            player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 5);
            assert (player.getName().equals("Player 2"));

            player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 13);
            assert (player.getName().equals("Player 6"));
        }

        {
            Player[] players = new Player[] {
                    new Player("Player 4", 10, 10, 15, 25),
                    new Player("Player 2", 5, 2, 11, 31),
                    new Player("Player 3", 7, 4, 7, 44),
                    new Player("Player 1", 1, 5, 1, 60),
                    new Player("Player 6", 15, 0, 12, 61),
                    new Player("Player 7", 16, 8, 2, 70),
                    new Player("Player 5", 11, 12, 6, 77)
            };

            Player player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 28);
            assert (player.getName().equals("Player 2"));

            player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 58);
            assert (player.getName().equals("Player 1"));

            player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 72);
            assert (player.getName().equals("Player 7"));
        }

        {
            Player[] players = new Player[] {
                    new Player("Player 2", 5, 12, 14, 50),
                    new Player("Player 6", 15, 2, 5, 40),
                    new Player("Player 5", 11, 1, 11, 54),
                    new Player("Player 4", 10, 3, 51, 88),
                    new Player("Player 7", 16, 8, 5, 77),
                    new Player("Player 1", 1, 15, 2, 22),
                    new Player("Player 3", 7, 5, 8, 66)
            };

            Player[] outPlayers = new Player[3];
            Player[] scratch = new Player[3];

            long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch);

            assert (maxTeamwork == 219);

            Player player = getPlayerOrNull(players, "Player 4");
            assert (player != null);

            player = getPlayerOrNull(players, "Player 2");
            assert (player != null);

            player = getPlayerOrNull(players, "Player 3");
            assert (player != null);
        }

        {
            Player[] players = new Player[] {
                    new Player("Player 2", 5, 5, 17, 50),
                    new Player("Player 6", 15, 4, 10, 40),
                    new Player("Player 5", 11, 3, 25, 54),
                    new Player("Player 4", 10, 9, 1, 88),
                    new Player("Player 7", 16, 7, 5, 77),
                    new Player("Player 1", 1, 2, 8, 22),
                    new Player("Player 9", 42, 15, 4, 56),
                    new Player("Player 8", 33, 11, 3, 72),
            };

            final int TEAM_SIZE = 4;

            Player[] outPlayers = new Player[TEAM_SIZE];
            Player[] scratch = new Player[TEAM_SIZE];

            long maxTeamwork = PocuBasketballAssociation.findDreamTeam(players, TEAM_SIZE, outPlayers, scratch);

            assert (maxTeamwork == 171);

            Player player = getPlayerOrNull(players, "Player 5");
            assert (player != null);

            player = getPlayerOrNull(players, "Player 6");
            assert (player != null);

            player = getPlayerOrNull(players, "Player 2");
            assert (player != null);

            player = getPlayerOrNull(players, "Player 7");
            assert (player != null);
        }

        {
            Player[] players = new Player[] {
                    new Player("Player 1", 2, 5, 10, 78),
                    new Player("Player 2", 10, 4, 5, 66),
                    new Player("Player 3", 3, 3, 2, 22),
                    new Player("Player 4", 1, 9, 8, 12),
                    new Player("Player 5", 11, 1, 12, 26),
                    new Player("Player 6", 7, 2, 10, 15),
                    new Player("Player 7", 8, 15, 3, 11),
                    new Player("Player 8", 5, 7, 13, 5),
                    new Player("Player 9", 8, 2, 7, 67),
                    new Player("Player 10", 1, 11, 0, 29),
                    new Player("Player 11", 2, 6, 9, 88)
            };

            Player[] tempPlayers = new Player[players.length];

            int k = PocuBasketballAssociation.findDreamTeamSize(players, tempPlayers);

            assert (k == 6);
        }

        {
            testProcessGameStats();
            testFindPlayerPointsPerGame();
            testFindPlayerShootingPercentage();
            testFind3ManDreamTeam();
            testFindDreamTeam();
            testFindDreamTeamSize();
        }
    }
    private static void testProcessGameStats() {
        // POCU test
        GameStat[] gameStats = new GameStat[]{
                new GameStat("Player 1", 1, 13, 5, 6, 10, 1),
                new GameStat("Player 2", 2, 5, 2, 5, 0, 10),
                new GameStat("Player 1", 3, 12, 6, 9, 8, 5),
                new GameStat("Player 3", 1, 31, 15, 40, 5, 3),
                new GameStat("Player 2", 1, 3, 1, 3, 12, 2),
                new GameStat("Player 1", 2, 11, 6, 11, 9, 3),
                new GameStat("Player 2", 3, 9, 3, 3, 1, 11),
                new GameStat("Player 3", 4, 32, 15, 51, 4, 2),
                new GameStat("Player 4", 3, 44, 24, 50, 1, 1),
                new GameStat("Player 1", 4, 11, 5, 14, 8, 3),
                new GameStat("Player 2", 4, 5, 1, 3, 1, 9),
        };

        Player[] players = new Player[]{
                new Player(),
                new Player(),
                new Player(),
                new Player()
        };

        PocuBasketballAssociation.processGameStats(gameStats, players);

        Player player = getPlayerOrNull(players, "Player 1");
        assert (player != null);
        assert (player.getPointsPerGame() == 11);
        assert (player.getAssistsPerGame() == 8);
        assert (player.getPassesPerGame() == 3);
        assert (player.getShootingPercentage() == 55);

        player = getPlayerOrNull(players, "Player 2");
        assert (player != null);
        assert (player.getPointsPerGame() == 5);
        assert (player.getAssistsPerGame() == 3);
        assert (player.getPassesPerGame() == 8);
        assert (player.getShootingPercentage() == 50);

        player = getPlayerOrNull(players, "Player 3");
        assert (player != null);
        assert (player.getPointsPerGame() == 31);
        assert (player.getAssistsPerGame() == 4);
        assert (player.getPassesPerGame() == 2);
        assert (player.getShootingPercentage() == 32);

        player = getPlayerOrNull(players, "Player 4");
        assert (player != null);
        assert (player.getPointsPerGame() == 44);
        assert (player.getAssistsPerGame() == 1);
        assert (player.getPassesPerGame() == 1);
        assert (player.getShootingPercentage() == 48);
    }

    private static void testFindPlayerPointsPerGame() {
        // POCU test
        Player[] players = new Player[]{
                new Player("Player 1", 1, 5, 1, 60),
                new Player("Player 2", 5, 2, 11, 31),
                new Player("Player 3", 7, 4, 7, 44),
                new Player("Player 4", 10, 10, 15, 25),
                new Player("Player 5", 11, 12, 6, 77),
                new Player("Player 6", 15, 0, 12, 61),
                new Player("Player 7", 16, 8, 2, 70)
        };

        Player player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 12);
        assert (player.getName().equals("Player 5"));

        player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 5);
        assert (player.getName().equals("Player 2"));

        player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 13);
        assert (player.getName().equals("Player 6"));

        // my test
        player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 0);
        assert (player.getName().equals("Player 1"));

        player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 20);
        assert (player.getName().equals("Player 7"));
    }

    private static void testFindPlayerShootingPercentage() {
        // POCU test
        Player[] players = new Player[]{
                new Player("Player 4", 10, 10, 15, 25),
                new Player("Player 2", 5, 2, 11, 31),
                new Player("Player 3", 7, 4, 7, 44),
                new Player("Player 1", 1, 5, 1, 60),
                new Player("Player 6", 15, 0, 12, 61),
                new Player("Player 7", 16, 8, 2, 70),
                new Player("Player 5", 11, 12, 6, 77)
        };

        Player player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 28);
        assert (player.getName().equals("Player 2"));

        player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 58);
        assert (player.getName().equals("Player 1"));

        player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 72);
        assert (player.getName().equals("Player 7"));

        // my test
        player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 10);
        assert (player.getName().equals("Player 4"));

        player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 80);
        assert (player.getName().equals("Player 5"));
    }

    private static void testFind3ManDreamTeam() {
        // POCU test
        Player[] players = new Player[]{
                new Player("Player 2", 5, 12, 14, 50),
                new Player("Player 6", 15, 2, 5, 40),
                new Player("Player 5", 11, 1, 11, 54),
                new Player("Player 4", 10, 3, 51, 88),
                new Player("Player 7", 16, 8, 5, 77),
                new Player("Player 1", 1, 15, 2, 22),
                new Player("Player 3", 7, 5, 8, 66)
        };

        Player[] outPlayers = new Player[3];
        Player[] scratch = new Player[3];

        long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch);

        assert (maxTeamwork == 219);

        Player player = getPlayerOrNull(outPlayers, "Player 4");
        assert (player != null);

        player = getPlayerOrNull(outPlayers, "Player 2");
        assert (player != null);

        player = getPlayerOrNull(outPlayers, "Player 3");
        assert (player != null);


        // my test
        players = new Player[]{
                new Player("Player 2", 5, 100, 1, 50),
                new Player("Player 6", 15, 100, 1, 40),
                new Player("Player 5", 11, 100, 1, 54),
                new Player("Player 4", 10, 1, 50, 88),
                new Player("Player 7", 16, 1, 60, 77),
                new Player("Player 1", 1, 1, 70, 22),
                new Player("Player 3", 7, 1, 80, 66)
        };

        maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch);

        assert (maxTeamwork == 300);

        player = getPlayerOrNull(outPlayers, "Player 2");
        assert (player != null);

        player = getPlayerOrNull(outPlayers, "Player 6");
        assert (player != null);

        player = getPlayerOrNull(outPlayers, "Player 5");
        assert (player != null);
    }

    private static void testFindDreamTeam() {
        // POCU test
        Player[] players = new Player[]{
                new Player("Player 2", 5, 5, 17, 50),
                new Player("Player 6", 15, 4, 10, 40),
                new Player("Player 5", 11, 3, 25, 54),
                new Player("Player 4", 10, 9, 1, 88),
                new Player("Player 7", 16, 7, 5, 77),
                new Player("Player 1", 1, 2, 8, 22),
                new Player("Player 9", 42, 15, 4, 56),
                new Player("Player 8", 33, 11, 3, 72),
        };

        int TEAM_SIZE = 4;

        Player[] outPlayers = new Player[TEAM_SIZE];
        Player[] scratch = new Player[TEAM_SIZE];

        long maxTeamwork = PocuBasketballAssociation.findDreamTeam(players, TEAM_SIZE, outPlayers, scratch);

        assert (maxTeamwork == 171);

        Player player = getPlayerOrNull(outPlayers, "Player 5");
        assert (player != null);

        player = getPlayerOrNull(outPlayers, "Player 6");
        assert (player != null);

        player = getPlayerOrNull(outPlayers, "Player 2");
        assert (player != null);

        player = getPlayerOrNull(outPlayers, "Player 7");
        assert (player != null);

        // my test
        TEAM_SIZE = 1;
        outPlayers = new Player[TEAM_SIZE];
        scratch = new Player[TEAM_SIZE];

        maxTeamwork = PocuBasketballAssociation.findDreamTeam(players, TEAM_SIZE, outPlayers, scratch);

        assert (maxTeamwork == 85);

        player = getPlayerOrNull(outPlayers, "Player 2");
        assert (player != null);

        // my test 2
        players = new Player[]{
                new Player("Player 1", 1, 2, 8, 22),
        };

        maxTeamwork = PocuBasketballAssociation.findDreamTeam(players, TEAM_SIZE, outPlayers, scratch);

        assert (maxTeamwork == 16);

        player = getPlayerOrNull(outPlayers, "Player 1");
        assert (player != null);
    }

    private static void testFindDreamTeamSize() {
        // POCU test
        Player[] players = new Player[]{
                new Player("Player 1", 2, 5, 10, 78),
                new Player("Player 2", 10, 4, 5, 66),
                new Player("Player 3", 3, 3, 2, 22),
                new Player("Player 4", 1, 9, 8, 12),
                new Player("Player 5", 11, 1, 12, 26),
                new Player("Player 6", 7, 2, 10, 15),
                new Player("Player 7", 8, 15, 3, 11),
                new Player("Player 8", 5, 7, 13, 5),
                new Player("Player 9", 8, 2, 7, 67),
                new Player("Player 10", 1, 11, 0, 29),
                new Player("Player 11", 2, 6, 9, 88)
        };

        Player[] tempPlayers = new Player[players.length];

        int k = PocuBasketballAssociation.findDreamTeamSize(players, tempPlayers);

        assert (k == 6 || k == 5);

        // my test 1
        players = new Player[]{
                new Player("Player 1", 2, 1, 100, 78),
                new Player("Player 2", 10, 1, 100, 66),
                new Player("Player 3", 3, 1, 100, 22),
                new Player("Player 4", 1, 100, 50, 12),
                new Player("Player 5", 11, 1, 100, 26),
                new Player("Player 6", 7, 1, 100, 15),
                new Player("Player 7", 8, 1, 100, 11),
        };

        tempPlayers = new Player[players.length];

        k = PocuBasketballAssociation.findDreamTeamSize(players, tempPlayers);

        assert (k == 1);

        // my test 2
        players = new Player[]{
                new Player("Player 1", 2, 1, 100, 78),
        };

        tempPlayers = new Player[players.length];

        k = PocuBasketballAssociation.findDreamTeamSize(players, tempPlayers);

        assert (k == 1);

    }


    private static Player getPlayerOrNull(final Player[] players, final String id) {
        for (Player player : players) {
            if (player.getName().equals(id)) {
                return player;
            }
        }

        return null;
    }
}