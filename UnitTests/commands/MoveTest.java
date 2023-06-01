package commands;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Board;
import src.Virologist;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    Board board;
    Create create;
    Move move;

    @BeforeEach
    void initEach(){
        System.out.println("A test has begun");
        board = new Board();
        System.out.println("A board has been created");
        create = new Create();
        System.out.println("A create has been created");
        move = new Move();
        System.out.println("A move has been created");
    }

    @Test
    //Alap mozgas teszt ket field kozott
    void moveSuccess() {
        //2 szomszedos mezo letrehozasa
        String[] createArgs = {"create", "field"};
        create.create(createArgs, board);
        createArgs = new String[]{"create", "field", "field0"}; //Szomszedosak egymassal
        create.create(createArgs, board);

        //Virologus letrehozasa
        createArgs = new String[]{"create", "virologist", "0", "0", "0.1", "field0"};   //Letrehozok egy virologust, aki a field1-an kezd
        create.create(createArgs, board);
        Virologist v1 = board.getVirologusok().get(0);

        //A move tenylegese tesztelese
        String[] moveArgs = {"move", "Virologist0", "field1"};
        move.move(moveArgs, board);

        //Az uj mezore lepett-e a virologus
        assertEquals(board.getMezok().get(1), v1.getField());
    }

    @Test
    void moveFailure(){
        //Ezzel letrehozok 3 fieldet egy sorba kotve
        String[] createArgs = {"create","field"};
        create.create(createArgs, board);
        createArgs = new String[]{"create", "field", "field0"};
        create.create(createArgs, board);
        createArgs = new String[]{"create", "field", "field1"};
        create.create(createArgs, board);

        //Virologus letrehozasa
        createArgs = new String[]{"create", "virologist", "0", "0", "0.1", "field0"};   //Letrehozok egy virologust, aki a field1-an kezd
        create.create(createArgs, board);
        Virologist v1 = board.getVirologusok().get(0);

        //Mozgas egy olyan mezore, amire nem lehetseges
        String[] moveArgs = {"move", "Virologist0", "field2"};
        move.move(moveArgs, board);

        //Nem szabadott atlepnie, azaz meg mindig a kezdo mezon kell allnia
        assertEquals(board.getMezok().get(0), v1.getField());
    }
}