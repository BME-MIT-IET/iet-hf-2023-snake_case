package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Board;
import src.Equipment;
import src.StringConstants;
import src.Virologist;

import static org.junit.jupiter.api.Assertions.*;

class StealTest {
    Board board;
    Create create;
    Collect collect;
    Move move;
    EndTurn endturn;
    Steal steal;
    Equip equip;

    ApplyEffect applyeffect;

    @BeforeEach
    void initEach(){
        System.out.println("A test has begun");
        board = new Board();
        System.out.println("A board has been created");
        create = new Create();
        System.out.println("A create has been created");
        collect = new Collect();
        System.out.println("A collect has been created");
        move = new Move();
        System.out.println("A move has been created");
        endturn = new EndTurn();
        System.out.println("An endturn has been created");
        steal = new Steal();
        System.out.println("A steal has been created");
        equip = new Equip();
        System.out.println("An endturn has been created");
        applyeffect = new ApplyEffect();
        System.out.println("An applyeffect has been created");
    }

    @Test
        //Sikeresen megtamadja a masik virologust
    void stealFromVirologistSuccess() {
        //Letrehozom a field-et
        String[] createArgs = {"create", "field"};
        create.create(createArgs, board);

        //A virologus letetele a mezore
        createArgs = new String[]{"create", "virologist", "5", "5", "0", "field0"};
        create.create(createArgs, board);

        //A virologus letetele a mezore
        createArgs = new String[]{"create", "virologist", "5", "5", "0", "field0"};
        create.create(createArgs, board);

        //Letrehozom az equipmentet
        createArgs = new String[]{"create","equipment","cape", "0"};
        create.create(createArgs, board);

        //Letrehozom a gcodot
        createArgs = new String[]{"create","genetical","code", "paralyze"};
        create.create(createArgs, board);

        //Equipelem a targyat
        String[] equipArgs = new String[]{"virologist0","cape0"};
        equip.equip(equipArgs, board);

        //Endturn
        String[] endturnArgs = new String[]{"endturn","testing"};
        endturn.endTurn(endturnArgs,board);

        //Effect alkalmazasa, hogy lehessen lopni
        String[] applyeffectArgs = new String[]{"applyeffect","virologist1","paralyze"};
        applyeffect.applyEffect(applyeffectArgs, board);

        //Lopas vegrehajtasa
        String[] stealArgs = new String[]{"steal","virologist1","virologist0", "cape"};
        steal.steal(stealArgs,board);

        //Virologus kiszedese
        Virologist v1 = board.getVirologusok().get(1);
        Virologist v2 = board.getVirologusok().get(0);
        System.out.println(v1.getInv().GetEquipments().size());
        System.out.println(v2.getInv().GetEquipments().size());

        assertEquals(1,v1.getEffects().getEffects().size());
    }

  /*  @Test
        //Megprobalja megtamadni a masik virologust, de nem tudja mert az dodge-olja a tamadast
    void stealFromVirologistFailure() {
        //Letrehozom a gcodot
        String[] createArgs = new String[]{"create","genetical","code", "paralyze"};
        create.create(createArgs, board);

        //Letrehozom a labort
        createArgs = new String[]{"create","laboratory","paralyze","0"};
        create.create(createArgs, board);

        //A virologus letetele a mezore, nyersanyagok nelkul, hogy fail-re fusson a craftolas
        createArgs = new String[]{"create", "virologist", "5", "5", "0.1", "laboratory0"};
        create.create(createArgs, board);

        //A virologus letetele a mezore, nyersanyagok nelkul, hogy fail-re fusson a craftolas
        createArgs = new String[]{"create", "virologist", "5", "5", "1", "laboratory0"};
        create.create(createArgs, board);

        //Collecteles a virologussal
        String[] collectArgs = new String[]{"collect","virologist0"};
        collect.collect(collectArgs, board);

        //EndTurn
        String[] endturnArgs = new String[]{"endturn","testing"};
        endturn.endTurn(endturnArgs,board);

        //Paralyze craftolasa
        String[] craftArgs = new String[]{"craft","virologist0", "paralyze"};
        craft.craft(craftArgs, board);

        //EndTurn
        endturnArgs = new String[]{"endturn","testing"};
        endturn.endTurn(endturnArgs,board);

        //Virologus tamadasa
        String[] attackArgs = new String[]{"attack","virologist0","virologist1", "paralyze"};
        attack.attack(attackArgs, board);

        //Virologus kiszedese
        Virologist v1 = board.getVirologusok().get(1);

        assertEquals(0,v1.getEffects().getEffects().size());
    }*/
}