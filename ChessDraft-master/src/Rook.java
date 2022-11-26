import java.util.Objects;

public class Rook extends Pieces {
    boolean isWhite;

    public Rook(Boolean isWhite){
        this.isWhite = isWhite;
    }

    @Override
    public boolean isPieceWhite() {
        return isWhite;
    }

    @Override
    public boolean isMoveValid(int currentRow, int currentColumn, int targetRow, int targetColum, ChessBoard board) {
        int columnDiff = targetColum - currentColumn, rowDiff = targetRow - currentRow;

        if(((columnDiff == 0 && rowDiff != 0) || (columnDiff != 0 && rowDiff == 0))){

            if(columnDiff == 1 || rowDiff == 1){return true;}

            if(rowDiff > 0){
                rowDiff--;
                for(int i = 1; i <= Math.abs(rowDiff); i++)
                    if(!Objects.equals(board.getPieceAsString(currentRow + i, currentColumn), "."))
                        return false;
            }

            if(rowDiff < 0){
                rowDiff++;
                for (int i = 1; i <= Math.abs(rowDiff); i++)
                    if(!Objects.equals(board.getPieceAsString(currentRow - i, currentColumn), "."))
                        return false;
            }

            if(columnDiff > 0) {
                columnDiff --;
                for (int i = 1; i <= Math.abs(columnDiff); i++)
                    if(!Objects.equals(board.getPieceAsString(currentRow, currentColumn + i), "."))
                        return false;
            }

            if (columnDiff < 0){
                columnDiff++;
                for (int i = 1; i <= Math.abs(columnDiff); i++)
                    if(!Objects.equals(board.getPieceAsString(currentRow, currentColumn - i), "."))
                        return false;
            }
            return true;
        }


        return false;
    }

    @Override
    public String toString() {
        return "R";
    }
}
