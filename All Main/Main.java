import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;

public class Main {
    private static int num_player = 0;
    public static Scanner input = new Scanner(System.in);
    private static ArrayList<Player> pemain = new ArrayList<Player>();
    private static Rotation rotation = Rotation.CLOCKWISE;// Buat bantuin nuker urutan pemain
    private int urutan_sekarang = 0;// Buat nentuin urutan pemain sekarang
    private Player pemain_sekarang = new Player("Dummy", -999);// Pembuatan pemain yang sedang bermain. Dibuat dulu data
                                                               // dummy awal sehingga dapat ditukar-tukar dalam
                                                               // permainan
    private Deck pile_card = new Deck();// Sebagai persiapan kebutuhan deck untuk kartu awal,dll
    private Card top_card = new WildCard(Type.WILDCLR, Color.BLACK);// Membuat kartu paling atas
    private Random random = new Random();// Buat fungsi random
    public Color color_select = Color.BLACK;// Warna inisiasi buat pilihan pemain nanti pas mau ngeluarin wildcard
    public int jumlah_draw = 0;// digunakan untuk menumpuk jumlah draw +2 ATAU +4 yang dikeluarkan
    private String str;// buang ngecheck pas declare hiji
    // Declare Hiji
    TimerTask task = new TimerTask() {
        public void run() {
            if (str != null) {
                if (str.equals("HIJI")) {
                    System.out.println("You declared HIJI");
                } else {
                    System.out.println("You didn't declare HIJI, here's 2 cards for you");
                    pemain_sekarang.drawupto(2);
                    urutan_sekarang++;// lanjut next orang
                    System.exit(0);
                }

            } else {
                System.out.println("You didn't declare HIJI, here's 2 cards for you");
                pemain_sekarang.drawupto(2);
                urutan_sekarang++;// lanjut next orang
                System.exit(0);
            }
        }
    };

    public void getInput() throws Exception {
        Timer timer = new Timer();
        timer.schedule(task, 3 * 1000);

        System.out.println("DECLARE HIJI");
        System.out.println("ketik `HIJI` dalam 3 detik atau ada KONSEKUENSI");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        str = in.readLine();

        timer.cancel();
        urutan_sekarang++;// lanjut next orang
    }
    // public void declare() {
    // try {
    // getInput();

    // } catch (Exception e) {
    // System.out.println(e);

    // }
    // System.out.println("main exit...");
    // }
    // mengubah arah putaran giliran pemain
    private void reverseRotation() {
        if (rotation.equals(Rotation.CLOCKWISE)) {
            rotation = Rotation.COUNTER_CLOCK_WISE;
        } else {
            rotation = Rotation.CLOCKWISE;
        }
    }

    // Melakukan next turn pemain
    private int getNextIndex() {
        var shiftOne = rotation == Rotation.CLOCKWISE ? 1 : -1;
        return (pemain.size() + urutan_sekarang + shiftOne) % pemain.size();
    }

    // Buat fungsi f01 sebenernya
    public void welcome() {
        System.out.print("Masukkan jumlah pemain: ");
        num_player = input.nextInt();
        // Memasukkan nama pemain dan membuat objek playernya
        for (int i = 0; i < num_player; i++) {
            System.out.print("Masukkan nama pemain: ");
            String nama_sementara = input.next();
            pemain.add(new Player(nama_sementara, i));
        }
        // Membuat masing-masing pemain memiliki kartu tangan
        for (int i = 0; i < num_player; i++) {
            pemain.get(i).initialHand();
        }
        // Menampilkan Daftar pemain dan urutannya
        System.out.println("Daftar pemain dan urutannya:");
        for (int i = 0; i < num_player; i++) {
            System.out.println(
                    i + ". " + pemain.get(i).getPlayerName() + ", urutan ke: " + pemain.get(i).getPlayerTurn());
        }
        // Merandom kartu yang paling awal dikeluarkan di arena
        while (top_card.getNum() == null) {
            int randomIndex = random.nextInt(pile_card.getDeck().size());
            top_card = pile_card.getDeck().get(randomIndex);
        }
        System.out.println("Kartu yang sedang ada di arena: " + top_card.getColor().toString() + " "
                + top_card.getNum().toString());
        // Mengeset pemain yang sedang mendapatkan giliran
        pemain_sekarang = pemain.get(urutan_sekarang);
        System.out.println("PERMAINAN DIMULAI!");
        System.out.println("Sekarang giliran pemain: " + pemain_sekarang.getPlayerName());
    }

