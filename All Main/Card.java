// koleksi2 kartu, memiliki basic operation seperti add, remove (getCard()), count, dan isEmpty

public abstract class Card {
    public abstract String getColor();
    public abstract String getNum();
    public abstract String getType();
    @Override
    public String toString() {
        return this.getColor() + "," + this.getNum() + "," + this.getType();
    }
}
