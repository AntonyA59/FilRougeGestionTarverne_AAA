package aaa.tavern.dto;

public class PlaceDto {
    
    protected PlaceDto(){

    }

    public PlaceDto(Integer id, String name, Integer type, Integer level, ManagerDto managerDto){
        this.id = id;
        this.name = name;
        this.type = type;
        this.level = level;
        this.managerDto = managerDto;
    }

    private Integer id;

    private String name;
    
    private Integer type;
    
    private Integer level;
    
    private ManagerDto managerDto;


    //#region Get

    public Integer getId() {
        return id;
    }
    
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
