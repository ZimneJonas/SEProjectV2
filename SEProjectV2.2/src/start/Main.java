/*

package start;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import userInterface.mainWindow.DataTable;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.table.DefaultTableModel;


public class Main {

	public static ArrayList<String> categoryList = new ArrayList<String>();
	public static DataTable table = new DataTable();
	
	
	public static void main(String[] args) {	
		start.DataHandler.make_directory();
		table = new DataTable();
		
		
		final long time_interval = +60000; //in miliseconds
		new Timer().schedule(new TimerTask() {
		      @Override
		      public void run() {
		          DataHandler.save((DefaultTableModel) table.getModel());

		      }
		  },0 , time_interval);
		  
		  Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		          public void run() {
		   DataHandler.save((DefaultTableModel) table.getModel());
		              System.out.println("In shutdown hook");
		           }
		      }, "Shutdown-thread"));
		
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new userInterface.mainWindow.MainWindow (); 
			}
			});
	}
}


*/












package start;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import userInterface.mainWindow.DataTable;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.table.DefaultTableModel;


/**
 * Main Class, which initializes mainFrame, the categoryList and the DataTable.
 * The Main Class also saves every minute and after the Program is closed.  
 * @author Dalya
 *
 */
public class Main {

  public static ArrayList<String> categoryList = new ArrayList<String>();
  public static ArrayList<Integer> productsInCategory= new ArrayList<Integer>();
  public static DataTable table/* = new DataTable()*/;
  
  
  public static void main(String[] args) {  
    start.DataHandler.make_directory();
    start.DataHandler.loadCategories();
    table = new DataTable();
    
    
    final long time_interval = +60000; //in miliseconds
    new Timer().schedule(new TimerTask() {
          @Override
          public void run() {
              DataHandler.save((DefaultTableModel) table.getModel());

          }
      },0 , time_interval);
      
      Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
              public void run() {
       DataHandler.save((DefaultTableModel) table.getModel());
               }
          }, "Shutdown-thread"));
    
    
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new userInterface.mainWindow.MainWindow (); 
      }
      });
  }
}











