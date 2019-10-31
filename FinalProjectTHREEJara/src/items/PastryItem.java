package items;

/**
 * backend PastryItem class inherits from Item class
 * @author Riana Jara
 *
 */
public class PastryItem extends Item{

	/**
	 * instance variables
	 */
	private String pastryFlavor;
	private boolean isHeated;
	
	final double HEAT_PRICE = 0.25;
	
	/**
	 * constructor with parameters from superclass constructor
	 * @param name represents Item type (Muffin, Cookie,etc;)
	 * @param cost represents pastry's price
	 * @param flavor represents pastry flavor (depends on if Muffin is chosen, or Cookie is chosen, etc;)
	 * @param heat boolean representing if user wants pastry heated (true = heated, false = not heated)
	 */
	public PastryItem(String name, double cost, String flavor , boolean heat){
		super(name,cost);
		pastryFlavor = flavor;
		isHeated = heat;
	}

	@Override
	/**
	 * calculateCost method overrides superclass method to add pastry cost based on its type (Muffin,Cookie,etc;), its flavor, and if it is heated or not
	 */
	public double calculateCost() {
		double cost = 0;
		if (super.getName().equals("Muffin")) {
			cost = cost+2.00;
		}
		if (super.getName().equals("Cookie")) {
			cost=cost+1.50;
		}
		if(super.getName().equals("Cheesecake Slice")){
			if (pastryFlavor.equals("Regular")) {
				cost = cost+4.00;
			}
			else if (pastryFlavor.equals("Cherry")) {
				cost = cost+4.50;
			}
			else if (pastryFlavor.equals("Blueberry")) {
				cost = cost+4.50;
			}
		}
		if(super.getName().equals("Danish")) {
			cost = cost+2.50;
		}
		if(isHeated) {
			cost= cost+HEAT_PRICE;
		}
		return cost;
	}
	
	/**
	 * getter for heatingOption, displayed on receipt and added to pastry toString
	 * @param heat represents boolean true or false
	 * @return String "heated" if true, or "not heated" if false
	 */
	public String heatingOption(boolean heat) {
		if (heat==true) {
			return "heated";
		}
		else {
			return "not heated";
		}
	}
	
	/**
	 * getter for heating option
	 * @return boolean isHeated
	 */
	public boolean getHeatingOption() {
		return isHeated;
	}
	
	/**
	 * setter for heating option
	 * @param isHeated represents true or false option chosen by user
	 */
	public void setHeatingOption(boolean isHeated) {
		this.isHeated=isHeated;
	}
	
	/**
	 * toString method overridden to fit to pastry item options chosen
	 */
	public String toString() {
		if (getName()=="Cheesecake Slice") {
			return"\n\t-- "+getName()+" ("+heatingOption(isHeated)+")\t\t$"+calculateCost()+"\n\t\tFlavor: "+pastryFlavor;
		}
		else {
			return "\n\t-- "+getName()+" ("+heatingOption(isHeated)+")\t\t\t$"+calculateCost()+"\n\t\tFlavor: "+pastryFlavor;
		}
	}
	
}
