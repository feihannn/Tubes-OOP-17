/* 
Class ini digunakan untuk mengakses giliran pemain tertentu dan mengubah arah putaran giliran pemain 
Kayaknya mendingan ditaro di Player (?), tadi gua mikirnya perlu gitu pake class ini jadinya gua bikin disini wkwk
*/


public class PlayerRound {
    private final Player[] players;
    private int current = 0;
    private Rotation rotation = Rotation.CLOCKWISE;

    public PlayerRound(Player[] players) {
        this.players = players;
    }

    public Player getCurrPlayer() { // Pemain yang sedang mendapat giliran
        return players[current];
    }

    public void reverseRotation() { // mengubah arah putaran giliran pemain
        if (rotation.equals(Rotation.CLOCKWISE)) {
            rotation = Rotation.COUNTER_CLOCK_WISE;
        }
        else rotation = Rotation.CLOCKWISE;
    }
    

    public Player switch() { // ganti ke pemain selanjutnya
        current = getNextIndex();
        return getCurrPlayer();
    }

    private int getNextIndex() {
        var shiftOne = rotation == Rotation.CLOCKWISE ? 1 : -1;
        return (players.length + current + shiftOne) % players.length;
    }
}