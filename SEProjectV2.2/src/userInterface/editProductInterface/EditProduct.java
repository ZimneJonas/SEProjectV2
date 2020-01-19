package userInterface.editProductInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Exception;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;


/**
 * EditProduct extends AddProduct. It gets the selected Row in the DataTable.
 * The Data from the selected Row is loaded in the TextFields to make Changes easy.
 * It implements two more TextFields to add or remove a certain amount.
 * 
 * @author Jonas
 * 
 *
 */
@SuppressWarnings("serial")
public class EditProduct extends AddProduct implements ActionListener{
  int row;
	/**
	 * Button for deleting a selected Button
	 */
  JButton deleteProduct;
  Label currentQuantity, quantityAdd, quantitySub;
	/**
	 * TextFields to add or remove
	 */
  TextField quantityAddTF, quantitySubTF;
  
  /**
	 * 
	 * Constructs the EditProduct Frame. Loads the Values of the DataTable
	 * @param row to be edited
	 */
public EditProduct(int row) {
	super();
	//this.setName("Produkt veraendern");
	this.row=row;
	this.addProduct.setText("Speichern");
	quantityTF.setVisible(false);
	quantityFM.setVisible(false);
	
     
	myGBC.gridx = 0;
	myGBC.gridy = 7;
	myGBC.gridwidth = 2; // width of the element
	myGBC.gridheight = 1; // height of the element
	deleteProduct = new JButton ("Produkt loeschen");
	deleteProduct.setBackground(Color.red);
	myGBL.setConstraints(deleteProduct, myGBC);
	add(deleteProduct);
	deleteProduct.addActionListener(this);
	   
	 myGBC.gridx = 1;
	 myGBC.gridy = 5;
	 myGBC.gridwidth = 1; 
	 myGBC.gridheight = 1; 
	 currentQuantity = new Label ("         ");
	 myGBL.setConstraints(currentQuantity, myGBC);
	 add(currentQuantity);
	 
	 myGBC.gridx = 2;
	 myGBC.gridy = 5;
	 myGBC.gridwidth = 1; 
	 myGBC.gridheight = 1; 
	 quantityAdd = new Label ("Hinzufuegen:");
	 myGBL.setConstraints(quantityAdd, myGBC);
	 add(quantityAdd);
	 
     myGBC.gridx = 3;
     myGBC.gridy = 5;
     myGBC.gridwidth = 1; 
     myGBC.gridheight = 1;
     quantityAddTF = new TextField (5);
     myGBL.setConstraints(quantityAddTF, myGBC);
     add(quantityAddTF);
	 
	 myGBC.gridx = 4;
	 myGBC.gridy = 5;
	 myGBC.gridwidth = 1; 
	 myGBC.gridheight = 1; 
	 quantitySub = new Label ("Entnehmen:");
	 myGBL.setConstraints(quantitySub, myGBC);
	 add(quantitySub);
	 
     myGBC.gridx = 5;
     myGBC.gridy = 5;
     myGBC.gridwidth = 1; 
     myGBC.gridheight = 1;
     quantitySubTF = new TextField (5);
     myGBL.setConstraints(quantitySubTF, myGBC);
     add(quantitySubTF);
     
     pack();
	 
	 this.loadValues(row);
  }
  

	/**
	 * actionPerformed implements the function of the Buttons.
	 * 
	 */

  @Override
  public void actionPerformed(ActionEvent e) {
    //editProduct Events
    System.out.println(e.getSource());
    if(e.getSource() == this.addProduct) { //speicher button
      //start.Main.table.model.removeRow(row);
      //this.addProduct();  
    //pack();
      changeProduct();
    } else if (e.getSource() == this.deleteProduct) {
      //new YesNoPopup();
      deleteProduct(row);
    }
    System.out.println(e.getActionCommand()+" pressed in MainFrame");
    
  }
  
  
  	/**
	 * loadValues takes the Index of the Selected Row and writes the Values to the corresponding.
	 * @param index of Data to be loaded 
	 */
  public void loadValues(int index) {
      
      System.out.println(row);
      descriptionTF.setText(start.Main.table.model.getValueAt(index, 0).toString());
      weightTF.setText(start.Main.table.model.getValueAt(index, 3).toString());
      stocknumberTF.setText(start.Main.table.model.getValueAt(index, 2).toString());
      priceTF.setText(start.Main.table.model.getValueAt(index, 4).toString());
      currentQuantity.setText(start.Main.table.model.getValueAt(index, 5).toString());
      categories.select(start.Main.table.model.getValueAt(index, 1).toString());
      //newCategoryTF.setText(start.Main.table.model.getValueAt(index, 2).toString());
  }
  
