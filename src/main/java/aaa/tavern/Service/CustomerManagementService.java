package aaa.tavern.Service;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.DAO.CustomerRepository;
import aaa.tavern.DAO.ManagerCustomerRepository;
import aaa.tavern.DAO.ManagerRepository;
import aaa.tavern.DAO.TableRestRepository;
import aaa.tavern.dto.CustomerDto;
import aaa.tavern.dto.RecipeDto;
import aaa.tavern.Service.utils.RandomService;
import aaa.tavern.Entity.Customer;
import aaa.tavern.Entity.Manager;
import aaa.tavern.Entity.ManagerCustomer;
import aaa.tavern.Entity.Recipe;
import aaa.tavern.Entity.RecipeCustomer;
import aaa.tavern.Entity.TableRest;
import aaa.tavern.Service.utils.ListRecipe;
import aaa.tavern.utils.ServiceUtil;

@Service
public class CustomerManagementService {
    @Autowired
    private RandomService randomService;
    
    @Autowired
    private ListRecipe listRecipe;

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private TableRestRepository tableRestRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private ManagerCustomerRepository managerCustomerRepository;

    /**
     * allows to send a random recipe which is in listRecipe in the utils folder
     * @return RecipeDto Object that contains the information of the chosen recipe
     */
    public RecipeDto getNewRecipe(){
        Object[] values= listRecipe.getListRecipe().values().toArray();
        int index= randomService.getRandomInt(values.length);
        Object randomObject= values[index];
        Recipe recipe=(Recipe)randomObject;

        return new RecipeDto(recipe);
    }

    /**
     * allows you to assign a customer to a place
     * @param customerId Id of the customer to whom we must assign a table
     * @param tableId table id to which a customer has been assigned
     * @throws EntityNotFoundException 
     */
    @Transactional(rollbackOn = EntityNotFoundException.class) 
    public void assignNewTable(int customerId,int tableId) throws EntityNotFoundException{
        Customer customer= ServiceUtil.getEntity(customerRepository, customerId);
        TableRest tableRest= ServiceUtil.getEntity(tableRestRepository, tableId);
        
        tableRest.setNumberPlace(tableRest.getNumberPlace()-1);
        customer.setTableRest(tableRest);

        tableRestRepository.save(tableRest);
        customerRepository.save(customer);
    }


    /**
     * allows you to create a new customer randomly
     * @param managerId id of the manager to whom we create a new customer
     * @return CustomerDto Object that contains the information of the new customer
     * @throws EntityNotFoundException exception if the id maager is not in the database
     */
    @Transactional
    public CustomerDto getNewCustomer(int managerId) throws EntityNotFoundException{
        Manager manager= ServiceUtil.getEntity(managerRepository, managerId);
        
        //init newCustomer 
        int purseOfGold = randomService.getRandomInt(100);
        float happiness = randomService.getRandomFloat(100f);
        float hunger = randomService.getRandomFloat(100f);
        float thirst = randomService.getRandomFloat(100f);
        float nauseaLevel = randomService.getRandomFloat(100f);
        float alcoholLevel = randomService.getRandomFloat(100f);
        float toilet = randomService.getRandomFloat(100f);
        Time timeInTavern= new Time(randomService.getRandomIntMinMax(500, 5000));
        float nauseaTolerance = randomService.getRandomFloat(100f);
        float alcoholTolerance = randomService.getRandomFloat(100f);
        boolean gender = randomService.getRandomBoolean();
        int expGiven = 0;
        TableRest tableRest = new TableRest();
        Set<RecipeCustomer> commandList= new HashSet<RecipeCustomer>();
        Customer newCustomer= new Customer(purseOfGold,happiness,hunger,thirst,nauseaLevel,alcoholLevel,
                                            toilet,timeInTavern,nauseaTolerance,alcoholTolerance,gender,
                                            expGiven,tableRest,commandList);
        customerRepository.save(newCustomer);

        ManagerCustomer managerCustomer= new ManagerCustomer(manager,newCustomer);
        
        managerCustomerRepository.save(managerCustomer);

        return new CustomerDto(newCustomer);
    }
}
