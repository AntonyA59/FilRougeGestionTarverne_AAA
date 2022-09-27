package aaa.tavern.dto;

public class PlaceDto {
    
    private String name;
    
    private Integer type;
    
    private Integer level;
    
    private ManagerDto managerDto;

    //#region Get
    public String getName() {
        return name;
    }


    public Integer getType() {
        return type;
    }


    public Integer getLevel() {
        return level;
    }


    public ManagerDto getManagerDto() {
        return managerDto;
    }
    //#endregion
}
