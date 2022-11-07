package aaa.tavern.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import aaa.tavern.dto.ManagerDto;
import aaa.tavern.dto.received.ShopIngredientDto;
import aaa.tavern.exception.ForbiddenException;
import aaa.tavern.service.ShopService;

@RestController
@RequestMapping("/api/game")
public class ShopController {
    @Autowired
    ShopService shopService;

    /**
     * controller that allows the manager to buy ingredrients
     * 
     * @RequestBody ShopIngredientDto with id manager and ingredients
     * @return ManagerDto with manager update
     * @throws EntityNotFoundException exception if the id manager or ingredient is
     *                                 not in the database
     * @throws ForbiddenException      exception if the inventory does not allow the
     *                                 creation of this revenue
     */
    @PostMapping("/shop/ShopBying")
    public ManagerDto byingIngredient(@RequestBody ShopIngredientDto shopIngredientDto) {
        try {
            return shopService.prepareIngredientAndBuy(shopIngredientDto.getIdManager(),
                    shopIngredientDto.getIdIngredient());
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Manager ou Ingredient inexistant");
        } catch (ForbiddenException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Opération non autorisée");
        }
    }

    /**
     * controller that allows the manager to sell ingredrients
     * 
     * @RequestBody ShopIngredientDto with id manager and ingredients
     * @return ManagerDto with manager update
     * @throws EntityNotFoundException exception if the id manager or ingredient is
     *                                 not in the database
     * @throws ForbiddenException      exception if the inventory does not allow the
     *                                 creation of this revenue
     */
    @PostMapping("/shop/ShopSelling")
    public ManagerDto sellingIngredient(@RequestBody ShopIngredientDto shopIngredientDto) {
        try {
            return shopService.prepareIngredientAndSell(shopIngredientDto.getIdManager(),
                    shopIngredientDto.getIdIngredient());

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Manager ou Ingredient inexistant");
        } catch (ForbiddenException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Opération non autorisée");
        }
    }
}
