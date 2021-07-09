package academy.pocu.comp3500.assignment3;

import academy.pocu.comp3500.assignment3.chess.Move;
import academy.pocu.comp3500.assignment3.chess.PlayerBase;

import java.util.ArrayList;

public class Player2 extends PlayerBase {
    private short round = 0;
    private ArrayList<Position> whitePos;// = new ArrayList<>();
    private ArrayList<Position> blackPos;// = new ArrayList<>();
    private Position currentPos = new Position(-1, -1);

    public Player2(boolean isWhite, int maxMoveTimeMilliseconds) {
        super(isWhite, maxMoveTimeMilliseconds);
    }


    @Override
    public Move getNextMove(char[][] board) {
        if (round++ == 0) {
            setFirstPosition(board, null);
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
            setFirstPosition(board, opponentMove);
        } else {
            setPosition(board, opponentMove);
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

        return getMove(board);
    }

    private Move getMove(char[][] board) {
        ArrayList<Position> ours;
        ArrayList<Position> enemy;
        if (isWhite()) {
            ours = whitePos;
            enemy = blackPos;
        } else {
            ours = blackPos;
            enemy = whitePos;
        }

        MoveScore move = getMoveScoreRecursive(board, 4, ours, enemy, true, new MoveScore());
        return new Move(move.fromX, move.fromY, move.toX, move.toY);
    }

    private MoveScore getMoveScoreRecursive(char[][] board, int depth, ArrayList<Position> ours, ArrayList<Position> enemy, boolean isMax, MoveScore move) {
        if (depth <= 0) {
            return move;
        }

        if (isMax) {
            int max = Integer.MIN_VALUE;
            MoveScore m = new MoveScore();
            m.score = max;
            for (int i = 0; i < ours.size(); ++i) {
                switch (Character.toLowerCase(board[ours.get(i).posY][ours.get(i).posX])) {
                    case 'p':
                        getPawnMove(board, i, ours, m, isMax);
                        break;
                    case 'n':
                        getKnightMove(board, i, ours, m, isMax);
                        break;
                    case 'b':
                        getBishopMove(board, i, ours, m, isMax);
                        break;
                    case 'r':
                        getRookMove(board, i, ours, m, isMax);
                        break;
                    case 'q':
                        getBishopMove(board, i, ours, m, isMax);
                        getRookMove(board, i, ours, m, isMax);
                        break;
                    case 'k':
                        getKingMove(board, i, ours, m, isMax);
                        break;
                    default:
                        return null;
                }
            }

            char c = move(board, ours.get(m.index), m);

            return getMoveScoreRecursive(board, depth - 1, ours, enemy, false, m);
        } else {
            int min = Integer.MAX_VALUE;
            MoveScore m = new MoveScore();
            m.score = min;
            for (int i = 0; i < enemy.size(); ++i) {
                switch (Character.toLowerCase(board[enemy.get(i).posY][enemy.get(i).posX])) {
                    case 'p':
                        getPawnMove(board, i, ours, m, isMax);
                        break;
                    case 'n':
                        getKnightMove(board, i, ours, m, isMax);
                        break;
                    case 'b':
                        getBishopMove(board, i, ours, m, isMax);
                        break;
                    case 'r':
                        getRookMove(board, i, ours, m, isMax);
                        break;
                    case 'q':
                        getBishopMove(board, i, ours, m, isMax);
                        getRookMove(board, i, ours, m, isMax);
                        break;
                    case 'k':
                        getKingMove(board, i, ours, m, isMax);
                        break;
                    default:
                        return null;
                }
            }

            char c = move(board, enemy.get(m.index), m);

            return getMoveScoreRecursive(board, depth - 1, ours, enemy, true, m);
        }
    }

    private void getPawnMove(char[][] board, int index, ArrayList<Position> pos, MoveScore result, boolean isMax) {
        boolean hasMoved = isWhite() ? pos.get(index).posY != 6 : pos.get(index).posY != 1;
        int sign = isWhite() ? -1 : 1;
        int i = hasMoved ? 1 : 0;

        while (i < Position.PWAN.length) {
            int x = Position.PWAN[i][0] + pos.get(index).posX;
            int y = sign * Position.PWAN[i][1] + pos.get(index).posY;
            ++i;

            if (x < 0 || y < 0 || x >= board[0].length || y >= board.length) {
                continue;
            }

            if (pos.get(index).posX == x && board[y][x] != 0) {
                continue;
            }

            if (pos.get(index).posX != x && board[y][x] == 0) {
                continue;
            }

            if (pos.get(index).posX != x && Character.isLowerCase(board[y][x]) == Character.isLowerCase(board[pos.get(index).posY][pos.get(index).posX])) {
                continue;
            }

            int temp = getScore(board, x, y);

            if (isMax) {
                if (temp > result.score) {
                    result.toX = x;
                    result.toY = y;
                    result.fromY = pos.get(index).posX;
                    result.fromY = pos.get(index).posY;
                    result.score = temp;
                    result.index = index;
                }
            } else {
                if (temp < result.score) {
                    result.toX = x;
                    result.toY = y;
                    result.fromY = pos.get(index).posX;
                    result.fromY = pos.get(index).posY;
                    result.score = temp;
                    result.index = index;
                }
            }
        }
    }

    private void getKnightMove(char[][] board, int index, ArrayList<Position> pos, MoveScore result, boolean isMax) {
        for (int i = 0; i < Position.KNIGHT.length; ++i) {
            int x = Position.KNIGHT[i][0] + pos.get(index).posX;
            int y = Position.KNIGHT[i][1] + pos.get(index).posY;

            if (x < 0 || y < 0 || x >= board[0].length || y >= board.length) {
                continue;
            }

            if (Character.isLowerCase(board[y][x]) == Character.isLowerCase(board[pos.get(index).posY][pos.get(index).posX])) {
                continue;
            }

            int temp = getScore(board, x, y);

            if (isMax) {
                if (temp > result.score) {
                    result.toX = x;
                    result.toY = y;
                    result.fromY = pos.get(index).posX;
                    result.fromY = pos.get(index).posY;
                    result.score = temp;
                    result.index = index;
                }
            } else {
                if (temp < result.score) {
                    result.toX = x;
                    result.toY = y;
                    result.fromY = pos.get(index).posX;
                    result.fromY = pos.get(index).posY;
                    result.score = temp;
                    result.index = index;
                }
            }
        }
    }

    private void getBishopMove(char[][] board, int index, ArrayList<Position> pos, MoveScore result, boolean isMax) {
        int xIncrement = 1;
        int yIncrement = 1;

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

            int x = xIncrement + pos.get(index).posX;
            int y = yIncrement + pos.get(index).posY;

            while (x >= 0 && y >= 0 && x < board[0].length && y < board.length) {
                if (Character.isLowerCase(board[y][x]) == Character.isLowerCase(board[pos.get(index).posY][pos.get(index).posX])) {
                    break;
                }

                int temp = getScore(board, x, y);

                if (isMax) {
                    if (temp > result.score) {
                        result.toX = x;
                        result.toY = y;
                        result.fromY = pos.get(index).posX;
                        result.fromY = pos.get(index).posY;
                        result.score = temp;
                        result.index = index;
                    }
                } else {
                    if (temp < result.score) {
                        result.toX = x;
                        result.toY = y;
                        result.fromY = pos.get(index).posX;
                        result.fromY = pos.get(index).posY;
                        result.score = temp;
                        result.index = index;
                    }
                }

                if (board[y][x] != 0) {
                    break;
                }

                x += xIncrement;
                y += yIncrement;
            }
        }
    }

