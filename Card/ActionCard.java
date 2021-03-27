public class ActionCard extends Card {
    private final Color color;
    private final Type type;

    public ActionCard(final Color color, final Type type) {
        this.color = color;
        this.type = type;
    }

    @Override
    public String getColor() {
        return this.color.toString();
    }

    @Override
    public String getNum() {
        return null;
    }

    @Override
    public String getType() {
        return this.type.toString();
    }

    /*
     * @Override public boolean match(Card card) { return
     * (card.getColor().equals(this.getColor()) ||
     * card.getType().equals(this.getType())); }
     * 
     * public boolean validate(String warna, String tipe) { for (Color colors :
     * Color.values()) { for (Type types : Type.values()) { if
     * (colors.name().equals(warna.toUpperCase()) &&
     * types.name().equals(tipe.toUpperCase())) return true; } } return false; }
     */

}