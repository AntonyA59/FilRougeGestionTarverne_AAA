package aaa.tavern.service;

import java.util.Map;
import java.util.Random;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.entity.Customer;
import aaa.tavern.entity.Ingredient;
import aaa.tavern.entity.TableRest;

@Service
public class CustomerManagementService {

    @Autowired
    Map<Integer,Ingredient> mapRecipes ; 

    @Autowired
    Map<Integer, TableRest> mapTables ;

    @Autowired
    Map<Integer, Customer> mapCustomer ;
    
    Ingredient generateOrder(){
        Random generator = new Random();
        Object[] values = mapRecipes.values().toArray();
        Object randomRecipe = values[generator.nextInt(values.length)];

        return (Ingredient) randomRecipe ;
    }

    void assignTable(int idCustomer, int idTableRest){
        Customer customer = mapCustomer.get(idCustomer) ;
        
        if (customer == null)
			throw new EntityNotFoundException();
        
        customer.setTableRest(mapTables.get(idTableRest));
    }

    void startEat(){
        
    }

    void endEat(){

    }
}
