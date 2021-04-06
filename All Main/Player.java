import java.util.ArrayList;
public class Player {
    private String playerName;
    private int playerTurn;
    public ArrayList<Card> kartu;//Ini kartu tangan
    public ArrayList<Card> discardable_kartu;//Ini kartu tangan yang bisa dibuang nantinya
    static int numofPlayer = 0;
    private Deck dek_kartu= new Deck();//Buat tumpukan kartu semuanya(fungsinya biar nanti pas draw tinggal ngerandom dari ini)

    //Constructor Player
    public Player(String playerName, int playerTurn) {
        this.playerName = playerName;
        this.playerTurn = playerTurn;
        kartu = new ArrayList<Card>();
        discardable_kartu = new ArrayList<Card>();
        numofPlayer ++;
    }
    // Bikin kartu yang dipegang sama player
    public void initialHand(){
        for (int i = 0; i < 7; i++){
            kartu.add(dek_kartu.getCard()); //ini fungsi drawRandomCard belum dibikin
        }
    }
    //Fungsi F04
    public void draw(){
        kartu.add(dek_kartu.getCard());
    }
    public void drawupto(int jumlah){
        for(int i=0;i<jumlah;i++){
            kartu.add(dek_kartu.getCard());
        }
    }
    //Perantara buat fungsi F05
    public void hasOneCard(){
        if (kartu.size() == 1){
            //declareHiji();
        }
    }
    //Fungsi F02
    public void getHand(){
        System.out.println("List Kartu Tangan: ");
        for (int i = 0; i < kartu.size(); i++){
            System.out.println((i+1)+". "+kartu.get(i));
        }
    }

    //Menampilkan kartu yang bisa di discard SECARA GENERAL
    public void getdiscardableHand(Card top_card){
        System.out.println("Kartu yang bisa dikeluarkan pemain: ");
        this.discardable_kartu.clear();
        for (int i = 0; i < kartu.size(); i++){
            if(discardable(kartu.get(i), top_card)){
                this.discardable_kartu.add(kartu.get(i));
            }
        }
        System.out.println("0. Cancel");
        for (int i = 0; i < discardable_kartu.size(); i++){
            System.out.println((i+1)+". "+discardable_kartu.get(i));
        }

    }
    //Menampilkan kartu yang bisa di discard berdasarkan warna tertentu
    public void getdiscardablecolor(Color warna){
        System.out.println("Kartu yang bisa dikeluarkan pemain: ");
        this.discardable_kartu.clear();
        for(int i=0;i<kartu.size();i++){
            if(kartu.get(i).getColor()==warna.toString()){
                this.discardable_kartu.add(kartu.get(i));
            }
        }
        System.out.println("0. Cancel");
        for (int i = 0; i < discardable_kartu.size(); i++){
            System.out.println((i+1)+". "+discardable_kartu.get(i));
        }
    }
    //Menampilkan kartu yang bisa di discard pada saat melakukan tumpukan+2
    public void getdiscardablestack(){
        System.out.println("Kartu yang bisa dikeluarkan pemain: ");
        this.discardable_kartu.clear();
        for(int i=0;i<kartu.size();i++){
            if(kartu.get(i).getType()!=null){
                if(kartu.get(i).getType().equals(Type.DRAW2.toString())){
                    this.discardable_kartu.add(kartu.get(i));
                }
            }
        }
        System.out.println("0. Cancel");
        for (int i = 0; i < discardable_kartu.size(); i++){
            System.out.println((i+1)+". "+discardable_kartu.get(i));
        }
    }
    //Buang kartu
    public void discard(Card kartu_pile){
        int i=0;
        boolean found=false;
        while(!found){
            if((kartu.get(i).getType()==kartu_pile.getType())&&(kartu.get(i).getNum()==kartu_pile.getNum())&&(kartu.get(i).getColor()==kartu_pile.getColor())){
                found=true;
            }else{
                i++;
            }
        }
        kartu.remove(i);
    }
    //Fungsi mengecheck apakah kartu bisa di discard
    public boolean discardable(Card kartu_1, Card kartu_2){//Asumsi kartu 2 selalu kartu yang paling atas
        return isMatchActionCard(kartu_1, kartu_2)||isMatchNumberCard(kartu_1, kartu_2)||isSameColor(kartu_1, kartu_2)||isValidWildCard(kartu_1);
    }
    
    //Fungsi mengecheck apakah kartu paling atas sama dengan kartu yang ada di tangan
    public static boolean isMatchActionCard(Card topCard, Card playedCard) {
        if(topCard.getType()!=null&&playedCard.getType()!=null){
            return isSameColor(topCard, playedCard)||topCard.getType().toString().equals(playedCard.getType().toString());
        }else{return false;}
    }

    public static boolean isMatchNumberCard(Card topCard, Card playedCard) {
        if(topCard.getNum()!=null&&playedCard.getNum()!=null){
            return isSameColor(topCard, playedCard)||topCard.getNum().toString().equals(playedCard.getNum().toString());
        }else{
            return false;
        }
    }

    public static boolean isValidWildCard(Card playedCard) {
        return playedCard.getColor().toString().equals(Color.BLACK.toString());
    }

    private static boolean isSameColor(Card topCard, Card playedCard) {
        return topCard.getColor().toString().equals(playedCard.getColor());
    }
    //Getter
    public String getPlayerName(){
        return playerName;
    }
    public int getPlayerTurn(){
        return playerTurn;
    }
    public static int getnumPlayers(){
        return numofPlayer;
    }
    public ArrayList<Card> getKartuTangan(){
        return kartu;
    }
    public ArrayList<Card> getDiscardableKartuTangan(){
        return discardable_kartu;
    }
}
