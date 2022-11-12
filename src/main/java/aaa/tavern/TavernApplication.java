package aaa.tavern;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import aaa.tavern.dto.received.ShopIngredientDto;
import aaa.tavern.dto.received.ShopIngredientQuantity;
import aaa.tavern.exception.ForbiddenException;
import aaa.tavern.service.ShopService;

@SpringBootApplication
public class TavernApplication {
	@Autowired
	static
	ShopService shopService;

	public static void main(String[] args) {
		

		SpringApplication.run(TavernApplication.class, args);
		ShopIngredientQuantity shopIngredientQuantity = new ShopIngredientQuantity(16, 1);
		ShopIngredientQuantity[] tabShopIngredientQuantity = new ShopIngredientQuantity[1] ;
		tabShopIngredientQuantity[0] = shopIngredientQuantity ;
		ShopIngredientDto shopIngredientDto = new ShopIngredientDto(836, tabShopIngredientQuantity);

		try {
			shopService.prepareIngredientAndBuy(shopIngredientDto) ;
		} catch (EntityNotFoundException | ForbiddenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