    // Menampilkan pemain atau fungsi F06
    public void listPlayers() {
        for (int i = 0; i < pemain.size(); i++) {
            System.out.println("Pemain " + (i + 1) + ": " + pemain.get(i).getPlayerName());
            System.out.println("Jumlah Kartu: " + pemain.get(i).getKartuTangan().size());
            if (i == urutan_sekarang) {
                System.out.println("Sedang giliran");
            } else {
                System.out.println("Tidak sedang giliran");
            }
        }
    }

    // Menampilkan urutan pemain sekarang dan berikutnya(fungsi F07)
    public void playerInTurn() {
        System.out.println("Sekarang giliran pemain: " + pemain.get(urutan_sekarang).getPlayerName());
        System.out.println("Urutan pemain berikutnya: " + pemain.get(getNextIndex()).getPlayerName());
    }

    // Menampilkan tampilan bantuan atau deskripsi game(fungsi F08)
    public void help() {
        System.out.println("Deskripsi Permainan: ");
        System.out.println("Mainan kayak UNO cuma ya direname aja jadi HIJI");
    }

    // setter
    public void setPemainSekarang(Player next) {
        pemain_sekarang = next;
    }

    public void setUrutanSekarang(int i) {
        urutan_sekarang = i;
    }

    public void setTopCard(Card kartu) {
        top_card = kartu;
    }

    // getter
    public Player getPemainSekarang() {
        return pemain_sekarang;
    }

    public int getUrutanSekarang() {
        return urutan_sekarang;
    }

    public Card getTopCard() {
        return top_card;
    }

