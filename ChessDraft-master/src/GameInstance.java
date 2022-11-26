public class GameInstance {
    int currentRow;
    int currentColumn;
    int targetRow;
    int targetColumn;
    boolean isPlayerWhite = false;


    public boolean changePlayerTurn(){
        isPlayerWhite = !isPlayerWhite;
        return isPlayerWhite;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public void setCurrentColumn(int currentColumn) {
        this.currentColumn = currentColumn;
    }

    public void setTargetRow(int targetRow) {
        this.targetRow = targetRow;
    }
    public void setTargetColumn(int targetColumn) {
        this.targetColumn = targetColumn;
    }
}
