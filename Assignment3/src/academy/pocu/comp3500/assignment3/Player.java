package academy.pocu.comp3500.assignment3;

import academy.pocu.comp3500.assignment3.chess.Move;
import academy.pocu.comp3500.assignment3.chess.PlayerBase;

import java.util.ArrayList;

public class Player extends PlayerBase {
    private short round = 0;
    private final short DEPTH = 4;
    static private int count = 0;

    public Player(boolean isWhite, int maxMoveTimeMilliseconds) {
        super(isWhite, maxMoveTimeMilliseconds);
    }


    @Override
    public Move getNextMove(char[][] board) {
        if (round++ == 0) {
        }
        count = 0;
        Move move = getMove(board);

        System.out.println("count: " + count++);
        return move;
    }

    @Override
    public Move getNextMove(char[][] board, Move opponentMove) {
        if (round++ == 0) {

        }
        count = 0;
        Move move = getMove(board);

        System.out.println("count: " + count++);
        return move;
    }

    private Move getMove(char[][] board) {
        int a = Integer.MIN_VALUE;
        int b = Integer.MAX_VALUE;

        MoveScore result = new MoveScore();
        result.score = Integer.MIN_VALUE;
        int move = getMoveScoreRecursive(board, DEPTH, 0, true, result);
        return new Move(result.fromX, result.fromY, result.toX, result.toY);
    }

