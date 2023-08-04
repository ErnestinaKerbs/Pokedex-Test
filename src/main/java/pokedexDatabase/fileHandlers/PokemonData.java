package pokedexDatabase.fileHandlers;

import entidades.Pokemon;
import entidades.PokemonLevel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PokemonData implements FileType {

    private ArrayList<Pokemon> pokemonsUpdated = null;

    public PokemonData(){

    }

    public PokemonData(ArrayList<Pokemon> pokemonsUpdated){
        this.pokemonsUpdated=pokemonsUpdated;
    }
    public ArrayList readData(BufferedReader buffer) throws IOException {

        ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
        String linea;
        while ((linea = buffer.readLine()) != null) {
            String[] data = linea.split(",");
            String pokemonName = data[0];
            PokemonLevel level = PokemonLevel.valueOf(data[1]);

            pokemons.add(new Pokemon(pokemonName,level));
        }

        return pokemons;
    }


    public void writeData(BufferedWriter buffer) throws IOException {

        for (Pokemon pokemon: pokemonsUpdated
             ) {
            buffer.write(pokemon.pokemonToTxt()+"\n");

        }
        buffer.flush(); // Asegurar que los datos se escriban en el disco
        System.out.println("Archivo escrito correctamente.");

    }

}
