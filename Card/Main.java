public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        System.out.println(deck.getList());

        for (int i = 0; i < 7; i++)
            System.out.println(deck.getCard());

        for (int j = 0; j < 3; j++) {
            Match matching = new Match();
            System.out.println(matching.matching());
        }
    }
}
