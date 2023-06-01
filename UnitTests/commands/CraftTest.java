package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Board;
import src.StringConstants;
import src.Virologist;

import static org.junit.jupiter.api.Assertions.*;

class CraftTest {

    Create create;
    Board board;
    Collect collect;
    Craft craft;
    Move move;
    EndTurn endturn;

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
        craft = new Craft();
        System.out.println("A craft has been created");
        endturn = new EndTurn();
        System.out.println("An endturn has been created");
    }

    @Test
        //Sikeresen keszit egy agenst a virologus
    void craftAgentSuccess() {
        //Letrehozom a equipment-et
        String[] createArgs = {"create", "field"};
        create.create(createArgs, board);

        //A virologus letetele a mezore
        createArgs = new String[]{"create", "virologist", "5", "5", "0.1", "field0"};
        create.create(createArgs, board);

        //Letrehozom a gcodot
        createArgs = new String[]{"create","genetical","code", "paralyze"};
        create.create(createArgs, board);

        //Letrehozom a labort
        createArgs = new String[]{"create","laboratory","paralyze","0","field0"};
        create.create(createArgs, board);

        //Virologus mozgatasa a laborba
        String[] moveArgs = new String[]{"move","virologist0","laboratory1"};
        move.move(moveArgs, board);

        //Collecteles a virologussal
        String[] collectArgs = new String[]{"collect","virologist0"};
        collect.collect(collectArgs, board);

        //EndTurn
        String[] endturnArgs = new String[]{"endturn"};
        endturn.endTurn(endturnArgs,board);

        //Paralyze craftolasa
        String[] craftArgs = new String[]{"craft","virologist0", "paralyze"};
        craft.craft(craftArgs, board);

        //Virologus kiszedese
        Virologist v1 = board.getVirologusok().get(0);

        assertEquals(StringConstants.PARALYZE,v1.getInv().getAgents().get(0).getEffect().getMyEffect());
    }

    @Test
        //Megprobal agenst kesziteni a virologus de nincsen eleg nyersanyaga
    void craftAgentFailure() {
        //Letrehozom a equipment-et
        String[] createArgs = {"create", "field"};
        create.create(createArgs, board);
        //A virologus letetele a mezore, nyersanyagok nelkul, hogy fail-re fusson a craftolas
        createArgs = new String[]{"create", "virologist", "0", "0", "0.1", "field0"};
        create.create(createArgs, board);
        //Letrehozom a gcodot
        createArgs = new String[]{"create","genetical","code", "paralyze"};
        create.create(createArgs, board);
        //Letrehozom a labort
        createArgs = new String[]{"create","laboratory","paralyze","0","field0"};
        create.create(createArgs, board);
        //Virologus mozgatasa a laborba
        String[] moveArgs = new String[]{"move","virologist0","laboratory1"};
        move.move(moveArgs, board);
        //Collecteles a virologussal
        String[] collectArgs = new String[]{"collect","virologist0"};
        collect.collect(collectArgs, board);
        //EndTurn
        String[] endturnArgs = new String[]{"endturn", "testing"};
        endturn.endTurn(endturnArgs,board);
        //Paralyze craftolasa
        String[] craftArgs = new String[]{"craft","virologist0", "paralyze"};
        craft.craft(craftArgs, board);

        //Virologus kiszedese
        Virologist v1 = board.getVirologusok().get(0);

        assertEquals(0,v1.getInv().getAgents().size());
    }
}