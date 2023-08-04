package entidades;

import pokedexDatabase.ListGetters;

import java.util.ArrayList;
import java.util.LinkedList;

public class Pokemon {
    private String name;
    private ArrayList<PokemonType> types = new ArrayList<PokemonType>();
    private PokemonLevel actualLevel;
    private ArrayList<PokemonAbility> abilities = new ArrayList<PokemonAbility>();
    private ArrayList<PokemonEvolution> evolutions = new ArrayList<PokemonEvolution>();

    private ListGetters listGetter = new ListGetters();

    public Pokemon(String name, PokemonLevel actualLevel) {
        this.name = name;
        this.actualLevel = actualLevel;
    }

    public void setName(String newName){
        name=newName;
    }

    public String getName(){
        return name;
    }

    public PokemonLevel getLevel(){
        return actualLevel;
    }
    public void addType(PokemonType newType){
        types.add(newType);
    }

    public void removeType(PokemonType type){
        listGetter.removeTypeFromList(type, types);
    }

    public void addEvolution(PokemonEvolution newEvolution){
        evolutions.add(newEvolution);
    }

    public void addAbility(PokemonAbility newAbility){
        abilities.add(newAbility);
    }

    public boolean nameEqualsTo(String name){
        return this.name.toLowerCase().contentEquals(name.toLowerCase());
    }

    public ArrayList<PokemonAbility> getAbilities(){
        return abilities;
    }

    public ArrayList<PokemonEvolution> getEvolutions(){
        return evolutions;
    }

    public String pokemonToTxt(){
        return this.name + "," + this.actualLevel;
    }

    public ArrayList<String> pokemonXevolutionToTxt(){

        ArrayList<String> pokemonXevolution = new ArrayList<String>();

        for (PokemonEvolution evolution: evolutions
             ) {

            pokemonXevolution.add(this.name + "," + evolution.getName());

        }

        return pokemonXevolution;
    }

    public ArrayList<String> pokemonXabilityToTxt(){

        ArrayList<String> pokemonXability = new ArrayList<String>();

        for (PokemonAbility ability: abilities
        ) {

            pokemonXability.add(this.name + "," + ability.toString());

        }

        return pokemonXability;
    }


    public ArrayList<String> pokemonXtypeToTxt(){

        ArrayList<String> pokemonXtype = new ArrayList<String>();

        for (PokemonType type: types
        ) {

            pokemonXtype.add(this.name + "," + type.toString());

        }

        return pokemonXtype;
    }

    public ArrayList<PokemonType> getTypes(){
        return types;
    }
}
