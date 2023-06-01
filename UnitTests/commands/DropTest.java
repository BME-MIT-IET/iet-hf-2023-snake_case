package commands;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Board;
import src.Equipment;
import src.Shelter;
import src.Virologist;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DropTest {

    Board board;
    Create create;
    Drop drop;
    Equip equip;
    Collect collect;
    EndTurn endTurn;
    String[] endTurnArgs = new String[] {"endturn", "testing"};

    @BeforeEach
    void init(){
        create = new Create();
        board = new Board();
        drop = new Drop();
        collect = new Collect();
        equip = new Equip();
        endTurn = new EndTurn();
    }

    @Test
    //Sikeresen eldob egy targyat
    void dropSuccess() {
        //Letre kell hozni a targyat
        String[] createArgs = new String[]{"create", "equipment", "bag"};
        create.create(createArgs, board);

        //Field letrehozasa
        createArgs = new String[] {"create", "shelter", "bag0"};
        create.create(createArgs, board);

        //Shelter kiszervezese a kesobbi hivatkozasert
        Shelter shelter = (Shelter) board.getMezok().get(0);

        //Letrehozom a virologust
        createArgs = new String[]{"create", "virologist", "0", "0", "0", "shelter0"};
        create.create(createArgs, board);

        //Hozzaadom a virologushoz a targyat
        String[] collectArgs = {"collect", "virologist0"};
        collect.collect(collectArgs ,board);

        //Virologus kiemelese
        Virologist v1 = board.getVirologusok().get(0);

        //Sikeresen fel lett veve
        assertEquals(1, v1.getInv().GetEquipments().size());
        assertEquals(2, shelter.getCounter());

        //End Turn
        endTurn.endTurn(endTurnArgs, board);

        //Eldobjuk, mert van
        String[] dropArgs = new String[]{"drop", "virologist0", "bag"};
        drop.drop(dropArgs, board);

        ArrayList<Equipment> emptyEquipmentArrayList = new ArrayList<>();
        //Ezutan nem mardhat nala
        assertEquals(emptyEquipmentArrayList, v1.getInv().GetEquipments());

        //Viszont a shelternek, most 3-on kell lennie a counternek
        assertEquals(3, shelter.getCounter());
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