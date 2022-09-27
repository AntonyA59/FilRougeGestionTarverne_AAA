package aaa.tavern.dto;

public class CategoryDto {
    
    private Integer id;
    private String name;
    
    protected CategoryDto(){

    }

    public CategoryDto(Integer id ,String name){
        this.name = name;
    }

    //#region get
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    //#endregion

    
}
