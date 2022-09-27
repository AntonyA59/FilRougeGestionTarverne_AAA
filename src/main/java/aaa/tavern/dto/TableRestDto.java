package aaa.tavern.dto;

public class TableRestDto {
    
    protected TableRestDto(){

    }
    public TableRestDto(Integer numberPlace, Float hygiene, Float posX, Float posY, PlaceDto placeDto){
        this.numberPlace = numberPlace;
        this.hygiene = hygiene;
        this.posX = posX;
        this.posY = posY;
        this.placeDto = placeDto;
    }

    private Integer id;
    
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
