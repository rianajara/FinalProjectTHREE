package frames;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

//import items from items.package
import items.CoffeeItem;
import items.DrinkItem;
import items.Item;
import items.PastryItem;
import items.TeaItem;

/**
 * 
 * @author Riana Jara
 * Purchase Frame - main frame that is called by main
 * This frame has buttons that trigger Coffee Panel, Tea Panel, and Pastry Panel
 * This frame leads to Finalize Frame when user is finished purchasing items
 */
public class guitestforfinal extends JFrame {
	/**
	 * Instance Variables 
	 */
	private ArrayList<Item> itemList = new ArrayList<Item>();
	private ArrayList<String> StringReceipts = new ArrayList<String>();
	protected JLabel initial = new JLabel("Select an item to purchase: ");
	protected JButton coffeeButton = new JButton("Coffee");
	protected JButton teaButton = new JButton("Tea");
	protected JButton pastryButton = new JButton("Pastry");
	protected JButton doneButton = new JButton("Done");
	protected JButton cancelButton = new JButton("Cancel");
	protected JTextArea addingReceipt = new JTextArea(20,55);;
	protected JScrollPane scroll;
	protected ArrayList<String> receipts = new ArrayList<String>();
	
	ActionListener doneListener = new DoneListener();
	public JPanel panel = new JPanel();
	double payment = 0;
	JLabel amountDue = new JLabel("Amount due: "+Double.toString(round(getOverTotal()-payment,2)));
	
	
	/**
	 * addItem method adds item to itemList arraylist
	 * @param d - represents either pastry/drink item
	 */
	public void addItem(Item d){
		itemList.add(d);
		
	}
	
	/**
	 * uses tax percentage and adds amount to get grand total
	 * @return double GRAND TOTAL 
	 */
	public double getOverTotal(){
		double tax =1.10;
		return getSubTotal()*tax;
	}
	
	
	/**
	 * getSubTotal computes total sub total for all items
	 * @return double subtotal of all items in itemList
	 */
	public double getSubTotal(){
		double cost = 0;
		for(int i = 0; i < itemList.size(); i++){	

			cost += itemList.get(i).calculateCost();
		}
		return cost;
	}
	
	public String getReceipt(){
		
		String receipt = "";
		for(int i = 0; i < itemList.size(); i++){
			receipt +=  itemList.get(i).toString()+"\n";
		}
		
		StringReceipts.add(receipt);

		return receipt;
	}
	
	/**
	 * computes amount of tax from subtotal
	 * @return double taxed amount from subtotal
	 */
	public double getTax() {
		double taxrate = 0.10;
		return getSubTotal()*taxrate;
	}

	
	/**
	 * method to round double value to format with a certain number of decimal places ($#.##)
	 * @param value - double can be any number
	 * @param places - how many decimal places
	 * @return double with correct number of decimal places
	 */
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	/**
	 * 
	 * @return String of all items purchase
	 */
	public String getAllSales(){
		String receipts = "";
		int size = StringReceipts.size();
		
		for(int i = 0; i < size; i++){	
			
			receipts += StringReceipts.get(i);
			
		}
		
		return receipts;
	}
	
	/**
	 * method adds buttons to initial purchase panel so when the user first runs program, only three buttons appear (coffee,tea,pastry) and 
	 * user is prompted to select
	 */
	public void initialPurchase() {
		panel.add(initial);
		panel.add(coffeeButton);
		panel.add(teaButton);
		panel.add(pastryButton);
		
	}
	
