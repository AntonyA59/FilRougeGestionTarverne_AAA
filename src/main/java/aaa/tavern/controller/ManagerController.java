package aaa.tavern.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import aaa.tavern.dto.CreateManagerDTO;
import aaa.tavern.dto.ManagerDto;
import aaa.tavern.service.ManagerService;

@RestController
@RequestMapping("/api/game")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/manager/listExistingManager")
    public List<ManagerDto> listExistingManager(@RequestParam int playerId) {

        return managerService.listExistingManagerDto(playerId);
    }

    @GetMapping("/manager")
    public ManagerDto getManager(@RequestParam int managerId) {

        return managerService.selectManager(managerId);
    }

    @PostMapping("/manager")
    public ResponseEntity<String> deleteManager(@RequestParam int managerId) {

        try {
            managerService.deleteManager(managerId);

            return ResponseEntity.ok().build();

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Le manager n'existe pas");
        }
    }

    @PostMapping("/api/manager/create")
    public ResponseEntity<String> createManager(@RequestBody CreateManagerDTO createManagerDTO,
            BindingResult bindingResult,
            int playerId) {
        if (!bindingResult.hasErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN);
        }
        managerService.createManager(createManagerDTO, playerId);
        return ResponseEntity.ok().build();
    }
}
