package aaa.tavern.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="subcategory")
public class SubCategory {
    @Id
    @Column(name = "id_subcategory")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSubCategory;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    public SubCategory() {
        this.idSubCategory = null ;
        this.name = "";
        this.category = new Category();
    }

    // #region get/set
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getIdSubCategory() {
        return idSubCategory;
    }

    public void setIdSubCategory(Integer idSubCategory) {
        this.idSubCategory = idSubCategory;
    }

    // #endregion get/set
}