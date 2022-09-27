package aaa.tavern.entity;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name="place")
public class Place{
    @Id 
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlace;
    
    private String name;
    
    private Integer type;
    
    private Integer level;
    
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;
    
    public Place() {}
    

    //#region
    public Integer getPlaceId() {
        return idPlace;
    }


    public void setPlaceId(Integer idPlace) {
        this.idPlace = idPlace;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Integer getType() {
        return type;
    }


    public void setType(Integer type) {
        this.type = type;
    }


    public Integer getLevel() {
        return level;
    }


    public void setLevel(Integer level) {
        this.level = level;
    }


    public Manager getManager() {
        return manager;
    }


    public void setManager(Manager manager) {
        this.manager = manager;
    }
    //#endregion
}

    /*
    public List<Integer> getListTable(){
        List<Integer> listTable = new ArrayList<Integer>() ;
        try{
            ResultSet resultat = DBManager.execute("SELECT id_table FROM table WHERE id_place = "+this.placeId+" ;");
            
            while(resultat.next()){
                listTable.add(resultat.getInt("id_table")) ;
            }
            return listTable ;
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null ;
        }
    }*/
