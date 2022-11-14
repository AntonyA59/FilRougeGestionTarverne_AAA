package aaa.tavern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import aaa.tavern.service.ShopService;

@SpringBootApplication
public class TavernApplication {
	@Autowired
	static ShopService shopService;

	public static void main(String[] args) {
		SpringApplication.run(TavernApplication.class, args);
	}

}
