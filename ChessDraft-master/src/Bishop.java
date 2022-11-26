import java.util.Objects;

public class Bishop extends Pieces {

    boolean isWhite;

    public Bishop(Boolean isWhite){
        this.isWhite = isWhite;
    }

    @Override
    public boolean isPieceWhite() {
        return isWhite;
    }

    @Override
    public boolean isMoveValid(int currentRow, int currentColumn, int targetRow, int targetColum, ChessBoard board) {

        int columnDiff = targetColum - currentColumn, rowDiff = targetRow - currentRow;

        String not = "There are pieces in between";

        if(Math.abs(columnDiff) == Math.abs(rowDiff)){

            rowDiff = rowDiff > 0? rowDiff - 1: rowDiff + 1;
            columnDiff = columnDiff > 0? columnDiff - 1: columnDiff + 1;

            if(rowDiff == 0 && columnDiff == 0)
                return true;

            if(rowDiff > 0 && columnDiff > 0)
                for(int i = 1; i <= rowDiff; i++)
                    if(!Objects.equals(board.getPieceAsString(currentRow + i, currentColumn + i), ".")){
                        System.out.println(not);
                        return false;
                    }

            if(rowDiff > 0 && columnDiff < 0)
                for(int i = 1; i <= rowDiff; i++)
                    if(!Objects.equals(board.getPieceAsString(currentRow + i, currentColumn - i), ".")){
                        System.out.println(not);
                        return false;
                    }

            if(rowDiff < 0 && columnDiff < 0)
                for (int i = 1; i <= Math.abs(rowDiff); i++)
                    if(!Objects.equals(board.getPieceAsString(currentRow - i, currentColumn - i), ".")){
                        System.out.println(not);
                        return false;
                    }

            if(rowDiff < 0 && columnDiff > 0)
                for (int i = 1; i <= Math.abs(rowDiff); i++)
                    if(!Objects.equals(board.getPieceAsString(currentRow - i, currentColumn + i), ".")){
                        System.out.println(not);
                        return false;
                    }

            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "B";
    }
}
