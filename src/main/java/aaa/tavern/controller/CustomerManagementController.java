package aaa.tavern.controller;

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
import aaa.tavern.service.CustomerManagementService;



@RestController
public class CustomerManagementController {
	@Autowired
	private CustomerManagementService customerManagementService;
    
    @GetMapping("/api/customerManagement/newRecipe")
    public RecipeDto getNeWRecipeForCustomer(){

        return customerManagementService.getNewRecipe();
    }

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

	////////////////////////////////customer leaver (client partir)   a faire !!!!!!
}