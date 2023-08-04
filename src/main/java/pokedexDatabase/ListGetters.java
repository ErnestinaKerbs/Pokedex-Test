package pokedexDatabase;

import entidades.Pokemon;
import entidades.PokemonEvolution;
import entidades.PokemonLevel;
import entidades.PokemonType;

import java.util.ArrayList;

public class ListGetters {

    public Pokemon getPokemonBy(String name, ArrayList<Pokemon> pokemons) throws Exception {
        for (Pokemon pokemon: pokemons
        ) {

            if(pokemon.nameEqualsTo(name)){
                return pokemon;
            }

        }

        throw new Exception("No se encontró pokemon");
    }



    public PokemonEvolution getPokemonEvolutionBy(String name, ArrayList<PokemonEvolution> evolutions){

        for (PokemonEvolution evolution: evolutions
        ) {

            if(evolution.nameEqualsTo(name)){
                return evolution;
            }

        }

        return null;
    }

    public void removeTypeFromList(PokemonType toRemove, ArrayList<PokemonType> types){
        //types.removeIf(type -> type.toString().equalsIgnoreCase(toRemove.toString()));
        types.removeIf(type->type.equals(toRemove));
    }

    public void removePokemonFromList(Pokemon toRemove, ArrayList<Pokemon> pokemons){
        //types.removeIf(type -> type.toString().equalsIgnoreCase(toRemove.toString()));
        pokemons.removeIf(pokemon->pokemon.nameEqualsTo(toRemove.getName()));
    }
}
