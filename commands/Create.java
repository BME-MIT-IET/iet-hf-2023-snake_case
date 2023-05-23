package commands;

import src.*;

import java.util.ArrayList;

public class Create {

    private static final String FIELD = "field";
    private static final String LABORATORY = "laboratory";
    private static final String WAREHOUSE = "warehouse";
    private static final String SHELTER = "shelter";

    /*Megkerdezi, hogy az adott genetikai kod benne van-e a genetikaiKodok listaban
     * ezt hasznalja: create laboratory, genetical code
     * @param String a kod neve
     * @return boolean benne van-e*/
    public static boolean hasGCode(String s, Board board){
        for(GCode gc : board.getGenetikaiKodok()){
            if(gc.getEffect().equals(s)){
                return true;
            }
        }
        return false;
    }

    /*Megkerdezi, hogy az adott genetikai kod hol van a genetikaiKodok listaban
     * ezt hasznalja: create laboratory
     * @param String a kod neve
     * @return int indexe a listaban*/
    public static int indexOfGCode(String s, Board board) {
        int index = -1;
        for (GCode gc : board.getGenetikaiKodok()) {
            if (gc.getEffect().equals(s)) {
                index = board.getGenetikaiKodok().indexOf(gc);
            }
        }
        return index;
    }

    /*Levagja a megfelelo szamu karaktert a szam elott, attol fuggoen, hogy mi van a szam elott
     * @param args = A felhasznalo altal kiadott parancs
     * @param hanyadik = A kiadatt parancsban hanyadik helyen all a levagando elem
     * @return Visszaadja csak a levagott szamot String-kent*/
    public static String numberCutDown(String[] args, int hanyadik){
        String fieldSzam = "";
        if(args[hanyadik].contains(FIELD)){
            fieldSzam = args[hanyadik].substring(5);
        }
        else if(args[hanyadik].contains(LABORATORY)){
            fieldSzam = args[hanyadik].substring(10);
        }
        else if(args[hanyadik].contains(WAREHOUSE)){
            fieldSzam = args[hanyadik].substring(9);
        }
        else if(args[hanyadik].contains(SHELTER)){
            fieldSzam = args[hanyadik].substring(7);
        }
        if(fieldSzam.length() == 0){
            System.out.println("I can't find that field");
            return null;
        }
        return fieldSzam;
    }

    public static int[] MelyikBeallitas(String[] args, int i){
        String fieldSzam = "";
        int[] melyik = new int[8];
        for(int e = 0; e < melyik.length; e++){
            melyik[e] = -1;
        }
        int melyikSzamlalo = 0;
        if(args[i].contains(FIELD)){
            if((fieldSzam = numberCutDown(args, i)) == null){
                int[] emptyArray = {};
                return emptyArray;
            }
            int fieldSzamNum = Integer.parseInt(fieldSzam);
            melyik[melyikSzamlalo] = fieldSzamNum;
            melyikSzamlalo++;
        }
        else if(args[i].contains(SHELTER)){
            if((fieldSzam = numberCutDown(args, i)) == null){
                int[] emptyArray = {};
                return emptyArray;
            }
            int fieldSzamNum = Integer.parseInt(fieldSzam);
            melyik[melyikSzamlalo] = fieldSzamNum;
            melyikSzamlalo++;
        }
        else if(args[i].contains(LABORATORY)){
            if((fieldSzam = numberCutDown(args, i)) == null){
                int[] emptyArray = {};
                return emptyArray;
            }
            int fieldSzamNum = Integer.parseInt(fieldSzam);
            melyik[melyikSzamlalo] = fieldSzamNum;
            melyikSzamlalo++;
        }
        else if(args[i].contains(WAREHOUSE)){
            if((fieldSzam = numberCutDown(args, i)) == null){
                int[] emptyArray = {};
                return emptyArray;
            }
            int fieldSzamNum = Integer.parseInt(fieldSzam);
            melyik[melyikSzamlalo] = fieldSzamNum;
            melyikSzamlalo++;
        }
        else {
            System.out.println("Something is not right with the neighbouring fields.");
            int[] emptyArray = {};
            return emptyArray;
        }
        return melyik;
    }