    private int getMoveScoreRecursive(char[][] board, int depth, int score, boolean isMax, MoveScore result) {
        if (depth <= 0) {
            return score;
        }

//        ArrayList<MoveScore> minimax = new ArrayList<>();
        MoveScore minimax = null;
        if (isMax) {
            int max = Integer.MIN_VALUE;
            for (int posX = 0; posX < 8; ++posX) {
                for (int posY = 0; posY < 8; ++posY) {
                    //max
                    //isWhite() : true; isLowercase : false;
                    //isWhite() : false; isLowercase : true;
                    if (Character.toLowerCase(board[posY][posX]) == 0 || Character.isLowerCase(board[posY][posX]) != isWhite()) {
                        continue;
                    }
                    int xIncrement = 1;
                    int yIncrement = 1;

                    switch (Character.toLowerCase(board[posY][posX])) {
                        case 'p':
                            boolean hasMoved = Character.isLowerCase(board[posY][posX]) ? posY != 6 : posY != 1;
                            int sign = Character.isLowerCase(board[posY][posX]) ? -1 : 1;
                            int i = hasMoved ? 1 : 0;

                            while (i < Position.PWAN.length) {
                                int x = Position.PWAN[i][0] + posX;
                                int y = sign * Position.PWAN[i][1] + posY;
                                i++;
                                if (x < 0 || y < 0 || x >= board[0].length || y >= board.length) {
                                    continue;
                                }
                                if (i == 1 && board[posY + sign][x] != 0) {
                                    continue;
                                }
                                if (posX == x && board[y][x] != 0) {
                                    continue;
                                }
                                if (posX != x && board[y][x] == 0) {
                                    continue;
                                }
                                if (board[y][x] != 0 && posX != x && Character.isLowerCase(board[y][x]) == Character.isLowerCase(board[posY][posX])) {
                                    continue;
                                }

                                int temp = getScore(board, x, y) * (isMax ? 1 : -1);
                                char c = move(board, posX, posY, x, y);
                                temp = getMoveScoreRecursive(board, depth - 1, temp, !isMax, result);

//                                minimax.add(new MoveScore(posX, posY, x, y, temp));
                                if (minimax == null) {
                                    minimax = new MoveScore(posX, posY, x, y, temp);
                                } else if (isMax) {
                                    if (minimax.score < temp) {
                                        minimax.score = temp;
                                        minimax.fromX = posX;
                                        minimax.fromY = posY;
                                        minimax.toX = x;
                                        minimax.toY = y;
                                    }
                                } else {
                                    if (minimax.score > temp) {
                                        minimax.score = temp;
                                        minimax.fromX = posX;
                                        minimax.fromY = posY;
                                        minimax.toX = x;
                                        minimax.toY = y;
                                    }
                                }
                                restore(board, x, y, posX, posY, c);
                            }
                            break;
                        case 'n':
//                            getKnightMove(board, x, y, max, minimax, depth, isMax, result);
                            for (int j = 0; j < Position.KNIGHT.length; ++j) {
                                int x = Position.KNIGHT[j][0] + posX;
                                int y = Position.KNIGHT[j][1] + posY;
                                if (x < 0 || y < 0 || x >= board[0].length || y >= board.length) {
                                    continue;
                                }
                                if (board[y][x] != 0 && Character.isLowerCase(board[y][x]) == Character.isLowerCase(board[posY][posX])) {
                                    continue;
                                }

                                int temp = getScore(board, x, y) * (isMax ? 1 : -1);
                                char c = move(board, posX, posY, x, y);
                                temp = getMoveScoreRecursive(board, depth - 1, temp, !isMax, result);
                                if (minimax == null) {
                                    minimax = new MoveScore(posX, posY, x, y, temp);
                                } else if (isMax) {
                                    if (minimax.score < temp) {
                                        minimax.score = temp;
                                        minimax.fromX = posX;
                                        minimax.fromY = posY;
                                        minimax.toX = x;
                                        minimax.toY = y;
                                    }
                                } else {
                                    if (minimax.score > temp) {
                                        minimax.score = temp;
                                        minimax.fromX = posX;
                                        minimax.fromY = posY;
                                        minimax.toX = x;
                                        minimax.toY = y;
                                    }
                                }

                                restore(board, x, y, posX, posY, c);
                            }
                            break;
                        case 'b':
//                            getBishopMove(board, x, y, max, minimax, depth, isMax, result);
                            xIncrement = 1;
                            yIncrement = 1;
                            for (int d = 0; d < 4; ++d) {
                                if (d == 1) {
                                    xIncrement = -1;
                                    yIncrement = 1;
                                } else if (d == 2) {
                                    xIncrement = 1;
                                    yIncrement = -1;
                                } else if (d == 3) {
                                    xIncrement = -1;
                                    yIncrement = -1;
                                }

                                int x = xIncrement + posX;
                                int y = yIncrement + posY;

                                while (x >= 0 && y >= 0 && x < board[0].length && y < board.length) {
                                    if (board[y][x] != 0 && Character.isLowerCase(board[y][x]) == Character.isLowerCase(board[posY][posX])) {
                                        break;
                                    }

                                    int temp = getScore(board, x, y) * (isMax ? 1 : -1);

                                    char c = move(board, posX, posY, x, y);
                                    temp = getMoveScoreRecursive(board, depth - 1, temp, !isMax, result);

                                    if (minimax == null) {
                                        minimax = new MoveScore(posX, posY, x, y, temp);
                                    } else if (isMax) {
                                        if (minimax.score < temp) {
                                            minimax.score = temp;
                                            minimax.fromX = posX;
                                            minimax.fromY = posY;
                                            minimax.toX = x;
                                            minimax.toY = y;
                                        }
                                    } else {
                                        if (minimax.score > temp) {
                                            minimax.score = temp;
                                            minimax.fromX = posX;
                                            minimax.fromY = posY;
                                            minimax.toX = x;
                                            minimax.toY = y;
                                        }
                                    }
                                    restore(board, x, y, posX, posY, c);

                                    if (board[y][x] != 0) {
                                        break;
                                    }

                                    x += xIncrement;
                                    y += yIncrement;
                                }
                            }
                            break;
                        case 'r':
//                            getRookMove(board, x, y, max, minimax, depth, isMax, result);
                            xIncrement = 1;
                            yIncrement = 0;

                            for (int d = 0; d < 4; ++d) {
                                if (d == 1) {
                                    xIncrement = -1;
                                    yIncrement = 0;
                                } else if (d == 2) {
                                    xIncrement = 0;
                                    yIncrement = -1;
                                } else if (d == 3) {
                                    xIncrement = 0;
                                    yIncrement = 1;
                                }

                                int x = xIncrement + posX;
                                int y = yIncrement + posY;

                                while (x >= 0 && y >= 0 && x < board[0].length && y < board.length) {
                                    if (board[y][x] != 0 && Character.isLowerCase(board[y][x]) == Character.isLowerCase(board[posY][posX])) {
                                        break;
                                    }

                                    int temp = getScore(board, x, y) * (isMax ? 1 : -1);

                                    char c = move(board, posX, posY, x, y);
                                    temp = getMoveScoreRecursive(board, depth - 1, temp, !isMax, result);

                                    if (minimax == null) {
                                        minimax = new MoveScore(posX, posY, x, y, temp);
                                    } else if (isMax) {
                                        if (minimax.score < temp) {
                                            minimax.score = temp;
                                            minimax.fromX = posX;
                                            minimax.fromY = posY;
                                            minimax.toX = x;
                                            minimax.toY = y;
                                        }
                                    } else {
                                        if (minimax.score > temp) {
                                            minimax.score = temp;
                                            minimax.fromX = posX;
                                            minimax.fromY = posY;
                                            minimax.toX = x;
                                            minimax.toY = y;
                                        }
                                    }
                                    restore(board, x, y, posX, posY, c);

                                    if (board[y][x] != 0) {
                                        break;
                                    }

                                    x += xIncrement;
                                    y += yIncrement;
                                }
                            }
                            break;
                        case 'q':
//                            getBishopMove(board, x, y, max, minimax, depth, isMax, result);
//                            getRookMove(board, x, y, max, minimax, depth, isMax, result);
                            xIncrement = 1;
                            yIncrement = 1;
                            for (int d = 0; d < 4; ++d) {
                                if (d == 1) {
                                    xIncrement = -1;
                                    yIncrement = 1;
                                } else if (d == 2) {
                                    xIncrement = 1;
                                    yIncrement = -1;
                                } else if (d == 3) {
                                    xIncrement = -1;
                                    yIncrement = -1;
                                }

                                int x = xIncrement + posX;
                                int y = yIncrement + posY;

                                while (x >= 0 && y >= 0 && x < board[0].length && y < board.length) {
                                    if (board[y][x] != 0 && Character.isLowerCase(board[y][x]) == Character.isLowerCase(board[posY][posX])) {
                                        break;
                                    }

                                    int temp = getScore(board, x, y) * (isMax ? 1 : -1);

                                    char c = move(board, posX, posY, x, y);
                                    temp = getMoveScoreRecursive(board, depth - 1, temp, !isMax, result);

                                    if (minimax == null) {
                                        minimax = new MoveScore(posX, posY, x, y, temp);
                                    } else if (isMax) {
                                        if (minimax.score < temp) {
                                            minimax.score = temp;
                                            minimax.fromX = posX;
                                            minimax.fromY = posY;
                                            minimax.toX = x;
                                            minimax.toY = y;
                                        }
                                    } else {
                                        if (minimax.score > temp) {
                                            minimax.score = temp;
                                            minimax.fromX = posX;
                                            minimax.fromY = posY;
                                            minimax.toX = x;
                                            minimax.toY = y;
                                        }
                                    }
                                    restore(board, x, y, posX, posY, c);

                                    if (board[y][x] != 0) {
                                        break;
                                    }

                                    x += xIncrement;
                                    y += yIncrement;
                                }
                            }

                            xIncrement = 1;
                            yIncrement = 0;
                            for (int d = 0; d < 4; ++d) {
                                if (d == 1) {
                                    xIncrement = -1;
                                    yIncrement = 0;
                                } else if (d == 2) {
                                    xIncrement = 0;
                                    yIncrement = -1;
                                } else if (d == 3) {
                                    xIncrement = 0;
                                    yIncrement = 1;
                                }

                                int x = xIncrement + posX;
                                int y = yIncrement + posY;

                                while (x >= 0 && y >= 0 && x < board[0].length && y < board.length) {
                                    if (board[y][x] != 0 && Character.isLowerCase(board[y][x]) == Character.isLowerCase(board[posY][posX])) {
                                        break;
                                    }

                                    int temp = getScore(board, x, y) * (isMax ? 1 : -1);

                                    char c = move(board, posX, posY, x, y);
                                    temp = getMoveScoreRecursive(board, depth - 1, temp, !isMax, result);

                                    if (minimax == null) {
                                        minimax = new MoveScore(posX, posY, x, y, temp);
                                    } else if (isMax) {
                                        if (minimax.score < temp) {
                                            minimax.score = temp;
                                            minimax.fromX = posX;
                                            minimax.fromY = posY;
                                            minimax.toX = x;
                                            minimax.toY = y;
                                        }
                                    } else {
                                        if (minimax.score > temp) {
                                            minimax.score = temp;
                                            minimax.fromX = posX;
                                            minimax.fromY = posY;
                                            minimax.toX = x;
                                            minimax.toY = y;
                                        }
                                    }
                                    restore(board, x, y, posX, posY, c);

                                    if (board[y][x] != 0) {
                                        break;
                                    }

                                    x += xIncrement;
                                    y += yIncrement;
                                }
                            }
                            break;
                        case 'k':
//                            getKingMove(board, x, y, max, minimax, depth, isMax, result);
                            for (i = 0; i < Position.KING.length; ++i) {
                                int x = Position.KING[i][0] + posX;
                                int y = Position.KING[i][1] + posY;

                                if (x < 0 || y < 0 || x >= board[0].length || y >= board.length) {
                                    continue;
                                }

                                if (board[y][x] != 0 && Character.isLowerCase(board[y][x]) == Character.isLowerCase(board[posY][posX])) {
                                    continue;
                                }

                                int temp = getScore(board, x, y) * (isMax ? 1 : -1);

                                char c = move(board, posX, posY, x, y);
                                temp = getMoveScoreRecursive(board, depth - 1, temp, !isMax, result);

                                if (minimax == null) {
                                    minimax = new MoveScore(posX, posY, x, y, temp);
                                } else if (isMax) {
                                    if (minimax.score < temp) {
                                        minimax.score = temp;
                                        minimax.fromX = posX;
                                        minimax.fromY = posY;
                                        minimax.toX = x;
                                        minimax.toY = y;
                                    }
                                } else {
                                    if (minimax.score > temp) {
                                        minimax.score = temp;
                                        minimax.fromX = posX;
                                        minimax.fromY = posY;
                                        minimax.toX = x;
                                        minimax.toY = y;
                                    }
                                }
                                restore(board, x, y, posX, posY, c);
                            }
                            break;
                        default:
                            return Integer.MIN_VALUE;
                    }
                }
            }
//            if (minimax.size() > 0) {
//                max = Integer.MIN_VALUE;
//                MoveScore move = null;
//                for (MoveScore m : minimax) {
//                    if (m.score > max) {
//                        max = m.score;
//                        move = m;
//                    }
//                }
//
//                result.fromX = move.fromX;
//                result.fromY = move.fromY;
//                result.toX = move.toX;
//                result.toY = move.toY;
//                result.score = move.score;
//            }
            if (minimax != null) {
                result.fromX = minimax.fromX;
                result.fromY = minimax.fromY;
                result.toX = minimax.toX;
                result.toY = minimax.toY;
                result.score = minimax.score;
                return result.score;
            }

            return score;
        } else {
            int min = Integer.MAX_VALUE;
            for (int posX = 0; posX < 8; ++posX) {
                for (int posY = 0; posY < 8; ++posY) {
                    //min
                    //isWhite() : true; isLowercase : true;
                    //isWhite() : false; isLowercase : false;
                    if (Character.toLowerCase(board[posY][posX]) == 0 || Character.isLowerCase(board[posY][posX]) == isWhite()) {
                        continue;
                    }

                    int xIncrement = 1;
                    int yIncrement = 1;

                    switch (Character.toLowerCase(board[posY][posX])) {
                        case 'p':
                            boolean hasMoved = Character.isLowerCase(board[posY][posX]) ? posY != 6 : posY != 1;
                            int sign = Character.isLowerCase(board[posY][posX]) ? -1 : 1;
                            int i = hasMoved ? 1 : 0;

                            while (i < Position.PWAN.length) {
                                int x = Position.PWAN[i][0] + posX;
                                int y = sign * Position.PWAN[i][1] + posY;
                                i++;
                                if (x < 0 || y < 0 || x >= board[0].length || y >= board.length) {
                                    continue;
                                }
                                if (i == 1 && board[posY + sign][x] != 0) {
                                    continue;
                                }
                                if (posX == x && board[y][x] != 0) {
                                    continue;
                                }
                                if (posX != x && board[y][x] == 0) {
                                    continue;
                                }
                                if (board[y][x] != 0 && posX != x && Character.isLowerCase(board[y][x]) == Character.isLowerCase(board[posY][posX])) {
                                    continue;
                                }

                                int temp = getScore(board, x, y) * (isMax ? 1 : -1);
                                char c = move(board, posX, posY, x, y);
                                temp = getMoveScoreRecursive(board, depth - 1, temp, !isMax, result);

                                if (minimax == null) {
                                    minimax = new MoveScore(posX, posY, x, y, temp);
                                } else if (isMax) {
                                    if (minimax.score < temp) {
                                        minimax.score = temp;
                                        minimax.fromX = posX;
                                        minimax.fromY = posY;
                                        minimax.toX = x;
                                        minimax.toY = y;
                                    }
                                } else {
                                    if (minimax.score > temp) {
                                        minimax.score = temp;
                                        minimax.fromX = posX;
                                        minimax.fromY = posY;
                                        minimax.toX = x;
                                        minimax.toY = y;
                                    }
                                }
                                restore(board, x, y, posX, posY, c);
                            }
                            break;
                        case 'n':
//                            getKnightMove(board, x, y, max, minimax, depth, isMax, result);
                            for (int j = 0; j < Position.KNIGHT.length; ++j) {
                                int x = Position.KNIGHT[j][0] + posX;
                                int y = Position.KNIGHT[j][1] + posY;
                                if (x < 0 || y < 0 || x >= board[0].length || y >= board.length) {
                                    continue;
                                }
                                if (board[y][x] != 0 && Character.isLowerCase(board[y][x]) == Character.isLowerCase(board[posY][posX])) {
                                    continue;
                                }

                                int temp = getScore(board, x, y) * (isMax ? 1 : -1);
                                char c = move(board, posX, posY, x, y);
                                temp = getMoveScoreRecursive(board, depth - 1, temp, !isMax, result);
                                if (minimax == null) {
                                    minimax = new MoveScore(posX, posY, x, y, temp);
                                } else if (isMax) {
                                    if (minimax.score < temp) {
                                        minimax.score = temp;
                                        minimax.fromX = posX;
                                        minimax.fromY = posY;
                                        minimax.toX = x;
                                        minimax.toY = y;
                                    }
                                } else {
                                    if (minimax.score > temp) {
                                        minimax.score = temp;
                                        minimax.fromX = posX;
                                        minimax.fromY = posY;
                                        minimax.toX = x;
                                        minimax.toY = y;
                                    }
                                }

                                restore(board, x, y, posX, posY, c);
                            }
                            break;
                        case 'b':
//                            getBishopMove(board, x, y, max, minimax, depth, isMax, result);
                            xIncrement = 1;
                            yIncrement = 1;
                            for (int d = 0; d < 4; ++d) {
                                if (d == 1) {
                                    xIncrement = -1;
                                    yIncrement = 1;
                                } else if (d == 2) {
                                    xIncrement = 1;
                                    yIncrement = -1;
                                } else if (d == 3) {
                                    xIncrement = -1;
                                    yIncrement = -1;
                                }

                                int x = xIncrement + posX;
                                int y = yIncrement + posY;

                                while (x >= 0 && y >= 0 && x < board[0].length && y < board.length) {
                                    if (board[y][x] != 0 && Character.isLowerCase(board[y][x]) == Character.isLowerCase(board[posY][posX])) {
                                        break;
                                    }

                                    int temp = getScore(board, x, y) * (isMax ? 1 : -1);

                                    char c = move(board, posX, posY, x, y);
                                    temp = getMoveScoreRecursive(board, depth - 1, temp, !isMax, result);

                                    if (minimax == null) {
                                        minimax = new MoveScore(posX, posY, x, y, temp);
                                    } else if (isMax) {
                                        if (minimax.score < temp) {
                                            minimax.score = temp;
                                            minimax.fromX = posX;
                                            minimax.fromY = posY;
                                            minimax.toX = x;
                                            minimax.toY = y;
                                        }
                                    } else {
                                        if (minimax.score > temp) {
                                            minimax.score = temp;
                                            minimax.fromX = posX;
                                            minimax.fromY = posY;
                                            minimax.toX = x;
                                            minimax.toY = y;
                                        }
                                    }
                                    restore(board, x, y, posX, posY, c);

                                    if (board[y][x] != 0) {
                                        break;
                                    }

                                    x += xIncrement;
                                    y += yIncrement;
                                }
                            }
                            break;
                        case 'r':
//                            getRookMove(board, x, y, max, minimax, depth, isMax, result);
                            xIncrement = 1;
                            yIncrement = 0;

                            for (int d = 0; d < 4; ++d) {
                                if (d == 1) {
                                    xIncrement = -1;
                                    yIncrement = 0;
                                } else if (d == 2) {
                                    xIncrement = 0;
                                    yIncrement = -1;
                                } else if (d == 3) {
                                    xIncrement = 0;
                                    yIncrement = 1;
                                }

                                int x = xIncrement + posX;
                                int y = yIncrement + posY;

                                while (x >= 0 && y >= 0 && x < board[0].length && y < board.length) {
                                    if (board[y][x] != 0 && Character.isLowerCase(board[y][x]) == Character.isLowerCase(board[posY][posX])) {
                                        break;
                                    }

                                    int temp = getScore(board, x, y) * (isMax ? 1 : -1);

                                    char c = move(board, posX, posY, x, y);
                                    temp = getMoveScoreRecursive(board, depth - 1, temp, !isMax, result);

                                    if (minimax == null) {
                                        minimax = new MoveScore(posX, posY, x, y, temp);
                                    } else if (isMax) {
                                        if (minimax.score < temp) {
                                            minimax.score = temp;
                                            minimax.fromX = posX;
                                            minimax.fromY = posY;
                                            minimax.toX = x;
                                            minimax.toY = y;
                                        }
                                    } else {
                                        if (minimax.score > temp) {
                                            minimax.score = temp;
                                            minimax.fromX = posX;
                                            minimax.fromY = posY;
                                            minimax.toX = x;
                                            minimax.toY = y;
                                        }
                                    }
                                    restore(board, x, y, posX, posY, c);

                                    if (board[y][x] != 0) {
                                        break;
                                    }

                                    x += xIncrement;
                                    y += yIncrement;
                                }
                            }
                            break;
                        case 'q':
//                            getBishopMove(board, x, y, max, minimax, depth, isMax, result);
//                            getRookMove(board, x, y, max, minimax, depth, isMax, result);
                            xIncrement = 1;
                            yIncrement = 1;
                            for (int d = 0; d < 4; ++d) {
                                if (d == 1) {
                                    xIncrement = -1;
                                    yIncrement = 1;
                                } else if (d == 2) {
                                    xIncrement = 1;
                                    yIncrement = -1;
                                } else if (d == 3) {
                                    xIncrement = -1;
                                    yIncrement = -1;
                                }

                                int x = xIncrement + posX;
                                int y = yIncrement + posY;

                                while (x >= 0 && y >= 0 && x < board[0].length && y < board.length) {
                                    if (board[y][x] != 0 && Character.isLowerCase(board[y][x]) == Character.isLowerCase(board[posY][posX])) {
                                        break;
                                    }

                                    int temp = getScore(board, x, y) * (isMax ? 1 : -1);

                                    char c = move(board, posX, posY, x, y);
                                    temp = getMoveScoreRecursive(board, depth - 1, temp, !isMax, result);

                                    if (minimax == null) {
                                        minimax = new MoveScore(posX, posY, x, y, temp);
                                    } else if (isMax) {
                                        if (minimax.score < temp) {
                                            minimax.score = temp;
                                            minimax.fromX = posX;
                                            minimax.fromY = posY;
                                            minimax.toX = x;
                                            minimax.toY = y;
                                        }
                                    } else {
                                        if (minimax.score > temp) {
                                            minimax.score = temp;
                                            minimax.fromX = posX;
                                            minimax.fromY = posY;
                                            minimax.toX = x;
                                            minimax.toY = y;
                                        }
                                    }
                                    restore(board, x, y, posX, posY, c);

                                    if (board[y][x] != 0) {
                                        break;
                                    }

                                    x += xIncrement;
                                    y += yIncrement;
                                }
                            }

                            xIncrement = 1;
                            yIncrement = 0;
                            for (int d = 0; d < 4; ++d) {
                                if (d == 1) {
                                    xIncrement = -1;
                                    yIncrement = 0;
                                } else if (d == 2) {
                                    xIncrement = 0;
                                    yIncrement = -1;
                                } else if (d == 3) {
                                    xIncrement = 0;
                                    yIncrement = 1;
                                }

                                int x = xIncrement + posX;
                                int y = yIncrement + posY;

                                while (x >= 0 && y >= 0 && x < board[0].length && y < board.length) {
                                    if (board[y][x] != 0 && Character.isLowerCase(board[y][x]) == Character.isLowerCase(board[posY][posX])) {
                                        break;
                                    }

                                    int temp = getScore(board, x, y) * (isMax ? 1 : -1);

                                    char c = move(board, posX, posY, x, y);
                                    temp = getMoveScoreRecursive(board, depth - 1, temp, !isMax, result);

                                    if (minimax == null) {
                                        minimax = new MoveScore(posX, posY, x, y, temp);
                                    } else if (isMax) {
                                        if (minimax.score < temp) {
                                            minimax.score = temp;
                                            minimax.fromX = posX;
                                            minimax.fromY = posY;
                                            minimax.toX = x;
                                            minimax.toY = y;
                                        }
                                    } else {
                                        if (minimax.score > temp) {
                                            minimax.score = temp;
                                            minimax.fromX = posX;
                                            minimax.fromY = posY;
                                            minimax.toX = x;
                                            minimax.toY = y;
                                        }
                                    }
                                    restore(board, x, y, posX, posY, c);

                                    if (board[y][x] != 0) {
                                        break;
                                    }

                                    x += xIncrement;
                                    y += yIncrement;
                                }
                            }
                            break;
                        case 'k':
//                            getKingMove(board, x, y, max, minimax, depth, isMax, result);
                            for (i = 0; i < Position.KING.length; ++i) {
                                int x = Position.KING[i][0] + posX;
                                int y = Position.KING[i][1] + posY;

                                if (x < 0 || y < 0 || x >= board[0].length || y >= board.length) {
                                    continue;
                                }

                                if (board[y][x] != 0 && Character.isLowerCase(board[y][x]) == Character.isLowerCase(board[posY][posX])) {
                                    continue;
                                }

                                int temp = getScore(board, x, y) * (isMax ? 1 : -1);

                                char c = move(board, posX, posY, x, y);
                                temp = getMoveScoreRecursive(board, depth - 1, temp, !isMax, result);

                                if (minimax == null) {
                                    minimax = new MoveScore(posX, posY, x, y, temp);
                                } else if (isMax) {
                                    if (minimax.score < temp) {
                                        minimax.score = temp;
                                        minimax.fromX = posX;
                                        minimax.fromY = posY;
                                        minimax.toX = x;
                                        minimax.toY = y;
                                    }
                                } else {
                                    if (minimax.score > temp) {
                                        minimax.score = temp;
                                        minimax.fromX = posX;
                                        minimax.fromY = posY;
                                        minimax.toX = x;
                                        minimax.toY = y;
                                    }
                                }
                                restore(board, x, y, posX, posY, c);
                            }
                            break;
                        default:
                            return Integer.MIN_VALUE;
                    }
                }
            }

//            if (minimax.size() > 0) {
//                min = Integer.MAX_VALUE;
//                MoveScore move = null;
//                for (MoveScore m : minimax) {
//                    if (m.score < min) {
//                        min = m.score;
//                        move = m;
//                    }
//                }
            if (minimax != null) {
                result.fromX = minimax.fromX;
                result.fromY = minimax.fromY;
                result.toX = minimax.toX;
                result.toY = minimax.toY;
                result.score = minimax.score;
                return minimax.score;
            }

            return score;
        }
    }

