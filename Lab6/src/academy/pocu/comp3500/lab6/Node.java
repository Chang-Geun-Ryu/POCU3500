package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;

import java.util.ArrayList;
import java.util.LinkedList;

final public class Node {
    private Node left = null;
    private Node right = null;
    private ArrayList<Player> players = new ArrayList<>();

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void setLeft(Node node) {
        this.left = node;
    }

    public void setRight(Node node) {
        this.right = node;
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getRight() {
        return this.right;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }
}
