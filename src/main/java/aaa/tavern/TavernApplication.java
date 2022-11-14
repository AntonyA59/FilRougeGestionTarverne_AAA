package aaa.tavern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TavernApplication {
	

	public static void main(String[] args) {		

		
		ConfigurableApplicationContext appContext = SpringApplication.run(TavernApplication.class, args);

		/*  Test pour le problème du Shop
		ShopIngredientQuantityDto shopIngredientQuantity = new ShopIngredientQuantityDto(6, 1);
		ShopIngredientQuantityDto[] tabShopIngredientQuantity = new ShopIngredientQuantityDto[2] ;
		tabShopIngredientQuantity[0] = shopIngredientQuantity ;

		shopIngredientQuantity = new ShopIngredientQuantityDto(15, 1);
		tabShopIngredientQuantity[1] = shopIngredientQuantity ;

		ShopIngredientDto shopIngredientDto = new ShopIngredientDto(836, tabShopIngredientQuantity);

    	ShopService service = appContext.getBean(ShopService.class);
		try {
			List<InventoryManagerIngredientDto> test = service.prepareIngredientAndSell(shopIngredientDto) ;
			int i = 0;
		} catch (EntityNotFoundException | ForbiddenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/

	}

}
