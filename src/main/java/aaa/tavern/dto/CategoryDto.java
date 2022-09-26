package aaa.tavern.dto;

public class CategoryDto {
    
    private String name;
    
    protected CategoryDto(){

    }

    public CategoryDto(String name){
        this.name = name;
    }


    public String getName() {
        return name;
    }
    
}
