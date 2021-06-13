package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;

import java.util.HashMap;

final public class Node {
    private Node left = null;
    private Node right = null;
    private HashMap<Integer, Player> playerHashMap = new HashMap<>();
    private int rating;

    public void addPlayer(Player player) {
//        this.players.add(player);
        playerHashMap.put(player.getId(), player);
        rating = player.getRating();
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

    public Player getPlayers(Player player) {
        return this.playerHashMap.get(player);
    }

    public HashMap<Integer, Player> getHashmap() {
        return this.playerHashMap;
    }

    public int getPlayersSize() {
        return this.playerHashMap.size();
    }

    public Player removePlayer(Player player) {
        return this.playerHashMap.remove(player.getId());
    }

    public int getRating() {
        return this.rating;
    }
}
