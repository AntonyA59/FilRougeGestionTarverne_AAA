/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package filrougeaaa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import filrougeaaa.utils.DBManager;

public class App {
    protected static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        rechercherIngredient();
        User player = new User();

        Manager manager = new Manager();
        manager.setChest(200);
        manager.setExp(0);
        manager.setMaxExp(100);
        manager.setReputation(0);
        manager.setLevel(1);
        manager.setUser(player);

        Customer client = new Customer(1);
        Table table2pers = new Table(1);
        Place bar = new Place(2);

        Ingredient pate = new Ingredient(1);
        Ingredient emmental = new Ingredient(7);

        Recipe painAuFromage = new Recipe(7);
        client.getCommand().add(painAuFromage);

        String email = "";
        String nomGerant = "";
        String mdp = "";

        System.out.println("Bienvenue dans Tavern Manager !");
        App.r.readLine();
        System.out.println("Vous devez vous inscrire avant de pouvoir jouer au jeu: ");
        App.r.readLine();
        System.out.println("Quelle est votre adresse mail ?");
        try {
            email = r.readLine();
            if (email == "") {
                player.setEmail("JohnDoe@mail.fr");
            } else {
                player.setEmail(email);
            }
        } catch (Exception e) {
            System.out.println("Choix invalide");
        }
        System.out.println("Adresse e-mail : " + player.getEmail());

        App.r.readLine();

        System.out.println("Quelle est votre mot de passe ?");
        while (mdp.isEmpty()) {
            try {
                mdp = App.r.readLine();

            } catch (Exception e) {
                System.out.println("Choix invalide");
            }
            if (mdp.isEmpty()) {
                System.out.println("Veuillez saisir un mot passe");
            } else {
                player.setPassword(mdp);

            }
        }

        System.out.println("Mot de passe : " + player.getPassword());
        App.r.readLine();

        String pseudo = "";
        System.out.println("Votre Pseudo: ");
        while (pseudo.isEmpty()) {
            try {
                pseudo = App.r.readLine();

            } catch (Exception e) {
                System.out.println("Choix invalide");
            }
            if (pseudo.isEmpty()) {
                System.out.println("Veuillez saisir un pseudo");
            } else {
                player.setNickName(pseudo);
            }
        }
        System.out.println("Pseudo : " + player.getNickName());
        App.r.readLine();
        System.out.println("Bienvenue " + player.getNickName() + " !");
        System.out.println("Il est maintenant temp de passer à la création de votre gérant");
        App.r.readLine();
        System.out.println("Quel est son nom ?");

        while (nomGerant.isEmpty()) {
            try {
                nomGerant = App.r.readLine();

            } catch (Exception e) {
                System.out.println("Choix invalide");
            }
            if (nomGerant.isEmpty()) {
                System.out.println("Veuillez saisir un nom");
            } else {
                manager.setName(nomGerant);
            }
        }
        System.out.println("Nom de votre gérant : " + nomGerant);
        App.r.readLine();
        System.out.println("Vous commencez avec " + manager.getChest() + " or dans votre coffre .");

    }

    public static void rechercherIngredient() {
        DBManager.init();
        ResultSet test =   DBManager.execute("SELECT i.name, ri.quantity " +
        "FROM ingredient i " +
        "INNER JOIN recipe_ingredient ri " +
        "ON ri.id_ingredient = i.id_ingredient " +
        "WHERE ri.id_recipe = 7 ");
        try {
            while(test.next()){
                String name = test.getString("name");
                int quantity = test.getInt("quantity");
                System.out.println("Name : " + name);
                System.out.println("Quantity : " + quantity);
            }
            DBManager.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        };
    }
}
