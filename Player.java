public abstract class Player {
    int wallet;
    int currentBet;
    Deck deck;
    Table table;
    String playerName;
    Card[] hand;
    String action;

    public void drawHand() {
        this.hand = new Card[]{deck.getRandomCard(), deck.getRandomCard()};
    }

    public void printHand() {
        System.out.println(hand[0].displayName + " and the " + hand[1].displayName);
    }

    public String getHand() {
        return hand[0].displayName + " and the " + hand[1].displayName;
    }

    public abstract void handleTurn();


    public void bet(int bet) {
        wallet -= bet;
        currentBet += bet;
        table.currentBet += bet;
        table.currentTotal += table.currentBet;
        table.returnFinishedPlayers();
    }

    public void check() {
    }

    public void call() {
        wallet -= (table.currentBet - currentBet);
        table.currentTotal += (table.currentBet - currentBet);
        currentBet += (table.currentBet - currentBet);
    }

    public void raise(int raise) {
        wallet -= raise;
        currentBet += raise;
        table.currentBet += raise;
        table.currentTotal += table.currentBet;
        table.returnFinishedPlayers();
    }

    public void fold() {
    }


}