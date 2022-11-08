package aaa.tavern.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import aaa.tavern.dto.CreateManagerDTO;
import aaa.tavern.dto.ManagerDto;
import aaa.tavern.dto.PlayerIdDto;
import aaa.tavern.dto.StatusDto;
import aaa.tavern.dto.received.ManagerIdDto;
import aaa.tavern.service.ManagerService;

@RestController
@RequestMapping("/api/game")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/manager/listExistingManager")
    public List<ManagerDto> listExistingManager(@RequestBody PlayerIdDto playerIdDto) {

        return managerService.listExistingManagerDto(playerIdDto.getPlayerId());
    }

    @GetMapping("/manager")
    public ManagerDto getManager(@RequestBody ManagerIdDto managerIdDto) {

        return managerService.selectManager(managerIdDto.getManagerId());
    }

    @PostMapping("/manager")
    public StatusDto deleteManager(@RequestBody ManagerIdDto managerIdDto) {

        try {
            managerService.deleteManager(managerIdDto.getManagerId());

            return new StatusDto(1,"Le manager est bien supprimé");

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Le manager n'existe pas");
        }
    }

    @PostMapping("/api/manager/create")
    public StatusDto createManager(@RequestBody CreateManagerDTO createManagerDTO,
            BindingResult bindingResult,
            int playerId) {
        if (!bindingResult.hasErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN);
        }
        managerService.createManager(createManagerDTO, playerId);
        return new StatusDto(1, "Le manager est bien créé");
    }
}