	/**
	 * constructor method
	 * "this" represents the current frame
	 * uses coffeeButton, teaButton, and pastryButton with implementations of ActionListeners to make new panels and replace initial purchase panel
	 * to make new panels according to which button is clicked by the user
	 * doneButton is also implemented by using the doneListener, which is an inner class that extends from ActionListener to open a 
	 * new frame that displays final receipt and asks user for payment until the user enters aa sufficient amount
	 */
	public guitestforfinal() {
			this.setTitle("New Order");
			this.setSize(500, 500);
			this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			
			initialPurchase();
			coffeeButton.addActionListener(new ActionListener() {
				//coffee instance varibales
				private JLabel instructions;
				private JLabel flavorINST;
				private JLabel sizesINST;
				private JLabel teaspoonsINST;
				private JLabel milkINST;
				private JLabel typeINST;
				private JLabel specialINST;
				
				private JComboBox<String> sizes;
				private JComboBox<String> teaspoons;
				private JComboBox<String> milks;
				private JComboBox<String> flavors;
				private JComboBox<String> types;
				
				private JTextField specials;
				
				private JButton saveButton;
				private JButton cancelButton;
				
				JPanel coffeePanel = new JPanel();
				
				/**
				 * createComponents() method creates the coffee purchase panel, displays coffee options
				 */
				private void createComponents() {
					instructions = new JLabel("Specify the coffee: ");
					flavorINST = new JLabel("flavor: ");
					String[] f = {"Regular", "Mocha", "Hazelnut", "Vanilla"};
					flavors = new JComboBox<String>(f);
					sizesINST = new JLabel("size: ");
					String[] s = {"S", "M" ,"L"};
					sizes = new JComboBox<String>(s);
					teaspoonsINST = new JLabel("sugar: ");
					String[] numTeaspoons = {"1", "2", "3", "4","5","6","7","8","9","10"};
					teaspoons = new JComboBox<String>(numTeaspoons);
					milkINST = new JLabel("milk: ");
					String[] bases = {"Whole Milk", "Half and Half", "No Milk"};
					milks = new JComboBox<String>(bases);
					typeINST = new JLabel("type: ");
					String[] temp = {"Hot","Iced","Blended"};
					types = new JComboBox<String>(temp);
					specialINST = new JLabel("special instructions: ");
					specials = new JTextField(60);
					saveButton = new JButton("Save");
					cancelButton = new JButton("Cancel");
					
					
					//add components to coffee panel
					coffeePanel.add(instructions);
					coffeePanel.add(flavorINST);
					coffeePanel.add(flavors);
					coffeePanel.add(sizesINST);
					coffeePanel.add(sizes);
					coffeePanel.add(teaspoonsINST);
					coffeePanel.add(teaspoons);
					coffeePanel.add(milkINST);
					coffeePanel.add(milks);
					coffeePanel.add(typeINST);
					coffeePanel.add(types);
					coffeePanel.add(specialINST);
					coffeePanel.add(specials);
					coffeePanel.add(saveButton);
					coffeePanel.add(cancelButton);
				}
				
				/**
				 * getPurchase() method returns coffee item with set size, flavor, sweetness, milk, temp, special instructions
				 * @return DrinkItem - coffee item with arguments
				 */
				private DrinkItem getPurchase() {
					String flavor = (String) flavors.getSelectedItem();
					String size = (String) sizes.getSelectedItem();
					String sweetness = (String) teaspoons.getSelectedItem();
					String milk = (String) milks.getSelectedItem();
					String temp = (String) types.getSelectedItem();
					String special = specials.getText();
					DrinkItem coffee= new CoffeeItem("Coffee",0,size, flavor+" Coffee", sweetness, milk,temp, special);
					return coffee;
				}
				
				@Override
				/**
				 * actionPerformed method overrides ActionListener method so that a new tea panel is made when the user clicks "Coffee"
				 * @param click represents the user's click of the "Coffee" button
				 */
				public void actionPerformed(ActionEvent click) {
					Component b = (Component) click.getSource();
					JFrame c = (JFrame) SwingUtilities.getRoot(b);
					createComponents();
					c.setTitle("New Coffee Order");
					c.setSize(800,400);
					
					saveButton.addActionListener(new ActionListener() {

						@Override
						/**
						 * actionPerformed method is overridden from ActionListener class so that when user saves,
						 * their purchase is added to the receipt
						 * 
						 */
						public void actionPerformed(ActionEvent arg0) {
							coffeePanel.removeAll();
							c.remove(coffeePanel);
							addItem(getPurchase());
							addingReceipt.setText(null);
							addingReceipt.append("\n\t~~~~~~~~~~~~~~~~~~Current Order~~~~~~~~~~~~~~~~~~");
							addingReceipt.append(getReceipt());
							addingReceipt.append("\n\n\n\t\tSubtotal: "+ Double.toString(round(getSubTotal(),2)));
							addingReceipt.append("\n\t\tTax: "+Double.toString(round(getTax(),2)));
							addingReceipt.append("\n\t\tGrand Total: "+Double.toString(round(getOverTotal(),2)));
							scroll = new JScrollPane(addingReceipt);
							panel.add(scroll);
							panel.add(doneButton);
							doneButton.addActionListener(doneListener);
							c.setSize(500,600);
							c.add(panel);
						}
						
						
					});
					
					cancelButton.addActionListener(new ActionListener() {

						@Override
						/**
						 * actionPerformed method is overridden from ActionListener class so that when user cancels,
						 * their purchases stay on the receipt
						 * 
						 */
						public void actionPerformed(ActionEvent arg0) {
							coffeePanel.removeAll();
							c.remove(coffeePanel);
							addingReceipt.setText(null);
							addingReceipt.append("\n\t~~~~~~~~~~~~~~~~~~Current Order~~~~~~~~~~~~~~~~~~");
							addingReceipt.append(getReceipt());
							addingReceipt.append("\n\n\n\t\tSubtotal: "+ Double.toString(round(getSubTotal(),2)));
							addingReceipt.append("\n\t\tTax: "+Double.toString(round(getTax(),2)));
							addingReceipt.append("\n\t\tGrand Total: "+Double.toString(round(getOverTotal(),2)));
							scroll = new JScrollPane(addingReceipt);
							panel.add(scroll);
							panel.add(doneButton);
							doneButton.addActionListener(doneListener);
							c.setSize(500,600);
							c.add(panel);
						}
						
					});
					
					c.remove(panel);
					c.add(coffeePanel);
					
				}
				
				
			});
			
			teaButton.addActionListener(new ActionListener() {
				/**
				 * instance variables
				 */
				private JLabel instructions;
				private JLabel flavorINST;
				private JLabel sizesINST;
				private JLabel teaspoonsINST;
				private JLabel milkINST;

				
				private JComboBox<String> sizes;
				private JComboBox<String> teaspoons;
				private JComboBox<String> milks;
				private JComboBox<String> flavors;
				private JButton saveButton;
				private JButton cancelButton;
				
				private JCheckBox boba;
				private JCheckBox poppingboba;
				private JCheckBox grassjelly;
				private JCheckBox lycheejelly;
				private JCheckBox coconutjelly;
				private JCheckBox minimochi;
				
				JPanel teaPanel = new JPanel();
				
				/**
				 * createComponents method for teaPanel, makes a panel that displays tea purchase options
				 */
				private void createComponents() {
					instructions = new JLabel("Specify the tea:");
					
					//make JComboBoxes for drop down menus
					flavorINST = new JLabel("flavor: ");
					String[] f = {"Green Tea", "Black Tea", "Jasmine Green Tea", "Rose Tea", "Oolong Tea"};
					flavors = new JComboBox<String>(f);
					sizesINST = new JLabel("size: ");
					String[] s = {"S", "M" ,"L"};
					sizes = new JComboBox<String>(s);
					teaspoonsINST = new JLabel("sugar: ");
					String[] numTeaspoons = {"full", "3/4", "1/2", "unsweetened"};
					teaspoons = new JComboBox<String>(numTeaspoons);
					milkINST = new JLabel("milk: ");
					String[] bases = {"Whole Milk", "Half and Half", "No Milk"};
					milks = new JComboBox<String>(bases);
				
					//make CheckBoxes for toppings
					boba = new JCheckBox("boba");
					poppingboba = new JCheckBox("popping boba");
					grassjelly = new JCheckBox("grass jelly");
					lycheejelly = new JCheckBox("lychee jelly");
					coconutjelly = new JCheckBox("coconut jelly");
					minimochi = new JCheckBox("mini mochi");
					
					cancelButton = new JButton("Cancel");
					saveButton = new JButton("Save");

					
					//add teaPanel components
					teaPanel.add(instructions);
					teaPanel.add(flavorINST);
					teaPanel.add(flavors);
					teaPanel.add(sizesINST);
					teaPanel.add(sizes);
					teaPanel.add(teaspoonsINST);
					teaPanel.add(teaspoons);
					teaPanel.add(milkINST);
					teaPanel.add(milks);
					teaPanel.add(saveButton);
					teaPanel.add(cancelButton);
					teaPanel.add(boba);
					teaPanel.add(poppingboba);
					teaPanel.add(grassjelly);
					teaPanel.add(lycheejelly);
					teaPanel.add(coconutjelly);
					teaPanel.add(minimochi);
				}
				
				/**
				 * getPurchase() for teaPanel returns tea
				 * @return DrinkItem with set toppings, sweetness, flavor, size
				 */
				private DrinkItem getPurchase() {
					String flavor = (String) flavors.getSelectedItem();
					String size = (String) sizes.getSelectedItem();
					String sweetness = (String) teaspoons.getSelectedItem();
					String milk = (String) milks.getSelectedItem();
					
					//makes an arraylist of toppings to be added if user checks boxes
					ArrayList<String> toppings = new ArrayList<String>();
					
					if (boba.isSelected()) {
						toppings.add(boba.getText());
					}
					
					if (poppingboba.isSelected()) {
						toppings.add(poppingboba.getText());
					}
					
					if (grassjelly.isSelected()) {
						toppings.add(grassjelly.getText());
					}
					
					if (lycheejelly.isSelected()) {
						toppings.add(lycheejelly.getText());
					}
					
					if (coconutjelly.isSelected()) {
						toppings.add(coconutjelly.getText());
					}
					
					if (minimochi.isSelected()) {
						toppings.add(minimochi.getText());
					}
					
					if (!boba.isSelected()&&!poppingboba.isSelected()&&!grassjelly.isSelected()&&!lycheejelly.isSelected()&&!coconutjelly.isSelected()&&!minimochi.isSelected()) {
						toppings.add("No toppings");
					}

					DrinkItem tea= new TeaItem("Tea",0,size, flavor, sweetness, milk, toppings);
					return tea;
				}
				
			
				
				@Override
				/**
				 * actionPerformed method overrides ActionListener method so that a new tea panel is made when the user clicks "Tea"
				 * @param click represents the user's click of the "Tea" button
				 */
				public void actionPerformed(ActionEvent click) {
					Component b = (Component) click.getSource();
					
					
					JFrame c = (JFrame) SwingUtilities.getRoot(b);
					
				
					createComponents();
					c.setTitle("New Tea Order");
					c.setSize(850,400);
				
					saveButton.addActionListener(new ActionListener() {

						@Override
						/**
						 * actionPerformed method is overridden from ActionListener class so that when user saves,
						 * their purchase is added to the receipt
						 * 
						 */
						public void actionPerformed(ActionEvent arg0) {
							teaPanel.removeAll();
							c.remove(teaPanel);
							addItem(getPurchase());
							addingReceipt.setText(null);
							addingReceipt.append("\n\t~~~~~~~~~~~~~~~~~~Current Order~~~~~~~~~~~~~~~~~~");
							addingReceipt.append(getReceipt());
							addingReceipt.append("\n\n\n\t\tSubtotal: "+ Double.toString(round(getSubTotal(),2)));
							addingReceipt.append("\n\t\tTax: "+Double.toString(round(getTax(),2)));
							addingReceipt.append("\n\t\tGrand Total: "+Double.toString(round(getOverTotal(),2)));
							scroll = new JScrollPane(addingReceipt);
							panel.add(scroll);
							panel.add(doneButton);
							doneButton.addActionListener(doneListener);
							c.setSize(500,600);
							c.add(panel);
						}
						
						
					});
					
					cancelButton.addActionListener(new ActionListener() {

						@Override
						/**
						 * actionPerformed method is overridden from ActionListener class so that when user cancels,
						 * their purchases stay on the receipt
						 * 
						 */
						public void actionPerformed(ActionEvent arg0) {
							teaPanel.removeAll();
							c.remove(teaPanel);
							addingReceipt.setText(null);
							addingReceipt.append("\n\t~~~~~~~~~~~~~~~~~~Current Order~~~~~~~~~~~~~~~~~~");
							addingReceipt.append(getReceipt());
							addingReceipt.append("\n\n\n\t\tSubtotal: "+ Double.toString(round(getSubTotal(),2)));
							addingReceipt.append("\n\t\tTax: "+Double.toString(round(getTax(),2)));
							addingReceipt.append("\n\t\tGrand Total: "+Double.toString(round(getOverTotal(),2)));
							scroll = new JScrollPane(addingReceipt);
							panel.add(scroll);
							panel.add(doneButton);
							doneButton.addActionListener(doneListener);
							c.setSize(500,600);
							c.add(panel);
						}
						
					});
					
					c.remove(panel);
					c.add(teaPanel);
					
				}
				
			});
			pastryButton.addActionListener(new ActionListener() {
				
				protected JComboBox<String> names;
				protected JComboBox<String>flavorsCookie;
				
				protected JComboBox<String>flavorsCake;
				protected JComboBox<String>flavorsMuffin;
				
				protected JComboBox<String>flavorsDanish;
				private JButton saveButton = new JButton("Save"); ;
				private JButton cancelButton = new JButton("Cancel");;
				protected JCheckBox heated = new JCheckBox("heated");
				JPanel pastryPanel = new JPanel();
				
				/**
				 * getPurchase() method returns Pastry item with set name, flavor, and heated option
				 * @return pastry item with arguments
				 */
				private PastryItem getPurchase() {
					String name = (String) names.getSelectedItem();
					String flavor = "";
					boolean hot = false;
					if (heated.isSelected()) {
						hot = true;
					}
					else {
						hot = false;
					}
					if (name.equals("Muffin")) {
						flavor = (String) flavorsMuffin.getSelectedItem();
						PastryItem pastry = new PastryItem(name,0,flavor,hot);
						return pastry;
					}
					else if (name.equals("Cheesecake Slice")) {
						flavor = (String) flavorsCake.getSelectedItem();
						PastryItem pastry = new PastryItem(name,0,flavor,hot);
						return pastry;
					}
					else if (name.equals("Cookie")) {
						flavor = (String) flavorsCookie.getSelectedItem();
						PastryItem pastry = new PastryItem(name,0,flavor,hot);
						return pastry;
					}
					else {
						flavor = (String) flavorsDanish.getSelectedItem();
						PastryItem pastry = new PastryItem(name,0,flavor,hot);
						return pastry;
					}
					
				}
				
				/**
				 * createComponents() method creates the buttons and comboboxes for the pastry panel
				 * and displays the options on the panel
				 * This method uses ItemListener to switch between combobox choices so that whenever
				 * the user changes a selection, their pastry flavors change too
				 */
				private void createComponents() {
					String [] desserts = {"Muffin","Cheesecake Slice", "Cookie", "Danish"};
					String[] cookies = {"Oatmeal", "White Choco & Macadamias", "Chocolate Chip", "Double Fudge"};
					String[] slices = {"Regular", "Cherry", "Blueberry"};
					String[] muffins = {"Banana Nut", "Blueberry","Chocolate Chip", "Coffee Cake"};
					String[] danishes = {"Apple Cinnamon","Strawberry & Cheese","Double Cheese"};
					
					names = new JComboBox<String>(desserts);
					flavorsCookie = new JComboBox<String>(cookies);
					flavorsCake = new JComboBox<String>(slices);
					flavorsMuffin= new JComboBox<String>(muffins);
					flavorsDanish = new JComboBox<String>(danishes);
										
					cancelButton = new JButton("Cancel");
					saveButton = new JButton("Save");
					
					names.addItemListener(new ItemListener() {
						/**
						 * itemStateChanged method is overridden from ItemListener class so that when user
						 * changes pastry item, flavor changes to appropriate item as well
						 */
						public void itemStateChanged(ItemEvent event) {
							JComboBox comboBox = (JComboBox) event.getSource();
							Object item = event.getItem();
							if (item.equals("Muffin")) {
								pastryPanel.remove(flavorsCake);
								pastryPanel.remove(flavorsCookie);
								pastryPanel.remove(flavorsDanish);
								pastryPanel.add(flavorsMuffin);
								pastryPanel.add(heated);
								pastryPanel.add(saveButton);
								pastryPanel.add(cancelButton);
								repaint();
								revalidate();
							}
							else if (item.equals("Cheesecake Slice")) {
								pastryPanel.remove(flavorsDanish);
								pastryPanel.remove(flavorsCookie);
								pastryPanel.remove(flavorsMuffin);
								pastryPanel.add(flavorsCake);
								pastryPanel.add(heated);
								pastryPanel.add(saveButton);
								pastryPanel.add(cancelButton);
								repaint();
								revalidate();
							}
							else if (item.equals("Cookie")) {
								pastryPanel.remove(flavorsCake);
								pastryPanel.remove(flavorsDanish);
								pastryPanel.remove(flavorsMuffin);
								pastryPanel.add(flavorsCookie);
								pastryPanel.add(heated);
								pastryPanel.add(saveButton);
								pastryPanel.add(cancelButton);
								repaint();
								revalidate();
							}
							else if(item.equals("Danish")) {
								pastryPanel.remove(flavorsCake);
								pastryPanel.remove(flavorsCookie);
								pastryPanel.remove(flavorsMuffin);
								pastryPanel.add(flavorsDanish);
								pastryPanel.add(heated);
								pastryPanel.add(saveButton);
								pastryPanel.add(cancelButton);
								repaint();
								revalidate();
							}
							
							
						}
					});
					
					pastryPanel.add(names);
					
					pastryPanel.add(cancelButton);
					
				}
				
				@Override
				/**
				 * actionPerformed method overrides ActionListener method so that a new tea panel is made when the user clicks "Pastry"
				 * @param click represents the user's click of the "Pastry" button
				 */
				public void actionPerformed(ActionEvent click) {
					Component b = (Component) click.getSource();
					
					JFrame c = (JFrame) SwingUtilities.getRoot(b);
					
					createComponents();
					c.setTitle("New Pastry Order");
					c.setSize(850,400);
					c.remove(panel);
					c.add(pastryPanel);
					saveButton.addActionListener(new ActionListener() {

						@Override
						/**
						 * actionPerformed method is overridden from ActionListener class so that when user saves,
						 * their purchase is added to the receipt
						 * 
						 */
						public void actionPerformed(ActionEvent arg0) {
							pastryPanel.removeAll();
							c.remove(pastryPanel);
							addItem(getPurchase());
							addingReceipt.setText(null);
							addingReceipt.append("\n\t~~~~~~~~~~~~~~~~~~Current Order~~~~~~~~~~~~~~~~~~");
							
						
							addingReceipt.append(getReceipt());
							addingReceipt.append("\n\n\n\t\tSubtotal: "+ Double.toString(round(getSubTotal(),2)));
							addingReceipt.append("\n\t\tTax: "+Double.toString(round(getTax(),2)));
							addingReceipt.append("\n\t\tGrand Total: "+Double.toString(round(getOverTotal(),2)));
							scroll = new JScrollPane(addingReceipt);
							panel.add(scroll);
							panel.add(doneButton);
							doneButton.addActionListener(doneListener);
							c.setSize(500,600);
							c.add(panel);
							
						}
						
					});
					
					
					cancelButton.addActionListener(new ActionListener() {
						@Override
						/**
						 * actionPerformed method is overridden from ActionListener class so that when user cancels,
						 * their purchases stay on the receipt
						 * 
						 */
						public void actionPerformed(ActionEvent arg0) {
							pastryPanel.removeAll();
							c.remove(pastryPanel);
							addingReceipt.setText(null);
							addingReceipt.append("\n\t~~~~~~~~~~~~~~~~~~Current Order~~~~~~~~~~~~~~~~~~");
							addingReceipt.append(getReceipt());
							addingReceipt.append("\n\n\n\t\tSubtotal: "+ Double.toString(round(getSubTotal(),2)));
							addingReceipt.append("\n\t\tTax: "+Double.toString(round(getTax(),2)));
							addingReceipt.append("\n\t\tGrand Total: "+Double.toString(round(getOverTotal(),2)));
							scroll = new JScrollPane(addingReceipt);
							panel.add(scroll);
							panel.add(doneButton);
							doneButton.addActionListener(doneListener);
							c.setSize(500,600);
							c.add(panel);
						}
					});
				}
				
			});
			
			
		
			this.add(panel);
			
			
	}
	

