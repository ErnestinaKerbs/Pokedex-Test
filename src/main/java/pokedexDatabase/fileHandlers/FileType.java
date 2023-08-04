package pokedexDatabase.fileHandlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public interface FileType {

    public ArrayList readData(BufferedReader buffer) throws Exception;

    public void writeData(BufferedWriter buffer) throws IOException;

}
