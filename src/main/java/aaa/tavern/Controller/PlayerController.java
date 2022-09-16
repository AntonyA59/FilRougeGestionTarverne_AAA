package aaa.tavern.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import aaa.tavern.CrudRepository.PlayerRepository;


@Controller
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/test")
    public String template(){
    
        
        return "test";
    }
    }
