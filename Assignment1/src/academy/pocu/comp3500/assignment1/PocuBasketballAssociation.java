package academy.pocu.comp3500.assignment1;

import academy.pocu.comp3500.assignment1.pba.Player;
import academy.pocu.comp3500.assignment1.pba.GameStat;

public final class PocuBasketballAssociation {
    private PocuBasketballAssociation() {
    }

    public static void processGameStats(final GameStat[] gameStats, final Player[] outPlayers) {

        quickSort(gameStats);

        String name = "";
        int gameCount = 0;
        int totalPoint = 0;
        int totalGoal = 0;
        int totalGoalAttemp = 0;
        int totalAssist = 0;
        int totalPass = 0;

        int playerIndex = 0;
        for (int i = 0; i < gameStats.length; ++i) {
            if (i == 0 || name.hashCode() == gameStats[i].getPlayerName().hashCode()) {
                name = gameStats[i].getPlayerName();
                totalPoint += gameStats[i].getPoints();
                totalGoal += gameStats[i].getGoals();
                totalGoalAttemp += gameStats[i].getGoalAttempts();
                totalAssist += gameStats[i].getAssists();
                totalPass += gameStats[i].getNumPasses();
                gameCount += 1;
            } else {
                outPlayers[playerIndex].setName(name);
                outPlayers[playerIndex].setPointsPerGame(totalPoint / gameCount);
                outPlayers[playerIndex].setAssistsPerGame(totalAssist / gameCount);
                outPlayers[playerIndex].setPassesPerGame(totalPass / gameCount);
                outPlayers[playerIndex++].setShootingPercentage((100 * totalGoal) / (totalGoalAttemp));

                name = gameStats[i].getPlayerName();
                totalPoint = gameStats[i].getPoints();
                totalGoal = gameStats[i].getGoals();
                totalGoalAttemp = gameStats[i].getGoalAttempts();
                totalAssist = gameStats[i].getAssists();
                totalPass = gameStats[i].getNumPasses();
                gameCount = 1;
            }

            if (gameStats.length - 1 == i) {
                outPlayers[playerIndex].setName(name);
                outPlayers[playerIndex].setPointsPerGame(totalPoint / gameCount);
                outPlayers[playerIndex].setAssistsPerGame(totalAssist / gameCount);
                outPlayers[playerIndex].setPassesPerGame(totalPass / gameCount);
                outPlayers[playerIndex++].setShootingPercentage((100 * totalGoal) / totalGoalAttemp);
            }
        }
    }

    public static Player findPlayerPointsPerGame(final Player[] players, int targetPoints) {
        if (players.length < 1) {
            return null;
        }
        return findPlayer(players, targetPoints, true);
    }

    public static Player findPlayerShootingPercentage(final Player[] players, int targetShootingPercentage) {
        if (players.length < 1) {
            return null;
        }
        return findPlayer(players, targetShootingPercentage, false);
    }

    public static long find3ManDreamTeam(final Player[] players, final Player[] outPlayers, final Player[] scratch) {
        long maxTeamwork = -1;
        quickSortPlayer(players);
//        System.out.println("///////////////////// 선수  ///////////////////");
//        for (Player p : players) {
//            System.out.println("getName" + p.getName() + ", Assists: " +p.getAssistsPerGame() + ", Passe: " + p.getPassesPerGame() + ", score: " + p.getAssistsPerGame()*p.getPassesPerGame());
//        }
//        System.out.println("///////////////////// 선수 ^^^^  ///////////////////");

        // maxTeamwork: 219, outPlayers: [ Player 4, Player 2, Player 3 ]
        int totalPasses = 0;
        int minPasses = Integer.MAX_VALUE;
        int minPassesIndex = -1;
        for (int i = 0; i < players.length; ++i) {
            int teamwork = 0;
            if (i < 3) {
                scratch[i] = players[i];
                outPlayers[i] = players[i];
                totalPasses += scratch[i].getPassesPerGame();
                if (minPasses > scratch[i].getPassesPerGame()) {
                    minPasses = scratch[i].getPassesPerGame();
                    minPassesIndex = i;
                }
                maxTeamwork = totalPasses * scratch[i].getAssistsPerGame();
            } else {
                scratch[minPassesIndex] = players[i];
                totalPasses = 0;
                minPasses = Integer.MAX_VALUE;
                for (int j = 0; j < 3; ++j) {
                    totalPasses += scratch[j].getPassesPerGame();
                    if (minPasses > scratch[j].getPassesPerGame()) {
                        minPasses = scratch[j].getPassesPerGame();
                        minPassesIndex = j;
                    }
                }

                teamwork = totalPasses * players[i].getAssistsPerGame();
                if (maxTeamwork < teamwork) {
                    for (int k = 0; k < 3; ++k) {
                        outPlayers[k] = scratch[k];
                    }
                    maxTeamwork = teamwork;
                }
            }
        }
        return maxTeamwork;
    }

