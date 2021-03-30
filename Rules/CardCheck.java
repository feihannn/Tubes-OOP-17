public class CardCheck {
    
    public static boolean isMatchActionCard(Card topCard, ActionCard playedCard) {
        if (isSameColor(topCard, playedCard)){
            return true;
        }
        return topCard.getType().equals(playedCard.getType());
    }

    public static boolean isMatchNumberCard(Card topCard, NumberCard playedCard) {
        if (isSameColor(topCard, playedCard)){
            return true;
        }
        return topCard.getNum().equals(playedCard.getNum());
    }

    public static boolean isValidWildCard(WildCard playedCard) {
        return playedCard.getColor().equals(Color.BLACK);
    }

    private static boolean isSameColor(Card topCard, Card playedCard) {
        return topCard.getColor().equals(playedCard.getColor());
    }
}