    /*Hozzaadja a parameterkent kapott field-et az argumentumkent kapott fieldhez
     * @param args = Az argumentumok amiket az erdeti fuggveny is megkapott.
     * @param f1 = Az a field amihez hozza adunk.*/
    public static Field addingNeighbours(String[] args, Field f1, Board board){
        //itt van korlatozva 8-ra
        int[] melyik = new int[8];
        for(int i = 0; i < melyik.length; i++){
            melyik[i] = -1;
        }
        if(args.length > 10){
            System.out.println("Too many fields, the maximum number of neighbouring fields can only be 8");
            return null;
        }
        if(args[1].equals(FIELD)){
            for(int i = 2; i < args.length; i++){
                int[] atmeneti = melyik;
                int x = 0;
                melyik = MelyikBeallitas(args, i);
                if(melyik.length == 0){
                    return null;
                }
                for(int e = 0; e < melyik.length; e++){
                    if(melyik[e] == -1){
                        melyik[e] = atmeneti[x];
                        x++;
                    }
                }
            }
        }
        else if(args[1].equals(SHELTER)){
            for(int i = 3; i < args.length; i++){
                int[] atmeneti = melyik;
                int x = 0;
                melyik = MelyikBeallitas(args, i);
                if(melyik.length == 0){
                    return null;
                }
                for(int e = 0; e < melyik.length; e++){
                    if(melyik[e] == -1){
                        melyik[e] = atmeneti[x];
                        x++;
                    }
                }
            }
        }
        else if(args[1].equals(WAREHOUSE)){
            for(int i = 5; i < args.length; i++){
                int[] atmeneti = melyik;
                int x = 0;
                melyik = MelyikBeallitas(args, i);
                if(melyik.length == 0){
                    return null;
                }
                for(int e = 0; e < melyik.length; e++){
                    if(melyik[e] == -1){
                        melyik[e] = atmeneti[x];
                        x++;
                    }
                }
            }
        }
        else if(args[1].equals(LABORATORY)){
            for(int i = 4; i < args.length; i++){
                int[] atmeneti = melyik;
                int x = 0;
                melyik = MelyikBeallitas(args, i);
                if(melyik.length == 0){
                    return null;
                }
                for(int e = 0; e < melyik.length; e++){
                    if(melyik[e] == -1){
                        melyik[e] = atmeneti[x];
                        x++;
                    }
                }
            }
        }
        for(int i = 0; i < melyik.length; i++){
            if(melyik[i] != -1){
                f1.AddNeighbour(board.getMezok().get(melyik[i]));
            }
        }
        return f1;
    }