    public static long findDreamTeam(final Player[] players, int k, final Player[] outPlayers, final Player[] scratch) {
        long maxTeamwork = -1;
        quickSortPlayer(players);
//        System.out.println("///////////////////// 선수  ///////////////////");
//        for (Player p : players) {
//            System.out.println("getName" + p.getName() + ", Assists: " +p.getAssistsPerGame() + ", Passe: " + p.getPassesPerGame() + ", score: " + p.getAssistsPerGame()*p.getPassesPerGame());
//        }
//        System.out.println("///////////////////// 선수 ^^^^  ///////////////////");

        // maxTeamwork: 219, outPlayers: [ Player 4, Player 2, Player 3 ]
        int totalPasses = 0;
        int minPasses = Integer.MAX_VALUE;
        int minPassesIndex = -1;
        for (int i = 0; i < players.length; ++i) {
            int teamwork = 0;
            if (i < k) {
                scratch[i] = players[i];
                outPlayers[i] = players[i];
                totalPasses += scratch[i].getPassesPerGame();
                if (minPasses > scratch[i].getPassesPerGame()) {
                    minPasses = scratch[i].getPassesPerGame();
                    minPassesIndex = i;
                }
                maxTeamwork = totalPasses * scratch[i].getAssistsPerGame();
            } else {
                scratch[minPassesIndex] = players[i];
                totalPasses = 0;
                minPasses = Integer.MAX_VALUE;
                for (int j = 0; j < k; ++j) {
                    totalPasses += scratch[j].getPassesPerGame();
                    if (minPasses > scratch[j].getPassesPerGame()) {
                        minPasses = scratch[j].getPassesPerGame();
                        minPassesIndex = j;
                    }
                }

                teamwork = totalPasses * players[i].getAssistsPerGame();
                if (maxTeamwork < teamwork) {
                    for (int j = 0; j < k; ++j) {
                        outPlayers[j] = scratch[j];
                    }
                    maxTeamwork = teamwork;
                }
            }
        }
        return maxTeamwork;
    }

    public static int findDreamTeamSize(final Player[] players, final Player[] scratch) {
        quickSortPlayer(players);

        int totalPasses = 0;
        int maxTeamwork = -1;
        int maxPlayer = 0;

        for (int i = 0; i < players.length; ++i) {

            scratch[i] = players[i];
            totalPasses += players[i].getPassesPerGame();

            int teamwork = players[i].getAssistsPerGame() * totalPasses;
            if (maxTeamwork < teamwork) {
                maxTeamwork = teamwork;
                maxPlayer = i + 1;
            }
        }

        return maxPlayer;
    }

    private static Player findPlayer(final Player[] players, int target, boolean findPoint) {
        int left = 0;
        int right = players.length - 1;
        int findIndex = (left + right) / 2;
        int current = 0;

        while (left < right) {
            current = getValue(players[findIndex], findPoint);

            if (target == current) {
                return players[findIndex];
            } else if (target < current) {
                right = findIndex;
                findIndex = (left + right) / 2;
            } else {
                left = findIndex;
                findIndex = (left + right) / 2;
            }

            if (findIndex == left) {
                int leftVelue = getValue(players[left], findPoint);
                int rightVelue = getValue(players[right], findPoint);
                if (Math.abs(leftVelue - target) == Math.abs(rightVelue - target)) {
                    return players[right];
                } else if (Math.abs(leftVelue - target) < Math.abs(rightVelue - target)) {
                    return players[left];
                } else {
                    return players[right];
                }
            }
        }

        return players[findIndex];
    }

    private static void quickSortPlayer(Player[] player) {
        quickSortPlayerRecursive(player, 0, player.length - 1);
    }

    private static void quickSortPlayerRecursive(Player[] players, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotPos = getPlayerPivotPos(players, left, right);

        quickSortPlayerRecursive(players, left, pivotPos - 1);
        quickSortPlayerRecursive(players, pivotPos + 1, right);
    }

    private static int getPlayerPivotPos(Player[] players, int left, int right) {
        Player pivot = players[right];
        int index = left - 1;

        for (int i = left; i < right; ++i) {
//            if (players[i].getPassesPerGame() * players[i].getAssistsPerGame() > pivot.getPassesPerGame() * pivot.getAssistsPerGame()) {
            if (players[i].getAssistsPerGame() > pivot.getAssistsPerGame()) {
//            if (players[i].getPointsPerGame() > pivot.getPointsPerGame()) {
                ++index;
                swapPlayer(players, index, i);
//            } else if (players[i].getPointsPerGame() == pivot.getPointsPerGame()) {
            } else if (players[i].getAssistsPerGame() == pivot.getAssistsPerGame()) {
//            } else if (players[i].getPassesPerGame() * players[i].getAssistsPerGame() == pivot.getPassesPerGame() * pivot.getAssistsPerGame()) {
                if (players[i].getPassesPerGame() > pivot.getPassesPerGame()) {
                    ++index;
                    swapPlayer(players, index, i);
                }
            }
        }

        index += 1;

        swapPlayer(players, index, right);

        return index;
    }

    private static void swapPlayer(Player[] stat, int pos1, int pos2) {
        Player temp = stat[pos1];
        stat[pos1] = stat[pos2];
        stat[pos2] = temp;
    }

    private static int getValue(final Player p, boolean findPoint) {
        return findPoint ? p.getPointsPerGame() : p.getShootingPercentage();
    }

    private static void quickSort(GameStat[] gameStats) {
        quickSortRecursive(gameStats, 0, gameStats.length - 1);
    }

    private static void quickSortRecursive(GameStat[] gameStats, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotPos = getPivotPos(gameStats, left, right);

        quickSortRecursive(gameStats, left, pivotPos - 1);
        quickSortRecursive(gameStats, pivotPos + 1, right);
    }

    private static int getPivotPos(GameStat[] gameStats, int left, int right) {
        GameStat pivot = gameStats[right];
        int index = left - 1;

        for (int i = left; i < right; ++i) {
            if (gameStats[i].getPlayerName().hashCode() < pivot.getPlayerName().hashCode()) {
                ++index;
                swap(gameStats, index, i);
            }
        }

        index += 1;

        swap(gameStats, index, right);

        return index;
    }

    private static void swap(GameStat[] stat, int pos1, int pos2) {
        GameStat temp = stat[pos1];
        stat[pos1] = stat[pos2];
        stat[pos2] = temp;
    }
}