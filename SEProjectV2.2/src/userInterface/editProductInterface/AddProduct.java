package userInterface.editProductInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import userInterface.mainWindow.DataTable;


/**
 * 
 * AddProduct implements the AddProduct Checks. 
 * Checks include Syntax, Weight and whether the Name or Number are already used.
 * @author Johannes, Jonas
 * @see userInterface.editProductInterface.Interface
 */
public class AddProduct extends Interface implements ActionListener{

  private static final long serialVersionUID = 1L;



  /**
   * Constructs the addProduct Window
   * 
	 */
   
  // view of the window
  public AddProduct(){
    super();
    this.setDefaultCloseOperation(Interface.DISPOSE_ON_CLOSE);
    //this.setResizable(false);
    this.addProduct.addActionListener(this);
  } // addProduct

  public void actionPerformed(ActionEvent e) {
    //editProduct Events  
    if(e.getSource() == this.addProduct) { //Add Product to Database&Table
      this.addProduct();
    } 
  }  
  
  
  /**
   *  implements all the Checks and gives Error messages
   * @param name String of the name TextField
   * @param category String of the category TextField
   * @param number String of the number TextField
   * @param weight String of the weight TextField
   * @param price String of the price TextField
   * @param quantity String of the quantity TextField
   * @return true if Checks are positive, else false
   */
  
  public boolean checkProduct(String name, String category, String number, String weight, String price, String quantity) {
	  ArrayList<Integer> Fehlermeldungen = new ArrayList<Integer>();
	  
      int desc = checkProductname(name);
      if (desc == 1) {Fehlermeldungen.add(11);}//Input to Long/Small
      if (desc == 2) {Fehlermeldungen.add(12);}//wrong character

      //stocknumberOK
      int sto = checkStocknumber(number);
      if (sto == 1) {Fehlermeldungen.add(31);}//Input to Long/Small =!6
      if (sto == 2) {Fehlermeldungen.add(32);}//Letters inside
     
      //priceOK
      int pri = checkPrice(price);
      if (pri == 1) {Fehlermeldungen.add(41);}//Number to Big/Small
      if (pri == 2) {Fehlermeldungen.add(42);}
      if (pri == 2) {Fehlermeldungen.add(43);}
      //weightOK
      int wei = checkWeight(weight);
      if (wei == 1) {Fehlermeldungen.add(51);}//Wrong Input
      if (wei == 2) {Fehlermeldungen.add(52);}
      if (wei == 3) {Fehlermeldungen.add(53);}
      //quantityOK
      int qua = checkQuantity(quantity);
      if (qua == 1) {Fehlermeldungen.add(61);}//Wrong Input
      if (qua == 2) {Fehlermeldungen.add(62);}//Number to big
      
      System.out.println(Fehlermeldungen);
      
      if (Fehlermeldungen.isEmpty()) {return true;}

    if (name.length()==0|category.length()==0|number.length()==0|price.length()==0|weight.length()==0|quantity.length()==0) {
      leereEingabeFM.setText("Bitte alle Felder ausfuellen!");}
    else {
    
    if (Fehlermeldungen.contains(11)) {descriptionFM.setText("zu viele Zeichen");} //Productdescription contains more than 256 characters
    if (Fehlermeldungen.contains(12)) {descriptionFM.setText("falsches Zeichen");}
   
    if (Fehlermeldungen.contains(31)) {stocknumberFM.setText("6- stellige Zahl");}
    if (Fehlermeldungen.contains(32)) {stocknumberFM.setText("keine Buchstaben");}
    
    if (Fehlermeldungen.contains(41)) {priceFM.setText("falsches Zeichen");}
    if (Fehlermeldungen.contains(42)) {priceFM.setText("Zahl zu gross");}
    if (Fehlermeldungen.contains(43)) {priceFM.setText("zu viele Nachkommastellen");}

    if (Fehlermeldungen.contains(51)) {weightFM.setText("falsches Zeichen");} 
    if (Fehlermeldungen.contains(52)) {weightFM.setText("Zahl zu gross");} 
    if (Fehlermeldungen.contains(53)) {weightFM.setText("zu viele Nachkommastellen");} 
    
    if (Fehlermeldungen.contains(61)) {quantityFM.setText("falsches Zeichen");} 
    if (Fehlermeldungen.contains(62)) {quantityFM.setText("Zahl zu gross");}
    }
    return false;
  
  }

  
  
  
  /**
   * adds the Product from the TextFields to the Database if all Checks are positive.
   * 
   */
  
