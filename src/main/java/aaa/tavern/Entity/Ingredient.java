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
@Table(name="ingredient")
public class Ingredient {
	@Id
    @Column(name = "id_ingredient")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idIngredient;

	@Column(name="name")
	protected String name;

	@Column(name="level")
	protected int level;

	@Column(name="buying_price")
	protected int buyingPrice;

	@ManyToOne
    @JoinColumn(name = "id_subcategory")
	protected SubCategory subCategory;

	public Ingredient() {
		this.idIngredient = null ;
		this.name = "";
		this.level = 0;
		this.buyingPrice = 0;
		this.subCategory = new SubCategory();
	}
	// #region Get/Set
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(int buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	// #endregion

	public Integer getIdIngredient() {
		return idIngredient;
	}

	public void setIdIngredient(Integer idIngredient) {
		this.idIngredient = idIngredient;
	}
}