package aaa.tavern.dto;

import aaa.tavern.entity.Place;

public class PlaceDto {
    
    private Integer id;

    private String name;
    
    private Integer type;
    
    private Integer level;

    public PlaceDto(Place place){
        this.id=place.getPlaceId();
        this.name= place.getName();
        this.type=place.getType();
        this.level=place.getLevel();
    }


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
    //#endregion
}
