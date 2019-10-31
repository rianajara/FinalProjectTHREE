package items;
/**
 * backend DrinkItem class inherits from Item class
 * @author Riana Jara
 *
 */
public class DrinkItem extends Item{
	private String drinkSize;
	private String drinkFlavor;
	private String drinkSweetness;
	private String drinkMilk;
	
	/**
	 * constructor method with no parameters, calls super class constructor method
	 */
	DrinkItem(){
		super();
		drinkSize = " ";
		drinkFlavor = " ";
		drinkSweetness = " ";
		drinkMilk = " ";
	}
	
	/**
	 * constructor method with parameters, calls super class constructor
	 * @param name String represents name of drink (Coffee/Tea)
	 * @param cost double represents cost of Item
	 * @param size String represents size of item (S/M/L)
	 * @param flavor String represents flavor of Item (depends on Coffee/Tea)
	 * @param sweet String represents sweetness level (1-10)
	 * @param milk String represents milk base (depends on Coffee/Tea)
	 */
	DrinkItem(String name, double cost, String size,String flavor,String sweet,String milk){
		super(name,cost);
		drinkSize = size;
		drinkFlavor = flavor;
		drinkSweetness = sweet;
		drinkMilk = milk;
	}
	
	/**
	 * getter for drink size
	 * @return size (S/M/L)
	 */
	public String getSize() {
		return drinkSize;
	}
	
	/**
	 * setter for drink size
	 * @param size String represents S/M/L
	 */
	public void setSize(String size) {
		drinkSize = size;
	}
	
	/**
	 * getter for drink flavor
	 * @return drink flavor (depends on Tea/Coffee)
	 */
	public String getFlavor() {
		return drinkFlavor;
	}
	
	/**
	 * setter for drink flavor
	 * @param flavor represents String drink flavors
	 */
	public void setFlavor(String flavor) {
		drinkFlavor = flavor;
	}
	
	/**
	 * getter for sweetness level
	 * @return drinkSweetness represents levels 1-10
	 */
	public String getSweetness() {
		return drinkSweetness;
	}
	
	/**
	 * setter for sweetness level
	 * @param sweet represents String teaspoon sugar level amount
	 */
	public void setSweetness(String sweet) {
		drinkSweetness = sweet;
	}
	
	/**
	 * getter for milk base
	 * @return String of milk base (whole,half/half,no milk)
	 */
	public String getMilk() {
		return drinkMilk;
	}
	
	/**
	 * setter for milk base
	 * @param milk base String
	 */
	public void setMilk(String milk) {
		drinkMilk = milk;
	}
	
	/**
	 * override method call
	 * @return getCost amount from superclass
	 */
	public double getCost() {
		return super.getCost();
	}
	
	/**
	 * implements calculateCost abstract method
	 * @return double cost of the item 
	 */
	public double calculateCost() {
		double cost = getCost();
		return cost;
	}
	
	/**
	 * toString method overridden
	 * @return String representation of drink item which will be called when printing receipt
	 */
	public String toString() {
		
		String receipt = "\n\t-- "+getFlavor()+" ("+drinkSize+")\t\t\t$"+calculateCost()+"\n\t\t"+"Sweetness: "+drinkSweetness+"\n\t\t"+"Milk: "+drinkMilk;
		return receipt;
	}

}
