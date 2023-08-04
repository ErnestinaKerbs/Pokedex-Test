import entidades.*;
import org.junit.Test;
import pokedexDatabase.PokedexDatabase;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PokedexDatabaseTest {
    PokedexDatabase database = new PokedexDatabase("src/test/databaseTestData");
    Pokemon pokemon;

    @Test
    public void testListarPokemons() {
        database.loadWorld();
        assertEquals(10, database.getPokemons().size());
    }
    @Test
    public void testListarEvolutions() {
        database.loadWorld();
        assertEquals(5, database.getEvolutions().size());
    }
    @Test
    public void changePokemonNameTest() throws Exception {
        database.loadWorld();
        database.changePokemonName("pokemon1", "pokecito");

        try {

            database.getPokemon("pokemon1");

        } catch (Exception e){

            assertEquals("No se encontró pokemon", e.getMessage());
        }

        assertEquals("pokecito",database.getPokemon("pokecito").getName());

    }

    @Test
    public void addPokemonTypeTest() throws Exception {
        database.loadWorld();

        database.addPokemonType(PokemonType.TYPE1, "pokemon9");

        ArrayList<PokemonType> pokemonTypes = new ArrayList<>();
        pokemonTypes.add(PokemonType.TYPE4);
        pokemonTypes.add(PokemonType.TYPE3);
        pokemonTypes.add(PokemonType.TYPE1);

        pokemon = database.getPokemon("pokemon9");
        int i = 0;
        for (PokemonType type : pokemon.getTypes()
        ) {
            assertEquals(pokemonTypes.get(i), type);
            i++;
        }
    }


    @Test
    public void removePokemonTypeTest() throws Exception {
        database.loadWorld();
        database.removePokemonType(PokemonType.TYPE3, "pokemon9");
        pokemon = database.getPokemon("pokemon9");
        assertEquals(1, pokemon.getTypes().size());
        assertEquals(PokemonType.TYPE4, pokemon.getTypes().get(0));

    }

    @Test
    public void addPokemonEvolutionTest() throws Exception {
        database.loadWorld();
        //le puedo agregar los types que quiera al evolution, una vez instanciado, ahora no le agrego nada
        database.addPokemonEvolution("evolution5","pokemon4");

        assertEquals(2,database.getPokemonEvolutions("pokemon4").size());
        assertEquals("evolution1",database.getPokemonEvolutions("pokemon4").get(0).getName());
        assertEquals("evolution5",database.getPokemonEvolutions("pokemon4").get(1).getName());
    }
    @Test
    public void addNewPokemonTest() throws Exception {
        database.loadWorld();
        database.addNewPokemon("pokecitoNuevo");
        assertEquals(11,database.getPokemons().size());
        assertEquals("pokecitoNuevo",database.getPokemon("pokecitoNuevo").getName());
    }
    @Test
    public void getPokemonAbilitiesTest() throws Exception {
        database.loadWorld();
        ArrayList<PokemonAbility> abilities2 = new ArrayList<>();
        abilities2.add(PokemonAbility.VELOCIDAD);
        abilities2.add(PokemonAbility.TELETRANSPORTARSE);
        ArrayList<PokemonAbility> abilities3 = new ArrayList<>();
        abilities3.add(PokemonAbility.VELOCIDAD);
        abilities3.add(PokemonAbility.TELETRANSPORTARSE);
        abilities3.add(PokemonAbility.FUERZA);
        int i=0;
        for (PokemonAbility ability: abilities2
             ) {
            assertEquals(ability,database.getPokemonAbilities("pokemon2").get(i));
            i++;
        }
        i=0;
        for (PokemonAbility ability: abilities3
        ) {
            assertEquals(ability,database.getPokemonAbilities("pokemon3").get(i));
            i++;
        }

    }
    @Test
    public void getPokemonEvolutionsTest() throws Exception {
        database.loadWorld();
        ArrayList<PokemonEvolution>  evolutions= database.getPokemonEvolutions("pokemon9");
        String[] nombres = evolutions.stream().map(PokemonEvolution::getName).toArray(String[]::new);
        assertArrayEquals(new String[]{"evolution1", "evolution2", "evolution5", "evolution4"},nombres);
    }

    @Test
    public void getPokemonTest() throws Exception {
        database.loadWorld();
        pokemon = database.getPokemon("pokemon8");
        assertEquals("pokemon8", pokemon.getName());
        assertEquals(PokemonLevel.LEVEL0,pokemon.getLevel());
    }

    @Test
    public void changePokemonLevelTest() throws Exception {

        database.loadWorld();

        pokemon = database.getPokemon("pokemon3");

        database.changeLevel(PokemonLevel.LEVEL2, "pokemon3");

        assertEquals(PokemonLevel.LEVEL2, pokemon.getLevel());

    }


    @Test
    public void updateWorldTest() throws Exception {
        database.loadWorld();
        try {

            database.getPokemon("pokecitoNuevo");

        } catch (Exception e){

            assertEquals("No se encontró pokemon", e.getMessage());

            database.addNewPokemon("pokecitoNuevo");

            database.addPokemonType(PokemonType.TYPE2, "pokecitoNuevo");

            database.addPokemonType(PokemonType.TYPE3, "pokecitoNuevo");

            database.addPokemonEvolution("evolution3","pokecitoNuevo");

            database.addPokemonEvolution("evolution5","pokecitoNuevo");

            database.updateWorld();

            database.loadWorld();

            Pokemon pokemon = database.getPokemon("pokecitoNuevo");

            assertTrue(pokemon.nameEqualsTo("pokecitoNuevo"));

        }
        removeLoadedPokemonTest(); // escribo los datos, y luego ejecuto la prueba para borrarlos acá
        //por única vez

    }

    public void removeLoadedPokemonTest() throws Exception {

        database.loadWorld();

        database.getPokemon("pokecitoNuevo");

        database.removePokemon("pokecitoNuevo");

        try {

            database.getPokemon("pokecitoNuevo");

        } catch (Exception e){

            assertEquals("No se encontró pokemon", e.getMessage());

        }

        database.updateWorld();
    }

}
