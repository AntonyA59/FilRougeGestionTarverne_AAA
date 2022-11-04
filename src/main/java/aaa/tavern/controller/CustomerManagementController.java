package aaa.tavern.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import aaa.tavern.dto.CustomerDto;
import aaa.tavern.dto.RecipeDto;
import aaa.tavern.exception.ForbiddenException;
import aaa.tavern.service.CustomerManagementService;


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
		try{
			return customerManagementService.getNewRecipe(managerId);
		}catch (EntityNotFoundException e) {
		
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Ce manager n'existe pas "
			);
		}
    }

	/**
	 * Controller that allows to create a new customer
	 * @param managerId  id of the manager to whom we create a new customer
	 * @return CustomerDto Object that contains the information of the new customer
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
	 * @return return promise without body with header ok
	 * @throws EntityNotFoundException exception if the id customer or table are not in the database
	 */
	@PostMapping("/api/customerManagement/customerAssignTable")
	public ResponseEntity<String> assignNewTableForCustomer(@RequestParam int customerId, @RequestParam int tableId ){
		try {
			customerManagementService.assignNewTable(customerId,tableId);
			//TODO: modifier le retour
			return ResponseEntity.ok().build();
		
		} catch (EntityNotFoundException e) {
		
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Customer ou table non trouvé dans la BDD"
			);
		}
	}

	/**
	 * Controller which indicates that the customer is served
	 * @param customerId id customer who starts to eat 
	 * @return return promise without body with header ok
	 * @throws EntityNotFoundException exception if the id customer are not in the database
	 */
	@PostMapping("/api/customerManagement/customerServed")
	public ResponseEntity<String> customerServed(@RequestParam int customerId){
		try {
			customerManagementService.customerServed(customerId);
//TODO: modifier le retour
			return ResponseEntity.ok().build();

		} catch (EntityNotFoundException e) {
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Customer non trouvé dans la BDD"
			);
		}
	}


	/**
	 * Controller that allows to get the money from the recipe consume from the customer
	 * @param customerId id customer who finished the recipe
	 * @param managerId id manager who should be given the money
	 * @return return promise without body with header ok
	 * @throws EntityNotFoundException exception if the id customer or manager are not in the database.
	 * @throws ForbiddenException exception if the consumption time is not good
	 */
	@PostMapping("/api/customerManagement/customerFinish")
	public ResponseEntity<String> customerFinish(@RequestParam int customerId, @RequestParam int managerId){
		try {
			customerManagementService.customerFinishRecipe(customerId,managerId);
			//TODO: modifier le retour
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
