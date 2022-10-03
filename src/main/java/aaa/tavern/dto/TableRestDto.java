package aaa.tavern.dto;

import aaa.tavern.entity.TableRest;

public class TableRestDto {
    
    private Integer id;

    private Integer numberPlace;
    
    private Float hygiene;
    
    private Float posX;

    private Float posY;
    
    private Integer place;

    public TableRestDto(TableRest tableRest){

        this.id=tableRest.getIdTable();
        this.numberPlace=tableRest.getNumberPlace();
        this.hygiene=tableRest.getHygiene();
        this.posX=tableRest.getPosX();
        this.posY=tableRest.getPosY();
        this.place= tableRest.getPlace().getPlaceId();
    }

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

    public Integer getId() {
        return id;
    }

    public Integer getPlace() {
        return place;
    }
    //#endregion


    
}
