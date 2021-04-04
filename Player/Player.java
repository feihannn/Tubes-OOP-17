import java.util.ArrayList;
import Card.Deck.java;

public class Player {
    private String playerName;
    private int playerTurn;
    private ArrayList<Card> kartu;
    static int numofPlayer = 0;
    Deck deck = new Deck();

    //Constructor Player
    public Player(String playerName, int playerTurn) {
        this.playerName = playerName;
        this.playerTurn = playerTurn;
        kartu = new ArrayList<Card>();
        numofPlayer ++;
    }
    // Bikin kartu yang dipegang sama player
    public void takeDeck(){
        for (int i = 0; i < 7; i++){
            kartu.add(deck.getCard()); //ini fungsi tlg di check lagi soalnya list yang tipe Card sedangkan ini tipenya String
        }
    }
    public boolean hasOneCard(){
        if (kartu.size() == 1){
            declareHiji();
        }
    }
    public void getHand(){
        for (int i = 0; i < kartu.size(); i++){
            System.out.println(kartu.get(i));
        }
    }
    public String getPlayerName(){
        return playerName;
    }
    public int getPlayerTurn(){
        return playerTurn;
    }
    public static int getnumPlayers(){
        return numofPlayer;
    }
}
// Tambahin = 1. Has hand card(Panjang aray kartu 1 panggil declare hiji); 2.Getternya (List kartu hand, nama, urutan);