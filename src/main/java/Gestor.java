import entidades.PokemonEvolution;
import entidades.PokemonLevel;
import entidades.PokemonType;
import pokedexDatabase.PokedexDatabase;
import userInterface.PokedexTerminalInterface;

import java.util.Arrays;
import java.util.Scanner;

public class Gestor {

    private PokedexTerminalInterface pokemonInterface = new PokedexTerminalInterface();
    private PokedexDatabase database;

    private Scanner scanner = new Scanner(System.in);

    public void start(){

        database.loadWorld();

        System.out.print("\nWelcome to Pokedex! \n\nInsert command or type \"man\" to visualize the commands available.\n");
        String userInput = "default";

        while (!userInput.equalsIgnoreCase("close")) {

            System.out.print("\n\nmenu> Insert a command\n");
            userInput =  scanner.nextLine();

            switch (userInput.toLowerCase()){

                case "man":{
                    this.listManual();
                    break;
                }
                case "listpokemons":{
                    this.listOfPokemons();
                    break;

                }
                case "listevolutions":{
                    this.seeEvolutionsAvailable();
                    break;
                }
                case "seepokemondata":{
                    this.seePokemonData();
                    break;
                }
                case "listabilitiesof":{
                    this.listAbilitiesOf();
                    break;
                }
                case "listevolutionsof":{
                    this.listEvolutionsOf();
                    break;
                }
                case "changepokemonname":{
                    this.changePokemonName();
                    break;
                }
                case "addpokemontype":{
                    this.addPokemonType();
                    break;
                }
                case "removepokemontype":{
                    this.removePokemonType();
                    break;
                }
                case "addpokemonevolution":{
                    this.addPokemonEvolution();
                    break;
                }
                case "changepokemonlevel":{
                    this.changePokemonLevel();
                    break;
                }
                case "removepokemon":{
                    this.removePokemon();
                    break;
                }
                case "addnewpokemon":{
                    this.addNewPokemon();
                    break;
                }
                case "close":{
                    //nada porque sale del bucle
                    break;
                }

                default:
                    System.out.print("\nUnexpected value! Insert a valid command or type \"man\" to check them.");
                    //throw new IllegalStateException("Unexpected value: " + userInput.toLowerCase());
            }

        }

        System.out.print("\nThank you for playing with Pokedex! Come back soon!!");
        database.updateWorld();

    }

    private void listManual() {

        String[] comands = {"command", "listPokemons", "listEvolutions","seePokemonData","listAbilitiesOf",
        "listEvolutionsOf","changePokemonName","addPokemonType","removePokemonType","addPokemonEvolution",
                "changePokemonLevel","removePokemon","addNewPokemon","close"};

        String[] descriptions= {"description", "shows all pokemons in Pokedex", "shows all evolutions in Pokedex",
                "shows a pokemon's name, level & types","shows a pokemon's list of abilities",
                "shows a pokemon's list of evolutions","allows to change an existing pokemon's name",
                "adds an existing type to an existing pokemon","removes an existing type from an existing pokemon",
                "adds an existing evolution to an existing pokemon",
                "allows to change an existing pokemon's level to other existing one",
                "removes an existing pokemon from the database","creates a new pokemon and adds it to the database",
                "closes Pokedex"};


        for(int i =0; i<comands.length; i++){

            System.out.print("\n"+comands[i] +"\t --- \t"+descriptions[i]);

        }


    }

    public Gestor(String databasePath){
        database = new PokedexDatabase(databasePath);
    }

    private void listAbilitiesOf() {

        System.out.print("\nlistAbilitiesOf>Insert pokemon's name:\n");
        String pokemonName = scanner.nextLine();

        try{
            pokemonInterface.abilities(pokemonName,database.getPokemonAbilities(pokemonName));

        }catch (Exception e){
            System.out.print("\nexitingCommand>The pokemon's name provided doesn't exist, please run the command again and insert a valid one.\n");

        }
    }

    private void listEvolutionsOf(){
        System.out.print("\nlistEvolutionsOf>Insert pokemon's name:\n");
        String pokemonName = scanner.nextLine();

        try{
            pokemonInterface.evolutions(pokemonName, database.getPokemonEvolutions(pokemonName));

        }catch (Exception e){
            System.out.print("\nexitingCommand>The pokemon's name provided doesn't exist, please run the command again and insert a valid one.\n");

        }
    }

