package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Move;
import com.chess.engine.board.Board;

import java.util.Collection;

public abstract class Piece {

    protected final int piecePositon;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;

    Piece(final int piecePositon, final Alliance pieceAlliance) {
        this.pieceAlliance = pieceAlliance;
        this.piecePositon = piecePositon;

        this.isFirstMove = false;
    }

    public Integer getPiecePosition() {
        return this.piecePositon;
    }

    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);

    public enum PieceType {

        PAWN("P"), KNIGHT("N"), BISHOP("B"), ROOK("R"), QUEEN("Q"), KING("K");

        private String pieceName;

        PieceType(final String pieceName) {
            this.pieceName = pieceName;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }

    }

}