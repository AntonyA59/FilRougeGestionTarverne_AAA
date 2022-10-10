package aaa.tavern.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class RecipeCustomerRepositoryTest {
    @Autowired
	private RecipeCustomerRepository recipeCustomerRepository;
    //findByCustomer
}
