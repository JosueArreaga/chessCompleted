import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String [] args){
        GameInstance gi = new GameInstance();
        LogicClass logic = new LogicClass(gi);

        Scanner scnr = new Scanner(System.in);
        String exit = null;


        System.out.println("Player one: Begin the game. Your Pieces are in row 1 and 2");

        while (!Objects.equals(exit, "exit")){
            exit = scnr.nextLine();
            if(!exit.equalsIgnoreCase("exit"))
                logic.getMove(exit);
        }
    }
}
