public abstract class Pieces {
    public abstract boolean isPieceWhite();
    public abstract boolean isMoveValid(int currentRow, int currentColumn, int targetRow, int targetColum, ChessBoard cb);
    public abstract String toString();
}
