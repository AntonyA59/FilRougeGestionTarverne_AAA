package aaa.tavern.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
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

import aaa.tavern.dto.PlayerDto;
import aaa.tavern.service.PlayerService;

/*
import aaa.tavern.entity.VerificationToken;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.context.ApplicationEventPublisher;
import java.util.Calendar;
import java.util.Locale;
*/

@Controller
public class PlayerController {

    @Autowired
    PlayerService playerService ;

    @PostMapping("/register")
    public String createPlayer(@Valid PlayerDto playerDto, BindingResult bindingResult, HttpServletRequest request){
        if(!bindingResult.hasErrors()){
            try{
                playerService.createPlayer(playerDto/*, request*/) ;
                return "redirect:/login" ;
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
    public String getForm(Model model,Principal principal){
        model.addAttribute("player", new PlayerDto("", "", "", "")) ;
        return "/register" ;
    }


    /*
    @GetMapping("/regitrationConfirm")
    public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token) {
    
        Locale locale = request.getLocale();
        
        VerificationToken verificationToken = playerService.getVerificationToken(token);
        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }
        
        Player player = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = messages.getMessage("auth.message.expired", null, locale)
            model.addAttribute("message", messageValue);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        } 
        
        player.setEnabled(true); 
        playerService.saveRegisteredUser(player); 
        return "redirect:/login.html?lang=" + request.getLocale().getLanguage(); 
    }
    */
}