public class ChessBoard {

    private final Pieces[][] board = new Pieces[8][8];
    int whitePawnRow = 1, whitePiecesRow = 0 , blackPawnRow = 6, blackPiecesRow = 7;
    int rookOneColumn = 0, rookTwoColumn = 7, knightOneColumn = 1, knightTwoColumn = 6, bishopOneColumn = 2, bishopTwoColumn = 5, kingColumn = 3, queenColumn = 4;


    public ChessBoard() {
        clearBoard();
        populateBoard();
    }

    private void clearBoard() {
        for(int i = 0; i < this.board.length; ++i) {
            for(int j = 0; j < this.board[0].length; ++j) {
                this.board[i][j] = new Empty();
            }
        }
    }

    private void populateBoard() {
        populatePawns();
        populateRooks();
        populateBishops();
        populateKnights();
        populateQueens();
        populateKings();
    }

    private void populateKings() {
        board[whitePiecesRow][kingColumn] = new King(true);
        board[blackPiecesRow][kingColumn] = new King(false);
    }

    private void populateQueens() {
        board[whitePiecesRow][queenColumn] = new Queen(true);
        board[blackPiecesRow][queenColumn] = new Queen(false);
    }

    private void populateKnights() {
        board[whitePiecesRow][knightOneColumn] = new Knight(true);
        board[whitePiecesRow][knightTwoColumn] = new Knight(true);
        board[blackPiecesRow][knightOneColumn] = new Knight(false);
        board[blackPiecesRow][knightTwoColumn] = new Knight(false);
    }

    private void populateBishops() {
        board[whitePiecesRow][bishopOneColumn] = new Bishop(true);
        board[whitePiecesRow][bishopTwoColumn] = new Bishop(true);
        board[blackPiecesRow][bishopOneColumn] = new Bishop(false);
        board[blackPiecesRow][bishopTwoColumn] = new Bishop(false);
    }

    private void populateRooks() {
        board[whitePiecesRow][rookOneColumn] = new Rook(true);
        board[whitePiecesRow][rookTwoColumn] = new Rook(true);
        board[blackPiecesRow][rookOneColumn] = new Rook(false);
        board[blackPiecesRow][rookTwoColumn] = new Rook(false);
    }

    private void populatePawns() {
        for(int i = 0; i < this.board.length; ++i) {
            for(int j = 0; j < this.board[0].length; ++j) {
                if (i == this.whitePawnRow) {
                    this.board[i][j] = new Pawn(true);
                }

                if (i == this.blackPawnRow) {
                    this.board[i][j] = new Pawn(false);
                }
            }
        }
    }

    public String getPieceAsString(int row, int column){
        //System.out.println("Inside board. Column: " + column + " row:" + row);
        //System.out.println(board[row][column].toString());
        return board[row][column].toString();
    }

    public Pieces getPiece(int row, int column){
        //System.out.println("Inside getPiece (chess board). Column: " + column + " row: " + row);
        return board[row][column];
    }

    public ChessBoard getBoard(){
        return this;
    }

    public void setPiece(int newRow, int newColumn, Pieces piece){
        board[newRow][newColumn] = piece;
    }

    public void playMove(int currentRow, int currentColumn, int targetRow, int targetColumn){

        //System.out.println("Inside playMove: " + currentColumn + " " + currentRow + " " + targetColumn + " " + targetRow);
        Pieces temp = board[currentRow][currentColumn];
        //System.out.println("Piece in memory: " +  board[currentRow][currentColumn]);
        board[currentRow][currentColumn] = new Empty();
       // System.out.println("empty piece: " + board[currentRow][currentColumn]);
        board[targetRow][targetColumn] = temp;
       // System.out.println("Piece in new square: " + board[targetRow][targetColumn]);
    }

    public void printBoard() {
        System.out.println();
        int rowNum = 1;
        System.out.print("   h  g  f  e  d  c  b  a");
        for (Pieces[] piece : this.board) {
            System.out.println();
            for (int j = 0; j < this.board[0].length; ++j) {
                if(j == 0){
                    System.out.print(rowNum + "  ");
                    rowNum++;
                }
                System.out.print(piece[j] + "  ");
            }
        }

        System.out.println("\n");
    }
}
