import entidades.Pokemon;
import entidades.PokemonType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PokedexUserExperienceTest {

  Pokedex world = new Pokedex("src/test/databaseTestData");

    @Test
    public void queriesTest() throws Exception {

        world.initializeWorld();

        world.seeEvolutionsAvailable();
        world.listOfPokemons();

        world.listAbilitiesOf("pokemon6");
        world.listEvolutionsOf("pokemon7");

    }

    @Test
    public void changePokemonNameTest() throws Exception {

        world.initializeWorld();

        world.seeDataOf("pokemon7");
        world.changePokemonName("pokemon7", "updatedPokeName");

        try{
            world.seeDataOf("pokemon7");
        }catch (Exception e){
            assertEquals("No se encontró pokemon", e.getMessage());
        }

        world.seeDataOf("updatedPokeName");

    }

    @Test
    public void addNewPokemonTest() throws Exception {

        world.initializeWorld();

        try{
            world.seeDataOf("newPokeName");
        }catch (Exception e){
            assertEquals("No se encontró pokemon", e.getMessage());
        }

        world.addNewPokemon("newPokeName");

        world.seeDataOf("newPokeName");

    }

    @Test
    public void addNewTypeTest() throws Exception{

        world.initializeWorld();

        world.addNewPokemon("newPokeName");
        world.seeDataOf("newPokeName");

        world.addPokemonType(PokemonType.TYPE3, "newPokeName");
        world.addPokemonType(PokemonType.TYPE4, "newPokeName");
        world.seeDataOf("newPokeName");

    }

    @Test
    public void removePokemonTypeTest() throws Exception{

        world.initializeWorld();

        world.seeDataOf("pokemon1");
        world.removePokemonType(PokemonType.TYPE1, "pokemon1");
        world.seeDataOf("pokemon1");

    }

    @Test
    public void addNewEvolutionTest() throws Exception{

        world.initializeWorld();

        world.addNewPokemon("newPokeName");
        world.listEvolutionsOf("newPokeName");
        world.addPokemonEvolution("evolution4","newPokeName");
        world.addPokemonEvolution("evolution2","newPokeName");
        world.listEvolutionsOf("newPokeName");

    }
    @Test
    public void uploadingChangesTest() throws Exception {

        world.initializeWorld();

        try{
            world.seeDataOf("newPokeName");
        }catch (Exception e){
            assertEquals("No se encontró pokemon", e.getMessage());
            world.addNewPokemon("newPokeName");
            world.listEvolutionsOf("newPokeName");
            world.addPokemonEvolution("evolution4","newPokeName");
            world.addPokemonEvolution("evolution2","newPokeName");
            world.closeWorld();
        }

        world.initializeWorld();
        world.seeDataOf("newPokeName");
        world.listEvolutionsOf("newPokeName");

        world.removePokemon("newPokeName");
        world.closeWorld();

        world.initializeWorld();

        try{
            world.seeDataOf("newPokeName");
        }catch (Exception e){
            assertEquals("No se encontró pokemon", e.getMessage());
            world.closeWorld();
        }


    }

}

