import java.util.Timer;
import java.util.TimerTask;
import java.io.*;
import java.util.ArrayList;

//
public class DeclareHiji {
    private Player player = (new Player("Dummy", -999));
    // temporary array entar tinggal ganti aja jadi List
    // kartu yangada di tangan pemain

    Deck deck = new Deck();

    // ArrayList<Card> player = (new Player()).kartu;
    // diisi sama turn player keseluruhan
    private String str = "";

    TimerTask task = new TimerTask() {
        public void run() {
            if (str.equals("")) {
                System.out.println("You didn't declare HIJI, here's 2 cards for you");
                addedCard();
                // turn player ganti ke setelahnya
                System.exit(0);
            }
        }
    };

    public ArrayList<String> addedCard() {
        ArrayList<String> addCard = new ArrayList<String>();
        addCard.add(player.getKartuTangan().toString());
        for (int i = 0; i < 2; i++) {
            String X = deck.getCard().toString();
            addCard.add(X);
            System.out.println(X);
        }
        // System.out.println(addCard);
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
        // turn player ganti ke setelahnya
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