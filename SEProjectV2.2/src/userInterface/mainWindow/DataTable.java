package userInterface.mainWindow;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.table.*;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//TableModel that returns the actual class and not String
//May or may not be needed


public class DataTable extends JTable{
	

	private static final long serialVersionUID = 1L;

	static String[] columNames = {"Produktbezeichnung",
			"Anzahl",
			"Kategorie",
			"Stückpreis(in Cent)",
			"Lagernummer",
			"Gewicht"};
	
	public static DefaultTableModel model= new DefaultTableModel( columNames, 0 );
	ArrayList<JCheckBox> searchCheckboxes = new ArrayList<>();
	TableRowSorter sorter;
    
	public DataTable() {
		
		super(model);
		sorter=new TableRowSorter<>(model);
		this.setRowSorter(sorter);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTableHeader().setReorderingAllowed(false);
		//setNewDatabase();
		//TODO Open Editing frame with correct values
 		addMouseListener(new MouseAdapter() { 
 	          public void mousePressed(MouseEvent e) { 
 	        	int row = ((DataTable) e.getSource()).getSelectedRow();
 	        	if ((e.getClickCount()==2) && (row!=-1)) {
 	        		userInterface.editProductInterface.EditProduct editProduct = new userInterface.editProductInterface.EditProduct();
 	        		editProduct.addProduct.setText("Speichern");
 	        		editProduct.editProduct(row);
 	        	} 
 	          }
 	        }); 
    }
	
	
//	public void setNewDatabase(){
//		model.setRowCount(0);
//		ArrayList<String> databaseAsString = start.Main.List.databaseAsString;
//		String[] showMe = new String[6];
//		for (int ii = 0; databaseAsString.size()/6>ii;ii++) {
//			for (int kk=0; kk<6;kk++) {
//				showMe[kk] = databaseAsString.get(ii*6+kk);
//			}
//			model.addRow(showMe);
//		}
//	}
	@Override	
	public boolean isCellEditable(int row, int column) {
	       return false;
	    
	}
	
	@Override
	// returns selected checkboxes
	public int[] getSelectedColumns() {
		ArrayList<Integer> indexesOfSelected = new ArrayList<Integer>();
		for (int i = 0; i < this.searchCheckboxes.size(); ++i) {
			JCheckBox box = this.searchCheckboxes.get(i);
			if (box.isSelected()) {
				indexesOfSelected.add(i);
			}
		}
		
		int[] rv = new int[indexesOfSelected.size()];
		// java is stupid
		for (int i = 0; i < rv.length; i++) {
			rv[i] = indexesOfSelected.get(i);
		}
		return rv;

	}

	ArrayList<JCheckBox> getCheckboxes() {
		return this.searchCheckboxes;
	}
	
	/**
	/Returns the shelf number at a row of a table
	*/
	String get_shelf(int row) {
 		Object rv= this.getValueAt(row, 4);
 		return ((String) rv).substring(0,3);
 	}
	
}
