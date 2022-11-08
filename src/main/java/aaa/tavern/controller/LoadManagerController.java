package aaa.tavern.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aaa.tavern.dto.LoadManagerDto;
import aaa.tavern.service.LoadManagerService;

@RestController
@RequestMapping("/api/game")
public class LoadManagerController {

    @Autowired
    private LoadManagerService loadManagerService;

    @GetMapping("/manager/loadManager")
    public LoadManagerDto loadManager(@RequestParam int managerId) {

        return loadManagerService.loadManager(managerId);
    }
}
