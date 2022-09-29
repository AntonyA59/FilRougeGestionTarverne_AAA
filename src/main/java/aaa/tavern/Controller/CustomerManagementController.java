package aaa.tavern.Controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import aaa.tavern.dto.CustomerDto;
import aaa.tavern.dto.RecipeDto;
import aaa.tavern.exception.ForbiddenException;
import aaa.tavern.Service.CustomerManagementService;



@RestController
public class CustomerManagementController {
	@Autowired
	private CustomerManagementService customerManagementService;
    
	/**
	 * Controller that requests a new recipe
	 * @return object with the recipe randomly found
	 */
    @PostMapping("/api/customerManagement/newRecipe")
    public RecipeDto getNeWRecipeForCustomer(@RequestParam int managerId){

        return customerManagementService.getNewRecipe(managerId);
    }

	/**
	 * Controller that allows to create a new customer
	 * @param managerId  id of the manager to whom we create a new customer
	 * @return CustomerDto Object that contains the information of the new customer
	 * 
	 * @throws EntityNotFoundException exception if the id manager is not in the database
	 */
	@PostMapping("/api/customerManagement/newCustomer")
	public CustomerDto getNewCustomer(@RequestParam int managerId){
		try {

			return customerManagementService.getNewCustomer(managerId);
		
		} catch (EntityNotFoundException e) {
		
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Ce manager n'existe pas "
			);
		}
	}
	/**
	 * Controller that assigns a table to a customer
	 * @param customerId id customer which receives the id of the table
	 * @param tableId id table that receives the customer
	 * @throws EntityNotFoundException exception if the id customer or table are not in the database
	 */
	@PostMapping("/api/customerManagement/customerAssignTable")
	public ResponseEntity<String> assignNewTableForCustomer(@RequestParam int customerId, @RequestParam int tableId ){
		try {
			customerManagementService.assignNewTable(customerId,tableId);
			
			return ResponseEntity.ok().build();
		
		} catch (EntityNotFoundException e) {
		
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Customer ou table non trouvé dans la BDD"
			);
		}
	}

	@PostMapping("/api/customerManager/customerFinish")
	public ResponseEntity<String> customerFinish(@RequestParam int customerId, @RequestParam int managerId){
		try {
			customerManagementService.customerFinishRecipe(customerId,managerId);
			
			return ResponseEntity.ok().build();
		
		} catch (EntityNotFoundException e) {
		
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Customer ou manager non trouvé dans la BDD"
			);
		} catch(ForbiddenException e1){
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST, "Le temps n'est pas terminé !"
			);
		}
	}
	
}
