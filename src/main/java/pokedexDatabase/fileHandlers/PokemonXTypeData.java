package pokedexDatabase.fileHandlers;

import entidades.Pokemon;
import entidades.PokemonType;
import pokedexDatabase.ListGetters;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PokemonXTypeData implements FileType{

    private ArrayList<Pokemon> pokemons;

    private ListGetters listGetters = new ListGetters();
    public PokemonXTypeData(ArrayList<Pokemon> pokemons){

        this.pokemons=pokemons;

    }

    @Override
    public ArrayList readData(BufferedReader buffer) throws Exception {
        ArrayList<String> pokemonNames = new ArrayList<String>();
        ArrayList<PokemonType> types = new ArrayList<PokemonType>();

        String linea;
        while ((linea = buffer.readLine()) != null) {
            String[] data = linea.split(",");
            String pokemonName = data[0];
            PokemonType pokemonType = PokemonType.valueOf(data[1]);
            pokemonNames.add(pokemonName);
            types.add(pokemonType);
        }

        Pokemon pokemon;
        int i=0; // para recorrer la lista de types de forma simultanea

        for (String name: pokemonNames
        ) {

            pokemon = listGetters.getPokemonBy(name, pokemons);
            pokemon.addType(types.get(i));
            i++;

        }

        return pokemons;
    }

    @Override
    public void writeData(BufferedWriter buffer) throws IOException {

        ArrayList<String> pokemonXType;

        for (Pokemon pokemon: pokemons
        ) {
            pokemonXType = pokemon.pokemonXtypeToTxt();

            for (String register: pokemonXType
            ) {
                buffer.write(register+"\n");
            }

        }
        buffer.flush();
        System.out.println("\n[INFO] File correctly loaded.");
    }


}
