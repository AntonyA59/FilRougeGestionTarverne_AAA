package aaa.tavern.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import aaa.tavern.exception.ForbiddenException;
import aaa.tavern.service.ShopService;

@RestController
public class ShopController {
    @Autowired
    ShopService shopService ;

    @PostMapping("/testShopBying")
    public String byingIngredient(@RequestParam int idManager, @RequestParam int idIngredient, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs){
        try {
            shopService.prepareIngredientAndBuy(idManager, idIngredient);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_ACCEPTABLE, "Manager ou Ingredient inexistant");
        } catch (ForbiddenException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_ACCEPTABLE, "Opération non autorisée");
        }
        return null ;
    }
}
