package aaa.tavern.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import aaa.tavern.entity.Ingredient;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class IngredientRepositoryTest {

	@Autowired
	IngredientRepository ingredientRepository;

	@Test
	@Sql("giventIngredients_findByLessThanEqualsLevel_ThenReturnIngredients.sql")
	public void giventIngredients_findByLessThanEqualsLevel_ThenReturnIngredients() {
		List<Ingredient> ingredients = ingredientRepository.findByLevelLessThanEqual(4);

		assertEquals(ingredients.size(), 4);
	}
}
