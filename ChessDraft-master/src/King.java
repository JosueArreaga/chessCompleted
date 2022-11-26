public class King extends Pieces {
    boolean isWhite;

    public King(Boolean isWhite){
        this.isWhite = isWhite;
    }

    @Override
    public boolean isPieceWhite() {
        return isWhite;
    }

    @Override
    public boolean isMoveValid(int currentRow, int currentColumn, int targetRow, int targetColum, ChessBoard cb) {
        int rowDiff = targetRow - currentRow, columnDiff = targetColum - currentColumn;
        return Math.abs(rowDiff) <= 1 && Math.abs(columnDiff) <= 1;
    }

    @Override
    public String toString() {
        return "K";
    }
}
