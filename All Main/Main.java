import java.util.Scanner; 
import java.util.ArrayList;
import java.util.*;
public class Main {
    private static int num_player=0;
    public static Scanner input = new Scanner(System.in);
    private static ArrayList<Player> pemain=new ArrayList<Player>();
    private static Rotation rotation = Rotation.CLOCKWISE;//Buat bantuin nuker urutan pemain
    private int urutan_sekarang=0;//Buat nentuin urutan pemain sekarang
    private Player pemain_sekarang=new Player("Dummy", -999);//Pembuatan pemain yang sedang bermain. Dibuat dulu data dummy awal sehingga dapat ditukar-tukar dalam permainan
    private Deck pile_card=new Deck();//Sebagai persiapan kebutuhan deck untuk kartu awal,dll
    private Card top_card=new WildCard(Type.WILDCLR, Color.BLACK);//Membuat kartu paling atas
    private Random random = new Random();//Buat fungsi random
    
    // mengubah arah putaran giliran pemain
    private void reverseRotation() { 
        if (rotation.equals(Rotation.CLOCKWISE)) {
            rotation = Rotation.COUNTER_CLOCK_WISE;
        }
        else {rotation = Rotation.CLOCKWISE;}
    }
    //Melakukan next turn pemain
    private int getNextIndex() {
        var shiftOne = rotation == Rotation.CLOCKWISE ? 1 : -1;
        return (pemain.size() + urutan_sekarang + shiftOne) % pemain.size();
    }
    //Buat fungsi f01 sebenernya
    public void welcome(){
        System.out.print("Masukkan jumlah pemain: ");
        num_player=input.nextInt();
        //Memasukkan nama pemain dan membuat objek playernya
        for (int i=0;i<num_player;i++){
            System.out.print("Masukkan nama pemain: ");
            String nama_sementara=input.next();
            pemain.add(new Player(nama_sementara, i));
        }
        //Membuat masing-masing pemain memiliki kartu tangan 
        for(int i=0;i<num_player;i++){
            pemain.get(i).initialHand();
        }
        //Menampilkan Daftar pemain dan urutannya
        System.out.println("Daftar pemain dan urutannya:");
        for(int i=0;i<num_player;i++){
            System.out.println(i+". "+pemain.get(i).getPlayerName()+", urutan ke: "+pemain.get(i).getPlayerTurn());
        }
        //Merandom kartu yang paling awal dikeluarkan di arena
        while(top_card.getNum()==null){
            int randomIndex = random.nextInt(pile_card.getDeck().size());
            top_card=pile_card.getDeck().get(randomIndex);
        }
        System.out.println("Kartu yang sedang ada di arena: "+top_card.getColor().toString()+" "+top_card.getNum().toString());
        //Mengeset pemain yang sedang mendapatkan giliran 
        pemain_sekarang=pemain.get(urutan_sekarang);
        System.out.println("PERMAINAN DIMULAI!");
        System.out.println("Sekarang giliran pemain: "+pemain_sekarang.getPlayerName());
    }
    //Menampilkan pemain atau fungsi F06
    public void listPlayers(){
        for(int i=0;i<pemain.size();i++){
            System.out.println("Pemain "+(i+1)+": "+pemain.get(i).getPlayerName());
            System.out.println("Jumlah Kartu: "+pemain.get(i).getKartuTangan().size());
            if(i==urutan_sekarang){
                System.out.println("Sedang giliran");
            }else{System.out.println("Tidak sedang giliran");}
        }
    }
    //Menampilkan urutan pemain sekarang dan berikutnya(fungsi F07)
    public void playerInTurn(){
        System.out.println("Sekarang giliran pemain: "+pemain.get(urutan_sekarang).getPlayerName());
        System.out.println("Urutan pemain berikutnya: "+pemain.get(getNextIndex()).getPlayerName());
    }
    //Menampilkan tampilan bantuan atau deskripsi game(fungsi F08)
    public void help(){
        System.out.println("Deskripsi Permainan: ");
        System.out.println("Mainan kayak UNO cuma ya direname aja jadi HIJI");
    }
    //setter
    public void setPemainSekarang(Player next){
        pemain_sekarang=next;
    }
    public void setUrutanSekarang(int i){
        urutan_sekarang=i;
    }
    public void setTopCard(Card kartu){
        top_card=kartu;
    }
    //getter
    public Player getPemainSekarang(){
        return pemain_sekarang;
    }
    public int getUrutanSekarang(){
        return urutan_sekarang;
    }
    public Card getTopCard(){
        return top_card;
    }
    public static void main(String[] args) {//Main programnya
        Main main_menu=new Main();//Digunakan untuk mengakses semua methode-methode lainnya
        boolean win=false;//Buat menentukan apabila game bisa diselesaikan atau tidak
        boolean done_discard=false;//Mengcheck apakah pemain masih ingin membuang kartu atau tidak
        int buang_kartu;//Digunakan untuk player memilih kartu yang ingin dibuang
        System.out.print("Masukkan perintah: ");
        String perintah=input.next();//Memasukkan perintah
        String opsi_discard;
        //Asumsi pemain harus memulai game terlebih dahulu baru boleh mengakses hal lainnya
        while(!perintah.equals("F01")){
            System.out.println("Anda belum memulai game, silahkan mulai gamenya terlebih dahulu!");
            System.out.print("Masukkan perintah: ");
            perintah=input.next();
        }
        main_menu.welcome();
        while(!win){
            System.out.print("Masukkan perintah: ");
            perintah=input.next();
            switch(perintah){
                case "F01"://Start game
                    System.out.println("Anda sudah memulai game!");
                    break;
                case "F02"://List kartu tangan
                    main_menu.getPemainSekarang().getHand();
                    break;
                case "F03"://Discard
                    boolean multi_disc=false;//mengecheck apakah player dapat melakukan multi discard
                    System.out.print("Kartu yang sedang ada di arena: ");//Menampilkan kartu yang sedang ada di arena
                    if(main_menu.getTopCard().getNum()==null){
                        System.out.println(main_menu.getTopCard().getColor().toString()+" "+main_menu.getTopCard().getType().toString());
                    }else{
                        System.out.println(main_menu.getTopCard().getColor().toString()+" "+main_menu.getTopCard().getNum().toString());
                    }
                    main_menu.getPemainSekarang().getdiscardableHand(main_menu.getTopCard());//Menampilkan kartu apa saja yang bisa dikeluarkan pemain
                    if(main_menu.getPemainSekarang().discardable_kartu.size()!=0){
                        System.out.print("Pilih kartu yang mau dibuang: ");
                        buang_kartu=input.nextInt();
                        main_menu.setTopCard(main_menu.getPemainSekarang().discardable_kartu.get(buang_kartu-1));//Menjadikan kartu paling atas sesuai dengan kartu yang dibuang
                        main_menu.getPemainSekarang().discard(main_menu.getTopCard());//Buang kartunya
                        for (int i=0;i<main_menu.getPemainSekarang().kartu.size();i++){//Mengecheck apakah kartu masih ada yang sama di tangan
                            if((main_menu.getPemainSekarang().kartu.get(i).getType()==main_menu.getTopCard().getType())&&(main_menu.getPemainSekarang().kartu.get(i).getNum()==main_menu.getTopCard().getNum())&&(main_menu.getPemainSekarang().kartu.get(i).getColor()==main_menu.getTopCard().getColor())){
                                multi_disc=true;
                            }
                        }
                        if(multi_disc){
                            System.out.print("Anda memiliki kartu yang sama persis dengan arena sekarang. Apakah anda ingin mengeluarkannya?(Y/N)");
                            perintah=input.next();
                            if(perintah.equals("Y")){
                                main_menu.getPemainSekarang().discard(main_menu.getTopCard());//Buang kartunya
                            }
                        }
                    }
                    //Ganti pemainnya
                    System.out.println("Anda telah membuang kartu/tidak memiliki kartu yang dapat dibuang!");
                    System.out.println("Giliran akan berganti!");
                    main_menu.setUrutanSekarang(main_menu.getNextIndex());
                    main_menu.setPemainSekarang(pemain.get(main_menu.getUrutanSekarang()));
                    System.out.println("Sekarang giliran: "+main_menu.getPemainSekarang().getPlayerName());
                    done_discard=false;
                    break;
                case "F04"://Draw kartu
                    main_menu.getPemainSekarang().draw();
                    break;
                case "F05"://Declare HIJI
                    System.out.println("Declare HIJI");
                    break;
                case "F06"://Melihat pemain
                    main_menu.listPlayers();
                    break;
                case "F07"://Melihat now and next player
                    main_menu.playerInTurn();
                    break;
                case "F08"://Help
                    main_menu.help();
                    break;
                default://Kalo selain itu
                    System.out.println("Perintah tidak valid!");
            }
            if (main_menu.getPemainSekarang().getKartuTangan().size()==0){
                win=true;
            }

        }
        System.out.println("Selamat! "+main_menu.getPemainSekarang().getPlayerName()+" memenangkan game ini!");
    }
}
