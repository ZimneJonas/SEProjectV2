package start;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import userInterface.mainWindow.DataTable;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

	public static ArrayList<String> categoryList = new ArrayList<String>();
	public static DataTable table = new DataTable();
	
	public static void main(String[] args) {
		final long time_interval = +60000; //in miliseconds
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new userInterface.mainWindow.MainWindow (); 
			}
			});
		new Timer().schedule(new TimerTask() {
		    @Override
		    public void run() {
		        DataHandler.save(model);//TODO here comes .....table.getModel();
		    }
		},0 , time_interval);
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
        		public void run() {
			DataHandler.save(table.getModelmodel);//TODO here comes ....table.getModel();
            		System.out.println("In shutdown hook");
       			 }
   		 }, "Shutdown-thread"));
	}
}