    private void seePokemonData() {
        System.out.print("\nseePokemonData>Insert pokemon's name:\n");

        try{
            pokemonInterface.pokemon(database.getPokemon(scanner.nextLine()));

        }catch (Exception e){
            System.out.print("\nexitingCommand>The pokemon's name provided doesn't exist, please run the command again and insert a valid one.\n");

        }

    }

    private void seeEvolutionsAvailable(){
        pokemonInterface.evolutions(database.getEvolutions());

    }

    private void listOfPokemons(){
        pokemonInterface.pokemons(database.getPokemons());
    }


    private void changePokemonName(){

        System.out.print("\nchangePokemonName>Insert pokemon's actual name:\n");
        String actualName = scanner.nextLine();
        System.out.print("\nchangePokemonName>Insert pokemon's new name:\n");
        String newName = scanner.nextLine();

        try{
            database.changePokemonName(actualName,newName);

        }catch (Exception e){
            System.out.print("\nexitingCommand>The pokemon's actual name provided doesn't exist, please run the command again and insert a valid one.\n");

        }


    }

    private void addPokemonType() {

        System.out.print("\naddPokemonType>Insert pokemon's name:\n");
        String pokemonName = scanner.nextLine();

        try{
            System.out.print("\naddPokemonType>Insert pokemon's new type:\n");
            PokemonType newType = PokemonType.valueOf(scanner.nextLine());
            database.addPokemonType(newType, pokemonName);

        }catch (Exception e){
            System.out.print("\nexitingCommand>Either the pokemon's name provided or type doesn't exist, please run the command again and insert a valid one.\n");
        }

    }

    private void addNewPokemon(){

        System.out.print("\naddNewPokemon>Insert pokemon's name:\n");
        String pokemonName = scanner.nextLine();

        try{
            //considero que los pokemons encontrados se general con LEVEL0 y habilidades default
            database.addNewPokemon(pokemonName);

        }catch (Exception e){
            System.out.print("\nexitingCommand>The pokemon's name provided doesn't exist, please run the command again and insert a valid one.\n");

        }

    }


    private void removePokemonType() {

        System.out.print("\nremovePokemonType>Insert pokemon's name:\n");
        String pokemonName = scanner.nextLine();


        try {
            System.out.print("\nremovePokemonType>Insert pokemon's type to remove:\n");
            PokemonType toRemove = PokemonType.valueOf(scanner.nextLine());
            database.removePokemonType(toRemove, pokemonName);

        } catch (Exception e) {
            System.out.print("\nexitingCommand>Either the pokemon's name or type provided doesn't exist, please run the command again and insert a valid one.\n");


        }
    }

    private void addPokemonEvolution() {

        System.out.print("\naddPokemonEvolution>Insert pokemon's name:\n");
        String pokemonName = scanner.nextLine();
        System.out.print("\naddPokemonEvolution>Insert pokemon's new evolution:\n");
        String newEvolution = scanner.nextLine();

        try{
            database.addPokemonEvolution(newEvolution,pokemonName);

        }catch (Exception e){
            System.out.print("\nexitingCommand>Either the pokemon's name or evolution provided doesn't exist, please run the command again and insert a valid one.\n");

        }



    }

    private void removePokemon(){

        System.out.print("\nremovePokemon>Insert pokemon's name:\n");
        String pokemonToRemove = scanner.nextLine();

        try{
            database.removePokemon(pokemonToRemove);

        }catch (Exception e){
            System.out.print("\nexitingCommand>The pokemon's name provided doesn't exist, please run the command again and insert a valid one.\n");

        }

    }

    private void changePokemonLevel() {
        System.out.print("\nchangePokemonLevel>Insert pokemon's name:\n");
        String pokemonName = scanner.nextLine();

        try {
            System.out.print("\nchangePokemonLevel>Insert new pokemon's level:\n");
            PokemonLevel newLevel = PokemonLevel.valueOf(scanner.nextLine());
            database.changeLevel(newLevel, pokemonName);

        } catch (Exception e) {
            System.out.print("\nexitingCommand>Either the pokemon's name or level provided doesn't exist, please run the command again and insert a valid one.\n");


        }

    }
}
