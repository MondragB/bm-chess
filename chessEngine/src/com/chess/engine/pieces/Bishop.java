package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

public class Bishop extends Piece {

    public final static int[] CANIDATE_MOVE_VECTOR_COORDINATES = { -9, -7, 7, 9 };

    public Bishop(int piecePosition, Alliance pieceAlliance) {
        super(PieceType.BISHOP, piecePosition, pieceAlliance, true);
    }

    public Bishop(final int piecePosition, Alliance pieceAlliance, final boolean isFirstMove) {
        super(PieceType.BISHOP, piecePosition, pieceAlliance, isFirstMove);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int candidateCoordinateOffset : CANIDATE_MOVE_VECTOR_COORDINATES) {
            int candidateDestinationCoordinate = this.piecePositon;
            while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                if (isFirstFileExlcusion(candidateDestinationCoordinate, candidateCoordinateOffset)
                        || isEighthFileExlcusion(candidateDestinationCoordinate, candidateCoordinateOffset)) {
                    break;
                }
                candidateDestinationCoordinate += candidateCoordinateOffset;
                if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                    if (!candidateDestinationTile.isTileOccupied()) {
                        legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                    } else {
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                        if (this.pieceAlliance != pieceAlliance) {
                            legalMoves.add(new Move.MajorAttackMove(board, this, candidateDestinationCoordinate,
                                    pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public String toString() {
        return PieceType.BISHOP.toString();
    }

    private static boolean isFirstFileExlcusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_FILE[currentPosition] && (candidateOffset == -9 || candidateOffset == 7);
    }

    private static boolean isEighthFileExlcusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_FILE[currentPosition] && (candidateOffset == -7 || candidateOffset == 9);
    }

    @Override
    public Bishop movePiece(final Move move) {
        return new Bishop(move.getDestinationCoordinate(), move.getMovedPiece().getPieceAlliance());
    }

}