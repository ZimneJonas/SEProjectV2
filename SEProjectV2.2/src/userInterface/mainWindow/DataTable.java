package userInterface.mainWindow;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//TableModel that returns the actual class and not String
//May or may not be needed
class MyModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	//TODO Insert actual class order
	Class[] columnClass= new Class[] {String.class, Integer.class, String.class, Integer.class, Float.class};
	
	MyModel(String[] columNames, int x){
		super(columNames, x);
	}
	@Override
	public Class getColumnClass(int column){
		return columnClass[column];
	}
}

public class DataTable extends JTable{
	

	private static final long serialVersionUID = 1L;

	static String[] columNames = {"Produktbezeichnung",
			"Anzahl",
			"Kategorie",
			"Stückpreis(in Cent)",
			"Lagernummer",
			"Gewicht"};
	
	static Mymodel model= new MyModel( columNames, 0 );
    
	public DataTable() {
		super(model);
		setNewDatabase();
		//TODO Open Editing frame with correct values
		addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent e) { 
	        	if ((e.getClickCount()==2) && (((DataTable) e.getSource()).getSelectedRow()!=-1)) {
	            		new EditProduct();
	          } 
	          }
	        }); 
    }
	
	
	public void setNewDatabase(){
		model.setRowCount(0);
		ArrayList<String> databaseAsString = start.Main.List.databaseAsString;
		String[] showMe = new String[6];
		for (int ii = 0; databaseAsString.size()/6>ii;ii++) {
			for (int kk=0; kk<6;kk++) {
				showMe[kk] = databaseAsString.get(ii*6+kk);
			}
			model.addRow(showMe);
		}		
	}
}
 
