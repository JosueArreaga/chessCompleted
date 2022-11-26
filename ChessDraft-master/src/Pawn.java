import java.util.Objects;

public class Pawn extends Pieces {

    boolean isWhite;

    public Pawn(Boolean isWhite){
        this.isWhite = isWhite;
    }

    @Override
    public boolean isPieceWhite() {
        return isWhite;
    }

    // Issue on 2 move at start needs to be fixed.
    @Override
    public boolean isMoveValid(int currentRow, int currentColumn, int targetRow, int targetColumn, ChessBoard board) {

        int columnDiff = targetColumn - currentColumn, rowDiff = targetRow - currentRow;

        // White pawn movements
        if(rowDiff == 2 && columnDiff == 0 && currentRow == board.whitePawnRow)
            return true;

        if(rowDiff == 1 && columnDiff == 0)
            if(Objects.equals(board.getPieceAsString(currentRow + 1, currentColumn), "."))
                return true;

        if(rowDiff == 1 && Math.abs(columnDiff) == 1){
            if(columnDiff == 1 && !(Objects.equals(board.getPieceAsString(currentRow + 1, currentColumn + 1), ".")))
                return true;
            if(columnDiff == -1 && !(Objects.equals(board.getPieceAsString(currentRow + 1, currentColumn - 1), ".")))
                return true;
        }


        // Black Pawn Movement
        if(rowDiff == -2 && columnDiff == 0 && currentRow == board.blackPawnRow)
            return true;

        if(rowDiff == - 1 && columnDiff == 0)
            if((Objects.equals(board.getPieceAsString(currentRow - 1, currentColumn), ".")))
                return true;

        if((rowDiff == -1 && Math.abs(columnDiff) == 1)) {
            if(columnDiff == 1 && !(Objects.equals(board.getPieceAsString(currentRow - 1, currentColumn + 1), ".")))
                return true;
            if(columnDiff == -1 && !(Objects.equals(board.getPieceAsString(currentRow - 1, currentColumn - 1), ".")))
                return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "P";
    }
}
