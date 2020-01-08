package start.database.singleProduct;

public class Product extends ProductData{


	public Product(String name, int weight, int amount, int price,
			int number, String category/*, Product prev, Product next*/) {
		
		setProductData(name, weight, amount, price, number, category);
	}
	
	
	//IN CASE WE GO WITH RINGS
	/****************************************
	
    private Product prev; //previous Product in Ring "left"
    private Product next; //next Product in Ring "right"
	//Next & Prev
    public void setPrev(Product prev) {
    	this.prev = prev;
    }
    
    public void setNext(Product next) {
    	this.next = next;
    }
    
    public Product getNext() {
    	return this.next;
    }
    
    public Product getPrev() {
    	return this.prev;
    }
    ************************************/
    
    
}	
