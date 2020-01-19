package userInterface.changeCategory;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.List;
import java.awt.TextField;
import javax.swing.JFrame;

/**
 * This class sets the interface for the ChangeCategery window.
 * The window contains two buttons (add delete), a textfield, and a List where all existing categories are displayed.
 * @author Rita
 *
 */

@SuppressWarnings("serial")
public class Interface extends JFrame{
	
	/**
	 * button delete category
	 * 
	 */
	public Button deleteCategory;
	/**
	 * Button add category
	 */
	public Button addCat;
	
	/**
	 * List of existing categories
	 */
	public List list;
	/**
	 * textfield
	 */
	public TextField addCategoryTF;
	
	
	public Interface() {
		super ("Kategorien"); // set title
		this.setResizable(false);
		GridBagLayout myGBL = new GridBagLayout ();
		setLayout(myGBL);
		
		GridBagConstraints myGBC = new GridBagConstraints();
		setVisible (true);
		
		// generally
		myGBC.insets = new Insets(5,5,5,5) ; // distance to line: 5 above, 5 left, 5 below, 5 right 
		
		// Button deleteProduct
		myGBC.gridx = 3;
		myGBC.gridy = 1;
		myGBC.gridwidth = 2; // width of the element
		myGBC.gridheight = 1; // height of the element
		deleteCategory = new Button ("loeschen");
		deleteCategory.setBackground(Color.red);
		myGBL.setConstraints(deleteCategory, myGBC);
		add(deleteCategory, myGBC);
		
		// Button okay
		myGBC.gridx = 3;
		myGBC.gridy = 6;
		myGBC.gridwidth = 1; 
		myGBC.gridheight = 1; 
		addCat = new Button ("Hinzufuegen");
		addCat.setBackground(Color.green);
		myGBL.setConstraints(addCat, myGBC);
		add(addCat, myGBC);
	
		myGBC.gridx = 0;
		myGBC.gridy = 0;
		myGBC.gridwidth = 2; // width of the element
		myGBC.gridheight = 2; // height of the element
		list = new List ();
		myGBL.setConstraints(list, myGBC);
		add(list, myGBC);
		
		// add category
		myGBC.gridx = 0;
		myGBC.gridy = 5;
		myGBC.gridwidth = 2; 
		myGBC.gridheight = 1; 
		Label addCategory = new Label ("Kategorie hinzufuegen:");
		myGBL.setConstraints(addCategory, myGBC);
		add(addCategory, myGBC);
		
		myGBC.gridx = 0;
		myGBC.gridy = 6;
		myGBC.gridwidth = 2; 
		myGBC.gridheight = 1;
		addCategoryTF = new TextField (3);
		myGBL.setConstraints(addCategoryTF, myGBC);
		add(addCategoryTF, myGBC);
		
		pack();
	}
}
