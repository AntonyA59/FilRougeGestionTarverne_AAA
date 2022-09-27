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
import aaa.tavern.Service.CustomerManagementService;



@RestController
public class CustomerManagementController {
	@Autowired
	private CustomerManagementService customerManagementService;
    
    @GetMapping("/api/newRecipe")
    public RecipeDto getNeWRecipeForCustomer(){

        return customerManagementService.getNewRecipe();
    }

	@PostMapping("/api/newCustomer")
	public CustomerDto getNewCustomer(@RequestParam int managerId){
		try {

			return customerManagementService.getNewCustomer(managerId);
		
		} catch (EntityNotFoundException e) {
		
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Ce manager n'existe pas "
			);
		}
	}

	@PostMapping("/api/customerAssignTable")
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
}
