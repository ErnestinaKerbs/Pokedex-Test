package entidades;

import pokedexDatabase.ListGetters;
import java.util.ArrayList;


public class Pokemon {
    private String name;
    private ArrayList<PokemonType> types = new ArrayList<>();
    private PokemonLevel actualLevel;
    private ArrayList<PokemonAbility> abilities = new ArrayList<>();
    private ArrayList<PokemonEvolution> evolutions = new ArrayList<>();

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

        ArrayList<String> pokemonXevolution = new ArrayList<>();

        for (PokemonEvolution evolution: evolutions
             ) {

            pokemonXevolution.add(this.name + "," + evolution.getName());

        }

        return pokemonXevolution;
    }

    public ArrayList<String> pokemonXabilityToTxt(){

        ArrayList<String> pokemonXability = new ArrayList<>();

        for (PokemonAbility ability: abilities
        ) {

            pokemonXability.add(this.name + "," + ability.toString());

        }

        return pokemonXability;
    }


    public ArrayList<String> pokemonXtypeToTxt(){

        ArrayList<String> pokemonXtype = new ArrayList<>();

        for (PokemonType type: types
        ) {

            pokemonXtype.add(this.name + "," + type.toString());

        }

        return pokemonXtype;
    }

    public ArrayList<PokemonType> getTypes(){
        return types;
    }

    public void setLevel(PokemonLevel newLevel) {
        this.actualLevel = newLevel;
    }
}
