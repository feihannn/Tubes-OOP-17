import java.util.Timer;
import java.util.TimerTask;
import java.io.*;
import java.util.ArrayList;

//
public class DeclareHiji {

    ArrayList<String> addCard = new ArrayList<String>();// temporary array entar tinggal ganti aja jadi List
    // kartu yangada di tangan pemain

    Deck deck = new Deck();
    Player curPlayer = new Player(getPlayerName(), getPlayerTurn());// ini gw gk ngerti pokoknya diinisiasi siapa yang
                                                                    // mau declare hijinya
    int curPlayerTurn = getPlayerTurn();
    private String str = "";

    TimerTask task = new TimerTask() {
        public void run() {
            if (str.equals("")) {
                System.out.println(getPlayerName() + " didn't declare HIJI, here's 2 cards for you");
                addedCard();
                curPlayerTurn++;
                System.exit(0);
            }
        }
    };

    public ArrayList<String> addedCard() {

        addCard.add(curPlayer.getKartuTangan().toString());
        for (int i = 0; i < 2; i++) {
            addCard.add(deck.getCard().toString());
        }
        return addCard;
    }

    public void getInput() throws Exception {
        Timer timer = new Timer();
        timer.schedule(task, 3 * 1000);

        System.out.println("DECLARE HIJI");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        str = in.readLine();

        timer.cancel();
        System.out.println("You declared HIJI"); // You diganti sama nama playernya
        curPlayerTurn++; // lanjut next orang
    }

    // jangann diapus code dibawah ini soalnya bakaln digunain setiap mau declare
    // hiji

    public static void main(String[] args) {
        try {
            (new DeclareHiji()).getInput();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

/*
 * Notes:
 * 
 * - setiap kali mau declare hiji harus ngikutin code yang ada di fungsi main
 * DeclareHiji ini, ada di atas tulisan ini persis (yang di komen jadi tlg
 * jangan diapus)
 * 
 * - tolong masukin player ke sini supaya kartu yang ada di temporary list bisa
 * di transfer ke list kartu di tangan pemain
 */