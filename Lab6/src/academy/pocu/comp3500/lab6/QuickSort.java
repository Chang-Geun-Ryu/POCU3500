package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;

final public class QuickSort {
    static public void sort(Player[] players) {
        sortRecursive(players, 0, players.length - 1);
    }

    static private void sortRecursive(Player[] players, int start, int end) {
        if (start >= end) {
            return;
        }

        int pivotPos = getPivot(players, start, end);

        sortRecursive(players, start, pivotPos - 1);
        sortRecursive(players, pivotPos + 1, end);
    }

    static private int getPivot(Player[] players, int start, int end) {
        Player pivotPlayer = players[end];
        int swapIndex = start - 1;
        for (int i = start; i < end; i++) {
            if (players[i].getRating() < pivotPlayer.getRating()) {
                swapPlayer(players, ++swapIndex, i);
            }
        }

        ++swapIndex;

        swapPlayer(players, swapIndex, end);

        return swapIndex;
    }

    static private void swapPlayer(Player[] players, int pos1, int pos2) {
        Player temp = players[pos1];
        players[pos1] = players[pos2];
        players[pos2] = temp;
    }
}
