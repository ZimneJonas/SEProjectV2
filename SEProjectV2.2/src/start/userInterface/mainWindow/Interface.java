package start.userInterface.mainWindow;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.List;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
class Interface extends Frame implements ActionListener{
	
	private Button addProduct;
	private Button editCategory;
	private Button search;

	public Interface() {
		super ("penfactory Lagerverwaltung"); // set title
		GridBagLayout myGBL = new GridBagLayout ();
		setLayout(myGBL);
		
		GridBagConstraints myGBC = new GridBagConstraints();
		setVisible (true);
		
		// generally
		myGBC.fill = GridBagConstraints.HORIZONTAL;
		myGBC.insets = new Insets(5,5,5,5) ; // distance to line: 5 above, 5 left, 5 below, 5 right 
		
		// button addProduct
		myGBC.gridx = 4;
		myGBC.gridy = 0;
		myGBC.gridwidth = 2; // width of the element
		myGBC.gridheight = 2; // height of the element
		addProduct = new Button ("Produkt hinzufuegen");
		addProduct.setBackground(Color.green);
		addProduct.addActionListener(this);
		myGBL.setConstraints(addProduct, myGBC);
		add(addProduct);
		
		// button edit category
		myGBC.gridx = 4;
		myGBC.gridy = 4;
		myGBC.gridwidth = 2;
		myGBC.gridheight = 2; 
		editCategory = new Button ("Kategorien bearbeiten");
		editCategory.setBackground(Color.green);
		editCategory.addActionListener(this);
		myGBL.setConstraints(editCategory, myGBC);
		add(editCategory);
		
		// button search
		myGBC.gridx = 3;
		myGBC.gridy = 5;
		myGBC.gridwidth = 1; 
		myGBC.gridheight = 1; 
		search = new Button ("Suche");
		search.setBackground(Color.cyan);
		search.addActionListener(this);
		myGBL.setConstraints(search, myGBC);
		add(search);
	
		// term
		myGBC.gridx = 0;
		myGBC.gridy = 0;
		myGBC.gridwidth = 2; 
		myGBC.gridheight = 1; 
		Label term = new Label ("Begriff:");
		myGBL.setConstraints(term, myGBC);
		add(term);
		
		myGBC.gridx = 2;
		myGBC.gridy = 0;
		myGBC.gridwidth = 2; 
		myGBC.gridheight = 1;
		TextField termTF = new TextField (50);
		myGBL.setConstraints(termTF, myGBC);
		add(termTF);
		
		// description
		myGBC.gridx = 0;
		myGBC.gridy = 3;
		myGBC.gridwidth = 2; 
		myGBC.gridheight = 1; 
		Label description = new Label ("Produktbezeichnung: ");
		myGBL.setConstraints(description, myGBC);
		add(description);

		// weight
		myGBC.gridx = 0;
		myGBC.gridy = 4;
		myGBC.gridwidth = 2; 
		myGBC.gridheight = 1; 
		Label weight = new Label ("Gewicht (in g): ");
		myGBL.setConstraints(weight, myGBC);
		add(weight);
		
		// price
		myGBC.gridx = 0;
		myGBC.gridy = 5;
		myGBC.gridwidth = 2; 
		myGBC.gridheight = 1; 
		Label price = new Label ("Stückpreis(in Cent): ");
		myGBL.setConstraints(price, myGBC);
		add(price);
		
		// category
		myGBC.gridx = 0;
		myGBC.gridy = 6;
		myGBC.gridwidth = 2; 
		myGBC.gridheight = 1; 
		Label category = new Label ("Kategorie: ");
		myGBL.setConstraints(category, myGBC);
		add(category);
		
		// stocknumber
		myGBC.gridx = 0;
		myGBC.gridy = 7;
		myGBC.gridwidth = 2; 
		myGBC.gridheight = 1; 
		Label stocknumber = new Label ("Lagernummer: ");
		myGBL.setConstraints(stocknumber, myGBC);
		add(stocknumber);

		// quantity
		myGBC.gridx = 0;
		myGBC.gridy = 8;
		myGBC.gridwidth = 2; 
		myGBC.gridheight = 1; 
		Label quantity = new Label ("Anzahl: "); // get.Quantity
		myGBL.setConstraints(quantity, myGBC);
		add(quantity);
		
		myGBC.gridx = 0;
		myGBC.gridy = 9;
		myGBC.gridwidth = 2;
		myGBC.gridheight = 2;
		List list = new List (6);
		list.add("Zeile1"); // example for the space for the table
		list.add("Zeile2");
		list.add("Zeile3");
		list.add("Zeile4");
		list.add("Zeile5");
		list.add("Zeile6");
		myGBL.setConstraints(list, myGBC);
		add(list);
		
		pack();

	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == addProduct){
			start.userInterface.addProductInterface.AddProduct addProductInterface = new start.userInterface.addProductInterface.AddProduct ();
			
		} else if(event.getSource() == editCategory) {
			start.userInterface.changeCategory.ChangeCategory changeCategoryInterface = new start.userInterface.changeCategory.ChangeCategory ();
			
		} else if(event.getSource() == search) {
			
			
		}
		
	}
}
