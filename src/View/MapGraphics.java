package View;

import commands.Move;
import src.Board;
import src.Field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

//Beagyazott Osztaly, ha mas nem ideiglenesen
public class MapGraphics extends JPanel {
    //Tarolja a jelenleg megjelenitett poligonokat
    private ArrayList<Polygon> polys = new ArrayList<>();
    private ArrayList<Integer> activeFieldIds = new ArrayList<>();
    private int clickedX;
    private int clickedY;
    private Board board;
    private GameWindow gameWindow;

    private MapGraphics that;

    SystemCall system;

    public MapGraphics(Board board, GameWindow gameWindow, SystemCall systemCall){
        this.board = board;
        this.gameWindow = gameWindow;
        this.system = systemCall;
        this.that = this;
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //beallitja, hogy hova nyomott
                that.setClickedX(e.getX());
                that.setClickedY(e.getY());

                //Tesztkiiras
                system.out().println("You clicked: x:"+that.getClickedX()+" y:"+that.getClickedY());

                //Ha valamire nyomott, ujrarajzolas!            Egyebkent ez nem ide kell feltetlenul, de nem tuom hova tegyuk, egyenlore szerintem jo ide -Dani
                if(that.checkHit()) {
                    that.repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //kötelezően meg kell jelennie
                //Szándékosan em csinál semmit!
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //kötelezően meg kell jelennie
                //Szándékosan em csinál semmit!
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //kötelezően meg kell jelennie
                //Szándékosan em csinál semmit!
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //kötelezően meg kell jelennie
                //Szándékosan em csinál semmit!
            }
        });

        //Meret beallitas
        this.setPreferredSize(new Dimension(800, 400));
    }

    //Minden ujrarajzolaskor ez meghivodik, frissitve a map-et
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, 800, 400);   //(20,20,780,380)
        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));

        //Mindig nullazodik a poligon arraylist, hogy frissiteni tudjuk a jelenleg latottakat
        polys.clear();

        //Megkeresi, hogy hol van a virologus, illetve a szomszedos mezoket
        int virFieldId = -1;
        for(int i = 0; i < board.getMezok().size(); i++) {
            if(board.getVirologusok().get(0).getField().equals(board.getMezok().get(i))) {
                virFieldId = i;
            }
        }

        //megkeressuk, hoyg milyen id-vel rendelkeznek a szomszedok
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(virFieldId);
        ArrayList<Field> fields = board.getMezok().get(virFieldId).getNeighbours();
        for(int i = 0; i < fields.size(); i++) {
            for (int j = 0; j < board.getMezok().size(); j++) {
                if(fields.get(i).equals(board.getMezok().get(j))) {
                    ids.add(j);
                }
            }
        }
        //eltarolja egy valtozoba is, ha a jovoben kellene
        activeFieldIds = ids;




        int[][] Xs = new int[board.getPolyKoordok().size()][8];
        int[][] Ys = new int[board.getPolyKoordok().size()][8];
        int[] n = new int [board.getPolyKoordok().size()];

        //Beolvassa a poligonok koordinatait
        for (int i = 0; i < board.getPolyKoordok().size(); i++) {

            for(int j = 0; j < board.getPolyKoordok().get(i).length; j++) {
                if (j == 0) {
                    n[i] = board.getPolyKoordok().get(i)[j];
                }
                else if (j % 2 == 1) {
                    Xs[i][j/2] = (int)(board.getPolyKoordok().get(i)[j] * 1.3);
                }
                else if (j % 2 == 0) {
                    Ys[i][(j-1)/2] = (int)(board.getPolyKoordok().get(i)[j] * 1.3);
                }
            }



        }
        //Megkeresem a virologus mezojenek a min es max koordinatait
        int maxx = 0;
        int maxy = 0;
        int minx = 800;
        int miny = 400;
        for (int i = 0; i < n[virFieldId]; i++) {
            //Ha barmelyik kooridnata kisebb/nagyobb mint a max/min, akkor az lesz az uj max/min
            if(Xs[virFieldId][i] < minx)
                minx = Xs[virFieldId][i];
            else if(Xs[virFieldId][i] >
                    maxx) maxx = Xs[virFieldId][i];
            if(Ys[virFieldId][i] < miny)
                miny = Ys[virFieldId][i];
            else if(Ys[virFieldId][i] >
                    maxy) maxy = Ys[virFieldId][i];
        }
        //Kiszamoljuk, milyen szeles es magas a jovobeli Polygon
        int width = maxx - minx;
        int height = maxy - miny;
        //Ebbol kiszamitjuk a Polygon bal felso pontjahoz vezeto eltolast
        int eltolx = (400 - width/2) - minx;
        int eltoly = (200 - height/2) - miny;
        //Minden mezo polygonjanak minden pontjat eltoljuk az eltolas szammal, igy a kozeppontban lesz a jatekos mezoje
        for (int i = 0; i < board.getPolyKoordok().size(); i++) {
            for(int j = 0; j < n[i]; j++) {
                Xs[i][j] += eltolx;
                Ys[i][j] += eltoly;
            }
        }


        //Polygonok letrehozasa a mar beallitott x es y koordinatakkal
        for (int i = 0; i < board.getPolyKoordok().size(); i++) {
            Polygon p = new Polygon(Xs[i],Ys[i], n[i]);
            polys.add(p);
        }



        //Kirajzolja a poligonokat egyesevel
        for(int i = 0; i < polys.size(); i++) {
            if(activeFieldIds.contains(i)) {
                g2d.setColor(new Color(150, 85, 0));
                g2d.draw(polys.get(i));
                //A mezo amin a virologus all, az kek szinu lesz
                if(activeFieldIds.get(0) == i) {
                    if(board.getMezok().get(i).getVirologists().size() > 1){
                        g2d.setColor(new Color(255, 0, 0));
                    }
                    else {
                        g2d.setColor(new Color(200, 200, 255));
                    }
                }
                //Ha ures mezo a szomszed, akkor szurke szinu, jelezve, hogy nem hangos
                else if(board.getMezok().get(i).getClass().getSimpleName().equals("Field")){
                    g2d.setColor(new Color(220, 220, 220));
                }
                //ha nem ures mezo, akkor sargas szinet kap
                else {
                    g2d.setColor(new Color(230, 230, 100));
                }
                g2d.fill(polys.get(i));
            }
        }



    }

    //MouseActionListener megadja az x y koordinatakat
    public void setClickedX(int x) {
        clickedX = x;
    }

    public void setClickedY(int y) {
        clickedY = y;
    }
    //Le is lehet kerdezni oket (hatah kell)
    public int getClickedX() {
        return clickedX;
    }

    public int getClickedY() {
        return clickedY;
    }

    public boolean checkHit() {
        for(int i = 0; i < polys.size(); i++){
            if(polys.get(i).contains(clickedX, clickedY)) {
                if(!board.getAction() && !board.getMove()){
                    gameWindow.noMoreAction();
                }
                system.out().println("Field"+ i +"-re nyomott!");
                Move move = new Move();
                String[] args;
                String bemenet = "move virologist0 field" + i;
                args = bemenet.split(" ");
                move.move(args, board);
                return true;
            }
        }
        return false;
    }

    public void setActiveFieldIds(ArrayList<Integer> ids) {
        activeFieldIds.clear();
        activeFieldIds = ids;
    }

}
