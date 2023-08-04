package pokedexDatabase;

import entidades.*;
import pokedexDatabase.fileHandlers.*;

import java.io.*;
import java.util.ArrayList;
public class PokedexDatabase {

    private ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
    private ArrayList<PokemonEvolution> evolutions = new ArrayList<PokemonEvolution>();

    private ListGetters listGetter = new ListGetters();

    private String dataPath;
    public PokedexDatabase(String dataPath){

        this.dataPath = dataPath;

    }


    public ArrayList<Pokemon> getPokemons(){
        return pokemons;
    }

    public ArrayList<PokemonEvolution> getEvolutions(){
        return evolutions;
    }

    public void changePokemonName(String actualName, String newName) throws Exception {
        Pokemon pokemon = listGetter.getPokemonBy(actualName,pokemons);
        pokemon.setName(newName);
    }


    public void loadWorld(){
        pokemons =  loadFileData(dataPath+"/pokemons.txt",new PokemonData());
        evolutions = loadFileData(dataPath+"/evolutions.txt", new EvolutionsData());
        evolutions = loadFileData(dataPath+"/evolutionXtype.txt",new EvolutionXTypeData(evolutions));
        pokemons = loadFileData(dataPath+"/pokemonXevolution.txt",new PokemonXEvolutionData(pokemons,evolutions));
        pokemons = loadFileData(dataPath+"/pokemonsXabilities.txt",new PokemonXAbilityData(pokemons));
        pokemons = loadFileData(dataPath+"/pokemonXtype.txt",new PokemonXTypeData(pokemons));
    }

    public void updateWorld(){

         uploadFileData(dataPath+"/pokemons.txt",new PokemonData(pokemons));
         //uploadFileData(dataPath+"/evolutions.txt", new EvolutionsData(evolutions)); no se actualizan xq no se agregan nuevas
         //uploadFileData(dataPath+"/evolutionXtype.txt",new EvolutionXTypeData(evolutions));
         uploadFileData(dataPath+"/pokemonXevolution.txt",new PokemonXEvolutionData(pokemons,evolutions));
         uploadFileData(dataPath+"/pokemonsXabilities.txt",new PokemonXAbilityData(pokemons));
         uploadFileData(dataPath+"/pokemonXtype.txt",new PokemonXTypeData(pokemons));

    }


    private ArrayList loadFileData(String fileName, FileType fileType){

        ArrayList array = null;
        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {
            array = fileType.readData(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return array;
    }

    private void uploadFileData(String fileName, FileType fileType){

        //se abre el archivo en modo escritura y automaticamente se eliminan todos sus datos
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(fileName))) {
            fileType.writeData(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void addPokemonType(PokemonType newType, String pokemonName) throws Exception {
        Pokemon pokemon = listGetter.getPokemonBy(pokemonName,pokemons);
        pokemon.addType(newType);
    }

    public void removePokemonType(PokemonType typeToRemove, String pokemonName) throws Exception {
        Pokemon pokemon = listGetter.getPokemonBy(pokemonName, pokemons);
        pokemon.removeType(typeToRemove);
    }

    public void addPokemonEvolution(String evolutionName, String pokemonName) throws Exception {
        PokemonEvolution newEvolution = listGetter.getPokemonEvolutionBy(evolutionName,evolutions);
        Pokemon pokemon = listGetter.getPokemonBy(pokemonName,pokemons);
        pokemon.addEvolution(newEvolution);
    }

    public void addNewPokemon(String name){
        //defaulLevel & defaultAbility
        Pokemon pokemon = new Pokemon(name, PokemonLevel.LEVEL0);
        pokemon.addAbility(PokemonAbility.FUERZA);
        pokemons.add(pokemon);


    }

    public ArrayList<PokemonAbility> getPokemonAbilities(String pokemonName) throws Exception {

        return listGetter.getPokemonBy(pokemonName, pokemons).getAbilities();
    }

    public ArrayList<PokemonEvolution> getPokemonEvolutions(String pokemonName) throws Exception {

        return listGetter.getPokemonBy(pokemonName, pokemons).getEvolutions();
    }

    public Pokemon getPokemon(String pokemonName) throws Exception {
        return listGetter.getPokemonBy(pokemonName,pokemons);
    }

    public void removePokemon(String pokemonName) throws Exception {
        Pokemon pokemon = listGetter.getPokemonBy(pokemonName, pokemons);
        listGetter.removePokemonFromList(pokemon,pokemons);
    }
}