    // a < v < b
    private int getPawnMove(char[][] board, int posX, int posY, int score, ArrayList<MoveScore> list, int depth, boolean isMax, MoveScore result) {
        boolean hasMoved = Character.isLowerCase(board[posY][posX]) ? posY != 6 : posY != 1;
        int sign = Character.isLowerCase(board[posY][posX]) ? -1 : 1;
        int i = hasMoved ? 1 : 0;

        while (i < Position.PWAN.length) {
            int x = Position.PWAN[i][0] + posX;
            int y = sign * Position.PWAN[i][1] + posY;
            i++;
            if (x < 0 || y < 0 || x >= board[0].length || y >= board.length) {
                continue;
            }

            if (i == 1 && board[posY + sign][x] != 0) {
                continue;
            }

            if (posX == x && board[y][x] != 0) {
                continue;
            }

            if (posX != x && board[y][x] == 0) {
                continue;
            }

            if (board[y][x] != 0 && posX != x && Character.isLowerCase(board[y][x]) == Character.isLowerCase(board[posY][posX])) {
                continue;
            }

            int temp = getScore(board, x, y) * (isMax ? 1 : -1);

            char c = move(board, posX, posY, x, y);
            temp = getMoveScoreRecursive(board, depth - 1, temp, !isMax, result);

            list.add(new MoveScore(posX, posY, x, y, temp));
            count++;
            restore(board, x, y, posX, posY, c);

            if (isMax) {
                if (score < temp) {
                    score = temp;
                }
            } else {
                if (score > temp) {
                    score = temp;
                }
            }

        }

        return score;
    }

