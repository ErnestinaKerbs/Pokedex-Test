package pokedexDatabase.fileHandlers;

import pokedexDatabase.ListGetters;
import entidades.Pokemon;
import entidades.PokemonEvolution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PokemonXEvolutionData implements FileType{

    private ArrayList<Pokemon> pokemons;
    private ArrayList<PokemonEvolution> evolutions;

    private ListGetters listGetters = new ListGetters();


    public PokemonXEvolutionData(ArrayList<Pokemon> pokemons, ArrayList<PokemonEvolution> evolutions){
        this.pokemons=pokemons;
        this.evolutions=evolutions;
    }

    @Override
    public ArrayList readData(BufferedReader buffer) throws Exception {
        ArrayList<String> pokemonNames = new ArrayList<String>();
        ArrayList<String> evolutionNames = new ArrayList<String>();
        String linea;
        while ((linea = buffer.readLine()) != null) {
            String[] data = linea.split(",");
            String pokemonName = data[0];
            String evolutionName = data[1];
            pokemonNames.add(pokemonName);
            evolutionNames.add(evolutionName);
        }

        Pokemon pokemon;
        PokemonEvolution evolution;
        int i=0; // para recorrer la lista de evolutions de forma simultanea

        for (String name: pokemonNames
             ) {

            pokemon = listGetters.getPokemonBy(name, pokemons);
            evolution = listGetters.getPokemonEvolutionBy(evolutionNames.get(i),evolutions);
            i++;

            pokemon.addEvolution(evolution);

        }

        return pokemons;
    }

    @Override
    public void writeData(BufferedWriter buffer) throws IOException {

        ArrayList<String> pokemonXEvolution;

            for (Pokemon pokemon: pokemons
            ) {
                pokemonXEvolution = pokemon.pokemonXevolutionToTxt();

                for (String register: pokemonXEvolution
                     ) {
                    buffer.write(register+"\n");
                }

            }
            buffer.flush(); // Asegurar que los datos se escriban en el disco
            System.out.println("Archivo escrito correctamente.");


    }

}
