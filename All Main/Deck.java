import java.util.*;

public class Deck {

    Random random = new Random();
    Card kartu;
    List<Card> cardList = new ArrayList<Card>();

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
            cardList.add(new NumberCard(Color.BLUE, number));
            cardList.add(new NumberCard(Color.RED, number));
            cardList.add(new NumberCard(Color.GREEN, number));
            cardList.add(new NumberCard(Color.YELLOW, number));
        }
    }

    public void createActionCard() {
        for (Color color : Color.values()) {
            if (color != Color.BLACK) {
                cardList.add(new ActionCard(color, Type.SKIP));
                cardList.add(new ActionCard(color, Type.REVERSE));
                cardList.add(new ActionCard(color, Type.DRAW2));
            }
        }
    }

    public void createWildCard() {
        cardList.add(new WildCard(Type.WILDCLR, Color.BLACK));
        cardList.add(new WildCard(Type.WLDDRAW4, Color.BLACK));
    }

    public Card getCard() {
        int randomIndex = random.nextInt(cardList.size());
        return cardList.get(randomIndex);
    }

    public String getList() {
        return cardList.toString();
    }
    public static void main (String args[]){
        Deck test=new Deck();
        System.out.println(test.getList());
        System.out.println(test.cardList.get(0));
    }
    public List<Card> getDeck(){
        return cardList;
    }
}