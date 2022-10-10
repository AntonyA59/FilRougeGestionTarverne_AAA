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
import aaa.tavern.service.ShopService;

@RestController
public class ShopController {
    @Autowired
    ShopService shopService ;

    @PostMapping("/testShopBying")
    public ResponseEntity<String> byingIngredient(@RequestParam int idManager, @RequestParam int idIngredient){
        try {
            shopService.prepareIngredientAndBuy(idManager, idIngredient);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_ACCEPTABLE, "Manager ou Ingredient inexistant");
        } catch (ForbiddenException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_ACCEPTABLE, "Opération non autorisée");
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/testShopSelling")
    public ResponseEntity<String> sellingIngredient(@RequestParam int idManager, @RequestParam int idIngredient){
        try {
            shopService.prepareIngredientAndSell(idManager, idIngredient);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_ACCEPTABLE, "Manager ou Ingredient inexistant");
        } catch (ForbiddenException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_ACCEPTABLE, "Opération non autorisée");
        }
        return ResponseEntity.ok().build();
    }
}
