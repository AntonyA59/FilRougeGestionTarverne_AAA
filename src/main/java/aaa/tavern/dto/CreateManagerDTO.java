package aaa.tavern.dto;

import aaa.tavern.entity.Manager;

public class CreateManagerDTO {
    private String name;

    protected CreateManagerDTO() {

    }

    public CreateManagerDTO(Manager manager) {
        this.name = manager.getName();
    }

    public String getName() {
        return name;
    }

}
