
public class WildCard extends Card {
    private final Type type;
    private final Color color;

    public WildCard(final Type type, final Color color) {
        this.type = type;
        this.color = color;
    }

    @Override
    public String getColor() {
        return this.color.toString();
    }

    @Override
    public String getType() {
        return this.type.toString();
    }

    @Override
    public String getNum() {
        return null;
    }

    /*
     * @Override public boolean match(Card card) { return
     * (card.getType().equals(this.getType())); }
     * 
     * public boolean validate(String tipe) { for (Type types : Type.values()) { if
     * (types.name().equals(tipe.toUpperCase())) return true; } return false; }
     */
}