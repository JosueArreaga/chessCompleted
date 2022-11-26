import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class LogicClass {
    private final HashSet<Character> chessPieces = new HashSet<>();
    private final HashSet<Character> columnLetterCoordinates = new HashSet<>();
    private final HashSet<Character> rowNumberCoordinates = new HashSet<>();
    private final HashMap<Character, Integer> valueOfLetterCoordinates = new HashMap<>();
    GameInstance gameInstance;
    ChessBoard chessBoard;

    public LogicClass(GameInstance gi){
        this.gameInstance = gi;
        chessBoard = new ChessBoard();
        chessBoard.printBoard();

        char [] pieces = {'R', 'N', 'B', 'K', 'Q', 'P', 'r', 'n', 'b', 'k', 'q', 'p'};
        char [] letter = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        char [] pos = {'1','2','3','4','5','6','7', '8'};

        int num = 7;
        for(int i = 0; i < 8; i++ ){
            valueOfLetterCoordinates.put(letter[i], num--);
        }

        int num2 = 7;
        for(int i = 8; i < 16; i++){
            valueOfLetterCoordinates.put(letter[i], num2--);
        }

        for (char value : pieces) this.chessPieces.add(value);
        for (char c : letter) this.columnLetterCoordinates.add(c);
        for (char po : pos) this.rowNumberCoordinates.add(po);
    }

    public void getMove(String move){

        if(!isNotationLegal(move)) {
            System.out.println("Move coordinates are invalid: Try again");
            chessBoard.printBoard();
            return;
        }

        if(!isMoveLegal(move)){
            System.out.println("Piece cannot move like that: Try again");
            chessBoard.printBoard();
            return;
        }

        if(!isPieceMine()){
            System.out.println("Either you tried to eat your own piece, or tried to play a piece your opponent controls: Try again");
            chessBoard.printBoard();
            return;
        }

        chessBoard.playMove(gameInstance.currentRow, gameInstance.currentColumn, gameInstance.targetRow, gameInstance.targetColumn);
        checkPawnPromotion();
        chessBoard.printBoard();

        if(gameInstance.isPlayerWhite)
            System.out.println("Player 2: Make a move");
        else
            System.out.println("Player 1: Make a move");
    }

    private boolean isNotationLegal(String move) {
            if(move.length() != 5) return false;
            if(!chessPieces.contains(move.charAt(0))) return false;
            if(!columnLetterCoordinates.contains(move.charAt(1))) return false;
            if(!rowNumberCoordinates.contains(move.charAt(2))) return false;
            if(!columnLetterCoordinates.contains(move.charAt(3))) return false;
            return rowNumberCoordinates.contains(move.charAt(4));
    }

    private boolean isMoveLegal(String move) {
        if(!isPieceInCorrectCoordinates(move)) return false;
        return isMoveLegalWithNoPiecesBetween();
    }

    private boolean isPieceInCorrectCoordinates(String move) {
        String pieceAsCharacter = String.valueOf((move.charAt(0)));
        pieceAsCharacter = pieceAsCharacter.toUpperCase();

        int rowOffSetValue = 1;

        gameInstance.setCurrentRow(Integer.parseInt(String.valueOf(move.charAt(2))) - rowOffSetValue);
        gameInstance.setTargetRow(Integer.parseInt(String.valueOf(move.charAt(4))) - rowOffSetValue);
        gameInstance.setCurrentColumn(valueOfLetterCoordinates.get(move.charAt(1)));
        gameInstance.setTargetColumn(valueOfLetterCoordinates.get(move.charAt(3)));

        return Objects.equals(chessBoard.getPieceAsString(gameInstance.currentRow, gameInstance.currentColumn), pieceAsCharacter);
    }

    private boolean isMoveLegalWithNoPiecesBetween() {
        return chessBoard.getPiece(gameInstance.currentRow, gameInstance.currentColumn).isMoveValid(gameInstance.currentRow, gameInstance.currentColumn,
                gameInstance.targetRow, gameInstance.targetColumn, chessBoard.getBoard());
    }

    private boolean isPieceMine() {
        boolean playerIsWhite = gameInstance.changePlayerTurn();

        if(playerIsWhite && chessBoard.getPiece(gameInstance.currentRow, gameInstance.currentColumn).isPieceWhite() && !chessBoard.getPiece(gameInstance.targetRow, gameInstance.targetColumn).isPieceWhite())
            return true;

        if(!playerIsWhite && !chessBoard.getPiece(gameInstance.currentRow, gameInstance.currentColumn).isPieceWhite() &&
                (chessBoard.getPiece(gameInstance.targetRow, gameInstance.targetColumn).isPieceWhite() || chessBoard.getPieceAsString(gameInstance.targetRow, gameInstance.targetColumn).equals(".")))
            return true;

        gameInstance.changePlayerTurn();
        return false;

    }

    private void checkPawnPromotion() {

        boolean pieceIsWhite = false;
        boolean pieceIsBlack = false;

        if(Objects.equals(chessBoard.getPiece(gameInstance.targetRow, gameInstance.targetColumn).toString(), "P") && gameInstance.targetRow == chessBoard.whitePiecesRow) {
            System.out.println("Do you want your pawn to become a 'R', 'N', 'B', or 'Q'");
            pieceIsBlack = true;
        }

        else if(Objects.equals(chessBoard.getPiece(gameInstance.targetRow, gameInstance.targetColumn).toString(), "P") && gameInstance.targetRow == chessBoard.blackPiecesRow){
            System.out.println("Do you want your pawn to become a 'R', 'N', 'B', or 'Q'");
            pieceIsWhite = true;
        }

        if(!pieceIsWhite && !pieceIsBlack) return;

        Scanner scnr = new Scanner(System.in);
        String piece = "";

        while(!piece.equalsIgnoreCase("R") && !piece.equalsIgnoreCase("N") && !piece.equalsIgnoreCase("B") && !piece.equalsIgnoreCase("Q")) {
            System.out.println("Enter your desired Piece");
            piece = scnr.nextLine();
        }

        if(pieceIsWhite){
            if(piece.equalsIgnoreCase("R")) chessBoard.setPiece(gameInstance.targetRow, gameInstance.targetColumn, new Rook(true));
            if(piece.equalsIgnoreCase("N")) chessBoard.setPiece(gameInstance.targetRow, gameInstance.targetColumn, new Knight(true));
            if(piece.equalsIgnoreCase("B")) chessBoard.setPiece(gameInstance.targetRow, gameInstance.targetColumn, new Bishop(true));
            if(piece.equalsIgnoreCase("Q")) chessBoard.setPiece(gameInstance.targetRow, gameInstance.targetColumn, new Queen(true));
        }

        if(pieceIsBlack){
            if(piece.equalsIgnoreCase("R")) chessBoard.setPiece(gameInstance.targetRow, gameInstance.targetColumn, new Rook(false));
            if(piece.equalsIgnoreCase("N")) chessBoard.setPiece(gameInstance.targetRow, gameInstance.targetColumn, new Knight(false));
            if(piece.equalsIgnoreCase("B")) chessBoard.setPiece(gameInstance.targetRow, gameInstance.targetColumn, new Bishop(false));
            if(piece.equalsIgnoreCase("Q")) chessBoard.setPiece(gameInstance.targetRow, gameInstance.targetColumn, new Queen(false));
        }

    }
}

