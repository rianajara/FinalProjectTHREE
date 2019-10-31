package items;

/**
 * abstract backend Item class which is the main superclass, from which pastry, tea, and drink inherit from
 * @author Riana Jara
 *
 */
public abstract class Item {
	/**
	 * instance variables
	 */
	protected String itemName;
	protected double itemCost;
	
	/**
	 * constructor class with no parameters
	 */
	Item(){
		itemName = " ";
		itemCost = 0;
	}
	
	/**
	 * superclass constructor with parameters
	 * @param name String representing Drink/Pastry
	 * @param cost double representing item cost
	 */
	Item(String name, double cost){
		itemName = name;
		itemCost = cost;
	}
	
	/**
	 * getter for item name
	 * @return String item name 
	 */
	public String getName() {
		return itemName;
	}
	
	/**
	 * setter for item name
	 * @param name represents String of item name/tyoe
	 */
	public void setName(String name) {
		itemName = name;
	}
	
	/**
	 * getter for item cost
	 * @return double item cost
	 */
	public double getCost() {
		return itemCost;
	}
	
	/**
	 * setter for item cost
	 * @param cost represents double of item price
	 */
	public void setCost(double cost) {
		itemCost = cost;
	}
	
	/**
	 * abstract calculateCost method, implemented in subclasses since cost 
	 * changes based on item type
	 * @return double cost of the item
	 */
	public abstract double calculateCost();
}