    private void getRookMove(char[][] board, int index, ArrayList<Position> pos, MoveScore result, boolean isMax) {
        int xIncrement = 1;
        int yIncrement = 0;

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

            int x = xIncrement + pos.get(index).posX;
            int y = yIncrement + pos.get(index).posY;

            while (x >= 0 && y >= 0 && x < board[0].length && y < board.length) {
                if (Character.isLowerCase(board[y][x]) == Character.isLowerCase(board[pos.get(index).posY][pos.get(index).posX])) {
                    break;
                }

                int temp = getScore(board, x, y);

                if (isMax) {
                    if (temp > result.score) {
                        result.toX = x;
                        result.toY = y;
                        result.fromY = pos.get(index).posX;
                        result.fromY = pos.get(index).posY;
                        result.score = temp;
                        result.index = index;
                    }
                } else {
                    if (temp < result.score) {
                        result.toX = x;
                        result.toY = y;
                        result.fromY = pos.get(index).posX;
                        result.fromY = pos.get(index).posY;
                        result.score = temp;
                        result.index = index;
                    }
                }

                if (board[y][x] != 0) {
                    break;
                }

                x += xIncrement;
                y += yIncrement;
            }
        }
    }

    private void getKingMove(char[][] board, int index, ArrayList<Position> pos, MoveScore result, boolean isMax) {
        for (int i = 0; i < Position.KING.length; ++i) {
            int x = Position.KING[i][0] + pos.get(index).posX;
            int y = Position.KING[i][1] + pos.get(index).posY;

            if (x < 0 || y < 0 || x >= board[0].length || y >= board.length) {
                continue;
            }

            if (Character.isLowerCase(board[y][x]) == Character.isLowerCase(board[pos.get(index).posY][pos.get(index).posX])) {
                continue;
            }

            int temp = getScore(board, x, y);

            if (isMax) {
                if (temp > result.score) {
                    result.toX = x;
                    result.toY = y;
                    result.fromY = pos.get(index).posX;
                    result.fromY = pos.get(index).posY;
                    result.score = temp;
                    result.index = index;
                }
            } else {
                if (temp < result.score) {
                    result.toX = x;
                    result.toY = y;
                    result.fromY = pos.get(index).posX;
                    result.fromY = pos.get(index).posY;
                    result.score = temp;
                    result.index = index;
                }
            }
        }
    }

    private Move movePos(int fromX, int fromY, int toX, int toY) {
        ArrayList<Position> ours;
        if (isWhite()) {
            ours = whitePos;
        } else {
            ours = blackPos;
        }

        currentPos.setPosition(fromX, fromY);
        for (int i = 0; i < ours.size(); ++i) {
            if (ours.get(i).hashCode() == currentPos.hashCode()) {
                ours.get(i).setPosition(2, 3);
            }
        }

        return new Move(fromX, fromY, toX, toY);
    }

    private char move(char[][] board, Position pos, MoveScore move) {
        pos.setPosition(move.toX, move.toY);

        char c = board[move.toY][move.toX];
        board[move.toY][move.toX] = board[move.fromY][move.fromX];
        board[move.fromY][move.fromX] = 0;
        return c;
    }

    private void setPosition(char[][] board, Move move) {
        ArrayList<Position> ours;
        ArrayList<Position> enemy;
        if (isWhite()) {
            ours = whitePos;
            enemy = blackPos;
        } else {
            ours = blackPos;
            enemy = blackPos;
        }

        currentPos.setPosition(move.fromX, move.fromY);
        for (int i = 0; i < enemy.size(); ++i) {
            if (enemy.get(i).hashCode() == currentPos.hashCode()) {
                enemy.get(i).setPosition(move.toX, move.toY);
                break;
            }
        }

        currentPos.setPosition(move.toX, move.toY);
        for (int i = 0; i < ours.size(); ++i) {
            if (ours.get(i).hashCode() == currentPos.hashCode()) {
                ours.remove(i);
                break;
            }
        }
    }

    private void setFirstPosition(char[][] board, Move move) {
        ArrayList<Position> whites = new ArrayList<>();
        ArrayList<Position> blacks = new ArrayList<>();

        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (board[j][i] == 0) {
                    continue;
                }

                char c = board[j][i];
                Position pos = new Position(i, j);
                if (Character.isLowerCase(c)) {
                    whites.add(pos);
                } else {
                    blacks.add(pos);
                }
            }
        }

        whitePos = whites;
        blackPos = blacks;

        if (move != null) {
            setPosition(board, move);
        }
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