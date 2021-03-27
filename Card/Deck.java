import java.util.*;

public class Deck {

    Random random = new Random();
    List<String> cardList = new ArrayList<>();

    public Deck() {
        createDeck();
    }

    public void createDeck() {
        createNumCard();
        createActionCard();
        createWildCard();
    }

    public void createNumCard() {
        for (Number number : Number.values()) {
            cardList.add(new NumberCard(Color.BLUE, number).toString());
            cardList.add(new NumberCard(Color.RED, number).toString());
            cardList.add(new NumberCard(Color.GREEN, number).toString());
            cardList.add(new NumberCard(Color.YELLOW, number).toString());
        }
    }

    public void createActionCard() {
        for (Color color : Color.values()) {
            if (color != Color.BLACK) {
                cardList.add(new ActionCard(color, Type.SKIP).toString());
                cardList.add(new ActionCard(color, Type.REVERSE).toString());
                cardList.add(new ActionCard(color, Type.DRAW2).toString());
            }
        }
    }

    public void createWildCard() {
        cardList.add(new WildCard(Type.WILDCLR, Color.BLACK).toString());
        cardList.add(new WildCard(Type.WLDDRAW4, Color.BLACK).toString());
    }

    public String getCard() {
        int randomIndex = random.nextInt(cardList.size());
        return cardList.get(randomIndex);
    }

    public String getList() {
        return cardList.toString();
    }
}