    private int getKnightMove(char[][] board, int posX, int posY, int score, ArrayList<MoveScore> list, int depth, boolean isMax, MoveScore result) {
//        int score = isMax ? a : b;
        int bestX = -1;
        int bestY = -1;

        for (int i = 0; i < Position.KNIGHT.length; ++i) {
            int x = Position.KNIGHT[i][0] + posX;
            int y = Position.KNIGHT[i][1] + posY;

            if (x < 0 || y < 0 || x >= board[0].length || y >= board.length) {
                continue;
            }

            if (board[y][x] != 0 && Character.isLowerCase(board[y][x]) == Character.isLowerCase(board[posY][posX])) {
                continue;
            }

            int temp = getScore(board, x, y) * (isMax ? 1 : -1);

            bestX = x;
            bestY = y;
            char c = move(board, posX, posY, bestX, bestY);
            temp = getMoveScoreRecursive(board, depth - 1, temp, !isMax, result);
            count++;

            list.add(new MoveScore(posX, posY, x, y, temp));

            restore(board, bestX, bestY, posX, posY, c);

            if (isMax) {
                if (score < temp) {
                    score = temp;
                }
            } else {
                if (score > temp) {
                    score = temp;
                }
            }
        }

        return score;
    }

    private int getBishopMove(char[][] board, int posX, int posY, int score, ArrayList<MoveScore> list, int depth, boolean isMax, MoveScore result) {
        int xIncrement = 1;
        int yIncrement = 1;
//        int score = isMax ? a : b;
        int bestX = -1;
        int bestY = -1;

        for (int d = 0; d < 4; ++d) {
            if (d == 1) {
                xIncrement = -1;
                yIncrement = 1;
            } else if (d == 2) {
                xIncrement = 1;
                yIncrement = -1;
            } else if (d == 3) {
                xIncrement = -1;
                yIncrement = -1;
            }

            int x = xIncrement + posX;
            int y = yIncrement + posY;

            while (x >= 0 && y >= 0 && x < board[0].length && y < board.length) {
                if (board[y][x] != 0 && Character.isLowerCase(board[y][x]) == Character.isLowerCase(board[posY][posX])) {
                    break;
                }

                int temp = getScore(board, x, y) * (isMax ? 1 : -1);

                bestX = x;
                bestY = y;
                char c = move(board, posX, posY, bestX, bestY);
                temp = getMoveScoreRecursive(board, depth - 1, temp, !isMax, result);

                list.add(new MoveScore(posX, posY, x, y, temp));
                count++;

                restore(board, bestX, bestY, posX, posY, c);

                if (isMax) {
                    if (score < temp) {
                        score = temp;
                    }
                } else {
                    if (score > temp) {
                        score = temp;
                    }
                }

                if (board[y][x] != 0) {
                    break;
                }

                x += xIncrement;
                y += yIncrement;
            }
        }
        return score;
    }

