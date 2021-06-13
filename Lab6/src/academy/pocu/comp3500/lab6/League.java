package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;


public class League {
    private BST bst;

    public League() {
        bst = new BST();
    }

    public League(final Player[] players, final boolean isSorted) {
        bst = new BST();

        if (isSorted == false) {
            QuickSort.sort(players);
        }

        if (players.length > 0) {
            bst.makeTree(players);
        }
    }

    public Player findMatchOrNull(final Player player) {
        if (bst.getRoot() == null) {
            return null;
        }

        return bst.findMatchPlayer(player);
    }

    public Player[] getTop(final int count) {
        return bst.getTop(count);
    }

    public Player[] getBottom(final int count) {
        return bst.getBottom(count);
    }

    public boolean join(final Player player) {
        return bst.join(player);
    }

    public boolean leave(final Player player) {
        return bst.leave(player);
    }

}