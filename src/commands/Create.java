package commands;

import src.*;

import static src.StringConstants.*;

public class Create {

    /*Seged fuggveny a parseNeighbour*/
    private static class ParseNeighbourReturnClass{
        int[] melyik = new int[8];
        boolean sikertelen = false;
    }
    private static final String FIELDERROR = "I couldn't create the field.";
    private static final String CODE_EXISTS = "This genetical code already exists!";
    private static final String CODE_CREATED = "The genetical code has been created, you can use it later with the reference: ";
    private static final String EQ_CREATED = "has been created you can use it later with the reference: ";

    private static final int CREATE_FIELD_PAR_NUM = 2;
    private static final int CREATE_SHELTER_PAR_NUM = 3;
    private static final int CREATE_WAREHOUSE_PAR_NUM = 5;
    private static final int CREATE_LABORATORY_PAR_NUM = 4;
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

    public static int[] whichSetting(String[] args, int i){
        String fieldSzam = "";
        int[] melyik = new int[8];
        int[] emptyArray = {};
        for(int e = 0; e < melyik.length; e++){
            melyik[e] = -1;
        }
        int melyikSzamlalo = 0;
        if(args[i].contains(FIELD)){
            if((fieldSzam = numberCutDown(args, i)) == null){
                return emptyArray;
            }
            int fieldSzamNum = Integer.parseInt(fieldSzam);
            melyik[melyikSzamlalo] = fieldSzamNum;
            melyikSzamlalo++;
        }
        else if(args[i].contains(SHELTER)){
            if((fieldSzam = numberCutDown(args, i)) == null){
                return emptyArray;
            }
            int fieldSzamNum = Integer.parseInt(fieldSzam);
            melyik[melyikSzamlalo] = fieldSzamNum;
            melyikSzamlalo++;
        }
        else if(args[i].contains(LABORATORY)){
            if((fieldSzam = numberCutDown(args, i)) == null){
                return emptyArray;
            }
            int fieldSzamNum = Integer.parseInt(fieldSzam);
            melyik[melyikSzamlalo] = fieldSzamNum;
            melyikSzamlalo++;
        }
        else if(args[i].contains(WAREHOUSE)){
            if((fieldSzam = numberCutDown(args, i)) == null){
                return emptyArray;
            }
            int fieldSzamNum = Integer.parseInt(fieldSzam);
            melyik[melyikSzamlalo] = fieldSzamNum;
            melyikSzamlalo++;
        }
        else {
            System.out.println("Something is not right with the neighbouring fields.");
            return emptyArray;
        }
        return melyik;
    }

    /*Hozzaadja a parameterkent kapott field-et az argumentumkent kapott fieldhez
     * @param args = Az argumentumok amiket az eredeti fuggveny is megkapott.
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

        String fieldType = args[1];
        System.out.println("A FIELD TYPE: " + fieldType);
        ParseNeighbourReturnClass eredmeny;
        switch(fieldType){
            case FIELD:
                eredmeny = parseNeighbours(args, CREATE_FIELD_PAR_NUM, melyik);
                if(eredmeny.sikertelen){
                    return null;
                }
                melyik = eredmeny.melyik;
                break;
            case SHELTER:
                eredmeny = parseNeighbours(args, CREATE_SHELTER_PAR_NUM, melyik);
                if(eredmeny.sikertelen){
                    return null;
                }
                melyik = eredmeny.melyik;
                break;
            case WAREHOUSE:
                eredmeny = parseNeighbours(args, CREATE_WAREHOUSE_PAR_NUM, melyik);
                if(eredmeny.sikertelen){
                    return null;
                }
                melyik = eredmeny.melyik;
                break;
            case LABORATORY:
                eredmeny = parseNeighbours(args, CREATE_LABORATORY_PAR_NUM, melyik);
                if(eredmeny.sikertelen){
                    return null;
                }
                melyik = eredmeny.melyik;
                break;
            default:
                throw new UnsupportedOperationException();
        }
        for(int i = 0; i < melyik.length; i++){
            if(melyik[i] != -1){
                f1.addNeighbour(board.getMezok().get(melyik[i]));
            }
        }
        return f1;
    }

    private static ParseNeighbourReturnClass parseNeighbours(String[] args, int paramNum, int[] melyik){
        ParseNeighbourReturnClass returnable = new ParseNeighbourReturnClass();
        for(int i = paramNum; i < args.length; i++){
            int[] atmeneti = melyik;
            int x = 0;
            melyik = whichSetting(args, i);
            if(melyik.length == 0){
                returnable.melyik = melyik;
                returnable.sikertelen = true;
                return returnable;
            }
            for(int e = 0; e < melyik.length; e++){
                if(melyik[e] == -1){
                    melyik[e] = atmeneti[x];
                    x++;
                }
            }
        }
        returnable.melyik = melyik;
        returnable.sikertelen = false;
        return returnable;
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
    public void create(String[] args, Board board){
        if(args.length < 2) {
            System.out.println("Not enough parameters!");
            return;
        }

        String createType = args[1];
        switch(createType){
            case VIROLOGIST:
                if(inputCheckVir(args, board)){
                    return;
                }
                int amino;
                int nukleo;
                try {
                    amino = Integer.parseInt(args[2]);
                    nukleo = Integer.parseInt(args[3]);
                }catch(NumberFormatException ex){
                    System.out.println("Invalid Animo or Nukleo number!");
                    return;
                }

                double dodgeChance = Double.parseDouble(args[4]);
                String fieldSzam = args[5].replaceAll("[^0-9]", "");            ///elvileg a replaceAll egy lassu muvelet, de szerintem nem lesz baj  -Dani
                if(fieldSzam.length() == 0){
                    System.out.println("I can't find that field.");
                    return;
                }
                int fieldNumber;
                try {
                    fieldNumber = Integer.parseInt(fieldSzam);
                }catch(NumberFormatException ex){
                    System.out.println("Field ID is invalid!");
                    return;
                }
                Field field = board.getMezok().get(fieldNumber);
            /*--------------------------------------------------------------------------------------------------

                        ITT KELL ATIRNI A VIROLOGUS LETREHOZAST, HA NEM IGY AKARJUK A DETERMINISZTIKUSSAGOT ELRENI,
                        ILLETVE A KONSTRUKTORBAN

            --------------------------------------------------------------------------------------------------*/
                Virologist v1 = new Virologist(amino, nukleo, dodgeChance, field);
                board.getVirologusok().add(v1);
                System.out.println("The virologist has been created, " +
                        "you can later use it with the reference: virologist"+(board.getVirologusok().size()-1));
                break;
            case FIELD:
                Field f1 = new Field();

