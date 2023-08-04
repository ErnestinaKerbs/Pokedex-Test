import entidades.Pokemon;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PokedexUserExperienceTest {

  Pokedex world = new Pokedex("src/main/data");

    @Test
    public void userExperienceTest() throws Exception {

        world.seeEvolutionsAvailable();
        world.listOfPokemons();
        world.listAbilitiesOf("pokemon6");
        world.listEvolutionsOf("pokemon7");
        world.seeDataOf("pokemon9");



    }

}