    /*Ezzel lehet letrehozni uj dolgokat a jatekterrre
     * @param args = A felhasznalotol kapott input
     * Syntax ha field: 	create [mit] [*optional* milyen szomszeddal]         TOBB SZOMSZED IS LEHET, EZEN MEG DOLGOZOM, LEHET KULON FUGGVENY KENE
     * Syntax ha shelter: 	create [mit] [milyen felszerelessel] [*optional* milyen szomszeddal]
     * Syntax ha laboratory:create [mit] [milyen genetikai koddal] [*optional* milyen szomszeddal]
     * Syntax ha virologus: create [mit] [amino] [nukleo] [hely]
     * Syntax ha equipment: create [mit]
     * Syntax ha warehouse: create [mit] [visszatoltesi ido] [tipus] [mennyiseg] [*optional* milyen szomszeddal]
     * Syntax ha GCode: 	create genetical code [mit]*/
    public void Create(String[] args, Board board){
        if(args.length < 2) {
            System.out.println("Not enough parameters!");
            return;
        }
        if(args[1].equals("virologist")){
            if(args.length < 5){
                System.out.println("Not enough parameters!");
                return;
            }
            if(board.getMezok().size() == 0){
                System.out.println("You can't make a virologist without a field to stand on!");
                return;
            }
            int amino = Integer.parseInt(args[2]);
            int nukleo = Integer.parseInt(args[3]);
            double dodgeChance = Double.parseDouble(args[4]);
            String fieldSzam = args[5].replaceAll("[^0-9]", "");			///elvileg a replaceAll egy lassu muvelet, de szerintem nem lesz baj  -Dani
            if(fieldSzam.length() == 0){
                System.out.println("I can't find that field.");
                return;
            }
            int fieldNumber = Integer.parseInt(fieldSzam);
            Field field = board.getMezok().get(fieldNumber);
            /*--------------------------------------------------------------------------------------------------

                        ITT KELL ATIRNI A VIROLOGUS LETREHOZAST, HA NEM IGY AKARJUK A DETERMINISZTIKUSSAGOT ELRENI,
                        ILLETVE A KONSTRUKTORBAN

            --------------------------------------------------------------------------------------------------*/
            Virologist v1 = new Virologist(amino, nukleo, dodgeChance, field);
            board.getVirologusok().add(v1);
            System.out.println("The virologist has been created, " +
                    "you can later use it with the reference: virologist"+(board.getVirologusok().size()-1));
        }
        else if(args[1].equals(FIELD)){
            Field f1 = new Field();

            /*Ha mar letezik egy masik field, tehat lehet neighbourje es hozza szeretnenk adni*/
            if(args.length > 2){
                f1 = addingNeighbours(args, f1, board);
                if(f1 == null){
                    System.out.println("I couldn't create the field.");
                    return;
                }
            }
            board.getMezok().add(f1);
            System.out.println("The field has been created, " +
                    "you can use it later with the reference: field"+(board.getMezok().size()-1));
        }

        /*Shelter letrehozasa*/
        /*Meg kell adni, hogy melyik mar korabban letrehozott equipment-tel akarjuk letrehozni a sheltert.*/
        else if(args[1].equals(SHELTER)){
            /*Feltetelek megtermetese*/
            if(board.getFelszerelesek().size() == 0) {
                System.out.println("If you want to create a shelter, first you need to create an equipment, " +
                        "that you can put in it.");
                return;
            }
            if(args[2].contains("bag")){
                String eqSzam = args[2].substring(3);
                if(eqSzam.length() == 0){
                    System.out.println("I can't find that bag.");
                    return;
                }
                int melyik = Integer.parseInt(eqSzam);
                Shelter s1 = new Shelter(board.getFelszerelesek().get(melyik));
                if(args.length > 3){
                    s1 = (Shelter)addingNeighbours(args, s1, board);
                }
                board.getMezok().add(s1);
            }else if(args[2].contains("cape")){
                String eqSzam = args[2].substring(4);
                if(eqSzam.length() == 0){
                    System.out.println("I can't find that cape.");
                    return;
                }
                int melyik = Integer.parseInt(eqSzam);
                Shelter s1 = new Shelter(board.getFelszerelesek().get(melyik));
                if(args.length > 3){
                    s1 = (Shelter)addingNeighbours(args, s1, board);
                }
                board.getMezok().add(s1);
            }else if(args[2].contains("gloves")){
                int melyik = Integer.parseInt(args[2].substring(6));
                Shelter s1 = new Shelter(board.getFelszerelesek().get(melyik));
                if(args.length > 3){
                    s1 = (Shelter)addingNeighbours(args, s1, board);
                    //Ha ferlecsuszna vmi a szomszedok hozzaadasa kozben
                    if(s1 == null){
                        System.out.println("I couldn't create the field.");
                        return;
                    }
                }
                board.getMezok().add(s1);
            }
            else if(args[2].contains("axe")){
                int melyik = Integer.parseInt(args[2].substring(3));
                Shelter s1 = new Shelter(board.getFelszerelesek().get(melyik));
                if(args.length > 3){
                    s1 = (Shelter)addingNeighbours(args, s1, board);
                    //Ha ferlecsuszna vmi a szomszedok hozzaadasa kozben
                    if(s1 == null){
                        System.out.println("I couldn't create the field.");
                        return;
                    }
                }
                board.getMezok().add(s1);
            }
            else{
                System.out.println("Something is not right! I can't find the equipment.");
                return;
            }
            System.out.println("The shelter has been created, " +
                    "you can use it later with the reference: shelter"+(board.getMezok().size()-1));
        }
        /*Laboratorium letrehozasa*/
        else if(args[1].equals(LABORATORY)){
            /*Feltetelek megtermetese*/
            if(board.getGenetikaiKodok().size() == 0){
                System.out.println("If you want to create a laboratory, first you need to create a gcode, " +
                        "that you can put in it.");
                return;
            }
            /*laborban fellelheto kod ellenorzese, hogy letezik-e*/
            if(hasGCode(args[2], board)){
                if(args.length < 4){
                    System.out.println("Not enough arguments");
                    return;
                }
                double bearChance = Double.parseDouble(args[3]);
                /*Letrehozza az uj labort a megtalalt genetikai koddal*/
                Laboratory lab1 = new Laboratory(board.getGenetikaiKodok().get(indexOfGCode(args[2], board)), bearChance);
                /*Hozzaadja a szomszedokat*/
                if(args.length > 4){
                    lab1 = (Laboratory)addingNeighbours(args, lab1, board);
                    if(lab1 == null){
                        System.out.println("I couldn't create the field.");
                        return;
                    }
                }
                board.getMezok().add(lab1);
            }
            else System.out.println("There is no such genetical code at the moment!");

            System.out.println("The laboratory has been created, " +
                    "you can use it later with the reference: laboratory"+(board.getMezok().size()-1));
        }
        /*Warehouse letrehozasa*/
        else if(args[1].equals(WAREHOUSE)){
            if(args.length < 5){
                System.out.println("Not enough arguments!");
                return;
            }
            int coold = Integer.parseInt(args[2]);
            char type = args[3].charAt(0);
            int amount = Integer.parseInt(args[4]);
            if(coold < 3){
                System.out.println("The warehouses cooldown must be at least be 3!");
                return;
            }
            else if(type != 'a' && type != 'n'){
                System.out.println("The type must either be letter a or the letter n!");
                return;
            }
            else if(amount < 1){
                System.out.println("The warehouse must contain at least 1 material!");
                return;
            }
            Warehouse w1 = new Warehouse(coold, type, amount);
            if(args.length > 5){
                w1 = (Warehouse) addingNeighbours(args, w1, board);
                //Ha ferlecsuszna vmi a szomszedok hozzaadasa kozben
                if(w1 == null){
                    System.out.println("I couldn't create the field.");
                    return;
                }
            }
            board.getMezok().add(w1);
            System.out.println("The warehouse has been created, " +
                    "you can use it later with the reference: warehouse"+(board.getMezok().size()-1));
        }
        /*Genetikai kod letrehozasa*/
        else if(args[1].equals("genetical") && args[2].equals("code")){
            /*Ha nem lenne 4. argumnetum megadva*/
            if(args.length < 4){
                System.out.println("Not enough information!");
            }
            else if(args[3].equals("paralyze")){
                /*Ha mar letezik a kod ne keszitsunk tobbet, ezt azert gondolom, mert szerintem nem kell tobb kod, mert amik vannak azokat
                 * csak masolgatni fogjuk a virologusokba es a mezokre, de ha mas a megallapodas ki lehet javitani*/
                /*EZT NAGYON JÓL GONDOLOD. by: He. Bence*/
                if(hasGCode("paralyze", board)){
                    System.out.println("This genetical code already exists!");
                    return;
                }
                board.getGenetikaiKodok().add(new GCode(1, 1, "paralyze"));
                System.out.println("The genetical code has been created, " +
                        "you can use it later with the reference: paralyze");
            }
            else if(args[3].equals("virusdance")){
                if(hasGCode("virusdance", board)){
                    System.out.println("This genetical code already exists!");
                    return;
                }
                board.getGenetikaiKodok().add(new GCode(1, 1, "virusdance"));
                System.out.println("The genetical code has been created, " +
                        "you can use it later with the reference: virusdance");
            }
            else if(args[3].equals("protectvirus")){
                if(hasGCode("protectvirus", board)){
                    System.out.println("This genetical code already exists!");
                    return;
                }
                board.getGenetikaiKodok().add(new GCode(1, 1, "protectvirus"));
                System.out.println("The genetical code has been created, " +
                        "you can use it later with the reference: protectvirus");
            }
            else if(args[3].equals("forgetvirus")){
                if(hasGCode("forgetvirus", board)){
                    System.out.println("This genetical code already exists!");
                    return;
                }
                board.getGenetikaiKodok().add(new GCode(1, 1, "forgetvirus"));
                System.out.println("The genetical code has been created, " +
                        "you can use it later with the reference: forgetvirus");
            }
            else if(args[3].equals("bearvirus")){
                if(hasGCode("bearvirus", board)){
                    System.out.println("This genetical code already exists!");
                    return;
                }
                board.getGenetikaiKodok().add(new GCode(0, 0, "bearvirus"));
                System.out.println("The genetical code has been created, " +
                        "you can use it later with the reference: bearvirus");
            }
            /*Ha elirnanak valamit*/
            else{
                System.out.println("There is no such virus!");
            }
            /*Equipment letrehozasa*/
            /*A konzolon beirtnak megfelelo equipmentet hoz letre.*/
        }
        else if(args[1].equals("equipment")){
            if(args[2].equals("cape")) {
                Cape kopeny = new Cape(Double.parseDouble(args[3]));
                board.getFelszerelesek().add(kopeny);
                System.out.println("The cape has been created you can use it later with the reference: cape"+(board.getFelszerelesek().size()-1));
            }else if(args[2].equals("bag")){
                Bag taska = new Bag();
                board.getFelszerelesek().add(taska);
                System.out.println("The bag has been created you can use it later with the reference: bag"+(board.getFelszerelesek().size()-1));
            }else if(args[2].equals("gloves")) {
                Gloves kesztyu = new Gloves();
                board.getFelszerelesek().add(kesztyu);
                System.out.println("The gloves has been created you can use it later with the reference: gloves" + (board.getFelszerelesek().size() - 1));
            }
            else if(args[2].equals("axe")){
                Axe axe = new Axe();
                board.getFelszerelesek().add(axe);
                System.out.println("The axe has been created you can use it later with the reference: axe" + (board.getFelszerelesek().size() - 1));
                }
            else{
                System.out.println("No such item can be created!");
            }
        }
        else{
            System.out.println("I don't know that command!");
        }
    }
}
