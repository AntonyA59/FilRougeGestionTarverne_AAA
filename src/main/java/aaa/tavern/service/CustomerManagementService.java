package aaa.tavern.service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.CustomerRepository;
import aaa.tavern.dao.ManagerCustomerRepository;
import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dao.RecipeRepository;
import aaa.tavern.dao.TableRestRepository;
import aaa.tavern.dto.CustomerDto;
import aaa.tavern.dto.CustomerTableRestDto;
import aaa.tavern.dto.ManagerDto;
import aaa.tavern.dto.RecipeDto;
import aaa.tavern.entity.Customer;
import aaa.tavern.entity.Manager;
import aaa.tavern.entity.ManagerCustomer;
import aaa.tavern.entity.Recipe;
import aaa.tavern.entity.RecipeCustomer;
import aaa.tavern.entity.TableRest;
import aaa.tavern.exception.ForbiddenException;
import aaa.tavern.service.utils.RandomService;
import aaa.tavern.utils.ServiceUtil;

@Service
public class CustomerManagementService {
    @Autowired
    private RandomService randomService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TableRestRepository tableRestRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private ManagerCustomerRepository managerCustomerRepository;

    @Autowired
    private RecipeRepository recipeRepository;


    /**
     * allows to send a random recipe which is in listRecipe in the utils folder
     * 
     * @param managerId id of the manager to be provided with a random recipe
     * @return RecipeDto Object that contains the information of the chosen recipe
     * @throws EntityNotFoundException exception if the id manager is not in the
     *                                 database
     */

    @Transactional(rollbackOn = EntityNotFoundException.class)
    public RecipeDto getNewRecipe(int managerId) throws EntityNotFoundException {

        Manager manager = ServiceUtil.getEntity(managerRepository, managerId);
        List<Recipe> listRecipe = recipeRepository.findByLevelLessThanEqual(manager.getLevel());
        Map<Integer, Recipe> mapRecipe = new HashMap<Integer, Recipe>();

        listRecipe.forEach(element -> mapRecipe.put(element.getId(), element));

        Object[] values = mapRecipe.values().toArray();
        int index = randomService.getRandomInt(values.length);
        Object randomObject = values[index];

        Recipe recipe = (Recipe) randomObject;

        return new RecipeDto(recipe);
    }

    /**
     * allows you to assign a customer to a place
     * 
     * @param customerId Id of the customer to whom we must assign a table
     * @param tableId    table id to which a customer has been assigned
     * @return CustomerTableRestDto with customer and tableRest Update
     * @throws EntityNotFoundException exception if the id table or id customer are
     *                                 not in the database
     */
    @Transactional(rollbackOn = { EntityNotFoundException.class, ForbiddenException.class })
    public CustomerTableRestDto assignNewTable(int customerId, int tableId)
            throws EntityNotFoundException, ForbiddenException {
        Customer customer = ServiceUtil.getEntity(customerRepository, customerId);
        TableRest tableRest = ServiceUtil.getEntity(tableRestRepository, tableId);
        if (tableRest.getNumberPlace() - 1 >= 0) {
            tableRest.setNumberPlace(tableRest.getNumberPlace() - 1);
            customer.setTableRest(tableRest);

            tableRestRepository.save(tableRest);
            customerRepository.save(customer);
            return new CustomerTableRestDto(customer, tableRest);

        } else
            throw new ForbiddenException();
    }

