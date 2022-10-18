package aaa.tavern.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.CustomerRepository;
import aaa.tavern.dao.IngredientRepository;
import aaa.tavern.dao.InventoryIngredientRepository;
import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dao.PlayerRepository;
import aaa.tavern.dto.CreateManagerDTO;
import aaa.tavern.dto.InventoryManagerIngredientDto;
import aaa.tavern.dto.ManagerDto;
import aaa.tavern.entity.Customer;
import aaa.tavern.entity.Ingredient;
import aaa.tavern.entity.InventoryIngredient;
import aaa.tavern.entity.Manager;
import aaa.tavern.entity.Player;
import aaa.tavern.exception.ForbiddenException;
import aaa.tavern.utils.ServiceUtil;

@Service
public class ManagerService {

    @Autowired
    private InventoryIngredientRepository inventoryIngredientRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public Manager createManager(CreateManagerDTO createManagerDto, int playerId) {
        Player player = ServiceUtil.getEntity(playerRepository, playerId);
        Manager manager = new Manager(createManagerDto.getName(), 0, 100, 1, 0, player);
        managerRepository.save(manager);
        return manager;
    }

    public void deleteManager(int idManager) {
        Manager manager = ServiceUtil.getEntity(managerRepository, idManager);
        managerRepository.delete(manager);
    }

    /**
     * Convert a Manager class instance into a ManagerDto class instance
     * 
     * @param manager
     * @return ManagerDto
     */
    public ManagerDto loadManagerDto(Manager manager) {
        return new ManagerDto(manager);
    }

    /**
     * Lists player's managers in the database and returns them in List of
     * managerDto
     * 
     * @param player
     * @return List<ManagerDto>
     */
    public List<ManagerDto> listExistingManagerDto(int idPlayer) {
        Player player = ServiceUtil.getEntity(playerRepository, idPlayer);
        List<Manager> managers = managerRepository.findByPlayer(player);
        if (managers.isEmpty()) {
            throw new EntityNotFoundException();
        }
        List<ManagerDto> managersDto = new ArrayList<ManagerDto>();
        for (Manager manager : managers) {
            ManagerDto managerDto = loadManagerDto(manager);
            managersDto.add(managerDto);
        }

        return managersDto;
    }

    @Transactional(rollbackOn = { EntityNotFoundException.class, ForbiddenException.class })
    public ManagerDto selectManager(int idManager) {
        Manager manager = ServiceUtil.getEntity(managerRepository, idManager);

        ManagerDto managerDto = new ManagerDto(manager);
        return managerDto;
    }

    public void giveExperienceManagerWithRecipe(int idManager, int idIngredient) {
        Manager manager = ServiceUtil.getEntity(managerRepository, idManager);
        Ingredient ingredient = ServiceUtil.getEntity(ingredientRepository, idIngredient);

        if (ingredient == null || manager == null)
            throw new EntityNotFoundException();

    }

    public void giveExperienceManagerWithCustomer(int idManager, int idCustomer) {
        Manager manager = ServiceUtil.getEntity(managerRepository, idManager);
        Customer customer = ServiceUtil.getEntity(customerRepository, idCustomer);

        if (customer == null || manager == null)
            throw new EntityNotFoundException();

    }

    /**
     * Loads manager's Inventory
     * 
     * @param idManager
     * @return List<InventoryManagerIngredientDto>
     */
    public List<InventoryManagerIngredientDto> loadInventoryIngredientsByManager(int idManager) {
        Manager manager = ServiceUtil.getEntity(managerRepository, idManager);

        List<InventoryIngredient> listInventoryIngredients = inventoryIngredientRepository.findByManager(manager);

        List<InventoryManagerIngredientDto> listInventoryManagerIngredientDto = new ArrayList<InventoryManagerIngredientDto>();

        for (InventoryIngredient inventoryIngredient : listInventoryIngredients) {
            Ingredient ingredient = inventoryIngredient.getIngredient();
            InventoryManagerIngredientDto inventoryManagerIngredientDto = new InventoryManagerIngredientDto(ingredient,
                    inventoryIngredient.getQuantity());
            listInventoryManagerIngredientDto.add(inventoryManagerIngredientDto);
        }

        return listInventoryManagerIngredientDto;
    }
}