  public void addProduct() {
    //Fehlermeldungen.clear();
      
    String name = descriptionTF.getText();
    String weight= weightTF.getText();
    String quantity= quantityTF.getText();
    String number= stocknumberTF.getText();
    String price= priceTF.getText();
    String category= categories.getSelectedItem();
    
    leereEingabeFM.setText("");
    descriptionFM.setText("");
    weightFM.setText(""); 
    priceFM.setText(""); 
    stocknumberFM.setText(""); 
    quantityFM.setText("");
    
         
    if (checkProduct(name, category, number, weight, price, quantity) == true) {
         if (checkNameInList(name) == false) {descriptionFM.setText("Produktbezeichnung schon vergeben");}
         else if (checkStocknumberInList(number) == false) {stocknumberFM.setText("Lagerplatz schon vergeben");}
         else if (checkWeightInShelf(number, weight, quantity) == 1) {leereEingabeFM.setText("Regal ueberlastet");}
         else {
          String[] newRow = {name, category, number, weight, price, quantity};
          int iii=start.Main.categoryList.indexOf(category);
          int temp=start.Main.productsInCategory.get(iii);
          start.Main.productsInCategory.set(iii, temp+1);
          start.Main.table.model.addRow(newRow);
          this.dispose();
          }
      } 
  }

  /**
   * checks whether the name is in the Database
   * @return true if name is NOT used, else false
   * @param name String of the name TextField
   */
      
  boolean checkNameInList(String name) {
    for (int ii = 0; ii < start.Main.table.model.getRowCount(); ii++) { 
      String n = (String) start.Main.table.model.getValueAt(ii, 0);
      if (n.equals(name)) {return false;}
    }
    return true;
  }
  /**
   * checks whether the number is used in the Database
   * @return true if number is NOT used, else false
   * @param stocknumber String of the number TextField
   */ 
  
  boolean checkStocknumberInList(String stocknumber) {
    for (int ii = 0; ii < start.Main.table.model.getRowCount(); ii++) { 
      String n = (String) start.Main.table.model.getValueAt(ii, 2);
      if (n.equals(stocknumber)) {return false;}
    }
    return true;
  }
  
  

  
  /**
   * checks whether the new Weight fits in the shelf
   * @return false if weight fits in the given Shelf, else true
   * @param stocknumber String of the number TextField
   * @param newWeight String of the weight TextField
   * @param newQuantity String of the quantity TextField
   * 
   */
  
  int checkWeightInShelf(String stocknumber, String newWeight, String newQuantity) {
    int workload = 0;
    String shelf = stocknumber.substring(0, 3);
    int she = Integer.parseInt(shelf);
    int newQuantityInt = Integer.parseInt(newQuantity);
    int newWeightInt = parseToInt(newWeight);
  
    for (int ii = 0; ii < start.Main.table.model.getRowCount(); ii++) { 
      String n = ((String) start.Main.table.model.getValueAt(ii, 2)).substring(0, 3); //stocknumber
      int num = Integer.parseInt(n);
      if (she == num) {
        System.out.println("hier");
        int quantity = Integer.parseInt((String) start.Main.table.model.getValueAt(ii, 5));
        int weight = parseToInt((String) start.Main.table.model.getValueAt(ii, 3));
        workload += quantity*weight;
        System.out.println(weight);
        System.out.println(workload);}
    }
    System.out.println(newQuantityInt);
    System.out.println(newWeightInt);
    workload += newQuantityInt*newWeightInt;
    if (workload > 100000000) {return 1;}
    return 0;
  }
  /**
   * checks whether the name uses the right Letters
   * @return false if the Syntax is Correct, else true
   * @param name String of the name TextField
	 */
  
  private int checkProductname(String name) {
      //List<Character> Zeichen = new List<Character>();
      ArrayList<Character> Zeichen = new ArrayList<Character>();
      Zeichen.add('a'); Zeichen.add('b'); Zeichen.add('c'); Zeichen.add('d'); Zeichen.add('e'); Zeichen.add('f');
      Zeichen.add('g'); Zeichen.add('h'); Zeichen.add('i'); Zeichen.add('j'); Zeichen.add('k'); Zeichen.add('l'); 
      Zeichen.add('m'); Zeichen.add('n'); Zeichen.add('o'); Zeichen.add('p'); Zeichen.add('q'); Zeichen.add('r');
      Zeichen.add('s'); Zeichen.add('t'); Zeichen.add('u'); Zeichen.add('v'); Zeichen.add('w'); Zeichen.add('x');
      Zeichen.add('y'); Zeichen.add('z');
      Zeichen.add('A'); Zeichen.add('B'); Zeichen.add('C'); Zeichen.add('D'); Zeichen.add('E'); Zeichen.add('F');
      Zeichen.add('G'); Zeichen.add('H'); Zeichen.add('I'); Zeichen.add('J'); Zeichen.add('K'); Zeichen.add('L');
      Zeichen.add('M'); Zeichen.add('N'); Zeichen.add('O'); Zeichen.add('P'); Zeichen.add('Q'); Zeichen.add('R');
      Zeichen.add('S'); Zeichen.add('T'); Zeichen.add('U'); Zeichen.add('V'); Zeichen.add('W'); Zeichen.add('X');
      Zeichen.add('Y'); Zeichen.add('Z');
      Zeichen.add('0'); Zeichen.add('1'); Zeichen.add('2'); Zeichen.add('3'); Zeichen.add('4'); Zeichen.add('5');
      Zeichen.add('6'); Zeichen.add('7'); Zeichen.add('8'); Zeichen.add('9');
      Zeichen.add('-'); Zeichen.add('!'); Zeichen.add('?'); Zeichen.add('.'); Zeichen.add('('); Zeichen.add(')');
      Zeichen.add('_');

      //char[] chara = name.toCharArray();
      for (char ch: name.toCharArray()) {
        if (Zeichen.contains(ch) == false) return 2; //wrong character  
      }
      if (name.length() > 256 | name.length() <= 1) {return 1;}
      else return 0;
    } 


