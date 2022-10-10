package aaa.tavern.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.CustomerRepository;
import aaa.tavern.dao.RecipeCustomerRepository;
import aaa.tavern.dto.RecipeDto;
import aaa.tavern.entity.Customer;
import aaa.tavern.entity.RecipeCustomer;
import aaa.tavern.utils.ServiceUtil;

@Service
public class RecipeCustomerService {

    @Autowired
    RecipeCustomerRepository recipeCustomerRepository;

    @Autowired
    CustomerRepository customerRepository;

    /**
     * Return customer's recipesDto
     * 
     * @param customerId
     * @return List<RecipeDto>
     */
    public List<RecipeDto> loadRecipeByCustomer(int customerId) {
        Customer customer = ServiceUtil.getEntity(customerRepository, customerId);

        List<RecipeCustomer> listRecipeCustomer = recipeCustomerRepository.findByCustomer(customer);

        if (listRecipeCustomer.isEmpty()) {
            throw new EntityNotFoundException();
        }

        List<RecipeDto> listRecipesDto = new ArrayList<RecipeDto>();

        for (RecipeCustomer recipeCustomer : listRecipeCustomer) {
            RecipeDto recipeDto = new RecipeDto(recipeCustomer.getRecipe());
            listRecipesDto.add(recipeDto);
        }

        return listRecipesDto;
    }
}
