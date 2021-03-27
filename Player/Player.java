import java.util.ArrayList;

public class Player {
    private String playerName;
    private int playerTurn;
    private ArrayList<Card> kartu;
    static int numofPlayer = 0;

    //Constructor Player
    public player(String playerName, int playerTurn) {
        this.playerName = playerName;
        this.playerTurn = playerTurn;
        kartu = new ArrayList<Card>();
        numofPlayer ++;
    }
    // Bikin kartu yang dipegang sama player
    public void takeDeck(){
        for (int i = 0; i < 7; i++){
            kartu.add(getCard()); //ini fungsi drawRandomCard belum dibikin
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