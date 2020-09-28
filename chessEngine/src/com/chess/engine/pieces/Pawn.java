package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;

public class Pawn extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATE = { 8, 16 };

    Pawn(final int piecePositon, final Alliance pieceAlliance) {
        super(piecePositon, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {

            final int candidateDestinationCoordinate = this.piecePositon
                    + (this.getPieceAlliance().getDirection() * currentCandidateOffset);

            if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                continue;
            }

            if (currentCandidateOffset == 8 && board.getTile(candidateDestinationCoordinate).isTileOccupied()) {

                // TODO - Adding special pawn moves specifically promotion
                legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
            } else if (currentCandidateOffset == 16 && this.isFirstMove()
                    && (BoardUtils.SECOND_RANK[this.piecePositon] && this.getPieceAlliance().isBlack())
                    || (BoardUtils.SEVENTH_RANK[this.piecePositon] && this.getPieceAlliance().isWhite())) {

                final int behindCandidateDestinationCoordinate = this.piecePositon
                        + (this.pieceAlliance.getDirection() * 8);
                if (!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied()
                        && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                }

            }
        }

        return legalMoves;
    }

}
