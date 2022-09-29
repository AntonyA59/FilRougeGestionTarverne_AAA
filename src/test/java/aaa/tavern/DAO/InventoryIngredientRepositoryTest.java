package aaa.tavern.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import aaa.tavern.Entity.InventoryIngredient;
import aaa.tavern.Entity.Manager;

@DataJpaTest
public class InventoryIngredientRepositoryTest {

    
    @Autowired
    InventoryIngredientRepository inventoryIngredientRepository;
    
    @Autowired
    ManagerRepository managerRepository;

    @Test
    @Sql("givenIngredientAndManager_findByManager_thenReturnInventoryIngredient.sql")
    public void givenIngredientAndManager_findByManager_thenReturnInventoryIngredient() {
        Manager manager = new Manager();
        Optional<Manager> managerOpt = managerRepository.findById(1);
        manager = managerOpt.get();
        List<InventoryIngredient> inventoryIngredients = inventoryIngredientRepository.findByManager(manager);

        assertEquals(inventoryIngredients.size(), 3);
    }
}
