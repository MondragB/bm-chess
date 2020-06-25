package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Move;
import com.chess.engine.board.Board;

import java.util.Collection;

public abstract class Piece {

    protected final int piecePositon;
    protected final Alliance pieceAlliance;

    Piece(final int piecePositon, final Alliance pieceAlliance) {
        this.pieceAlliance = pieceAlliance;
        this.piecePositon = piecePositon;
    }

    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);
}