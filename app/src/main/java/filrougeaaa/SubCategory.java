package filrougeaaa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="subcategory")
public class SubCategory {
    @Id
    @Column(name = "id_subcategory")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSubCategory;

    @Column(name = "name")
    private String name;

    @Column(name = "id_category")
    private Category category;

    public SubCategory() {
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