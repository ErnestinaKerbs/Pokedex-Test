package pokedexDatabase.fileHandlers;

import entidades.Pokemon;
import entidades.PokemonAbility;
import pokedexDatabase.ListGetters;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PokemonXAbilityData implements FileType {

    private ArrayList<Pokemon> pokemons;

    private ListGetters listGetters = new ListGetters();
    public PokemonXAbilityData(ArrayList<Pokemon> pokemons){

        this.pokemons=pokemons;

    }

    @Override
    public ArrayList readData(BufferedReader buffer) throws Exception {
        ArrayList<String> pokemonNames = new ArrayList<String>();
        ArrayList<PokemonAbility> abilities = new ArrayList<PokemonAbility>();

        String linea;
        while ((linea = buffer.readLine()) != null) {
            String[] data = linea.split(",");
            String pokemonName = data[0];
            PokemonAbility pokemonAbility = PokemonAbility.valueOf(data[1]);
            pokemonNames.add(pokemonName);
            abilities.add(pokemonAbility);
        }

        Pokemon pokemon;
        int i=0; // para recorrer la lista de abilities de forma simultanea

        for (String name: pokemonNames
        ) {

            pokemon = listGetters.getPokemonBy(name, pokemons);
            pokemon.addAbility(abilities.get(i));
            i++;

        }

        return pokemons;
    }

    @Override
    public void writeData(BufferedWriter buffer) throws IOException {

        ArrayList<String> pokemonXability;

        for (Pokemon pokemon: pokemons
        ) {
            pokemonXability = pokemon.pokemonXabilityToTxt();

            for (String register: pokemonXability
            ) {
                buffer.write(register+"\n");
            }

        }
        buffer.flush(); // Asegurar que los datos se escriban en el disco
        System.out.println("Archivo escrito correctamente.");

    }

}
