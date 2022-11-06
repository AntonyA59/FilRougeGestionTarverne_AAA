package aaa.tavern.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import aaa.tavern.dto.PlayerDto;
import aaa.tavern.service.PlayerService;

@RestController
@RequestMapping("/api")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @PostMapping("/register")
    public void createPlayer(@Valid @RequestBody PlayerDto playerDto) {
        try {
            playerService.createPlayer(playerDto);

        } catch (DataAccessException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Utilisateur déjà existant");
        }
    }

    // @GetMapping("/register")
    // public String getForm(Model model) {
    // model.addAttribute("player", new PlayerDto("", "", "", ""));
    // return "/register";
    // }

    /*
     * @GetMapping("/regitrationConfirm")
     * public String confirmRegistration(WebRequest request, Model
     * model, @RequestParam("token") String token) {
     * 
     * Locale locale = request.getLocale();
     * 
     * VerificationToken verificationToken =
     * playerService.getVerificationToken(token);
     * if (verificationToken == null) {
     * String message = messages.getMessage("auth.message.invalidToken", null,
     * locale);
     * model.addAttribute("message", message);
     * return "redirect:/badUser.html?lang=" + locale.getLanguage();
     * }
     * 
     * Player player = verificationToken.getUser();
     * Calendar cal = Calendar.getInstance();
     * if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime())
     * <= 0) {
     * String messageValue = messages.getMessage("auth.message.expired", null,
     * locale)
     * model.addAttribute("message", messageValue);
     * return "redirect:/badUser.html?lang=" + locale.getLanguage();
     * }
     * 
     * player.setEnabled(true);
     * playerService.saveRegisteredUser(player);
     * return "redirect:/login.html?lang=" + request.getLocale().getLanguage();
     * }
     */
}