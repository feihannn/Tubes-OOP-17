import java.util.ArrayList;

public class Player {
    private int playerId;
    private int playerTurn = 0;
    private ArrayList<Card> kartu;

    //Constructor Player
    public player(int playerId, int playerTurn) {
        this.playerId = playerId;
        this.playerTurn = playerTurn + 1;
        kartu = new ArrayList<Card>();
    }
    // Bikin kartu yang dipegang sama player
    public void takeDeck(){
        kartu.add(drawRandomCard()); //ini fungsi drawRandomCard belum dibikin
        kartu.add(drawRandomCard());
        kartu.add(drawRandomCard());
        kartu.add(drawRandomCard());
        kartu.add(drawRandomCard());
        kartu.add(drawRandomCard());
        kartu.add(drawRandomCard());
    }
    public void CreateHand() {
        
    }
}