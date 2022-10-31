package aaa.tavern.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public ModelAndView getHome(Principal principal){
        ModelAndView mv = new ModelAndView("index");
        if(principal != null){
            mv.addObject("playerMail", principal.getName()) ;
        }else{
            mv.addObject("playerMail", "<unknown>") ;
        }
        return mv ;
    }
}
