// import player (buat dapetin list kartu ditangannya)
// import pile (kartu yang dibuang biar tau kartu paling atasnya apa)

public class Match {
    Deck deck = new Deck();
    String hand = deck.getCard(); // ini tinggal diganti sama list kartu yang udh di tangan
    String pile = deck.getCard(); // ganti jadi kartu paling atas di pile (deck kartu yang dibuang)
    String[] list1 = hand.split(","); // ini string sebenernya bukan list tapi karena pake split jadi disebut list
                                      // solanya punya 3 variable (kartu yang ditangan)
    String[] list2 = pile.split(","); // ini string sebenernya bukan list tapi karena pake split jadi disebut list
                                      // solanya punya 3 variable (kartu yang di pile)

    public Match() {
        matching();
    }

    public boolean getType() {
        if (list2[2].equals(list1[2])) {
            if (list2[2].equals("null") || list1[2].equals("null"))
                return false;
            else if (list1[2].equals("WILD COLOR") || list1[2].equals("WILD DRAW 4"))
                return true;
            return true;
        } else
            return false;

    }

    public boolean getColor() {
        if (list2[0].equals(list1[0])) {
            return true;
        } else {
            if (list1[0].equals("BLACK")) {
                return true;
            }
            return false;
        }

    }

    public boolean getNumber() {
        if (list2[1].equals(list1[1])) {
            if (list1[1].equals("null")) {
                if (getType())
                    return true;
                else
                    return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean matching() {
        System.out.println(hand);
        System.out.println(pile);

        if (!getColor()) {
            if (!getNumber()) {
                if (!getType()) {
                    return false;
                } else
                    return true;
            } else
                return true;
        } else
            return true;
    }
}
