import java.util.Objects;
import java.util.Scanner;

public class PlayerCharacter extends Player {

    Scanner in;

    public PlayerCharacter(int wallet, Deck deck, Table table, String playerName, Scanner in) {
        this.wallet = wallet;
        this.deck = deck;
        this.table = table;
        this.playerName = playerName;
        this.in = in;
        this.action = "";
    }

    public void printHand() {
        System.out.print("You are holding the ");
        super.printHand();
    }

    public String getHand() {
        return "You are holding the " + super.getHand();
    }

    public void handleTurn() {

        System.out.println("> It's your turn, the table's bet is " + table.currentBet + ", what do you want to do?");


        if (!(currentBet < table.currentBet))   {action = returnPlayerChoice("Check", "Bet"); }
        else                                    {action = returnPlayerChoice("Call", "Raise", "Fold");}

        System.out.println("> You chose " + action);

        if (action.equals("check")) {check();}
        if (action.equals("bet"))   {bet();}
        if (action.equals("call"))  {call();}
        if (action.equals("raise")) {raise();}
        if (action.equals("fold"))  {fold();}

    }

    public String returnPlayerChoice(String ... options){
        for (int i = 0; i < options.length; i++){
            System.out.printf("(%d) %s\n", i + 1, options[i]);
        }

        String playerChoice = in.nextLine();

        for (int i = 0; i < options.length; i++){
            if (playerChoice.toLowerCase().contains(options[i].toLowerCase()) || playerChoice.equals(String.valueOf(i + 1))){
                return options[i].toLowerCase();
            }
        }

        System.out.println("Input not recognized, please try again");
        return returnPlayerChoice(options);
    }

    public int returnValue(String action){

        System.out.println("> You have " + wallet + ". How much do you want to " + action);
        String playerChoice = in.nextLine();

        try {
            int value = Integer.parseInt(playerChoice);
            if (value > wallet || value < 0){
                System.out.print("Invalid value: ");
                return returnValue(action);}
            return value;
        } catch (NumberFormatException ImSoSorry){
            System.out.print("Invalid input: ");
            return returnValue(action);
        }

    }

    public void call(){
        System.out.println("> You put down " + (table.currentBet - currentBet) + " to match the others");
        super.call();
    }

    public void bet(){
        int bet = returnValue("bet");
        System.out.println("> You bet " + bet);
        bet(bet);
    }

    public void raise(){
        call();
        int raise = returnValue("raise");
        System.out.println("> You raise to " + (raise + table.currentBet));
        raise(raise);
    }


}
