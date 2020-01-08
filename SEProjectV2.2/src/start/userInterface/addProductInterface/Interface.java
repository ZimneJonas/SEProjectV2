package start.userInterface.addProductInterface;

import java.awt.*;
import java.awt.event.*;


@SuppressWarnings("serial")
public class Interface extends Frame implements ActionListener {
	Button addProduct;
	TextField descriptionTF, weightTF, priceTF, newCategoryTF, stocknumberTF, quantityTF;
	Label stocknumberNG;
	GridBagConstraints myGBC;
	GridBagLayout myGBL;
	

	
	public Interface() {
		super ("Produkt hinzufuegen"); // set title
		myGBL = new GridBagLayout ();
		setLayout(myGBL);
		//f = new Frame();
		
		myGBC = new GridBagConstraints();
		setVisible(true);	
		
		// generally
		myGBC.fill = GridBagConstraints.HORIZONTAL;
		myGBC.insets = new Insets(10,10,10,10) ; // distance to line: 5 above, 5 left, 5 below, 5 right 
		
		// Button addProduct
		myGBC.gridx = 2;
		myGBC.gridy = 10;
		myGBC.gridwidth = 2; // width of the element
		myGBC.gridheight = 1; // height of the element
		addProduct = new Button ("Produkt hinzufügen");
		addProduct.setBackground(Color.green);
		addProduct.addActionListener(this);
		myGBL.setConstraints(addProduct, myGBC);
		add(addProduct);
		
		// description
		myGBC.gridx = 0;
		myGBC.gridy = 0;
		myGBC.gridwidth = 2; 
		myGBC.gridheight = 1; 
		Label description = new Label ("Produktbezeichnung: ");
		myGBL.setConstraints(description, myGBC);
		add(description);
		
		myGBC.gridx = 2;
		myGBC.gridy = 0;
		myGBC.gridwidth = 2; 
		myGBC.gridheight = 1; 
		descriptionTF = new TextField (50);
		myGBL.setConstraints(descriptionTF, myGBC);
		add(descriptionTF);
		
		// weight
		myGBC.gridx = 0;
		myGBC.gridy = 1;
		myGBC.gridwidth = 2; 
		myGBC.gridheight = 1; 
		Label weight = new Label ("Gewicht (in g): ");
		myGBL.setConstraints(weight, myGBC);
		add(weight);
		
		myGBC.gridx = 2;
		myGBC.gridy = 1;
		myGBC.gridwidth = 1; 
		myGBC.gridheight = 1;
		weightTF = new TextField (6);
		myGBL.setConstraints(weightTF, myGBC);
		add(weightTF);  
		
		// price
		myGBC.gridx = 0;
		myGBC.gridy = 2;
		myGBC.gridwidth = 2; 
		myGBC.gridheight = 1; 
		Label price = new Label ("Stueckpreis(in Cent): ");
		myGBL.setConstraints(price, myGBC);
		add(price);
		
		myGBC.gridx = 2;
		myGBC.gridy = 2;
		myGBC.gridwidth = 2; 
		myGBC.gridheight = 1;
		priceTF = new TextField (50);
		myGBL.setConstraints(priceTF, myGBC);
		add(priceTF);
		
		// category
		myGBC.gridx = 0;
		myGBC.gridy = 3;
		myGBC.gridwidth = 2; 
		myGBC.gridheight = 1; 
		Label category = new Label ("Kategorie: ");
		myGBL.setConstraints(category, myGBC);
		add(category);
		
		// new category
		myGBC.gridx = 0;
		myGBC.gridy = 4;
		myGBC.gridwidth = 2; 
		myGBC.gridheight = 1; 
		Label newCategory = new Label ("   neue Kategorie: ");
		myGBL.setConstraints(newCategory, myGBC);
		add(newCategory);
		
		myGBC.gridx = 2;
		myGBC.gridy = 4;
		myGBC.gridwidth = 2; 
		myGBC.gridheight = 1;
		newCategoryTF = new TextField (50);
		myGBL.setConstraints(newCategoryTF, myGBC);
		add(newCategoryTF);
		
		// stocknumber
		myGBC.gridx = 0;
		myGBC.gridy = 5;
		myGBC.gridwidth = 2; 
		myGBC.gridheight = 1; 
		Label stocknumber = new Label ("Lagernummer: ");
		myGBL.setConstraints(stocknumber, myGBC);
		add(stocknumber);
		
		myGBC.gridx = 2;
		myGBC.gridy = 5;
		myGBC.gridwidth = 1;  // enough space?
		myGBC.gridheight = 1;
		stocknumberTF = new TextField (6);
		myGBL.setConstraints(stocknumberTF, myGBC);
		add(stocknumberTF);
		
		myGBC.gridx = 3;
		myGBC.gridy = 5;
		myGBC.gridwidth = 2; 
		myGBC.gridheight = 1; 
		stocknumberNG = new Label ("");
		myGBL.setConstraints(stocknumberNG, myGBC);
		add(stocknumberNG);
		
		// quantity
		myGBC.gridx = 0;
		myGBC.gridy = 6;
		myGBC.gridwidth = 2; 
		myGBC.gridheight = 1; 
		Label quantity = new Label ("Anzahl: "); // get.Quantity
		myGBL.setConstraints(quantity, myGBC);
		add(quantity);
		
		myGBC.gridx = 2;
		myGBC.gridy = 6;
		myGBC.gridwidth = 1; 
		myGBC.gridheight = 1;
		quantityTF = new TextField (3);
		myGBL.setConstraints(quantityTF, myGBC);
		add(quantityTF);
		
		pack();
		
		this.addWindowListener(new WindowAdapter() {		//close window without saving
			public void windowClosing(WindowEvent we) {  
				dispose();  
				} 
			}
		);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == addProduct )
		{
			//get Input from Textfields
			String description = descriptionTF.getText();
			String newCategory = newCategoryTF.getText();
			String stocknumber = stocknumberTF.getText();
			String price = priceTF.getText();
			String weight = weightTF.getText();
			String quantity = quantityTF.getText();
			
			//check if Input is valid
			boolean descriptionOK;
			boolean newCategoryOK;
			boolean stocknumberOK;
			boolean priceOK;
			boolean weightOK;
			boolean quantityOK;
			
			//descriptionOK
			if (description.length() <= 256) {descriptionOK = true;} else descriptionOK = false;

			//newCategoryOK
			if (newCategory.length() <= 256) {
				newCategoryOK = true;
			}
				/*if (start.userInterface.changeCategory.Categories.CategoryList.contains(newCategory) == false) {
					start.userInterface.changeCategory.Categories Cat = new start.userInterface.changeCategory.Categories(newCategory);
				} //else print: schon vorhanden
				newCategoryOK = true;} */
			else newCategoryOK = false;
			
			//stocknumberOK
			if (stocknumber.length() == 6) {
				stocknumberNG.setText("");
				stocknumberOK = isInt(stocknumber);
			}
			 else {
				 stocknumberNG.setText("6- stellige Zahl");
				 stocknumberOK = false;
			 }
			
		 	//priceOK
		 	priceOK = isInt(price);
			
			//weightOK
		 	weightOK = isInt(weight);
		 	
		 	//quantityOK
		 	quantityOK = isInt(quantity);
		 	


		 	System.out.println(descriptionOK);
		 	System.out.println(newCategoryOK);
		 	System.out.println(stocknumberOK);
		 	System.out.println(priceOK);
		 	System.out.println(weightOK);
		 	System.out.println(quantityOK);
		 	
		 	if (descriptionOK&newCategoryOK&stocknumberOK&priceOK&weightOK &quantityOK == true) {
		 		System.out.println(description + weight + price+ newCategory+ stocknumber+ quantity);
		 	}
			//start.database.singleProduct.ProductData eins = new start.database.singleProduct.Product(description, weight2,weight2,weight2,weight2, newCategory);
					//(description, weight, price, newCategory, stocknumber, quantity);
			//start.database.allProducts.AllProducts.addProduct();
			//this.dispose();
		}
		//void checkInput(String name, String weight, String amount, String price, String number, String category);
	}
		boolean isInt(String zahl) {
			boolean bool;
		 	try {
		 		int zahl2 = Integer.parseInt(zahl);
		 		bool =  true;// else: Zahl zu groß	
		 		}
		 	catch(NumberFormatException ee) {
		 		bool = false; //print: keine Buchstaben		
		 	}
		 	return bool;
		}
	

}
