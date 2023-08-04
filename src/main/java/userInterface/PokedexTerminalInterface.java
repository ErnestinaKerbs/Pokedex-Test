package userInterface;

import entidades.*;

import java.util.*;

public class PokedexTerminalInterface {
    private Scanner scanner = new Scanner(System.in);

    public void abilities(String pokemonName,ArrayList<PokemonAbility> abilities ){

        System.out.printf("\n %s has these abilities: ", pokemonName);

        for(int i=0;i<abilities.size();i++){
            System.out.print("\n\t" + abilities.get(i));
        }

    }

    public void evolutions(String pokemonName, ArrayList<PokemonEvolution> evolutions){

        System.out.printf("\n %s has these evolutions: ", pokemonName);
        printEvolutions(evolutions);

    }

    public void evolutions(ArrayList<PokemonEvolution> evolutions){
        System.out.print("\n The Pokedex's world has these evolutions: ");
        printEvolutions(evolutions);

    }

    public void pokemons(ArrayList<Pokemon> pokemons){
        System.out.print("\nThese are all the pokemons in the world of Pokedex: ");
        for(int i=0;i<pokemons.size();i++){
            printPokemon(pokemons.get(i));
        }
    }

    public void pokemon(Pokemon pokemon){
        printPokemon(pokemon);
    }

    private void printEvolutions(ArrayList<PokemonEvolution> evolutions){

        PokemonEvolution evolution;

        for(int i=0;i<evolutions.size();i++){

            evolution = evolutions.get(i);

            System.out.printf("\n Evolution's name: %s \t Level evolved to: %s", evolution.getName(),evolution.getLevelEvolved());
            System.out.print("\n Types of the evolution: ");
            printTypes(evolution.getTypes());
        }

        if(evolutions.size()==0){
            System.out.print("\nThere're no evolutions to show!. Feel free to add new evolutions to your Pokemon!");

        }


    }

    private void printTypes(ArrayList<PokemonType> types){
        for(int i=0; i<types.size();i++){
            System.out.print("\n\t"+ types.get(i));
        }

        if(types.size()==0){
            System.out.print("\nThere're no types to show!. Feel free to add new types to your Pokemon!");

        }
    }

    private void printPokemon(Pokemon pokemon){

        System.out.print("\nPokemon´s name: " + pokemon.getName() + "\t Pokemons's level: "+ pokemon.getLevel());
        System.out.print("\nThese are pokemon's types: ");
        printTypes(pokemon.getTypes());

    }


}

