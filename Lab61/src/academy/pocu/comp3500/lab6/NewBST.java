package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class NewBST {
    private NodeNew root;
    private HashMap<Integer, HashMap<Integer, Player>> hashMap = new HashMap<>();

    public NodeNew getRoot() {
        return root;
    }

    public void makeTree(Player[] players) {
        root = makeTreeRecursive(players, 0, players.length - 1);
    }

    private NodeNew makeTreeRecursive(Player[] players, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        NodeNew node = new NodeNew(players[mid].getRating());
        HashMap<Integer, Player> map = new HashMap<>();
        map.put(players[mid].getId(), players[mid]);
        hashMap.put(players[mid].getRating(), map);

        int leftEnd = mid;
        int rightEnd = mid;

        if (mid > start) {
            int i = mid - 1;
            for (i = mid - 1; i >= start; --i) {
                if (node.getRating() == players[i].getRating()) {
                    getPlayerHashMap(node.getRating()).put(players[i].getId(), players[i]);
                } else {
                    leftEnd = i + 1;
                    break;
                }
            }
            if (i < start) {
                leftEnd = start;
            }
        }

        if (mid < end) {
            int i = mid + 1;
            for (i = mid + 1; i <= end; ++i) {
                if (node.getRating() == players[i].getRating()) {
                    getPlayerHashMap(node.getRating()).put(players[i].getId(), players[i]);
                } else {
                    rightEnd = i - 1;
                    break;
                }
            }

            if (i > end) {
                rightEnd = end;
            }
        }

        node.setLeft(makeTreeRecursive(players, start, leftEnd - 1));
        node.setRight(makeTreeRecursive(players, rightEnd + 1, end));

        return node;
    }

    public Player findMatchPlayer(Player player) {
        if (player == null || this.root == null) {
            return null;
        }

        if (root.getLeft() == null && root.getRight() == null) {
            for (Player p : hashMap.get(root.getRating()).values()) {
                if (p.getId() != player.getId()) {
                    return p;
                }
            }
            return null;
        }

        Stack<Player> finder = new Stack<>();

        NodeNew node = findPlayerRecursive(root, finder, player);

        if (node == null) {
            return null;
        } else if (getPlayerHashMap(node.getRating()).size() > 1) {
            for (Player p : getPlayerHashMap(node.getRating()).values()) {
                if (p.getId() != player.getId()) {
                    return p;
                }
            }
        } else {
            findPlayerRecursive(node.getRight(), finder, player);
            findPlayerRecursive(node.getLeft(), finder, player);

            return finder.peek();
        }

        return null;
    }

    private HashMap<Integer, Player> getPlayerHashMap(int rating) {
        return this.hashMap.get(rating);
    }

    private NodeNew findPlayerRecursive(NodeNew node, Stack<Player> finder, Player player) {
        if (node == null) {
            return null;
        }

        int nodeRating = node.getRating();
        int playerRating = player.getRating();

        if (finder.size() == 0) {
            for (Player p : getPlayerHashMap(nodeRating).values()) {
                if (p.getId() != player.getId()) {
                    finder.push(p);
                    break;
                }
            }
        } else {
            int finderRating = finder.peek().getRating();
            int diffFinder = Math.abs(finderRating - playerRating);
            int diffNode = Math.abs(nodeRating - playerRating);
            for (Player p : getPlayerHashMap(nodeRating).values()) {
                if (p.getId() != player.getId()) {
                    if (diffNode < diffFinder) {
                        finder.push(p);
                    } else if (diffNode == diffFinder && nodeRating > finderRating) {
                        finder.push(p);
                    }
                }
            }
        }

        if (playerRating == nodeRating) {
            for (Player p : getPlayerHashMap(nodeRating).values()) {
                if (p.getId() == player.getId()) {
                    return node;
                }
            }
            return null;
        } else if (playerRating < nodeRating) {
            return findPlayerRecursive(node.getLeft(), finder, player);
        } else {
            return findPlayerRecursive(node.getRight(), finder, player);
        }
    }

    public Player[] getTop(final int count) {
        ArrayList<Player> arrPlayer = new ArrayList<>();
        getTopRecursive(root, count, arrPlayer);
        Player[] p = arrPlayer.toArray(new Player[arrPlayer.size()]);
        return p;
    }


    private void getTopRecursive(NodeNew node, int count, ArrayList<Player> listPlayer) {
        if (node == null || listPlayer.size() >= count) {
            return;
        }

        getTopRecursive(node.getRight(), count, listPlayer);

        for (Player p : getPlayerHashMap(node.getRating()).values()) {
            if (listPlayer.size() < count) {
                listPlayer.add(p);
            } else {
                return;
            }
        }

        getTopRecursive(node.getLeft(), count, listPlayer);
    }

    public Player[] getBottom(final int count) {
        ArrayList<Player> arrPlayer = new ArrayList<>();
        getBottomRecursive(root, count, arrPlayer);
        Player[] p = arrPlayer.toArray(new Player[arrPlayer.size()]);
        return p;
    }


    private void getBottomRecursive(NodeNew node, int count, ArrayList<Player> listPlayer) {
        if (node == null || listPlayer.size() >= count) {
            return;
        }

        getBottomRecursive(node.getLeft(), count, listPlayer);

        for (Player p : getPlayerHashMap(node.getRating()).values()) {
            if (listPlayer.size() < count) {
                listPlayer.add(p);
            } else {
                return;
            }
        }

        if (listPlayer.size() >= count) {
            return;
        }

        getBottomRecursive(node.getRight(), count, listPlayer);
    }

    public boolean join(Player player) {
        if (root == null) {
            root = new NodeNew(player.getRating());
            HashMap<Integer, Player> map = new HashMap<>();
            map.put(player.getId(), player);
            hashMap.put(player.getRating(), map);
            return true;
        }

        return joinRecursive(root, player);
    }

    private boolean joinRecursive(NodeNew node, Player player) {
        if (node.getRating() == player.getRating()) {
            if (getPlayerHashMap(node.getRating()).containsKey(player.getId()) == false) {
                getPlayerHashMap(node.getRating()).put(player.getId(), player);
                return true;
            }
            return false;
        } else if (node.getRating() > player.getRating()) {
            if (node.getLeft() != null) {
                boolean b = joinRecursive(node.getLeft(), player);
                return b;
            } else {
                NodeNew newNode = new NodeNew(player.getRating());
                HashMap<Integer, Player> map = new HashMap<>();
                map.put(player.getId(), player);
                hashMap.put(player.getRating(), map);
                node.setLeft(newNode);
                return true;
            }
        } else {
            if (node.getRight() != null) {
                boolean b = joinRecursive(node.getRight(), player);
                return b;
            } else {
                NodeNew newNode = new NodeNew(player.getRating());
                HashMap<Integer, Player> map = new HashMap<>();
                map.put(player.getId(), player);
                hashMap.put(player.getRating(), map);
                node.setRight(newNode);
                return true;
            }
        }
    }

    public boolean leave(Player player) {
        if (this.hashMap.containsKey(player.getRating()) == false) {
            return false;
        } else if (this.hashMap.get(player.getRating()).size() > 1) {
            return this.hashMap.get(player.getRating()).remove(player.getId()) != null;
        }

        boolean result = this.hashMap.get(player.getRating()).remove(player.getId()) != null;
        if (result) {
            this.hashMap.remove(player.getRating());

            deleteNode(root, player.getRating());

            if (this.hashMap.size() <= 0) {
                this.root = null;
            }
        }
        return result;
    }

    private NodeNew findParentNodeRecursive(NodeNew parent, NodeNew node, Player player) {
        if (node == null) {
            return null;
        }

        if (node.getRating() == player.getRating()) {
            return parent;
        } else if (node.getRating() > player.getRating()) {
            return findParentNodeRecursive(node, node.getLeft(), player);
        } else {
            return findParentNodeRecursive(node, node.getRight(), player);
        }
    }

    private NodeNew minNode(NodeNew node) {
        if (node.getLeft() == null)
            return node;
        else {
            return minNode(node.getLeft());
        }
    }

    private NodeNew deleteNode(NodeNew node, int rating) {
        if (node == null)
            return null;
        if (node.getRating() > rating) {
            node.setLeft(deleteNode(node.getLeft(), rating));
        } else if (node.getRating() < rating) {
            node.setRight(deleteNode(node.getRight(), rating));
        } else {
            if (node.getLeft() != null && node.getRight() != null) {
                NodeNew temp = node;
                NodeNew minNodeForRight = minNode(temp.getRight());
                node.setRating(minNodeForRight.getRating());
                node.setRight(deleteNode(node.getRight(), minNodeForRight.getRating()));
            }
            else if (node.getLeft() != null) {
                node = node.getLeft();
            }
            else if (node.getRight() != null) {
                node = node.getRight();
            }
            else {
                node = null;
            }
        }
        return node;
    }

}
