package aaa.tavern.dto;

public class TableRestDto {
    
    private Integer numberPlace;
    
    private Float hygiene;
    
    private Float posX;

    private Float posY;
    
    private PlaceDto placeDto;


    //#region Get
    public Integer getNumberPlace() {
        return numberPlace;
    }

    public Float getHygiene() {
        return hygiene;
    }

    public Float getPosX() {
        return posX;
    }

    public Float getPosY() {
        return posY;
    }

    public PlaceDto getPlaceDto() {
        return placeDto;
    }
    //#endregion

    
}
