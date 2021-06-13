package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;

import java.util.ArrayList;
import java.util.Stack;

final public class BST {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void makeTree(Player[] players) {
        root = makeTreeRecursive(players, 0, players.length - 1);
    }

    private Node makeTreeRecursive(Player[] players, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        Node node = new Node();
        node.addPlayer(players[mid]);

        int leftEnd = mid;
        int rightEnd = mid;

        if (mid > start) {
            for (int i = mid - 1; i >= start; --i) {
                if (players[mid].getRating() == players[i].getRating()) {
                    node.addPlayer(players[i]);
                } else {
                    leftEnd = i + 1;
                    break;
                }
            }
        }

        if (mid < end) {
            for (int i = mid + 1; i <= end; ++i) {
                if (players[mid].getRating() == players[i].getRating()) {
                    node.addPlayer(players[i]);
                } else {
                    rightEnd = i - 1;
                    break;
                }
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
            for (Player p : root.getHashmap().values()) {
                if (p.getId() != player.getId()) {
                    return p;
                }
            }
            return null;
        }

        Stack<Player> finder = new Stack<>();

        Node node = findPlayerRecursive(root, finder, player);

        if (node == null) {
            return null;
        } else if (node.getPlayersSize() > 1) {
            for (Player p : node.getHashmap().values()) {
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

    private Node findPlayerRecursive(Node node, Stack<Player> finder, Player player) {
        if (node == null) {
            return null;
        }

        int nodeRating = node.getRating();
        int playerRating = player.getRating();

        if (finder.size() == 0) {
            for (Player p : node.getHashmap().values()) {
                if (p.getId() != player.getId()) {
                    finder.push(p);
                    break;
                }
            }
        } else {
            int finderRating = finder.peek().getRating();
            int diffFinder = Math.abs(finderRating - playerRating);
            int diffNode = Math.abs(nodeRating - playerRating);
            for (Player p : node.getHashmap().values()) {
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
            for (Player p : node.getHashmap().values()) {
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

    private void getTopRecursive(Node node, int count, ArrayList<Player> listPlayer) {
        if (node == null || listPlayer.size() >= count) {
            return;
        }

        getTopRecursive(node.getRight(), count, listPlayer);

        for (Player p : node.getHashmap().values()) {
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

    private void getBottomRecursive(Node node, int count, ArrayList<Player> listPlayer) {
        if (node == null || listPlayer.size() >= count) {
            return;
        }

        getBottomRecursive(node.getLeft(), count, listPlayer);

        for (Player p : node.getHashmap().values()) {
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
            root = new Node();
            root.addPlayer(player);
            return true;
        }

        return joinRecursive(root, player);
    }

    private boolean joinRecursive(Node node, Player player) {
        if (node.getRating() == player.getRating()) {
            for (Player p : node.getHashmap().values()) {
                if (p.getId() == player.getId()) {
                    return false;
                }
            }
            node.addPlayer(player);
            return true;
        } else if (node.getRating() > player.getRating()) {
            if (node.getLeft() != null) {
                boolean b = joinRecursive(node.getLeft(), player);
                return b;
            } else {
                Node newNode = new Node();
                newNode.addPlayer(player);
                node.setLeft(newNode);
                return true;
            }
        } else {
            if (node.getRight() != null) {
                boolean b = joinRecursive(node.getRight(), player);
                return b;
            } else {
                Node newNode = new Node();
                newNode.addPlayer(player);
                node.setRight(newNode);
                return true;
            }
        }
    }

    public boolean leave(Player player) {
        if (root == null) {
            return false;
        }


        Node deleteNodeParent = findParentNodeRecursive(root, root, player);

        if (deleteNodeParent == null) {
            return false;
        }

        if (deleteNodeParent.getRating() == player.getRating()) {
            boolean result = root.removePlayer(player) != null ? true : false;

            if (root.getPlayersSize() == 0) {
                if (root.getRight() != null) {
                    swapNodeRecursive(root, root, root.getRight(), true);
                } else if (root.getLeft() != null) {
                    root = root.getLeft();
                } else {
                    root = null;
                }
            }

            return result;
        }

        Node deleteNode;
        boolean isRight = false;
        if (deleteNodeParent.getLeft() != null && deleteNodeParent.getRating() == player.getRating()) {
            deleteNode = deleteNodeParent.getLeft();
        } else {
            deleteNode = deleteNodeParent.getRight();
            isRight = true;
        }

        if (deleteNode.getRating() == player.getRating()) {
            boolean result = deleteNode.removePlayer(player) != null ? true : false;

            if (deleteNode.getPlayersSize() == 0) {
                if (deleteNode.getRight() != null) {
                    swapNodeRecursive(deleteNode, deleteNode, deleteNode.getRight(), isRight);
                } else if (deleteNode.getLeft() != null) {
                    if (isRight) {
                        deleteNodeParent.setRight(deleteNode.getLeft());
                    } else {
                        deleteNodeParent.setLeft(deleteNode.getLeft());
                    }
                } else {
                    if (isRight) {
                        deleteNodeParent.setRight(null);
                    } else {
                        deleteNodeParent.setLeft(null);
                    }
                }
            }

            return result;
        }

        return false;
    }

    private Node findParentNodeRecursive(Node parent, Node node, Player player) {
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

    private void swapNodeRecursive(Node deletedNode, Node parentNode, Node node, boolean isRight) {

        if (node.getLeft() == null && node.getRight() == null) {
            for (Player p : node.getHashmap().values()) {
                deletedNode.addPlayer(p);
            }

            if (isRight) {
                parentNode.setRight(null);
            } else {
                parentNode.setLeft(null);
            }
            return;
        } else if (node.getLeft() == null && node.getRight() != null) {
            for (Player p : node.getHashmap().values()) {
                deletedNode.addPlayer(p);
            }

            if (isRight) {
                parentNode.setRight(node.getRight());
            } else {
                parentNode.setLeft(node.getRight());
            }
            return;
        }

        swapNodeRecursive(deletedNode, node, node.getLeft(), false);
    }
}
