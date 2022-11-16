package aaa.tavern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import aaa.tavern.service.ShopService;

@SpringBootApplication
public class TavernApplication extends SpringBootServletInitializer {
	@Autowired
	static ShopService shopService;

	public static void main(String[] args) {
		SpringApplication.run(TavernApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(TavernApplication.class);
	}
}
