public class Knight extends Pieces {

    boolean isWhite;
    public Knight(Boolean isWhite){
        this.isWhite = isWhite;
    }


    @Override
    public boolean isPieceWhite() {
        return isWhite;
    }

    @Override
    public boolean isMoveValid(int currentRow, int currentColumn, int targetRow, int targetColum, ChessBoard cb) {

        //System.out.println("Inside Horse: " + currentColumn + " " + currentRow + " " + targetColum + " " + targetRow);
        //System.out.println("Current board\n");

        int columnDiff = Math.abs(targetColum - currentColumn), rowDiff = Math.abs(targetRow - currentRow);
        return (columnDiff == 2 && rowDiff == 1) || (columnDiff == 1 && rowDiff == 2);
    }

    @Override
    public String toString() {
        return "N";
    }
}
