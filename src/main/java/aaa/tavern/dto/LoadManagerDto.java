package aaa.tavern.dto;

import java.util.List;

public class LoadManagerDto {

    private ManagerDto ManagerDto;

    private List<CategoryDto> listCategoryDto;

    private List<CustomerDto> listCustomerDto;

    private List<IngredientDto> listIngredientDto;

    private List<InventoryManagerIngredientDto> listInventoryManagerIngredientDto;

    private List<PlaceDto> listPlaceDto;

    private List<RecipeDto> listRecipeDto;

    private List<RecipeDto> listRecipeCustomerDto;

    private List<SubCategoryDto> listSubCategoryDto;

    private List<TableRestDto> listTableRestDto;

    protected LoadManagerDto() {

    }

    public LoadManagerDto(
            ManagerDto ManagerDto,
            List<CategoryDto> listCategoryDto,
            List<CustomerDto> listCustomerDto,
            List<IngredientDto> listIngredientDto,
            List<InventoryManagerIngredientDto> listInventoryManagerIngredientDto,
            List<PlaceDto> listPlaceDto,
            List<RecipeDto> listRecipeDto,
            List<RecipeDto> listRecipeCustomerDto,
            List<SubCategoryDto> listSubCategoryDto,
            List<TableRestDto> listTableRestDto) {
        this.ManagerDto = ManagerDto;
        this.listCategoryDto = listCategoryDto;
        this.listCustomerDto = listCustomerDto;
        this.listIngredientDto = listIngredientDto;
        this.listInventoryManagerIngredientDto = listInventoryManagerIngredientDto;
        this.listPlaceDto = listPlaceDto;
        this.listRecipeDto = listRecipeDto;
        this.listRecipeCustomerDto = listRecipeCustomerDto;
        this.listSubCategoryDto = listSubCategoryDto;
        this.listTableRestDto = listTableRestDto;
    }

    // #region get
    public ManagerDto getManagerDto() {
        return ManagerDto;
    }

    public List<CategoryDto> getListCategoryDto() {
        return listCategoryDto;
    }

    public List<CustomerDto> getListCustomerDto() {
        return listCustomerDto;
    }

    public List<IngredientDto> getListIngredientDto() {
        return listIngredientDto;
    }

    public List<InventoryManagerIngredientDto> getListInventoryManagerIngredientDto() {
        return listInventoryManagerIngredientDto;
    }

    public List<PlaceDto> getListPlaceDto() {
        return listPlaceDto;
    }

    public List<RecipeDto> getListRecipeDto() {
        return listRecipeDto;
    }

    public List<RecipeDto> getListRecipeCustomerDto() {
        return listRecipeCustomerDto;
    }

    public List<SubCategoryDto> getListSubCategoryDto() {
        return listSubCategoryDto;
    }

    public List<TableRestDto> getListTableRestDto() {
        return listTableRestDto;
    }

    // #endregion

}