    /**
     * allows you to create a new customer randomly
     * 
     * @param managerId id of the manager to whom we create a new customer
     * @return CustomerDto Object that contains the information of the new customer
     * @throws EntityNotFoundException exception if the id manager is not in the
     *                                 database
     */
    @Transactional(rollbackOn = EntityNotFoundException.class)
    public CustomerDto getNewCustomer(int managerId) throws EntityNotFoundException {
        Manager manager = ServiceUtil.getEntity(managerRepository, managerId);
        TableRest tableRest= ServiceUtil.getEntity(tableRestRepository, 1);
        // init newCustomer
        int purseOfGold = randomService.getRandomInt(100);
        float happiness = randomService.getRandomFloat(100f);
        float hunger = randomService.getRandomFloat(100f);
        float thirst = randomService.getRandomFloat(100f);
        float nauseaLevel = randomService.getRandomFloat(100f);
        float alcoholLevel = randomService.getRandomFloat(100f);
        float toilet = randomService.getRandomFloat(100f);
        Time timeInTavern = new Time(randomService.getRandomIntMinMax(500, 5000));
        float nauseaTolerance = randomService.getRandomFloat(100f);
        float alcoholTolerance = randomService.getRandomFloat(100f);
        boolean gender = randomService.getRandomBoolean();
        int expGiven = 0;
        HashSet<RecipeCustomer> commandList = new HashSet<RecipeCustomer>();
        Timestamp consommationStart = null;
        Customer newCustomer = new Customer(purseOfGold, happiness, hunger, thirst, nauseaLevel, alcoholLevel,
                toilet, timeInTavern, nauseaTolerance, alcoholTolerance, gender,
                expGiven, tableRest, commandList, consommationStart);
        customerRepository.save(newCustomer);

        ManagerCustomer managerCustomer = new ManagerCustomer(manager, newCustomer);

        managerCustomerRepository.save(managerCustomer);

        return new CustomerDto(newCustomer);
    }

    /**
     * Method high level to serve the recipe to the customer
     * 
     * @param customerId id customer to whom the recipe is served
     * @retrun CustomerDto whith customer update
     * @throws EntityNotFoundException exception if the id customer is not in the
     *                                 database
     */
    @Transactional(rollbackOn = EntityNotFoundException.class)
    public CustomerDto customerServed(int customerId) throws EntityNotFoundException {

        Customer customer = ServiceUtil.getEntity(customerRepository, customerId);
        Timestamp timeNow = new Timestamp(System.currentTimeMillis());
        customer.setConsommationStart(timeNow);
        customerRepository.save(customer);
        return new CustomerDto(customer);

    }

    /**
     * Method high level which indicates that the customer has finished eating
     * 
     * @param customerId id customer who has finished eating
     * @param managerId  id manager who should be given the money
     * @return ManagerDto with manager update
     * @throws EntityNotFoundException exception if the id customer or manager is
     *                                 not in the database
     * @throws ForbiddenException      exception if the consumption time is not good
     */
    @Transactional(rollbackOn = { EntityNotFoundException.class, ForbiddenException.class })
    public ManagerDto customerFinishRecipe(int customerId, int managerId)
            throws EntityNotFoundException, ForbiddenException {

        Customer customer = ServiceUtil.getEntity(customerRepository, customerId);
        Manager manager = ServiceUtil.getEntity(managerRepository, managerId);

        for (RecipeCustomer recipeCustomer : customer.getCommandList()) {
            checkRecipe(recipeCustomer.getRecipe(), manager, customer);
        }
        customerRepository.delete(customer);
        managerRepository.save(manager);

        return new ManagerDto(manager);
    }

    /**
     * receive a recipe and check it only if it is well finished, if so we give the
     * money and the exp to the customer
     * 
     * @param recipe   recipe that has been consumed
     * @param manager  manager who earns money and expands it
     * @param customer customer who has finished eating
     * @throws ForbiddenException execption rise if the recipe is not finished
     *                            consuming
     */
    private void checkRecipe(Recipe recipe, Manager manager, Customer customer) throws ForbiddenException {
        if (checkTime(recipe, customer)) {
            Integer goldWin = recipe.getSellingPrice();
            Integer goldManager = manager.getChest();
            manager.setChest(goldManager + goldWin);
            manager.setExperience(manager.getExperience() + recipe.getExpGiven());

        } else
            throw new ForbiddenException();
    }

    /**
     * methods that return a boolean depending on the end of consumption
     * 
     * @param recipe   recipe we want to check
     * @param customer customer from whom consumption is recovered start
     * @return Y/N according to the end of the consumption of the recipe
     */
    private boolean checkTime(Recipe recipe, Customer customer) {

        Timestamp startConsommation = customer.getConsommationStart();
        Long timeConsomation = recipe.getConsommationTime();

        Long totalMilli = startConsommation.getTime() + timeConsomation;
        Timestamp totalTimestamp = new Timestamp(totalMilli);

        Timestamp timeNow = new Timestamp(System.currentTimeMillis());

        return timeNow.after(totalTimestamp) ? true : false;

    }
}
