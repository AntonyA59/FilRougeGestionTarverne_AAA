package aaa.tavern.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import aaa.tavern.service.RecipeService;
import aaa.tavern.exception.ForbiddenException;



@RestController
public class RecipeController {

    @Autowired
    private RecipeService recipeService ;
    /**
     * 
     * @param managerId
     * @param recipeId
     * @param customerId
     * @return
     */
    @PostMapping("/api/recipe/requestRecipe")
    public ResponseEntity<String> requestRecipe(@RequestParam int managerId,@RequestParam  int recipeId ,@RequestParam int customerId){
        try {
            recipeService.prepareRecipe(managerId,recipeId,customerId);

            return ResponseEntity.ok().build();
            
        }catch (EntityNotFoundException e1){

			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "recette,client ou manager ne sont pas dans la BDD"
			);
		}
		catch (ForbiddenException e2){
			
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST, "l'inventaire du manager ne permet pas la création de la recette"
			);
		}
    }
}
