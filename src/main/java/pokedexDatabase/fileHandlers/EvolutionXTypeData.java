package pokedexDatabase.fileHandlers;

import entidades.PokemonEvolution;
import entidades.PokemonType;
import pokedexDatabase.ListGetters;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EvolutionXTypeData implements FileType {

    private ArrayList<PokemonEvolution> evolutions;

    private ListGetters listGetters = new ListGetters();
    public EvolutionXTypeData(ArrayList<PokemonEvolution> evolutions){

        this.evolutions=evolutions;

    }

    @Override
    public ArrayList readData(BufferedReader buffer) throws IOException {
        ArrayList<String> evolutionNames = new ArrayList<String>();
        ArrayList<PokemonType> types = new ArrayList<PokemonType>();

        String linea;
        while ((linea = buffer.readLine()) != null) {
            String[] data = linea.split(",");
            String evolutionName = data[0];
            PokemonType pokemonType = PokemonType.valueOf(data[1]);
            evolutionNames.add(evolutionName);
            types.add(pokemonType);
        }

        PokemonEvolution evolution;
        int i=0; // para recorrer la lista de types de forma simultanea

        for (String name: evolutionNames
        ) {

            evolution = listGetters.getPokemonEvolutionBy(name, evolutions);
            evolution.addType(types.get(i));
            i++;

        }

        return evolutions;
    }

    @Override
    public void writeData(BufferedWriter buffer) throws IOException {

    }

}