    private int getRookMove(char[][] board, int posX, int posY, int score, ArrayList<MoveScore> list, int depth, boolean isMax, MoveScore result) {
        int xIncrement = 1;
        int yIncrement = 0;
//        int score = isMax ? a : b;
        int bestX = -1;
        int bestY = -1;

        for (int d = 0; d < 4; ++d) {
            if (d == 1) {
                xIncrement = -1;
                yIncrement = 0;
            } else if (d == 2) {
                xIncrement = 0;
                yIncrement = -1;
            } else if (d == 3) {
                xIncrement = 0;
                yIncrement = 1;
            }

            int x = xIncrement + posX;
            int y = yIncrement + posY;

            while (x >= 0 && y >= 0 && x < board[0].length && y < board.length) {
                if (board[y][x] != 0 && Character.isLowerCase(board[y][x]) == Character.isLowerCase(board[posY][posX])) {
                    break;
                }

                int temp = getScore(board, x, y) * (isMax ? 1 : -1);

                bestX = x;
                bestY = y;
                char c = move(board, posX, posY, bestX, bestY);
                temp = getMoveScoreRecursive(board, depth - 1, temp, !isMax, result);

                list.add(new MoveScore(posX, posY, x, y, temp));
                count++;

                restore(board, bestX, bestY, posX, posY, c);

                if (isMax) {
                    if (score < temp) {
                        score = temp;
                    }
                } else {
                    if (score > temp) {
                        score = temp;
                    }
                }

                if (board[y][x] != 0) {
                    break;
                }

                x += xIncrement;
                y += yIncrement;
            }
        }

        return score;
    }

