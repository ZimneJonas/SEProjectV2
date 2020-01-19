
 package start;

 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.nio.file.Files;
 import java.nio.file.Path;
 import java.nio.file.Paths;
 import java.util.ArrayList;
 import javax.swing.table.DefaultTableModel;

/**
 * 
 * This is the class that handles all the external files and directories.
 * 
 *
 * @author Dalya
 */
 

 final public class DataHandler {
		/**
		 * Home directory on local pc
		 */
		final public static String HOMEDIR = System.getProperty("user.home"); 
		/**
		 * string for newline for current system
		 */
		final public static String NEWLINE = System.getProperty("line.separator");
		
		/**
		 * Path to file
		 */
		public static String MYPATH;
		/**
		 * String containing table data filename
		 */
		private static String filename = "\\table_data.txt";
		/**
		 * String containing category data filename
		 */
		private static String categoryfile = "\\category_data.txt";

		/**
		 * Creates a directory in the HOME directory of the local computer.
		 * 
		 */
   public static void make_directory() {
     MYPATH = (HOMEDIR + "\\penfactory");
     new File(MYPATH).mkdir();

   }
	/**
	 * Saves a TableModel as an external .txt file in the MYPATH directory. Rows are
	 * separated by "\n" Columns are separated by ";" A .txt file containing all
	 * categories and the amount of products they each contain is also created.
	 * 
	 * @param model Model to be saved
	 */
   public static void save(DefaultTableModel model) {
     File file = new File(MYPATH + filename);
     Path path = Paths.get(MYPATH + filename);
     file.setWritable(true);
     try (BufferedWriter writer = Files.newBufferedWriter(path)) {
       for (int row = 0; row < model.getRowCount(); row++) {
         String content = "";
         for (int column = 0; column < model.getColumnCount(); column++) {
           content += model.getValueAt(row, column).toString() + ";";

         }
         writer.write(content + NEWLINE);
         writer.flush();
       }
       writer.close();
     } catch (Exception e) {
       e.printStackTrace();
     }

     path = Paths.get(MYPATH + categoryfile);
     File file1 = new File(MYPATH + categoryfile);
     file1.setWritable(true);
     try (BufferedWriter writer = Files.newBufferedWriter(path)) {
       for (String content : start.Main.categoryList) {
         writer.write(content + NEWLINE);
         writer.flush();
       }
       writer.write(";" + NEWLINE);
       for (int content : start.Main.productsInCategory) {
         writer.write(content + NEWLINE);
         writer.flush();
       }
       writer.close();
     } catch (Exception e) {
       e.printStackTrace();
     }
     file.setReadOnly();
     file1.setReadOnly();
   }
	/**
	 * Loads external textfile on a DefaultTableModel.
	 * 
	 * @return TableModel to be loaded
	 */
   public static DefaultTableModel load() {
	   
     String[] columnNames = { "Produktbezeichnung", "Kategorie", "Lagernummer", "Gewicht", "Stueckpreis (Euro)",
         "Anzahl" };
     DefaultTableModel model = new DefaultTableModel(columnNames, 0);
     File file = new File(MYPATH + filename);
     String line;
     file.setReadable(true);
     try (BufferedReader br = new BufferedReader(new FileReader(file))) {
       while (((line = br.readLine()) != null) && (line.length()) > 3) { // temporary Bug fix for final line
                                         // garbage
         String[] data = line.split(";");
         model.addRow(data);
         // br.flush();
       }
       br.close();
     } catch (Exception e) {
       e.printStackTrace();
     }
     return model;
   }
	/**
	 * loads text file on start.Main.categoryList
	 */
   public static void loadCategories() {
     File file = new File(MYPATH + categoryfile);
     String line;
     try (BufferedReader br = new BufferedReader(new FileReader(file))) {
       while (((line = br.readLine()) != null) && (line.length()>1)) { // temporary Bug fix for final line
         start.Main.categoryList.add(line);
         System.out.println(line);
         // br.flush();
       }
       line=null; 
       while ((line = br.readLine()) != null) { // temporary Bug fix for final line
         int content=Integer.parseInt(line);
         start.Main.productsInCategory.add(content);
         System.out.println(line);
         // br.flush();
       }
       br.close();
     } catch (Exception e) {
       e.printStackTrace();
     }

   }
 }
 
 
 
