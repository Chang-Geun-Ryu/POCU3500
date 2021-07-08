package academy.pocu.comp3500.assignment3;

import academy.pocu.comp3500.assignment3.chess.Move;
import academy.pocu.comp3500.assignment3.chess.PlayerBase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Player2 extends PlayerBase {
    private short round = 0;
    private ArrayList<Position> whitePos = new ArrayList<>();
    private ArrayList<Position> blackPos = new ArrayList<>();
    private Position currentPos = new Position(-1, -1);

    private static final int[][] kingMoveOffsets = {
            {-1, 1},
            {-1, 0},
            {-1, -1},
            {0, 1},
            {0, -1},
            {1, 1},
            {1, 0},
            {1, -1}
    };

    private static final int[][] knightMoveOffsets = {
            {-2, -1},
            {-2, 1},
            {-1, -2},
            {-1, 2},
            {1, -2},
            {1, 2},
            {2, -1},
            {2, 1}
    };

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
            return new Move(isKingPosX, 6, isKingPosX, 4);
        } else {

            return new Move(3, 1, 3, 3);
        }
    }

//    private Move[] getPawnMove(Move move) {
//
//
//    }

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
                    return new Move(3, 4, 2, 3);
                }
            } else {
                return new Move(2, 1, 2, 3);
            }
        }

        return getMoveRecursive();
    }

    private Move getMoveRecursive() {


        return new Move();
    }

    private int getPawnMoveScore(char[][] board, Move move) {
//        boolean isFromPieceWhite = board[move.fromY][move.fromX];
//        boolean hasMoved = isFromPieceWhite ? move.fromY != 6 : move.fromY != 1;

        return 0;
    }

    private void movePos(int x, int y) {
        if (isWhite()) {

        } else {

        }
    }

    private void setPosition(char[][] board, Move move) {
        ArrayList<Position> enemy;
        ArrayList<Position> ours;
        if (isWhite()) {
            ours = whitePos;
            enemy = blackPos;
        } else {
            ours = blackPos;
            enemy = whitePos;
        }

        for (int i = 0; i < enemy.size(); ++i) {
            if (enemy.get(i).posX == move.fromX && enemy.get(i).posY == move.fromY) {
                enemy.get(i).setPosition(move.toX, move.toY);
                break;
            }
        }

        for (int i = 0; i < ours.size(); ++i) {
            if (ours.get(i).posX == move.toX && ours.get(i).posY == move.toY) {
                ours.remove(i);
                break;
            }
        }
    }


    private void setFirstPosition(char[][] board, Move move) {
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 8; ++j) {
                Position white = new Position(j, i + 6);
                Position black = new Position(j, i);
                whitePos.add(white);
                blackPos.add(black);
            }
        }

        if (move != null) {
            setPosition(board, move);
        }
    }

    private boolean isMoveValid(char[][] board, PlayerBase player, Move moveOrNull) {
        if (moveOrNull == null) {
            return false;
        }

        if (moveOrNull.fromX >= 8 || moveOrNull.fromX < 0
                || moveOrNull.fromY >= 8 || moveOrNull.fromY < 0) {
            return false;
        }

        final char symbol = board[moveOrNull.fromY][moveOrNull.fromX];

        if (symbol == 0) {
            return false;
        }

        if ((player.isWhite() && !Character.isLowerCase(symbol))
                || !player.isWhite() && Character.isLowerCase(symbol)) {
            return false;
        }

        if (moveOrNull.toX >= 8 || moveOrNull.toX < 0
                || moveOrNull.toY >= 8 || moveOrNull.toY < 0) {
            return false;
        }

        if (moveOrNull.fromX == moveOrNull.toX && moveOrNull.fromY == moveOrNull.toY) {
            return false;
        }

        char symbolInvariant = Character.toLowerCase(symbol);

        switch (symbolInvariant) {
            case 'p':
                return isPawnMoveValid(board, moveOrNull);

            case 'n':
                return isKnightMoveValid(board, moveOrNull);

            case 'b':
                return isBishopMoveValid(board, moveOrNull);

            case 'r':
                return isRookMoveValid(board, moveOrNull);

            case 'q':
                return isQueenMoveValid(board, moveOrNull);

            case 'k':
                return isKingMoveValid(board, moveOrNull);

            default:
                throw new IllegalArgumentException("Unknown piece symbol");
        }
    }

    private static boolean isBishopMoveValid(char[][] board, Move move) {
        char fromPiece = board[move.fromY][move.fromX];
        char toPiece = board[move.toY][move.toX];

        if (toPiece != 0 && Character.isLowerCase(fromPiece) == Character.isLowerCase(toPiece)) {
            return false;
        }

        if (Math.abs(move.fromX - move.toX) != Math.abs(move.fromY - move.toY)) {
            return false;
        }

        int xIncrement = move.fromX < move.toX ? 1 : -1;
        int yIncrement = move.fromY < move.toY ? 1 : -1;

        int x = move.fromX + xIncrement;
        int y = move.fromY + yIncrement;

        while (x != move.toX && y != move.toY) {
            if (board[y][x] != 0 && x != move.toX && y != move.toY) {
                return false;
            }

            x += xIncrement;
            y += yIncrement;
        }

        return true;
    }

    private static boolean isRookMoveValid(char[][] board, Move move) {
        char fromPiece = board[move.fromY][move.fromX];
        char toPiece = board[move.toY][move.toX];

        if (toPiece != 0 && Character.isLowerCase(fromPiece) == Character.isLowerCase(toPiece)) {
            return false;
        }

        if (move.fromX == move.toX) {
            int yIncrement = move.fromY < move.toY ? 1 : -1;

            int y = move.fromY + yIncrement;

            while (y != move.toY) {
                if (board[y][move.fromX] != 0) {
                    return false;
                }

                y += yIncrement;
            }

            return true;

        } else if (move.fromY == move.toY) {
            int xIncrement = move.fromX < move.toX ? 1 : -1;

            int x = move.fromX + xIncrement;

            while (x != move.toX) {
                if (board[move.fromY][x] != 0) {
                    return false;
                }

                x += xIncrement;
            }

            return true;
        }

        return false;
    }

    private static boolean isKnightMoveValid(char[][] board, Move move) {
        char fromPiece = board[move.fromY][move.fromX];
        char toPiece = board[move.toY][move.toX];

        if (toPiece != 0 && Character.isLowerCase(fromPiece) == Character.isLowerCase(toPiece)) {
            return false;
        }

        for (int i = 0; i < knightMoveOffsets.length; ++i) {
            if (move.fromX + knightMoveOffsets[i][0] == move.toX && move.fromY + knightMoveOffsets[i][1] == move.toY) {
                return true;
            }
        }

        return false;
    }

    private static boolean isQueenMoveValid(char[][] board, Move move) {
        return isBishopMoveValid(board, move) || isRookMoveValid(board, move);
    }

    private static boolean isKingMoveValid(char[][] board, Move move) {
        char fromPiece = board[move.fromY][move.fromX];
        char toPiece = board[move.toY][move.toX];

        if (toPiece != 0 && Character.isLowerCase(fromPiece) == Character.isLowerCase(toPiece)) {
            return false;
        }

        for (int i = 0; i < kingMoveOffsets.length; ++i) {
            if (move.fromX + kingMoveOffsets[i][0] == move.toX && move.fromY + kingMoveOffsets[i][1] == move.toY) {
                return true;
            }
        }

        return false;
    }

    private static boolean isPawnMoveValid(char[][] board, Move move) {
        char fromPiece = board[move.fromY][move.fromX];
        char toPiece = board[move.toY][move.toX];

        boolean isFromPieceWhite = Character.isLowerCase(fromPiece);
        boolean isToPieceWhite = Character.isLowerCase(toPiece);

        if (toPiece != 0 && isFromPieceWhite == isToPieceWhite) {
            return false;
        }

        if (toPiece != 0 && move.fromX == move.toX) {
            return false;
        }

        boolean hasMoved = isFromPieceWhite ? move.fromY != 6 : move.fromY != 1;

        if (!hasMoved && move.fromX == move.toX && Math.abs(move.toY - move.fromY) == 2) {
            if (move.toY > move.fromY && !isFromPieceWhite && board[move.toY - 1][move.toX] == 0) {
                return true;
            }

            return move.toY < move.fromY && isFromPieceWhite && board[move.toY + 1][move.toX] == 0;
        } else if (move.fromX == move.toX && Math.abs(move.toY - move.fromY) == 1) {
            if (move.toY > move.fromY && !isFromPieceWhite) {
                return true;
            }

            return move.toY < move.fromY && isFromPieceWhite;
        } else if (move.toX == move.fromX - 1 || move.toX == move.fromX + 1) {
            if (toPiece != 0 && isToPieceWhite != isFromPieceWhite) {
                return isFromPieceWhite ? move.toY == move.fromY - 1 : move.toY == move.fromY + 1;
            }
        }

        return false;
    }
}

/*
1. 알파베타 학습
2. 나(max) -> 상대(min) -> 나(max) -> 상대(min) -> -> ->
3. 해당 말들의 한턴당 이동가능 자리 탐색
4. 이동할 자리 점수 계산
 */