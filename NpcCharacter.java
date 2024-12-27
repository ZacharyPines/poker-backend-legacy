import java.util.Objects;
import java.util.Random;

public class NpcCharacter extends Player{

    Random random = new Random();

    public NpcCharacter(int wallet, Deck deck, Table table, String playerName, Object x){
        this.wallet = wallet;
        this.deck = deck;
        this.table = table;
        this.playerName = playerName;
    }

    public void printHand(){
        System.out.print(playerName + " is holding the ");
        super.printHand();
    }

    public void handleTurn() {

        //This is awful and will be going away soon
        if (!(currentBet < table.currentBet)) {
            if (random.nextInt(6) == 0) {action = "bet";}
            else {action = "check";}
        } else {
            if (random.nextInt(12) == 0) {action = "fold";}
            else if (random.nextInt(6) == 0) {action = "raise";}
            else {action = "call";}
        }

        if (action.equals("bet") || action.equals("raise")) {
            System.out.println(playerName + " " + action + "s 100");
        } else {
            System.out.println(playerName + " " + action + "s");
        }

        if (Objects.equals(action, "check"))  {check();}
        if (Objects.equals(action, "bet"))    {bet();}
        if (Objects.equals(action, "call"))   {call();}
        if (Objects.equals(action, "raise"))  {raise();}
        if (Objects.equals(action, "fold"))   {fold();}

    }

    public void bet(){
        super.bet(100);
    }

    public void raise(){
        super.raise(100);
    }
}
