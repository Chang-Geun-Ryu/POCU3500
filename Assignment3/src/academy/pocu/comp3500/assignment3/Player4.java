package academy.pocu.comp3500.assignment3;

import academy.pocu.comp3500.assignment3.chess.Move;
import academy.pocu.comp3500.assignment3.chess.PlayerBase;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Player4 extends PlayerBase {
    private short round = 0;
    private short DEPTH = 23;
    static private int count = 0;
    private boolean isFull = false;
    private long start = 0;
    private long end = 0;
//    private long duration = //TimeUnit.MILLISECONDS.convert(end - start, TimeUnit.NANOSECONDS);

    public Player4(boolean isWhite, int maxMoveTimeMilliseconds) {
        super(isWhite, maxMoveTimeMilliseconds);
    }


    @Override
    public Move getNextMove(char[][] board) {
        start = System.nanoTime();
        if (round++ == 0) {
            count = 0;
            for (int x = 0; x < 8; ++x) {
                for (int y = 0; y < 8; ++y) {
                    if (Character.toLowerCase(board[y][x]) != 0) {
                        ++count;
                    }
                }
            }
            if (count >= 32) {
                isFull = true;
            }
        }
        if (isFull) {
            int isQ = 3;
            for (int i = 0; i < 8; ++i) {
                if (Character.toLowerCase(board[7][i]) == 'k') {
                    isQ = i;
                    break;
                }
            }

            return new Move(isQ, 6, isQ, 4);
        }

        Move move = getMove(board);
        return move;
    }

    @Override
    public Move getNextMove(char[][] board, Move opponentMove) {
        start = System.nanoTime();
        if (round++ == 0) {
            count = 0;
            for (int x = 0; x < 8; ++x) {
                for (int y = 0; y < 8; ++y) {
                    if (Character.toLowerCase(board[y][x]) != 0) {
                        ++count;
                    }
                }
            }
            if (count >= 32) {
                isFull = true;
            }
        }

        if (isFull) {
            Move move = getMoveOpening(board);
            if (move != null) {
                return move;
            }
        }

        Move move = getMove(board);
        return move;
    }

    private Move getMoveOpening(char[][] board) {

        if (isWhite()) {
            if (round == 2) {
                int knight = 1;
                for (int i = 0; i < 8; ++i) {
                    if (Character.toLowerCase(board[7][i]) == 'n') {
                        knight = i;
                        break;
                    }
                }
                return new Move(knight, 7, knight + 1, 5);
            } else if (round == 3) {
                int bishop = 1;
                int isDir = 0;
                for (int i = 0; i < 8; ++i) {
                    if (Character.toLowerCase(board[7][i]) == 'b') {
                        bishop = i;
                        if (board[6][i - 1] == 0) {
                            isDir = -1;
                            break;
                        } else if (board[6][i + 1] == 0) {
                            isDir = 1;
                            break;
                        }
                    }
                }
                return new Move(bishop, 7, bishop + isDir * 4, 3);
            } else if (round == 4) {
                int isKnight = 1;
                for (int i = 0; i < 8; ++i) {
                    if (Character.toLowerCase(board[7][i]) == 'n') {
                        isKnight = i;
                        break;
                    }
                }
                return new Move(isKnight, 7, isKnight - 1, 5);
            }
        } else {
            if (round == 1) {
                int p = 4;
                for (int i = 0; i < 8; ++i) {
                    if (Character.toLowerCase(board[4][i]) == 'p') {
                        p = i;
                        break;
                    }
                }

                return new Move(p, 1, p, 3);
            } else if (round == 2) {
                int isKnight = 1;
                for (int i = 0; i < 8; ++i) {
                    if (Character.toLowerCase(board[0][i]) == 'n') {
                        isKnight = i;
                        break;
                    }
                }
                return new Move(isKnight, 0, isKnight + 1, 2);
            } else if (round == 3) {
                int isKnight = 1;
                for (int i = 0; i < 8; ++i) {
                    if (Character.toLowerCase(board[0][i]) == 'n') {
                        isKnight = i;
                        break;
                    }
                }
                return new Move(isKnight, 0, isKnight - 1, 2);
            }
        }
        isFull = false;
        return null;
    }

    private Move getMove(char[][] board) {
        int a = Integer.MIN_VALUE;
        int b = Integer.MAX_VALUE;

        --DEPTH;
        System.out.println(" ===============   Dep: " + DEPTH);
//        if (round - (25 - DEPTH)  <= 0) {
//            DEPTH - 25 - round
//        }

        MoveScore result = new MoveScore();
        result.score = Integer.MIN_VALUE;
        int move = getMoveScoreRecursive(board, DEPTH, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, true, result);
        return new Move(result.fromX, result.fromY, result.toX, result.toY);
    }

    private int getMoveScoreRecursive(char[][] board, int depth, int score, int a, int b, boolean isMax, MoveScore result) {
//        System.out.println("time" + TimeUnit.MILLISECONDS.convert(System.nanoTime() - start, TimeUnit.NANOSECONDS));
        if (depth <= DEPTH - 5) {
            if (TimeUnit.MILLISECONDS.convert(System.nanoTime() - start, TimeUnit.NANOSECONDS) + 50 > getMaxMoveTimeMilliseconds()) {
                return score;
            }
        }

        if (depth <= 0) {
            return score;
        }

        ArrayList<MoveScore> minimax = new ArrayList<>();
        if (round % 2 == 0) {
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
                                max = Math.max(max, getPawnMove(board, x, y, max, a, b, minimax, depth, isMax, result));
                                break;
                            case 'n':
                                max = Math.max(max, getKnightMove(board, x, y, max, a, b, minimax, depth, isMax, result));
                                break;
                            case 'b':
                                max = Math.max(max, getBishopMove(board, x, y, max, a, b, minimax, depth, isMax, result));
                                break;
                            case 'r':
                                max = Math.max(max, getRookMove(board, x, y, max, a, b, minimax, depth, isMax, result));
                                break;
                            case 'q':
                                max = Math.max(max, getBishopMove(board, x, y, max, a, b, minimax, depth, isMax, result));
                                max = Math.max(max, getRookMove(board, x, y, max, a, b, minimax, depth, isMax, result));
                                break;
                            case 'k':
                                max = Math.max(max, getKingMove(board, x, y, max, a, b, minimax, depth, isMax, result));
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
                if (minimax.size() > 0) {
                    max = Integer.MIN_VALUE;
                    MoveScore move = null;
                    for (MoveScore m : minimax) {
                        if (m.score >= max) {
                            max = m.score;
                            move = m;
                        }
                    }

                    result.fromX = move.fromX;
                    result.fromY = move.fromY;
                    result.toX = move.toX;
                    result.toY = move.toY;
                    result.score = move.score;
                }

                return score;
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
                                min = Math.min(min, getPawnMove(board, x, y, min, a, b, minimax, depth, isMax, result));
                                break;
                            case 'n':
                                min = Math.min(min, getKnightMove(board, x, y, min, a, b, minimax, depth, isMax, result));
                                break;
                            case 'b':
                                min = Math.min(min, getBishopMove(board, x, y, min, a, b, minimax, depth, isMax, result));
                                break;
                            case 'r':
                                min = Math.min(min, getRookMove(board, x, y, min, a, b, minimax, depth, isMax, result));
                                break;
                            case 'q':
                                min = Math.min(min, getBishopMove(board, x, y, min, a, b, minimax, depth, isMax, result));
                                min = Math.min(min, getRookMove(board, x, y, min, a, b, minimax, depth, isMax, result));
                                break;
                            case 'k':
                                min = Math.min(min, getKingMove(board, x, y, min, a, b, minimax, depth, isMax, result));
                                break;
                            default:
                                return Integer.MIN_VALUE;
                        }
                        b = Math.min(b, min);
                        if (b <= a) {
                            break;
                        }
                    }
                    if (b <= a) {
                        break;
                    }
                }

                if (minimax.size() > 0) {
                    min = Integer.MAX_VALUE;
                    MoveScore move = null;
                    for (MoveScore m : minimax) {
                        if (m.score <= min) {
                            min = m.score;
                            move = m;
                        }
                    }
                    result.fromX = move.fromX;
                    result.fromY = move.fromY;
                    result.toX = move.toX;
                    result.toY = move.toY;
                    result.score = move.score;
                    return move.score;
                }

                return score;
            }
        } else {
            if (isMax) {
                int max = Integer.MIN_VALUE;
                for (int x = 7; x >= 0; --x) {
                    for (int y = 7; y >= 0; --y) {
                        //max
                        //isWhite() : true; isLowercase : false;
                        //isWhite() : false; isLowercase : true;
                        if (Character.toLowerCase(board[y][x]) == 0 || Character.isLowerCase(board[y][x]) != isWhite()) {
                            continue;
                        }

                        switch (Character.toLowerCase(board[y][x])) {
                            case 'p':
                                max = Math.max(max, getPawnMove(board, x, y, max, a, b, minimax, depth, isMax, result));
                                break;
                            case 'n':
                                max = Math.max(max, getKnightMove(board, x, y, max, a, b, minimax, depth, isMax, result));
                                break;
                            case 'b':
                                max = Math.max(max, getBishopMove(board, x, y, max, a, b, minimax, depth, isMax, result));
                                break;
                            case 'r':
                                max = Math.max(max, getRookMove(board, x, y, max, a, b, minimax, depth, isMax, result));
                                break;
                            case 'q':
                                max = Math.max(max, getBishopMove(board, x, y, max, a, b, minimax, depth, isMax, result));
                                max = Math.max(max, getRookMove(board, x, y, max, a, b, minimax, depth, isMax, result));
                                break;
                            case 'k':
                                max = Math.max(max, getKingMove(board, x, y, max, a, b, minimax, depth, isMax, result));
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
                if (minimax.size() > 0) {
                    max = Integer.MIN_VALUE;
                    MoveScore move = null;
                    for (MoveScore m : minimax) {
                        if (m.score >= max) {
                            max = m.score;
                            move = m;
                        }
                    }

                    result.fromX = move.fromX;
                    result.fromY = move.fromY;
                    result.toX = move.toX;
                    result.toY = move.toY;
                    result.score = move.score;
                }

                return score;
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
                                min = Math.min(min, getPawnMove(board, x, y, min, a, b, minimax, depth, isMax, result));
                                break;
                            case 'n':
                                min = Math.min(min, getKnightMove(board, x, y, min, a, b, minimax, depth, isMax, result));
                                break;
                            case 'b':
                                min = Math.min(min, getBishopMove(board, x, y, min, a, b, minimax, depth, isMax, result));
                                break;
                            case 'r':
                                min = Math.min(min, getRookMove(board, x, y, min, a, b, minimax, depth, isMax, result));
                                break;
                            case 'q':
                                min = Math.min(min, getBishopMove(board, x, y, min, a, b, minimax, depth, isMax, result));
                                min = Math.min(min, getRookMove(board, x, y, min, a, b, minimax, depth, isMax, result));
                                break;
                            case 'k':
                                min = Math.min(min, getKingMove(board, x, y, min, a, b, minimax, depth, isMax, result));
                                break;
                            default:
                                return Integer.MIN_VALUE;
                        }
                        b = Math.min(b, min);
                        if (b <= a) {
                            break;
                        }
                    }
                    if (b <= a) {
                        break;
                    }
                }

                if (minimax.size() > 0) {
                    min = Integer.MAX_VALUE;
                    MoveScore move = null;
                    for (MoveScore m : minimax) {
                        if (m.score <= min) {
                            min = m.score;
                            move = m;
                        }
                    }
                    result.fromX = move.fromX;
                    result.fromY = move.fromY;
                    result.toX = move.toX;
                    result.toY = move.toY;
                    result.score = move.score;
                    return move.score;
                }

                return score;
            }
        }
    }

    // a < v < b
    private int getPawnMove(char[][] board, int posX, int posY, int score, int a, int b, ArrayList<MoveScore> list, int depth, boolean isMax, MoveScore result) {
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
            temp = getMoveScoreRecursive(board, depth - 1, temp, a, b, !isMax, result);

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

    private int getKnightMove(char[][] board, int posX, int posY, int score, int a, int b, ArrayList<MoveScore> list, int depth, boolean isMax, MoveScore result) {
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
            temp = getMoveScoreRecursive(board, depth - 1, temp, a, b, !isMax, result);
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

    private int getBishopMove(char[][] board, int posX, int posY, int score, int a, int b, ArrayList<MoveScore> list, int depth, boolean isMax, MoveScore result) {
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
                temp = getMoveScoreRecursive(board, depth - 1, a, b, temp, !isMax, result);

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

    private int getRookMove(char[][] board, int posX, int posY, int score, int a, int b, ArrayList<MoveScore> list, int depth, boolean isMax, MoveScore result) {
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
                temp = getMoveScoreRecursive(board, depth - 1, temp, a, b, !isMax, result);

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

    private int getKingMove(char[][] board, int posX, int posY, int score, int a, int b, ArrayList<MoveScore> list, int depth, boolean isMax, MoveScore result) {
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
            temp = getMoveScoreRecursive(board, depth - 1, temp, a, b, !isMax, result);

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