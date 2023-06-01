package commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import src.Board;
import src.Equipment;
import src.Virologist;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CollectTest {

    Create create;
    Board board;
    Collect collect;

    @BeforeEach
    void initEach(){
        System.out.println("A test has begun");
        board = new Board();
        System.out.println("A board has been created");
        create = new Create();
        System.out.println("A create has been created");
        collect = new Collect();
        System.out.println("A collect has been created");
    }

    @Test
    //Sikeresen felvesz egy genetical kodot a virologus egy laborbol
    void collectEquipmentSuccess() {
        //Letrehozom a equipment-et
        String[] createArgs = {"create", "equipment", "cape", "0"};
        create.create(createArgs, board);
        //A sheltert
        createArgs = new String[]{"create","shelter","cape0"};
        create.create(createArgs, board);
        //A virologus letetele a mezore
        createArgs = new String[]{"create", "virologist", "0", "0", "0.1", "shelter0"};
        create.create(createArgs, board);

        //Virologus kiszedese
        Virologist v1 = board.getVirologusok().get(0);

        //Felveszi az equipmentet
        String[] collectArgs = {"collect", "virologist0"};
        collect.collect(collectArgs, board);

        //A megfelelo equipmentnek nala kell lennie
        assertEquals(board.getFelszerelesek().get(0) ,v1.getInv().GetEquipments().get(0));
    }

    @Test
    //Egy olyan mezon szeretne collectelni, ahol nincs mit
    void collectEquipmentFailure() {
        String[] createArgs = {"create", "equipment", "cape", "0"};
        create.create(createArgs, board);
        createArgs = new String[]{"create","shelter", "cape0"};
        create.create(createArgs, board);
        createArgs = new String[]{"create", "field", "shelter0"};
        create.create(createArgs, board);

        //A shelterrel szomszedos ures mezore teszem le a virologust
        createArgs = new String[]{"create", "virologist", "0", "0", "0.1", "field1"};
        create.create(createArgs, board);

        //Virologus kiszedese
        Virologist v1 = board.getVirologusok().get(0);
        //Egy ures equipment listat letrehozok, hogy lehessen mihez hasonlitani
        ArrayList<Equipment> emptyEquipmentArrayList = new ArrayList<>();

        //Megprobalja felvenni az equipmentet
        String[] collectArgs = {"collect", "virologist0"};
        collect.collect(collectArgs, board);

        //Uresnek kell lennie a felszerelesek listajanak
        assertEquals(emptyEquipmentArrayList ,v1.getInv().GetEquipments());
    }
}