                /*Ha mar letezik egy masik field, tehat lehet neighbourje es hozza szeretnenk adni*/
                if(args.length > 2){
                    f1 = addingNeighbours(args, f1, board);
                    if(f1 == null){
                        System.out.println(FIELDERROR);
                        return;
                    }
                }
                board.getMezok().add(f1);
                System.out.println("The field has been created, " +
                        "you can use it later with the reference: field"+(board.getMezok().size()-1));
                break;
            /*Meg kell adni, hogy melyik mar korabban letrehozott equipment-tel akarjuk letrehozni a sheltert.*/
            case SHELTER:
                if(createShelter(args, board)){
                    return;
                }

                System.out.println("The shelter has been created, " +
                        "you can use it later with the reference: shelter"+(board.getMezok().size()-1));
                break;
            case LABORATORY:
                /*Feltetelek megtermetese*/
                if(board.getGenetikaiKodok().isEmpty()){
                    System.out.println("If you want to create a laboratory, first you need to create a gcode, " +
                            "that you can put in it.");
                    return;
                }
                /*laborban fellelheto kod ellenorzese, hogy letezik-e*/
                if(hasGCode(args[2], board)){
                    if(args.length <= 3){
                        System.out.println("Not enough arguments");
                        return;
                    }
                    double bearChance = Double.parseDouble(args[3]);   /*Itt már feltetelezve van a 4*/
                    /*Letrehozza az uj labort a megtalalt genetikai koddal*/
                    Laboratory lab1 = new Laboratory(board.getGenetikaiKodok().get(indexOfGCode(args[2], board)), bearChance);
                    /*Hozzaadja a szomszedokat*/
                    if(args.length > 4){
                        lab1 = (Laboratory)addingNeighbours(args, lab1, board);
                        if(lab1 == null){
                            System.out.println(FIELDERROR);
                            return;
                        }
                    }
                    board.getMezok().add(lab1);
                }
                else System.out.println("There is no such genetical code at the moment!");

                System.out.println("The laboratory has been created, " +
                        "you can use it later with the reference: laboratory"+(board.getMezok().size()-1));
                break;
            case WAREHOUSE:
                if(args.length < 5){
                    System.out.println("Not enough arguments!");
                    return;
                }

                int cooldown;
                char type;
                int amount;
                try {
                    cooldown = Integer.parseInt(args[2]);
                    type = args[3].charAt(0);
                    amount = Integer.parseInt(args[4]);
                }catch(NumberFormatException ex){
                    System.out.println("Invalid cooldown or amount!");
                    return;
                }
                if(inputCheckWarehouse(cooldown,type,amount)){
                    return;
                }

