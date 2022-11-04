package aaa.tavern.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dto.CategoryDto;
import aaa.tavern.dto.CustomerDto;
import aaa.tavern.dto.IngredientDto;
import aaa.tavern.dto.InventoryManagerIngredientDto;
import aaa.tavern.dto.LoadManagerDto;
import aaa.tavern.dto.ManagerDto;
import aaa.tavern.dto.PlaceDto;
import aaa.tavern.dto.RecipeDto;
import aaa.tavern.dto.SubCategoryDto;
import aaa.tavern.dto.TableRestDto;
import aaa.tavern.entity.Manager;
import aaa.tavern.utils.ServiceUtil;

@Service
public class LoadManagerService {

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    ManagerService managerService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    IngredientService ingredientService;

    @Autowired
    ManagerCustomerService managerCustomerService;

    @Autowired
    PlaceService placeService;

    @Autowired
    RecipeCustomerService recipeCustomerService;

    @Autowired
    RecipeService recipeService;

    @Autowired
    SubCategoryService subCategoryService;

    @Autowired
    TableRestService tableRestService;

    /**
     * Load Manager's game selected by its ID
     * 
     * @param managerId
     * @return LoadManagerDto
     */
    public LoadManagerDto loadManager(int managerId) {

        Manager manager = ServiceUtil.getEntity(managerRepository, managerId);

        ManagerDto managerDto = managerService.loadManagerDto(manager);

        List<InventoryManagerIngredientDto> listInventoryManagerIngredientDto = managerService
                .loadInventoryIngredientsByManager(managerId);

        List<CategoryDto> listCategoryDto = categoryService.loadAllCategory();
        List<SubCategoryDto> listSubCategoryDto = subCategoryService.loadAllSubCategory();
        List<IngredientDto> listIngredientDto = ingredientService.loadIngredientsByManagerLevel(manager.getLevel());
        List<CustomerDto> listCustomerDto = managerCustomerService.loadCustomerByManager(managerId);
        List<RecipeDto> listRecipeCustomerDto = new ArrayList<RecipeDto>();
        for (CustomerDto customerDto : listCustomerDto) {
            listRecipeCustomerDto = recipeCustomerService.loadRecipeByCustomer(customerDto.getId());
        }
        List<PlaceDto> listPlaceDto = placeService.loadPlaceByManagerId(managerId);
        List<TableRestDto> listTableRestDto = new ArrayList<TableRestDto>();
        for (PlaceDto placeDto : listPlaceDto) {
            listTableRestDto = tableRestService.loadTableRestByPlace(placeDto.getId());
        }
        List<RecipeDto> listRecipeDto = recipeService.loadRecipeByLessOrEqualLevel(manager.getLevel());

        LoadManagerDto loadManagerDto = new LoadManagerDto(managerDto, listCategoryDto, listCustomerDto,
                listIngredientDto, listInventoryManagerIngredientDto, listPlaceDto, listRecipeDto,
                listRecipeCustomerDto, listSubCategoryDto,
                listTableRestDto);
        return loadManagerDto;
    }
}
