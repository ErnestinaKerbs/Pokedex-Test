package entidades;

import java.util.ArrayList;

public class PokemonEvolution {
    private String evolutionName;

    private ArrayList<PokemonType> types = new ArrayList<PokemonType>();

    private PokemonLevel levelEvolved;

    public PokemonEvolution(String evolutionName, PokemonLevel levelEvolved){
        this.evolutionName=evolutionName;
        this.levelEvolved=levelEvolved;
    }


    public boolean nameEqualsTo(String name){
        return this.evolutionName.toLowerCase().contentEquals(name.toLowerCase());
    }


    public void addType(PokemonType newType){
        types.add(newType);
    }

    public String getName(){
        return evolutionName;
    }

    public PokemonLevel getLevelEvolved(){
        return levelEvolved;
    }

    public ArrayList<PokemonType> getTypes(){
        return types;
    }


}
