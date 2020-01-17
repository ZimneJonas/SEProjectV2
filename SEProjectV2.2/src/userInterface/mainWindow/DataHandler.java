
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.table.DefaultTableModel;

final class DataHandler {
	final public static String HOMEDIR = System.getProperty("user.home"); // home directory on local pc
	final public static String NEWLINE = System.getProperty("line.separator");
	public static String MYPATH; // path to file
	private static String filename = "\\table_data.txt";

	static void make_directory() {
		MYPATH = (HOMEDIR + "\\penfactory");
		new File(MYPATH).mkdir();

	}

	static void save(DefaultTableModel model) {
		Path path = Paths.get(MYPATH + filename);
		System.out.println(HOMEDIR);
		System.out.println(MYPATH + filename);
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			for (int row = 0; row < model.getRowCount(); row++) {
				String content = "";
				for (int column = 0; column < model.getColumnCount(); column++) {
					content += model.getValueAt(row, column).toString() + ";";

					System.out.println(content);

				}
				writer.write(content + NEWLINE);

			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static DefaultTableModel load() {
		String[] columnNames = { "First Name", "Last Name", "Sport" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		File file = new File(MYPATH + filename);
		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			while ((line = br.readLine()) != null) {
				System.out.println("!!" + line);
				String[] data = line.split(";");
				model.addRow(data);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

}
