import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int PLAYER_COUNT = 4;
        int STARTING_MONEY = 1000;

        Scanner in = new Scanner(System.in);
        Deck deck = new Deck();

        Table table = new Table(deck);

        Player[] playerMagazine = new Player[PLAYER_COUNT];
        for(int i = 1; i < PLAYER_COUNT; i++) {
            playerMagazine[i] = new NpcCharacter(STARTING_MONEY, deck, table, "Player " + (i + 1), in);
        }

        PlayerCharacter player = new PlayerCharacter(STARTING_MONEY, deck, table, "Me", in);
        playerMagazine[0] = player;

        //--------------------------------------------------------------------------------------------------

        boolean continueGame = true;
        while (continueGame) {

            resetGame(deck, playerMagazine, table);
            dealHands(PLAYER_COUNT);

            while (continueRound(player, table)) {
                table.playPhase();
                boxDisplay(player.getHand(), table.getCards(), table.currentTotal, player);
                table.handleBetting();
                table.ResetBetweenPhase(playerMagazine);
            }

            TellUsWhoWon(table.playerMagazine);

            table.ResetBetweenGame(playerMagazine);

            continueGame = continueGame(in, player);

        }
    }

 //--------------------------------------------------------------------------------------------------

    public static void resetGame(Deck deck, Player[] playerList, Table table) {
        deck.reset();

        for (Player body : playerList) {
            body.currentBet = 0;
            body.drawHand();
        }

        table.reset(playerList);
    }

    public static void dealHands(int num){
        System.out.println("You have been dealt your hand");
        System.out.print("Players ");
        for (int i = 2; i <= num - 1; i++){
            System.out.print(i + ", ");
        }
        System.out.println("and " + num + " have been dealt their hands");
    }

    public static boolean continueGame(Scanner in, Player player){

        for (int i = 0; i < 100; i++){
            System.out.print("-");
        }
        System.out.println();

        if(player.wallet <= 0){
            System.out.println("Thank you for playing, but you are out of money");
            return false;
        }

        System.out.println("That was an exhilarating match, do you want to continue playing?");
        boolean output = in.nextLine().toLowerCase().equals("yes");

        for (int i = 0; i < 100; i++){
            System.out.print("-");
        }
        System.out.println();

        return output;
    }

    public static boolean continueRound(Player player, Table table){
        return table.phase < 3;
    }

    public static void boxDisplay(String sentence1, String sentence2, int totalVal, Player player){
        int length = sentence2.length();
        if (player.action.equals("fold")) {sentence1 = "You have folded your hand";}
        String sentence3 = "There are " + totalVal + " dollars on the table";

        System.out.print("+");
        for (int i = 0; i < length + 2; i++) {System.out.print("-"); }
        System.out.println("+");

        System.out.printf("%s %-" + length + "s %s \n", "|", sentence1, "|");
        System.out.printf("%s %-" + length + "s %s \n", "|", sentence2, "|");
        System.out.printf("%s %-" + length + "s %s \n", "|", sentence3, "|");

        System.out.print("+");
        for (int i = 0; i < length + 2; i++) {System.out.print("-"); }
        System.out.println("+");

    }

    public static void TellUsWhoWon(ArrayList<Player> remainingPlayers){
        System.out.println();
        System.out.println("I'd love to tell you who wins now if I could do that");
        for (Player body : remainingPlayers){
            body.printHand();
        }
        System.out.println();
    }

}