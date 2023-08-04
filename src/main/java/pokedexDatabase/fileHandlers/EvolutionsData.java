package pokedexDatabase.fileHandlers;

import entidades.PokemonEvolution;
import entidades.PokemonLevel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EvolutionsData implements FileType{
    @Override
    public ArrayList readData(BufferedReader buffer) throws IOException {
        ArrayList<PokemonEvolution> evolutions = new ArrayList<PokemonEvolution>();
        String linea;
        while ((linea = buffer.readLine()) != null) {
            String[] data = linea.split(",");
            String evolutionName = data[0];
            PokemonLevel evolvedLevel = PokemonLevel.valueOf(data[1]);
            evolutions.add(new PokemonEvolution(evolutionName,evolvedLevel));
        }

        return evolutions;
    }

    @Override
    public void writeData(BufferedWriter buffer) throws IOException {

    }
}
