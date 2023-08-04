import entidades.Pokemon;
import org.junit.Test;
import pokedexDatabase.PokedexDatabase;

import static org.junit.Assert.*;

public class PokedexDatabaseUpdateTest {

    PokedexDatabase database = new PokedexDatabase("src/test/databaseUpdateTestData");
    @Test
    public void updateWorldTest() throws Exception {
        database.loadWorld();
        try {

            database.getPokemon("pokecitoNuevo");

        } catch (Exception e){

            assertEquals("No se encontró pokemon", e.getMessage());
        }

        database.addNewPokemon("pokecitoNuevo");

        database.updateWorld();

        database.loadWorld();

        Pokemon pokemon = database.getPokemon("pokecitoNuevo");

        assertTrue(pokemon.nameEqualsTo("pokecitoNuevo"));

    }
}
