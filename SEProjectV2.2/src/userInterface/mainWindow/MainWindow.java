package userInterface.mainWindow;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class MainWindow extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	Layout mainLayout;
	userInterface.editProductInterface.EditProduct editProduct;
	userInterface.changeCategory.ChangeCategory changeCategory;
	
	public MainWindow() {
		super("penfactory Lagerverwaltung");
		setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(10000,10000);//=always full screen
		
		Container c = getContentPane();
		
		mainLayout = new Layout();
		
		//SEARCH AND FILTER
		 mainLayout.termTF.getDocument().addDocumentListener(new DocumentListener() {
			// Refilter Table after each change on searchfield
			@Override
			// when new symbol is typed
			public void insertUpdate(DocumentEvent e) {
				search(searchfield.getText());
			}

			@Override
			// when symbol is deleted
			public void removeUpdate(DocumentEvent e) {
				search(searchfield.getText());
			}

			@Override
			// other changes(pasted text, etc.)
			public void changedUpdate(DocumentEvent e) {
				search(searchfield.getText());
			}

			public void search(String str) {
				// show all rows
				if (str.length() == 0) {
					table.sorter.setRowFilter(null);
				} 
				// set filter to the string, case insensitive
				else {
					mainLayout.table.sorter.setRowFilter(RowFilter.regexFilter(("(?i)" + str), 
            mainLayout.table.getSelectedColumns()));
					
					
				}

			}
		});
		c.add(mainLayout, BorderLayout.NORTH);
		
		mainLayout.search.addActionListener(this);
		mainLayout.addProduct.addActionListener(this);
		mainLayout.editCategory.addActionListener(this);

		
		this.setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent event) {

		//mainFrame Events
		if(event.getSource() == mainLayout.addProduct){ //Opens Add Product Interface
			editProduct = new userInterface.editProductInterface.EditProduct();
			editProduct.addProduct.addActionListener(this);
			
		} else if(event.getSource() == mainLayout.editCategory) { //Opens changeCategory Interface
			changeCategory = new userInterface.changeCategory.ChangeCategory();
			changeCategory.deleteCategory.addActionListener(this);
			changeCategory.addCat.addActionListener(this);
			
		} else if(event.getSource() == mainLayout.search) { //Search in Table (Probably not used)
			//TODO start		
		}
		
		//editProduct Events
		if (editProduct != null) {
			if(event.getSource() == editProduct.addProduct) { //Add Product to Database&Table
				editProduct.addProductToShelf();
				mainLayout.refreshTable();
			}	
		} 

		//changeCategory Events
		if (changeCategory != null) {
			if(event.getSource() == changeCategory.deleteCategory) { //Delete Category to Database
			//TODO deleteCategory
			} else if(event.getSource() == changeCategory.addCat) { //Add Category
				try {
					start.Main.List.addCategory(changeCategory.addCategoryTF.getText());
				} catch (Exception e) {
					new exceptions.Exception(e.getMessage());
				}
			}
			changeCategory.refreshList();	
		}
		System.out.println(event.getActionCommand());
	}
	
}
