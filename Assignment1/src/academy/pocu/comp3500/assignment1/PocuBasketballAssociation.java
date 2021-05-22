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
//        long maxTeamwork = -1;
//        quickSortPlayer(players);
//
//        for (int i = 0; i < players.length; ++i) {
//            long teamwork = 0;
//            long totalPasses = 0;
//            long minAssissts = Integer.MAX_VALUE;
//
//            for (int j = 0; j < outPlayers.length; ++j) {
//                totalPasses += players[(i + j) % players.length].getPassesPerGame();
//                if (minAssissts > players[(i + j) % players.length].getAssistsPerGame()) {
//                    minAssissts = players[(i + j) % players.length].getAssistsPerGame();
//                }
//            }
//
//            teamwork = totalPasses * minAssissts;
//
//            if (maxTeamwork < teamwork) {
//                maxTeamwork = teamwork;
//                for (int j = 0; j < outPlayers.length; ++j) {
//                    outPlayers[j] = players[(i + j) % players.length];
//                }
//            }
//        }
//        quickSortPlayer(players);
        return findDreamTeam(players, 3, outPlayers, scratch);
    }

    public static long findDreamTeam(final Player[] players, int k, final Player[] outPlayers, final Player[] scratch) {
        long maxTeamwork = -1;
        int n = players.length;
        int combo = (1 << k) - 1;
        int counting = 0;

//        quickSortPlayer(players);
//
//        System.out.println("///////////////////// 선수  ///////////////////");
//        for (Player p : players) {
//            System.out.println("getName" + p.getName() + ", Assists: " +p.getAssistsPerGame() + ", Passe: " + p.getPassesPerGame() + ", score: " + p.getAssistsPerGame()*p.getPassesPerGame());
//        }
//        System.out.println("///////////////////// 선수 ^^^^  ///////////////////");
        while (combo < 1 << n) {
//            System.out.print("counting: " + counting++ + " ");
            long totalPasses = 0;
            long minAssissts = Integer.MAX_VALUE;
            int index = 0;
            for (int i = 0; i < n; ++i) {
                if (((combo >> i) & 1) > 0) {
                    totalPasses += players[i].getPassesPerGame();
                    if (minAssissts > players[i].getAssistsPerGame()) {
                        minAssissts = players[i].getAssistsPerGame();
                    }
//                    System.out.print(players[i].getName() + ", ");
                    scratch[index++] = players[i];
                    if (index == k) {
//                        System.out.print(", teamwork: " + totalPasses * minAssissts);
                        if (maxTeamwork < totalPasses * minAssissts) {
                            maxTeamwork = totalPasses * minAssissts;
                            for (int j = 0; j < k; ++j) {
                                outPlayers[j] = scratch[j];
                            }
                        }
                    }
                }
            }
//            System.out.println("");

            int x = combo & -combo;
            int y = combo + x;
            int z = (combo & ~y);
            combo = z / x;
            combo >>= 1;
            combo |= y;
        }



        return maxTeamwork;
    }

    public static int findDreamTeamSize(final Player[] players, final Player[] scratch) {
        quickSortPlayer(players);

        int totalPasses = 0;
        int maxTeamwork = -1;
        int maxPlayer = 1;
        for (Player p : players) {
            totalPasses += p.getPassesPerGame();

//            System.out.println("getName" + p.getName() + ", Assists: " +p.getAssistsPerGame() + ", Passe: " + p.getPassesPerGame() + ", score: " + totalPasses * p.getAssistsPerGame());
            if (maxTeamwork < totalPasses * p.getAssistsPerGame()) {
                maxTeamwork = totalPasses * p.getAssistsPerGame();
                ++maxPlayer;
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

    private static Player findPlayer1(final Player[] players, int target, boolean findPoint) {
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