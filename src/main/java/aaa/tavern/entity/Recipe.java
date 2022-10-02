package aaa.tavern.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.sql.Date;

@Entity
@Table(name="recipe")
public class Recipe{
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRecipe;

	@Column(name="name")
    private String name;

	@Column(name="selling_price")
    private Integer sellingPrice;

	@Column(name="level")
    private Integer level ;

	@Column(name="consommation_time")
    private Time consommationTime ;

	@Column(name="preparation_time")
    private Time preparationTime ;

	@Column(name="peremption_date")
    private Date peremptionDate;

	@Column(name="exp_given")
    private Integer expGiven;

	@ManyToOne
    @JoinColumn(name = "subcategory_id")
    private SubCategory subCategory;

	@OneToMany(mappedBy = "recipe")
	private List<RecipeIngredient> tabIngredientsForRecipe= new ArrayList<RecipeIngredient>();

	public Recipe() {}

	public Recipe( String name, Integer sellingPrice, Integer level, Time consommationTime,
	Time preparationTime, Date peremptionDate, Integer expGiven, SubCategory subCategory,ArrayList<RecipeIngredient> tabIngredientsForRecipe) {
		this.name = name;
		this.sellingPrice = sellingPrice;
		this.level = level;
		this.consommationTime = consommationTime;
		this.preparationTime = preparationTime;
		this.peremptionDate = peremptionDate;
		this.expGiven = expGiven;
		this.subCategory = subCategory;
		this.tabIngredientsForRecipe=tabIngredientsForRecipe;
	}

	/**
	 * Deux Recipe sont les mêmes si ils ont le même identifiant.
	 */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Recipe that = (Recipe) o;
        return Objects.equals(idRecipe, that.idRecipe);
    }

	/**
	 * L'identifiant définit le hash.
	 */
    @Override
    public int hashCode() {
        return idRecipe.hashCode();
    }

//#region get/set

	public String getName() {
		return name;
	}

	public void setIdRecipe(Integer idRecipe) {
		this.idRecipe = idRecipe;
	}

	public void setSellingPrice(Integer sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setExpGiven(Integer expGiven) {
		this.expGiven = expGiven;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Time getConsommationTime() {
		return consommationTime;
	}

	public void setConsommationTime(Time consommationTime) {
		this.consommationTime = consommationTime;
	}

	public Time getPreparationTime() {
		return preparationTime;
	}

	public void setPreparationTime(Time preparationTime) {
		this.preparationTime = preparationTime;
	}

	public Date getPeremptionDate() {
		return peremptionDate;
	}

	public void setPeremptionDate(Date peremptionDate) {
		this.peremptionDate = peremptionDate;
	}

	public int getExpGiven() {
		return expGiven;
	}

	public void setExpGiven(int expGiven) {
		this.expGiven = expGiven;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public Integer getIdRecipe() {
		return idRecipe;
	}

	public List<RecipeIngredient> getTabIngredientsForRecipe() {
		return tabIngredientsForRecipe;
	}

	public void setTabIngredientsForRecipe(List<RecipeIngredient> tabIngredientsForRecipe) {
		this.tabIngredientsForRecipe = tabIngredientsForRecipe;
	}

	
//#endregion

}