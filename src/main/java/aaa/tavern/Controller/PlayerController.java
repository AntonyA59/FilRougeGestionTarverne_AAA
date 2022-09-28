package aaa.tavern.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import aaa.tavern.dto.PlayerDto;
import aaa.tavern.Service.PlayerService;




@Controller
public class PlayerController {

    @Autowired
    PlayerService playerService ;

    @PostMapping("/register")
    public String createPlayer(@Valid PlayerDto playerDto, BindingResult bindingResult, RedirectAttributes redirectAttrs){
        if(!bindingResult.hasErrors()){
            try{
                playerService.createPlayer(playerDto) ;
                return "/login" ;
            }catch(DataAccessException e){
                throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Utilisateur déjà existant"
                    );
            }
        }else{
            return "/register" ;
        }
    }

    @GetMapping("/register")
    public String getForm(Model model){
        return "" ;
    }

    @PostMapping("/testConnexion")
    public String connexionPlayer(@Valid PlayerDto playerDto, BindingResult bindingResult, RedirectAttributes redirectAttrs){
        return null ;
    }
}