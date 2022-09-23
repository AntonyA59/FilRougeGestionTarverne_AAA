package aaa.tavern.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {
    @Id
    @Column(name = "id_category")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategory;

    @Column(name="name")
    private String name;

	public Category() {
        this.idCategory = null ;
        this.name = "" ;
    }
//#region Get/Set
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }
//#endregion
}