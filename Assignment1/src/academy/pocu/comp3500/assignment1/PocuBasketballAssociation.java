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
                outPlayers[playerIndex++].setShootingPercentage(100 * totalGoal / totalGoalAttemp);

                name = gameStats[i].getPlayerName();
                totalPoint = gameStats[i].getPoints();
                totalGoal = gameStats[i].getGoals();
                totalGoalAttemp = gameStats[i].getGoalAttempts();
                totalAssist = gameStats[i].getAssists();
                totalPass = gameStats[i].getNumPasses();
                gameCount = 1;
            }

            if (outPlayers.length - 1 == playerIndex) {
                outPlayers[playerIndex].setName(name);
                outPlayers[playerIndex].setPointsPerGame(totalPoint / gameCount);
                outPlayers[playerIndex].setAssistsPerGame(totalAssist / gameCount);
                outPlayers[playerIndex].setPassesPerGame(totalAssist / gameCount);
                outPlayers[playerIndex++].setShootingPercentage(100 * totalGoal / totalGoalAttemp);
            }
        }
    }

    public static Player findPlayerPointsPerGame(final Player[] players, int targetPoints) {
        if (players.length < 1) {
            return null;
        }

//        int left = 0;
//        int right = players.length - 1;
//        int findIndex = (left + right) / 2;
//        Player p = players[findIndex];
//
//        while (left < right) {
//            if (targetPoints == players[findIndex].getPointsPerGame()) {
//                return players[findIndex];
//            } else if (targetPoints < players[findIndex].getPointsPerGame()) {
//                right = findIndex - 1;
//                p = players[findIndex];
//                findIndex = (left + right) / 2;
//            } else {
//                left = findIndex + 1;
//                findIndex = (left + right) / 2;
//            }
//        }
//
//        int i = Math.abs(players[findIndex].getPointsPerGame() - targetPoints);
//        int j = Math.abs(p.getPointsPerGame() - targetPoints);
//
//        if (i >= j) {
//            return p;
//        }

        return findPlayer(players, targetPoints, true);
    }

    public static Player findPlayerShootingPercentage(final Player[] players, int targetShootingPercentage) {
        if (players.length < 1) {
            return null;
        }

        return findPlayer(players, targetShootingPercentage, false);
    }

    public static long find3ManDreamTeam(final Player[] players, final Player[] outPlayers, final Player[] scratch) {
        return -1;
    }

    public static long findDreamTeam(final Player[] players, int k, final Player[] outPlayers, final Player[] scratch) {
        return -1;
    }

    public static int findDreamTeamSize(final Player[] players, final Player[] scratch) {
        return -1;
    }

    private static Player findPlayer(final Player[] players, int target, boolean findPoint) {
        int left = 0;
        int right = players.length - 1;
        int findIndex = (left + right) / 2;
        Player p = players[findIndex];
        int current = 0;

        while (left < right) {
            current = getValue(players[findIndex], findPoint);

            if (target == current) {
                return players[findIndex];
            } else if (target < current) {
                right = findIndex - 1;
                if (Math.abs(findPoint ? p.getPointsPerGame() - target : p.getShootingPercentage() - target) > Math.abs(current - target)) {
                    p = players[findIndex];
                }
                findIndex = (left + right) / 2;
            } else {
                left = findIndex + 1;
                if (Math.abs(findPoint ? p.getPointsPerGame() - target : p.getShootingPercentage() - target) > Math.abs(current - target)) {
                    p = players[findIndex];
                }
                findIndex = (left + right) / 2;
            }
        }

        int i = Math.abs(findPoint ? players[findIndex].getPointsPerGame() - target : players[findIndex].getShootingPercentage() - target);
        int j = Math.abs(findPoint ? p.getPointsPerGame() - target : p.getShootingPercentage() - target);

        if (i >= j) {
            return p;
        }

        return players[findIndex];
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