package aaa.tavern.Service.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import aaa.tavern.Entity.Recipe;

@Service
public class ListRecipe {
    private Map<Integer,Recipe> listRecipe=new HashMap<Integer,Recipe>();

    public ListRecipe(Map<Integer,Recipe> listRecipe){
        this.listRecipe=listRecipe;
    }
    
    public Map<Integer,Recipe> getListRecipe() {
        return listRecipe;
    } 
}