    private int getKingMove(char[][] board, int posX, int posY, int score, ArrayList<MoveScore> list, int depth, boolean isMax, MoveScore result) {
//        int score = isMax ? a : b;
        int bestX = -1;
        int bestY = -1;

        for (int i = 0; i < Position.KING.length; ++i) {
            int x = Position.KING[i][0] + posX;
            int y = Position.KING[i][1] + posY;

            if (x < 0 || y < 0 || x >= board[0].length || y >= board.length) {
                continue;
            }

            if (board[y][x] != 0 && Character.isLowerCase(board[y][x]) == Character.isLowerCase(board[posY][posX])) {
                continue;
            }

            int temp = getScore(board, x, y) * (isMax ? 1 : -1);

            bestX = x;
            bestY = y;
            char c = move(board, posX, posY, bestX, bestY);
            temp = getMoveScoreRecursive(board, depth - 1, temp, !isMax, result);

            list.add(new MoveScore(posX, posY, x, y, temp));
            count++;

            restore(board, bestX, bestY, posX, posY, c);

            if (isMax) {
                if (score < temp) {
                    score = temp;
                }
            } else {
                if (score > temp) {
                    score = temp;
                }
            }
        }

        return score;
    }

    private Move movePos(int fromX, int fromY, int toX, int toY) {

        return new Move(fromX, fromY, toX, toY);
    }

    private char move(char[][] board, int fromX, int fromY, int toX, int toY) {
//        System.out.println(fromX + "," + fromY + "," + toX + "," + toY);
        char c = board[toY][toX];
        board[toY][toX] = board[fromY][fromX];
        board[fromY][fromX] = 0;
        return c;
    }

    private void restore(char[][] board, int fromX, int fromY, int toX, int toY, char backWord) {
        board[toY][toX] = board[fromY][fromX];
        board[fromY][fromX] = backWord;
    }

    private int getScore(char[][] board, int x, int y) {
        char c = Character.toLowerCase(board[y][x]);

        switch (c) {
            case 'p':
                return 10;
            case 'n':
            case 'b':
                return 30;
            case 'r':
                return 50;
            case 'q':
                return 90;
            case 'k':
                return 100;
            default:
                return 0;
        }
    }
}

/*
1. 알파베타 학습
2. 나(max) -> 상대(min) -> 나(max) -> 상대(min) -> -> ->
3. 해당 말들의 한턴당 이동가능 자리 탐색
4. 이동할 자리 점수 계산
 */