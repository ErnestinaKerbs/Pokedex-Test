import entidades.PokemonType;
import pokedexDatabase.PokedexDatabase;
import userInterface.PokedexTerminalInterface;

public class Pokedex {

    private PokedexTerminalInterface pokemonInterface = new PokedexTerminalInterface();
    private String databasePath;
    private PokedexDatabase database;

    public Pokedex(String databasePath){
        this.databasePath = databasePath;
        database = new PokedexDatabase(databasePath);
        initializeWorld();
    }

    public  void initializeWorld(){
        database.loadWorld();
    }
    public void closeWorld(){
        database.updateWorld();
    }
    public void listAbilitiesOf(String pokemonName) throws Exception {

        pokemonInterface.abilities(pokemonName,database.getPokemonAbilities(pokemonName));
    }

    public void listEvolutionsOf(String pokemonName) throws Exception {
        pokemonInterface.evolutions(pokemonName, database.getPokemonEvolutions(pokemonName));
    }

    public void seeDataOf(String pokemonName) throws Exception {

        pokemonInterface.pokemon(database.getPokemon(pokemonName));

    }

    public void seeEvolutionsAvailable(){
        pokemonInterface.evolutions(database.getEvolutions());

    }

    public void listOfPokemons(){
        pokemonInterface.pokemons(database.getPokemons());
    }


    public void changePokemonName(String actualName, String newName) throws Exception {

        database.changePokemonName(actualName,newName);

    }

    public void addPokemonType(PokemonType newType, String pokemonName) throws Exception {

       database.addPokemonType(newType, pokemonName);

    }

    public void addNewPokemon(String name){
        //considero que los pokemons encontrados se general con LEVEL0 y abilidades default
        database.addNewPokemon(name);

    }


    public void removePokemonType(PokemonType pokemonType, String pokemonName) throws Exception {
        database.removePokemonType(pokemonType,pokemonName);
    }

    public void addPokemonEvolution(String newEvolution, String pokemonName) throws Exception {
        database.addPokemonEvolution(newEvolution,pokemonName);
    }

    public void removePokemon(String pokemonToRemove) throws Exception {
        database.removePokemon(pokemonToRemove);
    }
}
