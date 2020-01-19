package userInterface.mainWindow;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
/**
 * The class MainWindow describes the layout for the main window of the GUI.
 * To construct the GUI it uses the JFrame. For the buttons it uses the ActionListener.
 * MainWindow uses JFrame to set a title, tell the used Layout and the close operation.
 * The main window is visible.
 * @author Jonas
 */
public class MainWindow extends JFrame implements ActionListener{
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * The Layout of window
	 */
	static Layout mainLayout;
	/**
	 * subwindow for adding/editing/deleting products
	 */
	userInterface.editProductInterface.AddProduct addProduct;
	/**
	 * subwindow for adding/deleting categories
	 */
	
	userInterface.changeCategory.ChangeCategoryClass changeCategory;
	
	/**
	 * constructs the Main window.
	 * 
	 */
	public MainWindow() {
		super("penfactory Lagerverwaltung");
		setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);//=always full screen
		
		Container c = getContentPane();
		
		
		mainLayout = new Layout();
		c.add(mainLayout, BorderLayout.NORTH);
		
		mainLayout.addProduct.addActionListener(this);
		mainLayout.editCategory.addActionListener(this);
		
		//SEARCH AND FILTER
		 mainLayout.termTF.getDocument().addDocumentListener(new DocumentListener() {
			// Refilter Table after each change on searchfield
			@Override
			// when new symbol is typed
			public void insertUpdate(DocumentEvent e) {
				search(mainLayout.termTF.getText());
			}

			@Override
			// when symbol is deleted
			public void removeUpdate(DocumentEvent e) {
				search(mainLayout.termTF.getText());
			}

			@Override
			// other changes(pasted text, etc.)
			public void changedUpdate(DocumentEvent e) {
				search(mainLayout.termTF.getText());
			}

			/**
			 * reduces table view to only rows that contain the inputed string.
			 * The search is reduced to the selected checkboxes.
			 * This takes advantage of the varrargs type.
			 */
			public void search(String str) {
				// show all rows
				if (str.length() == 0) {
					start.Main.table.sorter.setRowFilter(null);
				} 
				// set filter to the string, case insensitive
				else {
					start.Main.table.sorter.setRowFilter(RowFilter.regexFilter(("(?i)" + str), start.Main.table.getSelectedColumns()));
				}

			}
		});
		this.setVisible(true);
	}	

	/**
	 * The actionsPerformed from action Listener opens the new window AddProduct or ChangeCategory after
	 * pressing the appropriate button.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {

		//mainFrame Events
		if(event.getSource() == mainLayout.addProduct){ //Opens Add Product Interface
			addProduct = new userInterface.editProductInterface.AddProduct();
		} else if(event.getSource() == mainLayout.editCategory) { //Opens changeCategory Interface
			changeCategory = new userInterface.changeCategory.ChangeCategoryClass();
		}	
		System.out.println(event.getActionCommand()+" pressed in MainFrame");
	}
}
	
		
