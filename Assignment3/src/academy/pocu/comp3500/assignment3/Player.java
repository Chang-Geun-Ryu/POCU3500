package academy.pocu.comp3500.assignment3;

import academy.pocu.comp3500.assignment3.chess.Move;
import academy.pocu.comp3500.assignment3.chess.PlayerBase;

import java.util.ArrayList;

public class Player extends PlayerBase {
    private short round = 0;
    private final short DEPTH = 8;

    public Player(boolean isWhite, int maxMoveTimeMilliseconds) {
        super(isWhite, maxMoveTimeMilliseconds);
    }


    @Override
    public Move getNextMove(char[][] board) {
        if (round++ == 0) {
        }

        if (isWhite()) {
            int isKingPosX = 3;
            for (int i = 0; i < 8; ++i) {
                if (board[7][i] == 'k') {
                    isKingPosX = i;
                    break;
                }
            }

            return movePos(isKingPosX, 6, isKingPosX, 4);
        } else {
            return movePos(3, 1, 3, 3);
        }
    }

    @Override
    public Move getNextMove(char[][] board, Move opponentMove) {
        if (round++ == 0) {

        }

        if (round <= 2) {
            if (isWhite()) {
                if (round == 2) {

                    return movePos(3, 4, 2, 3);
                }
            } else {
                if (round == 1) {
                    return movePos(2, 1, 2, 3);
                } else {
                    return movePos(3, 1, 3, 2);
                }
            }
        }

        Move move = getMove(board);

        return move;
    }

    private Move getMove(char[][] board) {
        int a = Integer.MIN_VALUE;
        int b = Integer.MAX_VALUE;

        MoveScore result = new MoveScore();
        result.score = Integer.MIN_VALUE;
        int move = getMoveScoreRecursive(board, DEPTH, a, b, true, result);
        return new Move(result.fromX, result.fromY, result.toX, result.toY);
    }

    private int getMoveScoreRecursive(char[][] board, int depth, int a, int b, boolean isMax, MoveScore result) {
        if (depth <= 0) {
            return isMax ? b : a;
        }

        if (isMax) {
            int max = Integer.MIN_VALUE;
            for (int x = 0; x < 8; ++x) {
                for (int y = 0; y < 8; ++y) {
                    //max
                    //isWhite() : true; isLowercase : false;
                    //isWhite() : false; isLowercase : true;
                    if (Character.toLowerCase(board[y][x]) == 0 || Character.isLowerCase(board[y][x]) != isWhite()) {
                        continue;
                    }

                    switch (Character.toLowerCase(board[y][x])) {
                        case 'p':
                            max = Math.max(max, getPawnMove(board, x, y, a, b, depth, isMax, result));
                            break;
                        case 'n':
                            max = Math.max(max, getKnightMove(board, x, y, a, b, depth, isMax, result));
                            break;
                        case 'b':
                            max = Math.max(max, getBishopMove(board, x, y, a, b, depth, isMax, result));
                            break;
                        case 'r':
                            max = Math.max(max, getRookMove(board, x, y, a, b, depth, isMax, result));
                            break;
                        case 'q':
                            max = Math.max(max, getBishopMove(board, x, y, a, b, depth, isMax, result));
                            max = Math.max(max, getRookMove(board, x, y, a, b, depth, isMax, result));
                            break;
                        case 'k':
                            max = Math.max(max, getKingMove(board, x, y, a, b, depth, isMax, result));
                            break;
                        default:
                            return Integer.MIN_VALUE;
                    }
                    a = Math.max(a, max);
                    if (b <= a) {
                        break;
                    }
                }
                if (b <= a) {
                    break;
                }
            }

            return max;
        } else {
            int min = Integer.MAX_VALUE;
            for (int x = 0; x < 8; ++x) {
                for (int y = 0; y < 8; ++y) {
                    //min
                    //isWhite() : true; isLowercase : true;
                    //isWhite() : false; isLowercase : false;
                    if (Character.toLowerCase(board[y][x]) == 0 || Character.isLowerCase(board[y][x]) == isWhite()) {
                        continue;
                    }

                    switch (Character.toLowerCase(board[y][x])) {
                        case 'p':
                            min = Math.min(min, getPawnMove(board, x, y, a, b, depth, isMax, result));
                            break;
                        case 'n':
                            min = Math.min(min, getKnightMove(board, x, y, a, b, depth, isMax, result));
                            break;
                        case 'b':
                            min = Math.min(min, getBishopMove(board, x, y, a, b, depth, isMax, result));
                            break;
                        case 'r':
                            min = Math.min(min, getRookMove(board, x, y, a, b, depth, isMax, result));
                            break;
                        case 'q':
                            min = Math.min(min, getBishopMove(board, x, y, a, b, depth, isMax, result));
                            min = Math.min(min, getRookMove(board, x, y, a, b, depth, isMax, result));
                            break;
                        case 'k':
                            min = Math.min(min, getKingMove(board, x, y, a, b, depth, isMax, result));
                            break;
                        default:
                            return Integer.MAX_VALUE;
                    }
                    b = Math.min(min, b);

                    if (b <= a) {
                        break;
                    }
                }

                if (b <= a) {
                    break;
                }
            }

            return min;
        }
    }

