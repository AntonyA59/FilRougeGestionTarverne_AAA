package filrougeaaa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import filrougeaaa.utils.DBManager;
import filrougeaaa.utils.Model;

public class RecipeIngredient extends Model {
    int quantity;
    Recipe recipe;
    Ingredient ingredient;

    public RecipeIngredient() {
        this.quantity = 0;
        this.recipe = new Recipe();
        this.ingredient = new Ingredient();
    }

    public RecipeIngredient(int id) {
        try {
            ResultSet resultat = DBManager.execute("SELECT * FROM recipe_ingredient WHERE id_ri = " + id);
            if (resultat.next()) {
                this.quantity = resultat.getInt("quantity");
                this.recipe = new Recipe(resultat.getInt("id_recipe"));
                this.ingredient = new Ingredient(resultat.getInt("id_ingredient"));
                this.id = id;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException" + ex.getMessage());
            System.out.println("SQLState" + ex.getSQLState());
            System.out.println("VendorError" + ex.getErrorCode());
        }
    }

    @Override
    public boolean get() {
        try {
            ResultSet resultat = DBManager.execute("SELECT * FROM recipe_ingredient WHERE id_ri = " + this.id);
            if (resultat.next()) {
                this.quantity = resultat.getInt("quantity");
                this.recipe = new Recipe(resultat.getInt("id_recipe"));
                this.ingredient = new Ingredient(resultat.getInt("id_ingredient"));
                return true;
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return false;
    }

    @Override
    public boolean get(int id) {
        try {
            ResultSet resultat = DBManager.execute("SELECT * FROM recipe_ingredient WHERE id_ri = " + id);
            if (resultat.next()) {
                this.quantity = resultat.getInt("quantity");
                this.recipe = new Recipe(resultat.getInt("id_recipe"));
                this.ingredient = new Ingredient(resultat.getInt("id_ingredient"));
                this.id = id;
                return true;
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return false;
    }

    @Override
    public boolean save() {
        String sql;
        if (this.id != 0) {
            sql = "UPDATE recipe_ingredient SET quantity=?,id_recipe=?,id_ingredient=? WHERE id_ri = ?";
        } else {
            sql = "INSERT INTO recipe_ingredient (quantity,id_recipe,id_ingredient) VALUES (?,?,?)";
        }
        try {
            PreparedStatement pstmt = DBManager.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, this.quantity);
            pstmt.setInt(2, this.recipe.getId());
            pstmt.setInt(3, this.ingredient.getId());
            if (this.id != 0)
                pstmt.setInt(4, this.id);

            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            if (this.id == 0 && keys.next()) {
                this.id = keys.getInt(1);
                return true;
            } else if (this.id != 0)
                return true;
            else
                return false;
        } catch (SQLException ex) {
            System.out.println("SQLException" + ex.getMessage());
            System.out.println("SQLState" + ex.getSQLState());
            System.out.println("VendorError" + ex.getErrorCode());
            return false;
        }
    }

    //#region get/set
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public int getId() {
        return this.id;
    }
    //#endregion
}
