package items;

/**
 * backend TeaItem class inherits from DrinkItem class and its superclass
 * @author Riana Jara
 *
 */
import java.util.ArrayList;

public class TeaItem extends DrinkItem{
	/*
	 * instance variables
	 */
	private ArrayList<String> toppings;
	final double TOPPING_PRICE = 0.25;
 	
	
	/**
	 * constructor method with no parameters
	 */
	TeaItem(){
		super();
		toppings = new ArrayList<String>();
	}
	
	/**
	 * constructor method with parameters, calls superclass constructor
	 * @param name represents Tea
	 * @param cost double value of cost of item
	 * @param size string representing size of drink (S/M/L)
	 * @param flavor String representing flavor of tea (green tea, rose tea, etc;)
	 * @param sweet String representing sweetness level (full,1/2,etc;)
	 * @param milk String representing milk base
	 * @param top ArrayList of toppings that user wants to add on
	 */
	public TeaItem(String name, double cost, String size,String flavor,String sweet,String milk, ArrayList<String> top){
		super(name,cost,size,flavor,sweet,milk);
		toppings = top;
	}
	
	/**
	 * addTopping method adds topping to arraylist of toppings
	 * @param t String represents topping to be added
	 */
	public void addTopping(String t) {
		toppings.add(t);
	}
	
	/**
	 * calculateCost() overrides superclass method and changes the cost of item based on tea options
	 * @return double cost of tea depending on topping add-ons, milk add-ons, size
	 */
	public double calculateCost() {
		double cost = super.getCost();
		for (int i=0;i<toppings.size(); i++) {
			cost = cost+TOPPING_PRICE;
		}
		if (getSize().toLowerCase().equals("s")) {
			cost = cost+2.50;
		}
		else if(getSize().toLowerCase().equals("m")) {
			cost = cost+3.00;
		}
			
		else if(getSize().toLowerCase().equals("l")){
			cost = cost +3.50;
		}
		
		if((getMilk().equals("Whole Milk"))||(getMilk().equals("Half and Half"))||(getMilk().equals("No Milk"))) {
			cost = cost + 0.25;
		}
		
		return cost;
	}
	
	/**
	 * this method prints the toppings that the user chose, this method is called when printing tea item in frontend method
	 * @param top represents the toppings list
	 * @return String value of toppings in arraylist
	 */
	public String printToppings(ArrayList<String> top) {
		String list = " ";

		for (String n : top){
			list += n + " | ";
		}
		return list;
	}
	
	/**
	 * this method uses superclass method and adds toppings list to tea item when printing the sale of tea
	 * @return String of tea item with chosen parameters
	 */
	public String toString() {
		return super.toString()+"\n\t\tToppings: "+printToppings(toppings);
		
	}
}
