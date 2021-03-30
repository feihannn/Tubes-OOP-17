import java.util.*;

/* 
Class Multiple Discard
BELUM MENANGANI:
- buat ngecek declare hiji
- daftar pemain yang masih bertahan
*/

public class MultiDiscard {
    private final PlayerRound playerRound;

    private Deck deck;
    private final Stack<String> discardPile = new Stack<>();

    Scanner scanner = new Scanner(System.in);

    public MultiDiscard(Deck deck, PlayerRound playerRound) { // Ini baru sederhana doang, buat biar kebayang kegunaan fungsi startDiscardPile();
        super();
        this.deck = deck;
        this.playerRound = playerRound;

        startDiscardPile();
    }

    private void startDiscardPile() {               // Saat game baru mulai, pile berasal dari deck
        var card = deck.getCard(); 

        if (card.getType() != null) {
            switch (card.getType()) { 
                case "WILD COLOR":                  // gapapa kalo keluaran pertama itu WILD COLOR, nanti pemain berikutnya yg menentukan warnanya apa
                    discard(card);
                    break;
                case "SKIP":
                    discard(card);
                    playerRound.switch();
                    break;
                case "REVERSE":
                    discard(card);
                    reverse();
                    break;
                case "DRAW 2":
                    discard(card);
                    drawTwoCards(playerRound.getCurrPlayer());
                    playerRound.switch();
                    break;
                case "WILD DRAW 4":
                    startDiscardPile(); // Asumsi: jika keluar wild draw 4 di awal pile, maka diambil ulang dari deck (tidak boleh)
                    break;
                default: rejectStartDiscardPile(card);
            }
        }
        else discard(card); // Buat Card Number
    }

    public void playingCard(PlayerRound playerRound, Card playedCard) { // Game sudah dimulai, Pile berasal dari pemain 
        if (playedCard.getType() != null) {
            switch (playedCard.getType()) {
                case "WILD COLOR":
                    setWildCard(playedCard);
                    discard(playedCard);
                    playerRound.switch();
                    break;
                case "SKIP":
                    checkActionCard(playedCard);
                    discard(playedCard);
                    playerRound.switch();
                    playerRound.switch();
                    break;
                case "REVERSE":
                    checkActionCard(playedCard);
                    discard(playedCard);
                    reverse();
                    break;
                case "DRAW 2":
                    checkActionCard(playedCard);
                    discard(playedCard);
                    playerRound.switch();
                    drawTwoCards(playerRound.getCurrPlayer());
                    playerRound.switch();
                    break;
                case "WILD DRAW 4":
                    setWildCard(playedCard);
                    discard(playedCard);
                    playerRound.switch();
                    drawFourCards(playerRound.getCurrPlayer());
                    playerRound.switch();
                    break;
                default: rejectPlayedCard(playedCard);
            }
        }
        else { // Buat Card Number
            checkNumberCard(playedCard);
            discard(playedCard);
            playerRound.switch();  
        }
    }

    private void checkNumberCard(Card playedCard) {     // cek Match NumberCard di pile teratas dengan yang dikeluarkan pemain
        var topPileCard = viewTopPileCard();

        if (isFirstDiscardAWildCard()) return;          

        if (!CardCheck.isMatchNumberCard(topPileCard, (NumberCard) playedCard)) {
            rejectPlayedCard(playedCard);
        }
    }

    private void checkActionCard(Card playedCard) {     // cek Match type ActionCard di pile teratas dengan yang dikeluarkan pemain
        var topPileCard = viewTopPileCard();                // Asumsi: Draw 2 gaboleh ditumpuk sama Draw 4

        if (isFirstDiscardAWildCard()) return;

        if (!CardCheck.isMatchActionCard(topPileCard, (ActionCard) playedCard)) {
            rejectPlayedCard(playedCard);
        }
    }

    private void setWildCard(Card playedCard) {                 // cek apa kartu benar2 WildCar (warnanya BLACK)
        if (!CardCheck.isValidWildCard((WildCard) playedCard)) {    // sekalian suruh pemain 'ubah' warna jadi (BLUE/RED/GREEN/YELLOW)
            rejectPlayedCard(playedCard);                           // BELUM DIBUAT KALO PEMAIN MASUKIN KATA GAK SESUAI DENGAN KEEMPAT PILIHAN DI ATAS (kayak case-sensitive)
        }
        System.out.println("Masukan warna yang Anda inginkan (BLUE/RED/GREEN/YELLOW): ");
        String color = scanner.next();
        (WildCard) playedCard.color = color;
    }

    private boolean isFirstDiscardAWildCard() {         // ini kebijakan buat yang menentukan warna berikutnya itu sesuai warna kartu yang dikeluarin si pemain pertama
        return discardPile.size() == 1 && viewTopPileCard().getType().equals("WILD COLOR");
    }

    public String viewTopPileCard() {                   // Melihat kartu teratas dari Pile
        return discardPile.get(discardPile.size() - 1);
    }

    private void discard(String card) {                 // ini buat 'nambahin' kartu ke Pile
        discardPile.add(card);
    }

    private void reverse() {                            // ini buat ngubah arah putaran giliran pemain
        playerRound.reverseRotation();
        playerRound.switch();
    }

    private void drawTwoCards(Player player) {          // ini buat nambahin 2 kartu tangan pemain dari deck
        drawCards(player, 2);
    }

    private void drawFourCards(Player player) {         // ini buat nambahin 4 kartu tangan pemain dari deck
        drawCards(player, 4);
    }

    private void drawCards(Player player, int total) {  // ini buat ngambil dari deck ke kartu tangan
        for (int i = 0; i < total; i++) {
            var drawnCard = deck.getCard();
            player.addToHand(drawnCard);                // ini sesuai fungsi yang gua comment di bawah ini
        }
    }


/* Menurut gua harusnya ada fungsi ini di Class PLayer buat nambahin jumlah kartu yang ada di tangannya */

    // public void addToHand(Card card){
    //     kartu.add(card);
    // }

    private void rejectStartDiscardPile(Card card) {    // ini buat ngeluarin pesan misal fungsi getType() dari card error
        throw new UnsupportedOperationException(
            String.format("Tidak mendukung untuk jenis kartu %s" + card.getType()));
    }

    private void rejectPlayedCard(Card playedCard) {    // ini buat ngeluarin pesan kalo type kartu error (gak sesuai dengan type Card yang tersedia) 
        throw new IllegalArgumentException(
            String.format("Kartu %s yang dimainkan tidak sesuai dengan %s", playedCard, viewTopPileCard()));
    }
}