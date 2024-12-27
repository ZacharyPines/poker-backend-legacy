import java.util.ArrayList;
import java.util.Random;

public class Deck {
    ArrayList<Card> cards;

    // suit is 0-3 in CHaSeD order. value is 1:1, but is indexed from 2.
    public void reset() {

        cards = new ArrayList<Card>();

        for(int value = 2; value < 15; value++){
            for(int suit = 0; suit < 4; suit++){

                String name = "";

                if (value == 2) {name += "Two"; }
                if (value == 3) {name += "Three"; }
                if (value == 4) {name += "Four"; }
                if (value == 5) {name += "Five"; }
                if (value == 6) {name += "Six"; }
                if (value == 7) {name += "Seven"; }
                if (value == 8) {name += "Eight"; }
                if (value == 9) {name += "Nine"; }
                if (value == 10) {name += "Ten"; }
                if (value == 11) {name += "Jack"; }
                if (value == 12) {name += "Queen"; }
                if (value == 13) {name += "King"; }
                if (value == 14) {name += "Ace"; }

                name += " of ";

                if (suit == 0) {name += "Clubs"; }
                if (suit == 1) {name += "Hearts"; }
                if (suit == 2) {name += "Spades"; }
                if (suit == 3) {name += "Diamonds"; }

                cards.add(new Card(value, suit, name));
            }
        }
    }

    public Card getRandomCard(){
        Random rand = new Random();
        int carnNum = rand.nextInt(cards.size());

        Card card = cards.get(carnNum);
        cards.remove(carnNum);
        return card;
    }

}