public class Empty extends Pieces {

    @Override
    public boolean isPieceWhite() {
        return false;
    }

    @Override
    public boolean isMoveValid(int currentRow, int currentColumn, int targetRow, int targetColum, ChessBoard cb) {
        return false;
    }

    @Override
    public String toString() {
        return ".";
    }
}
