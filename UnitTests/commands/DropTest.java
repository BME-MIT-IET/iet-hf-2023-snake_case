package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Board;
import src.Equipment;
import src.Virologist;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DropTest {

    Board board;
    Create create;
    Drop drop;
    Equip equip;
    Collect collect;

    @BeforeEach
    void init(){
        create = new Create();
        board = new Board();
        drop = new Drop();
        collect = new Collect();
        equip = new Equip();
    }

    @Test
    //Sikeresen eldob egy targyat
    void dropSuccess() {
        //Field letrehozasa
        String[] createArgs = {"create", "field"};
        create.create(createArgs, board);

        //Letre kell hozni a targyat
        createArgs = new String[]{"create", "equipment", "bag"};
        create.create(createArgs, board);

        //Letrehozom a virologust
        createArgs = new String[]{"create", "virologist", "0", "0", "0", "field0"};
        create.create(createArgs, board);

        //Hozzaadom a virologushoz a targyat
        String[] equipArgs = {"equip", "virologist0", "bag0"};
        equip.equip(equipArgs ,board);

        //Virologus kiemelese
        Virologist v1 = board.getVirologusok().get(0);

        //Sikeresen fel lett veve
        assertEquals(board.getFelszerelesek().get(0), v1.getInv().GetEquipments().get(0));

        //Eldobjuk, mert van
        String[] dropArgs = new String[]{"drop", "virologist0", "bag"};
        drop.drop(dropArgs, board);

        ArrayList<Equipment> emptyEquipmentArrayList = new ArrayList<>();
        //Ezutan nem mardhat nala
        assertEquals(emptyEquipmentArrayList, v1.getInv().GetEquipments());
    }

    @Test
    void dropFailure(){
        //Field letrehozasa
        String[] createArgs = {"create", "field"};
        create.create(createArgs, board);

        //Letrehozom a virologust
        createArgs = new String[]{"create", "virologist", "0", "0", "0", "field0"};
        create.create(createArgs, board);

        //Virologus kiemelese
        Virologist v1 = board.getVirologusok().get(0);

        //Sikeresnek kell lennie, mert ures mind a 2
        assertEquals(board.getFelszerelesek(), v1.getInv().GetEquipments());

        //Megprobalunk eldboni valamit
        String[] dropArgs = new String[]{"drop", "virologist0", "bag"};
        drop.drop(dropArgs, board);

        ArrayList<Equipment> emptyEquipmentArrayList = new ArrayList<>();
        //Ezutan nem mardhat nala
        assertEquals(emptyEquipmentArrayList, v1.getInv().GetEquipments());
    }
}