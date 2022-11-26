public class Queen extends Pieces {

    boolean isWhite;

    public Queen(Boolean isWhite){
        this.isWhite = isWhite;
    }

    @Override
    public boolean isPieceWhite() {
        return isWhite;
    }

    @Override
    public boolean isMoveValid(int currentRow, int currentColumn, int targetRow, int targetColum, ChessBoard cb) {

        return new Bishop(false).isMoveValid(currentRow,currentColumn,targetRow, targetColum, cb) ||
                new Rook(false).isMoveValid(currentRow,currentColumn,targetRow, targetColum, cb);
    }

    @Override
    public String toString() {
        return "Q";
    }
}
