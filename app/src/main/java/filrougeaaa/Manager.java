package filrougeaaa;
import filrougeaaa.utils.Model;

public class Manager extends Model{
    protected String name;
    protected int reputation;
    protected int chest;
    protected int exp;
    protected int maxExp;
    protected int level;
    protected Ingredient[] inventory;
    protected Place[] places;
    protected Reservation[] reservation;


	@Override
	public boolean get(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		return false;
	}
//#region get/set
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	public int getChest() {
		return chest;
	}

	public void setChest(int chest) {
		this.chest = chest;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getMaxExp() {
		return maxExp;
	}

	public void setMaxExp(int maxExp) {
		this.maxExp = maxExp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Ingredient[] getInventory() {
		return inventory;
	}

	public void setInventory(Ingredient[] inventory) {
		this.inventory = inventory;
	}

	public Place[] getPlaces() {
		return places;
	}

	public void setPlaces(Place[] places) {
		this.places = places;
	}

	public Reservation[] getReservation() {
		return reservation;
	}

	public void setReservation(Reservation[] reservation) {
		this.reservation = reservation;
	}
//#endregion
}