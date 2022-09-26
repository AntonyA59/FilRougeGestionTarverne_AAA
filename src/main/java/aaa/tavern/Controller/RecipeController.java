package aaa.tavern.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import aaa.tavern.exception.ForbiddenException;
import aaa.tavern.service.RecipeService;

@RestController
public class RecipeController {

    @Autowired
    private RecipeService recipeService ;
    
    @PostMapping("/api/requestRecipe")
    public ResponseEntity<String> requestRecipe(@RequestParam int managerId,@RequestParam  int recipeId ,@RequestParam int customerId){
        try {
            recipeService.prepareRecipe(managerId,recipeId,customerId);

            return ResponseEntity.ok().build();
            
        }catch (EntityNotFoundException e1){
			throw new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "Entity not found"
				);
		}
		catch (ForbiddenException e2)
		{
			throw new ResponseStatusException(
					  HttpStatus.BAD_REQUEST, "Forbidden"
					);
		}
    }
}
