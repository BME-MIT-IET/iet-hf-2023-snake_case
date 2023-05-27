package commands;

import src.Board;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Start {

    public void start(String[] args, Board b) throws FileNotFoundException {
        File file = null;
        if(args.length>=1){
            file = new File(args[0]);
        }else{
            file = new File("map1.txt");
        }

        Scanner sc = new Scanner(file);
        String[] commands;
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            commands = s.split(" ");
            if (commands[0].equals("create")) {
                Create createLoad = new Create();
                createLoad.create(commands, b);
            }
        }
        sc.close();
        
        //Map pont txt beolvasas, ami kell a megjeleniteshez
        if(args.length>=2){
            file = new File(args[0]);
        }else{
            file = new File("mapkoord1.txt");
        }
        sc = new Scanner(file);
        while (sc.hasNextLine()) {
        	String s = sc.nextLine();
        	String[] sarr = s.split(",", 0);
        	int[] intarr = new int[sarr.length];
        	for(int i = 0; i < sarr.length; i++) {
        		intarr[i] = Integer.parseInt(sarr[i]);
        	}
        	b.getPolyKoordok().add(intarr);
        }
        
        sc.close();
    }
}
