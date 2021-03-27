public class NumberCard extends Card {

    private final Color color;
    private final Number num;

    public NumberCard(final Color color, final Number num) {
        this.color = color;
        this.num = num;
    }

    @Override
    public String getColor() {
        return this.color.toString();
    }

    @Override
    public String getNum() {
        return this.num.toString();
    }

    @Override
    public String getType() {
        return null;
    }

    /*
     * @Override public boolean match(Card card) { return
     * (card.getColor().equals(this.getColor()) ||
     * card.getNum().equals(this.getNum())); }
     * 
     * public static boolean validate(String warna) { for (Color colors :
     * Color.values()) { if (colors.name().equals(warna.toUpperCase())) return true;
     * } return false; }
     */

}