  /**
   * Checks whether the Syntax of the Stocknumber is correct
   * @return false if the Syntax is correct, else true
   * @param Stocknumber String of the Stocknumber TextField
	 */
  
private int checkStocknumber(String Stocknumber) {//TODO Never uses Catch
      try {
        Integer.parseInt(Stocknumber);
        if (Stocknumber.length() == 6) return 0;
        else return  1;// else: Zahl zu groÃŸ  
        }
      catch(NumberFormatException ee) {
        return 2; //print: keine Buchstaben   
      }
    }
  
/**
 * Checks whether the Syntax of the price is correct
 * @return false if the Syntax is correct, else true
 * @param price String of the price TextField
	 */
private int checkPrice(String price)  {
	char[] charArray = price.toCharArray();
	  int aaa = 0;
	  for (int aa = 0; aa < price.length(); aa++) {
	    if (charArray[aa] == ',') {
	      aaa++;
	    }  
	  }
	  if (aaa >= 2) return 1;
      String[] teil = price.split(",");
      if (teil.length == 2) {
        String i = teil[0];
        String a = teil[1];

        try {
        int inti = Integer.parseInt(i);
        int inta = Integer.parseInt(a);
        if (inti < 0 | inta < 0) return 1;
          if ((i.length() <= 5) & (a.length() <= 2)) { return 0;}
          if (i.length() > 5) {return 2;}
          if (a.length() > 2) {return 3;}
          else return 1; //Number to Big/Small
        }
        catch(NumberFormatException ee) {
          return 1;
        }
      }
      else if (teil.length == 1) {
        String u = teil[0];   
       
        try {
        int intu = Integer.parseInt(u);
        if (intu <= 0) return 1;
          if (u.length() <= 5) { return 0;}
          else return 1;
        }
        catch(NumberFormatException ee) {
          return 1;
        }

      }
      else return 1;
    }
  
/**
 * Checks whether the Syntax of the weight is correct
 * @return false if the Syntax is correct, else true
 * @param weight String of the weight TextField
	 */
private static int checkWeight(String weight)  {  //Input in Kg
	char[] charArray = weight.toCharArray();
	  int aaa = 0;
	  for (int aa = 0; aa < weight.length(); aa++) {
	    if (charArray[aa] == ',') {
	      aaa++;
	    }  
	  }
	  if (aaa >= 2) return 1;
      String[] part = weight.split(",");
      if (part.length == 2) {
        String i = part[0];
        String a = part[1];

        try {
          int inti = Integer.parseInt(i);
          int inta = Integer.parseInt(a);
          if (inti < 0 | inta < 0) return 1;
          if ((i.length() <= 5) & (a.length() <= 4)) { return 0;}
          if (i.length() > 5) {return 2;}
          if (a.length() > 4) {return 3;}
          else return 1; 
        }
        catch(Exception ee) {
          return 1;
        }
      }
      else if (part.length == 1) {
        
        String u = part[0];   
        try {
        int intu = Integer.parseInt(u);
        if (intu <= 0) return 1;
          if (u.length() <= 4 | intu == 10000) { return 0;}
          if (u.length() >= 5) {return 2;}
          else return 1;
        }
        catch(NumberFormatException ee) {
          return 1;
        }

      }
      else return 1;
    }
/**
 * /**
 * Checks whether the Syntax of the Quantity is correct
 * @return false if the Syntax is correct, else true
 * @param quantity String of the quantity TextField
 */
private int checkQuantity(String quantity) {
      try {
        
        int a = Integer.parseInt(quantity);
        if (a < 0) return 1;
        if (a <= 100000000) return 0;// else: Zahl zu groÃŸ 
        
        }
      catch(Exception ee) {
         return 1;
      }
      return 2;
    }

/**
 * Converts the String in a usable integer
 * @return the String as integer (Kg to decigramm)
 * @param a String to transform
	 */

public static int parseToInt (String a) {
    String[] teil = a.split(",");
    if (teil.length == 1) {
      int i = Integer.parseInt(teil[0]);
      i = i*10000;
      return i;
    }
    else {
      String neu = teil[0]+teil[1];
      int o = Integer.parseInt(neu);
      int nachkomma = 4-teil[1].length();
      for (int ii=0; ii < nachkomma; ii++) {
        o = o*10;
      }
      return o;
    }
          
  }  

  
}
