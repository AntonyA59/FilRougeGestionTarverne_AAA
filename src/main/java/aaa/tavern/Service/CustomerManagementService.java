package aaa.tavern.Service;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.DAO.CustomerRepository;
import aaa.tavern.DAO.ManagerCustomerRepository;
import aaa.tavern.DAO.ManagerRepository;
import aaa.tavern.DAO.RecipeRepository;
import aaa.tavern.DAO.TableRestRepository;
import aaa.tavern.dto.CustomerDto;
import aaa.tavern.dto.RecipeDto;
import aaa.tavern.exception.ForbiddenException;
import aaa.tavern.Service.utils.RandomService;
import aaa.tavern.Entity.Customer;
import aaa.tavern.Entity.Manager;
import aaa.tavern.Entity.ManagerCustomer;
import aaa.tavern.Entity.Recipe;
import aaa.tavern.Entity.RecipeCustomer;
import aaa.tavern.Entity.TableRest;
import aaa.tavern.utils.ServiceUtil;
import net.bytebuddy.asm.Advice.Local;

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
     * @param managerId id of the manager to be provided with a random recipe
     * @return RecipeDto Object that contains the information of the chosen recipe
     * @throws EntityNotFoundException exception if the id manager is not in the database
     */

    @Transactional(rollbackOn = EntityNotFoundException.class) 
    public RecipeDto getNewRecipe(int managerId) throws EntityNotFoundException{

        Manager manager= ServiceUtil.getEntity(managerRepository, managerId);
        List<Recipe> listRecipe= recipeRepository.findByLevelLessThanEqual(manager.getLevel());
        Map<Integer,Recipe>mapRecipe = new HashMap<Integer,Recipe>();
        
        listRecipe.forEach(element->mapRecipe.put(element.getIdRecipe(), element));
        
        Object[] values= mapRecipe.values().toArray();
        int index= randomService.getRandomInt(values.length);
        Object randomObject= values[index];
        
        Recipe recipe=(Recipe)randomObject;

        return new RecipeDto(recipe);
    }

    /**
     * allows you to assign a customer to a place
     * @param customerId Id of the customer to whom we must assign a table
     * @param tableId table id to which a customer has been assigned
     * @throws EntityNotFoundException exception if the id table or id customer are not in the database
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
     * @throws EntityNotFoundException exception if the id manager is not in the database
     */
    @Transactional(rollbackOn = EntityNotFoundException.class) 
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


    @Transactional(rollbackOn = {EntityNotFoundException.class,ForbiddenException.class}) 
    public void customerFinishRecipe(int customerId, int managerId)throws EntityNotFoundException,ForbiddenException{
        
        Customer customer= ServiceUtil.getEntity(customerRepository, customerId);
        Manager manager= ServiceUtil.getEntity(managerRepository, managerId);
        
        for(RecipeCustomer recipeCustomer: customer.getCommandList()){
            checkRecipe(recipeCustomer.getRecipe(), manager, customer);
        }
    }



    private void checkRecipe(Recipe recipe, Manager manager,Customer customer) throws ForbiddenException{
        if(checkTime(recipe, customer)){
            //donner argent au manger
        }else{
            throw new ForbiddenException();
        }
    }




    private boolean checkTime(Recipe recipe, Customer customer){
        //test que le debut +time consommation et bien < time actuelle 
        
        Time timeConsommmation= recipe.getConsommationTime();
        LocalDateTime timeNow= LocalDateTime.now();
        // customer = début consommation (date+ heure)

        LocalDateTime localDebut= new LocalDateTime();
        Timestamp debutConsomation= new Timestamp(1);
        
        //recipe = temps de comsomation
        // il faut pouvoir debut consommation+ temps de consommation       compare       date et heure locale

        // date et heure locale


        return true;
    }
}
