package aaa.tavern.service;

import java.util.Random;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.CustomerRepository;
import aaa.tavern.dao.TableRestRepository;
import aaa.tavern.dto.NewRecipeDto;
import aaa.tavern.entity.Customer;
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

    public NewRecipeDto getNewRecipe(){
        Object[] values= listRecipe.getListRecipe().values().toArray();
        int index= randomService.getRandom(values.length);
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
}
