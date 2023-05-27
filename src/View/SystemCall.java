package View;

public class SystemCall {
    public void exit(int exitParam){
        System.exit(exitParam);
    }

    public java.io.PrintStream out(){
        return System.out;
    }
}
