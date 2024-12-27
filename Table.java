import java.util.ArrayList;
import java.util.Objects;

public class Table {
    ArrayList<Card> cards;
    int phase;
    Deck deck;
    int currentBet;
    int currentTotal;
    ArrayList<Player> playerMagazine;
    ArrayList<Player> finishedPlayers;

    public Table(Deck deck) {
        this.deck = deck;
        this.cards = new ArrayList<Card>();
        this.phase = 0;
        this.currentBet = 0;
        this.playerMagazine = new ArrayList<Player>();
        this.finishedPlayers = new ArrayList<Player>();
    }

    public void reset(Player[] playerListMaster) {
        cards = new ArrayList<Card>();
        phase = 0;
        resetMagazine(playerListMaster);
    }

    public void addCard(int num) {
        for(int i = 0; i < num; i++) {
            cards.add(deck.getRandomCard());
        }
    }

    public String getCards() {

        String output = "The table has ";

        for (int i = 0; i < cards.size() - 1; i++){
            output += "the " + cards.get(i).displayName + ", ";
        }

        output += "and the " + cards.get(cards.size() - 1).displayName;

        return output;
    }

    public void printCards() {
        System.out.print("The table has ");
        for (int i = 0; i < cards.size() - 1; i++){
            System.out.print("the ");
            System.out.print(cards.get(i).displayName);
            System.out.print(", ");
        }
        System.out.print("and the ");
        System.out.println(cards.get(cards.size() - 1).displayName);
    }

    public void playPhase() {
        if (phase == 0) {addCard(3); }
        if (phase == 1) {addCard(1); }
        if (phase == 2) {addCard(1); }
        phase++ ;
    }

    public void handleBetting() {
        while (playerMagazine.size() != 0){
            Player activePlayer = playerMagazine.remove(0);
            activePlayer.handleTurn();
            if(!(activePlayer.action.equals("fold"))) {
                finishedPlayers.add(activePlayer);
            }
        }
    }

    public void returnFinishedPlayers(){
        for (Player player : finishedPlayers){
            playerMagazine.add(player);
        }
        finishedPlayers.clear();
    }

    public void ResetBetweenPhase(Player[] masterList) {
        currentBet = 0;

        for(Player player : masterList){
            player.currentBet = 0;
        }

        returnFinishedPlayers();

        //This rotates through the magazine until the first player is at the front
        for (Player masterPlayer : masterList){
            for (int i = 0; i < playerMagazine.size(); i++) {
                if (masterPlayer == playerMagazine.get(0)) {
                    return;
                }
                playerMagazine.add(playerMagazine.remove(0));
            }
        }
    }

    public void ResetBetweenGame(Player[] masterList) {
        ResetBetweenPhase(masterList);
        currentTotal = 0;
        for (Player player : masterList){
            player.action = "";
        }
    }

    public void resetMagazine(Player[] playerListMaster) {
        playerMagazine.clear();
        finishedPlayers.clear();

        for(Player player : playerListMaster){
            playerMagazine.add(player);
        }
    }

}
