package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;

import java.util.HashMap;

public class NodeNew {
    private NodeNew left = null;
    private NodeNew right = null;
    private int rating;

    public NodeNew(int rating) {
        this.rating = rating;
    }

    public void setLeft(NodeNew node) {
        this.left = node;
    }

    public void setRight(NodeNew node) {
        this.right = node;
    }

    public NodeNew getLeft() {
        return this.left;
    }

    public NodeNew getRight() {
        return this.right;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
