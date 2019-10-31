package items;

/**
 * backend CoffeeItem class inherits from DrinkItem class and its superclass
 * @author Riana Jara
 *
 */
public class CoffeeItem extends DrinkItem {
	/*
	 * instance variables
	 */
	private String temperature;
	private String specialinstructions;
	
	/**
	 * constructor method with no parameters
	 */
	CoffeeItem(){
		super();
		temperature = " ";
		specialinstructions = " ";
	}
	
	/**
	 * constructor with parameters inherited from drinkITem
	 * @param name represents name (Coffee)
	 * @param cost double represents cost of coffee
	 * @param size String representing S/M/L
	 * @param flavor String representing coffee flavors (Hazelnut, Vanilla, etc;)
	 * @param sweet String representing teaspoons of sugar (1-10)
	 * @param milk String representing milk base (whole,no milk)
	 * @param temp String representing temperature (blended,iced,hot)
	 * @param special String representing user's special instructions
	 */
	public CoffeeItem(String name, double cost, String size,String flavor,String sweet,String milk, String temp, String special){
		super(name,cost,size,flavor,sweet,milk);
		temperature = temp;
		specialinstructions = special;
	}
	
	/**
	 * getter for temperature (iced,hot,blended)
	 * @return String represents temperature chosen
	 */
	public String getTemperature() {
		return temperature;
	}
	
	/**
	 * setter for temperature
	 * @param temp representing iced,hot,blended option
	 */
	public void setTemperature(String temp) {
		temperature = temp;
	}
	/**
	 * getter for special instructions
	 * @return String special instructions
	 */
	public String getSpecialInst() {
		return specialinstructions;
	}
	
	/**
	 * setter for special instructions
	 * @param special String representing what the user instructs
	 */
	public void setSpecialInst(String special) {
		specialinstructions = special;
	}
	
	/**
	 * calculateCost() overrides superclass method and changes the cost of item based on coffee options
	 * @return double cost of coffee depending on temperature, milk add-ons, size
	 */
	public double calculateCost() {
		double cost = super.getCost();
		if (temperature.toLowerCase().equals("hot")||temperature.toLowerCase().equals("iced")) {
			if (getSize().toLowerCase().equals("s")) {
				cost = cost+1.00;
			}
			else if(getSize().toLowerCase().equals("m")) {
				cost = cost+1.50;
			}
				
			else if(getSize().toLowerCase().equals("l")){
				cost = cost +2.00;
			}
			
		}
		else if (temperature.toLowerCase().equals("blended")) {
			if (getSize().toLowerCase().equals("s")) {
				cost = cost+1.25;
			}
			else if(getSize().toLowerCase().equals("m")) {
				cost = cost+1.75;
			}
				
			else if(getSize().toLowerCase().equals("l")){
				cost = cost +2.25;
			}
			
		}
		if((getMilk().equals("Whole Milk"))||(getMilk().equals("Half and Half"))||(getMilk().equals("No Milk"))) {
			cost = cost + 0.25;
		}
		return cost;
	}
	
	/**
	 * this method uses superclass method and adds temperature and user's special instructions to coffee item when printing the sale of coffee
	 * @return String of coffee item with chosen parameters
	 */
	public String toString() {
		String receipt = super.toString() + "\n\t\tTemp: "+temperature+"\n\t\tSpecial Instructions: "+specialinstructions;
		return receipt;
	}
	

}
