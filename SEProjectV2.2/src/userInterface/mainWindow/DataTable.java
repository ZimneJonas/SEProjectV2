package userInterface.mainWindow;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class DataTable extends JTable{
	

	private static final long serialVersionUID = 1L;

	static String[] columNames = {"Produktbezeichnung",
			"Anzahl",
			"Kategorie",
			"Stückpreis(in Cent)",
			"Lagernummer",
			"Lagernummer"};
	
	final static DefaultTableModel model = new DefaultTableModel( columNames, 0 );
    
	public DataTable() {
		super(model);
		setNewDatabase();	
    }
	
	
	public void setNewDatabase(){
		ArrayList<String> databaseAsString = start.Main.List.databaseAsString;
		Object[] showMe = new String[6];
		for (int ii = 0; databaseAsString.size()>ii;ii++) {
			for (int kk=0; kk<6;kk++) {
				showMe[kk] = databaseAsString.get(ii*6+kk);
			}
			model.addRow(showMe);
		}		
	}
}
 
