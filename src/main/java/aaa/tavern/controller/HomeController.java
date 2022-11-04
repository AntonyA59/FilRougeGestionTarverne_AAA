package aaa.tavern.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
    
    @GetMapping("/")
    public String getHome(Principal principal){
        ModelAndView mv = new ModelAndView("index");
        if(principal != null){
            mv.addObject("playerMail", principal.getName()) ;
            return principal.getName() ;
        }else{
            mv.addObject("playerMail", "<unknown>") ;
            return "unknown" ;
        }
    }
}
