import entidades.*;
import org.junit.Test;
import pokedexDatabase.PokedexDatabase;

import java.lang.reflect.Array;
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

        ArrayList<PokemonType> pokemonTypes = new ArrayList<PokemonType>();
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
        database.addPokemonEvolution(new PokemonEvolution("evolution5", PokemonLevel.LEVEL4),"pokemon4");

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
        ArrayList<PokemonAbility> abilities2 = new ArrayList<PokemonAbility>();
        abilities2.add(PokemonAbility.VELOCIDAD);
        abilities2.add(PokemonAbility.TELETRANSPORTARSE);
        ArrayList<PokemonAbility> abilities3 = new ArrayList<PokemonAbility>();
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
        String[] nombres = evolutions.stream().map(evolution -> evolution.getName()).toArray(String[]::new);
        assertArrayEquals(new String[]{"evolution1", "evolution2", "evolution5", "evolution4"},nombres);
    }

    @Test
    public void getPokemonTest() throws Exception {
        database.loadWorld();
        pokemon = database.getPokemon("pokemon8");
        assertEquals("pokemon8", pokemon.getName());
        assertEquals(PokemonLevel.LEVEL0,pokemon.getLevel());
    }

}
