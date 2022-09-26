package aaa.tavern.entity;

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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idIngredient;

	@Column(name="name")
	private String name;

	@Column(name="level")
	private Integer level;

	@Column(name="buying_price")
	private Integer buyingPrice;

	@ManyToOne
    @JoinColumn(name = "id_subcategory")
	private SubCategory subCategory;

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

	public Integer getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(Integer buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
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