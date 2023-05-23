package src;

import java.io.FileNotFoundException;

public interface CommandsInterface {
    void execute(String[]args) throws FileNotFoundException;
}