	class DoneListener implements ActionListener{
		/**
		 * actionPerformed method is overridden from ActionListener class so that when user clicks "Done",
		 * a new frame opens with the final receipt, and the user is prompted to enter a payment until
		 * total from receipt is met
		 *
		 */
		public void actionPerformed(ActionEvent click) {
			Component b = (Component) click.getSource();
			JFrame c = (JFrame) SwingUtilities.getRoot(b);
			
			JLabel ask = new JLabel("Payment: $");
			JTextField text = new JTextField(20);
			JFrame finalizeFrame = new JFrame();
			
			JPanel finalpanel = new JPanel();
			JButton payButton = new JButton("Pay");
			amountDue = new JLabel("Amount due: "+Double.toString(round(getOverTotal()-payment,2)));
			finalizeFrame.setSize(500,600);
			finalizeFrame.setTitle("Finalize Order");
			
			payButton.addActionListener(new ActionListener() {

				@Override
				/**
				 * actionPerformed method is overridden from ActionListener class so that when user clicks pay,
				 * the frame shows the remaining amount they have to pay or it thanks the user for paying when 
				 * complete total amount is entered
				 */
				public void actionPerformed(ActionEvent arg0) {
					Double input = Double.parseDouble(text.getText());
					addingReceipt.setText(null);
					addingReceipt.append("\n\t~~~~~~~~~~~~~~~~~~Final Order~~~~~~~~~~~~~~~~~~");
					addingReceipt.append(getReceipt());
					payment += input;
					if (payment<round(getOverTotal(),2)) {
						amountDue.setText("Insufficient Funds! Amount due: "+Double.toString(round(getOverTotal()-payment,2)));
					}
					//user will be given their change, displayed on receipt
					if(payment>=round(getOverTotal(),2)) {
						addingReceipt.setText(null);
						addingReceipt.append("\n\t~~~~~~~~~~~~~~~~~~Final Order~~~~~~~~~~~~~~~~~~");
						addingReceipt.append(getReceipt());
						addingReceipt.append("\n\n\t\tPayment: $"+payment
								+ "\n\t\tChange: $"+round(payment-round(getOverTotal(),2),2)
								+"\n\n\t~~~~~~~~~~~~~~~~~~THANK YOU!~~~~~~~~~~~~~~~~~~");
			
						amountDue.setText("Thank You!");
						finalpanel.remove(payButton);
						finalpanel.remove(ask);
						finalpanel.remove(text);
						//add "Exit" Button ONLY when user enters sufficient amount
						JButton exitButton = new JButton("Exit");
						exitButton.addActionListener(new ActionListener() {
							/**
							 * actionPerformed method is overridden from ActionListener class so that when user clicks "Exit"
							 * the frame closes, and program stops running
							 * 
							 */
							public void actionPerformed(ActionEvent click) {
								Component b = (Component) click.getSource();
								JFrame c = (JFrame) SwingUtilities.getRoot(b);
								c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								finalizeFrame.setVisible(false);
							}
						});
						finalpanel.add(exitButton);
						
					}
				}
			});
			finalpanel.add(amountDue);
			addingReceipt.setEditable(false);//no adding text to receipt
			scroll = new JScrollPane(addingReceipt); //adds scrollbar if necessary
			finalpanel.add(scroll);
			finalpanel.add(ask);
			finalpanel.add(text);
			finalpanel.add(payButton);
			finalizeFrame.add(finalpanel);
			c.setVisible(false);
			finalizeFrame.setVisible(true);
			finalizeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
				
			}
		}
	}

