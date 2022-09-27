package aaa.tavern.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.CustomerRepository;
import aaa.tavern.dao.ManagerCustomerRepository;
import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dao.TableRestRepository;
import aaa.tavern.dto.NewCustomerRandomDto;
import aaa.tavern.dto.NewRecipeDto;
import aaa.tavern.entity.Customer;
import aaa.tavern.entity.Manager;
import aaa.tavern.entity.ManagerCustomer;
import aaa.tavern.entity.Recipe;
import aaa.tavern.entity.TableRest;
import aaa.tavern.service.utils.ListRecipe;
import aaa.tavern.utils.ServiceUtil;

@Service
public class CustomerManagementService {
    @Autowired
    private ListRecipe listRecipe;
    
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

    public NewRecipeDto getNewRecipe(){
        Object[] values= listRecipe.getListRecipe().values().toArray();
        int index= randomService.getRandomInt(values.length);
        Object randomObject= values[index];
        Recipe recipe=(Recipe)randomObject;

        return new NewRecipeDto(recipe);
    }

    @Transactional(rollbackOn = EntityNotFoundException.class) 
    public void assignNewTable(int customerId,int tableId) throws EntityNotFoundException{
        Customer customer= ServiceUtil.getEntity(customerRepository, customerId);
        TableRest tableRest= ServiceUtil.getEntity(tableRestRepository, tableId);
        
        tableRest.setNumberPlace(tableRest.getNumberPlace()-1);
        customer.setTableRest(tableRest);

        tableRestRepository.save(tableRest);
        customerRepository.save(customer);
    }

    @Transactional
    public NewCustomerRandomDto getNewCustomer(int managerId) throws EntityNotFoundException{
        Manager manager= ServiceUtil.getEntity(managerRepository, managerId);
        Customer newCustomer= new Customer();
        customerRepository.save(newCustomer);
        ManagerCustomer managerCustomer= new ManagerCustomer(manager,newCustomer);
        managerCustomerRepository.save(managerCustomer);

        return new NewCustomerRandomDto(newCustomer);
    }
}