    public static void main(String[] args) {// Main programnya
        Main main_menu = new Main();// Digunakan untuk mengakses semua methode-methode lainnya
        boolean win = false;// Buat menentukan apabila game bisa diselesaikan atau tidak
        boolean same_person = false;// buat prevent biar dia ga draw terus kalo udh ngedraw stackan(+2 atau +4)
        int buang_kartu;// Digunakan untuk player memilih kartu yang ingin dibuang
        System.out.print("Masukkan perintah: ");
        String perintah = input.next();// Memasukkan perintah
        String ganti_warna;// pilih warna yang mau dikeluarin pas ngeluarin wildcard
        // Asumsi pemain harus memulai game terlebih dahulu baru boleh mengakses hal
        // lainnya
        while (!perintah.equals("F01")) {
            System.out.println("Anda belum memulai game, silahkan mulai gamenya terlebih dahulu!");
            System.out.print("Masukkan perintah: ");
            perintah = input.next();
        }
        main_menu.welcome();
        while (!win) {
            boolean done_discard_stack = false;// buat keperluan ngecheck dia udh buang kartu belum pas +2
            if (!same_person) {
                if (main_menu.jumlah_draw == 4 && main_menu.color_select != Color.BLACK) {// Kasus kalo sebelumnya kartu
                                                                                          // arena itu +4
                    System.out.println(
                            "Kartu di arena sebelumnya adalah +4! Anda harus menarik 4 kartu terlebih dahulu!(Proses dilakukan secara otomatis)");
                    main_menu.getPemainSekarang().drawupto(main_menu.jumlah_draw);
                    main_menu.jumlah_draw = 0;
                    System.out.println("Anda berhasil menarik 4 kartu");
                    same_person = true;
                } else if (main_menu.jumlah_draw > 0 && main_menu.color_select == Color.BLACK) {// Kasus kalo
                                                                                                // numpuk-numpuk +2
                    System.out.println(
                            "Kartu di arena sebelumnya adalah +2! Anda harus menumpuk kartu +2 juga atau harus menarik sebanyak "
                                    + main_menu.jumlah_draw);
                    main_menu.getPemainSekarang().getdiscardablestack();
                    if (main_menu.getPemainSekarang().discardable_kartu.size() == 0) {// Kalau gapunya kartu yang bisa
                                                                                      // ditumpuk
                        System.out.println("Anda tidak memiliki kartu +2. Anda harus menarik kartu sebanyak "
                                + main_menu.jumlah_draw + "(Proses dilakukan secara otomatis)");
                        main_menu.getPemainSekarang().drawupto(main_menu.jumlah_draw);
                        main_menu.jumlah_draw = 0;
                        System.out.println("Giliran akan berganti!");// Langsung turn skip
                        main_menu.setUrutanSekarang(main_menu.getNextIndex());
                        main_menu.setPemainSekarang(pemain.get(main_menu.getUrutanSekarang()));
                        System.out.println("Sekarang giliran: " + main_menu.getPemainSekarang().getPlayerName());
                        same_person = false;
                    } else {// Kalau punya kartu yang bisa ditumpuk
                        System.out.print("Pilih kartu yang mau dibuang: ");
                        buang_kartu = input.nextInt();
                        main_menu.setTopCard(main_menu.getPemainSekarang().discardable_kartu.get(buang_kartu - 1));// Menjadikan
                                                                                                                   // kartu
                                                                                                                   // paling
                                                                                                                   // atas
                                                                                                                   // sesuai
                                                                                                                   // dengan
                                                                                                                   // kartu
                                                                                                                   // yang
                                                                                                                   // dibuang
                        main_menu.getPemainSekarang().discard(main_menu.getTopCard());// Buang kartunya
                        if (main_menu.getPemainSekarang().kartu.size() == 1) {// declare HIJI
                            try {
                                (new DeclareHiji()).getInput();
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                        main_menu.jumlah_draw += 2;
                        done_discard_stack = true;
                    }
                }
            }
            System.out.print("Masukkan perintah: ");
            perintah = input.next();
            switch (perintah) {
            case "F01":// Start game
                System.out.println("Anda sudah memulai game!");
                break;
            case "F02":// List kartu tangan
                main_menu.getPemainSekarang().getHand();
                break;
            case "F03":// Discard
                boolean multi_disc = false;// mengecheck apakah player dapat melakukan multi discard
                boolean did_discard = false;// Mengecheck apakah pemain dapat mendiscard kartu atau tidak
                if (!done_discard_stack) {
                    System.out.print("Kartu yang sedang ada di arena: ");// Menampilkan kartu yang sedang ada di arena
                    if (main_menu.getTopCard().getNum() == null) {
                        System.out.println(main_menu.getTopCard().getColor().toString() + " "
                                + main_menu.getTopCard().getType().toString());
                    } else {
                        System.out.println(main_menu.getTopCard().getColor().toString() + " "
                                + main_menu.getTopCard().getNum().toString());
                    }
                    if (main_menu.color_select == Color.BLACK) {// Menampilkan kartu yang bisa dimainkan PADA KASUS UMUM
                        main_menu.getPemainSekarang().getdiscardableHand(main_menu.getTopCard());// Menampilkan kartu
                                                                                                 // apa saja yang bisa
                                                                                                 // dikeluarkan pemain
                    } else {// Menampilkan kartu apabila sudah ada pilihan warna dari pemain sebelumnya
                        main_menu.getPemainSekarang().getdiscardablecolor(main_menu.color_select);
                    }
                    if (main_menu.getPemainSekarang().discardable_kartu.size() != 0) {
                        System.out.print("Pilih kartu yang mau dibuang: ");
                        buang_kartu = input.nextInt();
                        main_menu.setTopCard(main_menu.getPemainSekarang().discardable_kartu.get(buang_kartu - 1));// Menjadikan
                                                                                                                   // kartu
                                                                                                                   // paling
                                                                                                                   // atas
                                                                                                                   // sesuai
                                                                                                                   // dengan
                                                                                                                   // kartu
                                                                                                                   // yang
                                                                                                                   // dibuang
                        main_menu.getPemainSekarang().discard(main_menu.getTopCard());// Buang kartunya
                        if (main_menu.getPemainSekarang().kartu.size() == 1) {// declare HIJI
                            try {
                                (new DeclareHiji()).getInput();
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                        for (int i = 0; i < main_menu.getPemainSekarang().kartu.size(); i++) {// Mengecheck apakah kartu
                                                                                              // masih ada yang sama di
                                                                                              // tangan
                            if ((main_menu.getPemainSekarang().kartu.get(i).getType() == main_menu.getTopCard()
                                    .getType())
                                    && (main_menu.getPemainSekarang().kartu.get(i).getNum() == main_menu.getTopCard()
                                            .getNum())
                                    && (main_menu.getPemainSekarang().kartu.get(i).getColor() == main_menu.getTopCard()
                                            .getColor())) {
                                multi_disc = true;
                            }
                        }
                        did_discard = true;
                        if (multi_disc) {
                            System.out.print(
                                    "Anda memiliki kartu yang sama persis dengan arena sekarang. Apakah anda ingin mengeluarkannya?(Y/N)");
                            perintah = input.next();
                            if (perintah.equals("Y")) {
                                main_menu.getPemainSekarang().discard(main_menu.getTopCard());// Buang kartunya
                            }
                            if (main_menu.getPemainSekarang().kartu.size() == 1) {// declare HIJI
                                try {
                                    (new DeclareHiji()).getInput();
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                            }
                        }
                    }
                }
                // Ganti pemainnya
                System.out.println("Anda telah membuang kartu/tidak memiliki kartu yang dapat dibuang!");
                System.out.println("Giliran akan berganti!");
                if (did_discard) {// Mengembalikan warna ke default dasar kalau pemain bisa mengeluarkan kartu
                    main_menu.color_select = Color.BLACK;
                }
                if (main_menu.getTopCard().getType() != null) {
                    if (main_menu.getTopCard().getType().equals(Type.SKIP.toString())) {// Melakukan skipping turn
                        main_menu.setUrutanSekarang(main_menu.getNextIndex());
                    } else if (main_menu.getTopCard().getType().equals(Type.REVERSE.toString())) {// Melakukan reverse
                                                                                                  // turn
                        main_menu.reverseRotation();
                    } else if (main_menu.getTopCard().getType().equals(Type.WILDCLR.toString())
                            || main_menu.getTopCard().getType().equals(Type.WLDDRAW4.toString())) {// Kalau ganti warna
                        System.out.print("Eits sebeluh berganti, pilih dulu warna wildcardmu: ");
                        ganti_warna = input.next();
                        if (ganti_warna.toLowerCase().equals("red")) {
                            main_menu.color_select = Color.RED;
                        } else if (ganti_warna.toLowerCase().equals("blue")) {
                            main_menu.color_select = Color.BLUE;
                        } else if (ganti_warna.toLowerCase().equals("green")) {
                            main_menu.color_select = Color.GREEN;
                        } else if (ganti_warna.toLowerCase().equals("yellow")) {
                            main_menu.color_select = Color.YELLOW;
                        }
                        if (main_menu.getTopCard().getType().equals(Type.WLDDRAW4.toString())) {
                            main_menu.jumlah_draw += 4;
                        }
                    } else if (main_menu.getTopCard().getType().equals(Type.DRAW2.toString())) {
                        main_menu.jumlah_draw += 2;
                    }
                }
                main_menu.setUrutanSekarang(main_menu.getNextIndex());
                main_menu.setPemainSekarang(pemain.get(main_menu.getUrutanSekarang()));
                System.out.println("Sekarang giliran: " + main_menu.getPemainSekarang().getPlayerName());
                same_person = false;
                break;
            case "F04":// Draw kartu
                main_menu.getPemainSekarang().draw();
                System.out.println("Anda berhasil menarik kartu!");
                break;
            case "F05":// Declare HIJI
                try {
                    (new DeclareHiji()).getInput();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "F06":// Melihat pemain
                main_menu.listPlayers();
                break;
            case "F07":// Melihat now and next player
                main_menu.playerInTurn();
                break;
            case "F08":// Help
                main_menu.help();
                break;
            default:// Kalo selain itu
                System.out.println("Perintah tidak valid!");
            }
            if (main_menu.getPemainSekarang().getKartuTangan().size() == 0) {
                win = true;
            }

        }
        System.out.println("Selamat! " + main_menu.getPemainSekarang().getPlayerName() + " memenangkan game ini!");
    }
}