                Warehouse w1 = new Warehouse(cooldown, type, amount);
                if(args.length > 5){
                    w1 = (Warehouse) addingNeighbours(args, w1, board);
                    //Ha ferlecsuszna vmi a szomszedok hozzaadasa kozben
                    if(w1 == null){
                        System.out.println(FIELDERROR);
                        return;
                    }
                }
                board.getMezok().add(w1);
                System.out.println("The warehouse has been created, " +
                        "you can use it later with the reference: warehouse"+(board.getMezok().size()-1));
                break;
            case "equipment":
                String eqName = args[2];
                switch(eqName){
                    case CAPE:
                        Cape kopeny = new Cape(Double.parseDouble(args[3]));
                        board.getFelszerelesek().add(kopeny);
                        System.out.println("The"+StringConstants.CAPE+EQ_CREATED+StringConstants.CAPE+(board.getFelszerelesek().size()-1));
                        break;
                    case BAG:
                        Bag taska = new Bag();
                        board.getFelszerelesek().add(taska);
                        System.out.println("The"+StringConstants.BAG+EQ_CREATED+StringConstants.BAG+(board.getFelszerelesek().size()-1));
                        break;
                    case GLOVES:
                        Gloves kesztyu = new Gloves();
                        board.getFelszerelesek().add(kesztyu);
                        System.out.println("The"+StringConstants.GLOVES+EQ_CREATED+StringConstants.GLOVES + (board.getFelszerelesek().size() - 1));
                        break;
                    case AXE:
                        Axe axe = new Axe();
                        board.getFelszerelesek().add(axe);
                        System.out.println("The"+StringConstants.AXE+EQ_CREATED+ StringConstants.AXE + (board.getFelszerelesek().size() - 1));
                        break;
                    default:
                        System.out.println("No such item can be created!");
                        throw new UnsupportedOperationException();
                }
                break;
            default:
                if(args[1].equals("genetical") && args[2].equals("code")){
                    createGCode(args,board);
                }
                else{
                    System.out.println("I don't know that command!");
                }
        }
    }

    private boolean inputCheckVir(String[] args, Board board){
        if(args.length < 5){
            System.out.println("Not enough parameters!");
            return true;
        }
        if(board.getMezok().isEmpty()){
            System.out.println("You can't make a virologist without a field to stand on!");
            return true;
        }
        return false;
    }

    private boolean createShelterPhysically(String[] args, Board board, String item){
        String eqSzam = args[2].substring(item.length());
        if(eqSzam.length() == 0){
            System.out.println("I can't find that item.");
            return true;
        }
        int melyik = Integer.parseInt(eqSzam);
        Shelter s1 = new Shelter(board.getFelszerelesek().get(melyik));
        if(args.length > 3){
            s1 = (Shelter)addingNeighbours(args, s1, board);
            //Ha ferlecsuszna vmi a szomszedok hozzaadasa kozben
            if(s1 == null){
                System.out.println(FIELDERROR);
                return true;
            }
        }
        board.getMezok().add(s1);
        return false;
    }

    private boolean inputCheckWarehouse(int cooldown, char type, int amount){
        if(cooldown < 3){
            System.out.println("The warehouses cooldown must be at least 3!");
            return true;
        }
        else if(type != 'a' && type != 'n'){
            System.out.println("The type must either be letter a or the letter n!");
            return true;
        }
        else if(amount < 1){
            System.out.println("The warehouse must contain at least 1 material!");
            return true;
        }
        return false;
    }

    private boolean createGCodePhysically(Board board, String name){
        if(hasGCode(name, board)){
            System.out.println(CODE_EXISTS);
            return true;
        }
        int price = 1;
        if(name.equals(BEARVIRUS)){
            price = 0;
        }
        board.getGenetikaiKodok().add(new GCode(price, price, name));
        System.out.println(CODE_CREATED+name);
        return false;
    }

    private boolean createShelter(String[] args, Board board){
        /*Feltetelek megtermetese*/
        if(board.getFelszerelesek().isEmpty()) {
            System.out.println("If you want to create a shelter, first you need to create an equipment, " +
                    "that you can put in it.");
            return true;
        }

        String itemName = args[2];
        System.out.println("AZ ITEM NAME: " + itemName);
        if(itemName.contains(BAG)){
            if(createShelterPhysically(args, board, BAG)){
                return true;
            }
        }
        else if(itemName.contains(CAPE)){
            if(createShelterPhysically(args, board, CAPE)){
                return true;
            }
        }
        else if(itemName.contains(GLOVES)){
            if(createShelterPhysically(args, board, GLOVES)){
                return true;
            }
        }
        else if(itemName.contains(AXE)){
            if(createShelterPhysically(args, board, AXE)){
                return true;
            }
        }
        else{
            System.out.println("Something is not right! I can't find the equipment.");
            throw new UnsupportedOperationException();
        }
        return false;
    }

    private void createGCode(String[] args, Board board) {
        /*Ha nem lenne 4. argumnetum megadva*/
        if(args.length < 4){
            System.out.println("Not enough information!");
            return;
        }
        String gcodeName = args[3];
        switch(gcodeName) {
            case PARALYZE:

                if (createGCodePhysically(board, PARALYZE)) {
                    return;
                }
                break;
            case DANCEVIRUS:
                if (createGCodePhysically(board, DANCEVIRUS)) {
                    return;
                }
                break;
            case PROTECTVIRUS:
                if (createGCodePhysically(board, PROTECTVIRUS)) {
                    return;
                }
                break;
            case FORGETVIRUS:
                if (createGCodePhysically(board, FORGETVIRUS)) {
                    return;
                }
                break;
            case BEARVIRUS:
                if (createGCodePhysically(board, BEARVIRUS)) {
                    return;
                }
                break;
            default:
                System.out.println("There is no such virus!");
                throw new UnsupportedOperationException();
        }
    }
}