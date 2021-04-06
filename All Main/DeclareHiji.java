import java.util.Timer;
import java.util.TimerTask;
import java.io.*;
import java.util.ArrayList;
import Card.Deck.java;
import Player.Player.java;

public class DeclareHiji {
    private ArrayList<String> card; // temporary array entar tinggal ganti aja List kartu pemain
    Deck deck = new Deck();
    Player player = new Player (getCurrentPlayerName(),getCurrentPlayerTurn(),getPlayerCard())// getPlayerCard = kartu yang
                                                                                              // ada di tangannya
    private String str = ""; // ini fungsi masih blm kebikin sii, masin garang

    TimerTask task = new TimerTask() {
        public void run() {
            if (str.equals("")) {
                System.out.println("You didn't declare HIJI, here's 2 cards for you");
                for (int i = 0; i < 2; i++) {
                    player.card.add(deck.getCard()); // nambahin 2 biji kartu ke tangan
                }
                player.playerTurn+1;// lanjut next orang
                System.exit(0);
            } else if (str.equals("hiji").toUpperCase()){
                System.out.println("You declared HIJI");
            }
        }
    };

    public DeclareHiji() {
        declare();
    }

    public boolean getInput() throws Exception {
        Timer timer = new Timer();
        timer.schedule(task, 3 * 1000);

        System.out.println("DECLARE HIJI");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        str = in.readLine();

        timer.cancel();
        player.playerTurn+1; // lanjut next orang
    }

    public void declare() {
        try {
            getInput();
            
        } catch (Exception e) {
            System.out.println(e);
            
        }
        // System.out.println("main exit...");
    }
}

// yang di double slash (gasris miring) gw gk tau commandnya apa tapi yang jelas
// instructionnya gt tinggal diikutin aja.
// Soalnya gw liat codenya massi byk yang blm jadi