  	/**
	 * deleteProduct deletes the selected Row
	 * @return reply confirmation of change
	 * @param row to delete
	 */
  public int deleteProduct(int row) {
    row=start.Main.table.convertRowIndexToModel(row);
    int reply = JOptionPane.showConfirmDialog(this, "Aenderungen uebernehmen?", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);;
      if (reply == JOptionPane.YES_OPTION) {
      String category= (String) start.Main.table.getValueAt(row,1);
      int index= start.Main.categoryList.indexOf(category);
      int temp=start.Main.productsInCategory.get(index);
      start.Main.productsInCategory.set(index, temp-1);
        start.Main.table.model.removeRow(row);
        this.dispose();
      }
      return reply;
  }
  
  
  	/**
	 * checks whether the Name already exist
	 * @return true if Name is NOT in the DataTable, else false
	 * @param name String of the name TextField
	 */
 boolean checkNameInList(String name) {
	    if (!name.equals(start.Main.table.model.getValueAt(row, 0).toString())) {
	    	System.out.println("AAA");
	         return super.checkNameInList(name);
	    }
	    return true;
 }
 
	/**
	 * checks if the Number already exist
	 * @return true if Number is free, else false
	 * @param stocknumber String of the stocknumber TextField
	 */
 boolean checkStocknumberInList(String stocknumber) {
	    if (!stocknumber.equals(start.Main.table.model.getValueAt(row, 2).toString())) {
	         return (super.checkStocknumberInList(stocknumber)==true);
	    }
	    return true;
}
  
 /**
	 * Implements all Checks, gives Error messages or changes the product accordingly
	 *
	 */
  public void changeProduct() {
	  	String quantity = "";
	  	String quantityAdd = "";
	  	String quantitySub = "";
	    String name = descriptionTF.getText();
	    String weight= weightTF.getText();
	    String number= stocknumberTF.getText();
	    String price= priceTF.getText();
	    String category= categories.getSelectedItem();
	    quantityAdd = quantityAddTF.getText();
	    quantitySub = quantitySubTF.getText();
	    String quantityBefore = currentQuantity.getText();
	    
	    leereEingabeFM.setText("");
	    descriptionFM.setText("");
	    weightFM.setText(""); 
	    priceFM.setText(""); 
	    stocknumberFM.setText(""); 
	    quantityFM.setText("");
	    
	  	int beforeInt = 0;
	  	int addInt = 0;
	  	int subInt = 0;
	  	
	  	
	  	System.out.println(name);
	  	System.out.println(number);
	
	    System.out.println("1");

	    if (checkProduct(name, category, number, weight, price, quantityBefore) == true) {
	    	System.out.println("1");
		    if (quantityAdd.length() != 0 & quantitySub.length() != 0) {leereEingabeFM.setText("hinzufuegen oder entnehmen");return;}
		
		    else if (quantityAdd.length() == 0 & quantitySub.length() == 0) {quantity = quantityBefore;}
		    else {
		    	beforeInt = Integer.parseInt(quantityBefore);
		    	if (quantityAdd.length() > 0)
		        try {addInt = Integer.parseInt(quantityAdd);}
		          catch(Exception ee) {leereEingabeFM.setText("Bitte nur Zahlen eingeben");return;}
		    	if (quantitySub.length() > 0)
			        try {subInt = Integer.parseInt(quantitySub);}
			          catch(Exception ee) { leereEingabeFM.setText("Bitte nur Zahlen eingeben");return;}
		    }
		    	
	        if (subInt != 0) {
	        	int quantityInt = beforeInt-subInt;
	        	if (quantityInt < 0) {leereEingabeFM.setText("nicht genuegend Artikel vorhanden"); return;}
	        	else {
	        	quantity = ((Integer) quantityInt).toString();
	        	}
		        }
	        
	        System.out.println("11");
	        
	        if (addInt != 0) {
	        	System.out.println("2");
	        	if (checkWeightInShelf(number, weight, quantityAdd) == 1) {leereEingabeFM.setText("Regal ueberlastet"); return;}
	        	else {
	        		int quantityInt = beforeInt+addInt;
	        		quantity = ((Integer) quantityInt).toString();
	        	}
	        }

	        if (this.checkNameInList(name) == false) {descriptionFM.setText("Produktbezeichnung schon vergeben");}
	        else if (this.checkStocknumberInList(number) == false) {stocknumberFM.setText("Lagerplatz schon vergeben");}
	        else {
	        	System.out.println("3");
	        	int reply=deleteProduct(row);
	        	if (reply==JOptionPane.YES_OPTION) {
	        		String[] newRow = {name, category, number, weight, price, quantity};
	        		start.Main.table.model.addRow(newRow);
	        		int iii=start.Main.categoryList.indexOf(category);
	        		int temp=start.Main.productsInCategory.get(iii);
	        		start.Main.productsInCategory.set(iii, temp+1);
	        		this.dispose();
	        	}
		        }
		    }
  }
}