    // a < v < b
    private int getPawnMove(char[][] board, int posX, int posY, int a, int b, int depth, boolean isMax, MoveScore result) {
        boolean hasMoved = Character.isLowerCase(board[posY][posX]) ? posY != 6 : posY != 1;
        int sign = Character.isLowerCase(board[posY][posX]) ? -1 : 1;
        int i = hasMoved ? 1 : 0;
        int score = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int bestX = -1;
        int bestY = -1;

        while (i < Position.PWAN.length) {
            int x = Position.PWAN[i][0] + posX;
            int y = sign * Position.PWAN[i][1] + posY;
            ++i;

            if (x < 0 || y < 0 || x >= board[0].length || y >= board.length) {
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

            int temp = getScore(board, x, y);
            if (isMax) {
                a = Math.max(a, temp);
            } else {
                b = Math.min(b, temp);
            }

            bestX = x;
            bestY = y;
            char c = move(board, posX, posY, bestX, bestY);
            temp = getMoveScoreRecursive(board, depth - 1, a, b, !isMax, result);

            if (depth == DEPTH && result.score < temp && (posX - bestX != 0 || posY - bestY != 0)) {
                result.score = score;
                result.fromX = posX;
                result.fromY = posY;
                result.toX = bestX;
                result.toY = bestY;
                score = temp;
            }
            restore(board, bestX, bestY, posX, posY, c);
        }

        return score;
    }

    private int getKnightMove(char[][] board, int posX, int posY, int a, int b, int depth, boolean isMax, MoveScore result) {
        int score = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
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

            int temp = getScore(board, x, y);

            if (isMax) {
                a = Math.max(a, temp);
            } else {
                b = Math.min(b, temp);
            }

            bestX = x;
            bestY = y;
            char c = move(board, posX, posY, bestX, bestY);
            temp = getMoveScoreRecursive(board, depth - 1, a, b, !isMax, result);

            if (depth == DEPTH && result.score < temp && (posX - bestX != 0 || posY - bestY != 0)) {
                result.score = score;
                result.fromX = posX;
                result.fromY = posY;
                result.toX = bestX;
                result.toY = bestY;
                score = temp;
            }
            restore(board, bestX, bestY, posX, posY, c);
        }

        return score;
    }

    private int getBishopMove(char[][] board, int posX, int posY, int a, int b, int depth, boolean isMax, MoveScore result) {
        int xIncrement = 1;
        int yIncrement = 1;
        int score = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
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

                int temp = getScore(board, x, y);

                if (isMax) {
                    a = Math.max(a, temp);
                } else {
                    b = Math.min(b, temp);
                }

                bestX = x;
                bestY = y;
                char c = move(board, posX, posY, bestX, bestY);
                temp = getMoveScoreRecursive(board, depth - 1, a, b, !isMax, result);

                if (depth == DEPTH && result.score < temp && (posX - bestX != 0 || posY - bestY != 0)) {
                    result.score = score;
                    result.fromX = posX;
                    result.fromY = posY;
                    result.toX = bestX;
                    result.toY = bestY;
                    score = temp;
                }
                restore(board, bestX, bestY, posX, posY, c);

                if (board[y][x] != 0) {
                    break;
                }

                x += xIncrement;
                y += yIncrement;
            }
        }
        return score;
    }

    private int getRookMove(char[][] board, int posX, int posY, int a, int b, int depth, boolean isMax, MoveScore result) {
        int xIncrement = 1;
        int yIncrement = 0;
        int score = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
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

                int temp = getScore(board, x, y);

                if (isMax) {
                    a = Math.max(a, temp);
                } else {
                    b = Math.min(b, temp);
                }

                bestX = x;
                bestY = y;
                char c = move(board, posX, posY, bestX, bestY);
                temp = getMoveScoreRecursive(board, depth - 1, a, b, !isMax, result);

                if (depth == DEPTH && result.score < temp && (posX - bestX != 0 || posY - bestY != 0)) {
                    result.score = score;
                    result.fromX = posX;
                    result.fromY = posY;
                    result.toX = bestX;
                    result.toY = bestY;
                    score = temp;
                }
                restore(board, bestX, bestY, posX, posY, c);

                if (board[y][x] != 0) {
                    break;
                }

                x += xIncrement;
                y += yIncrement;
            }
        }

        return score;
    }

    private int getKingMove(char[][] board, int posX, int posY, int a, int b, int depth, boolean isMax, MoveScore result) {
        int score = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
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

            int temp = getScore(board, x, y);

            if (isMax) {
                a = Math.max(a, temp);
            } else {
                b = Math.min(b, temp);
            }

            bestX = x;
            bestY = y;
            char c = move(board, posX, posY, bestX, bestY);
            temp = getMoveScoreRecursive(board, depth - 1, a, b, !isMax, result);

            if (depth == DEPTH && result.score < temp && (posX - bestX != 0 || posY - bestY != 0)) {
                result.score = score;
                result.fromX = posX;
                result.fromY = posY;
                result.toX = bestX;
                result.toY = bestY;
                score = temp;
            }
            restore(board, bestX, bestY, posX, posY, c);
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